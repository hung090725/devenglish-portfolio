package hung.edu.apptienganh.ui.qa;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.dao.RealDeviceTestCaseDao;
import hung.edu.apptienganh.data.local.entity.RealDeviceTestCase;

public class RealDeviceTestSessionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RealDeviceTestCaseAdapter adapter;
    private TextView txtSummary;
    private Button btnExport;
    private RealDeviceTestCaseDao dao;
    private List<RealDeviceTestCase> testCases = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_device_test_session);

        recyclerView = findViewById(R.id.recycler_test_cases);
        txtSummary = findViewById(R.id.txt_test_summary);
        btnExport = findViewById(R.id.btn_export_report);

        dao = AppDatabase.getInstance(this).realDeviceTestCaseDao();

        adapter = new RealDeviceTestCaseAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setListener(item -> {
            AppDatabase.databaseWriteExecutor.execute(() -> {
                dao.update(item);
                updateSummary();
            });
        });

        btnExport.setOnClickListener(v -> exportReport());

        AppDatabase.databaseWriteExecutor.execute(() -> {
            int count = dao.getCountSync();
            if (count != 36) {
                dao.deleteAllTestCasesSync(); // Sẽ phải tạo hàm này trong DAO
                seedInitialTestCases();
            }
            runOnUiThread(this::observeTestCases);
        });
    }

    private void observeTestCases() {
        dao.getAllTestCases().observe(this, items -> {
            this.testCases = items;
            adapter.setItems(items);
            updateSummary();
        });
    }

    private void updateSummary() {
        int pass = 0, fail = 0, block = 0, notTested = 0;
        for (RealDeviceTestCase item : testCases) {
            switch (item.getStatus()) {
                case "PASS": pass++; break;
                case "FAIL": fail++; break;
                case "BLOCKED": block++; break;
                default: notTested++; break;
            }
        }
        final String sumText = String.format(Locale.getDefault(), "PASS: %d | FAIL: %d | BLOCKED: %d | NOT TESTED: %d", pass, fail, block, notTested);
        runOnUiThread(() -> txtSummary.setText(sumText));
    }

    private void exportReport() {
        int pass = 0, fail = 0, block = 0, notTested = 0;
        for (RealDeviceTestCase item : testCases) {
            switch (item.getStatus()) {
                case "PASS": pass++; break;
                case "FAIL": fail++; break;
                case "BLOCKED": block++; break;
                default: notTested++; break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== REAL DEVICE TEST REPORT ===\n");
        sb.append("Date: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date())).append("\n");
        
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            sb.append("App Version: ").append(pInfo.versionName).append(" (").append(pInfo.versionCode).append(")\n");
        } catch (Exception e) {}

        sb.append("Device: ").append(Build.MANUFACTURER).append(" ").append(Build.MODEL).append("\n");
        sb.append("Android API: ").append(Build.VERSION.SDK_INT).append("\n");
        sb.append("-------------------------------\n");
        sb.append("SUMMARY: PASS=").append(pass).append(" FAIL=").append(fail)
          .append(" BLOCKED=").append(block).append(" NOT_TESTED=").append(notTested).append("\n");
        sb.append("Diagnostics Summary: (Check Backup/Restore screen to export)\n");
        sb.append("-------------------------------\n");

        for (RealDeviceTestCase item : testCases) {
            sb.append(String.format("[%s] %d. %s", item.getStatus(), item.getOrderIndex(), item.getTitle()));
            if (item.getNote() != null && !item.getNote().trim().isEmpty()) {
                sb.append(" - Note: ").append(item.getNote());
            }
            sb.append("\n");
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Real Device Report", sb.toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Real Device Report copied to clipboard", Toast.LENGTH_SHORT).show();
    }

    private void seedInitialTestCases() {
        String[] cases = {
                "Install APK", "First launch", "Splash", "Onboarding", "Level selection",
                "Home screen", "Lessons screen", "A0 day 1 open", "TTS button", "Microphone permission allow",
                "Microphone permission deny", "Speaking practice", "Quiz answer", "Mark completed", "Unlock next day",
                "Vocabulary Review", "Again / Hard / Good / Easy", "Reminder settings", "Notification permission allow/deny", "Backup export",
                "Copy backup JSON", "Uninstall app", "Reinstall app", "Import backup JSON", "Verify restored progress",
                "Solo QA Content Self-Test", "Solo QA Backup Self-Test", "Solo QA Progress Self-Test", "Export Diagnostics JSON", "Submit Beta Feedback",
                "Offline mode", "Close app / reopen app", "Small screen check nếu có", "A1 locked/unlocked logic", "A2 locked/unlocked logic",
                "B1 locked/unlocked logic"
        };
        List<RealDeviceTestCase> initial = new ArrayList<>();
        for (int i = 0; i < cases.length; i++) {
            RealDeviceTestCase tc = new RealDeviceTestCase();
            tc.setTitle(cases[i]);
            tc.setCategory("General");
            tc.setStatus("NOT_TESTED");
            tc.setOrderIndex(i + 1);
            tc.setUpdatedAt(System.currentTimeMillis());
            initial.add(tc);
        }
        dao.insertAll(initial);
    }
}
