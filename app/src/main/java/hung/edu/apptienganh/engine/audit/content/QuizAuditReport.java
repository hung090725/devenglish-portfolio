package hung.edu.apptienganh.engine.audit.content;

import java.util.List;

public class QuizAuditReport {
    public List<QuizAuditIssue> issues;
    public int totalAudited;
    
    public QuizAuditReport(List<QuizAuditIssue> issues, int totalAudited) {
        this.issues = issues;
        this.totalAudited = totalAudited;
    }
}
