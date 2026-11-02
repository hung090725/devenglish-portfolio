package hung.edu.apptienganh.ui.backup;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.export.LocalProgressBackupManager;
import hung.edu.apptienganh.data.export.LocalProgressExporter;
import hung.edu.apptienganh.data.local.AppDatabase;

public class BackupRestoreActivity extends AppCompatActivity {

    private LocalProgressBackupManager backupManager;
    private TextView txtProgressSummary;
    private TextView txtStatus;
    private MaterialCardView cardStatus;
    private EditText etJsonContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_restore);

        backupManager = new LocalProgressBackupManager(this);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_backup);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Sao lưu / Khôi phục");
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        txtProgressSummary = findViewById(R.id.txt_progress_summary);
        txtStatus = findViewById(R.id.txt_status);
        cardStatus = findViewById(R.id.card_status);
        etJsonContent = findViewById(R.id.et_json_content);

        loadProgressSummary();

        findViewById(R.id.btn_clear).setOnClickListener(v -> etJsonContent.setText(""));
        
        findViewById(R.id.btn_copy).setOnClickListener(v -> {
            String text = etJsonContent.getText().toString();
            if (!text.isEmpty()) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Backup JSON", text);
                if (clipboard != null) {
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(this, "Đã copy vào Clipboard", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btn_paste).setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard != null && clipboard.hasPrimaryClip()) {
                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                if (item != null && item.getText() != null) {
                    etJsonContent.setText(item.getText().toString());
                    Toast.makeText(this, "Đã dán từ Clipboard", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btn_export_json).setOnClickListener(v -> generateBackupJson());
        
        findViewById(R.id.btn_import_json).setOnClickListener(v -> {
            String json = etJsonContent.getText().toString();
            if (json.isEmpty()) {
                showStatus("❌ Ô nhập liệu đang trống. Hãy dán JSON vào trước.");
                return;
            }

            hung.edu.apptienganh.data.export.BackupPreview preview = backupManager.previewBackup(json);
            if (!preview.isValid) {
                showStatus("❌ Lỗi: " + preview.errorMessage);
                return;
            }

            String msg = "App Version: " + preview.appVersionName + " (" + preview.appVersionCode + ")\n"
                    + "Exported At: " + preview.exportedAt + "\n"
                    + "Day Progress: " + preview.dayProgressCount + "\n"
                    + "Vocabulary Review: " + preview.vocabularyProgressCount + "\n"
                    + "Quiz Attempts: " + preview.quizAttemptCount + "\n"
                    + "Speaking Results: " + preview.speakingResultCount + "\n\n"
                    + "Hành động này sẽ cập nhật tiến trình học tập hiện tại bằng dữ liệu trong file. Bạn có chắc chắn muốn tiếp tục?";

            new AlertDialog.Builder(this)
                    .setTitle("Xem trước File Backup & Xác nhận")
                    .setMessage(msg)
                    .setPositiveButton("Đồng ý Khôi phục", (dialog, which) -> processImportJson(json))
                    .setNegativeButton("Hủy", null)
                    .show();
        });

        findViewById(R.id.btn_export_diagnostics).setOnClickListener(v -> {
            new Thread(() -> {
                String diag = LocalProgressExporter.exportProgressJson(this);
                runOnUiThread(() -> {
                    etJsonContent.setText(diag);
                    showStatus("✅ Đã tạo JSON Diagnostics. Hãy copy và gửi cho Developer.");
                });
            }).start();
        });
    }

    private void loadProgressSummary() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            LocalProgressBackupManager.ProgressSummary summary = backupManager.getProgressSummary();
            runOnUiThread(() -> {
                String text = "A0: " + summary.a0Completed + "/14 ngày hoàn thành\n"
                        + "A1: " + summary.a1Completed + "/14 ngày hoàn thành\n"
                        + "A2: " + summary.a2Completed + "/14 ngày hoàn thành\n"
                        + "B1: " + summary.b1Completed + "/14 ngày hoàn thành\n\n"
                        + "Tổng cộng: " + summary.totalCompleted + "/" + summary.totalDays + " ngày học.";
                txtProgressSummary.setText(text);
            });
        });
    }

    private void generateBackupJson() {
        showStatus("⏳ Đang tạo chuỗi Backup...");
        AppDatabase.databaseWriteExecutor.execute(() -> {
            String json = backupManager.exportToJson();
            runOnUiThread(() -> {
                if (json != null) {
                    etJsonContent.setText(json);
                    showStatus("✅ Đã sao lưu thành công! Hãy copy đoạn JSON trên và lưu vào nơi an toàn (Ghi chú, Zalo, Google Keep).");
                } else {
                    showStatus("❌ Lỗi khi tạo file backup.");
                }
            });
        });
    }

    private void processImportJson(String json) {
        showStatus("⏳ Đang khôi phục tiến trình...");
        AppDatabase.databaseWriteExecutor.execute(() -> {
            LocalProgressBackupManager.ImportResult result = backupManager.importFromJson(json);
            runOnUiThread(() -> {
                if (result.success) {
                    showStatus("✅ Đã khôi phục thành công " + result.restoredCount + " bản ghi!\n\nHãy khởi động lại ứng dụng nếu thấy thông số chưa cập nhật.");
                    loadProgressSummary();
                } else {
                    showStatus("❌ Lỗi khôi phục: " + result.errorMessage);
                }
            });
        });
    }

    private void showStatus(String message) {
        txtStatus.setText(message);
        cardStatus.setVisibility(View.VISIBLE);
    }
}
