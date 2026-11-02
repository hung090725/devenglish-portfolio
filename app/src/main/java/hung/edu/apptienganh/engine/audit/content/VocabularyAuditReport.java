package hung.edu.apptienganh.engine.audit.content;

import java.util.List;

public class VocabularyAuditReport {
    public List<VocabularyAuditIssue> issues;
    public int totalAudited;
    
    public VocabularyAuditReport(List<VocabularyAuditIssue> issues, int totalAudited) {
        this.issues = issues;
        this.totalAudited = totalAudited;
    }
}
