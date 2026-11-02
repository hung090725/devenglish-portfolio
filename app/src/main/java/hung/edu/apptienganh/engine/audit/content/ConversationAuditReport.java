package hung.edu.apptienganh.engine.audit.content;

import java.util.List;

public class ConversationAuditReport {
    public List<ConversationAuditIssue> issues;
    public int totalAudited;
    
    public ConversationAuditReport(List<ConversationAuditIssue> issues, int totalAudited) {
        this.issues = issues;
        this.totalAudited = totalAudited;
    }
}
