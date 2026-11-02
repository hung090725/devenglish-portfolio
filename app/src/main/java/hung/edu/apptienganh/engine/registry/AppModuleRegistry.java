package hung.edu.apptienganh.engine.registry;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.ui.review.ReviewCenterActivity;
import hung.edu.apptienganh.ui.practice.PracticeFragment;
import hung.edu.apptienganh.ui.speaking.SpeakingPracticeActivity;
import hung.edu.apptienganh.ui.listening.ListeningTrainerActivity;
import hung.edu.apptienganh.ui.reading.ReadingTrainerActivity;
import hung.edu.apptienganh.ui.writing.WritingTrainerActivity;
import hung.edu.apptienganh.ui.grammar.GrammarLabActivity;
import hung.edu.apptienganh.ui.dictionary.DictionaryProActivity;
import hung.edu.apptienganh.ui.exam.ExamSimulatorActivity;
import hung.edu.apptienganh.ui.studio.ContentStudioActivity;
import hung.edu.apptienganh.ui.pronunciation.PronunciationClinicActivity;
import hung.edu.apptienganh.ui.flashcard.FlashcardActivity;
import hung.edu.apptienganh.ui.game.WordGameActivity;

public class AppModuleRegistry {
    private static final List<AppModule> MODULES = new ArrayList<>();

    static {
        // REVIEW
        MODULES.add(new AppModule("REV_CENTER", "Review Center", "Ôn tập kiến thức", AppModuleCategory.REVIEW, ReviewCenterActivity.class));
        MODULES.add(new AppModule("FLASHCARDS", "Flashcards", "Học từ vựng", AppModuleCategory.REVIEW, FlashcardActivity.class));
        
        // PRACTICE
        MODULES.add(new AppModule("SPEAKING", "Speaking Trainer", "Luyện nói", AppModuleCategory.PRACTICE, SpeakingPracticeActivity.class));
        MODULES.add(new AppModule("LISTENING", "Listening Trainer", "Luyện nghe", AppModuleCategory.PRACTICE, ListeningTrainerActivity.class));
        MODULES.add(new AppModule("READING", "Reading Trainer", "Luyện đọc hiểu", AppModuleCategory.PRACTICE, ReadingTrainerActivity.class));
        MODULES.add(new AppModule("WRITING", "Writing Trainer", "Luyện viết câu", AppModuleCategory.PRACTICE, WritingTrainerActivity.class));
        MODULES.add(new AppModule("PRONUNCIATION", "Pronunciation Clinic", "Luyện phát âm chuẩn", AppModuleCategory.PRACTICE, PronunciationClinicActivity.class));
        MODULES.add(new AppModule("WORD_GAME", "Word Game", "Trò chơi từ vựng", AppModuleCategory.PRACTICE, WordGameActivity.class));
        
        // TOOLS
        MODULES.add(new AppModule("GRAMMAR", "Grammar Lab", "Từ điển ngữ pháp", AppModuleCategory.TOOLS, GrammarLabActivity.class));
        MODULES.add(new AppModule("DICTIONARY", "Dictionary Pro", "Tra cứu từ vựng", AppModuleCategory.TOOLS, DictionaryProActivity.class));
        
        // ASSESSMENT
        MODULES.add(new AppModule("EXAM", "Exam Simulator", "Thi thử toàn diện", AppModuleCategory.ASSESSMENT, ExamSimulatorActivity.class));
        
        // DEBUG
        MODULES.add(new AppModule("CONTENT_STUDIO", "Content Studio", "Kiểm tra dữ liệu", AppModuleCategory.DEBUG, ContentStudioActivity.class));
    }

    public static List<AppModule> getAllModules() {
        return MODULES;
    }

    public static List<AppModule> getModulesByCategory(AppModuleCategory category) {
        List<AppModule> res = new ArrayList<>();
        for (AppModule m : MODULES) {
            if (m.category == category) {
                res.add(m);
            }
        }
        return res;
    }
}
