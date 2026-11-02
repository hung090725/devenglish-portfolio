package hung.edu.apptienganh.engine.audit.content.review.feedback;

import hung.edu.apptienganh.engine.audit.content.correction.ContentCorrectionPatch;
import java.util.ArrayList;
import java.util.List;

public class HumanFeedbackPatchResult {
    private List<ContentCorrectionPatch> generatedPatches = new ArrayList<>();
    private List<HumanReviewFeedbackItem> rejectedItems = new ArrayList<>();
    private List<HumanReviewFeedbackItem> reviewedOkItems = new ArrayList<>();
    private List<HumanReviewFeedbackItem> needsReviewItems = new ArrayList<>();

    public List<ContentCorrectionPatch> getGeneratedPatches() { return generatedPatches; }
    public void addGeneratedPatch(ContentCorrectionPatch patch) { this.generatedPatches.add(patch); }

    public List<HumanReviewFeedbackItem> getRejectedItems() { return rejectedItems; }
    public void addRejectedItem(HumanReviewFeedbackItem item) { this.rejectedItems.add(item); }

    public List<HumanReviewFeedbackItem> getReviewedOkItems() { return reviewedOkItems; }
    public void addReviewedOkItem(HumanReviewFeedbackItem item) { this.reviewedOkItems.add(item); }

    public List<HumanReviewFeedbackItem> getNeedsReviewItems() { return needsReviewItems; }
    public void addNeedsReviewItem(HumanReviewFeedbackItem item) { this.needsReviewItems.add(item); }
}
