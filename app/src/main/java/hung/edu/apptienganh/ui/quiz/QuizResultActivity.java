package hung.edu.apptienganh.ui.quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.ai.AiExplainerDialog;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.entity.QuizAnswer;
import hung.edu.apptienganh.data.local.entity.QuizAttempt;
import hung.edu.apptienganh.data.repository.AppRepository;

public class QuizResultActivity extends AppCompatActivity {

    public static final String EXTRA_ATTEMPT_ID = "attempt_id";

    private TextView txtScorePercent, txtScoreSummary, txtNoWrong;
    private RecyclerView rvWrongAnswers;
    private Button btnDone;
    private ImageView btnBack;

    private AppRepository repository;
    private QuizWrongAnswerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        txtScorePercent = findViewById(R.id.txt_score_percent);
        txtScoreSummary = findViewById(R.id.txt_score_summary);
        txtNoWrong = findViewById(R.id.txt_no_wrong);
        rvWrongAnswers = findViewById(R.id.rv_wrong_answers);
        btnDone = findViewById(R.id.btn_done);
        btnBack = findViewById(R.id.btn_back);

        repository = new AppRepository(this);
        adapter = new QuizWrongAnswerAdapter(this::onAskAiClicked);
        rvWrongAnswers.setLayoutManager(new LinearLayoutManager(this));
        rvWrongAnswers.setAdapter(adapter);

        btnBack.setOnClickListener(v -> finish());
        btnDone.setOnClickListener(v -> finish());

        int attemptId = getIntent().getIntExtra(EXTRA_ATTEMPT_ID, -1);
        if (attemptId > 0) {
            loadResult(attemptId);
        } else {
            finish();
        }
    }

    private void onAskAiClicked(QuizAnswer answer) {
        AiExplainerDialog.show(
                this,
                "Câu hỏi #" + answer.getQuestionId(),
                null,
                answer.getCorrectAnswer(),
                answer.getSelectedAnswer()
        );
    }

    private void loadResult(int attemptId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            QuizAttempt attempt = repository.getQuizAttemptSync(attemptId);
            List<QuizAnswer> answers = repository.getQuizAnswersForAttempt(attemptId);
            List<QuizAnswer> wrong = new ArrayList<>();
            if (answers != null) {
                for (QuizAnswer a : answers) {
                    if (!a.isCorrect()) wrong.add(a);
                }
            }
            List<QuizAnswer> finalWrong = wrong;
            runOnUiThread(() -> {
                if (attempt != null) {
                    txtScorePercent.setText(attempt.getScore() + "%");
                    txtScoreSummary.setText(attempt.getCorrectCount() + " đúng / "
                            + attempt.getWrongCount() + " sai / "
                            + attempt.getTotalQuestions() + " câu");
                }
                if (finalWrong.isEmpty()) {
                    txtNoWrong.setVisibility(View.VISIBLE);
                    rvWrongAnswers.setVisibility(View.GONE);
                } else {
                    txtNoWrong.setVisibility(View.GONE);
                    rvWrongAnswers.setVisibility(View.VISIBLE);
                    adapter.submitList(finalWrong);
                }
            });
        });
    }
}
