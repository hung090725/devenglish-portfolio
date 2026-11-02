package hung.edu.apptienganh.ui.conversation;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.entity.ConversationLine;
import hung.edu.apptienganh.data.repository.AppRepository;
import hung.edu.apptienganh.speech.TtsManager;

public class ConversationPracticeActivity extends AppCompatActivity {

    private TextView txtTitle, txtSituation, txtEmpty;
    private LinearLayout layoutLines;
    private Button btnSpeakCurrent, btnNextLine;

    private AppRepository repository;
    private TtsManager ttsManager;
    private List<ConversationLine> lines;
    private int currentLineIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_practice);

        ImageView btnBack = findViewById(R.id.btn_back);
        txtTitle = findViewById(R.id.txt_conversation_title);
        txtSituation = findViewById(R.id.txt_conversation_situation);
        txtEmpty = findViewById(R.id.txt_empty_conversation);
        layoutLines = findViewById(R.id.layout_conversation_lines);
        btnSpeakCurrent = findViewById(R.id.btn_speak_current_line);
        btnNextLine = findViewById(R.id.btn_next_line);

        repository = new AppRepository(this);
        ttsManager = new TtsManager(this);

        int conversationId = getIntent().getIntExtra("conversation_id", 1);

        btnBack.setOnClickListener(v -> finish());
        btnSpeakCurrent.setOnClickListener(v -> speakCurrentLine());
        btnNextLine.setOnClickListener(v -> showNextLine());

        loadConversation(conversationId);
    }

    private void loadConversation(int conversationId) {
        repository.getConversationById(conversationId).observe(this, conversation -> {
            if (conversation != null) {
                txtTitle.setText(conversation.getTitle() + " (" + conversation.getLevel() + ")");
                txtSituation.setText(conversation.getSituationVi());
            }
        });

        repository.getLinesForConversation(conversationId).observe(this, conversationLines -> {
            lines = conversationLines;
            currentLineIndex = 0;
            renderLines();
        });
    }

    private void renderLines() {
        layoutLines.removeAllViews();

        if (lines == null || lines.isEmpty()) {
            txtEmpty.setVisibility(View.VISIBLE);
            btnSpeakCurrent.setEnabled(false);
            btnNextLine.setEnabled(false);
            return;
        }

        txtEmpty.setVisibility(View.GONE);
        btnSpeakCurrent.setEnabled(true);
        btnNextLine.setEnabled(lines.size() > 1);

        for (int i = 0; i <= currentLineIndex && i < lines.size(); i++) {
            addLineView(lines.get(i), i == currentLineIndex);
        }

        btnNextLine.setText(currentLineIndex < lines.size() - 1 ? "Câu tiếp theo" : "Luyện lại từ đầu");
    }

    private void addLineView(ConversationLine line, boolean isCurrent) {
        TextView lineView = new TextView(this);
        lineView.setText(formatLine(line));
        lineView.setTextColor(getColor(isCurrent ? R.color.text_primary : R.color.text_secondary));
        lineView.setTextSize(isCurrent ? 17 : 15);
        lineView.setPadding(18, 14, 18, 14);
        lineView.setBackgroundResource(R.drawable.card_rounded_bg);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 12);
        lineView.setLayoutParams(params);
        lineView.setGravity(Gravity.START);

        layoutLines.addView(lineView);
    }

    private String formatLine(ConversationLine line) {
        StringBuilder builder = new StringBuilder();
        builder.append(line.getRole()).append(": ").append(line.getSentenceEn());

        if (line.getIpa() != null && !line.getIpa().isEmpty()) {
            builder.append("\n").append(line.getIpa());
        }

        if (line.getSentenceVi() != null && !line.getSentenceVi().isEmpty()) {
            builder.append("\n").append(line.getSentenceVi());
        }

        return builder.toString();
    }

    private void speakCurrentLine() {
        if (lines == null || lines.isEmpty() || ttsManager == null) {
            return;
        }

        ConversationLine currentLine = lines.get(currentLineIndex);
        ttsManager.speakEnglishUs(currentLine.getSentenceEn());
    }

    private void showNextLine() {
        if (lines == null || lines.isEmpty()) {
            return;
        }

        if (currentLineIndex < lines.size() - 1) {
            currentLineIndex++;
        } else {
            currentLineIndex = 0;
        }

        renderLines();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ttsManager != null) {
            ttsManager.shutdown();
        }
    }
}
