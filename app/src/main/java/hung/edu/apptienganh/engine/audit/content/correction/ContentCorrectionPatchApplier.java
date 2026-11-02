package hung.edu.apptienganh.engine.audit.content.correction;

import java.util.ArrayList;
import java.util.List;

public class ContentCorrectionPatchApplier {

    private ContentCorrectionPatchValidator validator;

    public ContentCorrectionPatchApplier(ContentCorrectionPatchValidator validator) {
        this.validator = validator;
    }

    public ContentCorrectionPatchResult applyPatches(List<ContentCorrectionPatch> patches, String currentActualValueProvider) {
        List<ContentCorrectionPatch> applied = new ArrayList<>();
        List<ContentCorrectionPatch> rejected = new ArrayList<>();

        for (ContentCorrectionPatch patch : patches) {
            // Mock: in reality, we'd use reflection or a specific interface to get/set the field
            String currentVal = currentActualValueProvider; // Simulating data fetch
            
            if (validator.validate(patch, currentVal)) {
                patch.setStatus("APPLIED");
                applied.add(patch);
            } else {
                patch.setStatus("REJECTED");
                rejected.add(patch);
            }
        }

        return new ContentCorrectionPatchResult(patches.size(), applied.size(), rejected.size(), applied, rejected);
    }
}
