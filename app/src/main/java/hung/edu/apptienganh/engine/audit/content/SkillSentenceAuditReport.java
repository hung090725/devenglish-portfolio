package hung.edu.apptienganh.engine.audit.content;

import java.util.List;

public class SkillSentenceAuditReport {
    public List<SkillSentenceAuditIssue> issues;
    public int totalAudited;
    
    public SkillSentenceAuditReport(List<SkillSentenceAuditIssue> issues, int totalAudited) {
        this.issues = issues;
        this.totalAudited = totalAudited;
    }
}
