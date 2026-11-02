package hung.edu.apptienganh.engine.audit.content.review.lock;

public class ReviewedContentLockEntry {
    private String sourceId;
    private String fieldName;
    private String lockedValue;
    private String lockedAt;
    private String reviewerName;

    public ReviewedContentLockEntry(String sourceId, String fieldName, String lockedValue, String lockedAt, String reviewerName) {
        this.sourceId = sourceId;
        this.fieldName = fieldName;
        this.lockedValue = lockedValue;
        this.lockedAt = lockedAt;
        this.reviewerName = reviewerName;
    }

    public String getSourceId() { return sourceId; }
    public String getFieldName() { return fieldName; }
    public String getLockedValue() { return lockedValue; }
    public String getLockedAt() { return lockedAt; }
    public String getReviewerName() { return reviewerName; }
}
