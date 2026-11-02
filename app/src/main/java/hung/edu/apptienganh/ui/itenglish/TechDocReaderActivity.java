package hung.edu.apptienganh.ui.itenglish;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.ui.itenglish.engine.ItEnglishEngine;
import hung.edu.apptienganh.ui.itenglish.model.TechDocArticle;
import hung.edu.apptienganh.ui.itenglish.model.TechDocQuiz;
import hung.edu.apptienganh.ui.itenglish.model.TechVocabulary;

/**
 * Priority 2 Feature: Technical Documentation Reader.
 * Reads React, Spring, Docker, Cloud docs, extracts specialized terms,
 * provides simplified plain English, and generates comprehension quizzes.
 */
public class TechDocReaderActivity extends AppCompatActivity {

    private TextView tvDocTitle, tvDocCategory, tvDocLevel;
    private Button tabContent, tabSimplified, tabVocab, tabQuiz;
    private View viewContentTab, viewSimplifiedTab, viewVocabTab, viewQuizTab;

    private TextView tvDocContent, tvSimplifiedText, tvSummaryVi;
    private LinearLayout layoutVocabList, layoutQuizList;
    private Button btnNextDoc;

    private List<TechDocArticle> articles;
    private int currentDocIndex = 0;
    private TechDocArticle currentArticle;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_doc_reader);

        initViews();
        setupTts();

        articles = ItEnglishEngine.getInstance().getAllTechArticles();
        if (!articles.isEmpty()) {
            displayArticle(articles.get(0));
        }

        setupTabListeners();
    }

    private void initViews() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        tvDocTitle = findViewById(R.id.tvDocTitle);
        tvDocCategory = findViewById(R.id.tvDocCategory);
        tvDocLevel = findViewById(R.id.tvDocLevel);
        btnNextDoc = findViewById(R.id.btnNextDoc);

        tabContent = findViewById(R.id.tabContent);
        tabSimplified = findViewById(R.id.tabSimplified);
        tabVocab = findViewById(R.id.tabVocab);
        tabQuiz = findViewById(R.id.tabQuiz);

        viewContentTab = findViewById(R.id.viewContentTab);
        viewSimplifiedTab = findViewById(R.id.viewSimplifiedTab);
        viewVocabTab = findViewById(R.id.viewVocabTab);
        viewQuizTab = findViewById(R.id.viewQuizTab);

        tvDocContent = findViewById(R.id.tvDocContent);
        tvSimplifiedText = findViewById(R.id.tvSimplifiedText);
        tvSummaryVi = findViewById(R.id.tvSummaryVi);
        layoutVocabList = findViewById(R.id.layoutVocabList);
        layoutQuizList = findViewById(R.id.layoutQuizList);
    }

    private void setupTts() {
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
            }
        });
    }

    private void setupTabListeners() {
        tabContent.setOnClickListener(v -> switchTab(0));
        tabSimplified.setOnClickListener(v -> switchTab(1));
        tabVocab.setOnClickListener(v -> switchTab(2));
        tabQuiz.setOnClickListener(v -> switchTab(3));

        btnNextDoc.setOnClickListener(v -> {
            if (articles.isEmpty()) return;
            currentDocIndex = (currentDocIndex + 1) % articles.size();
            displayArticle(articles.get(currentDocIndex));
            switchTab(0);
        });
    }

    private void switchTab(int tabIndex) {
        viewContentTab.setVisibility(tabIndex == 0 ? View.VISIBLE : View.GONE);
        viewSimplifiedTab.setVisibility(tabIndex == 1 ? View.VISIBLE : View.GONE);
        viewVocabTab.setVisibility(tabIndex == 2 ? View.VISIBLE : View.GONE);
        viewQuizTab.setVisibility(tabIndex == 3 ? View.VISIBLE : View.GONE);

        tabContent.setBackgroundColor(tabIndex == 0 ? 0xFF2563EB : 0xFF334155);
        tabContent.setTextColor(tabIndex == 0 ? 0xFFFFFFFF : 0xFF94A3B8);

        tabSimplified.setBackgroundColor(tabIndex == 1 ? 0xFF2563EB : 0xFF334155);
        tabSimplified.setTextColor(tabIndex == 1 ? 0xFFFFFFFF : 0xFF94A3B8);

        tabVocab.setBackgroundColor(tabIndex == 2 ? 0xFF2563EB : 0xFF334155);
        tabVocab.setTextColor(tabIndex == 2 ? 0xFFFFFFFF : 0xFF94A3B8);

        tabQuiz.setBackgroundColor(tabIndex == 3 ? 0xFF2563EB : 0xFF334155);
        tabQuiz.setTextColor(tabIndex == 3 ? 0xFFFFFFFF : 0xFF94A3B8);
    }

    private void displayArticle(TechDocArticle article) {
        this.currentArticle = article;
        tvDocTitle.setText(article.getTitle());
        tvDocCategory.setText(article.getTechnology());
        tvDocLevel.setText(article.getLevel());

        tvSimplifiedText.setText(article.getSimplifiedExplanationEn());
        tvSummaryVi.setText(article.getSummaryVi());

        // Highlight technical vocabularies in original text
        highlightVocabulariesInContent(article);

        // Populate vocabulary list tab
        populateVocabList(article.getVocabularies());

        // Populate quiz questions tab
        populateQuizList(article.getQuizzes());
    }

    private void highlightVocabulariesInContent(TechDocArticle article) {
        String fullText = article.getContentEn();
        SpannableString spannable = new SpannableString(fullText);

        for (TechVocabulary vocab : article.getVocabularies()) {
            String term = vocab.getTerm();
            int startIndex = fullText.toLowerCase(Locale.ROOT).indexOf(term.toLowerCase(Locale.ROOT));
            while (startIndex >= 0) {
                int endIndex = startIndex + term.length();
                spannable.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        showVocabDetailDialog(vocab);
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.parseColor("#38BDF8"));
                        ds.setUnderlineText(true);
                        ds.setFakeBoldText(true);
                    }
                }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                startIndex = fullText.toLowerCase(Locale.ROOT).indexOf(term.toLowerCase(Locale.ROOT), endIndex);
            }
        }

        tvDocContent.setText(spannable);
        tvDocContent.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void showVocabDetailDialog(TechVocabulary vocab) {
        new AlertDialog.Builder(this)
                .setTitle("📚 Thuật Ngữ IT: " + vocab.getTerm())
                .setMessage("Phiên âm: " + vocab.getIpa() + " (" + vocab.getPartOfSpeech() + ")\n\n"
                        + "🇻🇳 Nghĩa: " + vocab.getMeaningVi() + "\n\n"
                        + "📖 Định nghĩa: " + vocab.getDefinitionEn() + "\n\n"
                        + "💬 Ví dụ: " + vocab.getExampleEn())
                .setPositiveButton("🔊 Phát âm", (d, w) -> {
                    if (tts != null) tts.speak(vocab.getTerm(), TextToSpeech.QUEUE_FLUSH, null, "VOCAB_TTS");
                })
                .setNegativeButton("Đóng", null)
                .show();
    }

    private void populateVocabList(List<TechVocabulary> vocabs) {
        layoutVocabList.removeAllViews();
        for (TechVocabulary v : vocabs) {
            LinearLayout item = new LinearLayout(this);
            item.setOrientation(LinearLayout.VERTICAL);
            item.setBackgroundColor(Color.parseColor("#1E293B"));
            item.setPadding(32, 24, 32, 24);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 0, 18);
            item.setLayoutParams(lp);

            LinearLayout topRow = new LinearLayout(this);
            topRow.setOrientation(LinearLayout.HORIZONTAL);
            topRow.setGravity(android.view.Gravity.CENTER_VERTICAL);

            TextView tvTerm = new TextView(this);
            tvTerm.setText(v.getTerm() + " " + v.getIpa());
            tvTerm.setTextColor(Color.parseColor("#38BDF8"));
            tvTerm.setTextSize(15f);
            tvTerm.setTypeface(null, android.graphics.Typeface.BOLD);
            topRow.addView(tvTerm, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            Button btnSpeak = new Button(this);
            btnSpeak.setText("🔊");
            btnSpeak.setTextSize(12f);
            btnSpeak.setBackgroundColor(Color.parseColor("#334155"));
            btnSpeak.setOnClickListener(click -> {
                if (tts != null) tts.speak(v.getTerm(), TextToSpeech.QUEUE_FLUSH, null, "VOCAB_LIST_TTS");
            });
            topRow.addView(btnSpeak, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 80));

            item.addView(topRow);

            TextView tvMeaning = new TextView(this);
            tvMeaning.setText("🇻🇳 " + v.getMeaningVi());
            tvMeaning.setTextColor(Color.parseColor("#34D399"));
            tvMeaning.setTextSize(13f);
            tvMeaning.setPadding(0, 8, 0, 4);
            item.addView(tvMeaning);

            TextView tvDef = new TextView(this);
            tvDef.setText("📖 " + v.getDefinitionEn());
            tvDef.setTextColor(Color.parseColor("#94A3B8"));
            tvDef.setTextSize(12.5f);
            item.addView(tvDef);

            layoutVocabList.addView(item);
        }
    }

    private void populateQuizList(List<TechDocQuiz> quizzes) {
        layoutQuizList.removeAllViews();
        int qNum = 1;
        for (TechDocQuiz q : quizzes) {
            LinearLayout item = new LinearLayout(this);
            item.setOrientation(LinearLayout.VERTICAL);
            item.setBackgroundColor(Color.parseColor("#1E293B"));
            item.setPadding(32, 24, 32, 24);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 0, 18);
            item.setLayoutParams(lp);

            TextView tvQ = new TextView(this);
            tvQ.setText("Câu " + (qNum++) + ": " + q.getQuestion());
            tvQ.setTextColor(Color.WHITE);
            tvQ.setTextSize(14f);
            tvQ.setTypeface(null, android.graphics.Typeface.BOLD);
            item.addView(tvQ);

            for (int i = 0; i < q.getOptions().size(); i++) {
                final int optIndex = i;
                Button btnOpt = new Button(this);
                btnOpt.setText(q.getOptions().get(i));
                btnOpt.setTextSize(12f);
                btnOpt.setAllCaps(false);
                btnOpt.setBackgroundColor(Color.parseColor("#334155"));
                btnOpt.setTextColor(Color.parseColor("#CBD5E1"));

                LinearLayout.LayoutParams optLp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                optLp.setMargins(0, 8, 0, 0);
                btnOpt.setLayoutParams(optLp);

                btnOpt.setOnClickListener(v -> {
                    if (optIndex == q.getCorrectIndex()) {
                        btnOpt.setBackgroundColor(Color.parseColor("#059669"));
                        btnOpt.setTextColor(Color.WHITE);
                        Toast.makeText(TechDocReaderActivity.this, "🎉 CHÍNH XÁC!\n" + q.getExplanationVi(), Toast.LENGTH_LONG).show();
                    } else {
                        btnOpt.setBackgroundColor(Color.parseColor("#DC2626"));
                        btnOpt.setTextColor(Color.WHITE);
                        Toast.makeText(TechDocReaderActivity.this, "Chưa đúng rồi! Hãy đọc lại tài liệu.", Toast.LENGTH_SHORT).show();
                    }
                });

                item.addView(btnOpt);
            }

            layoutQuizList.addView(item);
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
