package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.dao.ContentSeedMetaDao;
import hung.edu.apptienganh.data.local.dao.ConversationDao;
import hung.edu.apptienganh.data.local.dao.DayProgressDao;
import hung.edu.apptienganh.data.local.dao.GrammarPointDao;
import hung.edu.apptienganh.data.local.dao.LearningDayDao;
import hung.edu.apptienganh.data.local.dao.LessonDao;
import hung.edu.apptienganh.data.local.dao.QuizQuestionDao;
import hung.edu.apptienganh.data.local.dao.VocabularyDao;
import hung.edu.apptienganh.data.local.entity.ContentSeedMeta;
import hung.edu.apptienganh.data.local.entity.DayProgress;
import hung.edu.apptienganh.data.local.entity.LearningDay;
import hung.edu.apptienganh.data.local.entity.Lesson;
import hung.edu.apptienganh.data.local.entity.QuizQuestion;

public class SeedDataProvider {

    public static final String SEED_KEY_A1 = "A1_CONTENT_V1";
    public static final String SEED_KEY_A2 = "A2_CONTENT_V1";
    public static final String SEED_KEY_B1 = "B1_CONTENT_V1";
    public static final String SEED_KEY_B2 = "B2_CONTENT_V1";
    public static final String SEED_KEY_C1 = "C1_CONTENT_V1";

    public static void seedDatabase(
            LessonDao lessonDao,
            VocabularyDao vocabularyDao,
            GrammarPointDao grammarPointDao,
            ConversationDao conversationDao,
            QuizQuestionDao quizQuestionDao,
            LearningDayDao learningDayDao,
            DayProgressDao dayProgressDao) {

        vocabularyDao.insertAll(A0VocabularySeed.buildVocabularyList());
        grammarPointDao.insertAll(A0GrammarSeed.buildGrammarPoints());
        conversationDao.insertAllConversations(A0ConversationSeed.buildConversations());
        conversationDao.insertAllConversationLines(A0ConversationSeed.buildConversationLines());
        learningDayDao.insertAll(A0LearningDaySeed.buildLearningDays());
        seedA0Quizzes(quizQuestionDao, learningDayDao);

        seedA1Content(vocabularyDao, grammarPointDao, conversationDao,
                quizQuestionDao, learningDayDao, dayProgressDao, null);

        lessonDao.insertAll(buildLegacyLessons());
        quizQuestionDao.insertAll(buildLegacyLessonQuizzes());
    }

    /** Idempotent A1 seed for fresh installs and v3→v4 upgrades. */
    public static void seedA1IfNeeded(
            VocabularyDao vocabularyDao,
            GrammarPointDao grammarPointDao,
            ConversationDao conversationDao,
            QuizQuestionDao quizQuestionDao,
            LearningDayDao learningDayDao,
            DayProgressDao dayProgressDao,
            ContentSeedMetaDao contentSeedMetaDao) {

        if (contentSeedMetaDao != null
                && contentSeedMetaDao.countByKeySync(SEED_KEY_A1) > 0) {
            return;
        }
        if (learningDayDao.getDayCountSync("A1") > 0) {
            markSeeded(contentSeedMetaDao, SEED_KEY_A1);
            return;
        }

        seedA1Content(vocabularyDao, grammarPointDao, conversationDao,
                quizQuestionDao, learningDayDao, dayProgressDao, contentSeedMetaDao);
    }

    /** Idempotent A2 seed for existing users who update from v0.7.x to v0.8.0+. */
    public static void seedA2IfNeeded(
            VocabularyDao vocabularyDao,
            GrammarPointDao grammarPointDao,
            ConversationDao conversationDao,
            QuizQuestionDao quizQuestionDao,
            LearningDayDao learningDayDao,
            DayProgressDao dayProgressDao,
            ContentSeedMetaDao contentSeedMetaDao) {

        if (contentSeedMetaDao != null
                && contentSeedMetaDao.countByKeySync(SEED_KEY_A2) > 0) {
            return;
        }
        if (learningDayDao.getDayCountSync("A2") > 0) {
            markSeeded(contentSeedMetaDao, SEED_KEY_A2);
            return;
        }

        seedA2Content(vocabularyDao, grammarPointDao, conversationDao,
                quizQuestionDao, learningDayDao, dayProgressDao, contentSeedMetaDao);
    }

