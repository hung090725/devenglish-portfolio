package hung.edu.apptienganh.ui.review;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hung.edu.apptienganh.R;

public class ReviewCenterActivity extends AppCompatActivity {

    private Button btnDueVocab, btnWeakGrammar, btnWeakSpeaking, btnQuizMistakes, btnFavoriteVocab, btnStartDailyReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_center);

        btnDueVocab = findViewById(R.id.btnDueVocab);
        btnWeakGrammar = findViewById(R.id.btnWeakGrammar);
        btnWeakSpeaking = findViewById(R.id.btnWeakSpeaking);
        btnQuizMistakes = findViewById(R.id.btnQuizMistakes);
        btnFavoriteVocab = findViewById(R.id.btnFavoriteVocab);
        btnStartDailyReview = findViewById(R.id.btnStartDailyReview);

        btnDueVocab.setOnClickListener(v -> showToast("Tính năng Ôn tập Từ vựng SRS đang phát triển."));
        btnWeakGrammar.setOnClickListener(v -> showToast("Tính năng Sửa lỗi Ngữ pháp đang phát triển."));
        btnWeakSpeaking.setOnClickListener(v -> showToast("Tính năng Luyện nói lại đang phát triển."));
        btnQuizMistakes.setOnClickListener(v -> showToast("Tính năng Ôn câu trắc nghiệm sai đang phát triển."));
        btnFavoriteVocab.setOnClickListener(v -> showToast("Tính năng Từ vựng yêu thích đang phát triển."));
        btnStartDailyReview.setOnClickListener(v -> showToast("Bắt đầu ôn tập tổng hợp..."));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
