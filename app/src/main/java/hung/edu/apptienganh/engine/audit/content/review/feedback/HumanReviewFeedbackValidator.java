package hung.edu.apptienganh.engine.audit.content.review.feedback;

import java.util.Arrays;
import java.util.List;

public class HumanReviewFeedbackValidator {

    private static final List<String> VALID_DECISIONS = Arrays.asList("OK", "FIX", "UNSURE", "MOVE_LEVEL", "REWRITE", "REMOVE");
    private static final List<String> VALID_CONFIDENCES = Arrays.asList("HIGH", "MEDIUM", "LOW");
    private static final List<String> VALID_LEVELS = Arrays.asList("A0", "A1", "A2", "B1", "B2", "C1");

    public HumanReviewFeedbackValidationResult validate(HumanReviewFeedbackItem item) {
        if (item.getReviewId() == null || item.getReviewId().trim().isEmpty()) {
            return new HumanReviewFeedbackValidationResult(false, "reviewId is required");
        }

        if (item.getDecision() == null || !VALID_DECISIONS.contains(item.getDecision().toUpperCase())) {
            return new HumanReviewFeedbackValidationResult(false, "Invalid decision: " + item.getDecision());
        }

        String decision = item.getDecision().toUpperCase();
        
        String confidence = item.getConfidence();
        if (confidence == null || confidence.trim().isEmpty()) {
            confidence = "MEDIUM";
            item.setConfidence("MEDIUM");
        }
        confidence = confidence.toUpperCase();

        if (!VALID_CONFIDENCES.contains(confidence)) {
            return new HumanReviewFeedbackValidationResult(false, "Invalid confidence: " + confidence);
        }

        if (confidence.equals("LOW")) {
            return new HumanReviewFeedbackValidationResult(false, "Cannot apply patch with LOW confidence");
        }

        if (decision.equals("FIX") || decision.equals("REWRITE")) {
            if (item.getCorrectedText() == null || item.getCorrectedText().trim().isEmpty()) {
                return new HumanReviewFeedbackValidationResult(false, "correctedText is required for " + decision);
            }
        }

        if (decision.equals("MOVE_LEVEL")) {
            if (item.getCorrectedLevel() == null || !VALID_LEVELS.contains(item.getCorrectedLevel().toUpperCase())) {
                return new HumanReviewFeedbackValidationResult(false, "Invalid or missing correctedLevel for MOVE_LEVEL");
            }
        }

        if (decision.equals("FIX") || decision.equals("REWRITE") || decision.equals("MOVE_LEVEL") || decision.equals("REMOVE")) {
            if (item.getReason() == null || item.getReason().trim().isEmpty()) {
                return new HumanReviewFeedbackValidationResult(false, "reason is required for decision " + decision);
            }
        }

        return new HumanReviewFeedbackValidationResult(true, "OK");
    }
}
