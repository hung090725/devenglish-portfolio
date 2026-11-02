package hung.edu.apptienganh.ui.qa;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.repository.AppRepository;
import hung.edu.apptienganh.qa.BackupSelfTester;
import hung.edu.apptienganh.qa.ContentSelfTester;
import hung.edu.apptienganh.qa.ProgressSelfTester;
import hung.edu.apptienganh.qa.ReminderSelfTester;
import hung.edu.apptienganh.qa.SelfTestResult;
import hung.edu.apptienganh.qa.SoloQaReportBuilder;
import hung.edu.apptienganh.ui.backup.BackupRestoreActivity;

public class SoloQaActivity extends AppCompatActivity {

    private TextView txtAppStatus, txtContentStatus, txtProgressStatus, txtFeatureStatus, txtTestResults;
    private Button btnRunContent, btnRunProgress, btnRunBackup, btnRunReminder;
    private Button btnCopyReport, btnOpenBackup, btnOpenRealDeviceTest, btnOpenNoPlay;
    private Button btnCopyTesterMsg, btnCopyReleaseNotes;
    private ProgressBar progressBar;
    private LinearLayout layoutResults;
    private TextView txtReleaseDashboard;

    private SoloQaReportBuilder reportBuilder;
    private AppRepository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_qa);

        repository = new AppRepository(this);
        reportBuilder = new SoloQaReportBuilder(this);

        txtAppStatus = findViewById(R.id.txt_app_status);
        txtContentStatus = findViewById(R.id.txt_content_status);
        txtProgressStatus = findViewById(R.id.txt_progress_status);
        txtFeatureStatus = findViewById(R.id.txt_feature_status);
        txtTestResults = findViewById(R.id.txt_test_results);
        progressBar = findViewById(R.id.progress_bar_qa);
        layoutResults = findViewById(R.id.layout_results);

        btnRunContent = findViewById(R.id.btn_run_content);
        btnRunProgress = findViewById(R.id.btn_run_progress);
        btnRunBackup = findViewById(R.id.btn_run_backup);
        btnRunReminder = findViewById(R.id.btn_run_reminder);
        btnCopyReport = findViewById(R.id.btn_copy_report);
        btnOpenBackup = findViewById(R.id.btn_open_backup);
        btnOpenRealDeviceTest = findViewById(R.id.btn_open_real_device_test);
        btnOpenNoPlay = findViewById(R.id.btn_open_noplay);
        btnCopyTesterMsg = findViewById(R.id.btn_copy_tester_msg);
        btnCopyReleaseNotes = findViewById(R.id.btn_copy_release_notes);
        txtReleaseDashboard = findViewById(R.id.txt_release_dashboard);

        setupListeners();
        loadInitialStatus();
    }

    private void loadInitialStatus() {
        progressBar.setVisibility(View.VISIBLE);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            // App Status
            StringBuilder appStatus = new StringBuilder();
            String versionName = "Unknown";
            try {
                PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                versionName = pInfo.versionName;
                appStatus.append("Version: ").append(versionName).append(" (").append(pInfo.versionCode).append(")\n");
            } catch (Exception e) {
                appStatus.append("Version: Unknown\n");
            }
            appStatus.append("Device: ").append(Build.MANUFACTURER).append(" ").append(Build.MODEL).append("\n");
            appStatus.append("Android API: ").append(Build.VERSION.SDK_INT).append("\n");
            
            final String finalVersionName = versionName;

            // Progress Status
            StringBuilder progStatus = new StringBuilder();
            progStatus.append("A0 Completed: ").append(repository.isA0CompletedSync()).append("\n");
            progStatus.append("A1 Unlocked: ").append(repository.isA1UnlockedSync()).append("\n");
            progStatus.append("A2 Unlocked: ").append(repository.isA2UnlockedSync()).append("\n");
            progStatus.append("B1 Unlocked: ").append(repository.isB1UnlockedSync()).append("\n");

            runOnUiThread(() -> {
                txtAppStatus.setText(appStatus.toString());
                txtProgressStatus.setText(progStatus.toString());
                txtContentStatus.setText("Press [Run Content] to check DB counts");
                txtFeatureStatus.setText("Press [Run Reminder] or manually check features");
                
                txtReleaseDashboard.setText("Artifact Paths:\n- APK: app/build/outputs/apk/release/app-release.apk\n- AAB: app/build/outputs/bundle/release/app-release.aab\nBuild Type: " + hung.edu.apptienganh.BuildConfig.BUILD_TYPE);
                
                progressBar.setVisibility(View.GONE);
                
                btnCopyTesterMsg.setOnClickListener(v -> {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Tester Msg", hung.edu.apptienganh.qa.TesterMessageGenerator.generateZaloMessage(finalVersionName));
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(SoloQaActivity.this, "Copied Tester Message", Toast.LENGTH_SHORT).show();
                });
                
                btnCopyReleaseNotes.setOnClickListener(v -> {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Release Notes", hung.edu.apptienganh.qa.ReleaseNotesGenerator.generateMarkdownReleaseNotes(finalVersionName));
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(SoloQaActivity.this, "Copied Release Notes", Toast.LENGTH_SHORT).show();
                });
            });
        });
    }

    private void setupListeners() {
        btnRunContent.setOnClickListener(v -> runTest(() -> {
            List<SelfTestResult> results = new ContentSelfTester(this).runTests();
            updateTestResultsUI(results);
        }));
        btnRunProgress.setOnClickListener(v -> runTest(() -> {
            List<SelfTestResult> results = new ProgressSelfTester(this).runTests();
            updateTestResultsUI(results);
        }));
        btnRunBackup.setOnClickListener(v -> runTest(() -> {
            List<SelfTestResult> results = new BackupSelfTester(this).runTests();
            updateTestResultsUI(results);
        }));
        btnRunReminder.setOnClickListener(v -> runTest(() -> {
            List<SelfTestResult> results = new ReminderSelfTester(this).runTests();
            updateTestResultsUI(results);
        }));

        btnCopyReport.setOnClickListener(v -> {
            AppDatabase.databaseWriteExecutor.execute(() -> {
                List<hung.edu.apptienganh.data.local.entity.RealDeviceTestCase> items = AppDatabase.getInstance(this).realDeviceTestCaseDao().getAllTestCasesSync();
                int pass = 0, fail = 0, block = 0, notTested = 0;
                if (items != null) {
                    for (hung.edu.apptienganh.data.local.entity.RealDeviceTestCase item : items) {
                        switch (item.getStatus()) {
                            case "PASS": pass++; break;
                            case "FAIL": fail++; break;
                            case "BLOCKED": block++; break;
                            default: notTested++; break;
                        }
                    }
                }
                reportBuilder.setRealDeviceTestStats(pass, fail, block, notTested);
                
                runOnUiThread(() -> {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("QA Report", reportBuilder.buildPlainTextReport());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(this, "Copied QA Report", Toast.LENGTH_SHORT).show();
                });
            });
        });

        btnOpenBackup.setOnClickListener(v -> {
            startActivity(new Intent(this, BackupRestoreActivity.class));
        });
        
        btnOpenRealDeviceTest.setOnClickListener(v -> {
            startActivity(new Intent(this, RealDeviceTestSessionActivity.class));
        });

        btnOpenNoPlay.setOnClickListener(v -> {
            startActivity(new Intent(this, NoPlayReleaseActivity.class));
        });
    }

    private void runTest(Runnable testRunner) {
        progressBar.setVisibility(View.VISIBLE);
        layoutResults.setVisibility(View.GONE);
        
        AppDatabase.databaseWriteExecutor.execute(() -> {
            testRunner.run();
        });
    }

    private void updateTestResultsUI(List<SelfTestResult> results) {
        runOnUiThread(() -> {
            progressBar.setVisibility(View.GONE);
            layoutResults.setVisibility(View.VISIBLE);
            
            reportBuilder.addResults(results);
            StringBuilder sb = new StringBuilder(txtTestResults.getText().toString());
            if (sb.length() > 0) sb.append("\n\n");
            for (SelfTestResult r : results) {
                sb.append("[").append(r.getStatus().name()).append("] ").append(r.getTestName()).append(": ").append(r.getMessage()).append("\n");
            }
            txtTestResults.setText(sb.toString());
        });
    }
}
