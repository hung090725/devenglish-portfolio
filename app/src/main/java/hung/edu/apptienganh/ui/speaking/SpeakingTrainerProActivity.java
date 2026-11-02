package hung.edu.apptienganh.ui.speaking;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.entity.SpeakingWeakSentence;

public class SpeakingTrainerProActivity extends AppCompatActivity {

    private TextView tvSentenceToSpeak, tvRecognizedText, tvScore, tvFeedback;
    private Button btnMic;
    private String targetSentence = "I want to improve my English speaking skills";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaking_trainer_pro);

        tvSentenceToSpeak = findViewById(R.id.tvSentenceToSpeak);
        tvRecognizedText = findViewById(R.id.tvRecognizedText);
        tvScore = findViewById(R.id.tvScore);
        tvFeedback = findViewById(R.id.tvFeedback);
        btnMic = findViewById(R.id.btnMic);

        tvSentenceToSpeak.setText(targetSentence + ".");

        btnMic.setOnClickListener(v -> {
            Toast.makeText(this, "Đang ghi âm (giả lập)...", Toast.LENGTH_SHORT).show();
            // Dummy logic
            String recognized = "I want to improve my speaking skills";
            int score = 80;
            tvRecognizedText.setText("Bạn đã nói: " + recognized);
            tvScore.setText("Điểm: " + score + "/100");

            if (score < 90) {
                tvFeedback.setText("Bạn phát âm sai một số từ. Câu này sẽ được lưu vào danh sách Weak Sentences.");
                AppDatabase.databaseWriteExecutor.execute(() -> {
                    SpeakingWeakSentence weak = new SpeakingWeakSentence(targetSentence, recognized, "B1", java.util.UUID.randomUUID().toString(), System.currentTimeMillis(), score, false);
                    AppDatabase.getInstance(this).speakingWeakSentenceDao().insert(weak);
                });
            } else {
                tvFeedback.setText("Tuyệt vời!");
            }
        });
    }
}
