package hung.edu.apptienganh.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.entity.Lesson;
import hung.edu.apptienganh.data.local.entity.UserProfile;
import hung.edu.apptienganh.data.repository.AppRepository;
import hung.edu.apptienganh.domain.model.LevelConstants;
import hung.edu.apptienganh.ui.lessondetail.LessonDayActivity;
import hung.edu.apptienganh.ui.lessondetail.LessonDetailActivity;
import hung.edu.apptienganh.ui.lessons.LessonAdapter;
import hung.edu.apptienganh.ui.speaking.SpeakingPracticeActivity;
import hung.edu.apptienganh.ui.vocabulary.VocabularyReviewActivity;

public class HomeFragment extends Fragment implements LessonAdapter.OnLessonClickListener {

    private TextView txtWelcome, txtLevel, txtStreak, txtProgressTitle;
    private TextView txtContinueHint, txtReviewDue, txtActiveLevel;
    private ProgressBar progressBar;
    private RecyclerView rvRecommended;
    private TextView txtEmptyLessons;
    private CardView btnContinue, btnPractice, btnReview;

    private AppRepository repository;
    private LessonAdapter adapter;
    private String continueLevel = LevelConstants.A0;
    private int continueDayIndex = 1;
    private int continueSectionIndex = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        txtWelcome = view.findViewById(R.id.txt_welcome);
        txtLevel = view.findViewById(R.id.txt_level);
        txtStreak = view.findViewById(R.id.txt_streak);
        txtProgressTitle = view.findViewById(R.id.txt_progress_title);
        txtContinueHint = view.findViewById(R.id.txt_continue_hint);
        txtReviewDue = view.findViewById(R.id.txt_review_due);
        txtActiveLevel = view.findViewById(R.id.txt_active_level);
        progressBar = view.findViewById(R.id.progress_bar);
        rvRecommended = view.findViewById(R.id.rv_recommended_lessons);
        txtEmptyLessons = view.findViewById(R.id.txt_empty_lessons);
        btnContinue = view.findViewById(R.id.btn_continue_learning);
        btnPractice = view.findViewById(R.id.btn_practice_speaking);
        btnReview = view.findViewById(R.id.btn_review_vocab);

        repository = new AppRepository(requireContext());

        setupRecyclerView();
        observeData();
        setupClickListeners();

        View btnHomeAiChat = view != null ? view.findViewById(R.id.btn_home_ai_chat) : null;
        if (btnHomeAiChat != null) {
            btnHomeAiChat.setOnClickListener(v -> startActivity(new Intent(getActivity(), hung.edu.apptienganh.ai.AiConversationActivity.class)));
        }

        View btnHomeCameraLens = view != null ? view.findViewById(R.id.btn_home_camera_lens) : null;
        if (btnHomeCameraLens != null) {
            btnHomeCameraLens.setOnClickListener(v -> startActivity(new Intent(getActivity(), hung.edu.apptienganh.camera.CameraLensActivity.class)));
        }

        View btnHomeItEnglish = view != null ? view.findViewById(R.id.btn_home_it_english) : null;
        if (btnHomeItEnglish != null) {
            btnHomeItEnglish.setOnClickListener(v -> startActivity(new Intent(getActivity(), hung.edu.apptienganh.ui.itenglish.ItEnglishHubActivity.class)));
        }

