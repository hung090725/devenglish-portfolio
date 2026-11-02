package hung.edu.apptienganh.ui.lessons;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.entity.DayProgress;
import hung.edu.apptienganh.data.local.entity.LearningDay;
import hung.edu.apptienganh.data.local.entity.Lesson;
import hung.edu.apptienganh.data.repository.AppRepository;
import hung.edu.apptienganh.domain.model.LevelConstants;
import hung.edu.apptienganh.ui.lessondetail.LessonDayActivity;
import hung.edu.apptienganh.ui.lessondetail.LessonDetailActivity;

public class LessonsFragment extends Fragment implements LessonAdapter.OnLessonClickListener, LearningDayAdapter.OnDayClickListener {

    private Button chipA0, chipA1, chipA2, chipB1, chipB2, chipC1;
    private RecyclerView rvLessons, rvLearningDays;
    private TextView txtEmpty, txtDayProgress, txtPercent, txtTimelineTitle, txtSupplementTitle;
    private TextView txtPathTitle, txtLockedMessage;
    private ProgressBar progressPath;
    private Button btnContinueDay;
    private CardView cardLearningPath;
    private LessonAdapter lessonAdapter;
    private LearningDayAdapter dayAdapter;
    private AppRepository repository;
    private String currentSelectedLevel = LevelConstants.A0;

    private List<LearningDay> learningDays = new ArrayList<>();
    private Map<Integer, DayProgress> progressMap = new HashMap<>();
    private int continueDayIndex = 1;
    private boolean a0Completed = false;
    private boolean a1Unlocked = false;
    private boolean a2Unlocked = false;
    private boolean b1Unlocked = false;

    private LiveData<List<Lesson>> lessonsLiveData;
    private LiveData<List<LearningDay>> learningDaysLiveData;
    private LiveData<List<DayProgress>> dayProgressLiveData;

