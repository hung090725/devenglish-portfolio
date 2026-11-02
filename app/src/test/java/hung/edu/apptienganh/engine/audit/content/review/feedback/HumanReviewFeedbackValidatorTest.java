package hung.edu.apptienganh.engine.audit.content.review.feedback;

import org.junit.Test;
import static org.junit.Assert.*;

public class HumanReviewFeedbackValidatorTest {

    @Test
    public void testValidOk() {
        HumanReviewFeedbackItem item = new HumanReviewFeedbackItem("R-1", "OK", "", "", "", "HIGH", "", "", "");
        HumanReviewFeedbackValidator validator = new HumanReviewFeedbackValidator();
        assertTrue(validator.validate(item).isValid());
    }

    @Test
    public void testInvalidFixMissingText() {
        HumanReviewFeedbackItem item = new HumanReviewFeedbackItem("R-1", "FIX", "", "", "reason", "HIGH", "", "", "");
        HumanReviewFeedbackValidator validator = new HumanReviewFeedbackValidator();
        assertFalse(validator.validate(item).isValid());
    }

    @Test
    public void testInvalidMoveLevel() {
        HumanReviewFeedbackItem item = new HumanReviewFeedbackItem("R-1", "MOVE_LEVEL", "", "Z9", "reason", "HIGH", "", "", "");
        HumanReviewFeedbackValidator validator = new HumanReviewFeedbackValidator();
        assertFalse(validator.validate(item).isValid());
    }

    @Test
    public void testInvalidLowConfidence() {
        HumanReviewFeedbackItem item = new HumanReviewFeedbackItem("R-1", "FIX", "text", "", "reason", "LOW", "", "", "");
        HumanReviewFeedbackValidator validator = new HumanReviewFeedbackValidator();
        assertFalse(validator.validate(item).isValid());
    }
}
