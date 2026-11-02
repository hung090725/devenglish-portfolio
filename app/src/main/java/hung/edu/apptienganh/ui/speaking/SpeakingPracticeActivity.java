package hung.edu.apptienganh.ui.speaking;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.entity.SpeakingResult;
import hung.edu.apptienganh.data.local.entity.UserProfile;
import hung.edu.apptienganh.data.repository.AppRepository;
import hung.edu.apptienganh.domain.scorer.SpeakingScorer;
import hung.edu.apptienganh.speech.SpeechRecognitionManager;
import hung.edu.apptienganh.speech.TtsManager;

public class SpeakingPracticeActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView txtPromptTitle, txtTranslationPrompt, txtTargetSentence;
    private Button btnSpeakTarget;

    // Result container
    private LinearLayout layoutResult;
    private TextView txtScorePercent, txtUserSpeak, txtCorrect, txtMissing, txtWrong;

    // Mic action
    private TextView txtStatus;
    private FrameLayout btnRecord;
    private ImageView imgRecordIcon;

    private String targetSentence = "My name is Hung.";
    private String promptTitle = "Luyện Nói Phát Âm";
    private String translationPrompt = "";

    private AppRepository repository;
    private TtsManager ttsManager;
    private SpeechRecognitionManager speechManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaking_practice);

        btnBack = findViewById(R.id.btn_back);
        txtPromptTitle = findViewById(R.id.txt_prompt_title);
        txtTranslationPrompt = findViewById(R.id.txt_translation_prompt);
        txtTargetSentence = findViewById(R.id.txt_target_sentence);
        btnSpeakTarget = findViewById(R.id.btn_speak_target);

        layoutResult = findViewById(R.id.layout_result);
        txtScorePercent = findViewById(R.id.txt_score_percent);
        txtUserSpeak = findViewById(R.id.txt_user_speak);
        txtCorrect = findViewById(R.id.txt_correct_words);
        txtMissing = findViewById(R.id.txt_missing_words);
        txtWrong = findViewById(R.id.txt_wrong_words);

        txtStatus = findViewById(R.id.txt_status);
        btnRecord = findViewById(R.id.btn_record);
        imgRecordIcon = findViewById(R.id.img_record_icon);

        repository = new AppRepository(this);
        ttsManager = new TtsManager(this);

        // Get intents
        Intent intent = getIntent();
        if (intent.hasExtra("target_sentence")) {
            String extraTarget = intent.getStringExtra("target_sentence");
            if (extraTarget != null && !extraTarget.trim().isEmpty()) {
                targetSentence = extraTarget;
            }
        }
        if (intent.hasExtra("prompt_title")) {
            promptTitle = intent.getStringExtra("prompt_title");
        }
        if (intent.hasExtra("translation_prompt")) {
            translationPrompt = intent.getStringExtra("translation_prompt");
        }

        // Bind data
        txtPromptTitle.setText(promptTitle);
        txtTargetSentence.setText(targetSentence);
        if (translationPrompt != null && !translationPrompt.isEmpty()) {
            txtTranslationPrompt.setText(translationPrompt);
            txtTranslationPrompt.setVisibility(View.VISIBLE);
        } else {
            txtTranslationPrompt.setVisibility(View.GONE);
        }

        btnBack.setOnClickListener(v -> finish());
        setupClickListeners();
        setupSpeechRecognizer();
    }

    private void setupClickListeners() {
        btnSpeakTarget.setOnClickListener(v -> {
            if (ttsManager != null) {
                ttsManager.speakEnglishUs(targetSentence);
            }
        });

        btnRecord.setOnClickListener(v -> {
            if (speechManager == null) return;

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
                                layoutResult.setVisibility(View.GONE);
                                speechManager.startListening();
                            }
                            @Override
                            public void onDenied() {
                                Toast.makeText(SpeakingPracticeActivity.this, "Không thể luyện nói nếu thiếu quyền Micro.", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                return;
            }

            if (speechManager.isListening()) {
                speechManager.stopListening();
            } else {
                layoutResult.setVisibility(View.GONE);
                speechManager.startListening();
            }
        });
    }

    private void setupSpeechRecognizer() {
        speechManager = new SpeechRecognitionManager(this, new SpeechRecognitionManager.SpeechRecognitionCallback() {
            @Override
            public void onReadyForSpeech() {
                txtStatus.setText("Đang lắng nghe... Nói đi!");
                imgRecordIcon.setImageResource(android.R.drawable.ic_btn_speak_now);
                btnRecord.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#EF4444")));
            }

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {
                txtStatus.setText("Nhấn nút Micro để bắt đầu nói");
                imgRecordIcon.setImageResource(R.drawable.ic_mic);
                btnRecord.setBackgroundTintList(null);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(SpeakingPracticeActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                txtStatus.setText("Nhấn nút Micro để bắt đầu nói");
                imgRecordIcon.setImageResource(R.drawable.ic_mic);
                btnRecord.setBackgroundTintList(null);
            }

            @Override
            public void onResults(String recognizedText) {
                txtUserSpeak.setText("Bạn nói: \"" + recognizedText + "\"");

                // Tính điểm phát âm câu mẫu
                SpeakingScorer.SpeakingScoreResult result = SpeakingScorer.calculateScore(targetSentence, recognizedText);
                
                txtScorePercent.setText("Điểm số: " + result.getScore() + "%");
                
                // Đổi màu điểm số
                if (result.getScore() >= 80) txtScorePercent.setTextColor(Color.parseColor("#10B981")); // Success Green
                else if (result.getScore() >= 50) txtScorePercent.setTextColor(Color.parseColor("#F59E0B")); // Warning Yellow
                else txtScorePercent.setTextColor(Color.parseColor("#EF4444")); // Error Red

                // Hiển thị danh sách từ phân loại
                txtCorrect.setText("Từ đúng: " + listToString(result.getCorrectWords()));
                txtMissing.setText("Từ thiếu: " + listToString(result.getMissingWords()));
                txtWrong.setText("Từ sai/dư: " + listToString(result.getWrongWords()));
                if (result.getFeedbackVi() != null && !result.getFeedbackVi().isEmpty()) {
                    txtStatus.setText(result.getFeedbackVi());
                }

                layoutResult.setVisibility(View.VISIBLE);

                SpeakingResult dbResult = new SpeakingResult();
                dbResult.setUserId(hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID);
                dbResult.setLearningDayId(0);
                dbResult.setTargetSentence(targetSentence);
                dbResult.setRecognizedText(recognizedText == null ? "" : recognizedText);
                dbResult.setScore(result.getScore());
                dbResult.setCorrectWords(listToString(result.getCorrectWords()));
                dbResult.setMissingWords(listToString(result.getMissingWords()));
                dbResult.setExtraWords(listToString(result.getExtraWords()));
                dbResult.setWrongWords(listToString(result.getWrongWords()));
                dbResult.setFeedbackVi(result.getFeedbackVi());
                dbResult.setCreatedAt(System.currentTimeMillis());
                repository.insertSpeakingResult(dbResult);

                // Cập nhật streak của người học
                updateUserStreak();
            }

            @Override
            public void onPartialResults(String partialText) {}
        });
    }

    private void updateUserStreak() {
        AppRepository repo = new AppRepository(this);
        repo.getUserProfile().observe(this, profile -> {
            if (profile != null) {
                // Tăng streak nhẹ nhàng làm khích lệ (nếu streak bằng 0 thì set = 1)
                if (profile.getStreak() == 0) {
                    profile.setStreak(1);
                    repo.updateUserProfile(profile);
                }
            }
        });
    }

    private String listToString(List<String> list) {
        if (list == null || list.isEmpty()) return "--";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SpeechRecognitionManager.RECORD_AUDIO_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Quyền ghi âm đã được cấp. Hãy nhấn lại Micro để bắt đầu luyện nói.", Toast.LENGTH_SHORT).show();
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
    protected void onPause() {
        super.onPause();
        if (ttsManager != null) ttsManager.stop();
        if (speechManager != null && speechManager.isListening()) {
            speechManager.stopListening();
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
