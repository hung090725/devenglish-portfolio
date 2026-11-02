package hung.edu.apptienganh.engine.audit.content;

public class QuizAuditIssue {
    public String targetId;
    public String level;
    public String issueType;
    public String description;
    public boolean isCritical;

    public QuizAuditIssue(String targetId, String level, String issueType, String description, boolean isCritical) {
        this.targetId = targetId;
        this.level = level;
        this.issueType = issueType;
        this.description = description;
        this.isCritical = isCritical;
    }
}
