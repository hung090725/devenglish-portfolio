package hung.edu.apptienganh.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hung.edu.apptienganh.ai.AiCallback;
import hung.edu.apptienganh.ai.AiConfig;

public class GeminiVisionClient {

    private static volatile GeminiVisionClient sInstance;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler;

    private GeminiVisionClient() {
        Handler h;
        try {
            h = (Looper.getMainLooper() != null) ? new Handler(Looper.getMainLooper()) : null;
        } catch (Throwable t) {
            h = null;
        }
        this.mainHandler = h;
    }

    public static GeminiVisionClient getInstance() {
        if (sInstance == null) {
            synchronized (GeminiVisionClient.class) {
                if (sInstance == null) {
                    sInstance = new GeminiVisionClient();
                }
            }
        }
        return sInstance;
    }

    private void post(Runnable r) {
        if (mainHandler != null) {
            mainHandler.post(r);
        } else {
            r.run();
        }
    }

    public void analyzeImageDeeply(Context context, Bitmap bitmap, AiCallback<VisualWordInfo> callback) {
        if (bitmap == null) {
            post(() -> callback.onError("Không có hình ảnh để phân tích."));
            return;
        }

        executorService.execute(() -> {
            boolean hasApiKey = AiConfig.hasApiKey(context);
            if (hasApiKey) {
                try {
                    VisualWordInfo result = callGeminiVision(context, bitmap);
                    post(() -> callback.onSuccess(result));
                    return;
                } catch (Exception e) {
                    if (AiConfig.isOfflineFallbackAllowed(context)) {
                        VisualWordInfo fallback = VisualVocabularyDatabase.lookup("object", 0.90f);
                        fallback.setDetailedAiNotesVi("Lỗi kết nối AI: " + e.getMessage() + ". Đã hiển thị dữ liệu từ điển ngoại tuyến.");
                        post(() -> callback.onSuccess(fallback));
                        return;
                    }
                    post(() -> callback.onError("Lỗi phân tích AI Vision: " + e.getMessage()));
                    return;
                }
            }

            // Offline smart fallback
            VisualWordInfo offline = VisualVocabularyDatabase.lookup("object", 0.90f);
            offline.setDetailedAiNotesVi("Chế độ ngoại tuyến: Để AI phân tích chi tiết từng góc cạnh nhỏ nhất, bạn có thể nhập Gemini API Key trong phần Cài đặt.");
            post(() -> callback.onSuccess(offline));
        });
    }

