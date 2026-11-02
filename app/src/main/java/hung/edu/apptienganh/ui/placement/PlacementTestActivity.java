package hung.edu.apptienganh.ui.placement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.MainActivity;
import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.entity.PlacementResult;
import hung.edu.apptienganh.domain.model.LevelConstants;

public class PlacementTestActivity extends AppCompatActivity {

    private TextView tvQuestionProgress, tvQuestion, tvResultSuggestedLevel, tvResultExplanation, tvTitle;
    private Button btnOptionA, btnOptionB, btnOptionC, btnOptionD;
    private LinearLayout layoutResult;
    private Button btnStartSuggested, btnStartA0;

    private List<Question> questionList = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int[] levelScores = new int[6]; // A0=0, A1=1, A2=2, B1=3, B2=4, C1=5

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_test);

        tvTitle = findViewById(R.id.tvTitle);
        tvQuestionProgress = findViewById(R.id.tvQuestionProgress);
        tvQuestion = findViewById(R.id.tvQuestion);
        btnOptionA = findViewById(R.id.btnOptionA);
        btnOptionB = findViewById(R.id.btnOptionB);
        btnOptionC = findViewById(R.id.btnOptionC);
        btnOptionD = findViewById(R.id.btnOptionD);

        layoutResult = findViewById(R.id.layoutResult);
        tvResultSuggestedLevel = findViewById(R.id.tvResultSuggestedLevel);
        tvResultExplanation = findViewById(R.id.tvResultExplanation);
        btnStartSuggested = findViewById(R.id.btnStartSuggested);
        btnStartA0 = findViewById(R.id.btnStartA0);

        loadDummyQuestions();
        showQuestion();

        View.OnClickListener answerListener = v -> {
            Button btn = (Button) v;
            checkAnswer(btn.getText().toString());
        };

        btnOptionA.setOnClickListener(answerListener);
        btnOptionB.setOnClickListener(answerListener);
        btnOptionC.setOnClickListener(answerListener);
        btnOptionD.setOnClickListener(answerListener);

        btnStartA0.setOnClickListener(v -> finishTest(LevelConstants.A0));
        btnStartSuggested.setOnClickListener(v -> finishTest(getSuggestedLevelStr()));
    }

    private void checkAnswer(String selectedAnswer) {
        Question q = questionList.get(currentQuestionIndex);
        if (selectedAnswer.equals(q.correctAnswer)) {
            score++;
            switch (q.levelTag) {
                case "A0": levelScores[0]++; break;
                case "A1": levelScores[1]++; break;
                case "A2": levelScores[2]++; break;
                case "B1": levelScores[3]++; break;
                case "B2": levelScores[4]++; break;
                case "C1": levelScores[5]++; break;
            }
        }
        currentQuestionIndex++;
        if (currentQuestionIndex < questionList.size()) {
            showQuestion();
        } else {
            showResult();
        }
    }

    private void showQuestion() {
        Question q = questionList.get(currentQuestionIndex);
        tvQuestionProgress.setText("Câu " + (currentQuestionIndex + 1) + " / " + questionList.size());
        tvQuestion.setText(q.question);
        btnOptionA.setText(q.optionA);
        btnOptionB.setText(q.optionB);
        btnOptionC.setText(q.optionC);
        btnOptionD.setText(q.optionD);
    }

    private void showResult() {
        tvTitle.setVisibility(View.GONE);
        tvQuestionProgress.setVisibility(View.GONE);
        tvQuestion.setVisibility(View.GONE);
        btnOptionA.setVisibility(View.GONE);
        btnOptionB.setVisibility(View.GONE);
        btnOptionC.setVisibility(View.GONE);
        btnOptionD.setVisibility(View.GONE);

        layoutResult.setVisibility(View.VISIBLE);
        
        String suggested = getSuggestedLevelStr();
        tvResultSuggestedLevel.setText("Trình độ gợi ý: " + suggested);
        
        String explanation = "Bạn trả lời đúng " + score + "/" + questionList.size() + " câu. \n" +
                "Bạn có nền tảng tốt ở các trình độ dưới " + suggested + ". \n" +
                "Hãy bắt đầu học từ " + suggested + " để đạt hiệu quả tốt nhất.";
        tvResultExplanation.setText(explanation);

        AppDatabase.databaseWriteExecutor.execute(() -> {
            PlacementResult pr = new PlacementResult(System.currentTimeMillis(), score, questionList.size(), suggested, explanation);
            AppDatabase.getInstance(this).placementResultDao().insert(pr);
        });
    }

    private String getSuggestedLevelStr() {
        if (score < 5) return LevelConstants.A0;
        if (score < 10) return LevelConstants.A1;
        if (score < 15) return LevelConstants.A2;
        if (score < 20) return LevelConstants.B1;
        if (score < 25) return LevelConstants.B2;
        return LevelConstants.C1;
    }

    private void finishTest(String chosenLevel) {
        Toast.makeText(this, "Đã lưu lựa chọn: " + chosenLevel, Toast.LENGTH_SHORT).show();
        // Cần lưu profile hoặc mở khoá các bài đến chosenLevel. Tạm thời chỉ trở về MainActivity.
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void loadDummyQuestions() {
        // Dummy 30 questions
        for (int i = 1; i <= 30; i++) {
            String tag = "A0";
            if (i > 5) tag = "A1";
            if (i > 10) tag = "A2";
            if (i > 15) tag = "B1";
            if (i > 20) tag = "B2";
            if (i > 25) tag = "C1";
            questionList.add(new Question("Question " + i + " (" + tag + ")", "A", "B", "C", "D", "A", tag));
        }
    }

    private static class Question {
        String question, optionA, optionB, optionC, optionD, correctAnswer, levelTag;
        Question(String q, String a, String b, String c, String d, String correct, String tag) {
            question = q; optionA = a; optionB = b; optionC = c; optionD = d; correctAnswer = correct; levelTag = tag;
        }
    }
}
