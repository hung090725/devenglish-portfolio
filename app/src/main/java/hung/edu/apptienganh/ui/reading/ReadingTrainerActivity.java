package hung.edu.apptienganh.ui.reading;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import hung.edu.apptienganh.R;

public class ReadingTrainerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_trainer);
        
        Button btnAnswer = findViewById(R.id.btnAnswerQuestions);
        btnAnswer.setOnClickListener(v -> {
            Toast.makeText(this, "Opening questions...", Toast.LENGTH_SHORT).show();
        });
    }
}
