package hung.edu.apptienganh.domain.scorer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class SpeakingScorer {

    public static class SpeakingScoreResult {
        private final int score;
        private final List<String> correctWords;
        private final List<String> missingWords;
        private final List<String> extraWords;
        private final List<String> wrongWords;
        private final String feedbackVi;

        public SpeakingScoreResult(int score, List<String> correctWords, List<String> missingWords,
                                   List<String> extraWords, List<String> wrongWords, String feedbackVi) {
            this.score = score;
            this.correctWords = correctWords;
            this.missingWords = missingWords;
            this.extraWords = extraWords;
            this.wrongWords = wrongWords;
            this.feedbackVi = feedbackVi;
        }

        public int getScore() {
            return score;
        }

        public List<String> getCorrectWords() {
            return correctWords;
        }

        public List<String> getMissingWords() {
            return missingWords;
        }

        public List<String> getExtraWords() {
            return extraWords;
        }

        public List<String> getWrongWords() {
            return wrongWords;
        }

        public String getFeedbackVi() {
            return feedbackVi;
        }
    }

    public static SpeakingScoreResult calculateScore(String target, String recognized) {
        if (target == null || target.trim().isEmpty()) {
            return emptyResult(0, "Không có câu mẫu để so sánh.");
        }
        if (recognized == null || recognized.trim().isEmpty()) {
            List<String> targetWords = splitWords(normalizeText(target));
            return new SpeakingScoreResult(0, new ArrayList<>(), targetWords, new ArrayList<>(),
                    new ArrayList<>(), "Bạn cần nói chậm hơn và rõ từng từ.");
        }

        String normalizedTarget = normalizeText(target);
        String normalizedRecognized = normalizeText(recognized);

        List<String> targetWords = splitWords(normalizedTarget);
        List<String> recognizedWords = splitWords(normalizedRecognized);

        List<String> correctWords = getLCS(targetWords, recognizedWords);

        List<String> missingWords = new ArrayList<>();
        List<String> tempTarget = new ArrayList<>(targetWords);
        for (String word : correctWords) {
            tempTarget.remove(word);
        }
        missingWords.addAll(tempTarget);

        List<String> extraWords = new ArrayList<>();
        List<String> tempRecognized = new ArrayList<>(recognizedWords);
        for (String word : correctWords) {
            tempRecognized.remove(word);
        }
        extraWords.addAll(tempRecognized);

        int score = targetWords.isEmpty() ? 0
                : (int) Math.round(((double) correctWords.size() / targetWords.size()) * 100.0);
        if (score > 100) score = 100;

        String feedback = buildFeedbackVi(score, missingWords, extraWords);
        return new SpeakingScoreResult(score, correctWords, missingWords, extraWords, extraWords, feedback);
    }

    private static SpeakingScoreResult emptyResult(int score, String feedback) {
        return new SpeakingScoreResult(score, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), feedback);
    }

    private static String buildFeedbackVi(int score, List<String> missingWords, List<String> extraWords) {
        if (score >= 90) {
            return "Xuất sắc! Bạn phát âm rất gần với câu mẫu.";
        }
        if (score >= 80) {
            return "Bạn nói đúng phần lớn câu. Hãy luyện thêm một lần nữa cho tự nhiên hơn.";
        }
        if (score >= 50) {
            StringBuilder sb = new StringBuilder("Câu của bạn gần đúng, hãy luyện lại 2 lần nữa.");
            if (!missingWords.isEmpty()) {
                sb.append(" Hãy chú ý thiếu từ '").append(missingWords.get(0)).append("'.");
            }
            return sb.toString();
        }
        if (!missingWords.isEmpty()) {
            return "Bạn nói đúng phần lớn câu. Hãy chú ý thiếu từ '" + missingWords.get(0) + "'.";
        }
        if (!extraWords.isEmpty()) {
            return "Bạn thêm từ không cần thiết ('" + extraWords.get(0) + "'). Hãy nói chậm và đúng câu mẫu.";
        }
        return "Bạn cần nói chậm hơn và rõ từng từ.";
    }

    private static String normalizeText(String text) {
        if (text == null) return "";
        return text.toLowerCase(Locale.ROOT)
                .replaceAll("[.,?!:;\"()'\\[\\]-]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    private static List<String> splitWords(String text) {
        List<String> list = new ArrayList<>();
        if (text.isEmpty()) return list;
        String[] words = text.split(" ");
        for (String w : words) {
            if (!w.trim().isEmpty()) {
                list.add(w.trim());
            }
        }
        return list;
    }

    private static List<String> getLCS(List<String> list1, List<String> list2) {
        int m = list1.size();
        int n = list2.size();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (list1.get(i - 1).equals(list2.get(j - 1))) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        List<String> result = new ArrayList<>();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (list1.get(i - 1).equals(list2.get(j - 1))) {
                result.add(list1.get(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        Collections.reverse(result);
        return result;
    }
}
