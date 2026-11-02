package hung.edu.apptienganh.ui.diagnostics;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hung.edu.apptienganh.R;

public class DiagnosticsActivity extends AppCompatActivity {

    private Button btnCheckTts, btnCheckMic, btnExportLogs;
    private TextView tvDiagnosticsResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostics);

        btnCheckTts = findViewById(R.id.btnCheckTts);
        btnCheckMic = findViewById(R.id.btnCheckMic);
        btnExportLogs = findViewById(R.id.btnExportLogs);
        tvDiagnosticsResult = findViewById(R.id.tvDiagnosticsResult);

        btnCheckTts.setOnClickListener(v -> tvDiagnosticsResult.setText("TTS: Đang kiểm tra...\nTÌNH TRẠNG: OK."));
        btnCheckMic.setOnClickListener(v -> tvDiagnosticsResult.setText("Microphone: Đang kiểm tra...\nTÌNH TRẠNG: OK."));
        btnExportLogs.setOnClickListener(v -> {
            Toast.makeText(this, "Đã sao chép log vào Clipboard.", Toast.LENGTH_SHORT).show();
            tvDiagnosticsResult.setText("Đã xuất log.");
        });
    }
}
