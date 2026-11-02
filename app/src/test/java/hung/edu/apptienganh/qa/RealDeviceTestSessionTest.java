package hung.edu.apptienganh.qa;

import org.junit.Test;
import static org.junit.Assert.*;
import hung.edu.apptienganh.data.local.entity.RealDeviceTestCase;

public class RealDeviceTestSessionTest {

    @Test
    public void testTestCaseEntity() {
        RealDeviceTestCase tc = new RealDeviceTestCase();
        assertEquals("NOT_TESTED", tc.getStatus());

        tc.setStatus("PASS");
        assertEquals("PASS", tc.getStatus());

        tc.setStatus("FAIL");
        assertEquals("FAIL", tc.getStatus());

        tc.setStatus("BLOCKED");
        assertEquals("BLOCKED", tc.getStatus());
    }
}
