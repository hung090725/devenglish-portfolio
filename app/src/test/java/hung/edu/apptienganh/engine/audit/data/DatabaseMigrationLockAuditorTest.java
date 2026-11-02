package hung.edu.apptienganh.engine.audit.data;

import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseMigrationLockAuditorTest {
    @Test
    public void testMigrationIsLocked() {
        DatabaseMigrationLockAuditor auditor = new DatabaseMigrationLockAuditor();
        assertTrue(auditor.checkMigrationLock());
    }
}
