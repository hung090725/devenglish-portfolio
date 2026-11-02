package hung.edu.apptienganh.qa;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.export.LocalProgressBackupManager;

public class BackupSelfTester {
    private final Context context;

    public BackupSelfTester(Context context) {
        this.context = context.getApplicationContext();
    }

    public List<SelfTestResult> runTests() {
        List<SelfTestResult> results = new ArrayList<>();
        LocalProgressBackupManager backupManager = new LocalProgressBackupManager(context);
        
        try {
            String json = backupManager.exportToJson();
            if (json == null || json.isEmpty()) {
                results.add(new SelfTestResult("Backup JSON Export", SelfTestStatus.FAIL, "Exported JSON is null or empty"));
            } else {
                results.add(new SelfTestResult("Backup JSON Export", SelfTestStatus.PASS, "JSON exported successfully"));
                
                if (json.contains("\"appVersionName\"")) {
                    results.add(new SelfTestResult("JSON has appVersionName", SelfTestStatus.PASS, "Yes"));
                } else {
                    results.add(new SelfTestResult("JSON has appVersionName", SelfTestStatus.FAIL, "Missing appVersionName"));
                }
                
                if (json.contains("\"databaseVersion\"")) {
                    results.add(new SelfTestResult("JSON has databaseVersion", SelfTestStatus.PASS, "Yes"));
                } else {
                    results.add(new SelfTestResult("JSON has databaseVersion", SelfTestStatus.FAIL, "Missing databaseVersion"));
                }
            }
        } catch (Exception e) {
            results.add(new SelfTestResult("BackupSelfTester", SelfTestStatus.FAIL, "Exception: " + e.getMessage()));
        }
        return results;
    }
}
