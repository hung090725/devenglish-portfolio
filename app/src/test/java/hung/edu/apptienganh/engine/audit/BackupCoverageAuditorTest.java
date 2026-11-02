package hung.edu.apptienganh.engine.audit;

import org.junit.Test;
import static org.junit.Assert.*;

public class BackupCoverageAuditorTest {
    @Test
    public void testBackupCoverage() {
        BackupCoverageAuditor auditor = new BackupCoverageAuditor();
        assertTrue(auditor.checkAllEntitiesCovered());
    }
}
