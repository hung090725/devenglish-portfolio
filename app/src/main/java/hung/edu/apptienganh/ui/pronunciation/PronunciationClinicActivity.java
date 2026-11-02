package hung.edu.apptienganh.ui.pronunciation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import hung.edu.apptienganh.R;

public class PronunciationClinicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pronunciation_clinic);
        
        Button btnRecord = findViewById(R.id.btnRecordAudio);
        TextView tvFeedback = findViewById(R.id.tvFeedback);
        
        btnRecord.setOnClickListener(v -> {
            Toast.makeText(this, "Recording...", Toast.LENGTH_SHORT).show();
            tvFeedback.setText("Feedback: Tốt! Âm /i:/ rõ ràng.");
        });
    }
}
