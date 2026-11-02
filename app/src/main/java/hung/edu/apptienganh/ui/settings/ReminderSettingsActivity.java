package hung.edu.apptienganh.ui.settings;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.reminder.ReminderPreferences;
import hung.edu.apptienganh.reminder.ReminderScheduler;

public class ReminderSettingsActivity extends AppCompatActivity {

    private Switch switchReminder;
    private TimePicker timePicker;
    private TextView txtReminderSummary;
    private Button btnSave;

    private final ActivityResultLauncher<String> notificationPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
                if (!granted) {
                    Toast.makeText(this,
                            "Không có quyền thông báo. Bạn vẫn có thể học trong app.",
                            Toast.LENGTH_LONG).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_settings);

        ImageView btnBack = findViewById(R.id.btn_back);
        switchReminder = findViewById(R.id.switch_reminder);
        timePicker = findViewById(R.id.time_picker);
        txtReminderSummary = findViewById(R.id.txt_reminder_summary);
        btnSave = findViewById(R.id.btn_save_reminder);

        timePicker.setIs24HourView(true);
        timePicker.setHour(ReminderPreferences.getReminderHour(this));
        timePicker.setMinute(ReminderPreferences.getReminderMinute(this));
        switchReminder.setChecked(ReminderPreferences.isReminderEnabled(this));
        updateSummary();

        btnBack.setOnClickListener(v -> finish());
        switchReminder.setOnCheckedChangeListener((buttonView, isChecked) -> updateSummary());
        timePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> updateSummary());
        btnSave.setOnClickListener(v -> saveSettings());
    }

    private void updateSummary() {
        String time = String.format("%02d:%02d", timePicker.getHour(), timePicker.getMinute());
        if (switchReminder.isChecked()) {
            txtReminderSummary.setText("Nhắc học lúc " + time + " mỗi ngày");
        } else {
            txtReminderSummary.setText("Nhắc học đang tắt");
        }
    }

    private void saveSettings() {
        ReminderPreferences.setReminderTime(this, timePicker.getHour(), timePicker.getMinute());
        ReminderPreferences.setReminderEnabled(this, switchReminder.isChecked());
        ReminderScheduler.applyReminderSettings(this);

        if (switchReminder.isChecked()) {
            requestNotificationPermissionIfNeeded();
        }

        Toast.makeText(this, "Đã lưu cài đặt nhắc học", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void requestNotificationPermissionIfNeeded() {
        if (!hung.edu.apptienganh.utils.PermissionHelper.hasNotificationPermission(this)) {
            hung.edu.apptienganh.utils.PermissionHelper.requestPermissionWithExplanation(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS,
                    101, // Arbitrary request code, handled by activity result or general fallback
                    "Cấp Quyền Thông Báo",
                    "App dùng thông báo để nhắc bạn học và ôn từ đúng giờ. Xin hãy cấp quyền nhé!",
                    new hung.edu.apptienganh.utils.PermissionHelper.PermissionCallback() {
                        @Override
                        public void onGranted() {
                            Toast.makeText(ReminderSettingsActivity.this, "Đã bật thông báo", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onDenied() {
                            Toast.makeText(ReminderSettingsActivity.this, "Chưa cấp quyền thông báo. App vẫn hoạt động bình thường.", Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Đã bật thông báo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Chưa cấp quyền thông báo. App vẫn hoạt động bình thường.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
