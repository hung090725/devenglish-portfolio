package hung.edu.apptienganh.engine.audit.content.review.feedback;

import hung.edu.apptienganh.engine.audit.content.correction.ContentCorrectionPatch;
import java.util.List;
import java.util.UUID;

public class HumanFeedbackToPatchConverter {

    private HumanReviewFeedbackValidator validator;

    public HumanFeedbackToPatchConverter(HumanReviewFeedbackValidator validator) {
        this.validator = validator;
    }

    public HumanFeedbackPatchResult convert(List<HumanReviewFeedbackItem> items) {
        HumanFeedbackPatchResult result = new HumanFeedbackPatchResult();

        for (HumanReviewFeedbackItem item : items) {
            HumanReviewFeedbackValidationResult validation = validator.validate(item);
            if (!validation.isValid()) {
                result.addRejectedItem(item);
                continue;
            }

            String decision = item.getDecision().toUpperCase();
            if (decision.equals("OK")) {
                result.addReviewedOkItem(item);
            } else if (decision.equals("UNSURE")) {
                result.addNeedsReviewItem(item);
            } else {
                String patchId = "PATCH-" + UUID.randomUUID().toString().substring(0, 8);
                String sourceId = item.getReviewId(); // Note: In real app, we need to map reviewId to sourceId
                String reason = "Human Review: " + item.getReason();
                String confidence = item.getConfidence();
                String reviewer = item.getReviewerName() != null ? item.getReviewerName() : "Unknown";
                String status = "PENDING";
                
                String type = "Unknown";
                String level = "Unknown";
                String fieldName = "";
                String oldValue = "Unknown";
                String newValue = "";

                if (decision.equals("MOVE_LEVEL")) {
                    fieldName = "level";
                    newValue = item.getCorrectedLevel();
                } else if (decision.equals("REMOVE")) {
                    fieldName = "status";
                    newValue = "REMOVED";
                } else {
                    // FIX or REWRITE
                    fieldName = "content"; // simplified
                    newValue = item.getCorrectedText();
                }
                
                ContentCorrectionPatch patch = new ContentCorrectionPatch(
                    patchId, type, level, sourceId, fieldName, oldValue, newValue, reason, confidence, reviewer, System.currentTimeMillis(), status
                );

                result.addGeneratedPatch(patch);
            }
        }

        return result;
    }
}
