package hung.edu.apptienganh.engine.audit.content;

import hung.edu.apptienganh.engine.audit.content.review.ContentReviewItem;
import java.util.ArrayList;
import java.util.List;

public class QuizAnswerVerificationAuditor {

    public List<ContentReviewItem> auditQuiz(String quizId, String level, String question, String optA, String optB, String optC, String optD, String correctAnswer) {
        List<ContentReviewItem> warnings = new ArrayList<>();
        
        if (correctAnswer == null || correctAnswer.isEmpty()) {
            warnings.add(new ContentReviewItem(
                "Quiz-Verify-" + quizId, "Quiz", level, quizId, "correctAnswer", correctAnswer, 
                "Correct answer is empty", "Add correct answer", "CRITICAL", "OPEN"
            ));
            return warnings;
        }

        if (!correctAnswer.matches("[A-D]")) {
            warnings.add(new ContentReviewItem(
                "Quiz-Verify-" + quizId, "Quiz", level, quizId, "correctAnswer", correctAnswer, 
                "Correct answer must be A, B, C, or D", "Change to uppercase A/B/C/D", "HIGH", "OPEN"
            ));
        }

        // Check duplicates
        if (optA.equals(optB) || optA.equals(optC) || optA.equals(optD) || optB.equals(optC) || optB.equals(optD) || optC.equals(optD)) {
            warnings.add(new ContentReviewItem(
                "Quiz-Verify-" + quizId, "Quiz", level, quizId, "options", optA + "|" + optB + "|" + optC + "|" + optD, 
                "Duplicate options found", "Change duplicated options", "HIGH", "OPEN"
            ));
        }

        return warnings;
    }
}
