package hung.edu.apptienganh.ui.vocabulary;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.entity.Vocabulary;
import hung.edu.apptienganh.data.repository.AppRepository;
import hung.edu.apptienganh.domain.scorer.SpeakingScorer;
import hung.edu.apptienganh.speech.SpeechRecognitionManager;
import hung.edu.apptienganh.speech.TtsManager;

public class VocabularyDetailActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView txtWord, txtType, txtIpaUk, txtIpaUs, txtMeaning, txtContext;
    private TextView txtExampleEn, txtExampleVi, txtCollocations, txtMistake;

    // Pronunciation Views
    private LinearLayout btnSpeakUk, btnSpeakUs;
    private FrameLayout btnMic;
    private ImageView imgMicIcon;
    private TextView txtPracticeStatus, txtScore, txtRecognized;

    private AppRepository repository;
    private TtsManager ttsManager;
    private SpeechRecognitionManager speechManager;
    private Vocabulary currentVocabulary;
    private String vocabWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_detail);

        btnBack = findViewById(R.id.btn_back);
        txtWord = findViewById(R.id.txt_word);
        txtType = findViewById(R.id.txt_type);
        txtIpaUk = findViewById(R.id.txt_ipa_uk);
        txtIpaUs = findViewById(R.id.txt_ipa_us);
        txtMeaning = findViewById(R.id.txt_meaning);
        txtContext = findViewById(R.id.txt_context);
        txtExampleEn = findViewById(R.id.txt_example_en);
        txtExampleVi = findViewById(R.id.txt_example_vi);
        txtCollocations = findViewById(R.id.txt_collocations);
        txtMistake = findViewById(R.id.txt_mistake);

        btnSpeakUk = findViewById(R.id.btn_speak_uk);
        btnSpeakUs = findViewById(R.id.btn_speak_us);
        btnMic = findViewById(R.id.btn_mic);
        imgMicIcon = findViewById(R.id.img_mic_icon);
        txtPracticeStatus = findViewById(R.id.txt_practice_status);
        txtScore = findViewById(R.id.txt_score);
        txtRecognized = findViewById(R.id.txt_recognized);

        repository = new AppRepository(this);
        ttsManager = new TtsManager(this);

        vocabWord = getIntent().getStringExtra("vocab_word");
        if (vocabWord == null || vocabWord.trim().isEmpty()) {
            Toast.makeText(this, "Lỗi: Không tìm thấy từ vựng.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnBack.setOnClickListener(v -> finish());
        setupSpeechRecognizer();
        setupClickListeners();
        loadVocabularyData();
    }

    private void loadVocabularyData() {
        // Tra cứu từ bằng Repository
        repository.searchVocabulary(vocabWord).observe(this, list -> {
            if (list != null && !list.isEmpty()) {
                // Lấy phần tử khớp chính xác
                for (Vocabulary vocab : list) {
                    if (vocab.getWord().equalsIgnoreCase(vocabWord)) {
                        currentVocabulary = vocab;
                        bindData(vocab);
                        break;
                    }
                }
                // Nếu không có khớp chính xác tuyệt đối, lấy phần tử đầu tiên
                if (currentVocabulary == null) {
                    currentVocabulary = list.get(0);
                    bindData(currentVocabulary);
                }
            }
        });
    }

    private void bindData(Vocabulary vocab) {
        txtWord.setText(vocab.getWord());
        txtType.setText(vocab.getType());
        txtIpaUk.setText("UK " + vocab.getIpaUk());
        txtIpaUs.setText("US " + vocab.getIpaUs());
        txtMeaning.setText("Nghĩa: " + vocab.getMeaningVi());
        txtContext.setText(vocab.getContextMeaningVi());
        txtExampleEn.setText(vocab.getExampleEn());
        txtExampleVi.setText(vocab.getExampleVi());
        txtCollocations.setText(vocab.getCollocations());
        txtMistake.setText(vocab.getCommonMistake());
    }

    private void setupClickListeners() {
        // Phát âm UK
        btnSpeakUk.setOnClickListener(v -> {
            if (ttsManager != null && currentVocabulary != null) {
                ttsManager.speakEnglishUk(currentVocabulary.getWord());
            }
        });

        // Phát âm US
        btnSpeakUs.setOnClickListener(v -> {
            if (ttsManager != null && currentVocabulary != null) {
                ttsManager.speakEnglishUs(currentVocabulary.getWord());
            }
        });

        // Luyện nói (click to start/stop)
        btnMic.setOnClickListener(v -> {
            if (speechManager == null) return;
            
            if (!SpeechRecognitionManager.checkPermission(this)) {
                SpeechRecognitionManager.requestPermission(this);
                return;
            }

            if (speechManager.isListening()) {
                speechManager.stopListening();
            } else {
                txtScore.setVisibility(View.GONE);
                txtRecognized.setVisibility(View.GONE);
                speechManager.startListening();
            }
        });
    }

    private void setupSpeechRecognizer() {
        speechManager = new SpeechRecognitionManager(this, new SpeechRecognitionManager.SpeechRecognitionCallback() {
            @Override
            public void onReadyForSpeech() {
                txtPracticeStatus.setText("Đang lắng nghe... Nói đi!");
                imgMicIcon.setImageResource(android.R.drawable.ic_btn_speak_now);
                btnMic.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#EF4444"))); // Red when recording
            }

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {
                txtPracticeStatus.setText("Nhấn Giữ Micro Để Luyện Phát Âm");
                imgMicIcon.setImageResource(R.drawable.ic_mic);
                btnMic.setBackgroundTintList(null); // Trở lại màu gradient
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(VocabularyDetailActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                txtPracticeStatus.setText("Nhấn Giữ Micro Để Luyện Phát Âm");
                imgMicIcon.setImageResource(R.drawable.ic_mic);
                btnMic.setBackgroundTintList(null);
            }

            @Override
            public void onResults(String recognizedText) {
                txtRecognized.setText("Bạn đã nói: \"" + recognizedText + "\"");
                txtRecognized.setVisibility(View.VISIBLE);

                // Chấm điểm phát âm từ vựng
                if (currentVocabulary != null) {
                    SpeakingScorer.SpeakingScoreResult result = SpeakingScorer.calculateScore(currentVocabulary.getWord(), recognizedText);
                    txtScore.setText("Điểm số: " + result.getScore() + "%");
                    txtScore.setVisibility(View.VISIBLE);

                    // Cập nhật độ thành thạo (mastery) từ vựng vào SQLite
                    repository.updateVocabularyMastery(currentVocabulary.getId(), result.getScore());
                }
            }

            @Override
            public void onPartialResults(String partialText) {}
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SpeechRecognitionManager.RECORD_AUDIO_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Quyền ghi âm đã được cấp. Hãy nhấn lại Micro để luyện nói.", Toast.LENGTH_SHORT).show();
            } else {
                if (!androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.RECORD_AUDIO)) {
                    new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Cần Quyền Ghi Âm")
                        .setMessage("Bạn đã từ chối quyền ghi âm vĩnh viễn. Vui lòng mở Cài đặt ứng dụng để cấp quyền này, nếu không tính năng luyện nói sẽ không hoạt động.")
                        .setPositiveButton("Mở Cài Đặt", (dialog, which) -> {
                            android.content.Intent intent = new android.content.Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            android.net.Uri uri = android.net.Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        })
                        .setNegativeButton("Đóng", null)
                        .show();
                } else {
                    Toast.makeText(this, "Bạn cần cấp quyền ghi âm để luyện nói.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ttsManager != null) {
            ttsManager.shutdown();
        }
        if (speechManager != null) {
            speechManager.destroy();
        }
    }
}
