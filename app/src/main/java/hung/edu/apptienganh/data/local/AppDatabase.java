package hung.edu.apptienganh.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hung.edu.apptienganh.data.local.dao.BetaFeedbackDao;
import hung.edu.apptienganh.data.local.entity.BetaFeedback;
import hung.edu.apptienganh.data.local.dao.ContentSeedMetaDao;
import hung.edu.apptienganh.data.local.entity.*;
import hung.edu.apptienganh.data.local.dao.*;
import hung.edu.apptienganh.domain.model.DaySection;
import hung.edu.apptienganh.data.local.migration.DatabaseMigrations;
import hung.edu.apptienganh.data.seed.SeedDataProvider;

@Database(entities = {
        UserProfile.class,
        Lesson.class,
        Vocabulary.class,
        GrammarPoint.class,
        Conversation.class,
        ConversationLine.class,
        QuizQuestion.class,
        SpeakingResult.class,
        LearningDay.class,
        DayProgress.class,
        VocabularyReviewProgress.class,
        QuizAttempt.class,
        QuizAnswer.class,
        ContentSeedMeta.class,
        BetaFeedback.class,
        RealDeviceTestCase.class,
        Achievement.class,
        PlacementResult.class,
        ListeningPracticeResult.class,
        GrammarMistakeRecord.class,
        QuizWrongAnswerReview.class,
        SpeakingWeakSentence.class,
        FavoriteVocabulary.class,
        LearningInsight.class,
        WeaknessItem.class,
        WritingPrompt.class,
        WritingAttempt.class,
        SentenceBuilderExercise.class,
        SentenceBuilderResult.class,
        ErrorNote.class,
        RecentWordView.class,
        GrammarPracticeExercise.class,
        GrammarPracticeResult.class,
                LearningEvent.class,
        ExamSession.class,
        ExamQuestion.class,
        ExamResult.class,
        LearningGoal.class,
        GoalProgress.class,
        ContentIssue.class,
        ContentAuditReport.class,
        PronunciationDrill.class,
        MinimalPairExercise.class,
        PronunciationAttempt.class,
        FlashcardResult.class,
        WordGameSession.class,
        WordGameResult.class,
        ReadingPassage.class,
        ReadingQuestion.class,
        ReadingResult.class,
        WritingTemplate.class,
        WritingChecklistItem.class,
        StudyDaySummary.class,
        StudySession.class
}, version = 9, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static final int DEFAULT_USER_ID = 1;
    public static final int DATABASE_VERSION = 9;

    public abstract UserProfileDao userProfileDao();
    public abstract LessonDao lessonDao();
    public abstract VocabularyDao vocabularyDao();
    public abstract IntelligenceDao intelligenceDao();
    public abstract WritingDao writingDao();
    public abstract SentenceBuilderDao sentenceBuilderDao();
    public abstract ErrorNotebookDao errorNotebookDao();
    public abstract GrammarPracticeDao grammarPracticeDao();
    public abstract AnalyticsDao analyticsDao();

    // Sprint 19 DAOs
    public abstract ExamDao examDao();
    public abstract GoalDao goalDao();
    public abstract ContentStudioDao contentStudioDao();
    public abstract PronunciationDao pronunciationDao();
    public abstract FlashcardDao flashcardDao();
    public abstract WordGameDao wordGameDao();
    public abstract ReadingDao readingDao();
    public abstract StudyCalendarDao studyCalendarDao();
    public abstract Writing2Dao writing2Dao();

    public abstract GrammarPointDao grammarPointDao();
    public abstract ConversationDao conversationDao();
    public abstract QuizQuestionDao quizQuestionDao();
    public abstract SpeakingResultDao speakingResultDao();
    public abstract LearningDayDao learningDayDao();
    public abstract DayProgressDao dayProgressDao();
    public abstract VocabularyReviewProgressDao vocabularyReviewProgressDao();
    public abstract QuizAttemptDao quizAttemptDao();
    public abstract QuizAnswerDao quizAnswerDao();
    public abstract ContentSeedMetaDao contentSeedMetaDao();
    public abstract BetaFeedbackDao betaFeedbackDao();
    public abstract RealDeviceTestCaseDao realDeviceTestCaseDao();
    public abstract AchievementDao achievementDao();
    public abstract PlacementResultDao placementResultDao();
    public abstract ListeningPracticeResultDao listeningPracticeResultDao();
    public abstract GrammarMistakeRecordDao grammarMistakeRecordDao();
    public abstract QuizWrongAnswerReviewDao quizWrongAnswerReviewDao();
    public abstract SpeakingWeakSentenceDao speakingWeakSentenceDao();
    public abstract FavoriteVocabularyDao favoriteVocabularyDao();

    public static final boolean DEV_DATABASE_RESET_ENABLED = false;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    RoomDatabase.Builder<AppDatabase> builder = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "englishpath_database")
                            .addMigrations(
                                    DatabaseMigrations.MIGRATION_2_3,
                                    DatabaseMigrations.MIGRATION_3_4,
                                    DatabaseMigrations.MIGRATION_4_5,
                                    DatabaseMigrations.MIGRATION_5_6,
                                    DatabaseMigrations.MIGRATION_6_7, DatabaseMigrations.MIGRATION_7_8)
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback);
                            
                    INSTANCE = builder.build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> runInitialSeed(false));
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> runInitialSeed(true));
        }
    };

    private static void runInitialSeed(boolean isOpenOnly) {
        if (INSTANCE == null) {
            return;
        }

        UserProfileDao userProfileDao = INSTANCE.userProfileDao();
        LessonDao lessonDao = INSTANCE.lessonDao();
        VocabularyDao vocabularyDao = INSTANCE.vocabularyDao();
        GrammarPointDao grammarPointDao = INSTANCE.grammarPointDao();
        ConversationDao conversationDao = INSTANCE.conversationDao();
        QuizQuestionDao quizQuestionDao = INSTANCE.quizQuestionDao();
        LearningDayDao learningDayDao = INSTANCE.learningDayDao();
        DayProgressDao dayProgressDao = INSTANCE.dayProgressDao();
        ContentSeedMetaDao contentSeedMetaDao = INSTANCE.contentSeedMetaDao();

        if (!isOpenOnly) {
            UserProfile defaultProfile = new UserProfile("Học Viên", "A0", 15, 0, 0, 0);
            userProfileDao.insert(defaultProfile);

            SeedDataProvider.seedDatabase(
                    lessonDao, vocabularyDao, grammarPointDao, conversationDao,
                    quizQuestionDao, learningDayDao, dayProgressDao);

            seedInitialDayProgress(dayProgressDao, learningDayDao, "A0");

            // Seed A2 on first install (fresh DB)
            SeedDataProvider.seedA2IfNeeded(
                    vocabularyDao, grammarPointDao, conversationDao, quizQuestionDao,
                    learningDayDao, dayProgressDao, contentSeedMetaDao);

            // Seed B1 on first install (fresh DB)
            SeedDataProvider.seedB1IfNeeded(
                    vocabularyDao, grammarPointDao, conversationDao, quizQuestionDao,
                    learningDayDao, dayProgressDao, contentSeedMetaDao);

            // Seed B2 on first install (fresh DB)
            SeedDataProvider.seedB2IfNeeded(
                    vocabularyDao, grammarPointDao, conversationDao, quizQuestionDao,
                    learningDayDao, dayProgressDao, contentSeedMetaDao);

            // Seed C1 on first install (fresh DB)
            SeedDataProvider.seedC1IfNeeded(
                    vocabularyDao, grammarPointDao, conversationDao, quizQuestionDao,
                    learningDayDao, dayProgressDao, contentSeedMetaDao);
        } else {
            SeedDataProvider.seedA1IfNeeded(
                    vocabularyDao, grammarPointDao, conversationDao, quizQuestionDao,
                    learningDayDao, dayProgressDao, contentSeedMetaDao);
            SeedDataProvider.seedA2IfNeeded(
                    vocabularyDao, grammarPointDao, conversationDao, quizQuestionDao,
                    learningDayDao, dayProgressDao, contentSeedMetaDao);
            SeedDataProvider.seedB1IfNeeded(
                    vocabularyDao, grammarPointDao, conversationDao, quizQuestionDao,
                    learningDayDao, dayProgressDao, contentSeedMetaDao);
            SeedDataProvider.seedB2IfNeeded(
                    vocabularyDao, grammarPointDao, conversationDao, quizQuestionDao,
                    learningDayDao, dayProgressDao, contentSeedMetaDao);
            SeedDataProvider.seedC1IfNeeded(
                    vocabularyDao, grammarPointDao, conversationDao, quizQuestionDao,
                    learningDayDao, dayProgressDao, contentSeedMetaDao);
        }
    }


    static void seedInitialDayProgress(DayProgressDao dayProgressDao, LearningDayDao learningDayDao, String level) {
        int totalDays = learningDayDao.getDayCountSync(level);
        if (totalDays <= 0) {
            return;
        }
        long now = System.currentTimeMillis();
        List<DayProgress> progressList = new ArrayList<>();
        for (int day = 1; day <= totalDays; day++) {
            boolean unlocked = day == 1 && "A0".equals(level);
            String status = unlocked ? DaySection.STATUS_AVAILABLE : DaySection.STATUS_LOCKED;
            progressList.add(new DayProgress(
                    DEFAULT_USER_ID, level, day, unlocked, false,
                    0, 0, 0, 0, 0, status, 0, 0, now));
        }
        dayProgressDao.insertAll(progressList);
    }
}
