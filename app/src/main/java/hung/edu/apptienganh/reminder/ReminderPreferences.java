package hung.edu.apptienganh.reminder;

import android.content.Context;
import android.content.SharedPreferences;

public final class ReminderPreferences {

    private static final String PREFS = "englishpath_reminders";
    private static final String KEY_ENABLED = "reminder_enabled";
    private static final String KEY_HOUR = "reminder_hour";
    private static final String KEY_MINUTE = "reminder_minute";

    public static final int DEFAULT_HOUR = 20;
    public static final int DEFAULT_MINUTE = 0;

    private ReminderPreferences() {}

    private static SharedPreferences prefs(Context context) {
        return context.getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static boolean isReminderEnabled(Context context) {
        return prefs(context).getBoolean(KEY_ENABLED, false);
    }

    public static void setReminderEnabled(Context context, boolean enabled) {
        prefs(context).edit().putBoolean(KEY_ENABLED, enabled).apply();
    }

    public static int getReminderHour(Context context) {
        return prefs(context).getInt(KEY_HOUR, DEFAULT_HOUR);
    }

    public static int getReminderMinute(Context context) {
        return prefs(context).getInt(KEY_MINUTE, DEFAULT_MINUTE);
    }

    public static void setReminderTime(Context context, int hour, int minute) {
        prefs(context).edit()
                .putInt(KEY_HOUR, hour)
                .putInt(KEY_MINUTE, minute)
                .apply();
    }

    public static String formatTime(Context context) {
        int hour = getReminderHour(context);
        int minute = getReminderMinute(context);
        return String.format("%02d:%02d", hour, minute);
    }
}
