package hung.edu.apptienganh.ui.lessondetail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.entity.LearningDay;
import hung.edu.apptienganh.data.local.entity.QuizQuestion;
import hung.edu.apptienganh.data.local.entity.SpeakingResult;
import hung.edu.apptienganh.data.repository.AppRepository;
import hung.edu.apptienganh.domain.model.DaySection;
import hung.edu.apptienganh.domain.quiz.QuizScorer;
import hung.edu.apptienganh.domain.scorer.SpeakingScorer;
import hung.edu.apptienganh.speech.SpeechRecognitionManager;
import hung.edu.apptienganh.speech.TtsManager;
import hung.edu.apptienganh.ui.quiz.QuizResultActivity;

public class LessonDayActivity extends AppCompatActivity {

    private TextView txtDayHeader, txtObjective, txtPronunciation, txtVocabulary, txtGrammar;
    private TextView txtConversation, txtListening, txtSpeakingTarget, txtSpeakingFeedback, txtLatestSpeaking;
    private TextView txtQuizQuestion, txtQuizExplanation, txtQuizScore;
    private Button btnSpeakPronunciation, btnSpeakListening, btnSpeakTarget;
    private Button btnQuizA, btnQuizB, btnQuizC, btnQuizD, btnComplete;
    private FrameLayout btnRecord;
    private ImageView imgRecordIcon, btnBack;
    private NestedScrollView scrollContent;

    private AppRepository repository;
    private TtsManager ttsManager;
    private SpeechRecognitionManager speechManager;

    private String level = "A0";
    private int dayIndex = 1;
    private int learningDayId = 1;
    private LearningDay currentDay;
    private String speakingTarget = "";
    private String listeningSample = "";

    private List<QuizQuestion> quizQuestions = new ArrayList<>();
    private final List<QuizScorer.AnswerRecord> quizAnswerRecords = new ArrayList<>();
    private int quizIndex = 0;
    private int quizCorrect = 0;
    private String selectedAnswer = "";
    private boolean quizChecked = false;
    private int speakingScore = 0;
    private int quizScorePercent = 0;

