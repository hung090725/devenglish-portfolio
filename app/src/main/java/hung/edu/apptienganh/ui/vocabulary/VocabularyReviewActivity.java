package hung.edu.apptienganh.ui.vocabulary;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.entity.Vocabulary;
import hung.edu.apptienganh.data.local.entity.VocabularyReviewProgress;
import hung.edu.apptienganh.data.repository.AppRepository;
import hung.edu.apptienganh.domain.model.DaySection;
import hung.edu.apptienganh.domain.srs.VocabularyReviewFilter;
import hung.edu.apptienganh.speech.TtsManager;

public class VocabularyReviewActivity extends AppCompatActivity {

    private TextView txtReviewProgress, txtWord, txtIpa, txtAnswer, txtEmptyReview;
    private Button btnListen, btnShowAnswer, btnOptionA, btnOptionB, btnOptionC, btnOptionD;
    private Button btnAgain, btnHard, btnGood, btnEasy;
    private Button btnFilterAll, btnFilterA0, btnFilterA1, btnFilterA2, btnFilterB1, btnFilterDue, btnFilterWeak;
    private LinearLayout layoutRatings;
    private CardView cardWord;
    private ImageView btnBack;

    private AppRepository repository;
    private TtsManager ttsManager;

    private final List<VocabularyReviewProgress> dueList = new ArrayList<>();
    private int currentIndex = 0;
    private Vocabulary currentVocab;
    private String correctMeaning = "";
    private String currentFilter = VocabularyReviewFilter.FILTER_DUE_ONLY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_review);

        bindViews();
        repository = new AppRepository(this);
        ttsManager = new TtsManager(this);

        btnBack.setOnClickListener(v -> finish());
        btnListen.setOnClickListener(v -> {
            if (currentVocab != null) {
                ttsManager.speakEnglishUs(currentVocab.getWord());
            }
        });
        btnShowAnswer.setOnClickListener(v -> revealAnswer());
        btnAgain.setOnClickListener(v -> rate(DaySection.RATING_AGAIN));
        btnHard.setOnClickListener(v -> rate(DaySection.RATING_HARD));
        btnGood.setOnClickListener(v -> rate(DaySection.RATING_GOOD));
        btnEasy.setOnClickListener(v -> rate(DaySection.RATING_EASY));

        ImageView btnReport = findViewById(R.id.btn_report_content);
        btnReport.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(this, hung.edu.apptienganh.ui.feedback.BetaFeedbackActivity.class);
            intent.putExtra(hung.edu.apptienganh.ui.feedback.BetaFeedbackActivity.EXTRA_CATEGORY, "Lesson content issue");
            String screenName = "VocabularyReview";
            if (currentVocab != null) {
                screenName += " - Word: " + currentVocab.getWord();
            }
            intent.putExtra(hung.edu.apptienganh.ui.feedback.BetaFeedbackActivity.EXTRA_SCREEN_NAME, screenName);
            startActivity(intent);
        });

        setupFilters();
        loadDueWords();
    }

    private void bindViews() {
        btnBack = findViewById(R.id.btn_back);
        txtReviewProgress = findViewById(R.id.txt_review_progress);
        txtWord = findViewById(R.id.txt_word);
        txtIpa = findViewById(R.id.txt_ipa);
        txtAnswer = findViewById(R.id.txt_answer);
        txtEmptyReview = findViewById(R.id.txt_empty_review);
        btnListen = findViewById(R.id.btn_listen);
        btnShowAnswer = findViewById(R.id.btn_show_answer);
        btnOptionA = findViewById(R.id.btn_option_a);
        btnOptionB = findViewById(R.id.btn_option_b);
        btnOptionC = findViewById(R.id.btn_option_c);
        btnOptionD = findViewById(R.id.btn_option_d);
        layoutRatings = findViewById(R.id.layout_ratings);
        cardWord = findViewById(R.id.card_word);
        btnAgain = findViewById(R.id.btn_again);
        btnHard = findViewById(R.id.btn_hard);
        btnGood = findViewById(R.id.btn_good);
        btnEasy = findViewById(R.id.btn_easy);
        btnFilterAll = findViewById(R.id.btn_filter_all);
        btnFilterA0 = findViewById(R.id.btn_filter_a0);
        btnFilterA1 = findViewById(R.id.btn_filter_a1);
        btnFilterA2 = findViewById(R.id.btn_filter_a2);
        btnFilterB1 = findViewById(R.id.btn_filter_b1);
        btnFilterDue = findViewById(R.id.btn_filter_due);
        btnFilterWeak = findViewById(R.id.btn_filter_weak);
    }

    private void setupFilters() {
        btnFilterAll.setOnClickListener(v -> applyFilter(VocabularyReviewFilter.FILTER_ALL));
        btnFilterA0.setOnClickListener(v -> applyFilter(VocabularyReviewFilter.FILTER_A0));
        btnFilterA1.setOnClickListener(v -> applyFilter(VocabularyReviewFilter.FILTER_A1));
        btnFilterA2.setOnClickListener(v -> applyFilter(VocabularyReviewFilter.FILTER_A2));
        btnFilterB1.setOnClickListener(v -> applyFilter(VocabularyReviewFilter.FILTER_B1));
        btnFilterDue.setOnClickListener(v -> applyFilter(VocabularyReviewFilter.FILTER_DUE_ONLY));
        btnFilterWeak.setOnClickListener(v -> applyFilter(VocabularyReviewFilter.FILTER_WEAK));
        highlightFilter(btnFilterDue);
    }

    private void applyFilter(String filter) {
        currentFilter = filter;
        highlightFilter(getFilterButton(filter));
        loadDueWords();
    }

    private Button getFilterButton(String filter) {
        switch (filter) {
            case VocabularyReviewFilter.FILTER_A0: return btnFilterA0;
            case VocabularyReviewFilter.FILTER_A1: return btnFilterA1;
            case VocabularyReviewFilter.FILTER_A2: return btnFilterA2;
            case VocabularyReviewFilter.FILTER_B1: return btnFilterB1;
            case VocabularyReviewFilter.FILTER_DUE_ONLY: return btnFilterDue;
            case VocabularyReviewFilter.FILTER_WEAK: return btnFilterWeak;
            default: return btnFilterAll;
        }
    }

    private void highlightFilter(Button selected) {
        Button[] buttons = {btnFilterAll, btnFilterA0, btnFilterA1, btnFilterA2, btnFilterB1, btnFilterDue, btnFilterWeak};
        for (Button b : buttons) {
            boolean active = b == selected;
            b.setBackgroundResource(active ? R.drawable.button_gradient : R.drawable.card_rounded_bg);
            b.setTextColor(active ? Color.WHITE : Color.parseColor("#64748B"));
        }
    }

    private void loadDueWords() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            List<VocabularyReviewProgress> filtered = repository.getReviewListSync(currentFilter);
            runOnUiThread(() -> {
                dueList.clear();
                dueList.addAll(filtered);
                if (dueList.isEmpty()) {
                    showEmptyState();
                } else {
                    currentIndex = 0;
                    showWordAt(currentIndex);
                }
            });
        });
    }

    private void showEmptyState() {
        cardWord.setVisibility(View.GONE);
        layoutRatings.setVisibility(View.GONE);
        txtEmptyReview.setVisibility(View.VISIBLE);
        txtReviewProgress.setText("Không có từ trong bộ lọc này");
    }

    private void showWordAt(int index) {
        if (index >= dueList.size()) {
            showEmptyState();
            txtEmptyReview.setText("Bạn đã ôn xong các từ trong bộ lọc này!");
            txtEmptyReview.setVisibility(View.VISIBLE);
            return;
        }

        VocabularyReviewProgress progress = dueList.get(index);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            Vocabulary vocab = repository.getVocabularyByIdSync(progress.getVocabularyId());
            runOnUiThread(() -> bindWord(vocab, index));
        });
    }

    private void bindWord(Vocabulary vocab, int index) {
        if (vocab == null) {
            Toast.makeText(this, "Không tìm thấy từ vựng.", Toast.LENGTH_SHORT).show();
            return;
        }
        currentVocab = vocab;
        correctMeaning = vocab.getMeaningVi();

        cardWord.setVisibility(View.VISIBLE);
        txtEmptyReview.setVisibility(View.GONE);
        txtReviewProgress.setText("Từ " + (index + 1) + "/" + dueList.size()
                + " • " + vocab.getLevel());
        txtWord.setText(vocab.getWord());
        String ipa = vocab.getIpaUs() != null && !vocab.getIpaUs().isEmpty()
                ? vocab.getIpaUs() : vocab.getIpaUk();
        txtIpa.setText(ipa != null ? ipa : "");
        txtAnswer.setVisibility(View.GONE);
        layoutRatings.setVisibility(View.GONE);
        btnShowAnswer.setVisibility(View.VISIBLE);

        setupOptions(vocab);
    }

    private void setupOptions(Vocabulary vocab) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            List<Vocabulary> pool = new ArrayList<>();
            List<Vocabulary> sameLevel = repository.getVocabularyByLevelSync(vocab.getLevel());
            if (sameLevel != null) {
                Collections.shuffle(sameLevel);
                for (Vocabulary v : sameLevel) {
                    if (v.getId() != vocab.getId() && pool.size() < 3) {
                        pool.add(v);
                    }
                }
            }
            List<String> options = new ArrayList<>();
            options.add(vocab.getMeaningVi());
            for (Vocabulary v : pool) {
                options.add(v.getMeaningVi());
            }
            while (options.size() < 4) {
                options.add("—");
            }
            Collections.shuffle(options);
            List<String> finalOptions = new ArrayList<>(options);
            runOnUiThread(() -> {
                btnOptionA.setVisibility(View.VISIBLE);
                btnOptionB.setVisibility(View.VISIBLE);
                btnOptionC.setVisibility(View.VISIBLE);
                btnOptionD.setVisibility(View.VISIBLE);
                btnOptionA.setText(finalOptions.get(0));
                btnOptionB.setText(finalOptions.get(1));
                btnOptionC.setText(finalOptions.get(2));
                btnOptionD.setText(finalOptions.get(3));
                btnOptionA.setOnClickListener(v -> onOptionSelected(finalOptions.get(0)));
                btnOptionB.setOnClickListener(v -> onOptionSelected(finalOptions.get(1)));
                btnOptionC.setOnClickListener(v -> onOptionSelected(finalOptions.get(2)));
                btnOptionD.setOnClickListener(v -> onOptionSelected(finalOptions.get(3)));
            });
        });
    }

    private void onOptionSelected(String meaning) {
        if (meaning.equals(correctMeaning)) {
            Toast.makeText(this, "Đúng rồi!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Chưa đúng. Hãy xem đáp án.", Toast.LENGTH_SHORT).show();
        }
        revealAnswer();
    }

    private void revealAnswer() {
        if (currentVocab == null) return;
        txtAnswer.setText(currentVocab.getMeaningVi() + "\n\n" + currentVocab.getExampleEn()
                + "\n→ " + currentVocab.getExampleVi());
        txtAnswer.setVisibility(View.VISIBLE);
        btnShowAnswer.setVisibility(View.GONE);
        layoutRatings.setVisibility(View.VISIBLE);
    }

    private void rate(String rating) {
        if (currentVocab == null) return;
        repository.applyVocabularyReviewRating(currentVocab.getId(), rating);
        currentIndex++;
        if (currentIndex >= dueList.size()) {
            Toast.makeText(this, "Đã ôn xong!", Toast.LENGTH_SHORT).show();
            loadDueWords();
        } else {
            showWordAt(currentIndex);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ttsManager != null) ttsManager.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ttsManager != null) ttsManager.shutdown();
    }
}
