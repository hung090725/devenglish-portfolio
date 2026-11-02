package hung.edu.apptienganh.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.ai.AiConfig;
import hung.edu.apptienganh.ui.backup.BackupRestoreActivity;
import hung.edu.apptienganh.ui.diagnostics.DiagnosticsActivity;

public class SettingsCenterActivity extends AppCompatActivity {

    private Button btnDailyGoal, btnReminderSettings, btnAiConfig, btnAudioSettings, btnBackupRestore, btnDiagnostics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_center);

        btnDailyGoal = findViewById(R.id.btnDailyGoal);
        btnReminderSettings = findViewById(R.id.btnReminderSettings);
        btnAiConfig = findViewById(R.id.btnAiConfig);
        btnAudioSettings = findViewById(R.id.btnAudioSettings);
        btnBackupRestore = findViewById(R.id.btnBackupRestore);
        btnDiagnostics = findViewById(R.id.btnDiagnostics);

        btnDailyGoal.setOnClickListener(v -> Toast.makeText(this, "Tính năng Mục tiêu học tập đang phát triển.", Toast.LENGTH_SHORT).show());
        btnReminderSettings.setOnClickListener(v -> startActivity(new Intent(this, ReminderSettingsActivity.class)));
        btnAiConfig.setOnClickListener(v -> showAiSettingsDialog());
        btnAudioSettings.setOnClickListener(v -> Toast.makeText(this, "Tính năng Cài đặt giọng đọc TTS đang phát triển.", Toast.LENGTH_SHORT).show());
        btnBackupRestore.setOnClickListener(v -> startActivity(new Intent(this, BackupRestoreActivity.class)));
        btnDiagnostics.setOnClickListener(v -> startActivity(new Intent(this, DiagnosticsActivity.class)));
    }

    private void showAiSettingsDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_ai_settings, null);
        EditText etApiKey = dialogView.findViewById(R.id.etApiKey);
        EditText etModel = dialogView.findViewById(R.id.etModel);

        etApiKey.setText(AiConfig.getApiKey(this));
        etModel.setText(AiConfig.getModel(this));

        new AlertDialog.Builder(this)
                .setTitle("Cấu hình Trợ lý AI")
                .setView(dialogView)
                .setPositiveButton("Lưu", (dialog, which) -> {
                    String key = etApiKey.getText().toString().trim();
                    String model = etModel.getText().toString().trim();
                    AiConfig.setApiKey(this, key);
                    AiConfig.setModel(this, model.isEmpty() ? AiConfig.DEFAULT_MODEL : model);
                    Toast.makeText(this, "Đã lưu cấu hình AI!", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("Xóa Key (Dùng Offline)", (dialog, which) -> {
                    AiConfig.setApiKey(this, "");
                    Toast.makeText(this, "Đã chuyển sang chế độ Ngoại tuyến", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }
}
