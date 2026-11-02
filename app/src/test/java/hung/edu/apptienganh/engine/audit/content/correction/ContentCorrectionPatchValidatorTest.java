package hung.edu.apptienganh.engine.audit.content.correction;

import org.junit.Test;
import static org.junit.Assert.*;

public class ContentCorrectionPatchValidatorTest {

    @Test
    public void testValidateSuccess() {
        ContentCorrectionPatchValidator validator = new ContentCorrectionPatchValidator();
        ContentCorrectionPatch patch = new ContentCorrectionPatch("1", "Quiz", "A1", "q1", "correctAnswer", "A", "B", "reason", "HIGH", "reviewer", 0L, "DRAFT");
        
        assertTrue(validator.validate(patch, "A"));
    }

    @Test
    public void testValidateFailOldValueMismatch() {
        ContentCorrectionPatchValidator validator = new ContentCorrectionPatchValidator();
        ContentCorrectionPatch patch = new ContentCorrectionPatch("1", "Quiz", "A1", "q1", "correctAnswer", "A", "B", "reason", "HIGH", "reviewer", 0L, "DRAFT");
        
        assertFalse(validator.validate(patch, "C")); // actual is C, but patch thinks it's A
    }

    @Test
    public void testValidateFailEmptyCriticalField() {
        ContentCorrectionPatchValidator validator = new ContentCorrectionPatchValidator();
        ContentCorrectionPatch patch = new ContentCorrectionPatch("1", "Quiz", "A1", "q1", "word", "apple", "", "reason", "HIGH", "reviewer", 0L, "DRAFT");
        
        assertFalse(validator.validate(patch, "apple")); // Cannot be empty for 'word'
    }

    @Test
    public void testValidateFailInvalidQuizAnswer() {
        ContentCorrectionPatchValidator validator = new ContentCorrectionPatchValidator();
        ContentCorrectionPatch patch = new ContentCorrectionPatch("1", "Quiz", "A1", "q1", "correctAnswer", "A", "E", "reason", "HIGH", "reviewer", 0L, "DRAFT");
        
        assertFalse(validator.validate(patch, "A")); // 'E' is not valid
    }
}
