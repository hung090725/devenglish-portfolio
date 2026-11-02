package hung.edu.apptienganh.ui.profile;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.repository.AppRepository;
import hung.edu.apptienganh.domain.model.LevelConstants;
import hung.edu.apptienganh.ui.qa.SoloQaActivity;
import hung.edu.apptienganh.reminder.ReminderPreferences;
import hung.edu.apptienganh.ui.settings.ReminderSettingsActivity;

public class ProfileFragment extends Fragment {

    private TextView txtUserName, txtUserLevel, txtCompletedLessons, txtLearnedWords;
    private TextView txtStreakDays, txtSpeakingAvg, txtA0Progress, txtA1Progress, txtA2Progress, txtB1Progress;
    private TextView txtWordsToReview, txtReminderStatus;
    private TextView txtQuizBest, txtSpeakingAttempts, txtLowestSpeaking;
    private Button btnReset, btnReminderSettings;
    private AppRepository repository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txtUserName = view.findViewById(R.id.txt_user_name);
        txtUserLevel = view.findViewById(R.id.txt_user_level);
        txtCompletedLessons = view.findViewById(R.id.txt_completed_lessons);
        txtLearnedWords = view.findViewById(R.id.txt_learned_words);
        txtStreakDays = view.findViewById(R.id.txt_streak_days);
        txtSpeakingAvg = view.findViewById(R.id.txt_speaking_avg);
        txtA0Progress = view.findViewById(R.id.txt_a0_progress);
        txtA1Progress = view.findViewById(R.id.txt_a1_progress);
        txtA2Progress = view.findViewById(R.id.txt_a2_progress);
        txtB1Progress = view.findViewById(R.id.txt_b1_progress);
        txtWordsToReview = view.findViewById(R.id.txt_words_to_review);
        txtReminderStatus = view.findViewById(R.id.txt_reminder_status);
        txtQuizBest = view.findViewById(R.id.txt_quiz_best);
        txtSpeakingAttempts = view.findViewById(R.id.txt_speaking_attempts);
        txtLowestSpeaking = view.findViewById(R.id.txt_lowest_speaking);
        btnReset = view.findViewById(R.id.btn_reset);
        btnReminderSettings = view.findViewById(R.id.btn_reminder_settings);
        Button btnBackupRestore = view.findViewById(R.id.btn_backup_restore);

        repository = new AppRepository(requireContext());

        observeData();
        setupResetButton();
        btnReminderSettings.setOnClickListener(v ->
                startActivity(new Intent(requireContext(), ReminderSettingsActivity.class)));
        btnBackupRestore.setOnClickListener(v ->
                startActivity(new Intent(requireContext(), hung.edu.apptienganh.ui.backup.BackupRestoreActivity.class)));

        Button btnDiagnostics = view.findViewById(R.id.btn_diagnostics);
        Button btnBetaFeedback = view.findViewById(R.id.btn_beta_feedback);
        TextView txtAppVersion = view.findViewById(R.id.txt_app_version);

        try {
            android.content.pm.PackageInfo pInfo = requireContext().getPackageManager().getPackageInfo(requireContext().getPackageName(), 0);
            txtAppVersion.setText("Version " + pInfo.versionName);
        } catch (Exception e) {
            txtAppVersion.setText("Version 0.7.0-beta");
        }

        boolean isDebuggable = (0 != (requireContext().getApplicationInfo().flags & android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE));
        if (isDebuggable) {
            btnDiagnostics.setVisibility(View.VISIBLE);
            btnDiagnostics.setText("Mở QA Test Mode");
            btnDiagnostics.setOnClickListener(v -> {
                startActivity(new Intent(requireContext(), hung.edu.apptienganh.ui.qa.SoloQaActivity.class));
            });

            btnBetaFeedback.setVisibility(View.VISIBLE);
            btnBetaFeedback.setOnClickListener(v -> {
                startActivity(new Intent(requireContext(), hung.edu.apptienganh.ui.feedback.BetaFeedbackActivity.class));
            });
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateReminderStatus();
    }

