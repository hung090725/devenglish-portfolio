package hung.edu.apptienganh.reminder;

import android.content.Context;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public final class ReminderScheduler {

    public static final String WORK_DAILY_STUDY = "daily_study_reminder";

    private ReminderScheduler() {}

    public static void applyReminderSettings(Context context) {
        if (ReminderPreferences.isReminderEnabled(context)) {
            scheduleDailyReminder(context);
        } else {
            cancelDailyReminder(context);
        }
    }

    public static void scheduleDailyReminder(Context context) {
        long initialDelayMs = computeInitialDelayMs(context);
        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(
                DailyStudyReminderWorker.class, 24, TimeUnit.HOURS)
                .setInitialDelay(initialDelayMs, TimeUnit.MILLISECONDS)
                .addTag(WORK_DAILY_STUDY)
                .build();

        if (!hung.edu.apptienganh.utils.PermissionHelper.hasNotificationPermission(context)) {
            return;
        }

        try {
            WorkManager.getInstance(context.getApplicationContext())
                    .enqueueUniquePeriodicWork(
                            WORK_DAILY_STUDY,
                            ExistingPeriodicWorkPolicy.UPDATE, // Better than REPLACE if supported, but let's use REPLACE
                            request);
        } catch (Throwable e) {
            try {
                WorkManager.getInstance(context.getApplicationContext())
                        .enqueueUniquePeriodicWork(
                                WORK_DAILY_STUDY,
                                ExistingPeriodicWorkPolicy.REPLACE,
                                request);
            } catch (Exception ex) {
                // Ignore
            }
        }
    }

    public static void cancelDailyReminder(Context context) {
        try {
            WorkManager.getInstance(context.getApplicationContext())
                    .cancelUniqueWork(WORK_DAILY_STUDY);
        } catch (Exception e) {
            // Ignore
        }
    }

    public static long computeInitialDelayMs(Context context) {
        Calendar now = Calendar.getInstance();
        Calendar target = Calendar.getInstance();
        target.set(Calendar.HOUR_OF_DAY, ReminderPreferences.getReminderHour(context));
        target.set(Calendar.MINUTE, ReminderPreferences.getReminderMinute(context));
        target.set(Calendar.SECOND, 0);
        target.set(Calendar.MILLISECOND, 0);

        if (!target.after(now)) {
            target.add(Calendar.DAY_OF_YEAR, 1);
        }
        return target.getTimeInMillis() - now.getTimeInMillis();
    }
}
