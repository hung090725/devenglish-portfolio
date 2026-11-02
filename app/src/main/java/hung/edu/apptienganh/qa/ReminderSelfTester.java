package hung.edu.apptienganh.qa;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.reminder.ReminderPreferences;

public class ReminderSelfTester {
    private final Context context;

    public ReminderSelfTester(Context context) {
        this.context = context.getApplicationContext();
    }

    public List<SelfTestResult> runTests() {
        List<SelfTestResult> results = new ArrayList<>();
        
        try {
            boolean enabled = ReminderPreferences.isReminderEnabled(context);
            results.add(new SelfTestResult("Reminder Preference", enabled ? SelfTestStatus.PASS : SelfTestStatus.FAIL, enabled ? "Enabled" : "Disabled"));
            
            String time = ReminderPreferences.formatTime(context);
            results.add(new SelfTestResult("Reminder Time Configured", time != null ? SelfTestStatus.PASS : SelfTestStatus.FAIL, time));
            
        } catch (Exception e) {
            results.add(new SelfTestResult("ReminderSelfTester", SelfTestStatus.FAIL, "Exception: " + e.getMessage()));
        }
        return results;
    }
}