    private void observeData() {
        repository.getUserProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null) {
                txtUserName.setText(profile.getName());
                txtStreakDays.setText(String.valueOf(profile.getStreak()));
                txtUserLevel.setText("Trình độ: " + formatLevelLabel(profile.getCurrentLevel()));
            }
        });

        repository.getCompletedDayCount(LevelConstants.A0).observe(getViewLifecycleOwner(), count -> {
            int completed = count != null ? count : 0;
            txtCompletedLessons.setText("A0: " + completed + "/14");
            int percent = (int) Math.round(((double) completed / LevelConstants.A0_DAY_COUNT) * 100.0);
            txtA0Progress.setText("Tiến độ A0: " + percent + "% (" + completed + "/14)");
        });

        repository.getCompletedDayCount(LevelConstants.A1).observe(getViewLifecycleOwner(), count -> {
            int completed = count != null ? count : 0;
            int percent = (int) Math.round(((double) completed / LevelConstants.A1_DAY_COUNT) * 100.0);
            txtA1Progress.setText("Tiến độ A1: " + percent + "% (" + completed + "/14)");
        });

        repository.getCompletedDayCount(LevelConstants.A2).observe(getViewLifecycleOwner(), count -> {
            int completed = count != null ? count : 0;
            int total = 14;
            int percent = (int) Math.round(((double) completed / total) * 100.0);
            if (txtA2Progress != null) {
                txtA2Progress.setText("Tiến độ A2: " + percent + "% (" + completed + "/" + total + ")");
            }
        });

        repository.getCompletedDayCount(LevelConstants.B1).observe(getViewLifecycleOwner(), count -> {
            int completed = count != null ? count : 0;
            int total = 14;
            int percent = (int) Math.round(((double) completed / total) * 100.0);
            if (txtB1Progress != null) {
                txtB1Progress.setText("Tiến độ B1: " + percent + "% (" + completed + "/" + total + ")");
            }
        });

        repository.getLearnedVocabularyCount().observe(getViewLifecycleOwner(), count -> {
            if (count != null) {
                txtLearnedWords.setText(String.valueOf(count));
            }
        });

        repository.getVocabularyDueCount().observe(getViewLifecycleOwner(), count -> {
            int due = count != null ? count : 0;
            txtWordsToReview.setText("Từ cần ôn: " + due);
        });

        repository.getAverageSpeakingScore().observe(getViewLifecycleOwner(), avgScore -> {
            if (avgScore != null) {
                int scoreInt = (int) Math.round(avgScore);
                txtSpeakingAvg.setText(scoreInt + "%");
            } else {
                txtSpeakingAvg.setText("0%");
            }
        });

        repository.getSpeakingAttemptCount().observe(getViewLifecycleOwner(), count -> {
            txtSpeakingAttempts.setText("Lượt luyện nói: " + (count != null ? count : 0));
        });

        repository.getBestQuizScore().observe(getViewLifecycleOwner(), best -> {
            if (best != null && best > 0) {
                txtQuizBest.setText("Quiz điểm cao nhất: " + best + "%");
            } else {
                txtQuizBest.setText("Quiz điểm cao nhất: —");
            }
        });

        repository.getLowestRecentSpeakingResult().observe(getViewLifecycleOwner(), result -> {
            if (result != null) {
                String sentence = result.getTargetSentence();
                if (sentence == null || sentence.isEmpty()) {
                    sentence = "(không có câu)";
                }
                txtLowestSpeaking.setText("Câu điểm thấp gần đây (" + result.getScore() + "%): " + sentence);
            } else {
                txtLowestSpeaking.setText("Câu điểm thấp gần đây: —");
            }
        });

        updateReminderStatus();
    }

    private void updateReminderStatus() {
        if (txtReminderStatus == null || getContext() == null) return;
        if (ReminderPreferences.isReminderEnabled(getContext())) {
            txtReminderStatus.setText("Nhắc học: Bật lúc " + ReminderPreferences.formatTime(getContext()));
        } else {
            txtReminderStatus.setText("Nhắc học: Tắt");
        }
    }

    private String formatLevelLabel(String level) {
        switch (level) {
            case "A0": return "Mất gốc (A0)";
            case "A1": return "Cơ bản (A1)";
            case "A2": return "Tiền Trung cấp (A2)";
            case "B1": return "Giao tiếp (B1)";
            case "C1": return "Nâng cao (C1)";
            default: return level;
        }
    }

    private void setupResetButton() {
        btnReset.setOnClickListener(v -> {
            new AlertDialog.Builder(getContext())
                    .setTitle("Xóa tiến độ học tập")
                    .setMessage("Bạn có chắc chắn muốn xóa toàn bộ tiến trình học tập, từ vựng đã thuộc và lịch sử luyện nói không? Hành động này không thể hoàn tác.")
                    .setPositiveButton("Xóa hết", (dialog, which) -> {
                        repository.resetProgress();
                        Toast.makeText(getContext(), "Đã đặt lại toàn bộ tiến độ học tập!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });
    }
}
