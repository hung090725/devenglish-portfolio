package hung.edu.apptienganh.engine.audit.content.review.feedback;

public class HumanReviewFeedbackItem {
    private String reviewId;
    private String decision;
    private String correctedText;
    private String correctedLevel;
    private String reason;
    private String confidence;
    private String reviewerName;
    private String reviewedAt;
    private String notes;

    public HumanReviewFeedbackItem() {
    }

    public HumanReviewFeedbackItem(String reviewId, String decision, String correctedText, String correctedLevel, String reason, String confidence, String reviewerName, String reviewedAt, String notes) {
        this.reviewId = reviewId;
        this.decision = decision;
        this.correctedText = correctedText;
        this.correctedLevel = correctedLevel;
        this.reason = reason;
        this.confidence = confidence;
        this.reviewerName = reviewerName;
        this.reviewedAt = reviewedAt;
        this.notes = notes;
    }

    public String getReviewId() { return reviewId; }
    public void setReviewId(String reviewId) { this.reviewId = reviewId; }

    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }

    public String getCorrectedText() { return correctedText; }
    public void setCorrectedText(String correctedText) { this.correctedText = correctedText; }

    public String getCorrectedLevel() { return correctedLevel; }
    public void setCorrectedLevel(String correctedLevel) { this.correctedLevel = correctedLevel; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getConfidence() { return confidence; }
    public void setConfidence(String confidence) { this.confidence = confidence; }

    public String getReviewerName() { return reviewerName; }
    public void setReviewerName(String reviewerName) { this.reviewerName = reviewerName; }

    public String getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(String reviewedAt) { this.reviewedAt = reviewedAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