    private final Observer<List<Lesson>> lessonsObserver = lessons -> {
        if (!LevelConstants.A0.equals(currentSelectedLevel) && !LevelConstants.A1.equals(currentSelectedLevel)) {
            if (lessons != null && !lessons.isEmpty()) {
                rvLessons.setVisibility(View.VISIBLE);
                txtEmpty.setVisibility(View.GONE);
                lessonAdapter.setLessons(lessons);
            } else {
                rvLessons.setVisibility(View.GONE);
                txtEmpty.setVisibility(View.VISIBLE);
                lessonAdapter.setLessons(new ArrayList<>());
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lessons, container, false);

        chipA0 = view.findViewById(R.id.chip_a0);
        chipA1 = view.findViewById(R.id.chip_a1);
        chipA2 = view.findViewById(R.id.chip_a2);
        chipB1 = view.findViewById(R.id.chip_b1);
        chipB2 = view.findViewById(R.id.chip_b2);
        chipC1 = view.findViewById(R.id.chip_c1);
        rvLessons = view.findViewById(R.id.rv_lessons);
        rvLearningDays = view.findViewById(R.id.rv_learning_days);
        txtEmpty = view.findViewById(R.id.txt_empty_state);
        cardLearningPath = view.findViewById(R.id.card_a0_path);
        txtDayProgress = view.findViewById(R.id.txt_a0_day_progress);
        txtPercent = view.findViewById(R.id.txt_a0_percent);
        progressPath = view.findViewById(R.id.progress_a0);
        btnContinueDay = view.findViewById(R.id.btn_continue_day);
        txtTimelineTitle = view.findViewById(R.id.txt_timeline_title);
        txtSupplementTitle = view.findViewById(R.id.txt_supplement_title);
        txtPathTitle = view.findViewById(R.id.txt_path_title);
        txtLockedMessage = view.findViewById(R.id.txt_locked_message);

        repository = new AppRepository(requireContext());

        setupRecyclerViews();
        setupChips();
        refreshUnlockState();
        selectLevel(LevelConstants.A0);

        btnContinueDay.setOnClickListener(v -> openDay(currentSelectedLevel, continueDayIndex));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUnlockState();
        updatePathUi();
    }

    private void setupRecyclerViews() {
        rvLessons.setLayoutManager(new LinearLayoutManager(getContext()));
        lessonAdapter = new LessonAdapter(this);
        rvLessons.setAdapter(lessonAdapter);

        rvLearningDays.setLayoutManager(new LinearLayoutManager(getContext()));
        dayAdapter = new LearningDayAdapter(this);
        rvLearningDays.setAdapter(dayAdapter);
    }

    private void refreshUnlockState() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            boolean completed = repository.isA0CompletedSync();
            boolean unlocked = repository.isA1UnlockedSync();
            boolean a2 = repository.isA2UnlockedSync();
            boolean b1 = repository.isB1UnlockedSync();
            if (getActivity() == null) return;
            requireActivity().runOnUiThread(() -> {
                if (!isAdded()) return;
                a0Completed = completed;
                a1Unlocked = unlocked;
                a2Unlocked = a2;
                b1Unlocked = b1;
                updatePathUi();
            });
        });
    }

    private void observeLearningPath(String level) {
        if (learningDaysLiveData != null) {
            learningDaysLiveData.removeObservers(getViewLifecycleOwner());
        }
        if (dayProgressLiveData != null) {
            dayProgressLiveData.removeObservers(getViewLifecycleOwner());
        }

        learningDaysLiveData = repository.getLearningDays(level);
        learningDaysLiveData.observe(getViewLifecycleOwner(), days -> {
            if (days != null) {
                learningDays = days;
                dayAdapter.setDays(days, new ArrayList<>(progressMap.values()));
                updateContinueDay(level);
            }
        });

        dayProgressLiveData = repository.getDayProgressList(level);
        dayProgressLiveData.observe(getViewLifecycleOwner(), progressList -> {
            progressMap.clear();
            if (progressList != null) {
                int completed = 0;
                for (DayProgress p : progressList) {
                    progressMap.put(p.getDayIndex(), p);
                    if (p.isCompleted()) completed++;
                }
                int total = LevelConstants.A0.equals(level)
                        ? LevelConstants.A0_DAY_COUNT : LevelConstants.A1_DAY_COUNT;
                int percent = total > 0 ? (int) Math.round(((double) completed / total) * 100) : 0;
                progressPath.setProgress(percent);
                txtPercent.setText("Tiến độ: " + percent + "% (" + completed + "/" + total + ")");
                dayAdapter.setDays(learningDays, progressList);
                updateContinueDay(level);
            }
            updatePathUi();
        });
    }

    private void updateContinueDay(String level) {
        repository.resolveContinueDay(level, (dayIndex, sectionIndex) -> {
            if (getActivity() == null) return;
            requireActivity().runOnUiThread(() -> {
                if (!isAdded()) return;
                continueDayIndex = dayIndex;
                int globalDay = LevelConstants.globalDayNumber(level, dayIndex);
                int totalGlobal;
                if (LevelConstants.B1.equals(level)) {
                    totalGlobal = 56;
                } else if (LevelConstants.A2.equals(level)) {
                    totalGlobal = 42;
                } else if (LevelConstants.A1.equals(level)) {
                    totalGlobal = 28;
                } else {
                    totalGlobal = 14;
                }
                txtDayProgress.setText("Bạn đang ở ngày " + globalDay + "/" + totalGlobal);
                btnContinueDay.setText("Continue Day " + globalDay);
            });
        });
    }

    private void updatePathUi() {
        if (txtPathTitle == null) return;

        boolean isLearningPath = LevelConstants.A0.equals(currentSelectedLevel)
                || LevelConstants.A1.equals(currentSelectedLevel)
                || LevelConstants.A2.equals(currentSelectedLevel)
                || LevelConstants.B1.equals(currentSelectedLevel);
        cardLearningPath.setVisibility(isLearningPath ? View.VISIBLE : View.GONE);
        rvLearningDays.setVisibility(isLearningPath ? View.VISIBLE : View.GONE);
        txtTimelineTitle.setVisibility(isLearningPath ? View.VISIBLE : View.GONE);
        txtSupplementTitle.setVisibility(isLearningPath ? View.VISIBLE : View.GONE);

        if (!isLearningPath) {
            return;
        }

        if (LevelConstants.A0.equals(currentSelectedLevel)) {
            txtPathTitle.setText("Khóa A0 – 14 Ngày");
            txtTimelineTitle.setText("Timeline A0 (Ngày 1–14)");
            txtLockedMessage.setVisibility(View.GONE);
            btnContinueDay.setEnabled(true);
        } else if (LevelConstants.A1.equals(currentSelectedLevel)) {
            txtPathTitle.setText("Khóa A1 – 14 Ngày (Ngày 15–28)");
            txtTimelineTitle.setText("Timeline A1 (Ngày 15–28)");
            if (!a1Unlocked) {
                txtLockedMessage.setVisibility(View.VISIBLE);
                txtLockedMessage.setText("Hoàn thành A0 ngày 14 để mở khóa A1.");
                btnContinueDay.setEnabled(false);
            } else {
                txtLockedMessage.setVisibility(View.GONE);
                btnContinueDay.setEnabled(true);
            }
        } else if (LevelConstants.A2.equals(currentSelectedLevel)) {
            // A2
            txtPathTitle.setText("Khóa A2 – 14 Ngày (Ngày 29–42)");
            txtTimelineTitle.setText("Timeline A2 (Ngày 29–42)");
            if (!a2Unlocked) {
                txtLockedMessage.setVisibility(View.VISIBLE);
                txtLockedMessage.setText("Hoàn thành A1 ngày 28 để mở khóa A2.");
                btnContinueDay.setEnabled(false);
            } else {
                txtLockedMessage.setVisibility(View.GONE);
                btnContinueDay.setEnabled(true);
            }
        } else if (LevelConstants.B1.equals(currentSelectedLevel)) {
            // B1
            txtPathTitle.setText("Khóa B1 – 14 Ngày (Ngày 43–56)");
            txtTimelineTitle.setText("Timeline B1 (Ngày 43–56)");
            if (!b1Unlocked) {
                txtLockedMessage.setVisibility(View.VISIBLE);
                txtLockedMessage.setText("Hoàn thành A2 ngày 42 để mở khóa B1.");
                btnContinueDay.setEnabled(false);
            } else {
                txtLockedMessage.setVisibility(View.GONE);
                btnContinueDay.setEnabled(true);
            }
        }
    }

    private void setupChips() {
        chipA0.setOnClickListener(v -> selectLevel(LevelConstants.A0));
        chipA1.setOnClickListener(v -> selectLevel(LevelConstants.A1));
        chipA2.setOnClickListener(v -> selectLevel(LevelConstants.A2));
        chipB1.setOnClickListener(v -> selectLevel("B1"));
        chipB2.setOnClickListener(v -> selectLevel("B2"));
        chipC1.setOnClickListener(v -> selectLevel("C1"));
    }

    private void selectLevel(String level) {
        currentSelectedLevel = level;
        updateChipStyles();

        boolean isLearningPath = LevelConstants.A0.equals(level)
                || LevelConstants.A1.equals(level)
                || LevelConstants.A2.equals(level)
                || LevelConstants.B1.equals(level);

        if (lessonsLiveData != null) {
            lessonsLiveData.removeObserver(lessonsObserver);
        }
        lessonsLiveData = repository.getLessonsByLevel(level);
        lessonsLiveData.observe(getViewLifecycleOwner(), lessonsObserver);

        if (isLearningPath) {
            observeLearningPath(level);
            rvLessons.setVisibility(View.VISIBLE);
            txtEmpty.setVisibility(View.GONE);
        } else {
            if (learningDaysLiveData != null) {
                learningDaysLiveData.removeObservers(getViewLifecycleOwner());
            }
            if (dayProgressLiveData != null) {
                dayProgressLiveData.removeObservers(getViewLifecycleOwner());
            }
        }
        updatePathUi();
    }

    private void updateChipStyles() {
        resetChip(chipA0);
        resetChip(chipA1);
        resetChip(chipA2);
        resetChip(chipB1);
        resetChip(chipB2);
        resetChip(chipC1);

        switch (currentSelectedLevel) {
            case "A0": highlightChip(chipA0); break;
            case "A1": highlightChip(chipA1); break;
            case "A2": highlightChip(chipA2); break;
            case "B1": highlightChip(chipB1); break;
            case "B2": highlightChip(chipB2); break;
            case "C1": highlightChip(chipC1); break;
        }
    }

    private void resetChip(Button chip) {
        chip.setBackgroundResource(R.drawable.card_rounded_bg);
        chip.setBackgroundTintList(null);
        boolean isDarkMode = (getResources().getConfiguration().uiMode &
                android.content.res.Configuration.UI_MODE_NIGHT_MASK) ==
                android.content.res.Configuration.UI_MODE_NIGHT_YES;
        chip.setTextColor(isDarkMode ? Color.parseColor("#94A3B8") : Color.parseColor("#64748B"));
    }

    private void highlightChip(Button chip) {
        chip.setBackgroundResource(R.drawable.button_gradient);
        chip.setBackgroundTintList(null);
        chip.setTextColor(Color.WHITE);
    }

    private void openDay(String level, int dayIndex) {
        if (LevelConstants.A1.equals(level) && !a1Unlocked) {
            Toast.makeText(getContext(), "Hoàn thành A0 ngày 14 để mở khóa A1.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (LevelConstants.A2.equals(level) && !a2Unlocked) {
            Toast.makeText(getContext(), "Hoàn thành A1 ngày 28 để mở khóa A2.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (LevelConstants.B1.equals(level) && !b1Unlocked) {
            Toast.makeText(getContext(), "Hoàn thành A2 ngày 42 để mở khóa B1.", Toast.LENGTH_SHORT).show();
            return;
        }
        int sectionIndex = 0;
        DayProgress p = progressMap.get(dayIndex);
        if (p != null && p.getStatus() != null && "IN_PROGRESS".equals(p.getStatus())) {
            sectionIndex = p.getCurrentSectionIndex();
        }
        Intent intent = new Intent(getActivity(), LessonDayActivity.class);
        intent.putExtra("day_index", dayIndex);
        intent.putExtra("section_index", sectionIndex);
        intent.putExtra("level", level);
        startActivity(intent);
    }

    @Override
    public void onLessonClick(Lesson lesson) {
        Intent intent = new Intent(getActivity(), LessonDetailActivity.class);
        intent.putExtra("lesson_id", lesson.getId());
        startActivity(intent);
    }

    @Override
    public void onDayClick(LearningDay day, boolean unlocked) {
        if (LevelConstants.A1.equals(day.getLevel()) && !a1Unlocked) {
            Toast.makeText(getContext(), "Hoàn thành A0 ngày 14 để mở khóa A1.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (LevelConstants.A2.equals(day.getLevel()) && !a2Unlocked) {
            Toast.makeText(getContext(), "Hoàn thành A1 ngày 28 để mở khóa A2.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!unlocked) {
            Toast.makeText(getContext(), "Hoàn thành ngày trước để mở khóa ngày này.", Toast.LENGTH_SHORT).show();
            return;
        }
        openDay(day.getLevel(), day.getDayIndex());
    }
}
