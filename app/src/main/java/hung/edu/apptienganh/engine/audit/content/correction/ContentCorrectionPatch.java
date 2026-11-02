package hung.edu.apptienganh.engine.audit.content.correction;

public class ContentCorrectionPatch {
    private String patchId;
    private String type;
    private String level;
    private String sourceId;
    private String fieldName;
    private String oldValue;
    private String newValue;
    private String reason;
    private String confidence;
    private String reviewer;
    private long createdAt;
    private String status;

    public ContentCorrectionPatch(String patchId, String type, String level, String sourceId, String fieldName, 
                                  String oldValue, String newValue, String reason, String confidence, 
                                  String reviewer, long createdAt, String status) {
        this.patchId = patchId;
        this.type = type;
        this.level = level;
        this.sourceId = sourceId;
        this.fieldName = fieldName;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.reason = reason;
        this.confidence = confidence;
        this.reviewer = reviewer;
        this.createdAt = createdAt;
        this.status = status;
    }

    public String getPatchId() { return patchId; }
    public String getType() { return type; }
    public String getLevel() { return level; }
    public String getSourceId() { return sourceId; }
    public String getFieldName() { return fieldName; }
    public String getOldValue() { return oldValue; }
    public String getNewValue() { return newValue; }
    public String getReason() { return reason; }
    public String getConfidence() { return confidence; }
    public String getReviewer() { return reviewer; }
    public long getCreatedAt() { return createdAt; }
    public String getStatus() { return status; }
    
    public void setStatus(String status) { this.status = status; }
}