    private VisualWordInfo callGeminiVision(Context context, Bitmap original) throws Exception {
        String apiKey = AiConfig.getApiKey(context);
        String model = AiConfig.getModel(context);
        String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent?key=" + apiKey;

        // Resize bitmap to max dimension 800 to optimize speed and payload
        int maxDim = 800;
        int width = original.getWidth();
        int height = original.getHeight();
        float ratio = Math.min((float) maxDim / width, (float) maxDim / height);
        Bitmap scaled = (ratio < 1.0f) ? Bitmap.createScaledBitmap(original, Math.round(width * ratio), Math.round(height * ratio), true) : original;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        scaled.compress(Bitmap.CompressFormat.JPEG, 85, baos);
        byte[] imageBytes = baos.toByteArray();
        String base64Image = Base64.encodeToString(imageBytes, Base64.NO_WRAP);

        JSONObject requestJson = new JSONObject();

        // System Instruction
        JSONObject sysInstruction = new JSONObject();
        JSONArray sysParts = new JSONArray();
        JSONObject sysPartText = new JSONObject();
        sysPartText.put("text", "You are an expert English visual dictionary for Vietnamese learners.\n"
                + "Analyze the image. Identify the PRIMARY object, subtle detail, or specific item in focus.\n"
                + "Return strictly a JSON object with this exact schema:\n"
                + "{\n"
                + "  \"wordEn\": \"(English word or exact phrase)\",\n"
                + "  \"ipa\": \"/(International Phonetic Alphabet)/\",\n"
                + "  \"meaningVi\": \"(Nghĩa tiếng Việt chuẩn xác nhất)\",\n"
                + "  \"wordType\": \"(danh từ / động từ / tính từ)\",\n"
                + "  \"cefrLevel\": \"(A1/A2/B1/B2/C1)\",\n"
                + "  \"collocationEn\": \"(Cụm từ tiếng Anh hay gặp)\",\n"
                + "  \"collocationVi\": \"(Dịch nghĩa cụm từ)\",\n"
                + "  \"exampleSentenceEn\": \"(Một câu ví dụ hoàn chỉnh có chứa từ này)\",\n"
                + "  \"exampleSentenceVi\": \"(Dịch câu ví dụ sang tiếng Việt)\",\n"
                + "  \"detailedAiNotesVi\": \"(Mô tả chi tiết đặc điểm của vật thể này bằng tiếng Việt)\"\n"
                + "}");
        sysParts.put(sysPartText);
        sysInstruction.put("parts", sysParts);
        requestJson.put("systemInstruction", sysInstruction);

        // Contents
        JSONArray contents = new JSONArray();
        JSONObject turn = new JSONObject();
        turn.put("role", "user");
        JSONArray parts = new JSONArray();

        // Part 1: Inline Data (Image)
        JSONObject imgPart = new JSONObject();
        JSONObject inlineData = new JSONObject();
        inlineData.put("mimeType", "image/jpeg");
        inlineData.put("data", base64Image);
        imgPart.put("inlineData", inlineData);
        parts.put(imgPart);

        // Part 2: Prompt Text
        JSONObject txtPart = new JSONObject();
        txtPart.put("text", "Identify the primary object in this camera shot with exact English word, IPA, meaning, phrase, sentence and Vietnamese translation.");
        parts.put(txtPart);

        turn.put("parts", parts);
        contents.put(turn);
        requestJson.put("contents", contents);

        JSONObject genConfig = new JSONObject();
        genConfig.put("temperature", 0.3);
        genConfig.put("maxOutputTokens", 600);
        requestJson.put("generationConfig", genConfig);

        String raw = executePost(endpoint, requestJson.toString());
        return parseVisionResponse(raw);
    }

    private VisualWordInfo parseVisionResponse(String rawJson) throws Exception {
        JSONObject root = new JSONObject(rawJson);
        JSONArray candidates = root.optJSONArray("candidates");
        if (candidates != null && candidates.length() > 0) {
            JSONObject content = candidates.getJSONObject(0).optJSONObject("content");
            if (content != null) {
                JSONArray parts = content.optJSONArray("parts");
                if (parts != null && parts.length() > 0) {
                    String text = parts.getJSONObject(0).optString("text", "").trim();
                    if (text.startsWith("```json")) text = text.substring(7);
                    if (text.startsWith("```")) text = text.substring(3);
                    if (text.endsWith("```")) text = text.substring(0, text.length() - 3);

                    JSONObject o = new JSONObject(text.trim());
                    VisualWordInfo info = new VisualWordInfo(
                            o.optString("wordEn", "Object"),
                            o.optString("ipa", "/ˈɒb.dʒɪkt/"),
                            o.optString("meaningVi", "Vật thể"),
                            o.optString("wordType", "danh từ (noun)"),
                            o.optString("cefrLevel", "A2"),
                            o.optString("collocationEn", ""),
                            o.optString("collocationVi", ""),
                            o.optString("exampleSentenceEn", ""),
                            o.optString("exampleSentenceVi", "")
                    );
                    info.setDetailedAiNotesVi(o.optString("detailedAiNotesVi", ""));
                    info.setConfidence(0.98f);
                    return info;
                }
            }
        }
        throw new Exception("Không nhận được dữ liệu hợp lệ từ AI Vision.");
    }

    private String executePost(String urlString, String jsonBody) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setConnectTimeout(20000);
        conn.setReadTimeout(25000);
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int code = conn.getResponseCode();
        InputStream is = (code >= 200 && code < 300) ? conn.getInputStream() : conn.getErrorStream();
        if (is == null) throw new Exception("HTTP error code: " + code);

        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append('\n');
        }
        br.close();

        if (code >= 400) {
            throw new Exception("Vision API (" + code + "): " + sb.toString());
        }

        return sb.toString();
    }
}
