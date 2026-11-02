package hung.edu.apptienganh.engine.audit;

import org.junit.Test;
import static org.junit.Assert.*;

public class ReleaseArtifactCheckerTest {
    @Test
    public void testCheckerReturnsReport() {
        ReleaseArtifactChecker checker = new ReleaseArtifactChecker();
        ReleaseArtifactReport report = checker.check();
        assertNotNull(report);
        // By default on a clean instance with no files, it should be NOT_READY
        assertEquals("NOT_READY", report.getStatus());
    }
}
