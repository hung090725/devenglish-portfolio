package hung.edu.apptienganh.engine.audit.content;

import java.util.ArrayList;
import java.util.List;
import hung.edu.apptienganh.data.local.entity.QuizQuestion;

public class QuizValidityAuditor {
    public QuizAuditReport audit(List<QuizQuestion> items) {
        List<QuizAuditIssue> issues = new ArrayList<>();
        
        for (QuizQuestion q : items) {
            String idInfo = "Quiz-" + q.getId();
            String level = "Unknown"; // No level in QuizQuestion
            
            if (q.getQuestion() == null || q.getQuestion().isEmpty()) {
                issues.add(new QuizAuditIssue(idInfo, level, "EMPTY_QUESTION", "Question text is empty", true));
            }
            if (q.getOptionA() == null || q.getOptionA().isEmpty() ||
                q.getOptionB() == null || q.getOptionB().isEmpty() ||
                q.getOptionC() == null || q.getOptionC().isEmpty() ||
                q.getOptionD() == null || q.getOptionD().isEmpty()) {
                issues.add(new QuizAuditIssue(idInfo, level, "EMPTY_OPTION", "One or more options are empty", true));
            }
            
            // Check correct answer is A, B, C, or D
            if (q.getCorrectAnswer() == null || !q.getCorrectAnswer().matches("^[ABCD]$")) {
                issues.add(new QuizAuditIssue(idInfo, level, "INVALID_CORRECT_ANSWER", "Correct answer is not A, B, C, or D", true));
            }
            
            // Check for duplicate options
            String a = q.getOptionA();
            String b = q.getOptionB();
            String c = q.getOptionC();
            String d = q.getOptionD();
            
            if (a != null && b != null && c != null && d != null) {
                if (a.equals(b) || a.equals(c) || a.equals(d) || b.equals(c) || b.equals(d) || c.equals(d)) {
                    issues.add(new QuizAuditIssue(idInfo, level, "DUPLICATE_OPTION", "Quiz has duplicate options", true));
                }
            }
            
            if (q.getExplanationVi() == null || q.getExplanationVi().isEmpty()) {
                issues.add(new QuizAuditIssue(idInfo, level, "EMPTY_EXPLANATION", "Explanation VI is empty", true));
            }
            
            if (q.getLearningDayId() <= 0) {
                issues.add(new QuizAuditIssue(idInfo, level, "INVALID_DAY_ID", "Learning day ID is invalid", false));
            }
            
            if (q.getQuestionType() == null || q.getQuestionType().isEmpty()) {
                issues.add(new QuizAuditIssue(idInfo, level, "EMPTY_QUIZ_TYPE", "Quiz type is empty", true));
            }
        }
        
        return new QuizAuditReport(issues, items.size());
    }
}