    /** Idempotent B1 seed for users who update to v0.9.0+. */
    public static void seedB1IfNeeded(
            VocabularyDao vocabularyDao,
            GrammarPointDao grammarPointDao,
            ConversationDao conversationDao,
            QuizQuestionDao quizQuestionDao,
            LearningDayDao learningDayDao,
            DayProgressDao dayProgressDao,
            ContentSeedMetaDao contentSeedMetaDao) {

        if (contentSeedMetaDao != null
                && contentSeedMetaDao.countByKeySync(SEED_KEY_B1) > 0) {
            return;
        }
        if (learningDayDao.getDayCountSync("B1") > 0) {
            markSeeded(contentSeedMetaDao, SEED_KEY_B1);
            return;
        }

        seedB1Content(vocabularyDao, grammarPointDao, conversationDao,
                quizQuestionDao, learningDayDao, dayProgressDao, contentSeedMetaDao);
    }

    private static void seedA1Content(
            VocabularyDao vocabularyDao,
            GrammarPointDao grammarPointDao,
            ConversationDao conversationDao,
            QuizQuestionDao quizQuestionDao,
            LearningDayDao learningDayDao,
            DayProgressDao dayProgressDao,
            ContentSeedMetaDao contentSeedMetaDao) {

        int a0GrammarCount = grammarPointDao.getCountByLevelSync("A0");
        int a0ConversationCount = conversationDao.getCountByLevelSync("A0");

        vocabularyDao.insertAll(A1VocabularySeed.buildVocabularyList());
        grammarPointDao.insertAll(A1GrammarSeed.buildGrammarPoints());
        conversationDao.insertAllConversations(A1ConversationSeed.buildConversations());
        conversationDao.insertAllConversationLines(
                A1ConversationSeed.buildConversationLines(a0ConversationCount));

        List<LearningDay> a1Days = A1LearningDaySeed.buildLearningDays(
                a0GrammarCount, a0ConversationCount);
        learningDayDao.insertAll(a1Days);

        for (LearningDay day : learningDayDao.getDaysByLevelSync("A1")) {
            List<QuizQuestion> quizzes = A1QuizSeed.buildDayQuizzes(
                    day.getDayIndex(), day.getId());
            if (!quizzes.isEmpty()) {
                quizQuestionDao.insertAll(quizzes);
            }
        }

        seedA1DayProgress(dayProgressDao, learningDayDao);
        unlockA1IfA0Completed(dayProgressDao, learningDayDao);
        markSeeded(contentSeedMetaDao, SEED_KEY_A1);
    }

    private static void seedA2Content(
            VocabularyDao vocabularyDao,
            GrammarPointDao grammarPointDao,
            ConversationDao conversationDao,
            QuizQuestionDao quizQuestionDao,
            LearningDayDao learningDayDao,
            DayProgressDao dayProgressDao,
            ContentSeedMetaDao contentSeedMetaDao) {

        int a0GrammarCount = grammarPointDao.getCountByLevelSync("A0");
        int a1GrammarCount = grammarPointDao.getCountByLevelSync("A1");
        int a0ConversationCount = conversationDao.getCountByLevelSync("A0");
        int a1ConversationCount = conversationDao.getCountByLevelSync("A1");

        vocabularyDao.insertAll(A2VocabularySeed.buildVocabularyList());
        grammarPointDao.insertAll(A2GrammarSeed.buildGrammarPoints());
        conversationDao.insertAllConversations(A2ConversationSeed.buildConversations());
        conversationDao.insertAllConversationLines(
                A2ConversationSeed.buildConversationLines(a0ConversationCount + a1ConversationCount));

        List<LearningDay> a2Days = A2LearningDaySeed.buildLearningDays(
                a0GrammarCount + a1GrammarCount, a0ConversationCount + a1ConversationCount);
        learningDayDao.insertAll(a2Days);

        for (LearningDay day : learningDayDao.getDaysByLevelSync("A2")) {
            List<QuizQuestion> quizzes = A2QuizSeed.buildDayQuizzes(
                    day.getDayIndex(), day.getId());
            if (!quizzes.isEmpty()) {
                quizQuestionDao.insertAll(quizzes);
            }
        }

        seedA2DayProgress(dayProgressDao, learningDayDao);
        unlockA2IfA1Completed(dayProgressDao, learningDayDao);
        markSeeded(contentSeedMetaDao, SEED_KEY_A2);
    }

