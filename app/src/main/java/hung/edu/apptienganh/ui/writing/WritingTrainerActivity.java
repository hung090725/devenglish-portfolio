package hung.edu.apptienganh.ui.writing;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.ai.AiCallback;
import hung.edu.apptienganh.ai.AiTutorEngine;
import hung.edu.apptienganh.ai.AiWritingReview;

public class WritingTrainerActivity extends AppCompatActivity {

    private static final String[] SAMPLE_PROMPTS = {
            "Write 3-5 sentences about your daily routine or your favorite hobby. Explain why you enjoy it.",
            "Describe your dream vacation destination. Where would you go, and who would you travel with?",
            "What are the advantages of learning English in today's digital world? Share your thoughts.",
            "Describe a memorable experience or celebration you had with your friends or family.",
            "Write a short formal email asking for information about an English communication course."
    };

    private int promptIndex = 0;

    private ImageButton btnBack;
    private TextView tvInstruction, tvPrompt, tvWordCount;
    private Button btnSamplePrompt, btnSubmit;
    private EditText etAnswer;
    private LinearLayout layoutWritingLoading;
    private CardView cardResult;
    private TextView tvScoreBadge, tvSummaryVi, tvNativeRewrite, tvCorrectionsDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_trainer);

        initViews();
        setupListeners();
        updatePromptText();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        tvInstruction = findViewById(R.id.tvInstruction);
        tvPrompt = findViewById(R.id.tvPrompt);
        tvWordCount = findViewById(R.id.tvWordCount);
        btnSamplePrompt = findViewById(R.id.btnSamplePrompt);
        btnSubmit = findViewById(R.id.btnSubmit);
        etAnswer = findViewById(R.id.etAnswer);
        layoutWritingLoading = findViewById(R.id.layoutWritingLoading);
        cardResult = findViewById(R.id.cardResult);
        tvScoreBadge = findViewById(R.id.tvScoreBadge);
        tvSummaryVi = findViewById(R.id.tvSummaryVi);
        tvNativeRewrite = findViewById(R.id.tvNativeRewrite);
        tvCorrectionsDetail = findViewById(R.id.tvCorrectionsDetail);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnSamplePrompt.setOnClickListener(v -> {
            promptIndex = (promptIndex + 1) % SAMPLE_PROMPTS.length;
            updatePromptText();
            cardResult.setVisibility(View.GONE);
        });

        etAnswer.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int count, int after) {
                String text = s.toString().trim();
                int words = text.isEmpty() ? 0 : text.split("\\s+").length;
                tvWordCount.setText("Số từ: " + words);
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        btnSubmit.setOnClickListener(v -> submitForAiReview());
    }

    private void updatePromptText() {
        tvPrompt.setText(SAMPLE_PROMPTS[promptIndex]);
    }

    private void submitForAiReview() {
        String answer = etAnswer.getText().toString().trim();
        if (answer.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập bài viết trước khi chấm.", Toast.LENGTH_SHORT).show();
            return;
        }

        layoutWritingLoading.setVisibility(View.VISIBLE);
        cardResult.setVisibility(View.GONE);
        btnSubmit.setEnabled(false);

        String topic = SAMPLE_PROMPTS[promptIndex];
        AiTutorEngine.getInstance().reviewWriting(this, topic, answer, new AiCallback<AiWritingReview>() {
            @Override
            public void onSuccess(AiWritingReview result) {
                layoutWritingLoading.setVisibility(View.GONE);
                btnSubmit.setEnabled(true);
                displayReviewResult(result);
            }

            @Override
            public void onError(String errorMessage) {
                layoutWritingLoading.setVisibility(View.GONE);
                btnSubmit.setEnabled(true);
                Toast.makeText(WritingTrainerActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayReviewResult(AiWritingReview review) {
        cardResult.setVisibility(View.VISIBLE);

        int score = review.getScore();
        tvScoreBadge.setText(score + "/100");
        if (score >= 80) {
            tvScoreBadge.setTextColor(0xFF059669); // Green
        } else if (score >= 60) {
            tvScoreBadge.setTextColor(0xFF2563EB); // Blue
        } else {
            tvScoreBadge.setTextColor(0xFFDC2626); // Red
        }

        tvSummaryVi.setText(review.getSummaryVi());

        if (review.getNaturalRewriteEn() != null && !review.getNaturalRewriteEn().isEmpty()) {
            tvNativeRewrite.setText(review.getNaturalRewriteEn());
            tvNativeRewrite.setVisibility(View.VISIBLE);
        } else {
            tvNativeRewrite.setVisibility(View.GONE);
        }

        StringBuilder sb = new StringBuilder();
        if (review.getCorrections() != null && !review.getCorrections().isEmpty()) {
            for (AiWritingReview.SentenceCorrection c : review.getCorrections()) {
                sb.append("❌ Gốc: \"").append(c.original).append("\"\n")
                  .append("✅ Sửa: \"").append(c.improved).append("\"\n")
                  .append("💡 Lý do: ").append(c.explanationVi).append("\n\n");
            }
        }

        if (review.getGrammarTips() != null && !review.getGrammarTips().isEmpty()) {
            sb.append("📌 Mẹo ngữ pháp & phong cách:\n");
            for (String tip : review.getGrammarTips()) {
                sb.append("• ").append(tip).append("\n");
            }
        }

        if (sb.length() == 0) {
            sb.append("Tuyệt vời! Không phát hiện lỗi cấu trúc câu nghiêm trọng.");
        }

        tvCorrectionsDetail.setText(sb.toString().trim());
    }
}
