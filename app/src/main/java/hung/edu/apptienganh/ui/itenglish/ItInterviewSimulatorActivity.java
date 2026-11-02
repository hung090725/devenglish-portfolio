package hung.edu.apptienganh.ui.itenglish;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.ui.itenglish.engine.ItEnglishEngine;
import hung.edu.apptienganh.ui.itenglish.model.InterviewQuestion;

/**
 * Priority 3 Feature: IT English Mock Interview Simulator.
 * Simulates real-world hiring rounds with AI interviewer, Speech recognition,
 * rubric scoring breakdown, and high-band sample answers.
 */
public class ItInterviewSimulatorActivity extends AppCompatActivity {

    private static final int REQUEST_MIC_CODE = 4002;

    private TextView tvRoleBadge, tvRoundBadge, tvQuestionEn, tvQuestionVi;
    private Button btnSpeakQuestion, btnNextQuestion, btnSubmitAnswer;
    private EditText etCandidateAnswer;
    private ImageButton btnMicInterview, btnSpeakSample;

    private CardView cardInterviewReport;
    private TextView tvOverallScore, tvScoreTech, tvScoreVocab, tvScoreFluency;
    private TextView tvInterviewFeedback, tvSampleAnswer, tvInterviewTips;

    private List<InterviewQuestion> questions;
    private int currentQuestionIndex = 0;
    private InterviewQuestion currentQuestion;

    private TextToSpeech tts;
    private SpeechRecognizer speechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it_interview_simulator);

        initViews();
        setupTts();
        setupSpeechRecognizer();

        questions = ItEnglishEngine.getInstance().getAllInterviewQuestions();
        if (!questions.isEmpty()) {
            displayQuestion(questions.get(0));
        }

        setupListeners();
    }

    private void initViews() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        tvRoleBadge = findViewById(R.id.tvRoleBadge);
        tvRoundBadge = findViewById(R.id.tvRoundBadge);
        tvQuestionEn = findViewById(R.id.tvQuestionEn);
        tvQuestionVi = findViewById(R.id.tvQuestionVi);

        btnSpeakQuestion = findViewById(R.id.btnSpeakQuestion);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);
        btnSubmitAnswer = findViewById(R.id.btnSubmitAnswer);

        etCandidateAnswer = findViewById(R.id.etCandidateAnswer);
        btnMicInterview = findViewById(R.id.btnMicInterview);
        btnSpeakSample = findViewById(R.id.btnSpeakSample);

        cardInterviewReport = findViewById(R.id.cardInterviewReport);
        tvOverallScore = findViewById(R.id.tvOverallScore);
        tvScoreTech = findViewById(R.id.tvScoreTech);
        tvScoreVocab = findViewById(R.id.tvScoreVocab);
        tvScoreFluency = findViewById(R.id.tvScoreFluency);
        tvInterviewFeedback = findViewById(R.id.tvInterviewFeedback);
        tvSampleAnswer = findViewById(R.id.tvSampleAnswer);
        tvInterviewTips = findViewById(R.id.tvInterviewTips);
    }

    private void setupTts() {
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
            }
        });
    }

    private void setupSpeechRecognizer() {
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {
                    Toast.makeText(ItInterviewSimulatorActivity.this, "🎙️ Hãy trả lời phỏng vấn bằng tiếng Anh...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onBeginningOfSpeech() {}
                @Override
                public void onRmsChanged(float rmsdB) {}
                @Override
                public void onBufferReceived(byte[] buffer) {}
                @Override
                public void onEndOfSpeech() {}

                @Override
                public void onError(int error) {
                    Toast.makeText(ItInterviewSimulatorActivity.this, "Chưa nghe rõ câu trả lời, bạn có thể gõ trực tiếp.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResults(Bundle results) {
                    ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (matches != null && !matches.isEmpty()) {
                        String recognized = matches.get(0);
                        String current = etCandidateAnswer.getText().toString();
                        if (!current.trim().isEmpty()) {
                            etCandidateAnswer.setText(current + " " + recognized);
                        } else {
                            etCandidateAnswer.setText(recognized);
                        }
                    }
                }

                @Override
                public void onPartialResults(Bundle partialResults) {}
                @Override
                public void onEvent(int eventType, Bundle params) {}
            });
        }
    }

    private void setupListeners() {
        btnNextQuestion.setOnClickListener(v -> {
            if (questions.isEmpty()) return;
            currentQuestionIndex = (currentQuestionIndex + 1) % questions.size();
            displayQuestion(questions.get(currentQuestionIndex));
        });

        btnSpeakQuestion.setOnClickListener(v -> {
            if (tts != null && currentQuestion != null) {
                tts.speak(currentQuestion.getQuestionEn(), TextToSpeech.QUEUE_FLUSH, null, "QUESTION_TTS");
            }
        });

        btnMicInterview.setOnClickListener(v -> startVoiceRecognition());

        btnSubmitAnswer.setOnClickListener(v -> {
            if (currentQuestion == null) return;
            String answer = etCandidateAnswer.getText().toString();
            if (answer.trim().isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập hoặc nói câu trả lời của bạn.", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> eval = ItEnglishEngine.getInstance().evaluateInterviewResponse(currentQuestion, answer);
            displayEvaluationReport(eval);
        });

        btnSpeakSample.setOnClickListener(v -> {
            if (tts != null && currentQuestion != null) {
                tts.speak(currentQuestion.getSampleAnswerEn(), TextToSpeech.QUEUE_FLUSH, null, "SAMPLE_TTS");
            }
        });
    }

    private void displayQuestion(InterviewQuestion question) {
        this.currentQuestion = question;
        tvRoleBadge.setText(question.getRole());
        tvRoundBadge.setText(question.getRound());
        tvQuestionEn.setText(question.getQuestionEn());
        tvQuestionVi.setText(question.getQuestionVi());

        etCandidateAnswer.setText("");
        cardInterviewReport.setVisibility(View.GONE);
    }

    private void displayEvaluationReport(Map<String, Object> eval) {
        cardInterviewReport.setVisibility(View.VISIBLE);

        int overall = (int) eval.get("overallScore");
        int tech = (int) eval.get("technicalScore");
        int vocab = (int) eval.get("vocabScore");
        int fluency = (int) eval.get("fluencyScore");

        tvOverallScore.setText(overall + "%");
        tvScoreTech.setText(tech + "%");
        tvScoreVocab.setText(vocab + "%");
        tvScoreFluency.setText(fluency + "%");

        tvInterviewFeedback.setText((String) eval.get("feedbackVi"));
        tvSampleAnswer.setText((String) eval.get("sampleAnswerEn"));
        tvInterviewTips.setText(currentQuestion.getInterviewTipsVi());
    }

    private void startVoiceRecognition() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_MIC_CODE);
            return;
        }

        if (speechRecognizer != null) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
            speechRecognizer.startListening(intent);
        } else {
            Toast.makeText(this, "Nhận diện giọng nói không khả dụng.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_MIC_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startVoiceRecognition();
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
        super.onDestroy();
    }
}
