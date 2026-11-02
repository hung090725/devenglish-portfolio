package hung.edu.apptienganh.camera;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import hung.edu.apptienganh.R;

/**
 * Deep Knowledge & Encyclopedic Learning Hub (Viện Hàn Lâm Học Sâu)
 * Integrating ideas 16 to 35: Etymology, Component Anatomy, Confusing Words,
 * British vs American, Sensory Adjectives, Mindmap, IELTS Speaking Band 7.5+,
 * Applied Grammar, Phrasal Verbs, ESP Specialized English, and AI Tutor Insights.
 */
public class DeepKnowledgeExplorerDialog extends Dialog {

    public interface DialogListener {
        void onSpeakRequested(String text, boolean isUkAccent);
        void onSaveRequested(VisualWordInfo info);
        void onShadowingRequested(String sentence);
    }

    private final VisualWordInfo info;
    private final DialogListener listener;
    private boolean isUkAccent = false;

    public DeepKnowledgeExplorerDialog(@NonNull Context context, VisualWordInfo info, DialogListener listener) {
        super(context);
        this.info = info;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }

        buildView();
    }

    private void buildView() {
        Context ctx = getContext();

        // Root container
        LinearLayout root = new LinearLayout(ctx);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.parseColor("#F8FAFC"));
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // 1. Top Header Bar
        LinearLayout topBar = new LinearLayout(ctx);
        topBar.setOrientation(LinearLayout.HORIZONTAL);
        topBar.setBackgroundColor(Color.parseColor("#0F172A"));
        topBar.setPadding(32, 32, 32, 32);
        topBar.setGravity(android.view.Gravity.CENTER_VERTICAL);

        TextView tvTitle = new TextView(ctx);
        tvTitle.setText("🏛️ BÁCH KHOA HỌC SÂU (DEEP KNOWLEDGE HUB)");
        tvTitle.setTextColor(Color.WHITE);
        tvTitle.setTextSize(15f);
        tvTitle.setTypeface(null, android.graphics.Typeface.BOLD);
        LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        topBar.addView(tvTitle, titleLp);

        ImageButton btnClose = new ImageButton(ctx);
        btnClose.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        btnClose.setColorFilter(Color.WHITE);
        btnClose.setBackgroundColor(Color.TRANSPARENT);
        btnClose.setOnClickListener(v -> dismiss());
        topBar.addView(btnClose);

        root.addView(topBar);

        // 2. Scrollable Content Area
        androidx.core.widget.NestedScrollView scroll = new androidx.core.widget.NestedScrollView(ctx);
        scroll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));

        LinearLayout content = new LinearLayout(ctx);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(32, 24, 32, 32);

        // Header Word Card
        addHeaderWordCard(ctx, content);

        // Feature 16: Từ nguyên học (Etymology)
        if (info.getEtymologyRoot() != null && !info.getEtymologyRoot().isEmpty()) {
            addSectionCard(ctx, content, "🧬 NGUỒN GỐC & TỪ NGUYÊN HỌC (ETYMOLOGY)", info.getEtymologyRoot(), "#EFF6FF", "#1E40AF");
        }

        // Feature 17: Phân tách cấu tạo (Component Anatomy)
        if (info.getComponentAnatomy() != null && !info.getComponentAnatomy().isEmpty()) {
            addSectionCard(ctx, content, "🔍 BÓC TÁCH GIẢI PHẪU ĐỒ VẬT (COMPONENT ANATOMY)", info.getComponentAnatomy(), "#F0FDF4", "#166534");
        }

        // Feature 18: Phân biệt từ dễ nhầm lẫn (Confusing Words)
        if (info.getConfusingWordAlert() != null && !info.getConfusingWordAlert().isEmpty()) {
            addSectionCard(ctx, content, "⚠️ CẢNH BÁO: TỪ DỄ DÙNG SAI (CONFUSING WORDS)", info.getConfusingWordAlert(), "#FEF2F2", "#991B1B");
        }

        // Feature 20: Anh-Anh vs Anh-Mỹ
        if (info.getBritishVsAmerican() != null && !info.getBritishVsAmerican().isEmpty()) {
            addSectionCard(ctx, content, "🇬🇧 / 🇺🇸 ANH - ANH VS ANH - MỸ (BRITISH VS AMERICAN)", info.getBritishVsAmerican(), "#FDF4FF", "#86198F");
        }

        // Feature 25: Ngữ pháp ứng dụng (Applied Grammar Beacon)
        if (info.getGrammarRuleNotes() != null && !info.getGrammarRuleNotes().isEmpty()) {
            addSectionCard(ctx, content, "⚖️ LA BÀN NGỮ PHÁP THỰC TẾ (APPLIED GRAMMAR)", info.getGrammarRuleNotes(), "#FFFBEB", "#92400E");
        }

        // Feature 26: Luyện thi IELTS Speaking Band 7.5+
        if (info.getIeltsSpeakingSample() != null && !info.getIeltsSpeakingSample().isEmpty()) {
            addSectionCard(ctx, content, "🏆 PHÒNG THI IELTS SPEAKING BAND 7.5+", info.getIeltsSpeakingSample(), "#F8FAFC", "#0F172A");
        }

        // Feature 27: Phrasal Verbs
        if (info.getPhrasalVerbs() != null && !info.getPhrasalVerbs().isEmpty()) {
            addSectionCard(ctx, content, "🔗 CHÒM SAO CỤM ĐỘNG TỪ (PHRASAL VERBS)", info.getPhrasalVerbs(), "#F0FDF4", "#15803D");
        }

        // Feature 21: Tính từ 5 giác quan
        if (info.getSensoryAdjectives() != null && !info.getSensoryAdjectives().isEmpty()) {
            addSectionCard(ctx, content, "🎨 TÍNH TỪ 5 GIÁC QUAN (SENSORY ADJECTIVES)", info.getSensoryAdjectives(), "#F5F3FF", "#5B21B6");
        }

        // Feature 22: Sơ đồ tư duy
        if (info.getMindmapTree() != null && !info.getMindmapTree().isEmpty()) {
            addSectionCard(ctx, content, "🌳 SƠ ĐỒ TƯ DUY TOÀN DIỆN (AR MINDMAP TREE)", info.getMindmapTree(), "#ECFEFF", "#155E75");
        }

        // Feature 23: Mẹo siêu trí nhớ
        if (info.getMnemonicHook() != null && !info.getMnemonicHook().isEmpty()) {
            addSectionCard(ctx, content, "💡 MẸO SIÊU TRÍ NHỚ (MNEMONIC HOOK)", info.getMnemonicHook(), "#FEF3C7", "#78350F");
        }

        // Feature 28: ESP Tiếng Anh chuyên ngành
        if (info.getEspSpecializedVocab() != null && !info.getEspSpecializedVocab().isEmpty()) {
            addSectionCard(ctx, content, "💼 TIẾNG ANH CHUYÊN NGÀNH (ESP: IT / BUSINESS)", info.getEspSpecializedVocab(), "#F1F5F9", "#334155");
        }

        // Feature 29: Kịch bản mua sắm
        if (info.getShoppingInquiries() != null && !info.getShoppingInquiries().isEmpty()) {
            addSectionCard(ctx, content, "🛒 GIAO TIẾP MUA SẮM QUỐC TẾ (SHOPPING INQUIRIES)", info.getShoppingInquiries(), "#FEF9C3", "#854D0E");
        }

        // Feature 35: Lời bình luận của Gia sư AI
        if (info.getAiTutorFact() != null && !info.getAiTutorFact().isEmpty()) {
            addSectionCard(ctx, content, "🤖 GIA SƯ AI CHIA SẺ THÊM (VIRTUAL TUTOR INSIGHT)", info.getAiTutorFact(), "#EFF6FF", "#1D4ED8");
        }

        scroll.addView(content);
        root.addView(scroll);

        // 3. Bottom Action Buttons Bar
        LinearLayout bottomBar = new LinearLayout(ctx);
        bottomBar.setOrientation(LinearLayout.HORIZONTAL);
        bottomBar.setBackgroundColor(Color.WHITE);
        bottomBar.setPadding(24, 20, 24, 24);
        bottomBar.setElevation(16f);

        Button btnShadowing = new Button(ctx);
        btnShadowing.setText("🗣️ Shadowing");
        btnShadowing.setBackgroundColor(Color.parseColor("#8B5CF6"));
        btnShadowing.setTextColor(Color.WHITE);
        btnShadowing.setOnClickListener(v -> {
            if (listener != null) {
                listener.onShadowingRequested(info.getExampleSentenceEn());
            }
        });
        LinearLayout.LayoutParams btnShadowLp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        btnShadowLp.setMarginEnd(12);
        bottomBar.addView(btnShadowing, btnShadowLp);

        Button btnSaveAll = new Button(ctx);
        btnSaveAll.setText("⭐ Lưu Tất Cả");
        btnSaveAll.setBackgroundColor(Color.parseColor("#059669"));
        btnSaveAll.setTextColor(Color.WHITE);
        btnSaveAll.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSaveRequested(info);
                Toast.makeText(ctx, "⭐ Đã lưu toàn bộ kiến thức chuyên sâu vào Sổ tay!", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout.LayoutParams btnSaveLp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        bottomBar.addView(btnSaveAll, btnSaveLp);

        root.addView(bottomBar);

        setContentView(root);
    }

    private void addHeaderWordCard(Context ctx, LinearLayout parent) {
        LinearLayout card = new LinearLayout(ctx);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setBackgroundColor(Color.WHITE);
        card.setPadding(32, 28, 32, 28);
        card.setElevation(6f);

        LinearLayout row = new LinearLayout(ctx);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(android.view.Gravity.CENTER_VERTICAL);

        TextView tvWord = new TextView(ctx);
        tvWord.setText(info.getWordEn());
        tvWord.setTextColor(Color.parseColor("#0F172A"));
        tvWord.setTextSize(24f);
        tvWord.setTypeface(null, android.graphics.Typeface.BOLD);
        row.addView(tvWord);

        TextView tvCefr = new TextView(ctx);
        tvCefr.setText(" " + (info.getCefrLevel() != null ? info.getCefrLevel() : "A1") + " ");
        tvCefr.setBackgroundColor(Color.parseColor("#DBEAFE"));
        tvCefr.setTextColor(Color.parseColor("#1D4ED8"));
        tvCefr.setTextSize(12f);
        tvCefr.setTypeface(null, android.graphics.Typeface.BOLD);
        LinearLayout.LayoutParams cefrLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cefrLp.setMarginStart(16);
        row.addView(tvCefr, cefrLp);

        card.addView(row);

        TextView tvIpa = new TextView(ctx);
        tvIpa.setText(info.getIpa() + (info.getSyllableStress() != null ? " • Nhấn âm: " + info.getSyllableStress() : ""));
        tvIpa.setTextColor(Color.parseColor("#2563EB"));
        tvIpa.setTextSize(14f);
        tvIpa.setPadding(0, 4, 0, 4);
        card.addView(tvIpa);

        TextView tvMeaning = new TextView(ctx);
        tvMeaning.setText("🇻🇳 " + info.getMeaningVi());
        tvMeaning.setTextColor(Color.parseColor("#059669"));
        tvMeaning.setTextSize(16f);
        tvMeaning.setTypeface(null, android.graphics.Typeface.BOLD);
        card.addView(tvMeaning);

        // Accent toggles row (UK / US accent audio)
        LinearLayout accentRow = new LinearLayout(ctx);
        accentRow.setOrientation(LinearLayout.HORIZONTAL);
        accentRow.setPadding(0, 16, 0, 0);

        Button btnUk = new Button(ctx);
        btnUk.setText("🇬🇧 Giọng Anh (UK)");
        btnUk.setTextSize(11f);
        btnUk.setBackgroundColor(Color.parseColor("#E2E8F0"));
        btnUk.setTextColor(Color.parseColor("#0F172A"));
        btnUk.setOnClickListener(v -> {
            if (listener != null) listener.onSpeakRequested(info.getWordEn(), true);
        });
        LinearLayout.LayoutParams ukLp = new LinearLayout.LayoutParams(0, 80, 1f);
        ukLp.setMarginEnd(8);
        accentRow.addView(btnUk, ukLp);

        Button btnUs = new Button(ctx);
        btnUs.setText("🇺🇸 Giọng Mỹ (US)");
        btnUs.setTextSize(11f);
        btnUs.setBackgroundColor(Color.parseColor("#E2E8F0"));
        btnUs.setTextColor(Color.parseColor("#0F172A"));
        btnUs.setOnClickListener(v -> {
            if (listener != null) listener.onSpeakRequested(info.getWordEn(), false);
        });
        LinearLayout.LayoutParams usLp = new LinearLayout.LayoutParams(0, 80, 1f);
        accentRow.addView(btnUs, usLp);

        card.addView(accentRow);

        LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cardLp.setMargins(0, 0, 0, 24);
        parent.addView(card, cardLp);
    }

    private void addSectionCard(Context ctx, LinearLayout parent, String header, String body, String bgColorHex, String textColorHex) {
        LinearLayout card = new LinearLayout(ctx);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setBackgroundColor(Color.parseColor(bgColorHex));
        card.setPadding(28, 20, 28, 20);
        card.setElevation(3f);

        TextView tvHeader = new TextView(ctx);
        tvHeader.setText(header);
        tvHeader.setTextColor(Color.parseColor(textColorHex));
        tvHeader.setTextSize(12.5f);
        tvHeader.setTypeface(null, android.graphics.Typeface.BOLD);
        card.addView(tvHeader);

        TextView tvBody = new TextView(ctx);
        tvBody.setText(body);
        tvBody.setTextColor(Color.parseColor("#1E293B"));
        tvBody.setTextSize(13.5f);
        tvBody.setPadding(0, 6, 0, 0);
        tvBody.setLineSpacing(6f, 1f);
        card.addView(tvBody);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 18);
        parent.addView(card, lp);
    }
}
