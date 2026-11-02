package hung.edu.apptienganh.ui.exam;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import hung.edu.apptienganh.R;

public class ExamSimulatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_simulator);
        
        Button btnStartExam = findViewById(R.id.btnStartExam);
        btnStartExam.setOnClickListener(v -> {
            Toast.makeText(this, "Exam started!", Toast.LENGTH_SHORT).show();
        });
    }
}