    private static void seedB1Content(
            VocabularyDao vocabularyDao,
            GrammarPointDao grammarPointDao,
            ConversationDao conversationDao,
            QuizQuestionDao quizQuestionDao,
            LearningDayDao learningDayDao,
            DayProgressDao dayProgressDao,
            ContentSeedMetaDao contentSeedMetaDao) {

        int a0GrammarCount = grammarPointDao.getCountByLevelSync("A0");
        int a1GrammarCount = grammarPointDao.getCountByLevelSync("A1");
        int a2GrammarCount = grammarPointDao.getCountByLevelSync("A2");
        int a0ConversationCount = conversationDao.getCountByLevelSync("A0");
        int a1ConversationCount = conversationDao.getCountByLevelSync("A1");
        int a2ConversationCount = conversationDao.getCountByLevelSync("A2");

        vocabularyDao.insertAll(B1VocabularySeed.buildVocabularyList());
        grammarPointDao.insertAll(B1GrammarSeed.buildGrammarPoints());
        conversationDao.insertAllConversations(B1ConversationSeed.buildConversations());
        conversationDao.insertAllConversationLines(
                B1ConversationSeed.buildConversationLines(
                        a0ConversationCount + a1ConversationCount + a2ConversationCount));

        List<LearningDay> b1Days = B1LearningDaySeed.buildLearningDays(
                a0GrammarCount + a1GrammarCount + a2GrammarCount,
                a0ConversationCount + a1ConversationCount + a2ConversationCount);
        learningDayDao.insertAll(b1Days);

        for (LearningDay day : learningDayDao.getDaysByLevelSync("B1")) {
            List<QuizQuestion> quizzes = B1QuizSeed.buildDayQuizzes(
                    day.getDayIndex(), day.getId());
            if (!quizzes.isEmpty()) {
                quizQuestionDao.insertAll(quizzes);
            }
        }

        seedB1DayProgress(dayProgressDao, learningDayDao);
        unlockB1IfA2Completed(dayProgressDao, learningDayDao);
        markSeeded(contentSeedMetaDao, SEED_KEY_B1);
    }

    /** Idempotent B2 seed for users who update to v1.0.0+. */
    public static void seedB2IfNeeded(
            VocabularyDao vocabularyDao,
            GrammarPointDao grammarPointDao,
            ConversationDao conversationDao,
            QuizQuestionDao quizQuestionDao,
            LearningDayDao learningDayDao,
            DayProgressDao dayProgressDao,
            ContentSeedMetaDao contentSeedMetaDao) {

        if (contentSeedMetaDao != null
                && contentSeedMetaDao.countByKeySync(SEED_KEY_B2) > 0) {
            return;
        }
        if (learningDayDao.getDayCountSync("B2") > 0) {
            markSeeded(contentSeedMetaDao, SEED_KEY_B2);
            return;
        }

        seedB2Content(vocabularyDao, grammarPointDao, conversationDao,
                quizQuestionDao, learningDayDao, dayProgressDao, contentSeedMetaDao);
    }

