package hung.edu.apptienganh.data.export;

import android.content.Context;

import androidx.room.Room;
import org.robolectric.RuntimeEnvironment;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import hung.edu.apptienganh.data.local.AppDatabase;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class LocalProgressExporterTest {

    private Context context;
    private AppDatabase db;

    @Before
    public void setUp() {
        context = RuntimeEnvironment.getApplication();
    }

    @After
    public void tearDown() {
        AppDatabase.getInstance(context).close();
    }

    @Test
    public void exportProgressJson_returnsValidJson() throws Exception {
        // Run export in background to avoid main thread DB access exception
        String jsonStr = AppDatabase.databaseWriteExecutor.submit(() -> 
            LocalProgressExporter.exportProgressJson(context)
        ).get();

        // Verify JSON parsing
        assertNotNull(jsonStr);
        JSONObject root = new JSONObject(jsonStr);
        assertTrue(root.has("databaseVersion"));
        // appVersionName might be null in Robolectric
        assertTrue(root.has("reminderEnabled"));
        assertTrue(root.has("A0_progress"));
        assertTrue(root.has("A1_progress"));
        assertTrue(root.has("deviceModel"));
        assertTrue(root.has("androidVersion"));
        assertTrue(root.has("testTimestamp"));
        assertTrue(root.has("currentLevel"));
    }
}
