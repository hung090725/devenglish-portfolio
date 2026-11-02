package hung.edu.apptienganh.ui.grammar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.entity.GrammarPoint;
import hung.edu.apptienganh.data.local.entity.QuizQuestion;
import hung.edu.apptienganh.data.repository.AppRepository;

public class GrammarDetailActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView txtTitle, txtFormula, txtUsage;
    private TextView txtCorrect, txtWrong, txtExplanation;
    private Button btnDoExercise;

    // Exercise Quiz Views
    private NestedScrollView scrollContent;
    private LinearLayout layoutExercise;
    private TextView txtExQuestion, txtExExplanation;
    private Button btnExOptA, btnExOptB, btnExOptC, btnExOptD;

    private AppRepository repository;
    private int grammarId;
    private int mappedLessonId;
    private GrammarPoint currentGrammar;
    private List<QuizQuestion> exerciseQuestions = new ArrayList<>();

    // State
    private boolean isExerciseMode = false;
    private int currentExIndex = 0;
    private String selectedAnswer = "";
    private boolean isAnswerChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_detail);

        btnBack = findViewById(R.id.btn_back);
        txtTitle = findViewById(R.id.txt_grammar_title);
        txtFormula = findViewById(R.id.txt_formula);
        txtUsage = findViewById(R.id.txt_usage);
        txtCorrect = findViewById(R.id.txt_correct_examples);
        txtWrong = findViewById(R.id.txt_wrong_examples);
        txtExplanation = findViewById(R.id.txt_explanation);
        btnDoExercise = findViewById(R.id.btn_do_exercise);

        scrollContent = findViewById(R.id.scroll_content);
        layoutExercise = findViewById(R.id.layout_exercise);
        txtExQuestion = findViewById(R.id.txt_exercise_question);
        txtExExplanation = findViewById(R.id.txt_ex_explanation);
        btnExOptA = findViewById(R.id.btn_ex_opt_a);
        btnExOptB = findViewById(R.id.btn_ex_opt_b);
        btnExOptC = findViewById(R.id.btn_ex_opt_c);
        btnExOptD = findViewById(R.id.btn_ex_opt_d);

        repository = new AppRepository(this);

        grammarId = getIntent().getIntExtra("grammar_id", 1);
        mapGrammarToLesson(grammarId);

        btnBack.setOnClickListener(v -> finish());
        setupClickListeners();
        loadGrammarData();
    }

    private void mapGrammarToLesson(int id) {
        // Ánh xạ Grammar ID sang Lesson ID
        switch (id) {
            case 1: mappedLessonId = 4; break; // Verb to be
            case 2: mappedLessonId = 5; break; // Personal Pronouns
            case 3: mappedLessonId = 6; break; // Possessive Adjectives
            case 4: mappedLessonId = 7; break; // Simple Present Basic
            default: mappedLessonId = 4; break;
        }
    }

    private void loadGrammarData() {
        repository.getGrammarPointById(grammarId).observe(this, grammar -> {
            if (grammar != null) {
                currentGrammar = grammar;
                txtTitle.setText(grammar.getTitle());
                txtFormula.setText(grammar.getFormula());
                txtUsage.setText(grammar.getUsageVi());
                txtCorrect.setText(grammar.getCorrectExamples());
                txtWrong.setText(grammar.getWrongExamples());
                txtExplanation.setText(grammar.getExplanationVi());
            }
        });

        // Load Quiz trắc nghiệm liên kết
        repository.getQuestionsForLesson(mappedLessonId).observe(this, questions -> {
            if (questions != null) {
                exerciseQuestions = questions;
                if (exerciseQuestions.isEmpty()) {
                    btnDoExercise.setText("Hoàn Thành Bài Học");
                }
            }
        });
    }

    private void setupClickListeners() {
        btnExOptA.setOnClickListener(v -> selectOption("A"));
        btnExOptB.setOnClickListener(v -> selectOption("B"));
        btnExOptC.setOnClickListener(v -> selectOption("C"));
        btnExOptD.setOnClickListener(v -> selectOption("D"));

        btnDoExercise.setOnClickListener(v -> {
            if (exerciseQuestions.isEmpty()) {
                completeLesson();
                return;
            }

            if (!isExerciseMode) {
                isExerciseMode = true;
                scrollContent.setVisibility(View.GONE);
                layoutExercise.setVisibility(View.VISIBLE);
                currentExIndex = 0;
                displayExerciseQuestion(0);
            } else {
                if (!isAnswerChecked) {
                    if (selectedAnswer.isEmpty()) {
                        Toast.makeText(this, "Vui lòng chọn một đáp án.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    checkExerciseAnswer();
                } else {
                    if (currentExIndex < exerciseQuestions.size() - 1) {
                        currentExIndex++;
                        displayExerciseQuestion(currentExIndex);
                    } else {
                        completeLesson();
                    }
                }
            }
        });
    }

    private void displayExerciseQuestion(int index) {
        isAnswerChecked = false;
        selectedAnswer = "";
        txtExExplanation.setVisibility(View.GONE);
        btnDoExercise.setText("Kiểm Tra Đáp Án");

        resetOptionButtons();

        QuizQuestion question = exerciseQuestions.get(index);
        txtExQuestion.setText("Câu " + (index + 1) + ": " + question.getQuestion());
        btnExOptA.setText("A. " + question.getOptionA());
        btnExOptB.setText("B. " + question.getOptionB());
        btnExOptC.setText("C. " + question.getOptionC());
        btnExOptD.setText("D. " + question.getOptionD());
    }

    private void selectOption(String opt) {
        if (isAnswerChecked) return;
        selectedAnswer = opt;
        resetOptionButtons();

        boolean isDarkMode = (getResources().getConfiguration().uiMode & 
                android.content.res.Configuration.UI_MODE_NIGHT_MASK) == 
                android.content.res.Configuration.UI_MODE_NIGHT_YES;
        int selectedBg = isDarkMode ? Color.parseColor("#312E81") : Color.parseColor("#EEF2FF");

        if (opt.equals("A")) btnExOptA.setBackgroundTintList(android.content.res.ColorStateList.valueOf(selectedBg));
        else if (opt.equals("B")) btnExOptB.setBackgroundTintList(android.content.res.ColorStateList.valueOf(selectedBg));
        else if (opt.equals("C")) btnExOptC.setBackgroundTintList(android.content.res.ColorStateList.valueOf(selectedBg));
        else if (opt.equals("D")) btnExOptD.setBackgroundTintList(android.content.res.ColorStateList.valueOf(selectedBg));
    }

    private void resetOptionButtons() {
        btnExOptA.setBackgroundTintList(null);
        btnExOptB.setBackgroundTintList(null);
        btnExOptC.setBackgroundTintList(null);
        btnExOptD.setBackgroundTintList(null);
    }

    private void checkExerciseAnswer() {
        isAnswerChecked = true;
        QuizQuestion question = exerciseQuestions.get(currentExIndex);
        String correctAns = question.getCorrectAnswer();

        int successColor = Color.parseColor("#D1FAE5");
        int errorColor = Color.parseColor("#FEE2E2");

        if (!selectedAnswer.equals(correctAns)) {
            highlightButton(selectedAnswer, errorColor);
        }
        highlightButton(correctAns, successColor);

        txtExExplanation.setText(question.getExplanationVi());
        txtExExplanation.setVisibility(View.VISIBLE);
        txtExExplanation.setTextColor(selectedAnswer.equals(correctAns) ? Color.parseColor("#10B981") : Color.parseColor("#EF4444"));

        if (currentExIndex < exerciseQuestions.size() - 1) {
            btnDoExercise.setText("Câu Tiếp Theo");
        } else {
            btnDoExercise.setText("Hoàn Thành & Trở Về");
        }
    }

    private void highlightButton(String opt, int color) {
        if (opt.equals("A")) btnExOptA.setBackgroundTintList(android.content.res.ColorStateList.valueOf(color));
        else if (opt.equals("B")) btnExOptB.setBackgroundTintList(android.content.res.ColorStateList.valueOf(color));
        else if (opt.equals("C")) btnExOptC.setBackgroundTintList(android.content.res.ColorStateList.valueOf(color));
        else if (opt.equals("D")) btnExOptD.setBackgroundTintList(android.content.res.ColorStateList.valueOf(color));
    }

    private void completeLesson() {
        repository.updateLessonStatus(mappedLessonId, true);
        Toast.makeText(this, "Chúc mừng! Bạn đã hoàn thành chủ điểm ngữ pháp này.", Toast.LENGTH_LONG).show();
        finish();
    }
}
