package hung.edu.apptienganh.engine;

import hung.edu.apptienganh.data.local.entity.WritingAttempt;
import hung.edu.apptienganh.data.local.entity.WritingPrompt;

public class WritingScorer {

    public WritingAttempt scoreAttempt(WritingPrompt prompt, String userAnswer) {
        WritingAttempt attempt = new WritingAttempt();
        attempt.promptId = prompt.id;
        attempt.userAnswer = userAnswer;
        attempt.createdAt = System.currentTimeMillis();
        
        if (userAnswer == null || userAnswer.trim().isEmpty()) {
            attempt.score = 0;
            attempt.feedbackVi = "Bạn chưa nhập câu trả lời.";
            return attempt;
        }

        // Rule-based basic scoring
        int score = 100;
        StringBuilder feedback = new StringBuilder();

        // 1. Minimum words check
        String[] words = userAnswer.trim().split("\\s+");
        if (words.length < 3) {
            score -= 20;
            feedback.append("Câu trả lời hơi ngắn. ");
        }

        // 2. Expected keywords
        if (prompt.expectedKeywords != null && !prompt.expectedKeywords.isEmpty()) {
            String[] keywords = prompt.expectedKeywords.split(",");
            int matched = 0;
            for (String kw : keywords) {
                if (userAnswer.toLowerCase().contains(kw.trim().toLowerCase())) {
                    matched++;
                }
            }
            if (matched < keywords.length) {
                score -= (keywords.length - matched) * 10;
                feedback.append("Có vẻ bạn thiếu một vài từ khoá quan trọng. ");
            }
        }

        // 3. Simple punctuation check
        if (!userAnswer.matches(".*[.?!]$")) {
            score -= 5;
            feedback.append("Chú ý thêm dấu câu ở cuối câu nhé. ");
        }

        if (score < 0) score = 0;

        attempt.score = score;
        if (feedback.length() == 0) {
            attempt.feedbackVi = "Rất tốt! Phản hồi tự động cơ bản không tìm thấy lỗi lớn.";
        } else {
            attempt.feedbackVi = feedback.toString().trim();
        }

        return attempt;
    }
}
