package hung.edu.apptienganh.qa;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SoloQaReportBuilder {

    private final Context context;
    private final List<SelfTestResult> results = new ArrayList<>();
    private String realDeviceTestSessionStatus = "NOT_TESTED";
    private int realDevicePassCount = 0;
    private int realDeviceFailCount = 0;
    private int realDeviceBlockedCount = 0;
    private int realDeviceNotTestedCount = 0;

    public SoloQaReportBuilder(Context context) {
        this.context = context.getApplicationContext();
    }

    public void addResult(SelfTestResult result) {
        results.add(result);
    }

    public void addResults(List<SelfTestResult> resultList) {
        results.addAll(resultList);
    }

    public void setRealDeviceTestStats(int pass, int fail, int blocked, int notTested) {
        this.realDevicePassCount = pass;
        this.realDeviceFailCount = fail;
        this.realDeviceBlockedCount = blocked;
        this.realDeviceNotTestedCount = notTested;
        
        if (fail > 0) {
            this.realDeviceTestSessionStatus = "NEED FIX";
        } else if (blocked > 0) {
            this.realDeviceTestSessionStatus = "BLOCKED";
        } else if (pass > 0 && notTested == 0) {
            this.realDeviceTestSessionStatus = "PASS";
        } else if (pass > 0) {
            this.realDeviceTestSessionStatus = "PARTIAL PASS";
        } else {
            this.realDeviceTestSessionStatus = "NOT_TESTED";
        }
    }

    public String buildPlainTextReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== SOLO QA REPORT ===\n");
        sb.append("Generated: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date())).append("\n");

        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            sb.append("App Version: ").append(pInfo.versionName).append(" (").append(pInfo.versionCode).append(")\n");
        } catch (Exception e) {
            sb.append("App Version: Unknown\n");
        }

        sb.append("Device: ").append(Build.MANUFACTURER).append(" ").append(Build.MODEL).append("\n");
        sb.append("Android API: ").append(Build.VERSION.SDK_INT).append("\n");
        sb.append("Build Type: ").append(hung.edu.apptienganh.BuildConfig.BUILD_TYPE).append("\n");
        sb.append("Database Version: ").append(hung.edu.apptienganh.data.local.AppDatabase.DATABASE_VERSION).append("\n");
        sb.append("-----------------------\n");

        int passCount = 0;
        int warnCount = 0;
        int failCount = 0;

        for (SelfTestResult r : results) {
            sb.append("[").append(r.getStatus().name()).append("] ")
              .append(r.getTestName()).append(": ")
              .append(r.getMessage()).append("\n");

            if (r.getStatus() == SelfTestStatus.PASS) passCount++;
            else if (r.getStatus() == SelfTestStatus.WARNING) warnCount++;
            else failCount++;
        }

        sb.append("-----------------------\n");
        sb.append("SUMMARY: PASS=").append(passCount)
          .append(", WARNING=").append(warnCount)
          .append(", FAIL=").append(failCount).append("\n");

        sb.append("-----------------------\n");
        sb.append("REAL DEVICE EVIDENCE MODE\n");
        sb.append("Session Status: ").append(realDeviceTestSessionStatus).append("\n");
        if (realDeviceTestSessionStatus.equals("NOT_TESTED")) {
            sb.append("Real-device validation is required before calling this production-ready.\n");
        } else {
            sb.append("Real Device Stats -> PASS: ").append(realDevicePassCount)
              .append(" | FAIL: ").append(realDeviceFailCount)
              .append(" | BLOCKED: ").append(realDeviceBlockedCount)
              .append(" | NOT TESTED: ").append(realDeviceNotTestedCount).append("\n");
        }
          
        sb.append("\nRecommendations:\n");
        if (failCount > 0) {
            sb.append("- Cần sửa lỗi (FAIL) trước khi gửi bản phát hành cho Tester.\n");
        } else {
            sb.append("- Hệ thống hoạt động ổn định. Sẵn sàng phát hành (nếu manual check ok).\n");
        }

        return sb.toString();
    }

    public String buildJsonReport() {
        // Build simple JSON manually to avoid dependencies
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"timestamp\": \"").append(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).format(new Date())).append("\",\n");
        
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            sb.append("  \"appVersion\": \"").append(pInfo.versionName).append("\",\n");
            sb.append("  \"versionCode\": ").append(pInfo.versionCode).append(",\n");
        } catch (Exception e) {}

        sb.append("  \"device\": \"").append(Build.MANUFACTURER).append(" ").append(Build.MODEL).append("\",\n");
        sb.append("  \"androidApi\": ").append(Build.VERSION.SDK_INT).append(",\n");
        sb.append("  \"buildType\": \"").append(hung.edu.apptienganh.BuildConfig.BUILD_TYPE).append("\",\n");
        sb.append("  \"databaseVersion\": ").append(hung.edu.apptienganh.data.local.AppDatabase.DATABASE_VERSION).append(",\n");
        
        sb.append("  \"results\": [\n");
        for (int i = 0; i < results.size(); i++) {
            SelfTestResult r = results.get(i);
            sb.append("    {\n");
            sb.append("      \"testName\": \"").append(r.getTestName().replace("\"", "\\\"")).append("\",\n");
            sb.append("      \"status\": \"").append(r.getStatus().name()).append("\",\n");
            sb.append("      \"message\": \"").append(r.getMessage().replace("\"", "\\\"").replace("\n", " ")).append("\"\n");
            sb.append("    }");
            if (i < results.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ],\n");

        sb.append("  \"realDeviceTestSessionStatus\": \"").append(realDeviceTestSessionStatus).append("\",\n");
        sb.append("  \"realDevicePassCount\": ").append(realDevicePassCount).append(",\n");
        sb.append("  \"realDeviceFailCount\": ").append(realDeviceFailCount).append(",\n");
        sb.append("  \"realDeviceBlockedCount\": ").append(realDeviceBlockedCount).append(",\n");
        sb.append("  \"realDeviceNotTestedCount\": ").append(realDeviceNotTestedCount).append(",\n");

        sb.append("  \"recommendations\": \"Check text report for manual recommendation details\"\n");
        sb.append("}\n");
        return sb.toString();
    }
}
