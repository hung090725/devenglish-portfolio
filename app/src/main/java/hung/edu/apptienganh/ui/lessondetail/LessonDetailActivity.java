package hung.edu.apptienganh.ui.lessondetail;

import android.content.Intent;
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
import hung.edu.apptienganh.data.local.entity.Lesson;
import hung.edu.apptienganh.data.local.entity.QuizQuestion;
import hung.edu.apptienganh.data.repository.AppRepository;
import hung.edu.apptienganh.speech.TtsManager;
import hung.edu.apptienganh.ui.speaking.SpeakingPracticeActivity;

public class LessonDetailActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView txtTitle, txtObjective, txtContentVi, txtContentEn;
    private Button btnSpeakExample, btnPracticeSpeak, btnBottomAction;

    // Quiz Views
    private NestedScrollView scrollContent;
    private LinearLayout layoutQuiz;
    private TextView txtQuizQuestion, txtQuizExplanation;
    private Button btnOptA, btnOptB, btnOptC, btnOptD;

    private AppRepository repository;
    private TtsManager ttsManager;
    private int lessonId;
    private Lesson currentLesson;
    private List<QuizQuestion> quizQuestions = new ArrayList<>();
    
    // Quiz State
    private boolean isQuizMode = false;
    private int currentQuizIndex = 0;
    private String selectedAnswer = "";
    private boolean isAnswerChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);

        btnBack = findViewById(R.id.btn_back);
        txtTitle = findViewById(R.id.txt_lesson_title);
        txtObjective = findViewById(R.id.txt_objective);
        txtContentVi = findViewById(R.id.txt_content_vi);
        txtContentEn = findViewById(R.id.txt_content_en);
        btnSpeakExample = findViewById(R.id.btn_speak_example);
        btnPracticeSpeak = findViewById(R.id.btn_practice_speak);
        btnBottomAction = findViewById(R.id.btn_bottom_action);

        scrollContent = findViewById(R.id.scroll_content);
        layoutQuiz = findViewById(R.id.layout_quiz);
        txtQuizQuestion = findViewById(R.id.txt_quiz_question);
        txtQuizExplanation = findViewById(R.id.txt_quiz_explanation);
        btnOptA = findViewById(R.id.btn_opt_a);
        btnOptB = findViewById(R.id.btn_opt_b);
        btnOptC = findViewById(R.id.btn_opt_c);
        btnOptD = findViewById(R.id.btn_opt_d);

        repository = new AppRepository(this);
        ttsManager = new TtsManager(this);

        lessonId = getIntent().getIntExtra("lesson_id", -1);
        if (lessonId == -1) {
            Toast.makeText(this, "Lỗi: Không tìm thấy bài học.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnBack.setOnClickListener(v -> finish());
        setupClickListeners();
        loadLessonData();
    }

    private void loadLessonData() {
        repository.getLessonById(lessonId).observe(this, lesson -> {
            if (lesson != null) {
                currentLesson = lesson;
                txtTitle.setText(lesson.getTitle());
                txtObjective.setText(lesson.getObjective());
                txtContentVi.setText(lesson.getContentVi());
                txtContentEn.setText(lesson.getContentEn());
            }
        });

        // Load Quiz Questions
        repository.getQuestionsForLesson(lessonId).observe(this, questions -> {
            if (questions != null) {
                quizQuestions = questions;
                if (quizQuestions.isEmpty()) {
                    btnBottomAction.setText("Hoàn Thành Bài Học");
                }
            }
        });
    }

    private void setupClickListeners() {
        // Phát âm ví dụ
        btnSpeakExample.setOnClickListener(v -> {
            if (currentLesson != null && ttsManager != null) {
                String englishText = extractEnglishExample(currentLesson.getContentEn());
                if (!englishText.isEmpty()) {
                    ttsManager.speakEnglishUs(englishText);
                }
            }
        });

        // Luyện nói câu ví dụ
        btnPracticeSpeak.setOnClickListener(v -> {
            if (currentLesson != null) {
                String englishText = extractEnglishExample(currentLesson.getContentEn());
                if (englishText.isEmpty()) {
                    Toast.makeText(this, "Bài học chưa có câu ví dụ tiếng Anh.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(LessonDetailActivity.this, SpeakingPracticeActivity.class);
                intent.putExtra("target_sentence", englishText);
                intent.putExtra("prompt_title", "Luyện nói câu ví dụ");
                startActivity(intent);
            }
        });

        // Quiz option selection
        btnOptA.setOnClickListener(v -> selectOption("A"));
        btnOptB.setOnClickListener(v -> selectOption("B"));
        btnOptC.setOnClickListener(v -> selectOption("C"));
        btnOptD.setOnClickListener(v -> selectOption("D"));

        // Bottom action click
        btnBottomAction.setOnClickListener(v -> {
            if (quizQuestions.isEmpty()) {
                // Không có quiz -> Hoàn thành bài học trực tiếp
                completeLesson();
                return;
            }

            if (!isQuizMode) {
                // Vào chế độ Quiz
                isQuizMode = true;
                scrollContent.setVisibility(View.GONE);
                layoutQuiz.setVisibility(View.VISIBLE);
                currentQuizIndex = 0;
                displayQuizQuestion(0);
            } else {
                // Đang trong chế độ Quiz
                if (!isAnswerChecked) {
                    if (selectedAnswer.isEmpty()) {
                        Toast.makeText(this, "Vui lòng chọn một đáp án.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    checkQuizAnswer();
                } else {
                    // Chuyển câu tiếp theo hoặc hoàn thành
                    if (currentQuizIndex < quizQuestions.size() - 1) {
                        currentQuizIndex++;
                        displayQuizQuestion(currentQuizIndex);
                    } else {
                        // Đã làm hết quiz -> Hoàn thành bài học
                        completeLesson();
                    }
                }
            }
        });
    }

    private void displayQuizQuestion(int index) {
        isAnswerChecked = false;
        selectedAnswer = "";
        txtQuizExplanation.setVisibility(View.GONE);
        btnBottomAction.setText("Kiểm Tra Đáp Án");

        resetOptionButtons();

        QuizQuestion question = quizQuestions.get(index);
        txtQuizQuestion.setText("Câu " + (index + 1) + ": " + question.getQuestion());
        btnOptA.setText("A. " + question.getOptionA());
        btnOptB.setText("B. " + question.getOptionB());
        btnOptC.setText("C. " + question.getOptionC());
        btnOptD.setText("D. " + question.getOptionD());
    }

    private void selectOption(String opt) {
        if (isAnswerChecked) return; // Không cho chọn lại sau khi đã check
        
        selectedAnswer = opt;
        resetOptionButtons();

        boolean isDarkMode = (getResources().getConfiguration().uiMode & 
                android.content.res.Configuration.UI_MODE_NIGHT_MASK) == 
                android.content.res.Configuration.UI_MODE_NIGHT_YES;
        int selectedBg = isDarkMode ? Color.parseColor("#312E81") : Color.parseColor("#EEF2FF");

        if (opt.equals("A")) btnOptA.setBackgroundTintList(android.content.res.ColorStateList.valueOf(selectedBg));
        else if (opt.equals("B")) btnOptB.setBackgroundTintList(android.content.res.ColorStateList.valueOf(selectedBg));
        else if (opt.equals("C")) btnOptC.setBackgroundTintList(android.content.res.ColorStateList.valueOf(selectedBg));
        else if (opt.equals("D")) btnOptD.setBackgroundTintList(android.content.res.ColorStateList.valueOf(selectedBg));
    }

    private void resetOptionButtons() {
        btnOptA.setBackgroundTintList(null);
        btnOptB.setBackgroundTintList(null);
        btnOptC.setBackgroundTintList(null);
        btnOptD.setBackgroundTintList(null);
    }

    private void checkQuizAnswer() {
        isAnswerChecked = true;
        QuizQuestion question = quizQuestions.get(currentQuizIndex);
        String correctAns = question.getCorrectAnswer();

        // Đánh dấu đáp án đúng/sai bằng màu sắc
        int successColor = Color.parseColor("#D1FAE5"); // Light green
        int errorColor = Color.parseColor("#FEE2E2");   // Light red
        
        // Tô màu đỏ cho đáp án chọn sai
        if (!selectedAnswer.equals(correctAns)) {
            highlightButton(selectedAnswer, errorColor);
        }
        
        // Tô màu xanh cho đáp án đúng
        highlightButton(correctAns, successColor);

        // Hiển thị giải thích
        txtQuizExplanation.setText(question.getExplanationVi());
        txtQuizExplanation.setVisibility(View.VISIBLE);
        txtQuizExplanation.setTextColor(selectedAnswer.equals(correctAns) ? Color.parseColor("#10B981") : Color.parseColor("#EF4444"));

        if (currentQuizIndex < quizQuestions.size() - 1) {
            btnBottomAction.setText("Câu Tiếp Theo");
        } else {
            btnBottomAction.setText("Hoàn Thành & Trở Về");
        }
    }

    private void highlightButton(String opt, int color) {
        if (opt.equals("A")) btnOptA.setBackgroundTintList(android.content.res.ColorStateList.valueOf(color));
        else if (opt.equals("B")) btnOptB.setBackgroundTintList(android.content.res.ColorStateList.valueOf(color));
        else if (opt.equals("C")) btnOptC.setBackgroundTintList(android.content.res.ColorStateList.valueOf(color));
        else if (opt.equals("D")) btnOptD.setBackgroundTintList(android.content.res.ColorStateList.valueOf(color));
    }

    private String extractEnglishExample(String contentEn) {
        if (contentEn == null || contentEn.trim().isEmpty()) {
            return "";
        }
        return contentEn.split("\n")[0].trim();
    }

    private void completeLesson() {
        repository.updateLessonStatus(lessonId, true);
        Toast.makeText(this, "Chúc mừng! Bạn đã hoàn thành bài học " + (currentLesson != null ? currentLesson.getTitle() : ""), Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ttsManager != null) {
            ttsManager.shutdown();
        }
    }
}