        return view;
    }

    private void setupRecyclerView() {
        rvRecommended.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LessonAdapter(this);
        rvRecommended.setAdapter(adapter);
    }

    private void observeData() {
        repository.getUserProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null) {
                txtWelcome.setText("Chào " + profile.getName() + "!");
                txtStreak.setText("🔥 " + profile.getStreak() + " ngày");
                txtLevel.setText(formatLevelLabel(profile.getCurrentLevel()));
            }
        });

        repository.getCompletedDayCount(LevelConstants.A0).observe(getViewLifecycleOwner(), a0Completed -> updateCombinedProgress(
                a0Completed != null ? a0Completed : 0, null, null, null));

        repository.getCompletedDayCount(LevelConstants.A1).observe(getViewLifecycleOwner(), a1Completed -> updateCombinedProgress(
                null, a1Completed != null ? a1Completed : 0, null, null));

        repository.getCompletedDayCount(LevelConstants.A2).observe(getViewLifecycleOwner(), a2Completed -> updateCombinedProgress(
                null, null, a2Completed != null ? a2Completed : 0, null));

        repository.getCompletedDayCount(LevelConstants.B1).observe(getViewLifecycleOwner(), b1Completed -> updateCombinedProgress(
                null, null, null, b1Completed != null ? b1Completed : 0));

        repository.resolveGlobalContinueDay((level, dayIndex, sectionIndex) -> {
            if (getActivity() == null) return;
            requireActivity().runOnUiThread(() -> {
                if (!isAdded()) return;
                continueLevel = level;
                continueDayIndex = dayIndex;
                continueSectionIndex = sectionIndex;
                int globalDay = LevelConstants.globalDayNumber(level, dayIndex);
                txtContinueHint.setText("Tiếp tục " + level + ": Ngày " + globalDay);
                txtActiveLevel.setText("Bạn đang học level " + level);
            });
        });

        repository.getVocabularyDueCount().observe(getViewLifecycleOwner(), count -> {
            int due = count != null ? count : 0;
            txtReviewDue.setText("Hôm nay cần ôn " + due + " từ");
        });

        repository.getAllLessons().observe(getViewLifecycleOwner(), lessons -> {
            if (lessons != null && !lessons.isEmpty()) {
                List<Lesson> recommended = new ArrayList<>();
                for (Lesson lesson : lessons) {
                    if (!lesson.isCompleted() && recommended.size() < 3) {
                        recommended.add(lesson);
                    }
                }
                if (recommended.isEmpty()) {
                    rvRecommended.setVisibility(View.GONE);
                    txtEmptyLessons.setVisibility(View.VISIBLE);
                } else {
                    rvRecommended.setVisibility(View.VISIBLE);
                    txtEmptyLessons.setVisibility(View.GONE);
                    adapter.setLessons(recommended);
                }
            }
        });
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

    private int cachedA0Completed = 0;
    private int cachedA1Completed = 0;
    private int cachedA2Completed = 0;
    private int cachedB1Completed = 0;

    private void updateCombinedProgress(Integer a0, Integer a1, Integer a2, Integer b1) {
        if (a0 != null) cachedA0Completed = a0;
        if (a1 != null) cachedA1Completed = a1;
        if (a2 != null) cachedA2Completed = a2;
        if (b1 != null) cachedB1Completed = b1;
        int totalCompleted = cachedA0Completed + cachedA1Completed + cachedA2Completed + cachedB1Completed;
        int percent = (int) Math.round(((double) totalCompleted / 56.0) * 100.0);
        progressBar.setProgress(percent);
        txtProgressTitle.setText("Tổng lộ trình: " + percent + "% (A0 "
                + cachedA0Completed + "/14, A1 " + cachedA1Completed + "/14, A2 " + cachedA2Completed + "/14, B1 " + cachedB1Completed + "/14)");
    }

    private void setupClickListeners() {
        btnContinue.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LessonDayActivity.class);
            intent.putExtra("day_index", continueDayIndex);
            intent.putExtra("section_index", continueSectionIndex);
            intent.putExtra("level", continueLevel);
            startActivity(intent);
        });

        btnPractice.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SpeakingPracticeActivity.class);
            intent.putExtra("target_sentence", "I usually wake up at six o'clock.");
            startActivity(intent);
        });

        btnReview.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), VocabularyReviewActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onLessonClick(Lesson lesson) {
        Intent intent = new Intent(getActivity(), LessonDetailActivity.class);
        intent.putExtra("lesson_id", lesson.getId());
        startActivity(intent);
    }
}
