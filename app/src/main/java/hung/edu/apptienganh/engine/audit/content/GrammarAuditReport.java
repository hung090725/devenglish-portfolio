package hung.edu.apptienganh.engine.audit.content;

import java.util.List;

public class GrammarAuditReport {
    public List<GrammarAuditIssue> issues;
    public int totalAudited;
    
    public GrammarAuditReport(List<GrammarAuditIssue> issues, int totalAudited) {
        this.issues = issues;
        this.totalAudited = totalAudited;
    }
}