    private static void seedB2Content(
            VocabularyDao vocabularyDao,
            GrammarPointDao grammarPointDao,
            ConversationDao conversationDao,
            QuizQuestionDao quizQuestionDao,
            LearningDayDao learningDayDao,
            DayProgressDao dayProgressDao,
            ContentSeedMetaDao contentSeedMetaDao) {

        int a0GrammarCount = grammarPointDao.getCountByLevelSync("A0");
        int a1GrammarCount = grammarPointDao.getCountByLevelSync("A1");
        int a2GrammarCount = grammarPointDao.getCountByLevelSync("A2");
        int b1GrammarCount = grammarPointDao.getCountByLevelSync("B1");
        
        int a0ConversationCount = conversationDao.getCountByLevelSync("A0");
        int a1ConversationCount = conversationDao.getCountByLevelSync("A1");
        int a2ConversationCount = conversationDao.getCountByLevelSync("A2");
        int b1ConversationCount = conversationDao.getCountByLevelSync("B1");

        vocabularyDao.insertAll(B2VocabSeedHelper.getVocabularies());
        grammarPointDao.insertAll(B2GrammarSeed.getGrammarPoints());
        
        List<hung.edu.apptienganh.data.local.entity.Conversation> b2Conversations = B2LessonSeed.getConversations();
        conversationDao.insertAllConversations(b2Conversations);
        
        int b2Offset = a0ConversationCount + a1ConversationCount + a2ConversationCount + b1ConversationCount;
        List<hung.edu.apptienganh.data.local.entity.ConversationLine> allB2Lines = new ArrayList<>();
        for (hung.edu.apptienganh.data.local.entity.Conversation conv : b2Conversations) {
            allB2Lines.addAll(B2LessonSeed.getConversationLines(conv.getId() + b2Offset, 0));
        }
        conversationDao.insertAllConversationLines(allB2Lines);

        learningDayDao.insertAll(B2LessonSeed.getLearningDays());

        for (LearningDay day : learningDayDao.getDaysByLevelSync("B2")) {
            List<QuizQuestion> quizzes = B2LessonSeed.getQuizQuestions();
            List<QuizQuestion> dayQuizzes = new ArrayList<>();
            for(QuizQuestion q : quizzes) {
                if(q.getLearningDayId() == day.getDayIndex()) {
                    q.setLearningDayId(day.getId());
                    dayQuizzes.add(q);
                }
            }
            if (!dayQuizzes.isEmpty()) {
                quizQuestionDao.insertAll(dayQuizzes);
            }
        }

        seedB2DayProgress(dayProgressDao, learningDayDao);
        unlockB2IfB1Completed(dayProgressDao, learningDayDao);
        markSeeded(contentSeedMetaDao, SEED_KEY_B2);
    }

    /** Idempotent C1 seed for users who update to v1.0.0+. */
    public static void seedC1IfNeeded(
            VocabularyDao vocabularyDao,
            GrammarPointDao grammarPointDao,
            ConversationDao conversationDao,
            QuizQuestionDao quizQuestionDao,
            LearningDayDao learningDayDao,
            DayProgressDao dayProgressDao,
            ContentSeedMetaDao contentSeedMetaDao) {

        if (contentSeedMetaDao != null
                && contentSeedMetaDao.countByKeySync(SEED_KEY_C1) > 0) {
            return;
        }
        if (learningDayDao.getDayCountSync("C1") > 0) {
            markSeeded(contentSeedMetaDao, SEED_KEY_C1);
            return;
        }

        seedC1Content(vocabularyDao, grammarPointDao, conversationDao,
                quizQuestionDao, learningDayDao, dayProgressDao, contentSeedMetaDao);
    }

