package hung.edu.apptienganh.ui.qa;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hung.edu.apptienganh.R;

public class NoPlayReleaseActivity extends AppCompatActivity {

    private TextView txtVersionInfo, txtArtifactPaths, txtReleaseMessage;
    private Button btnCopyMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_play_release);

        txtVersionInfo = findViewById(R.id.txt_version_info);
        txtArtifactPaths = findViewById(R.id.txt_artifact_paths);
        txtReleaseMessage = findViewById(R.id.txt_release_message);
        btnCopyMessage = findViewById(R.id.btn_copy_message);

        loadVersionInfo();
        loadStaticInfo();

        btnCopyMessage.setOnClickListener(v -> copyMessage());
    }

    private void loadVersionInfo() {
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            txtVersionInfo.setText("Current Version: " + pInfo.versionName + " (" + pInfo.versionCode + ")");
        } catch (Exception e) {
            txtVersionInfo.setText("Current Version: Unknown");
        }
    }

    private void loadStaticInfo() {
        txtArtifactPaths.setText(
            "1. Debug APK: app/build/outputs/apk/debug/app-debug.apk\n" +
            "2. Release APK: app/build/outputs/apk/release/app-release.apk\n" +
            "3. App Bundle: app/build/outputs/bundle/release/app-release.aab"
        );

        txtReleaseMessage.setText(
            "Chào bạn, đây là bản thử nghiệm ứng dụng EnglishPath AI.\n" +
            "Bạn hãy tải file APK này về, chọn 'Install anyway' (Cài đặt dù có cảnh báo) vì app chưa lên Google Play.\n" +
            "Nếu gặp lỗi, vui lòng vào Profile -> Feedback để gửi góp ý. Xin cảm ơn!"
        );
    }

    private void copyMessage() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Release Message", txtReleaseMessage.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied Message", Toast.LENGTH_SHORT).show();
    }
}
