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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.ui.itenglish.engine.ItEnglishEngine;
import hung.edu.apptienganh.ui.itenglish.model.CodeSnippet;
import hung.edu.apptienganh.ui.itenglish.model.ItEvaluationResult;

/**
 * Priority 1 Feature: Code-to-English AI Lab.
 * Enables developers to explain code in English with independent Technical & English scores.
 */
public class CodeToEnglishActivity extends AppCompatActivity {

    private static final int REQUEST_MIC_CODE = 4001;

    private TextView tvSnippetTitle, tvLanguageBadge, tvSnippetLevel, tvCodeContent;
    private EditText etUserExplanation;
    private ImageButton btnMicInput, btnSpeakIdeal;
    private Button btnEvaluate, btnHint, btnNextSnippet;

    private CardView cardResult;
    private TextView tvTechScore, tvEnglishScore, tvTechFeedback, tvEnglishFeedback;
    private TextView tvIdealExpression, tvKeyTermsStatus;

    private List<CodeSnippet> snippets;
    private int currentIndex = 0;
    private CodeSnippet currentSnippet;

    private TextToSpeech tts;
    private SpeechRecognizer speechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_to_english);

        initViews();
        setupTts();
        setupSpeechRecognizer();

        snippets = ItEnglishEngine.getInstance().getAllCodeSnippets();
        if (!snippets.isEmpty()) {
            displaySnippet(snippets.get(0));
        }

        setupListeners();
    }

    private void initViews() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        tvSnippetTitle = findViewById(R.id.tvSnippetTitle);
        tvLanguageBadge = findViewById(R.id.tvLanguageBadge);
        tvSnippetLevel = findViewById(R.id.tvSnippetLevel);
        tvCodeContent = findViewById(R.id.tvCodeContent);

        etUserExplanation = findViewById(R.id.etUserExplanation);
        btnMicInput = findViewById(R.id.btnMicInput);
        btnSpeakIdeal = findViewById(R.id.btnSpeakIdeal);

        btnEvaluate = findViewById(R.id.btnEvaluate);
        btnHint = findViewById(R.id.btnHint);
        btnNextSnippet = findViewById(R.id.btnNextSnippet);

        cardResult = findViewById(R.id.cardResult);
        tvTechScore = findViewById(R.id.tvTechScore);
        tvEnglishScore = findViewById(R.id.tvEnglishScore);
        tvTechFeedback = findViewById(R.id.tvTechFeedback);
        tvEnglishFeedback = findViewById(R.id.tvEnglishFeedback);
        tvIdealExpression = findViewById(R.id.tvIdealExpression);
        tvKeyTermsStatus = findViewById(R.id.tvKeyTermsStatus);
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
                    Toast.makeText(CodeToEnglishActivity.this, "🎙️ Đang lắng nghe... Hãy nói lời giải thích bằng tiếng Anh!", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(CodeToEnglishActivity.this, "Không nhận diện được giọng nói. Bạn có thể gõ trực tiếp.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResults(Bundle results) {
                    ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (matches != null && !matches.isEmpty()) {
                        String recognized = matches.get(0);
                        String current = etUserExplanation.getText().toString();
                        if (!current.trim().isEmpty()) {
                            etUserExplanation.setText(current + " " + recognized);
                        } else {
                            etUserExplanation.setText(recognized);
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
        btnNextSnippet.setOnClickListener(v -> {
            if (snippets.isEmpty()) return;
            currentIndex = (currentIndex + 1) % snippets.size();
            displaySnippet(snippets.get(currentIndex));
        });

        btnMicInput.setOnClickListener(v -> startVoiceRecognition());

        btnHint.setOnClickListener(v -> {
            if (currentSnippet == null) return;
            new AlertDialog.Builder(this)
                    .setTitle("💡 Gợi Ý Kỹ Thuật (Hint)")
                    .setMessage("Ý nghĩa: " + currentSnippet.getExplanationVi() + "\n\n"
                            + "Từ khóa nên có: " + String.join(", ", currentSnippet.getRequiredKeyTerms()))
                    .setPositiveButton("Đã hiểu", null)
                    .show();
        });

        btnEvaluate.setOnClickListener(v -> {
            if (currentSnippet == null) return;
            String text = etUserExplanation.getText().toString();
            if (text.trim().isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập lời giải thích bằng tiếng Anh.", Toast.LENGTH_SHORT).show();
                return;
            }

            ItEvaluationResult eval = ItEnglishEngine.getInstance().evaluateCodeExplanation(currentSnippet, text);
            displayEvaluationResult(eval);
        });

        btnSpeakIdeal.setOnClickListener(v -> {
            if (tts != null && currentSnippet != null) {
                tts.speak(currentSnippet.getIdealExplanationEn(), TextToSpeech.QUEUE_FLUSH, null, "IDEAL_TTS");
            }
        });
    }

    private void displaySnippet(CodeSnippet snippet) {
        this.currentSnippet = snippet;
        tvSnippetTitle.setText(snippet.getTitle());
        tvLanguageBadge.setText(snippet.getLanguage());
        tvSnippetLevel.setText("Snippet • " + snippet.getLevel());
        tvCodeContent.setText(snippet.getCode());
        etUserExplanation.setText("");
        cardResult.setVisibility(View.GONE);
    }

    private void displayEvaluationResult(ItEvaluationResult result) {
        cardResult.setVisibility(View.VISIBLE);
        tvTechScore.setText(result.getTechnicalScore() + "%");
        tvEnglishScore.setText(result.getEnglishScore() + "%");
        tvTechFeedback.setText(result.getTechnicalFeedbackVi());
        tvEnglishFeedback.setText(result.getEnglishFeedbackVi());
        tvIdealExpression.setText(result.getImprovedExpressionEn());

        StringBuilder terms = new StringBuilder();
        if (!result.getMatchedTerms().isEmpty()) {
            terms.append("✅ Thuật ngữ đã dùng: ").append(String.join(", ", result.getMatchedTerms()));
        }
        if (!result.getMissingTerms().isEmpty()) {
            if (terms.length() > 0) terms.append("\n");
            terms.append("⚠️ Nên bổ sung thêm: ").append(String.join(", ", result.getMissingTerms()));
        }
        tvKeyTermsStatus.setText(terms.toString());
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
            Toast.makeText(this, "Nhận diện giọng nói không khả dụng trên máy này.", Toast.LENGTH_SHORT).show();
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
