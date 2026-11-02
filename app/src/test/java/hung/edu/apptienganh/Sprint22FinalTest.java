package hung.edu.apptienganh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import java.io.File;
import java.util.List;

import hung.edu.apptienganh.engine.registry.AppModule;
import hung.edu.apptienganh.engine.registry.AppModuleRegistry;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class Sprint22FinalTest {

    @Test
    public void testAppModuleRegistryNoBrokenRoutes() {
        List<AppModule> modules = AppModuleRegistry.getAllModules();
        assertFalse("Modules must not be empty", modules.isEmpty());
        for (AppModule module : modules) {
            assertNotNull("Activity class must not be null for " + module.code, module.targetActivityClassName);
        }
    }

    @Test
    public void testPersonalReleaseKitPresence() {
        File releaseKitDir = new File("../docs/release_kit");
        assertTrue("Release Kit dir must exist", releaseKitDir.exists() && releaseKitDir.isDirectory());
        
        String[] requiredFiles = {
            "README_RELEASE_KIT.md",
            "APK_INSTALL_GUIDE.md",
            "RELEASE_NOTES.md",
            "TEST_CHECKLIST.md",
            "KNOWN_LIMITATIONS.md",
            "BUG_REPORT_TEMPLATE.md",
            "BACKUP_BEFORE_UPDATE_GUIDE.md",
            "DIAGNOSTICS_GUIDE.md",
            "SEND_TO_FRIEND_MESSAGE.md",
            "FILES_TO_ATTACH.md"
        };
        
        for (String fileName : requiredFiles) {
            File f = new File(releaseKitDir, fileName);
            assertTrue("Must contain " + fileName, f.exists());
        }
    }

    @Test
    public void testFinalUxMicrocopyPresence() {
        File microcopyFile = new File("../docs/FINAL_UX_MICROCOPY_PACK.md");
        assertTrue("FINAL_UX_MICROCOPY_PACK.md must exist", microcopyFile.exists());
    }

    @Test
    public void testDebugReleaseBehaviorDocPresence() {
        File behaviorDoc = new File("../docs/DEBUG_RELEASE_FINAL_BEHAVIOR.md");
        assertTrue("DEBUG_RELEASE_FINAL_BEHAVIOR.md must exist", behaviorDoc.exists());
    }
}
