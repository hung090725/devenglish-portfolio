package hung.edu.apptienganh.ai;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AiTutorEngine {

    private static volatile AiTutorEngine sInstance;
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    private AiTutorEngine() {}

    private void postToMain(Runnable r) {
        try {
            if (Looper.getMainLooper() != null) {
                new Handler(Looper.getMainLooper()).post(r);
                return;
            }
        } catch (Throwable ignored) {}
        r.run();
    }

    public static AiTutorEngine getInstance() {
        if (sInstance == null) {
            synchronized (AiTutorEngine.class) {
                if (sInstance == null) {
                    sInstance = new AiTutorEngine();
                }
            }
        }
        return sInstance;
    }

    // ==========================================
    // 1. AI CONVERSATION PARTNER
    // ==========================================
    public void sendMessage(Context context, String topic, List<AiMessage> history,
                            String userMessage, AiCallback<AiMessage> callback) {
        if (userMessage == null || userMessage.trim().isEmpty()) {
            postToMain(() -> callback.onError("Tin nhắn không được để trống."));
            return;
        }

        executorService.execute(() -> {
            boolean hasApiKey = AiConfig.hasApiKey(context);
            if (hasApiKey) {
                try {
                    AiMessage response = callGeminiChat(context, topic, history, userMessage);
                    postToMain(() -> callback.onSuccess(response));
                    return;
                } catch (Exception e) {
                    // Fall back to offline pedagogical engine if permitted
                    if (AiConfig.isOfflineFallbackAllowed(context)) {
                        AiMessage offlineResponse = generateOfflineChatResponse(topic, history, userMessage);
                        postToMain(() -> callback.onSuccess(offlineResponse));
                        return;
                    }
                    postToMain(() -> callback.onError("Lỗi kết nối AI: " + e.getMessage()));
                    return;
                }
            }

            // Offline smart mode
            AiMessage offlineResponse = generateOfflineChatResponse(topic, history, userMessage);
            postToMain(() -> callback.onSuccess(offlineResponse));
        });
    }

    // ==========================================
    // 2. AI WRITING TUTOR
    // ==========================================
    public void reviewWriting(Context context, String topic, String userText,
                              AiCallback<AiWritingReview> callback) {
        if (userText == null || userText.trim().isEmpty()) {
            postToMain(() -> callback.onError("Vui lòng nhập bài viết cần chấm."));
            return;
        }

        executorService.execute(() -> {
            boolean hasApiKey = AiConfig.hasApiKey(context);
            if (hasApiKey) {
                try {
                    AiWritingReview review = callGeminiWritingReview(context, topic, userText);
                    postToMain(() -> callback.onSuccess(review));
                    return;
                } catch (Exception e) {
                    if (AiConfig.isOfflineFallbackAllowed(context)) {
                        AiWritingReview offline = generateOfflineWritingReview(topic, userText);
                        postToMain(() -> callback.onSuccess(offline));
                        return;
                    }
                    postToMain(() -> callback.onError("Lỗi AI chấm bài: " + e.getMessage()));
                    return;
                }
            }

            AiWritingReview offline = generateOfflineWritingReview(topic, userText);
            postToMain(() -> callback.onSuccess(offline));
        });
    }

    // ==========================================
    // 3. AI EXPLAINER ("Hỏi AI vì sao câu này sai")
    // ==========================================
    public void explainQuestion(Context context, String question, List<String> options,
                                String correctAnswer, String userAnswer,
                                AiCallback<String> callback) {
        executorService.execute(() -> {
            boolean hasApiKey = AiConfig.hasApiKey(context);
            if (hasApiKey) {
                try {
                    String explanation = callGeminiExplainer(context, question, options, correctAnswer, userAnswer);
                    postToMain(() -> callback.onSuccess(explanation));
                    return;
                } catch (Exception e) {
                    if (AiConfig.isOfflineFallbackAllowed(context)) {
                        String offline = generateOfflineExplanation(question, options, correctAnswer, userAnswer);
                        postToMain(() -> callback.onSuccess(offline));
                        return;
                    }
                    postToMain(() -> callback.onError("Không thể lấy giải thích từ AI: " + e.getMessage()));
                    return;
                }
            }

            String offline = generateOfflineExplanation(question, options, correctAnswer, userAnswer);
            postToMain(() -> callback.onSuccess(offline));
        });
    }

    // ==========================================
    // GEMINI NETWORK CALLS
    // ==========================================
    private AiMessage callGeminiChat(Context context, String topic, List<AiMessage> history, String userMessage) throws Exception {
        String apiKey = AiConfig.getApiKey(context);
        String model = AiConfig.getModel(context);
        String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent?key=" + apiKey;

        JSONObject requestJson = new JSONObject();
        
        // System instruction
        JSONObject sysInstruction = new JSONObject();
        JSONArray sysParts = new JSONArray();
        JSONObject sysPartText = new JSONObject();
        sysPartText.put("text", "You are Antigravity AI Tutor, a warm, professional, encouraging English conversation partner for Vietnamese learners.\n"
                + "Topic: " + (topic != null ? topic : "General Conversation") + ".\n"
                + "Rules:\n"
                + "1. Reply in English with 1-3 natural, engaging sentences to keep the conversation going.\n"
                + "2. Inspect the user's sentence. If there are grammatical errors, odd word choices, or unnatural phrasing, provide a constructive, polite feedback tip in Vietnamese, followed by a 'Gợi ý câu chuẩn bản xứ:' rewrite. If the user's sentence was already great, you can provide a small compliment or alternative way to say it in Vietnamese.\n"
                + "3. Output MUST strictly adhere to this format:\n"
                + "[REPLY]\n(Your conversational English reply here)\n"
                + "[FEEDBACK_VI]\n(Your Vietnamese grammar/phrasing feedback & native suggestion here, or leave blank if completely perfect)\n");
        sysParts.put(sysPartText);
        sysInstruction.put("parts", sysParts);
        requestJson.put("systemInstruction", sysInstruction);

        // Contents
        JSONArray contents = new JSONArray();
        if (history != null) {
            int start = Math.max(0, history.size() - 6);
            for (int i = start; i < history.size(); i++) {
                AiMessage m = history.get(i);
                JSONObject turn = new JSONObject();
                turn.put("role", m.isUser() ? "user" : "model");
                JSONArray parts = new JSONArray();
                JSONObject part = new JSONObject();
                part.put("text", m.getText());
                parts.put(part);
                turn.put("parts", parts);
                contents.put(turn);
            }
        }

        JSONObject currentTurn = new JSONObject();
        currentTurn.put("role", "user");
        JSONArray curParts = new JSONArray();
        JSONObject curPart = new JSONObject();
        curPart.put("text", userMessage);
        curParts.put(curPart);
        currentTurn.put("parts", curParts);
        contents.put(currentTurn);

        requestJson.put("contents", contents);

        JSONObject genConfig = new JSONObject();
        genConfig.put("temperature", 0.7);
        genConfig.put("maxOutputTokens", 600);
        requestJson.put("generationConfig", genConfig);

        String rawResponse = executePostRequest(endpoint, requestJson.toString());
        return parseGeminiChatResponse(rawResponse);
    }

    private AiWritingReview callGeminiWritingReview(Context context, String topic, String userText) throws Exception {
        String apiKey = AiConfig.getApiKey(context);
        String model = AiConfig.getModel(context);
        String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent?key=" + apiKey;

        JSONObject requestJson = new JSONObject();
        JSONObject sysInstruction = new JSONObject();
        JSONArray sysParts = new JSONArray();
        JSONObject sysPartText = new JSONObject();
        sysPartText.put("text", "You are an expert English writing examiner. Analyze this writing submission by a Vietnamese student.\n"
                + "Topic: " + (topic != null ? topic : "Free Writing") + ".\n"
                + "Return JSON only with this schema:\n"
                + "{\n"
                + "  \"score\": (integer 0-100),\n"
                + "  \"summaryVi\": \"(Nhận xét tổng quan bằng tiếng Việt)\",\n"
                + "  \"naturalRewriteEn\": \"(Bản viết lại hoàn chỉnh tự nhiên chuẩn bản xứ bằng tiếng Anh)\",\n"
                + "  \"grammarTips\": [\"(Mẹo 1)\", \"(Mẹo 2)\"],\n"
                + "  \"corrections\": [\n"
                + "     {\"original\": \"...\", \"improved\": \"...\", \"explanationVi\": \"...\"}\n"
                + "  ]\n"
                + "}");
        sysParts.put(sysPartText);
        sysInstruction.put("parts", sysParts);
        requestJson.put("systemInstruction", sysInstruction);

        JSONArray contents = new JSONArray();
        JSONObject turn = new JSONObject();
        turn.put("role", "user");
        JSONArray parts = new JSONArray();
        JSONObject p = new JSONObject();
        p.put("text", userText);
        parts.put(p);
        turn.put("parts", parts);
        contents.put(turn);
        requestJson.put("contents", contents);

        String raw = executePostRequest(endpoint, requestJson.toString());
        return parseGeminiWritingReviewResponse(raw, userText);
    }

    private String callGeminiExplainer(Context context, String question, List<String> options,
                                       String correctAnswer, String userAnswer) throws Exception {
        String apiKey = AiConfig.getApiKey(context);
        String model = AiConfig.getModel(context);
        String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent?key=" + apiKey;

        JSONObject requestJson = new JSONObject();
        JSONArray contents = new JSONArray();
        JSONObject turn = new JSONObject();
        turn.put("role", "user");
        JSONArray parts = new JSONArray();
        JSONObject p = new JSONObject();
        
        StringBuilder prompt = new StringBuilder();
        prompt.append("Giải thích câu hỏi trắc nghiệm tiếng Anh cho học viên Việt Nam:\n");
        prompt.append("Câu hỏi: ").append(question).append("\n");
        if (options != null && !options.isEmpty()) {
            prompt.append("Các lựa chọn: ").append(options.toString()).append("\n");
        }
        prompt.append("Đáp án đúng: ").append(correctAnswer).append("\n");
        if (userAnswer != null && !userAnswer.isEmpty()) {
            prompt.append("Học viên đã chọn: ").append(userAnswer).append("\n");
        }
        prompt.append("\nYêu cầu: Viết lời giải thích súc tích bằng tiếng Việt, bao gồm:\n")
              .append("1. Phân tích vì sao đáp án đúng là chính xác (ngữ pháp / từ vựng).\n")
              .append("2. Nếu người học chọn sai, chỉ ra bẫy khiến người học dễ nhầm.\n")
              .append("3. Cho 1 câu ví dụ minh họa kèm dịch nghĩa tiếng Việt.");

        p.put("text", prompt.toString());
        parts.put(p);
        turn.put("parts", parts);
        contents.put(turn);
        requestJson.put("contents", contents);

        String raw = executePostRequest(endpoint, requestJson.toString());
        JSONObject root = new JSONObject(raw);
        JSONArray candidates = root.optJSONArray("candidates");
        if (candidates != null && candidates.length() > 0) {
            JSONObject content = candidates.getJSONObject(0).optJSONObject("content");
            if (content != null) {
                JSONArray resParts = content.optJSONArray("parts");
                if (resParts != null && resParts.length() > 0) {
                    return resParts.getJSONObject(0).optString("text", "Không có nội dung giải thích.");
                }
            }
        }
        return "Không thể phân tích phản hồi từ AI.";
    }

    private String executePostRequest(String urlString, String jsonBody) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(20000);
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();
        InputStream is = (responseCode >= 200 && responseCode < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        if (is == null) {
            throw new Exception("Lỗi mạng HTTP code: " + responseCode);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append('\n');
        }
        br.close();

        if (responseCode >= 400) {
            throw new Exception("AI API Error (" + responseCode + "): " + sb.toString());
        }

        return sb.toString();
    }

    private AiMessage parseGeminiChatResponse(String rawJson) {
        try {
            JSONObject root = new JSONObject(rawJson);
            JSONArray candidates = root.optJSONArray("candidates");
            if (candidates != null && candidates.length() > 0) {
                JSONObject content = candidates.getJSONObject(0).optJSONObject("content");
                if (content != null) {
                    JSONArray parts = content.optJSONArray("parts");
                    if (parts != null && parts.length() > 0) {
                        String fullText = parts.getJSONObject(0).optString("text", "");
                        return extractReplyAndFeedback(fullText);
                    }
                }
            }
        } catch (Exception ignored) {}
        return new AiMessage(AiMessage.SENDER_AI, "I'm glad to chat with you! Let's continue practicing English together.");
    }

    private AiMessage extractReplyAndFeedback(String fullText) {
        String reply = fullText;
        String feedback = null;

        if (fullText.contains("[REPLY]") && fullText.contains("[FEEDBACK_VI]")) {
            int replyIdx = fullText.indexOf("[REPLY]") + 7;
            int feedIdx = fullText.indexOf("[FEEDBACK_VI]");
            reply = fullText.substring(replyIdx, feedIdx).trim();
            feedback = fullText.substring(feedIdx + 13).trim();
        } else if (fullText.contains("[FEEDBACK_VI]")) {
            int feedIdx = fullText.indexOf("[FEEDBACK_VI]");
            reply = fullText.substring(0, feedIdx).trim();
            feedback = fullText.substring(feedIdx + 13).trim();
        }

        if (feedback != null && (feedback.equalsIgnoreCase("none") || feedback.isEmpty() || feedback.equals("null"))) {
            feedback = null;
        }

        return new AiMessage(AiMessage.SENDER_AI, reply, feedback);
    }

    private AiWritingReview parseGeminiWritingReviewResponse(String rawJson, String originalText) {
        AiWritingReview review = new AiWritingReview();
        try {
            JSONObject root = new JSONObject(rawJson);
            JSONArray candidates = root.optJSONArray("candidates");
            if (candidates != null && candidates.length() > 0) {
                JSONObject content = candidates.getJSONObject(0).optJSONObject("content");
                if (content != null) {
                    JSONArray parts = content.optJSONArray("parts");
                    if (parts != null && parts.length() > 0) {
                        String text = parts.getJSONObject(0).optString("text", "");
                        // Clean markdown json if wrapped in ```json ... ```
                        text = text.trim();
                        if (text.startsWith("```json")) {
                            text = text.substring(7);
                        }
                        if (text.startsWith("```")) {
                            text = text.substring(3);
                        }
                        if (text.endsWith("```")) {
                            text = text.substring(0, text.length() - 3);
                        }
                        JSONObject resObj = new JSONObject(text.trim());
                        review.setScore(resObj.optInt("score", 75));
                        review.setSummaryVi(resObj.optString("summaryVi", "Đã phân tích bài viết thành công."));
                        review.setNaturalRewriteEn(resObj.optString("naturalRewriteEn", ""));
                        
                        JSONArray tips = resObj.optJSONArray("grammarTips");
                        if (tips != null) {
                            List<String> tipList = new ArrayList<>();
                            for (int i = 0; i < tips.length(); i++) {
                                tipList.add(tips.getString(i));
                            }
                            review.setGrammarTips(tipList);
                        }

                        JSONArray corrs = resObj.optJSONArray("corrections");
                        if (corrs != null) {
                            List<AiWritingReview.SentenceCorrection> corrList = new ArrayList<>();
                            for (int i = 0; i < corrs.length(); i++) {
                                JSONObject c = corrs.getJSONObject(i);
                                corrList.add(new AiWritingReview.SentenceCorrection(
                                        c.optString("original", ""),
                                        c.optString("improved", ""),
                                        c.optString("explanationVi", "")
                                ));
                            }
                            review.setCorrections(corrList);
                        }
                        return review;
                    }
                }
            }
        } catch (Exception ignored) {}

        // Fallback to offline review if parsing fails
        return generateOfflineWritingReview(null, originalText);
    }

    // ==========================================
    // 4. SMART OFFLINE PEDAGOGICAL ENGINE
    // ==========================================
    public AiMessage generateOfflineChatResponse(String topic, List<AiMessage> history, String userMessage) {
        String trimmed = userMessage != null ? userMessage.trim() : "";
        String lower = trimmed.toLowerCase(Locale.ROOT);

        // Detect linguistic mistakes commonly made by Vietnamese learners
        String feedbackVi = detectCommonMistakes(lower, trimmed);

        // Generate contextual replies based on topic and keywords
        String reply;
        String topicLower = topic != null ? topic.toLowerCase(Locale.ROOT) : "";

        if (topicLower.contains("coffee") || topicLower.contains("cà phê")) {
            reply = respondCoffeeTopic(lower);
        } else if (topicLower.contains("interview") || topicLower.contains("phỏng vấn")) {
            reply = respondInterviewTopic(lower);
        } else if (topicLower.contains("travel") || topicLower.contains("airport") || topicLower.contains("du lịch")) {
            reply = respondTravelTopic(lower);
        } else if (topicLower.contains("shopping") || topicLower.contains("mua sắm")) {
            reply = respondShoppingTopic(lower);
        } else {
            reply = respondGeneralTopic(lower);
        }

        return new AiMessage(AiMessage.SENDER_AI, reply, feedbackVi);
    }

    private String detectCommonMistakes(String lower, String original) {
        List<String> tips = new ArrayList<>();

        if (lower.contains("i am agree")) {
            tips.add("💡 'agree' là động từ, nên nói: \"I agree\" thay vì \"I am agree\".");
        }
        if (lower.contains("i very like") || lower.contains("i very love")) {
            tips.add("💡 Trong tiếng Anh không nói 'very like', hãy dùng: \"I really like...\" hoặc \"I like it very much\".");
        }
        if (lower.matches(".*\\b(he|she|it)\\s+(go|like|want|need|have)\\b.*")) {
            tips.add("💡 Với chủ ngữ số ít (he, she, it) ở hiện tại đơn, nhớ chia động từ (goes, likes, wants, needs, has).");
        }
        if (lower.contains("good in english") || lower.contains("good in math")) {
            tips.add("💡 Khi nói giỏi môn gì, hãy dùng giới từ 'at': \"good at English\".");
        }
        if (lower.matches(".*\\bi have \\d+ years old.*")) {
            tips.add("💡 Nói về số tuổi hãy dùng động từ 'to be': \"I am ... years old\" (không dùng 'have').");
        }
        if (lower.contains("informations") || lower.contains("advices")) {
            tips.add("💡 'Information' và 'Advice' là danh từ không đếm được, không thêm đuôi '-s'.");
        }
        if (lower.contains("thank you very much") || lower.contains("thanks")) {
            // Good polite phrasing
        }

        if (original.length() > 4 && Character.isLowerCase(original.charAt(0))) {
            tips.add("📝 Nhớ viết hoa chữ cái đầu câu bạn nhé.");
        }
        if (original.length() > 6 && !original.matches(".*[.?!]$")) {
            tips.add("📝 Nhớ kết thúc câu bằng dấu chấm (.) hoặc dấu hỏi (?).");
        }

        if (tips.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (String tip : tips) {
            sb.append(tip).append("\n");
        }
        return sb.toString().trim();
    }

    private String respondCoffeeTopic(String lower) {
        if (lower.contains("hello") || lower.contains("hi")) {
            return "Hello! Welcome to Central Perk Cafe. What can I get started for you today?";
        }
        if (lower.contains("latte") || lower.contains("cappuccino") || lower.contains("coffee") || lower.contains("tea") || lower.contains("order")) {
            return "Excellent choice! Would you like that hot or iced? And what size would you prefer: small, medium, or large?";
        }
        if (lower.contains("hot") || lower.contains("iced") || lower.contains("ice") || lower.contains("medium") || lower.contains("large") || lower.contains("small")) {
            return "Got it! Any milk preference like oat milk or almond milk, and would you like some pastry with that?";
        }
        if (lower.contains("how much") || lower.contains("price") || lower.contains("cost")) {
            return "That comes to $4.50. Will you be paying by cash or credit card?";
        }
        if (lower.contains("card") || lower.contains("cash") || lower.contains("pay")) {
            return "Thank you! Here is your receipt. Your drink will be ready at the pick-up counter in just two minutes. Enjoy your day!";
        }
        return "Sounds wonderful! Is there anything else I can add to your order today?";
    }

    private String respondInterviewTopic(String lower) {
        if (lower.contains("hello") || lower.contains("hi") || lower.contains("morning") || lower.contains("afternoon")) {
            return "Good day! Thank you for coming in today. Could you start by introducing yourself briefly?";
        }
        if (lower.contains("my name") || lower.contains("i am") || lower.contains("experience") || lower.contains("graduated")) {
            return "That sounds very impressive. What do you consider your greatest strength, and how does it help you in teamwork?";
        }
        if (lower.contains("strength") || lower.contains("hardworking") || lower.contains("problem") || lower.contains("learn")) {
            return "I appreciate your insight. Could you share an example of a difficult challenge you faced and how you overcame it?";
        }
        if (lower.contains("challenge") || lower.contains("project") || lower.contains("solved") || lower.contains("difficult")) {
            return "Well done! Where do you see yourself professionally in the next three to five years?";
        }
        return "Thank you for sharing that. Do you have any questions for us about the company or the team culture?";
    }

    private String respondTravelTopic(String lower) {
        if (lower.contains("hello") || lower.contains("hi") || lower.contains("ticket") || lower.contains("passport")) {
            return "Hello! Welcome to the international check-in counter. May I see your passport and flight booking reference, please?";
        }
        if (lower.contains("here") || lower.contains("check in") || lower.contains("baggage") || lower.contains("luggage")) {
            return "Thank you. Do you have any check-in bags today, or just carry-on luggage?";
        }
        if (lower.contains("bag") || lower.contains("carry") || lower.contains("one") || lower.contains("two")) {
            return "Understood. Please place your bag on the scale. Would you prefer a window seat or an aisle seat?";
        }
        if (lower.contains("window") || lower.contains("aisle")) {
            return "Great, I've assigned you seat 14A. Boarding starts at gate B4 in 45 minutes. Have a wonderful flight!";
        }
        return "I'm here to help with your trip! Are you looking for the terminal, security gate, or taxi station?";
    }

    private String respondShoppingTopic(String lower) {
        if (lower.contains("looking for") || lower.contains("shirt") || lower.contains("shoes") || lower.contains("size")) {
            return "We have several options in that style! What color and size are you looking for?";
        }
        if (lower.contains("try") || lower.contains("fitting room")) {
            return "The fitting rooms are right around the corner on the left. Feel free to try it on and let me know how it fits!";
        }
        if (lower.contains("discount") || lower.contains("sale") || lower.contains("price")) {
            return "This item is currently 20% off for our weekend special! Would you like me to wrap it up for you?";
        }
        return "Welcome to our store! Let me know if you need any assistance finding sizes or styles.";
    }

    private String respondGeneralTopic(String lower) {
        if (lower.contains("how are you") || lower.contains("how do you do")) {
            return "I'm doing fantastic, thank you! How has your day been going so far?";
        }
        if (lower.contains("weather") || lower.contains("rain") || lower.contains("sunny") || lower.contains("hot") || lower.contains("cold")) {
            return "Talking about the weather is always great small talk! What kind of weather do you enjoy the most?";
        }
        if (lower.contains("hobby") || lower.contains("free time") || lower.contains("music") || lower.contains("movie") || lower.contains("book")) {
            return "That sounds like a relaxing way to spend your time! How often do you get to do that during the week?";
        }
        if (lower.contains("thank") || lower.contains("thanks")) {
            return "You're very welcome! You're making great progress in English. Keep it up!";
        }
        return "That's an interesting point! Could you elaborate more on that, or share why you feel that way?";
    }

    public AiWritingReview generateOfflineWritingReview(String topic, String userText) {
        AiWritingReview review = new AiWritingReview();
        String text = userText != null ? userText.trim() : "";
        String[] words = text.split("\\s+");
        int wordCount = words.length;

        int score = 65;
        if (wordCount >= 20) score += 15;
        else if (wordCount >= 10) score += 10;
        else if (wordCount >= 5) score += 5;

        List<String> tips = new ArrayList<>();
        List<AiWritingReview.SentenceCorrection> corrections = new ArrayList<>();

        String lower = text.toLowerCase(Locale.ROOT);
        if (lower.contains("i am agree")) {
            score -= 5;
            corrections.add(new AiWritingReview.SentenceCorrection(
                    "I am agree", "I agree",
                    "'agree' là động từ nên không đi kèm với động từ to be 'am'."));
        }
        if (lower.contains("i very like")) {
            score -= 5;
            corrections.add(new AiWritingReview.SentenceCorrection(
                    "I very like", "I really like / I like ... very much",
                    "Không dùng 'very' trực tiếp trước động từ; dùng 'really' hoặc đặt 'very much' ở cuối."));
        }
        if (lower.contains("good in english")) {
            score -= 5;
            corrections.add(new AiWritingReview.SentenceCorrection(
                    "good in English", "good at English",
                    "Cụm tính từ chuẩn là 'good at' khi nói về kỹ năng/môn học."));
        }

        // Sentence structure and punctuation
        if (!text.matches(".*[.?!]$")) {
            score -= 5;
            tips.add("Hãy nhớ đặt dấu câu (chấm, hỏi, cảm thán) ở cuối mỗi câu hoàn chỉnh.");
        }
        if (Character.isLowerCase(text.charAt(0))) {
            score -= 5;
            tips.add("Viết hoa chữ cái đầu tiên của câu để bài viết chuyên nghiệp hơn.");
        }

        if (wordCount < 10) {
            tips.add("Nên phát triển ý tưởng dài hơn bằng cách bổ sung lý do (because, since) hoặc ví dụ (for example).");
        } else {
            tips.add("Độ dài bài viết tốt! Hãy thử kết hợp thêm các liên từ (furthermore, in addition, however) để tạo tính mạch lạc.");
        }

        score = Math.max(40, Math.min(100, score));
        review.setScore(score);
        review.setSummaryVi("Bài viết thể hiện được ý tưởng cơ bản. " + (score >= 80 ? "Diễn đạt lưu loát và tự nhiên!" : "Cần lưu ý thêm về ngữ pháp và cấu trúc câu để đạt điểm cao hơn."));

        // Generate polished native rewrite
        String rewritten = text;
        rewritten = rewritten.replace("I am agree", "I agree")
                             .replace("i am agree", "I agree")
                             .replace("I very like", "I really like")
                             .replace("i very like", "I really like")
                             .replace("good in English", "good at English");
        if (!rewritten.isEmpty() && Character.isLowerCase(rewritten.charAt(0))) {
            rewritten = Character.toUpperCase(rewritten.charAt(0)) + rewritten.substring(1);
        }
        if (!rewritten.endsWith(".") && !rewritten.endsWith("!") && !rewritten.endsWith("?")) {
            rewritten = rewritten + ".";
        }
        review.setNaturalRewriteEn(rewritten);
        review.setGrammarTips(tips);
        review.setCorrections(corrections);

        return review;
    }

    public String generateOfflineExplanation(String question, List<String> options,
                                             String correctAnswer, String userAnswer) {
        StringBuilder sb = new StringBuilder();
        sb.append("🎯 **Phân tích chi tiết câu hỏi:**\n\n");
        sb.append("• **Câu hỏi:** \"").append(question).append("\"\n");
        sb.append("• **Đáp án chính xác:** **").append(correctAnswer).append("**\n");
        if (userAnswer != null && !userAnswer.isEmpty() && !userAnswer.equalsIgnoreCase(correctAnswer)) {
            sb.append("• **Lựa chọn của bạn:** ").append(userAnswer).append(" (Chưa chính xác)\n");
        }
        sb.append("\n📚 **Quy tắc ngữ pháp / Kiến thức trọng tâm:**\n");
        
        String qLower = question != null ? question.toLowerCase(Locale.ROOT) : "";
        if (qLower.contains("yesterday") || qLower.contains("ago") || qLower.contains("last")) {
            sb.append("Dấu hiệu nhận biết thì Quá khứ đơn (Past Simple): Trong câu có các trạng từ thời gian như 'yesterday', 'ago', 'last week/month/year', động từ bắt buộc chia ở dạng V2/V-ed.\n");
        } else if (qLower.contains("tomorrow") || qLower.contains("next") || qLower.contains("soon")) {
            sb.append("Dấu hiệu thì Tương lai đơn (Future Simple): Xuất hiện các từ chỉ tương lai như 'tomorrow', 'next week', cấu trúc thường dùng là 'will + V-bare' hoặc 'be going to + V-bare'.\n");
        } else if (qLower.contains("now") || qLower.contains("at the moment") || qLower.contains("currently")) {
            sb.append("Dấu hiệu thì Hiện tại tiếp diễn (Present Continuous): Diễn tả hành động đang xảy ra tại thời điểm nói ('now', 'at the moment'), cấu trúc là: S + am/is/are + V-ing.\n");
        } else if (qLower.contains("if")) {
            sb.append("Cấu trúc câu điều kiện (Conditional sentence): Cần xác định loại câu điều kiện (Loại 1 diễn tả sự việc có thể xảy ra, Loại 2 diễn tả sự việc không có thật ở hiện tại) để chia mệnh đề IF và mệnh đề chính cho hòa hợp.\n");
        } else {
            sb.append("Dựa vào sự hòa hợp giữa Chủ ngữ và Động từ (Subject-Verb Agreement), cấu trúc câu và từ loại (danh từ, động từ, tính từ, trạng từ) phù hợp nhất với ngữ cảnh để hoàn thiện câu một cách chính xác.\n");
        }

        sb.append("\n💡 **Lời khuyên ghi nhớ:**\n");
        sb.append("Khi làm bài, hãy luôn gạch chân từ khóa chỉ thời gian và xác định chủ ngữ chính của câu trước khi đưa ra lựa chọn cuối cùng.");

        return sb.toString();
    }
}
