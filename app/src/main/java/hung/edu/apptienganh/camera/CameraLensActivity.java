package hung.edu.apptienganh.camera;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.ai.AiCallback;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.entity.ErrorNote;

/**
 * World-Class AR Camera Lens Activity integrating 15 advanced educational features:
 * - 100% Offline real-time object & OCR detection
 * - Scavenger Hunt Quests & Pokédex Album
 * - Syllable Stress & CEFR C1 Academic Toggle
 * - AI Speech Evaluation & Pronunciation Scoring
 * - AR Pop-Quiz & Blind Active Recall Mode
 * - Idiom Spotlights, Word Family Orbits & Instant Dialogues
 * - Freeze-frame reading & One-Click Anki Export
 */
public class CameraLensActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_CODE = 3001;
    private static final int REQUEST_RECORD_AUDIO_CODE = 3002;

    private PreviewView previewView;
    private GraphicOverlay graphicOverlay;

    // Top Controls
    private ImageButton btnBack, btnFlash, btnSwitchCamera, btnSpeakWord, btnAutoSpeak, btnFreeze;
    private ImageButton btnQuest, btn30DayJourney, btnBlindMode, btnPokedex;
    private TextView tvLiveStatus, tvLiveArBadge;

    // Mode & Level Controls
    private Button btnModeObject, btnModeText, btnCefrC1;
    private Button btnZoom1x, btnZoom2x, btnZoom3x;

    // Action Buttons
    private Button btnPronounceTest, btnPopQuiz, btnDeepAiScan, btnShutterCapture;
    private Button btnSpeakSentence, btnSpeakMeaningVi, btnSaveWord, btnExportAnki, btnDeepKnowledge;

    private ProgressBar progressAiVision;
    private CardView bottomVocabCard;

    // Vocabulary Fields
    private TextView tvWordEn, tvCefrBadge, tvWordType, tvIpa, tvMeaningVi;
    private TextView tvCollocation, tvIdiom, tvWordFamily, tvDialogue;
    private TextView tvExampleEn, tvExampleVi, tvAiNotes;

    // Systems & Helpers
    private TextToSpeech tts;
    private ExecutorService cameraExecutor;
    private Camera camera;
    private ProcessCameraProvider cameraProvider;
    private CameraVisionAnalyzer analyzer;
    private ScaleGestureDetector scaleGestureDetector;

    private CameraQuestManager questManager;
    private SpeechEvaluationHelper speechEvaluationHelper;

    // States
    private int lensFacing = CameraSelector.LENS_FACING_BACK;
    private boolean isTorchOn = false;
    private boolean isAutoSpeakEnabled = true;
    private boolean isFrozen = false;
    private boolean isBlindMode = false;
    private boolean isAcademicC1Mode = false;

    private String lastSpokenWord = "";
    private long lastSpokenTimestamp = 0;
    private VisualWordInfo currentWordInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_lens);

        questManager = new CameraQuestManager(this);
        speechEvaluationHelper = new SpeechEvaluationHelper(this);

        initViews();
        setupTts();
        setupListeners();
        setupTouchAndGesture();
        updateQuestBannerHud();

        cameraExecutor = Executors.newSingleThreadExecutor();

        if (allPermissionsGranted()) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO},
                    REQUEST_CAMERA_CODE);
        }

        // Set initial sample state
        VisualWordInfo initial = VisualVocabularyDatabase.lookup("laptop", 0.95f);
        displayWordInfo(initial);
    }

    private void initViews() {
        previewView = findViewById(R.id.previewView);
        graphicOverlay = findViewById(R.id.graphicOverlay);

        btnBack = findViewById(R.id.btnBack);
        btnFlash = findViewById(R.id.btnFlash);
        btnSwitchCamera = findViewById(R.id.btnSwitchCamera);
        btnSpeakWord = findViewById(R.id.btnSpeakWord);
        btnAutoSpeak = findViewById(R.id.btnAutoSpeak);
        btnFreeze = findViewById(R.id.btnFreeze);

        btnQuest = findViewById(R.id.btnQuest);
        btn30DayJourney = findViewById(R.id.btn30DayJourney);
        btnBlindMode = findViewById(R.id.btnBlindMode);
        btnPokedex = findViewById(R.id.btnPokedex);

        tvLiveStatus = findViewById(R.id.tvLiveStatus);
        tvLiveArBadge = findViewById(R.id.tvLiveArBadge);

        btnModeObject = findViewById(R.id.btnModeObject);
        btnModeText = findViewById(R.id.btnModeText);
        btnCefrC1 = findViewById(R.id.btnCefrC1);

        btnZoom1x = findViewById(R.id.btnZoom1x);
        btnZoom2x = findViewById(R.id.btnZoom2x);
        btnZoom3x = findViewById(R.id.btnZoom3x);

        btnPronounceTest = findViewById(R.id.btnPronounceTest);
        btnPopQuiz = findViewById(R.id.btnPopQuiz);
        btnDeepAiScan = findViewById(R.id.btnDeepAiScan);
        btnShutterCapture = findViewById(R.id.btnShutterCapture);

        btnSpeakSentence = findViewById(R.id.btnSpeakSentence);
        btnSpeakMeaningVi = findViewById(R.id.btnSpeakMeaningVi);
        btnSaveWord = findViewById(R.id.btnSaveWord);
        btnExportAnki = findViewById(R.id.btnExportAnki);
        btnDeepKnowledge = findViewById(R.id.btnDeepKnowledge);

        progressAiVision = findViewById(R.id.progressAiVision);
        bottomVocabCard = findViewById(R.id.bottomVocabCard);

        tvWordEn = findViewById(R.id.tvWordEn);
        tvCefrBadge = findViewById(R.id.tvCefrBadge);
        tvWordType = findViewById(R.id.tvWordType);
        tvIpa = findViewById(R.id.tvIpa);
        tvMeaningVi = findViewById(R.id.tvMeaningVi);

        tvCollocation = findViewById(R.id.tvCollocation);
        tvIdiom = findViewById(R.id.tvIdiom);
        tvWordFamily = findViewById(R.id.tvWordFamily);
        tvDialogue = findViewById(R.id.tvDialogue);

        tvExampleEn = findViewById(R.id.tvExampleEn);
        tvExampleVi = findViewById(R.id.tvExampleVi);
        tvAiNotes = findViewById(R.id.tvAiNotes);
    }

    private void setupTts() {
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
            }
        });
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());
        btnFlash.setOnClickListener(v -> toggleTorch());

        btnSwitchCamera.setOnClickListener(v -> {
            lensFacing = (lensFacing == CameraSelector.LENS_FACING_BACK)
                    ? CameraSelector.LENS_FACING_FRONT
                    : CameraSelector.LENS_FACING_BACK;
            startCamera();
        });

        btnAutoSpeak.setOnClickListener(v -> {
            isAutoSpeakEnabled = !isAutoSpeakEnabled;
            if (isAutoSpeakEnabled) {
                btnAutoSpeak.setColorFilter(0xFF38BDF8);
                Toast.makeText(this, "🔊 Đã BẬT tự động phát âm khi thấy từ mới", Toast.LENGTH_SHORT).show();
            } else {
                btnAutoSpeak.setColorFilter(0xFF64748B);
                Toast.makeText(this, "🔇 Đã TẮT tự động phát âm", Toast.LENGTH_SHORT).show();
            }
        });

        btnFreeze.setOnClickListener(v -> toggleFreeze());

        // Quests, Blind Mode, and Pokédex
        btnQuest.setOnClickListener(v -> showQuestDialog());
        btn30DayJourney.setOnClickListener(v -> show30DayJourneyDialog());
        btnBlindMode.setOnClickListener(v -> toggleBlindMode());
        btnPokedex.setOnClickListener(v -> showPokedexDialog());

        // Mode and CEFR Level Switching
        btnModeObject.setOnClickListener(v -> switchMode(CameraVisionAnalyzer.MODE_OBJECT));
        btnModeText.setOnClickListener(v -> switchMode(CameraVisionAnalyzer.MODE_TEXT));
        btnCefrC1.setOnClickListener(v -> toggleCefrC1Mode());

        // Zoom Controls
        btnZoom1x.setOnClickListener(v -> setCameraZoom(1.0f));
        btnZoom2x.setOnClickListener(v -> setCameraZoom(2.0f));
        btnZoom3x.setOnClickListener(v -> setCameraZoom(3.0f));

        // Interactive Audio & Actions
        btnSpeakWord.setOnClickListener(v -> {
            if (currentWordInfo != null) {
                speakEnglish(currentWordInfo.getWordEn());
            }
        });

        btnSpeakSentence.setOnClickListener(v -> {
            if (currentWordInfo != null) {
                speakEnglish(currentWordInfo.getExampleSentenceEn());
            }
        });

        btnSpeakMeaningVi.setOnClickListener(v -> {
            if (currentWordInfo != null) {
                speakVietnamese(currentWordInfo.getMeaningVi() + ". " + currentWordInfo.getExampleSentenceVi());
            }
        });

        // Pronunciation Test & AR Pop-Quiz
        btnPronounceTest.setOnClickListener(v -> startPronunciationTest());
        btnPopQuiz.setOnClickListener(v -> showPopQuizChallenge());

        // Save & Export
        btnSaveWord.setOnClickListener(v -> saveWordToNotebook());
        btnExportAnki.setOnClickListener(v -> exportAnkiFlashcards());

        btnDeepAiScan.setOnClickListener(v -> performDeepAiScan());
        btnShutterCapture.setOnClickListener(v -> captureStudyCard());

        if (btnDeepKnowledge != null) {
            btnDeepKnowledge.setOnClickListener(v -> openDeepKnowledgeHub());
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupTouchAndGesture() {
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(@NonNull ScaleGestureDetector detector) {
                if (camera != null && camera.getCameraInfo().getZoomState().getValue() != null) {
                    float currentZoom = camera.getCameraInfo().getZoomState().getValue().getZoomRatio();
                    float delta = detector.getScaleFactor();
                    camera.getCameraControl().setZoomRatio(currentZoom * delta);
                }
                return true;
            }
        });

        previewView.setOnTouchListener((v, event) -> {
            scaleGestureDetector.onTouchEvent(event);

            if (event.getAction() == MotionEvent.ACTION_UP && !scaleGestureDetector.isInProgress()) {
                float x = event.getX();
                float y = event.getY();

                // If currently frozen, tapping resumes live camera
                if (isFrozen) {
                    toggleFreeze();
                    return true;
                }

                // If in blind mode, tapping reveals the word!
                if (isBlindMode && graphicOverlay != null) {
                    graphicOverlay.revealBlindTag();
                }

                if (graphicOverlay != null) {
                    graphicOverlay.triggerFocusAnimation(x, y);
                }

                if (camera != null) {
                    MeteringPointFactory factory = previewView.getMeteringPointFactory();
                    MeteringPoint point = factory.createPoint(x, y);
                    FocusMeteringAction action = new FocusMeteringAction.Builder(point).build();
                    camera.getCameraControl().startFocusAndMetering(action);
                }
                return true;
            }
            return true;
        });
    }

    private void updateQuestBannerHud() {
        if (graphicOverlay != null && questManager != null) {
            CameraQuestManager.Quest q = questManager.getCurrentQuest();
            if (q != null) {
                String banner = "🎯 " + q.titleVi + " (" + questManager.getQuestProgressCount() + "/" + q.requiredCount + ")";
                graphicOverlay.setActiveQuestBannerText(banner);
            }
        }
    }

    private void toggleBlindMode() {
        isBlindMode = !isBlindMode;
        if (graphicOverlay != null) {
            graphicOverlay.setBlindModeEnabled(isBlindMode);
        }

        if (isBlindMode) {
            btnBlindMode.setColorFilter(0xFFF59E0B);
            Toast.makeText(this, "🧠 Đã BẬT chế độ Đố che từ (Chạm màn hình để mở đáp án)!", Toast.LENGTH_SHORT).show();
        } else {
            btnBlindMode.setColorFilter(0xFF94A3B8);
            Toast.makeText(this, "👁️ Đã TẮT chế độ Đố che từ", Toast.LENGTH_SHORT).show();
        }
    }

    private void toggleCefrC1Mode() {
        isAcademicC1Mode = !isAcademicC1Mode;
        if (graphicOverlay != null) {
            graphicOverlay.setAcademicC1ModeEnabled(isAcademicC1Mode);
        }

        if (isAcademicC1Mode) {
            btnCefrC1.setBackgroundColor(0xFF8B5CF6);
            btnCefrC1.setTextColor(0xFFFFFFFF);
            Toast.makeText(this, "🎓 Đã BẬT Từ Vựng Nâng Cao C1 / IELTS", Toast.LENGTH_SHORT).show();
        } else {
            btnCefrC1.setBackgroundColor(0xFF334155);
            btnCefrC1.setTextColor(0xFFCBD5E1);
            Toast.makeText(this, "Cơ bản A1 - B2", Toast.LENGTH_SHORT).show();
        }

        if (currentWordInfo != null) {
            displayWordInfo(currentWordInfo);
        }
    }

    private void showQuestDialog() {
        CameraQuestManager.Quest q = questManager.getCurrentQuest();
        if (q == null) return;

        String status = questManager.isQuestCompleted()
                ? "🎉 ĐÃ HOÀN THÀNH (" + questManager.getQuestProgressCount() + "/" + q.requiredCount + ")"
                : "⚡ TIẾN ĐỘ: " + questManager.getQuestProgressCount() + "/" + q.requiredCount;

        new AlertDialog.Builder(this)
                .setTitle(q.titleVi)
                .setMessage(q.descriptionVi + "\n\n"
                        + status + "\n\n"
                        + "💡 Hướng camera vào các đồ vật quanh bạn để tự động nhận diện và hoàn thành nhiệm vụ!")
                .setPositiveButton("Nhiệm vụ tiếp theo ➔", (dialog, which) -> {
                    questManager.advanceToNextQuest();
                    updateQuestBannerHud();
                    Toast.makeText(this, "Đã chuyển sang nhiệm vụ mới!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Đóng", null)
                .show();
    }

    private void showPokedexDialog() {
        Set<String> words = questManager.getTotalDiscoveredWords();
        int total = questManager.getTotalDiscoveredCount();

        StringBuilder sb = new StringBuilder();
        sb.append("🏆 Đã mở khóa: ").append(total).append(" từ vựng thực tế trong đời sống!\n\n");

        int count = 1;
        for (String w : words) {
            VisualWordInfo info = VisualVocabularyDatabase.lookup(w, 1.0f);
            if (info != null) {
                sb.append(count++).append(". ").append(info.getWordEn())
                        .append(" ").append(info.getIpa() != null ? info.getIpa() : "")
                        .append(" • ").append(info.getMeaningVi()).append("\n");
            }
            if (count > 25) {
                sb.append("... và ").append(total - 25).append(" từ vựng khác.\n");
                break;
            }
        }

        new AlertDialog.Builder(this)
                .setTitle("📚 Kho Từ Đã Săn (AR Pokédex)")
                .setMessage(sb.toString())
                .setPositiveButton("📤 Xuất Thẻ Anki / Quizlet", (dialog, which) -> exportAnkiFlashcards())
                .setNegativeButton("Đóng", null)
                .show();
    }

    private void showPopQuizChallenge() {
        if (currentWordInfo == null) {
            Toast.makeText(this, "Hãy hướng camera vào đồ vật để tạo câu đố!", Toast.LENGTH_SHORT).show();
            return;
        }

        String question = currentWordInfo.getQuizQuestionEn() != null
                ? currentWordInfo.getQuizQuestionEn()
                : "Choose the correct English word for '" + currentWordInfo.getMeaningVi() + "':";

        List<String> options = (currentWordInfo.getQuizOptions() != null && !currentWordInfo.getQuizOptions().isEmpty())
                ? currentWordInfo.getQuizOptions()
                : java.util.Arrays.asList(currentWordInfo.getWordEn(), "Mountain", "Airplane");

        int correctIndex = currentWordInfo.getQuizAnswerIndex();

        CharSequence[] items = options.toArray(new CharSequence[0]);

        new AlertDialog.Builder(this)
                .setTitle("🧠 AR Pop-Quiz: Thử Thách 3 Giây")
                .setSingleChoiceItems(items, -1, (dialog, which) -> {
                    dialog.dismiss();
                    if (which == correctIndex) {
                        triggerHapticFeedback();
                        Toast.makeText(this, "🎉 CHÍNH XÁC! +50 EXP\nBạn đã ghi nhớ xuất sắc từ vựng này!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Chưa đúng rồi! Đáp án đúng là: " + options.get(correctIndex), Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void startPronunciationTest() {
        if (currentWordInfo == null) {
            Toast.makeText(this, "Chưa nhận diện được từ vựng nào.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_CODE);
            return;
        }

        String targetWord = currentWordInfo.getWordEn();

        speechEvaluationHelper.startListening(targetWord, new SpeechEvaluationHelper.EvaluationCallback() {
            @Override
            public void onListeningStarted() {
                Toast.makeText(CameraLensActivity.this, "🎙️ ĐANG LẮNG NGHE... Hãy phát âm từ: " + targetWord, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onEvaluationResult(String spokenText, int scorePercent, String feedbackVi, boolean isExcellent) {
                triggerHapticFeedback();

                new AlertDialog.Builder(CameraLensActivity.this)
                        .setTitle(isExcellent ? "🌟 Phát Âm Chuẩn Xác (" + scorePercent + "%)" : "🎙️ Kết Quả Phát Âm (" + scorePercent + "%)")
                        .setMessage("Từ chuẩn: " + targetWord + " " + (currentWordInfo.getIpa() != null ? currentWordInfo.getIpa() : "") + "\n"
                                + "Bạn vừa nói: \"" + spokenText + "\"\n\n"
                                + "Nhận xét AI: " + feedbackVi)
                        .setPositiveButton("Luyện lại", (d, w) -> startPronunciationTest())
                        .setNeutralButton("Nghe mẫu", (d, w) -> speakEnglish(targetWord))
                        .setNegativeButton("Đóng", null)
                        .show();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(CameraLensActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDeepKnowledgeHub() {
        if (currentWordInfo == null) {
            Toast.makeText(this, "Hãy hướng ống kính vào một đồ vật để khám phá bách khoa học sâu!", Toast.LENGTH_SHORT).show();
            return;
        }

        DeepKnowledgeExplorerDialog dialog = new DeepKnowledgeExplorerDialog(this, currentWordInfo, new DeepKnowledgeExplorerDialog.DialogListener() {
            @Override
            public void onSpeakRequested(String text, boolean isUkAccent) {
                if (tts != null && text != null && !text.isEmpty()) {
                    tts.setLanguage(isUkAccent ? Locale.UK : Locale.US);
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, isUkAccent ? "CAMERA_TTS_UK" : "CAMERA_TTS_US");
                }
            }

            @Override
            public void onSaveRequested(VisualWordInfo info) {
                saveWordToNotebook();
            }

            @Override
            public void onShadowingRequested(String sentence) {
                startShadowingPractice(sentence);
            }
        });
        dialog.show();
    }

    private void show30DayJourneyDialog() {
        if (questManager == null) return;
        new AlertDialog.Builder(this)
                .setTitle("🗓️ Thử Thách 30 Ngày Ngôi Nhà Song Ngữ")
                .setMessage(questManager.get30DayJourneySummary())
                .setPositiveButton("Bắt đầu săn đồ vật hôm nay", (dialog, which) -> {
                    Toast.makeText(this, "🎯 Hãy quét các vật thể trong nhà để hoàn thành mục tiêu ngày " + questManager.getJourneyDay() + "!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Đóng", null)
                .show();
    }

    private void startShadowingPractice(String targetSentence) {
        if (targetSentence == null || targetSentence.trim().isEmpty()) {
            Toast.makeText(this, "Chưa có câu mẫu để luyện Shadowing.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_CODE);
            return;
        }

        speechEvaluationHelper.startListening(targetSentence, new SpeechEvaluationHelper.EvaluationCallback() {
            @Override
            public void onListeningStarted() {
                Toast.makeText(CameraLensActivity.this, "🗣️ CHẾ ĐỘ SHADOWING: Hãy đọc to theo ngữ điệu câu mẫu!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onEvaluationResult(String spokenText, int scorePercent, String feedbackVi, boolean isExcellent) {
                triggerHapticFeedback();

                new AlertDialog.Builder(CameraLensActivity.this)
                        .setTitle(isExcellent ? "🌟 Ngữ Điệu Xuất Sắc (" + scorePercent + "%)" : "🗣️ Kết Quả Luyện Shadowing (" + scorePercent + "%)")
                        .setMessage("Câu mẫu chuẩn:\n\"" + targetSentence + "\"\n\n"
                                + "Bạn vừa đọc:\n\"" + spokenText + "\"\n\n"
                                + "Đánh giá ngữ điệu & độ lưu loát: " + feedbackVi)
                        .setPositiveButton("Luyện lại", (d, w) -> startShadowingPractice(targetSentence))
                        .setNeutralButton("Nghe mẫu chuẩn", (d, w) -> speakEnglish(targetSentence))
                        .setNegativeButton("Hoàn tất", null)
                        .show();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(CameraLensActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void exportAnkiFlashcards() {
        String tsvContent = questManager.exportAnkiFlashcardsTsv();

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, tsvContent);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "EnglishPath_AR_Flashcards.tsv");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "Xuất thẻ sang Anki / Drive / Email");
        startActivity(shareIntent);
    }

    private void setCameraZoom(float zoomRatio) {
        if (camera != null) {
            camera.getCameraControl().setZoomRatio(zoomRatio);

            btnZoom1x.setBackgroundColor(zoomRatio == 1.0f ? 0xFF2563EB : 0xFF334155);
            btnZoom1x.setTextColor(zoomRatio == 1.0f ? 0xFFFFFFFF : 0xFFCBD5E1);

            btnZoom2x.setBackgroundColor(zoomRatio == 2.0f ? 0xFF2563EB : 0xFF334155);
            btnZoom2x.setTextColor(zoomRatio == 2.0f ? 0xFFFFFFFF : 0xFFCBD5E1);

            btnZoom3x.setBackgroundColor(zoomRatio == 3.0f ? 0xFF2563EB : 0xFF334155);
            btnZoom3x.setTextColor(zoomRatio == 3.0f ? 0xFFFFFFFF : 0xFFCBD5E1);
        }
    }

    private void toggleFreeze() {
        isFrozen = !isFrozen;
        if (analyzer != null) {
            analyzer.setFrozen(isFrozen);
        }
        if (graphicOverlay != null) {
            graphicOverlay.setFrozen(isFrozen);
        }

        if (isFrozen) {
            btnFreeze.setColorFilter(0xFFEA580C);
            btnFreeze.setImageResource(android.R.drawable.ic_media_play);
            tvLiveStatus.setText("⏸️ ĐÃ GIỮ HÌNH ĐỂ ĐỌC (OFFLINE)");
            tvLiveStatus.setTextColor(0xFFF97316);
            Toast.makeText(this, "⏸️ Đã giữ hình. Bạn có thể thoải mái đọc từ vựng và câu ví dụ mà không lo rung tay!", Toast.LENGTH_SHORT).show();
        } else {
            btnFreeze.setColorFilter(0xFFFCD34D);
            btnFreeze.setImageResource(android.R.drawable.ic_media_pause);
            tvLiveStatus.setText("⚡ 100% OFFLINE • AR ACTIVE");
            tvLiveStatus.setTextColor(0xFF34D399);
            Toast.makeText(this, "▶️ Tiếp tục quét trực tiếp", Toast.LENGTH_SHORT).show();
        }
    }

    private void switchMode(int mode) {
        if (analyzer != null) {
            analyzer.setMode(mode);
        }

        if (mode == CameraVisionAnalyzer.MODE_OBJECT) {
            btnModeObject.setBackgroundColor(0xFF2563EB);
            btnModeObject.setTextColor(0xFFFFFFFF);
            btnModeText.setBackgroundColor(0xFF334155);
            btnModeText.setTextColor(0xFF94A3B8);
            tvLiveStatus.setText("⚡ OFFLINE • NHẬN DIỆN VẬT THỂ");
            tvLiveArBadge.setText("🎯 Lia ống kính về phía vật thể quanh bạn");
        } else {
            btnModeText.setBackgroundColor(0xFF2563EB);
            btnModeText.setTextColor(0xFFFFFFFF);
            btnModeObject.setBackgroundColor(0xFF334155);
            btnModeObject.setTextColor(0xFF94A3B8);
            tvLiveStatus.setText("⚡ OFFLINE • SÁCH & VĂN BẢN (OCR)");
            tvLiveArBadge.setText("📝 Hướng camera vào trang sách, biển hiệu, màn hình");
        }

        if (graphicOverlay != null) {
            graphicOverlay.clear();
        }
    }

    private boolean allPermissionsGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                cameraProvider = cameraProviderFuture.get();

                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                analyzer = new CameraVisionAnalyzer(new CameraVisionAnalyzer.OnVisionResultListener() {
                    @Override
                    public void onObjectDetected(VisualWordInfo info, List<GraphicOverlay.ArTag> tags) {
                        runOnUiThread(() -> handleDetectionResult(info, tags));
                    }

                    @Override
                    public void onTextDetected(String fullText, VisualWordInfo info, List<GraphicOverlay.ArTag> tags) {
                        runOnUiThread(() -> handleDetectionResult(info, tags));
                    }

                    @Override
                    public void onNoResult() {
                        runOnUiThread(() -> {
                            if (graphicOverlay != null && !isFrozen) {
                                graphicOverlay.clear();
                            }
                        });
                    }
                });

                analyzer.setGraphicOverlay(graphicOverlay);

                ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build();
                imageAnalysis.setAnalyzer(cameraExecutor, analyzer);

                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(lensFacing)
                        .build();

                cameraProvider.unbindAll();
                camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis);

            } catch (ExecutionException | InterruptedException e) {
                Toast.makeText(this, "Lỗi khởi tạo Camera: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void handleDetectionResult(VisualWordInfo info, List<GraphicOverlay.ArTag> tags) {
        if (info == null || isFrozen) return;

        // Render AR tags on camera feed
        if (graphicOverlay != null && tags != null) {
            graphicOverlay.setTags(tags);
        }

        // Update detail card
        displayWordInfo(info);

        // Check Scavenger Hunt Quests
        boolean completed = questManager.onWordScanned(info.getWordEn());
        updateQuestBannerHud();
        if (completed) {
            triggerHapticFeedback();
            Toast.makeText(this, "🎉 CHÚC MỪNG! BẠN ĐÃ HOÀN THÀNH NHIỆM VỤ SĂN TỪ VỰNG!", Toast.LENGTH_LONG).show();
        }

        // Haptic tap feedback & auto-read aloud if enabled and word has changed (debounced 3.5s)
        long now = System.currentTimeMillis();
        String currentWord = info.getWordEn();
        if (currentWord != null) {
            if (!currentWord.equalsIgnoreCase(lastSpokenWord) || (now - lastSpokenTimestamp > 3500)) {
                lastSpokenWord = currentWord;
                lastSpokenTimestamp = now;

                triggerHapticFeedback();

                if (isAutoSpeakEnabled) {
                    speakEnglish(currentWord);
                }
            }
        }
    }

    private void triggerHapticFeedback() {
        try {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null && vibrator.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(30);
                }
            }
        } catch (Exception ignored) {}
    }

    private void toggleTorch() {
        if (camera != null && camera.getCameraInfo().hasFlashUnit()) {
            isTorchOn = !isTorchOn;
            camera.getCameraControl().enableTorch(isTorchOn);
            btnFlash.setImageResource(isTorchOn ? android.R.drawable.ic_menu_agenda : android.R.drawable.ic_menu_compass);
        } else {
            Toast.makeText(this, "Thiết bị không có đèn flash", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayWordInfo(VisualWordInfo info) {
        if (info == null) return;
        this.currentWordInfo = info;

        int percent = Math.round(info.getConfidence() * 100);

        // Academic C1 title handling
        String title = info.getWordEn();
        String meaning = info.getMeaningVi();
        if (isAcademicC1Mode && info.getAcademicC1WordEn() != null) {
            title = "🎓 " + info.getAcademicC1WordEn();
            if (info.getAcademicC1MeaningVi() != null) {
                meaning = info.getAcademicC1MeaningVi();
            }
        }

        tvLiveArBadge.setText("🎯 " + title + " (" + percent + "%) • " + meaning);
        tvWordEn.setText(title);
        tvIpa.setText(info.getIpa());
        tvMeaningVi.setText(meaning);
        tvCefrBadge.setText(isAcademicC1Mode ? "C1" : (info.getCefrLevel() != null ? info.getCefrLevel() : "A1"));
        tvWordType.setText(info.getWordType() != null ? info.getWordType() : "(noun)");

        // Collocation
        if (info.getCollocationEn() != null && !info.getCollocationEn().isEmpty()) {
            tvCollocation.setText("🔗 Cụm từ: " + info.getCollocationEn() + " • " + info.getCollocationVi());
            tvCollocation.setVisibility(View.VISIBLE);
        } else {
            tvCollocation.setVisibility(View.GONE);
        }

        // Idiom Spotlight (Ý tưởng 7)
        if (info.getIdiomEn() != null && !info.getIdiomEn().isEmpty()) {
            tvIdiom.setText("💡 Thành ngữ: " + info.getIdiomEn() + " • " + info.getIdiomVi());
            tvIdiom.setVisibility(View.VISIBLE);
        } else {
            tvIdiom.setVisibility(View.GONE);
        }

        // Word Family Orbits (Ý tưởng 6)
        if (info.getWordFamilyEn() != null && !info.getWordFamilyEn().isEmpty()) {
            tvWordFamily.setText("🌳 Họ hàng từ: " + info.getWordFamilyEn() + "\n(" + info.getWordFamilyVi() + ")");
            tvWordFamily.setVisibility(View.VISIBLE);
        } else {
            tvWordFamily.setVisibility(View.GONE);
        }

        // Instant Contextual Dialogue (Ý tưởng 8)
        if (info.getDialogueEn() != null && !info.getDialogueEn().isEmpty()) {
            tvDialogue.setText("💬 Hội thoại mẫu:\n" + info.getDialogueEn() + "\n" + info.getDialogueVi());
            tvDialogue.setVisibility(View.VISIBLE);
        } else {
            tvDialogue.setVisibility(View.GONE);
        }

        // Example Sentences
        if (info.getExampleSentenceEn() != null && !info.getExampleSentenceEn().isEmpty()) {
            tvExampleEn.setText(info.getExampleSentenceEn());
            tvExampleVi.setText(info.getExampleSentenceVi());
            tvExampleEn.setVisibility(View.VISIBLE);
            tvExampleVi.setVisibility(View.VISIBLE);
        } else {
            tvExampleEn.setVisibility(View.GONE);
            tvExampleVi.setVisibility(View.GONE);
        }

        if (info.getDetailedAiNotesVi() != null && !info.getDetailedAiNotesVi().isEmpty()) {
            tvAiNotes.setText("🤖 Chi tiết AI: " + info.getDetailedAiNotesVi());
            tvAiNotes.setVisibility(View.VISIBLE);
        } else {
            tvAiNotes.setVisibility(View.GONE);
        }
    }

    private void performDeepAiScan() {
        Bitmap bitmap = previewView.getBitmap();
        if (bitmap == null) {
            Toast.makeText(this, "Không thể chụp ảnh từ Camera.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressAiVision.setVisibility(View.VISIBLE);
        btnDeepAiScan.setEnabled(false);
        tvLiveStatus.setText("🔍 ĐANG PHÂN TÍCH CHI TIẾT BẰNG AI...");
        tvLiveStatus.setTextColor(0xFF38BDF8);

        GeminiVisionClient.getInstance().analyzeImageDeeply(this, bitmap, new AiCallback<VisualWordInfo>() {
            @Override
            public void onSuccess(VisualWordInfo result) {
                progressAiVision.setVisibility(View.GONE);
                btnDeepAiScan.setEnabled(true);
                tvLiveStatus.setText("⚡ 100% OFFLINE • AR ACTIVE");
                tvLiveStatus.setTextColor(0xFF34D399);

                displayWordInfo(result);
                speakEnglish(result.getWordEn());
                Toast.makeText(CameraLensActivity.this, "Đã nhận diện thành công: " + result.getWordEn(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String errorMessage) {
                progressAiVision.setVisibility(View.GONE);
                btnDeepAiScan.setEnabled(true);
                tvLiveStatus.setText("⚡ 100% OFFLINE • AR ACTIVE");
                tvLiveStatus.setTextColor(0xFF34D399);
                Toast.makeText(CameraLensActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void captureStudyCard() {
        if (currentWordInfo == null) {
            Toast.makeText(this, "Chưa nhận diện được từ vựng nào.", Toast.LENGTH_SHORT).show();
            return;
        }

        saveWordToNotebook();

        new AlertDialog.Builder(this)
                .setTitle("📸 Đã Chụp & Lưu Thẻ Học AR")
                .setMessage("Từ vựng: " + currentWordInfo.getWordEn() + " " + (currentWordInfo.getIpa() != null ? currentWordInfo.getIpa() : "") + "\n"
                        + "Nghĩa: " + currentWordInfo.getMeaningVi() + "\n\n"
                        + "Ví dụ: " + currentWordInfo.getExampleSentenceEn() + "\n"
                        + "(" + currentWordInfo.getExampleSentenceVi() + ")\n\n"
                        + "✅ Đã lưu trực tiếp vào Sổ tay từ vựng của bạn!")
                .setPositiveButton("Phát âm từ & câu", (dialog, which) -> {
                    speakEnglish(currentWordInfo.getWordEn() + ". " + currentWordInfo.getExampleSentenceEn());
                })
                .setNegativeButton("Đóng", null)
                .show();
    }

    private void saveWordToNotebook() {
        if (currentWordInfo == null) return;

        AppDatabase.databaseWriteExecutor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(this);

            ErrorNote note = new ErrorNote();
            note.sourceType = "CAMERA_LENS";
            note.level = currentWordInfo.getCefrLevel() != null ? currentWordInfo.getCefrLevel() : "A1";
            note.title = currentWordInfo.getWordEn() + " " + (currentWordInfo.getIpa() != null ? currentWordInfo.getIpa() : "");
            note.originalText = currentWordInfo.getExampleSentenceEn();
            note.correctedText = currentWordInfo.getMeaningVi();
            note.explanationVi = (currentWordInfo.getCollocationEn() != null ? currentWordInfo.getCollocationEn() : "")
                    + " • " + (currentWordInfo.getCollocationVi() != null ? currentWordInfo.getCollocationVi() : "");
            note.userNote = currentWordInfo.getExampleSentenceVi();
            note.isResolved = false;
            note.createdAt = System.currentTimeMillis();

            db.errorNotebookDao().insertNote(note);

            runOnUiThread(() -> Toast.makeText(this, "⭐ Đã lưu \"" + currentWordInfo.getWordEn() + "\" vào Sổ tay từ vựng!", Toast.LENGTH_SHORT).show());
        });
    }

    private void speakEnglish(String text) {
        if (tts != null && text != null && !text.isEmpty()) {
            tts.setLanguage(Locale.US);
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "CAMERA_TTS_EN");
        }
    }

    private void speakVietnamese(String text) {
        if (tts != null && text != null && !text.isEmpty()) {
            try {
                int res = tts.setLanguage(new Locale("vi", "VN"));
                if (res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED) {
                    tts.setLanguage(Locale.getDefault());
                }
            } catch (Exception e) {
                tts.setLanguage(Locale.getDefault());
            }
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "CAMERA_TTS_VI");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            Toast.makeText(this, "Ứng dụng cần quyền Camera để nhận diện từ vựng trực quan.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        if (cameraExecutor != null) {
            cameraExecutor.shutdown();
        }
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        if (speechEvaluationHelper != null) {
            speechEvaluationHelper.stopListening();
        }
        super.onDestroy();
    }
}
