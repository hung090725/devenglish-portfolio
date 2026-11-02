package hung.edu.apptienganh.ui.qa;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.engine.audit.AppCompletionAuditor;
import hung.edu.apptienganh.engine.audit.AppCompletionScore;

public class AppCompletionAuditActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_completion_audit);

        TextView tvReport = findViewById(R.id.tvAuditReport);
        Button btnCopy = findViewById(R.id.btnCopyReport);

        new Thread(() -> {
            AppCompletionAuditor auditor = new AppCompletionAuditor(this);
            AppCompletionScore score = auditor.runAudit();
            String json = auditor.formatAsJson(score);
            
            runOnUiThread(() -> {
                tvReport.setText(json);
                btnCopy.setOnClickListener(v -> {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Audit JSON", json);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(this, "Copied JSON to clipboard", Toast.LENGTH_SHORT).show();
                });
            });
        }).start();
    }
}
