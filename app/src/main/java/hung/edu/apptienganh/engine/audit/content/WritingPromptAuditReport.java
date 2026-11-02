package hung.edu.apptienganh.engine.audit.content;

import java.util.List;

public class WritingPromptAuditReport {
    public List<WritingPromptAuditIssue> issues;
    public int totalAudited;
    
    public WritingPromptAuditReport(List<WritingPromptAuditIssue> issues, int totalAudited) {
        this.issues = issues;
        this.totalAudited = totalAudited;
    }
}
