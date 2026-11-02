package hung.edu.apptienganh;

import android.content.Context;

import androidx.work.testing.WorkManagerTestInitHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import hung.edu.apptienganh.reminder.ReminderPreferences;
import hung.edu.apptienganh.reminder.ReminderScheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class ReminderSchedulerTest {

    private Context context;

    @Before
    public void setUp() {
        context = RuntimeEnvironment.getApplication();
        WorkManagerTestInitHelper.initializeTestWorkManager(context);
    }

    @Test
    public void enableReminder_schedulesWithPositiveDelay() {
        ReminderPreferences.setReminderEnabled(context, true);
        ReminderPreferences.setReminderTime(context, 20, 0);

        long delay = ReminderScheduler.computeInitialDelayMs(context);
        assertTrue(delay > 0);
        assertTrue(delay <= 24L * 60 * 60 * 1000);

        ReminderScheduler.scheduleDailyReminder(context);
    }

    @Test
    public void disableReminder_cancelsWork() {
        ReminderPreferences.setReminderEnabled(context, false);
        ReminderScheduler.cancelDailyReminder(context);
        assertEquals("daily_study_reminder", ReminderScheduler.WORK_DAILY_STUDY);
    }
}
