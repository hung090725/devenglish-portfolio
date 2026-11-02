package hung.edu.apptienganh.engine.audit.content.review.feedback;

import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import static org.junit.Assert.*;

public class HumanReviewFeedbackParserTest {

    @Test
    public void testParseCsv() throws Exception {
        File tempFile = File.createTempFile("test_feedback", ".csv");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("reviewId,decision,correctedText,correctedLevel,reason,confidence,reviewerName,reviewedAt,notes\n");
            writer.write("REV-1,FIX,new text,,bad grammar,HIGH,Alice,2026-06-14,\n");
            writer.write("REV-2,OK,,,,,,,,\n");
        }

        HumanReviewFeedbackParser parser = new HumanReviewFeedbackParser();
        List<HumanReviewFeedbackItem> items = parser.parseCsv(tempFile);

        assertEquals(2, items.size());
        assertEquals("REV-1", items.get(0).getReviewId());
        assertEquals("FIX", items.get(0).getDecision());
        assertEquals("new text", items.get(0).getCorrectedText());
        assertEquals("REV-2", items.get(1).getReviewId());
        assertEquals("OK", items.get(1).getDecision());

        tempFile.delete();
    }
}
