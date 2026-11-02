package hung.edu.apptienganh.engine.audit.content;

import java.util.ArrayList;
import java.util.List;
import hung.edu.apptienganh.data.local.entity.WritingPrompt;

public class WritingPromptAuditor {
    public WritingPromptAuditReport audit(List<WritingPrompt> items) {
        List<WritingPromptAuditIssue> issues = new ArrayList<>();
        
        for (WritingPrompt w : items) {
            String idInfo = "Writing-" + w.id;
            String level = w.level != null ? w.level : "Unknown";
            
            if (w.promptText == null || w.promptText.isEmpty()) {
                issues.add(new WritingPromptAuditIssue(idInfo, level, "EMPTY_PROMPT", "Prompt text is empty", true));
            }
            if (w.instructionVi == null || w.instructionVi.isEmpty()) {
                issues.add(new WritingPromptAuditIssue(idInfo, level, "EMPTY_INSTRUCTION", "Instruction VI is empty", true));
            }
            if (w.expectedKeywords == null || w.expectedKeywords.isEmpty()) {
                issues.add(new WritingPromptAuditIssue(idInfo, level, "EMPTY_KEYWORDS", "Expected keywords are empty", false));
            }
            if (w.sampleAnswer == null || w.sampleAnswer.isEmpty()) {
                issues.add(new WritingPromptAuditIssue(idInfo, level, "EMPTY_SAMPLE_ANSWER", "Sample answer is empty", true));
            }
            if (w.explanationVi == null || w.explanationVi.isEmpty()) {
                issues.add(new WritingPromptAuditIssue(idInfo, level, "EMPTY_EXPLANATION", "Explanation VI is empty", false));
            }
        }
        
        return new WritingPromptAuditReport(issues, items.size());
    }
}
