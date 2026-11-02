package hung.edu.apptienganh.engine.audit.content.correction;

import java.util.List;

public class ContentCorrectionPatchResult {
    private int totalPatches;
    private int appliedCount;
    private int rejectedCount;
    private List<ContentCorrectionPatch> appliedPatches;
    private List<ContentCorrectionPatch> rejectedPatches;

    public ContentCorrectionPatchResult(int totalPatches, int appliedCount, int rejectedCount, 
                                        List<ContentCorrectionPatch> appliedPatches, 
                                        List<ContentCorrectionPatch> rejectedPatches) {
        this.totalPatches = totalPatches;
        this.appliedCount = appliedCount;
        this.rejectedCount = rejectedCount;
        this.appliedPatches = appliedPatches;
        this.rejectedPatches = rejectedPatches;
    }

    public int getTotalPatches() { return totalPatches; }
    public int getAppliedCount() { return appliedCount; }
    public int getRejectedCount() { return rejectedCount; }
    public List<ContentCorrectionPatch> getAppliedPatches() { return appliedPatches; }
    public List<ContentCorrectionPatch> getRejectedPatches() { return rejectedPatches; }
}