    private static void seedC1Content(
            VocabularyDao vocabularyDao,
            GrammarPointDao grammarPointDao,
            ConversationDao conversationDao,
            QuizQuestionDao quizQuestionDao,
            LearningDayDao learningDayDao,
            DayProgressDao dayProgressDao,
            ContentSeedMetaDao contentSeedMetaDao) {

        int a0GrammarCount = grammarPointDao.getCountByLevelSync("A0");
        int a1GrammarCount = grammarPointDao.getCountByLevelSync("A1");
        int a2GrammarCount = grammarPointDao.getCountByLevelSync("A2");
        int b1GrammarCount = grammarPointDao.getCountByLevelSync("B1");
        int b2GrammarCount = grammarPointDao.getCountByLevelSync("B2");
        
        int a0ConversationCount = conversationDao.getCountByLevelSync("A0");
        int a1ConversationCount = conversationDao.getCountByLevelSync("A1");
        int a2ConversationCount = conversationDao.getCountByLevelSync("A2");
        int b1ConversationCount = conversationDao.getCountByLevelSync("B1");
        int b2ConversationCount = conversationDao.getCountByLevelSync("B2");

        vocabularyDao.insertAll(C1VocabSeedHelper.getVocabularies());
        grammarPointDao.insertAll(C1GrammarSeed.getGrammarPoints());
        
        List<hung.edu.apptienganh.data.local.entity.Conversation> c1Conversations = C1LessonSeed.getConversations();
        conversationDao.insertAllConversations(c1Conversations);
        
        int c1Offset = a0ConversationCount + a1ConversationCount + a2ConversationCount + b1ConversationCount + b2ConversationCount;
        List<hung.edu.apptienganh.data.local.entity.ConversationLine> allC1Lines = new ArrayList<>();
        for (hung.edu.apptienganh.data.local.entity.Conversation conv : c1Conversations) {
            allC1Lines.addAll(C1LessonSeed.getConversationLines(conv.getId() + c1Offset, 0));
        }
        conversationDao.insertAllConversationLines(allC1Lines);

        learningDayDao.insertAll(C1LessonSeed.getLearningDays());

        for (LearningDay day : learningDayDao.getDaysByLevelSync("C1")) {
            List<QuizQuestion> quizzes = C1LessonSeed.getQuizQuestions();
            List<QuizQuestion> dayQuizzes = new ArrayList<>();
            for(QuizQuestion q : quizzes) {
                if(q.getLearningDayId() == day.getDayIndex()) {
                    q.setLearningDayId(day.getId());
                    dayQuizzes.add(q);
                }
            }
            if (!dayQuizzes.isEmpty()) {
                quizQuestionDao.insertAll(dayQuizzes);
            }
        }

        seedC1DayProgress(dayProgressDao, learningDayDao);
        unlockC1IfB2Completed(dayProgressDao, learningDayDao);
        markSeeded(contentSeedMetaDao, SEED_KEY_C1);
    }

    private static void unlockA1IfA0Completed(DayProgressDao dayProgressDao, LearningDayDao learningDayDao) {
        int a0Total = learningDayDao.getDayCountSync("A0");
        if (a0Total <= 0) {
            return;
        }
        DayProgress lastA0 = dayProgressDao.getProgressSync(
                hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID, "A0", a0Total);
        if (lastA0 == null || !lastA0.isCompleted()) {
            return;
        }
        DayProgress firstA1 = dayProgressDao.getProgressSync(
                hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID, "A1", 1);
        long now = System.currentTimeMillis();
        if (firstA1 == null) {
            dayProgressDao.insert(new hung.edu.apptienganh.data.local.entity.DayProgress(
                    hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID,
                    "A1", 1, true, false, 0, 0, 0, 0, 0,
                    hung.edu.apptienganh.domain.model.DaySection.STATUS_AVAILABLE, 0, 0, now));
        } else if (!firstA1.isUnlocked()) {
            firstA1.setUnlocked(true);
            firstA1.setStatus(hung.edu.apptienganh.domain.model.DaySection.STATUS_AVAILABLE);
            firstA1.setUpdatedAt(now);
            dayProgressDao.update(firstA1);
        }
    }

