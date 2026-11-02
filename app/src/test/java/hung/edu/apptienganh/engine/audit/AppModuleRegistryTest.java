package hung.edu.apptienganh.engine.audit;

import org.junit.Test;
import static org.junit.Assert.*;
import hung.edu.apptienganh.engine.registry.AppModuleRegistry;
import hung.edu.apptienganh.engine.registry.AppModuleCategory;

public class AppModuleRegistryTest {
    @Test
    public void testRegistryHasModules() {
        assertFalse(AppModuleRegistry.getAllModules().isEmpty());
    }

    @Test
    public void testRegistryHasPracticeModules() {
        assertFalse(AppModuleRegistry.getModulesByCategory(AppModuleCategory.PRACTICE).isEmpty());
    }
}
