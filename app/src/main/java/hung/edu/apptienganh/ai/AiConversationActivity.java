package hung.edu.apptienganh.ai;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import hung.edu.apptienganh.R;

public class AiConversationActivity extends AppCompatActivity {

    private static final int REQUEST_RECORD_AUDIO = 2001;

    private static final String[] TOPICS = {
            "☕ Tại quán Cà phê (At a Coffee Shop)",
            "💼 Phỏng vấn xin việc (Job Interview)",
            "✈️ Sân bay & Du lịch (Airport & Travel)",
            "🛍️ Mua sắm (Shopping & Bargaining)",
            "💬 Trò chuyện hàng ngày (Casual Daily Chat)"
    };

    private TextView tvAiStatus;
    private Spinner spinnerTopic;
    private RecyclerView rvChat;
    private LinearLayout layoutLoading;
    private EditText etMessage;
    private ImageButton btnMic, btnSend, btnBack, btnAiSettings;

    private AiChatAdapter chatAdapter;
    private TextToSpeech tts;
    private SpeechRecognizer speechRecognizer;
    private String currentTopic = TOPICS[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_conversation);

        initViews();
        setupTts();
        setupSpeechRecognizer();
        setupTopicSpinner();
        setupChatRecyclerView();
        setupListeners();
        updateAiStatusText();

        // Initial welcome message
        triggerTopicGreeting(currentTopic);
    }

    private void initViews() {
        tvAiStatus = findViewById(R.id.tvAiStatus);
        spinnerTopic = findViewById(R.id.spinnerTopic);
        rvChat = findViewById(R.id.rvChat);
        layoutLoading = findViewById(R.id.layoutLoading);
        etMessage = findViewById(R.id.etMessage);
        btnMic = findViewById(R.id.btnMic);
        btnSend = findViewById(R.id.btnSend);
        btnBack = findViewById(R.id.btnBack);
        btnAiSettings = findViewById(R.id.btnAiSettings);
    }

    private void updateAiStatusText() {
        if (AiConfig.hasApiKey(this)) {
            tvAiStatus.setText("🤖 Gemini Online (" + AiConfig.getModel(this) + ")");
            tvAiStatus.setTextColor(0xFF2563EB); // Blue
        } else {
            tvAiStatus.setText("⚡ Chế độ Thông minh Ngoại tuyến");
            tvAiStatus.setTextColor(0xFF059669); // Green
        }
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
                @Override public void onReadyForSpeech(Bundle params) {
                    Toast.makeText(AiConversationActivity.this, "Đang lắng nghe... Hãy nói tiếng Anh nhé!", Toast.LENGTH_SHORT).show();
                }
                @Override public void onBeginningOfSpeech() {}
                @Override public void onRmsChanged(float rmsdB) {}
                @Override public void onBufferReceived(byte[] buffer) {}
                @Override public void onEndOfSpeech() {}
                @Override public void onError(int error) {
                    Toast.makeText(AiConversationActivity.this, "Không nhận diện được giọng nói (" + error + ")", Toast.LENGTH_SHORT).show();
                }
                @Override public void onResults(Bundle results) {
                    ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (matches != null && !matches.isEmpty()) {
                        etMessage.setText(matches.get(0));
                        sendMessage();
                    }
                }
                @Override public void onPartialResults(Bundle partialResults) {}
                @Override public void onEvent(int eventType, Bundle params) {}
            });
        }
    }

    private void setupTopicSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TOPICS);
        spinnerTopic.setAdapter(adapter);
        spinnerTopic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = TOPICS[position];
                if (!selected.equals(currentTopic)) {
                    currentTopic = selected;
                    triggerTopicGreeting(currentTopic);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupChatRecyclerView() {
        chatAdapter = new AiChatAdapter(this::speakText);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        rvChat.setLayoutManager(layoutManager);
        rvChat.setAdapter(chatAdapter);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());
        btnAiSettings.setOnClickListener(v -> showAiSettingsDialog());
        btnSend.setOnClickListener(v -> sendMessage());
        btnMic.setOnClickListener(v -> startVoiceInput());
    }

    private void triggerTopicGreeting(String topic) {
        String greeting;
        if (topic.contains("Coffee") || topic.contains("Cà phê")) {
            greeting = "Hello! Welcome to Central Perk Cafe. What can I get started for you today?";
        } else if (topic.contains("Interview") || topic.contains("Phỏng vấn")) {
            greeting = "Good day! Thank you for coming in today. Could you start by introducing yourself briefly?";
        } else if (topic.contains("Airport") || topic.contains("Sân bay")) {
            greeting = "Welcome to the international check-in counter! Where are you flying to today?";
        } else if (topic.contains("Shopping") || topic.contains("Mua sắm")) {
            greeting = "Hello! How can I help you find what you need today? Are you looking for clothes or accessories?";
        } else {
            greeting = "Hello! I'm your AI English Tutor. How is your day going? What would you like to talk about?";
        }

        chatAdapter.setMessages(new ArrayList<>());
        chatAdapter.addMessage(new AiMessage(AiMessage.SENDER_AI, greeting));
        speakText(greeting);
    }

    private void sendMessage() {
        String text = etMessage.getText().toString().trim();
        if (text.isEmpty()) return;

        etMessage.setText("");
        AiMessage userMsg = new AiMessage(AiMessage.SENDER_USER, text);
        chatAdapter.addMessage(userMsg);
        rvChat.smoothScrollToPosition(chatAdapter.getItemCount() - 1);

        layoutLoading.setVisibility(View.VISIBLE);

        AiTutorEngine.getInstance().sendMessage(this, currentTopic, chatAdapter.getMessages(), text, new AiCallback<AiMessage>() {
            @Override
            public void onSuccess(AiMessage result) {
                layoutLoading.setVisibility(View.GONE);
                chatAdapter.addMessage(result);
                rvChat.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
                speakText(result.getText());
            }

            @Override
            public void onError(String errorMessage) {
                layoutLoading.setVisibility(View.GONE);
                Toast.makeText(AiConversationActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startVoiceInput() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO);
            return;
        }

        if (speechRecognizer != null) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
            speechRecognizer.startListening(intent);
        } else {
            Toast.makeText(this, "Thiết bị không hỗ trợ nhận dạng giọng nói", Toast.LENGTH_SHORT).show();
        }
    }

    private void speakText(String text) {
        if (tts != null && text != null) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "UTTERANCE_ID");
        }
    }

    private void showAiSettingsDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_ai_settings, null);
        EditText etApiKey = dialogView.findViewById(R.id.etApiKey);
        EditText etModel = dialogView.findViewById(R.id.etModel);

        etApiKey.setText(AiConfig.getApiKey(this));
        etModel.setText(AiConfig.getModel(this));

        new AlertDialog.Builder(this)
                .setTitle("Cấu hình Trợ lý AI")
                .setView(dialogView)
                .setPositiveButton("Lưu", (dialog, which) -> {
                    String key = etApiKey.getText().toString().trim();
                    String model = etModel.getText().toString().trim();
                    AiConfig.setApiKey(this, key);
                    AiConfig.setModel(this, model.isEmpty() ? AiConfig.DEFAULT_MODEL : model);
                    updateAiStatusText();
                    Toast.makeText(this, "Đã lưu cài đặt AI thành công!", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("Xóa Key (Dùng Offline)", (dialog, which) -> {
                    AiConfig.setApiKey(this, "");
                    updateAiStatusText();
                    Toast.makeText(this, "Đã chuyển sang chế độ Ngoại tuyến", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startVoiceInput();
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