    private static void unlockA2IfA1Completed(DayProgressDao dayProgressDao, LearningDayDao learningDayDao) {
        int a1Total = learningDayDao.getDayCountSync("A1");
        if (a1Total <= 0) {
            return;
        }
        DayProgress lastA1 = dayProgressDao.getProgressSync(
                hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID, "A1", a1Total);
        if (lastA1 == null || !lastA1.isCompleted()) {
            return;
        }
        DayProgress firstA2 = dayProgressDao.getProgressSync(
                hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID, "A2", 1);
        long now = System.currentTimeMillis();
        if (firstA2 == null) {
            dayProgressDao.insert(new hung.edu.apptienganh.data.local.entity.DayProgress(
                    hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID,
                    "A2", 1, true, false, 0, 0, 0, 0, 0,
                    hung.edu.apptienganh.domain.model.DaySection.STATUS_AVAILABLE, 0, 0, now));
        } else if (!firstA2.isUnlocked()) {
            firstA2.setUnlocked(true);
            firstA2.setStatus(hung.edu.apptienganh.domain.model.DaySection.STATUS_AVAILABLE);
            firstA2.setUpdatedAt(now);
            dayProgressDao.update(firstA2);
        }
    }

    private static void unlockB1IfA2Completed(DayProgressDao dayProgressDao, LearningDayDao learningDayDao) {
        int a2Total = learningDayDao.getDayCountSync("A2");
        if (a2Total <= 0) {
            return;
        }
        DayProgress lastA2 = dayProgressDao.getProgressSync(
                hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID, "A2", a2Total);
        if (lastA2 == null || !lastA2.isCompleted()) {
            return;
        }
        DayProgress firstB1 = dayProgressDao.getProgressSync(
                hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID, "B1", 1);
        long now = System.currentTimeMillis();
        if (firstB1 == null) {
            dayProgressDao.insert(new hung.edu.apptienganh.data.local.entity.DayProgress(
                    hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID,
                    "B1", 1, true, false, 0, 0, 0, 0, 0,
                    hung.edu.apptienganh.domain.model.DaySection.STATUS_AVAILABLE, 0, 0, now));
        } else if (!firstB1.isUnlocked()) {
            firstB1.setUnlocked(true);
            firstB1.setStatus(hung.edu.apptienganh.domain.model.DaySection.STATUS_AVAILABLE);
            firstB1.setUpdatedAt(now);
            dayProgressDao.update(firstB1);
        }
    }

    private static void unlockB2IfB1Completed(DayProgressDao dayProgressDao, LearningDayDao learningDayDao) {
        int b1Total = learningDayDao.getDayCountSync("B1");
        if (b1Total <= 0) {
            return;
        }
        DayProgress lastB1 = dayProgressDao.getProgressSync(
                hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID, "B1", b1Total);
        if (lastB1 == null || !lastB1.isCompleted()) {
            return;
        }
        DayProgress firstB2 = dayProgressDao.getProgressSync(
                hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID, "B2", 1);
        long now = System.currentTimeMillis();
        if (firstB2 == null) {
            dayProgressDao.insert(new hung.edu.apptienganh.data.local.entity.DayProgress(
                    hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID,
                    "B2", 1, true, false, 0, 0, 0, 0, 0,
                    hung.edu.apptienganh.domain.model.DaySection.STATUS_AVAILABLE, 0, 0, now));
        } else if (!firstB2.isUnlocked()) {
            firstB2.setUnlocked(true);
            firstB2.setStatus(hung.edu.apptienganh.domain.model.DaySection.STATUS_AVAILABLE);
            firstB2.setUpdatedAt(now);
            dayProgressDao.update(firstB2);
        }
    }

