package hung.edu.apptienganh.qa;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SelfTestEngineTest {

    @Test
    public void testResultCreation() {
        SelfTestResult result = new SelfTestResult("Test DB", SelfTestStatus.PASS, "OK");
        assertEquals("Test DB", result.getTestName());
        assertEquals(SelfTestStatus.PASS, result.getStatus());
        assertEquals("OK", result.getMessage());
    }

    @Test
    public void testResultWithNullMessage() {
        SelfTestResult result = new SelfTestResult("Test Null", SelfTestStatus.FAIL, null);
        assertNotNull(result.getMessage());
        assertEquals("", result.getMessage());
    }
}
