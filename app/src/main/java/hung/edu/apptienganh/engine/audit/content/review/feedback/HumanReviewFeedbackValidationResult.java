package hung.edu.apptienganh.engine.audit.content.review.feedback;

public class HumanReviewFeedbackValidationResult {
    private boolean isValid;
    private String errorMessage;

    public HumanReviewFeedbackValidationResult(boolean isValid, String errorMessage) {
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