    private int currentSectionIndex = DaySection.OBJECTIVE;
    private final View[] sectionAnchors = new View[9];
    private final Handler scrollHandler = new Handler(Looper.getMainLooper());
    private Runnable scrollSaveRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_day);

        bindViews();
        repository = new AppRepository(this);
        ttsManager = new TtsManager(this);

        level = getIntent().getStringExtra("level");
        if (level == null) level = "A0";
        dayIndex = getIntent().getIntExtra("day_index", -1);
        if (dayIndex <= 0) {
            Toast.makeText(this, "Lỗi dữ liệu bài học. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        currentSectionIndex = getIntent().getIntExtra("section_index", DaySection.OBJECTIVE);

        btnBack.setOnClickListener(v -> finish());
        setupSpeech();
        setupQuizButtons();
        setupSectionTracking();
        loadDay();
    }

    private void bindViews() {
        btnBack = findViewById(R.id.btn_back);
        scrollContent = findViewById(R.id.scroll_content);
        txtDayHeader = findViewById(R.id.txt_day_header);
        txtObjective = findViewById(R.id.txt_objective_content);
        txtPronunciation = findViewById(R.id.txt_pronunciation);
        txtVocabulary = findViewById(R.id.txt_vocabulary);
        txtGrammar = findViewById(R.id.txt_grammar);
        txtConversation = findViewById(R.id.txt_conversation);
        txtListening = findViewById(R.id.txt_listening);
        txtSpeakingTarget = findViewById(R.id.txt_speaking_target);
        txtSpeakingFeedback = findViewById(R.id.txt_speaking_feedback);
        txtLatestSpeaking = findViewById(R.id.txt_latest_speaking);
        btnSpeakPronunciation = findViewById(R.id.btn_speak_pronunciation);
        btnSpeakListening = findViewById(R.id.btn_speak_listening);
        btnSpeakTarget = findViewById(R.id.btn_speak_target);
        btnRecord = findViewById(R.id.btn_record);
        imgRecordIcon = findViewById(R.id.img_record_icon);
        txtQuizQuestion = findViewById(R.id.txt_quiz_question);
        txtQuizExplanation = findViewById(R.id.txt_quiz_explanation);
        txtQuizScore = findViewById(R.id.txt_quiz_score);
        btnQuizA = findViewById(R.id.btn_quiz_a);
        btnQuizB = findViewById(R.id.btn_quiz_b);
        btnQuizC = findViewById(R.id.btn_quiz_c);
        btnQuizD = findViewById(R.id.btn_quiz_d);
        btnComplete = findViewById(R.id.btn_complete);

        sectionAnchors[DaySection.OBJECTIVE] = findViewById(R.id.section_objective);
        sectionAnchors[DaySection.PRONUNCIATION] = findViewById(R.id.section_pronunciation);
        sectionAnchors[DaySection.VOCABULARY] = findViewById(R.id.section_vocabulary);
        sectionAnchors[DaySection.GRAMMAR] = findViewById(R.id.section_grammar);
        sectionAnchors[DaySection.CONVERSATION] = findViewById(R.id.section_conversation);
        sectionAnchors[DaySection.LISTENING] = findViewById(R.id.section_listening);
        sectionAnchors[DaySection.SPEAKING] = findViewById(R.id.section_speaking);
        sectionAnchors[DaySection.QUIZ] = findViewById(R.id.section_quiz);
    }

    private void setupSectionTracking() {
        scrollContent.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            int detected = detectCurrentSection(scrollY);
            if (detected != currentSectionIndex) {
                currentSectionIndex = detected;
                scheduleSaveSection();
            }
        });
    }

    private int detectCurrentSection(int scrollY) {
        int detected = DaySection.OBJECTIVE;
        for (int i = DaySection.QUIZ; i >= DaySection.OBJECTIVE; i--) {
            View anchor = sectionAnchors[i];
            if (anchor != null && anchor.getTop() <= scrollY + 120) {
                detected = i;
                break;
            }
        }
        return detected;
    }

    private void scheduleSaveSection() {
        if (scrollSaveRunnable != null) {
            scrollHandler.removeCallbacks(scrollSaveRunnable);
        }
        scrollSaveRunnable = () -> repository.saveDaySection(level, dayIndex, currentSectionIndex);
        scrollHandler.postDelayed(scrollSaveRunnable, 400);
    }

    private void scrollToSection(int sectionIndex) {
        View anchor = sectionAnchors[sectionIndex];
        if (anchor != null) {
            scrollContent.post(() -> scrollContent.smoothScrollTo(0, anchor.getTop()));
        }
    }

    private void loadDay() {
        repository.markDayInProgress(level, dayIndex, currentSectionIndex);
        repository.getLearningDay(level, dayIndex).observe(this, day -> {
            if (day == null) {
                Toast.makeText(this, "Không tìm thấy dữ liệu bài học.", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
            currentDay = day;
            learningDayId = day.getId();
            bindDayContent(day);
            loadQuiz(day.getId());
            loadLatestSpeaking(day.getId());
            scrollContent.post(() -> scrollToSection(currentSectionIndex));

            ImageView btnReport = findViewById(R.id.btn_report_content);
            btnReport.setOnClickListener(v -> {
                Intent intent = new Intent(this, hung.edu.apptienganh.ui.feedback.BetaFeedbackActivity.class);
                intent.putExtra(hung.edu.apptienganh.ui.feedback.BetaFeedbackActivity.EXTRA_CATEGORY, "Lesson content issue");
                intent.putExtra(hung.edu.apptienganh.ui.feedback.BetaFeedbackActivity.EXTRA_SCREEN_NAME, "LessonDayActivity - " + day.getTitle());
                startActivity(intent);
            });
        });
    }

    private void loadLatestSpeaking(int learningDayId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            SpeakingResult latest = repository.getLatestSpeakingForDaySync(learningDayId);
            if (latest != null) {
                runOnUiThread(() -> {
                    txtLatestSpeaking.setText("Điểm nói gần nhất: " + latest.getScore() + "%");
                    txtLatestSpeaking.setVisibility(View.VISIBLE);
                    speakingScore = latest.getScore();
                });
            }
        });
    }

    private void bindDayContent(LearningDay day) {
        txtDayHeader.setText(day.getTitle());
        txtObjective.setText(day.getObjectiveVi() + "\n\n" + day.getLessonSummaryVi());
        txtPronunciation.setText(day.getPronunciationFocus());
        txtVocabulary.setText(formatWordList(day.getVocabularyList()));
        txtGrammar.setText(day.getGrammarPoint());
        txtConversation.setText(formatPipeLines(day.getConversationSentences()));
        txtListening.setText(formatPipeLines(day.getListeningSentences()));

        speakingTarget = firstLine(day.getSpeakingPracticeSentences());
        listeningSample = firstEnglishFromPipe(day.getListeningSentences());
        if (speakingTarget.isEmpty()) {
            speakingTarget = firstEnglishFromPipe(day.getConversationSentences());
        }
        txtSpeakingTarget.setText(speakingTarget);

        btnSpeakPronunciation.setOnClickListener(v -> {
            currentSectionIndex = DaySection.PRONUNCIATION;
            repository.saveDaySection(level, dayIndex, currentSectionIndex);
            speak(firstEnglishFromText(day.getPronunciationFocus()));
        });
        btnSpeakListening.setOnClickListener(v -> {
            currentSectionIndex = DaySection.LISTENING;
            repository.saveDaySection(level, dayIndex, currentSectionIndex);
            speak(listeningSample);
        });
        btnSpeakTarget.setOnClickListener(v -> {
            currentSectionIndex = DaySection.SPEAKING;
            repository.saveDaySection(level, dayIndex, currentSectionIndex);
            speak(speakingTarget);
        });

        btnComplete.setOnClickListener(v -> completeDay());
    }

    private void loadQuiz(int learningDayId) {
        repository.getQuestionsForLearningDay(learningDayId).observe(this, questions -> {
            if (questions != null && !questions.isEmpty()) {
                quizQuestions = questions;
                quizIndex = 0;
                quizCorrect = 0;
                quizAnswerRecords.clear();
                showQuizQuestion(0);
            } else {
                txtQuizQuestion.setText("Chưa có câu hỏi trắc nghiệm cho bài học này.");
                btnQuizA.setVisibility(View.GONE);
                btnQuizB.setVisibility(View.GONE);
                btnQuizC.setVisibility(View.GONE);
                btnQuizD.setVisibility(View.GONE);
            }
        });
    }

    private void setupQuizButtons() {
        btnQuizA.setOnClickListener(v -> selectQuiz("A"));
        btnQuizB.setOnClickListener(v -> selectQuiz("B"));
        btnQuizC.setOnClickListener(v -> selectQuiz("C"));
        btnQuizD.setOnClickListener(v -> selectQuiz("D"));
    }

    private void showQuizQuestion(int index) {
        if (quizQuestions.isEmpty()) return;
        currentSectionIndex = DaySection.QUIZ;
        repository.saveDaySection(level, dayIndex, currentSectionIndex);
        quizChecked = false;
        selectedAnswer = "";
        txtQuizExplanation.setVisibility(View.GONE);
        resetQuizButtons();

        QuizQuestion q = quizQuestions.get(index);
        txtQuizQuestion.setText("Câu " + (index + 1) + "/" + quizQuestions.size() + ": " + q.getQuestion());
        btnQuizA.setText("A. " + q.getOptionA());
        btnQuizB.setText("B. " + q.getOptionB());
        btnQuizC.setText("C. " + q.getOptionC());
        btnQuizD.setText("D. " + q.getOptionD());
    }

    private void selectQuiz(String opt) {
        if (quizChecked || quizQuestions.isEmpty()) return;
        selectedAnswer = opt;
        resetQuizButtons();
        int tint = Color.parseColor("#EEF2FF");
        if ("A".equals(opt)) btnQuizA.setBackgroundTintList(android.content.res.ColorStateList.valueOf(tint));
        if ("B".equals(opt)) btnQuizB.setBackgroundTintList(android.content.res.ColorStateList.valueOf(tint));
        if ("C".equals(opt)) btnQuizC.setBackgroundTintList(android.content.res.ColorStateList.valueOf(tint));
        if ("D".equals(opt)) btnQuizD.setBackgroundTintList(android.content.res.ColorStateList.valueOf(tint));

        checkQuizAnswer();
    }

    private void checkQuizAnswer() {
        quizChecked = true;
        QuizQuestion q = quizQuestions.get(quizIndex);
        QuizScorer.AnswerRecord record = QuizScorer.recordAnswer(q, selectedAnswer);
        quizAnswerRecords.add(record);
        if (record.isCorrect()) {
            quizCorrect++;
        }
        txtQuizExplanation.setText(q.getExplanationVi());
        txtQuizExplanation.setVisibility(View.VISIBLE);

        if (quizIndex < quizQuestions.size() - 1) {
            quizIndex++;
            txtQuizQuestion.postDelayed(() -> showQuizQuestion(quizIndex), 1200);
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        QuizScorer.QuizScoreResult result = QuizScorer.calculate(quizAnswerRecords);
        quizScorePercent = result.getScorePercent();
        txtQuizScore.setText("Điểm quiz: " + quizScorePercent + "%");
        txtQuizScore.setVisibility(View.VISIBLE);

        repository.saveQuizAttempt(learningDayId, quizAnswerRecords, attemptId -> runOnUiThread(() -> {
            Intent intent = new Intent(LessonDayActivity.this, QuizResultActivity.class);
            intent.putExtra(QuizResultActivity.EXTRA_ATTEMPT_ID, (int) attemptId);
            startActivity(intent);
        }));
    }

    private void resetQuizButtons() {
        btnQuizA.setBackgroundTintList(null);
        btnQuizB.setBackgroundTintList(null);
        btnQuizC.setBackgroundTintList(null);
        btnQuizD.setBackgroundTintList(null);
    }

    private void setupSpeech() {
        speechManager = new SpeechRecognitionManager(this, new SpeechRecognitionManager.SpeechRecognitionCallback() {
            @Override public void onReadyForSpeech() {
                imgRecordIcon.setImageResource(android.R.drawable.ic_btn_speak_now);
            }
            @Override public void onBeginningOfSpeech() {}
            @Override public void onRmsChanged(float rmsdB) {}
            @Override public void onBufferReceived(byte[] buffer) {}
            @Override public void onEndOfSpeech() {
                imgRecordIcon.setImageResource(R.drawable.ic_mic);
            }
            @Override public void onError(String errorMessage) {
                Toast.makeText(LessonDayActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                imgRecordIcon.setImageResource(R.drawable.ic_mic);
            }
            @Override public void onResults(String recognizedText) {
                SpeakingScorer.SpeakingScoreResult result =
                        SpeakingScorer.calculateScore(speakingTarget, recognizedText);
                speakingScore = result.getScore();
                txtSpeakingFeedback.setText(result.getFeedbackVi() + " (" + result.getScore() + "%)");
                txtSpeakingFeedback.setVisibility(View.VISIBLE);
                txtLatestSpeaking.setText("Điểm nói gần nhất: " + result.getScore() + "%");
                txtLatestSpeaking.setVisibility(View.VISIBLE);
                saveSpeakingResult(result, recognizedText);
            }
            @Override public void onPartialResults(String partialText) {}
        });

        btnRecord.setOnClickListener(v -> {
            if (!hung.edu.apptienganh.utils.PermissionHelper.hasMicrophonePermission(this)) {
                hung.edu.apptienganh.utils.PermissionHelper.requestPermissionWithExplanation(
                        this,
                        android.Manifest.permission.RECORD_AUDIO,
                        SpeechRecognitionManager.RECORD_AUDIO_REQUEST_CODE,
                        "Cấp Quyền Micro",
                        "App cần quyền sử dụng Micro để thu âm giọng nói của bạn và chấm điểm phát âm. Xin hãy cấp quyền nhé!",
                        new hung.edu.apptienganh.utils.PermissionHelper.PermissionCallback() {
                            @Override
                            public void onGranted() {
                                currentSectionIndex = DaySection.SPEAKING;
                                repository.saveDaySection(level, dayIndex, currentSectionIndex);
                                txtSpeakingFeedback.setVisibility(View.GONE);
                                speechManager.startListening();
                            }
                            @Override
                            public void onDenied() {
                                Toast.makeText(LessonDayActivity.this, "Không thể luyện nói nếu thiếu quyền Micro.", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                return;
            }
            if (speechManager.isListening()) {
                speechManager.stopListening();
            } else {
                currentSectionIndex = DaySection.SPEAKING;
                repository.saveDaySection(level, dayIndex, currentSectionIndex);
                txtSpeakingFeedback.setVisibility(View.GONE);
                speechManager.startListening();
            }
        });
    }

    private void saveSpeakingResult(SpeakingScorer.SpeakingScoreResult result, String recognizedText) {
        SpeakingResult sr = new SpeakingResult();
        sr.setUserId(AppDatabase.DEFAULT_USER_ID);
        sr.setLearningDayId(learningDayId);
        sr.setTargetSentence(speakingTarget);
        sr.setRecognizedText(recognizedText == null ? "" : recognizedText);
        sr.setScore(result.getScore());
        sr.setCorrectWords(joinList(result.getCorrectWords()));
        sr.setMissingWords(joinList(result.getMissingWords()));
        sr.setExtraWords(joinList(result.getExtraWords()));
        sr.setWrongWords(joinList(result.getWrongWords()));
        sr.setFeedbackVi(result.getFeedbackVi());
        sr.setCreatedAt(System.currentTimeMillis());
        repository.insertSpeakingResult(sr);
    }

    private static String joinList(List<String> words) {
        if (words == null || words.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(words.get(i));
        }
        return sb.toString();
    }

    private void completeDay() {
        if (quizQuestions.isEmpty()) {
            quizScorePercent = 80;
        }
        if (speakingScore == 0) {
            speakingScore = 70;
        }
        currentSectionIndex = DaySection.COMPLETED;
        repository.saveDaySection(level, dayIndex, currentSectionIndex);
        repository.completeLearningDay(level, dayIndex, quizScorePercent, speakingScore);
        Toast.makeText(this, "Chúc mừng! Bạn đã hoàn thành " + (currentDay != null ? currentDay.getTitle() : "bài học"), Toast.LENGTH_LONG).show();
        finish();
    }

    private void speak(String text) {
        if (ttsManager != null) {
            ttsManager.speakEnglishUs(text);
        }
    }

    private String formatWordList(String csv) {
        if (csv == null) return "";
        String[] words = csv.split(",");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append("• ").append(words[i].trim());
            if (i < words.length - 1) sb.append("\n");
        }
        return sb.toString();
    }

    private String formatPipeLines(String multiline) {
        if (multiline == null) return "";
        StringBuilder sb = new StringBuilder();
        String[] lines = multiline.split("\n");
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length >= 2) {
                sb.append(parts[0].trim()).append("\n  → ").append(parts[1].trim()).append("\n\n");
            } else {
                sb.append(line.trim()).append("\n");
            }
        }
        return sb.toString().trim();
    }

    private String firstLine(String multiline) {
        if (multiline == null || multiline.trim().isEmpty()) return "";
        return multiline.split("\n")[0].trim();
    }

    private String firstEnglishFromPipe(String multiline) {
        String line = firstLine(multiline);
        if (line.contains("|")) {
            return line.split("\\|")[0].trim();
        }
        return line;
    }

    private String firstEnglishFromText(String text) {
        if (text == null) return "";
        int dot = text.indexOf('.');
        if (dot > 0) {
            return text.substring(0, dot + 1);
        }
        return text.split("\n")[0];
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SpeechRecognitionManager.RECORD_AUDIO_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Quyền ghi âm đã được cấp. Hãy nhấn lại Micro.", Toast.LENGTH_SHORT).show();
            } else {
                if (!androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.RECORD_AUDIO)) {
                    hung.edu.apptienganh.utils.PermissionHelper.showSettingsDialog(this, "Cấp quyền ghi âm", "Bạn đã từ chối cấp quyền trước đó. Hãy mở Cài đặt và cấp quyền Micro để sử dụng tính năng luyện nói.", null);
                } else {
                    Toast.makeText(this, "Bạn cần cấp quyền ghi âm để luyện nói.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        repository.saveDaySection(level, dayIndex, currentSectionIndex);
        if (ttsManager != null) ttsManager.stop();
        if (speechManager != null && speechManager.isListening()) {
            speechManager.stopListening();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (scrollSaveRunnable != null) {
            scrollHandler.removeCallbacks(scrollSaveRunnable);
        }
        if (ttsManager != null) ttsManager.shutdown();
        if (speechManager != null) speechManager.destroy();
    }
}
