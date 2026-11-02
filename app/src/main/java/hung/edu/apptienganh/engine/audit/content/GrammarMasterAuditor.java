package hung.edu.apptienganh.engine.audit.content;

import java.util.ArrayList;
import java.util.List;
import hung.edu.apptienganh.data.local.entity.GrammarPoint;

public class GrammarMasterAuditor {
    public GrammarAuditReport audit(List<GrammarPoint> items) {
        List<GrammarAuditIssue> issues = new ArrayList<>();
        
        for (GrammarPoint g : items) {
            String idInfo = "Grammar-" + g.getId() + "-" + g.getTitle();
            String level = g.getLevel() != null ? g.getLevel() : "Unknown";
            
            if (g.getTitle() == null || g.getTitle().isEmpty()) {
                issues.add(new GrammarAuditIssue(idInfo, level, "EMPTY_TITLE", "Title is empty", true));
            }
            
            if (g.getFormula() == null || g.getFormula().isEmpty()) {
                issues.add(new GrammarAuditIssue(idInfo, level, "EMPTY_FORMULA", "Formula is empty", true));
            }
            
            if (g.getUsageVi() == null || g.getUsageVi().isEmpty()) {
                issues.add(new GrammarAuditIssue(idInfo, level, "EMPTY_USAGE", "Usage VI is empty", true));
            }
            
            if (g.getCorrectExamples() == null || g.getCorrectExamples().isEmpty()) {
                issues.add(new GrammarAuditIssue(idInfo, level, "EMPTY_CORRECT_EXAMPLES", "Correct examples are empty", true));
            }
            
            if (g.getWrongExamples() == null || g.getWrongExamples().isEmpty()) {
                issues.add(new GrammarAuditIssue(idInfo, level, "EMPTY_WRONG_EXAMPLES", "Wrong examples are empty", false));
            }
            
            if (g.getExplanationVi() == null || g.getExplanationVi().isEmpty()) {
                issues.add(new GrammarAuditIssue(idInfo, level, "EMPTY_EXPLANATION", "Explanation VI is empty", true));
            }
            
            if (g.getShortPracticeQuestions() == null || g.getShortPracticeQuestions().isEmpty()) {
                issues.add(new GrammarAuditIssue(idInfo, level, "EMPTY_PRACTICE", "Practice questions are empty", false));
            }
        }
        
        return new GrammarAuditReport(issues, items.size());
    }
}
