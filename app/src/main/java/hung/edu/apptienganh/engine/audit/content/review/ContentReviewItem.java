package hung.edu.apptienganh.engine.audit.content.review;

public class ContentReviewItem {
    private String reviewId;
    private String type;
    private String level;
    private String sourceId;
    private String fieldName;
    private String currentText;
    private String issue;
    private String suggestedAction;
    private String confidence;
    private String status;
    private String reviewerNote;
    private String correctedText;

    public ContentReviewItem(String reviewId, String type, String level, String sourceId, 
                             String fieldName, String currentText, String issue, 
                             String suggestedAction, String confidence, String status) {
        this.reviewId = reviewId;
        this.type = type;
        this.level = level;
        this.sourceId = sourceId;
        this.fieldName = fieldName;
        this.currentText = currentText;
        this.issue = issue;
        this.suggestedAction = suggestedAction;
        this.confidence = confidence;
        this.status = status;
    }

    public String getReviewId() { return reviewId; }
    public String getType() { return type; }
    public String getLevel() { return level; }
    public String getSourceId() { return sourceId; }
    public String getFieldName() { return fieldName; }
    public String getCurrentText() { return currentText; }
    public String getIssue() { return issue; }
    public String getSuggestedAction() { return suggestedAction; }
    public String getConfidence() { return confidence; }
    public String getStatus() { return status; }
    public String getReviewerNote() { return reviewerNote; }
    public void setReviewerNote(String reviewerNote) { this.reviewerNote = reviewerNote; }
    public String getCorrectedText() { return correctedText; }
    public void setCorrectedText(String correctedText) { this.correctedText = correctedText; }
}
