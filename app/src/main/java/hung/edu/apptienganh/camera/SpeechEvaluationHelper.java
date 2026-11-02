package hung.edu.apptienganh.camera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Evaluates user spoken pronunciation against detected target vocabulary
 * (Ý tưởng 9: So Khớp Giọng Đọc AI & Chấm Điểm Phát Âm).
 */
public class SpeechEvaluationHelper {

    public interface EvaluationCallback {
        void onListeningStarted();
        void onEvaluationResult(String spokenText, int scorePercent, String feedbackVi, boolean isExcellent);
        void onError(String errorMessage);
    }

    private final Context context;
    private SpeechRecognizer speechRecognizer;
    private boolean isListening = false;

    public SpeechEvaluationHelper(Context context) {
        this.context = context;
    }

    public void startListening(String expectedWord, EvaluationCallback callback) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            callback.onError("Thiết bị không hỗ trợ nhận diện giọng nói SpeechRecognizer.");
            return;
        }

        stopListening();

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                isListening = true;
                callback.onListeningStarted();
            }

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {
                isListening = false;
            }

            @Override
            public void onError(int error) {
                isListening = false;
                String msg = "Không nghe rõ. Hãy nói gần mic hơn và thử lại nhé.";
                if (error == SpeechRecognizer.ERROR_NO_MATCH) {
                    msg = "Chưa phát hiện được giọng nói tiếng Anh. Hãy thử lại!";
                }
                callback.onError(msg);
            }

            @Override
            public void onResults(Bundle results) {
                isListening = false;
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    String bestSpoken = matches.get(0).trim();
                    evaluatePronunciation(bestSpoken, expectedWord, callback);
                } else {
                    callback.onError("Không nhận diện được từ vừa phát âm.");
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {}

            @Override
            public void onEvent(int eventType, Bundle params) {}
        });

        speechRecognizer.startListening(intent);
    }

    public void stopListening() {
        if (speechRecognizer != null) {
            try {
                speechRecognizer.stopListening();
                speechRecognizer.destroy();
            } catch (Exception ignored) {}
            speechRecognizer = null;
        }
        isListening = false;
    }

    public boolean isListening() {
        return isListening;
    }

    public static int calculateSimilarityScore(String spoken, String expected) {
        if (spoken == null || expected == null) return 0;

        String cleanSpoken = spoken.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]", "");
        String cleanExpected = expected.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]", "");

        if (cleanSpoken.equals(cleanExpected)) return 100;
        if (cleanSpoken.contains(cleanExpected) || cleanExpected.contains(cleanSpoken)) return 92;

        int distance = computeLevenshtein(cleanSpoken, cleanExpected);
        int maxLen = Math.max(cleanSpoken.length(), cleanExpected.length());
        if (maxLen == 0) return 100;

        float ratio = 1.0f - ((float) distance / (float) maxLen);
        int score = Math.round(ratio * 100f);
        return Math.max(10, Math.min(100, score));
    }

    private void evaluatePronunciation(String spoken, String expected, EvaluationCallback callback) {
        int score = calculateSimilarityScore(spoken, expected);

        String feedback;
        boolean isExcellent = false;

        if (score >= 90) {
            feedback = "⭐⭐⭐ Xuất sắc (" + score + "%). Phát âm cực kỳ chuẩn xác và tự nhiên!";
            isExcellent = true;
        } else if (score >= 75) {
            feedback = "⭐⭐ Khá tốt (" + score + "%). Bạn đã nói rất gần đúng, chú ý thêm âm đuôi (ending sound) nhé.";
        } else if (score >= 55) {
            feedback = "⭐ Tạm được (" + score + "%). Hãy bấm nghe loa máy đọc lại một lần rồi thử đọc lại.";
        } else {
            feedback = "Cần cố gắng thêm (" + score + "%). Nghe kỹ âm tiết và phát âm to rõ hơn nhé.";
        }

        callback.onEvaluationResult(spoken, score, feedback, isExcellent);
    }

    private static int computeLevenshtein(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + cost
                    );
                }
            }
        }

        return dp[a.length()][b.length()];
    }
}
