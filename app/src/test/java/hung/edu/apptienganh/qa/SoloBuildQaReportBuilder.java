package hung.edu.apptienganh.qa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SoloBuildQaReportBuilder {

    private final String appVersionName;

    public SoloBuildQaReportBuilder(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public void generateReport() throws IOException {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date());

        String userDir = System.getProperty("user.dir");
        // user.dir khi chạy unit test thường là <project>/app
        // Ta dùng parent path để lên <project>
        File projectDir = new File(userDir).getParentFile();
        if (projectDir == null) {
            projectDir = new File(userDir);
        }

        File appDir = new File(projectDir, "app");

        // Artifact paths
        File debugApk = new File(appDir, "build/outputs/apk/debug/app-debug.apk");
        File releaseApk = new File(appDir, "build/outputs/apk/release/app-release.apk");
        File releaseAab = new File(appDir, "build/outputs/bundle/release/app-release.aab");
        
        File lintReport = new File(appDir, "build/reports/lint-results-debug.html");
        File unitTestReport = new File(appDir, "build/reports/tests/testDebugUnitTest/index.html");

        File docsDir = new File(projectDir, "docs/no_play_release");
        if (!docsDir.exists()) {
            docsDir.mkdirs();
        }

        File reportFile = new File(docsDir, "SOLO_QA_BUILD_REPORT.md");

        StringBuilder sb = new StringBuilder();
        sb.append("# Solo QA Build Report\n\n");
        sb.append("## 1. App Information\n");
        sb.append("- **App Version:** ").append(appVersionName).append("\n");
        sb.append("- **Build Timestamp:** ").append(timestamp).append("\n\n");

        sb.append("## 2. Artifact Paths\n");
        sb.append("- **Debug APK:** ").append(debugApk.exists() ? "✅ Tồn tại" : "❌ Thiếu").append("\n");
        sb.append("- **Release APK:** ").append(releaseApk.exists() ? "✅ Tồn tại" : "❌ Thiếu").append("\n");
        sb.append("- **Release AAB:** ").append(releaseAab.exists() ? "✅ Tồn tại" : "❌ Thiếu").append("\n\n");

        sb.append("## 3. Test & Lint Status\n");
        sb.append("- **Lint Report:** ").append(lintReport.exists() ? "✅ Có HTML" : "❌ Không tìm thấy").append("\n");
        sb.append("- **Unit Test Report:** ").append(unitTestReport.exists() ? "✅ Có HTML" : "❌ Không tìm thấy").append("\n\n");

        sb.append("## 4. Known Limitations\n");
        sb.append("- Chưa có tester ngoài.\n");
        sb.append("- Chưa chạy connectedDebugAndroidTest nếu không có thiết bị/emulator kết nối.\n");
        sb.append("- Tính năng TTS và Micro cần thiết bị thật để test.\n\n");

        sb.append("## 5. Next Steps\n");
        sb.append("Tham khảo [Hướng dẫn chạy test trên thiết bị thật](../SOLO_REAL_DEVICE_TEST_REPORT.md) để tiến hành QA thủ công.\n");

        try (FileWriter writer = new FileWriter(reportFile)) {
            writer.write(sb.toString());
        }
    }
}
