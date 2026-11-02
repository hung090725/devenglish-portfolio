package hung.edu.apptienganh.ui.itenglish;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.List;
import java.util.Map;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.ui.itenglish.engine.ItEnglishEngine;

/**
 * Workplace Practice Tools: Bug Report Trainer, Git Commit & Standup Simulator.
 */
public class ItPracticeToolsActivity extends AppCompatActivity {

    public static final String EXTRA_INITIAL_TAB = "extra_initial_tab";
    public static final int TAB_BUG_REPORT = 0;
    public static final int TAB_GIT_COMMIT = 1;
    public static final int TAB_STANDUP = 2;

    private Button tabBugReport, tabGitCommit, tabStandup;
    private View viewBugReport, viewGitCommit, viewStandup;

    // Bug report views
    private EditText etBugTitle, etBugSteps, etBugExpected, etBugActual, etBugEnv;
    private Button btnEvaluateBug;

    // Git commit views
    private EditText etGitCommitMsg;
    private Button btnEvaluateGit;

    // Standup views
    private EditText etStandupYesterday, etStandupToday, etStandupBlockers;
    private Button btnEvaluateStandup;

    // Result views
    private CardView cardToolResult;
    private TextView tvToolResultTitle, tvToolScore, tvToolFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it_practice_tools);

        initViews();
        setupListeners();

        int initialTab = getIntent().getIntExtra(EXTRA_INITIAL_TAB, TAB_BUG_REPORT);
        switchTab(initialTab);
    }

    private void initViews() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        tabBugReport = findViewById(R.id.tabBugReport);
        tabGitCommit = findViewById(R.id.tabGitCommit);
        tabStandup = findViewById(R.id.tabStandup);

        viewBugReport = findViewById(R.id.viewBugReport);
        viewGitCommit = findViewById(R.id.viewGitCommit);
        viewStandup = findViewById(R.id.viewStandup);

        // Bug report
        etBugTitle = findViewById(R.id.etBugTitle);
        etBugSteps = findViewById(R.id.etBugSteps);
        etBugExpected = findViewById(R.id.etBugExpected);
        etBugActual = findViewById(R.id.etBugActual);
        etBugEnv = findViewById(R.id.etBugEnv);
        btnEvaluateBug = findViewById(R.id.btnEvaluateBug);

        // Git commit
        etGitCommitMsg = findViewById(R.id.etGitCommitMsg);
        btnEvaluateGit = findViewById(R.id.btnEvaluateGit);

        // Standup
        etStandupYesterday = findViewById(R.id.etStandupYesterday);
        etStandupToday = findViewById(R.id.etStandupToday);
        etStandupBlockers = findViewById(R.id.etStandupBlockers);
        btnEvaluateStandup = findViewById(R.id.btnEvaluateStandup);

        // Result
        cardToolResult = findViewById(R.id.cardToolResult);
        tvToolResultTitle = findViewById(R.id.tvToolResultTitle);
        tvToolScore = findViewById(R.id.tvToolScore);
        tvToolFeedback = findViewById(R.id.tvToolFeedback);
    }

    private void setupListeners() {
        tabBugReport.setOnClickListener(v -> switchTab(TAB_BUG_REPORT));
        tabGitCommit.setOnClickListener(v -> switchTab(TAB_GIT_COMMIT));
        tabStandup.setOnClickListener(v -> switchTab(TAB_STANDUP));

        btnEvaluateBug.setOnClickListener(v -> evaluateBug());
        btnEvaluateGit.setOnClickListener(v -> evaluateGit());
        btnEvaluateStandup.setOnClickListener(v -> evaluateStandup());
    }

    private void switchTab(int tab) {
        viewBugReport.setVisibility(tab == TAB_BUG_REPORT ? View.VISIBLE : View.GONE);
        viewGitCommit.setVisibility(tab == TAB_GIT_COMMIT ? View.VISIBLE : View.GONE);
        viewStandup.setVisibility(tab == TAB_STANDUP ? View.VISIBLE : View.GONE);

        tabBugReport.setBackgroundColor(tab == TAB_BUG_REPORT ? 0xFFEF4444 : 0xFF334155);
        tabBugReport.setTextColor(tab == TAB_BUG_REPORT ? 0xFFFFFFFF : 0xFF94A3B8);

        tabGitCommit.setBackgroundColor(tab == TAB_GIT_COMMIT ? 0xFF059669 : 0xFF334155);
        tabGitCommit.setTextColor(tab == TAB_GIT_COMMIT ? 0xFFFFFFFF : 0xFF94A3B8);

        tabStandup.setBackgroundColor(tab == TAB_STANDUP ? 0xFF0284C7 : 0xFF334155);
        tabStandup.setTextColor(tab == TAB_STANDUP ? 0xFFFFFFFF : 0xFF94A3B8);

        cardToolResult.setVisibility(View.GONE);
    }

    private void evaluateBug() {
        String title = etBugTitle.getText().toString();
        String steps = etBugSteps.getText().toString();
        String exp = etBugExpected.getText().toString();
        String act = etBugActual.getText().toString();
        String env = etBugEnv.getText().toString();

        if (title.trim().isEmpty() || steps.trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập ít nhất Tiêu đề và Các bước tái hiện lỗi.", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> eval = ItEnglishEngine.getInstance().evaluateBugReport(title, steps, exp, act, env);
        int score = (int) eval.get("score");
        @SuppressWarnings("unchecked")
        List<String> suggestions = (List<String>) eval.get("suggestions");

        cardToolResult.setVisibility(View.VISIBLE);
        tvToolResultTitle.setText("🐛 ĐÁNH GIÁ BUG REPORT THEO CHUẨN JIRA / GITHUB");
        tvToolScore.setText("Điểm chất lượng: " + score + "/100 " + (score >= 70 ? "✅ ĐẠT YÊU CẦU" : "⚠️ CẦN CẢI THIỆN"));
        tvToolScore.setTextColor(score >= 70 ? 0xFF10B981 : 0xFFF59E0B);

        StringBuilder sb = new StringBuilder();
        if (score >= 80) {
            sb.append("🌟 Báo cáo lỗi rất rõ ràng, đầy đủ thông tin tái hiện cho đồng nghiệp và QA tester.\n");
        } else {
            sb.append("💡 Góp ý hoàn thiện báo cáo lỗi chuyên nghiệp:\n");
            for (String s : suggestions) {
                sb.append("• ").append(s).append("\n");
            }
        }
        tvToolFeedback.setText(sb.toString().trim());
    }

    private void evaluateGit() {
        String msg = etGitCommitMsg.getText().toString();
        if (msg.trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập Commit Message.", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> eval = ItEnglishEngine.getInstance().evaluateGitCommit(msg);
        int score = (int) eval.get("score");
        @SuppressWarnings("unchecked")
        List<String> tips = (List<String>) eval.get("tips");

        cardToolResult.setVisibility(View.VISIBLE);
        tvToolResultTitle.setText("🐙 ĐÁNH GIÁ CONVENTIONAL COMMITS");
        tvToolScore.setText("Điểm chuẩn hóa: " + score + "/100 " + (score >= 80 ? "✅ CHUẨN QUỐC TẾ" : "⚠️ CẦN SỬA LẠI"));
        tvToolScore.setTextColor(score >= 80 ? 0xFF10B981 : 0xFFF59E0B);

        StringBuilder sb = new StringBuilder();
        if (score >= 80) {
            sb.append("🌟 Commit message tuân thủ đúng chuẩn Conventional Commits, thể mệnh lệnh và độ dài lý tưởng!\n");
        } else {
            sb.append("💡 Quy chuẩn viết Git Commit chuyên nghiệp:\n");
            for (String t : tips) {
                sb.append("• ").append(t).append("\n");
            }
        }
        tvToolFeedback.setText(sb.toString().trim());
    }

    private void evaluateStandup() {
        String yesterday = etStandupYesterday.getText().toString();
        String today = etStandupToday.getText().toString();
        String blockers = etStandupBlockers.getText().toString();

        if (yesterday.trim().isEmpty() || today.trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập công việc hôm qua và hôm nay.", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> eval = ItEnglishEngine.getInstance().evaluateDailyStandup(yesterday, today, blockers);
        int score = (int) eval.get("score");
        @SuppressWarnings("unchecked")
        List<String> remarks = (List<String>) eval.get("remarks");

        cardToolResult.setVisibility(View.VISIBLE);
        tvToolResultTitle.setText("⏱️ ĐÁNH GIÁ GIAO TIẾP DAILY STANDUP (SCRUM)");
        tvToolScore.setText("Điểm hiệu quả: " + score + "/100 " + (score >= 75 ? "✅ GIAO TIẾP TỐT" : "⚠️ CẦN RÕ RÀNG HƠN"));
        tvToolScore.setTextColor(score >= 75 ? 0xFF10B981 : 0xFFF59E0B);

        StringBuilder sb = new StringBuilder();
        if (score >= 80) {
            sb.append("🌟 Báo cáo Daily Standup ngắn gọn, trọng tâm, đúng ngữ điệu Agile quốc tế!\n");
        } else {
            sb.append("💡 Lưu ý để họp Standup hiệu quả hơn:\n");
            for (String r : remarks) {
                sb.append("• ").append(r).append("\n");
            }
        }
        tvToolFeedback.setText(sb.toString().trim());
    }
}
