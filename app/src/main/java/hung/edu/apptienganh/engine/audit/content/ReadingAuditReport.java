package hung.edu.apptienganh.engine.audit.content;

import java.util.List;

public class ReadingAuditReport {
    public List<ReadingAuditIssue> issues;
    public int totalAudited;
    
    public ReadingAuditReport(List<ReadingAuditIssue> issues, int totalAudited) {
        this.issues = issues;
        this.totalAudited = totalAudited;
    }
}
