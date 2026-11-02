package hung.edu.apptienganh.ui.listening;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.speech.TtsManager;

public class ListeningTrainerActivity extends AppCompatActivity {

    private Button btnPlayAudio, btnSubmitAnswer;
    private TextView tvSentenceWithBlanks, tvFeedback;
    private EditText etAnswer;
    private TtsManager ttsManager;
    private String fullSentence = "She goes to the market every morning.";
    private String correctAnswers = "goes, morning";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_trainer);

        btnPlayAudio = findViewById(R.id.btnPlayAudio);
        btnSubmitAnswer = findViewById(R.id.btnSubmitAnswer);
        tvSentenceWithBlanks = findViewById(R.id.tvSentenceWithBlanks);
        tvFeedback = findViewById(R.id.tvFeedback);
        etAnswer = findViewById(R.id.etAnswer);

        ttsManager = new TtsManager(this);

        btnPlayAudio.setOnClickListener(v -> ttsManager.speakEnglishUs(fullSentence));
        
        btnSubmitAnswer.setOnClickListener(v -> {
            String answer = etAnswer.getText().toString().trim();
            if (answer.equalsIgnoreCase("goes, morning") || answer.equalsIgnoreCase("goes,morning")) {
                tvFeedback.setText("Chính xác!");
                tvFeedback.setTextColor(Color.GREEN);
            } else {
                tvFeedback.setText("Chưa đúng. Đáp án: " + correctAnswers);
                tvFeedback.setTextColor(Color.RED);
            }
        });
    }
}
