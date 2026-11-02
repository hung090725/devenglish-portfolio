package hung.edu.apptienganh.engine.audit;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
import hung.edu.apptienganh.engine.registry.AppModuleRegistry;
import hung.edu.apptienganh.engine.registry.AppModule;

public class ModuleRegistryTest {
    @Test
    public void testModulesAreRegistered() {
        List<AppModule> modules = AppModuleRegistry.getAllModules();
        assertFalse(modules.isEmpty());
    }
}
