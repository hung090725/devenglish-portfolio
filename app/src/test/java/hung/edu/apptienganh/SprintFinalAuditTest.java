package hung.edu.apptienganh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import hung.edu.apptienganh.engine.audit.ReleaseArtifactChecker;
import hung.edu.apptienganh.engine.audit.ReleaseArtifactReport;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class SprintFinalAuditTest {

    @Test
    public void testReleaseArtifactChecker_UnsignedReleaseNotShareReady() throws IOException {
        // Create mock directory structure
        File tempDir = Files.createTempDirectory("mock_build").toFile();
        File apkReleaseDir = new File(tempDir, "apk/release");
        apkReleaseDir.mkdirs();
        new File(apkReleaseDir, "app-release-unsigned.apk").createNewFile();
        
        File bundleReleaseDir = new File(tempDir, "bundle/release");
        bundleReleaseDir.mkdirs();
        new File(bundleReleaseDir, "app-release.aab").createNewFile();

        ReleaseArtifactChecker checker = new ReleaseArtifactChecker(tempDir.getAbsolutePath());
        ReleaseArtifactReport report = checker.check();
        
        assertEquals("NEED_SIGNED_APK_FOR_DISTRIBUTION", report.getStatus());
        assertFalse(report.hasSignedReleaseApk);
        assertTrue(report.hasUnsignedReleaseApk);
        
        // Clean up
        tempDir.deleteOnExit();
    }

    @Test
    public void testStaticProjectAudit_NoJksAndSigningPropertiesInRepo() {
        // Check root directory
        File rootDir = new File("../");
        
        File[] files = rootDir.listFiles((dir, name) -> name.endsWith(".jks") || name.equals("signing.properties"));
        assertTrue("Repository must not contain .jks or signing.properties", files == null || files.length == 0);
    }

    @Test
    public void testDocumentationPresence() {
        File releaseKitDir = new File("../docs/release_kit");
        assertTrue(new File(releaseKitDir, "SIGNING_STRATEGY_PERSONAL.md").exists());
        assertTrue(new File(releaseKitDir, "INSTALLABILITY_MATRIX.md").exists());
        
        File personalDistDir = new File(releaseKitDir, "personal_distribution");
        assertTrue(personalDistDir.exists() && personalDistDir.isDirectory());
        assertTrue(new File(personalDistDir, "WHAT_NOT_TO_SEND.md").exists());
    }
}
