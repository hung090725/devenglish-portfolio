package hung.edu.apptienganh.engine.audit.data;

import org.junit.Test;
import static org.junit.Assert.*;

public class SeedDataIntegrityAuditorTest {
    @Test
    public void testSeedDataHasNoDuplicates() {
        SeedDataIntegrityAuditor auditor = new SeedDataIntegrityAuditor();
        assertTrue(auditor.checkIntegrity());
    }
}
