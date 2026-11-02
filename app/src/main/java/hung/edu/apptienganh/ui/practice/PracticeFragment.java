package hung.edu.apptienganh.ui.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.ai.AiConversationActivity;
import hung.edu.apptienganh.camera.CameraLensActivity;

import hung.edu.apptienganh.ui.dictionary.DictionaryProActivity;
import hung.edu.apptienganh.ui.errornotebook.ErrorNotebookActivity;
import hung.edu.apptienganh.ui.exam.ExamSimulatorActivity;
import hung.edu.apptienganh.ui.flashcard.FlashcardActivity;
import hung.edu.apptienganh.ui.grammar.GrammarLabActivity;
import hung.edu.apptienganh.ui.listening.ListeningTrainerActivity;
import hung.edu.apptienganh.ui.pronunciation.PronunciationClinicActivity;
import hung.edu.apptienganh.ui.reading.ReadingTrainerActivity;
import hung.edu.apptienganh.ui.review.ReviewCenterActivity;
import hung.edu.apptienganh.ui.sentence.SentenceBuilderActivity;
import hung.edu.apptienganh.ui.speaking.SpeakingPracticeActivity;
import hung.edu.apptienganh.ui.writing.WritingTrainerActivity;

public class PracticeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_practice, container, false);
        setupClicks(view);
        return view;
    }

    private void setupClicks(View view) {
        // AI Conversation Partner Banner
        View cardAiChatBanner = view.findViewById(R.id.cardAiChatBanner);
        if (cardAiChatBanner != null) {
            cardAiChatBanner.setOnClickListener(v -> startActivity(new Intent(requireContext(), AiConversationActivity.class)));
        }

        // DEVENGLISH AI Banner (English for IT & Developers)
        View cardItEnglishBanner = view.findViewById(R.id.cardItEnglishBanner);
        if (cardItEnglishBanner != null) {
            cardItEnglishBanner.setOnClickListener(v -> startActivity(new Intent(requireContext(), hung.edu.apptienganh.ui.itenglish.ItEnglishHubActivity.class)));
        }

        // 4 Core Skills
        View cardSpeaking = view.findViewById(R.id.cardSpeaking);
        if (cardSpeaking != null) {
            cardSpeaking.setOnClickListener(v -> startActivity(new Intent(requireContext(), SpeakingPracticeActivity.class)));
        }

        View cardListening = view.findViewById(R.id.cardListening);
        if (cardListening != null) {
            cardListening.setOnClickListener(v -> startActivity(new Intent(requireContext(), ListeningTrainerActivity.class)));
        }

        View cardWriting = view.findViewById(R.id.cardWriting);
        if (cardWriting != null) {
            cardWriting.setOnClickListener(v -> startActivity(new Intent(requireContext(), WritingTrainerActivity.class)));
        }

        View cardReading = view.findViewById(R.id.cardReading);
        if (cardReading != null) {
            cardReading.setOnClickListener(v -> startActivity(new Intent(requireContext(), ReadingTrainerActivity.class)));
        }

        // Language Tools
        View cardPronunciation = view.findViewById(R.id.cardPronunciation);
        if (cardPronunciation != null) {
            cardPronunciation.setOnClickListener(v -> startActivity(new Intent(requireContext(), PronunciationClinicActivity.class)));
        }

        View cardGrammarLab = view.findViewById(R.id.cardGrammarLab);
        if (cardGrammarLab != null) {
            cardGrammarLab.setOnClickListener(v -> startActivity(new Intent(requireContext(), GrammarLabActivity.class)));
        }

        View cardDictionary = view.findViewById(R.id.cardDictionary);
        if (cardDictionary != null) {
            cardDictionary.setOnClickListener(v -> startActivity(new Intent(requireContext(), DictionaryProActivity.class)));
        }

        View cardSentenceBuilder = view.findViewById(R.id.cardSentenceBuilder);
        if (cardSentenceBuilder != null) {
            cardSentenceBuilder.setOnClickListener(v -> startActivity(new Intent(requireContext(), SentenceBuilderActivity.class)));
        }

        // Review & Assessment
        View cardReviewCenter = view.findViewById(R.id.cardReviewCenter);
        if (cardReviewCenter != null) {
            cardReviewCenter.setOnClickListener(v -> startActivity(new Intent(requireContext(), ReviewCenterActivity.class)));
        }

        View cardErrorNotebook = view.findViewById(R.id.cardErrorNotebook);
        if (cardErrorNotebook != null) {
            cardErrorNotebook.setOnClickListener(v -> startActivity(new Intent(requireContext(), ErrorNotebookActivity.class)));
        }

        View cardFlashcards = view.findViewById(R.id.cardFlashcards);
        if (cardFlashcards != null) {
            cardFlashcards.setOnClickListener(v -> startActivity(new Intent(requireContext(), FlashcardActivity.class)));
        }

        View cardExamSimulator = view.findViewById(R.id.cardExamSimulator);
        if (cardExamSimulator != null) {
            cardExamSimulator.setOnClickListener(v -> startActivity(new Intent(requireContext(), ExamSimulatorActivity.class)));
        }
    }
}