    private static void unlockC1IfB2Completed(DayProgressDao dayProgressDao, LearningDayDao learningDayDao) {
        int b2Total = learningDayDao.getDayCountSync("B2");
        if (b2Total <= 0) {
            return;
        }
        DayProgress lastB2 = dayProgressDao.getProgressSync(
                hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID, "B2", b2Total);
        if (lastB2 == null || !lastB2.isCompleted()) {
            return;
        }
        DayProgress firstC1 = dayProgressDao.getProgressSync(
                hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID, "C1", 1);
        long now = System.currentTimeMillis();
        if (firstC1 == null) {
            dayProgressDao.insert(new hung.edu.apptienganh.data.local.entity.DayProgress(
                    hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID,
                    "C1", 1, true, false, 0, 0, 0, 0, 0,
                    hung.edu.apptienganh.domain.model.DaySection.STATUS_AVAILABLE, 0, 0, now));
        } else if (!firstC1.isUnlocked()) {
            firstC1.setUnlocked(true);
            firstC1.setStatus(hung.edu.apptienganh.domain.model.DaySection.STATUS_AVAILABLE);
            firstC1.setUpdatedAt(now);
            dayProgressDao.update(firstC1);
        }
    }

    private static void seedA0Quizzes(QuizQuestionDao quizQuestionDao, LearningDayDao learningDayDao) {
        for (LearningDay day : learningDayDao.getDaysByLevelSync("A0")) {
            List<QuizQuestion> dayQuizzes = new ArrayList<>();
            for (QuizQuestion q : A0QuizSeed.buildDayQuizzes()) {
                if (q.getLearningDayId() == day.getDayIndex()) {
                    q.setLearningDayId(day.getId());
                    dayQuizzes.add(q);
                }
            }
            if (!dayQuizzes.isEmpty()) {
                quizQuestionDao.insertAll(dayQuizzes);
            }
        }
    }

    private static void markSeeded(ContentSeedMetaDao contentSeedMetaDao, String key) {
        if (contentSeedMetaDao == null) {
            return;
        }
        if (contentSeedMetaDao.countByKeySync(key) == 0) {
            ContentSeedMeta meta = new ContentSeedMeta();
            meta.setSeedKey(key);
            meta.setSeededAt(System.currentTimeMillis());
            contentSeedMetaDao.insert(meta);
        }
    }

    public static void seedA1DayProgress(DayProgressDao dayProgressDao, LearningDayDao learningDayDao) {
        int totalDays = learningDayDao.getDayCountSync("A1");
        if (totalDays <= 0) {
            return;
        }
        long now = System.currentTimeMillis();
        List<hung.edu.apptienganh.data.local.entity.DayProgress> progressList = new ArrayList<>();
        for (int day = 1; day <= totalDays; day++) {
            progressList.add(new hung.edu.apptienganh.data.local.entity.DayProgress(
                    hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID,
                    "A1", day, false, false,
                    0, 0, 0, 0, 0,
                    hung.edu.apptienganh.domain.model.DaySection.STATUS_LOCKED,
                    0, 0, now));
        }
        dayProgressDao.insertAll(progressList);
    }

    public static void seedA2DayProgress(DayProgressDao dayProgressDao, LearningDayDao learningDayDao) {
        int totalDays = learningDayDao.getDayCountSync("A2");
        if (totalDays <= 0) {
            return;
        }
        long now = System.currentTimeMillis();
        List<hung.edu.apptienganh.data.local.entity.DayProgress> progressList = new ArrayList<>();
        for (int day = 1; day <= totalDays; day++) {
            progressList.add(new hung.edu.apptienganh.data.local.entity.DayProgress(
                    hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID,
                    "A2", day, false, false,
                    0, 0, 0, 0, 0,
                    hung.edu.apptienganh.domain.model.DaySection.STATUS_LOCKED,
                    0, 0, now));
        }
        dayProgressDao.insertAll(progressList);
    }

    public static void seedB1DayProgress(DayProgressDao dayProgressDao, LearningDayDao learningDayDao) {
        int totalDays = learningDayDao.getDayCountSync("B1");
        if (totalDays <= 0) {
            return;
        }
        long now = System.currentTimeMillis();
        List<hung.edu.apptienganh.data.local.entity.DayProgress> progressList = new ArrayList<>();
        for (int day = 1; day <= totalDays; day++) {
            progressList.add(new hung.edu.apptienganh.data.local.entity.DayProgress(
                    hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID,
                    "B1", day, false, false,
                    0, 0, 0, 0, 0,
                    hung.edu.apptienganh.domain.model.DaySection.STATUS_LOCKED,
                    0, 0, now));
        }
        dayProgressDao.insertAll(progressList);
    }

