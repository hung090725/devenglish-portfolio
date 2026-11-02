package hung.edu.apptienganh.data.export;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import android.content.Context;

@RunWith(RobolectricTestRunner.class)
public class LocalProgressBackupManagerTest {

    @Test
    public void testPreviewBackup_InvalidJson() {
        Context context = RuntimeEnvironment.application;
        LocalProgressBackupManager manager = new LocalProgressBackupManager(context);
        BackupPreview preview = manager.previewBackup("invalid json");
        
        assertFalse(preview.isValid);
        assertTrue(preview.errorMessage.contains("Lỗi cú pháp JSON"));
    }

    @Test
    public void testPreviewBackup_ValidJson() {
        String validJson = "{" +
                "\"version\": 2," +
                "\"appVersionName\": \"0.9.0\"," +
                "\"appVersionCode\": 90," +
                "\"exportedAt\": \"2026-06-12T10:00:00Z\"," +
                "\"dayProgress\": [{}, {}]," +
                "\"vocabularyReviewProgress\": [{}, {}, {}]," +
                "\"quizAttempts\": [{}]," +
                "\"speakingResults\": []" +
                "}";

        Context context = RuntimeEnvironment.application;
        LocalProgressBackupManager manager = new LocalProgressBackupManager(context);
        BackupPreview preview = manager.previewBackup(validJson);
        
        assertTrue(preview.isValid);
        assertEquals("0.9.0", preview.appVersionName);
        assertEquals(90, preview.appVersionCode);
        assertEquals("2026-06-12T10:00:00Z", preview.exportedAt);
        assertEquals(2, preview.dayProgressCount);
        assertEquals(3, preview.vocabularyProgressCount);
        assertEquals(1, preview.quizAttemptCount);
        assertEquals(0, preview.speakingResultCount);
    }
}
