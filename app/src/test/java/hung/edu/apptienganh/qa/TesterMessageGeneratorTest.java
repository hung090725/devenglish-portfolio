package hung.edu.apptienganh.qa;

import org.junit.Test;
import static org.junit.Assert.*;

public class TesterMessageGeneratorTest {

    @Test
    public void testMessageContent() {
        String msg = TesterMessageGenerator.generateZaloMessage("0.9.4-beta");
        assertTrue("Message should contain version name", msg.contains("0.9.4-beta"));
        assertTrue("Message should mention Diagnostics", msg.contains("Diagnostics"));
        assertFalse("Message should not mention Play Console", msg.contains("Play Console"));
    }
}
