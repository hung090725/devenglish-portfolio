package hung.edu.apptienganh.engine.audit.content.correction;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class ContentCorrectionPatchApplierTest {

    @Test
    public void testApplyPatches() {
        ContentCorrectionPatchValidator validator = new ContentCorrectionPatchValidator();
        ContentCorrectionPatchApplier applier = new ContentCorrectionPatchApplier(validator);
        
        ContentCorrectionPatch validPatch = new ContentCorrectionPatch("1", "Quiz", "A1", "q1", "correctAnswer", "A", "B", "reason", "HIGH", "reviewer", 0L, "DRAFT");
        ContentCorrectionPatch invalidPatch = new ContentCorrectionPatch("2", "Quiz", "A1", "q1", "correctAnswer", "C", "D", "reason", "HIGH", "reviewer", 0L, "DRAFT");
        
        // Simulating that the current actual value in DB is "A"
        ContentCorrectionPatchResult result = applier.applyPatches(Arrays.asList(validPatch, invalidPatch), "A");
        
        assertEquals(2, result.getTotalPatches());
        assertEquals(1, result.getAppliedCount()); // The valid one
        assertEquals(1, result.getRejectedCount()); // The invalid one (oldValue "C" != "A")
        
        assertEquals("APPLIED", validPatch.getStatus());
        assertEquals("REJECTED", invalidPatch.getStatus());
    }
}