    public static void seedB2DayProgress(DayProgressDao dayProgressDao, LearningDayDao learningDayDao) {
        int totalDays = learningDayDao.getDayCountSync("B2");
        if (totalDays <= 0) {
            return;
        }
        long now = System.currentTimeMillis();
        List<hung.edu.apptienganh.data.local.entity.DayProgress> progressList = new ArrayList<>();
        for (int day = 1; day <= totalDays; day++) {
            progressList.add(new hung.edu.apptienganh.data.local.entity.DayProgress(
                    hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID,
                    "B2", day, false, false,
                    0, 0, 0, 0, 0,
                    hung.edu.apptienganh.domain.model.DaySection.STATUS_LOCKED,
                    0, 0, now));
        }
        dayProgressDao.insertAll(progressList);
    }

    public static void seedC1DayProgress(DayProgressDao dayProgressDao, LearningDayDao learningDayDao) {
        int totalDays = learningDayDao.getDayCountSync("C1");
        if (totalDays <= 0) {
            return;
        }
        long now = System.currentTimeMillis();
        List<hung.edu.apptienganh.data.local.entity.DayProgress> progressList = new ArrayList<>();
        for (int day = 1; day <= totalDays; day++) {
            progressList.add(new hung.edu.apptienganh.data.local.entity.DayProgress(
                    hung.edu.apptienganh.data.local.AppDatabase.DEFAULT_USER_ID,
                    "C1", day, false, false,
                    0, 0, 0, 0, 0,
                    hung.edu.apptienganh.domain.model.DaySection.STATUS_LOCKED,
                    0, 0, now));
        }
        dayProgressDao.insertAll(progressList);
    }

    private static List<Lesson> buildLegacyLessons() {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("A0", "PRONUNCIATION", "The Alphabet",
                "Học bảng chữ cái tiếng Anh từ A đến Z",
                "Nhận biết mặt chữ và phát âm chuẩn 26 chữ cái tiếng Anh",
                "Bảng chữ cái tiếng Anh gồm 26 chữ cái.",
                "A /eɪ/, B /biː/, C /siː/, D /diː/, E /iː/",
                1, false));
        lessons.add(new Lesson("A0", "GRAMMAR", "Verb to be",
                "Động từ TO BE ở thì hiện tại đơn",
                "Sử dụng thành thạo Am, Is, Are",
                "Động từ TO BE mang nghĩa là 'thì, là, ở'.",
                "I am a student.\nShe is nice.\nThey are here.",
                2, false));
        lessons.add(new Lesson("A1", "GRAMMAR", "Present Simple Review",
                "Ôn thì hiện tại đơn A1",
                "Chia động từ đúng với mọi chủ ngữ",
                "Hiện tại đơn dùng cho thói quen và sự thật.",
                "I work every day.\nShe likes music.",
                1, false));
        lessons.add(new Lesson("A1", "CONVERSATION", "Travel Basics",
                "Giao tiếp du lịch cơ bản",
                "Hội thoại sân bay và khách sạn",
                "Mẫu câu hỏi đáp khi đi du lịch.",
                "May I see your passport?\nI would like to book a room.",
                2, false));
        return lessons;
    }

    private static List<QuizQuestion> buildLegacyLessonQuizzes() {
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion(1, "Chữ cái nào là nguyên âm?", "B", "F", "E", "H", "C",
                "Nguyên âm chính: A, E, I, O, U."));
        questions.add(new QuizQuestion(2, "Chọn câu đúng: I ___ a student.", "is", "am", "are", "be", "B",
                "I → am."));
        return questions;
    }

}
