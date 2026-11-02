package hung.edu.apptienganh;

import androidx.room.testing.MigrationTestHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import androidx.sqlite.db.SupportSQLiteDatabase;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.migration.DatabaseMigrations;

@RunWith(AndroidJUnit4.class)
public class MigrationTest {

    private static final String TEST_DB = "migration-test-db";

    @Rule
    public MigrationTestHelper helper = new MigrationTestHelper(
            InstrumentationRegistry.getInstrumentation(),
            AppDatabase.class.getCanonicalName(),
            new FrameworkSQLiteOpenHelperFactory());

    @Test
    public void migrate2To3() throws IOException {
        SupportSQLiteDatabase db = helper.createDatabase(TEST_DB, 2);
        
        // Insert dummy data into day_progress to test migration
        db.execSQL("INSERT INTO day_progress (id, userId, level, dayIndex, isUnlocked, isCompleted, vocabularyScore, grammarScore, conversationScore, quizScore, updatedAt) " +
                "VALUES (1, 1, 'A0', 1, 1, 0, 10, 10, 10, 10, 1000)");
        db.close();

        db = helper.runMigrationsAndValidate(TEST_DB, 3, true, DatabaseMigrations.MIGRATION_2_3);
        
        // Verify that the new columns exist and default values are set correctly
        android.database.Cursor cursor = db.query("SELECT status, bestQuizScore FROM day_progress WHERE id = 1");
        cursor.moveToFirst();
        assert cursor.getString(0).equals("AVAILABLE"); // Migrated to AVAILABLE since isUnlocked = 1 and isCompleted = 0
        assert cursor.getInt(1) == 10; // bestQuizScore copied from quizScore
        cursor.close();
    }

    @Test
    public void migrate3To4() throws IOException {
        SupportSQLiteDatabase db = helper.createDatabase(TEST_DB, 3);
        db.close();
        
        db = helper.runMigrationsAndValidate(TEST_DB, 4, true, DatabaseMigrations.MIGRATION_3_4);
        
        // Verify new table exists
        android.database.Cursor cursor = db.query("SELECT * FROM content_seed_meta");
        assert cursor.getCount() == 0;
        cursor.close();
    }
}
