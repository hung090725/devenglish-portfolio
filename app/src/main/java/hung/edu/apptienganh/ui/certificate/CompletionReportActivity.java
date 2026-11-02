package hung.edu.apptienganh.ui.certificate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import hung.edu.apptienganh.R;
import hung.edu.apptienganh.engine.LearningCertificateGenerator;

public class CompletionReportActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion_report);
        
        TextView tvReportBody = findViewById(R.id.tvReportBody);
        LearningCertificateGenerator generator = new LearningCertificateGenerator();
        tvReportBody.setText(generator.generateReportText("B1", 120, 85, 90));
        
        Button btnCopyReport = findViewById(R.id.btnCopyReport);
        btnCopyReport.setOnClickListener(v -> {
            Toast.makeText(this, "Report copied to clipboard!", Toast.LENGTH_SHORT).show();
        });
    }
}
