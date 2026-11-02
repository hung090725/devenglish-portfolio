package hung.edu.apptienganh.data.export;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class LocalProgressBackupManagerTest {

    private LocalProgressBackupManager backupManager;
    private Context context;

    @Before
    public void setup() {
        context = ApplicationProvider.getApplicationContext();
        backupManager = new LocalProgressBackupManager(context);
    }

    @Test
    public void testExportToJson_ReturnsValidJsonString() {
        String json = backupManager.exportToJson();
        assertNotNull("Exported JSON should not be null", json);
        assertTrue("Exported JSON should contain formatVersion", json.contains("\"formatVersion\":"));
    }
}
