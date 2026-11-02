package hung.edu.apptienganh.engine.audit.content.review.feedback;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class HumanFeedbackToPatchConverterTest {

    @Test
    public void testConvertFix() {
        HumanReviewFeedbackValidator validator = new HumanReviewFeedbackValidator();
        HumanFeedbackToPatchConverter converter = new HumanFeedbackToPatchConverter(validator);

        HumanReviewFeedbackItem item = new HumanReviewFeedbackItem("R-1", "FIX", "fixed text", "", "bad", "HIGH", "Alice", "", "");
        HumanFeedbackPatchResult result = converter.convert(Arrays.asList(item));

        assertEquals(1, result.getGeneratedPatches().size());
        assertEquals("fixed text", result.getGeneratedPatches().get(0).getNewValue());
        assertEquals("content", result.getGeneratedPatches().get(0).getFieldName());
    }

    @Test
    public void testConvertOk() {
        HumanReviewFeedbackValidator validator = new HumanReviewFeedbackValidator();
        HumanFeedbackToPatchConverter converter = new HumanFeedbackToPatchConverter(validator);

        HumanReviewFeedbackItem item = new HumanReviewFeedbackItem("R-1", "OK", "", "", "", "HIGH", "", "", "");
        HumanFeedbackPatchResult result = converter.convert(Arrays.asList(item));

        assertEquals(0, result.getGeneratedPatches().size());
        assertEquals(1, result.getReviewedOkItems().size());
    }
}
