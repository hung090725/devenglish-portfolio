package hung.edu.apptienganh.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.dao.ConversationDao;
import hung.edu.apptienganh.data.local.dao.DayProgressDao;
import hung.edu.apptienganh.data.local.dao.GrammarPointDao;
import hung.edu.apptienganh.data.local.dao.LearningDayDao;
import hung.edu.apptienganh.data.local.dao.LessonDao;
import hung.edu.apptienganh.data.local.dao.QuizAnswerDao;
import hung.edu.apptienganh.data.local.dao.QuizAttemptDao;
import hung.edu.apptienganh.data.local.dao.QuizQuestionDao;
import hung.edu.apptienganh.data.local.dao.SpeakingResultDao;
import hung.edu.apptienganh.data.local.dao.UserProfileDao;
import hung.edu.apptienganh.data.local.dao.VocabularyDao;
import hung.edu.apptienganh.data.local.dao.VocabularyReviewProgressDao;
import hung.edu.apptienganh.data.local.entity.Conversation;
import hung.edu.apptienganh.data.local.entity.ConversationLine;
import hung.edu.apptienganh.data.local.entity.DayProgress;
import hung.edu.apptienganh.data.local.entity.GrammarPoint;
import hung.edu.apptienganh.data.local.entity.LearningDay;
import hung.edu.apptienganh.data.local.entity.Lesson;
import hung.edu.apptienganh.data.local.entity.QuizAnswer;
import hung.edu.apptienganh.data.local.entity.QuizAttempt;
import hung.edu.apptienganh.data.local.entity.QuizQuestion;
import hung.edu.apptienganh.data.local.entity.SpeakingResult;
import hung.edu.apptienganh.data.local.entity.UserProfile;
import hung.edu.apptienganh.data.local.entity.Vocabulary;
import hung.edu.apptienganh.data.local.entity.VocabularyReviewProgress;
import hung.edu.apptienganh.domain.model.DaySection;
import hung.edu.apptienganh.domain.model.LevelConstants;
import hung.edu.apptienganh.domain.path.ContinueDayResolver;
import hung.edu.apptienganh.domain.quiz.QuizScorer;
import hung.edu.apptienganh.domain.srs.SrsScheduler;
import hung.edu.apptienganh.domain.srs.VocabularyReviewFilter;

public class AppRepository {

    private final UserProfileDao userProfileDao;
    private final LessonDao lessonDao;
    private final VocabularyDao vocabularyDao;
    private final GrammarPointDao grammarPointDao;
    private final ConversationDao conversationDao;
    private final QuizQuestionDao quizQuestionDao;
    private final SpeakingResultDao speakingResultDao;
    private final LearningDayDao learningDayDao;
    private final DayProgressDao dayProgressDao;
    private final VocabularyReviewProgressDao vocabularyReviewProgressDao;
    private final QuizAttemptDao quizAttemptDao;
    private final QuizAnswerDao quizAnswerDao;

    public AppRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        userProfileDao = db.userProfileDao();
        lessonDao = db.lessonDao();
        vocabularyDao = db.vocabularyDao();
        grammarPointDao = db.grammarPointDao();
        conversationDao = db.conversationDao();
        quizQuestionDao = db.quizQuestionDao();
        speakingResultDao = db.speakingResultDao();
        learningDayDao = db.learningDayDao();
        dayProgressDao = db.dayProgressDao();
        vocabularyReviewProgressDao = db.vocabularyReviewProgressDao();
        quizAttemptDao = db.quizAttemptDao();
        quizAnswerDao = db.quizAnswerDao();
    }

    // User Profile
    public LiveData<UserProfile> getUserProfile() {
        return userProfileDao.getUserProfile();
    }

    public void updateUserProfile(UserProfile userProfile) {
        AppDatabase.databaseWriteExecutor.execute(() -> userProfileDao.update(userProfile));
    }

    public void insertUserProfile(UserProfile userProfile) {
        AppDatabase.databaseWriteExecutor.execute(() -> userProfileDao.insert(userProfile));
    }

    // Learning path A0
    public LiveData<List<LearningDay>> getLearningDays(String level) {
        return learningDayDao.getDaysByLevel(level);
    }

    public LiveData<LearningDay> getLearningDay(String level, int dayIndex) {
        return learningDayDao.getDayByIndex(level, dayIndex);
    }

    public LiveData<List<DayProgress>> getDayProgressList(String level) {
        return dayProgressDao.getProgressForLevel(AppDatabase.DEFAULT_USER_ID, level);
    }

    public LiveData<DayProgress> getInProgressDay(String level) {
        return dayProgressDao.getInProgressDay(AppDatabase.DEFAULT_USER_ID, level);
    }

    public LiveData<Integer> getCompletedDayCount(String level) {
        return dayProgressDao.getCompletedDayCount(AppDatabase.DEFAULT_USER_ID, level);
    }

    public LiveData<List<QuizQuestion>> getQuestionsForLearningDay(int learningDayId) {
        return quizQuestionDao.getQuestionsForLearningDay(learningDayId);
    }

    public void markDayInProgress(String level, int dayIndex, int sectionIndex) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            long now = System.currentTimeMillis();
            DayProgress current = dayProgressDao.getProgressSync(
                    AppDatabase.DEFAULT_USER_ID, level, dayIndex);
            if (current == null) {
                return;
            }
            if (current.isCompleted() || DaySection.STATUS_COMPLETED.equals(current.getStatus())) {
                return;
            }
            current.setStatus(DaySection.STATUS_IN_PROGRESS);
            current.setCurrentSectionIndex(sectionIndex);
            current.setLastOpenedAt(now);
            current.setUpdatedAt(now);
            dayProgressDao.update(current);
        });
    }

    public void saveDaySection(String level, int dayIndex, int sectionIndex) {
        markDayInProgress(level, dayIndex, sectionIndex);
    }

    public void completeLearningDay(String level, int dayIndex, int quizScore, int speakingScore) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            long now = System.currentTimeMillis();
            int overall = (quizScore + speakingScore) / 2;

            DayProgress current = dayProgressDao.getProgressSync(
                    AppDatabase.DEFAULT_USER_ID, level, dayIndex);
            if (current == null) {
                current = new DayProgress(AppDatabase.DEFAULT_USER_ID, level, dayIndex,
                        true, true, now, overall, speakingScore, quizScore, quizScore,
                        DaySection.STATUS_COMPLETED, DaySection.COMPLETED, now, now);
                dayProgressDao.insert(current);
            } else {
                current.setCompleted(true);
                current.setStatus(DaySection.STATUS_COMPLETED);
                current.setCurrentSectionIndex(DaySection.COMPLETED);
                current.setScore(overall);
                current.setQuizScore(quizScore);
                current.setSpeakingScore(speakingScore);
                current.setBestQuizScore(Math.max(current.getBestQuizScore(), quizScore));
                current.setCompletedAt(now);
                current.setUpdatedAt(now);
                dayProgressDao.update(current);
            }

            int totalDays = learningDayDao.getDayCountSync(level);
            if (dayIndex < totalDays) {
                DayProgress next = dayProgressDao.getProgressSync(
                        AppDatabase.DEFAULT_USER_ID, level, dayIndex + 1);
                if (next == null) {
                    next = new DayProgress(AppDatabase.DEFAULT_USER_ID, level, dayIndex + 1,
                            true, false, 0, 0, 0, 0, 0,
                            DaySection.STATUS_AVAILABLE, 0, 0, now);
                    dayProgressDao.insert(next);
                } else if (!next.isUnlocked()) {
                    next.setUnlocked(true);
                    next.setStatus(DaySection.STATUS_AVAILABLE);
                    next.setUpdatedAt(now);
                    dayProgressDao.update(next);
                }
            } else if (LevelConstants.A0.equals(level)) {
                unlockA1FirstDaySync(now);
            } else if (LevelConstants.A1.equals(level)) {
                unlockA2FirstDaySync(now);
            }

            int completed = dayProgressDao.getCompletedDayCountSync(AppDatabase.DEFAULT_USER_ID, level);
            UserProfile profile = userProfileDao.getUserProfileSync();
            if (profile != null) {
                profile.setTotalLessonsCompleted(completed);
                if (profile.getStreak() == 0) {
                    profile.setStreak(1);
                }
                userProfileDao.update(profile);
            }
        });
    }

    private void unlockA1FirstDaySync(long now) {
        if (learningDayDao.getDayCountSync(LevelConstants.A1) <= 0) {
            return;
        }
        DayProgress firstA1 = dayProgressDao.getProgressSync(
                AppDatabase.DEFAULT_USER_ID, LevelConstants.A1, 1);
        if (firstA1 == null) {
            dayProgressDao.insert(new DayProgress(
                    AppDatabase.DEFAULT_USER_ID, LevelConstants.A1, 1,
                    true, false, 0, 0, 0, 0, 0,
                    DaySection.STATUS_AVAILABLE, 0, 0, now));
        } else if (!firstA1.isUnlocked()) {
            firstA1.setUnlocked(true);
            firstA1.setStatus(DaySection.STATUS_AVAILABLE);
            firstA1.setUpdatedAt(now);
            dayProgressDao.update(firstA1);
        }
        UserProfile profile = userProfileDao.getUserProfileSync();
        if (profile != null && LevelConstants.A0.equals(profile.getCurrentLevel())) {
            profile.setCurrentLevel(LevelConstants.A1);
            userProfileDao.update(profile);
        }
    }

    private void unlockA2FirstDaySync(long now) {
        if (learningDayDao.getDayCountSync(LevelConstants.A2) <= 0) return;
        DayProgress firstA2 = dayProgressDao.getProgressSync(
                AppDatabase.DEFAULT_USER_ID, LevelConstants.A2, 1);
        if (firstA2 == null) {
            dayProgressDao.insert(new DayProgress(
                    AppDatabase.DEFAULT_USER_ID, LevelConstants.A2, 1,
                    true, false, 0, 0, 0, 0, 0,
                    DaySection.STATUS_AVAILABLE, 0, 0, now));
        } else if (!firstA2.isUnlocked()) {
            firstA2.setUnlocked(true);
            firstA2.setStatus(DaySection.STATUS_AVAILABLE);
            firstA2.setUpdatedAt(now);
            dayProgressDao.update(firstA2);
        }
        UserProfile profile = userProfileDao.getUserProfileSync();
        if (profile != null && LevelConstants.A1.equals(profile.getCurrentLevel())) {
            profile.setCurrentLevel(LevelConstants.A2);
            userProfileDao.update(profile);
        }
    }

    public void resolveGlobalContinueDay(GlobalContinueDayCallback callback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            ContinueDayResolver.ContinueTarget target = ContinueDayResolver.resolve(
                    dayProgressDao, learningDayDao, AppDatabase.DEFAULT_USER_ID);
            if (callback != null) {
                callback.onResolved(target.getLevel(), target.getDayIndex(), target.getSectionIndex());
            }
        });
    }

    public interface GlobalContinueDayCallback {
        void onResolved(String level, int dayIndex, int sectionIndex);
    }

    public boolean isA0CompletedSync() {
        return ContinueDayResolver.isA0Completed(
                dayProgressDao, learningDayDao, AppDatabase.DEFAULT_USER_ID);
    }

    public boolean isA1UnlockedSync() {
        return ContinueDayResolver.isA1Unlocked(dayProgressDao, AppDatabase.DEFAULT_USER_ID);
    }

    public boolean isA2UnlockedSync() {
        return ContinueDayResolver.isA2Unlocked(dayProgressDao, AppDatabase.DEFAULT_USER_ID);
    }

    public boolean isB1UnlockedSync() {
        return ContinueDayResolver.isB1Unlocked(dayProgressDao, AppDatabase.DEFAULT_USER_ID);
    }

    public int getDayCountSync(String level) {
        return learningDayDao.getDayCountSync(level);
    }

    public void resolveContinueDay(String level, ContinueDayCallback callback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            int dayIndex = resolveContinueDayIndexSync(level);
            int sectionIndex = resolveContinueSectionIndexSync(level, dayIndex);
            if (callback != null) {
                callback.onResolved(dayIndex, sectionIndex);
            }
        });
    }

    public interface ContinueDayCallback {
        void onResolved(int dayIndex, int sectionIndex);
    }

    public int resolveContinueDayIndexSync(String level) {
        DayProgress inProgress = dayProgressDao.getInProgressDaySync(
                AppDatabase.DEFAULT_USER_ID, level);
        if (inProgress != null) {
            return inProgress.getDayIndex();
        }
        int total = learningDayDao.getDayCountSync(level);
        for (int day = 1; day <= total; day++) {
            DayProgress p = dayProgressDao.getProgressSync(AppDatabase.DEFAULT_USER_ID, level, day);
            if (p == null || !p.isCompleted()) {
                return day;
            }
        }
        return Math.max(1, total);
    }

    public int resolveContinueSectionIndexSync(String level, int dayIndex) {
        DayProgress p = dayProgressDao.getProgressSync(AppDatabase.DEFAULT_USER_ID, level, dayIndex);
        if (p != null && DaySection.STATUS_IN_PROGRESS.equals(p.getStatus())) {
            return p.getCurrentSectionIndex();
        }
        return DaySection.OBJECTIVE;
    }

    // Vocabulary SRS
    public void ensureVocabularyReviewInitialized() {
        AppDatabase.databaseWriteExecutor.execute(this::ensureVocabularyReviewInitializedSync);
    }

    private void ensureVocabularyReviewInitializedSync() {
        long now = System.currentTimeMillis();
        ensureLevelVocabularyReviewInitializedSync(LevelConstants.A0, now);
        ensureLevelVocabularyReviewInitializedSync(LevelConstants.A1, now);
        ensureLevelVocabularyReviewInitializedSync(LevelConstants.A2, now);
    }

    private void ensureLevelVocabularyReviewInitializedSync(String level, long now) {
        List<Vocabulary> vocabList = vocabularyDao.getVocabularyByLevelSync(level);
        if (vocabList == null || vocabList.isEmpty()) {
            return;
        }
        List<VocabularyReviewProgress> toInsert = new ArrayList<>();
        for (Vocabulary v : vocabList) {
            VocabularyReviewProgress existing = vocabularyReviewProgressDao.getByVocabIdSync(
                    AppDatabase.DEFAULT_USER_ID, v.getId());
            if (existing == null) {
                toInsert.add(SrsScheduler.createNew(AppDatabase.DEFAULT_USER_ID, v.getId(), now));
            }
        }
        if (!toInsert.isEmpty()) {
            vocabularyReviewProgressDao.insertAll(toInsert);
        }
    }

    public LiveData<Integer> getVocabularyDueCount() {
        AppDatabase.databaseWriteExecutor.execute(this::ensureVocabularyReviewInitializedSync);
        return vocabularyReviewProgressDao.getDueCount(
                AppDatabase.DEFAULT_USER_ID, System.currentTimeMillis());
    }

    public List<VocabularyReviewProgress> getDueVocabularySync() {
        ensureVocabularyReviewInitializedSync();
        return vocabularyReviewProgressDao.getDueSync(
                AppDatabase.DEFAULT_USER_ID, System.currentTimeMillis());
    }

    public List<VocabularyReviewProgress> getReviewListSync(String filter) {
        ensureVocabularyReviewInitializedSync();
        long now = System.currentTimeMillis();
        List<VocabularyReviewProgress> source;
        if (VocabularyReviewFilter.FILTER_DUE_ONLY.equals(filter)) {
            source = vocabularyReviewProgressDao.getDueSync(AppDatabase.DEFAULT_USER_ID, now);
        } else {
            source = vocabularyReviewProgressDao.getAllForUserSync(AppDatabase.DEFAULT_USER_ID);
        }
        return VocabularyReviewFilter.apply(source, filter, now, vocabularyDao::getByIdSync);
    }

    public LiveData<Integer> getVocabularyDueCountByLevel(String level) {
        AppDatabase.databaseWriteExecutor.execute(this::ensureVocabularyReviewInitializedSync);
        return vocabularyReviewProgressDao.getDueCount(AppDatabase.DEFAULT_USER_ID,
                System.currentTimeMillis());
    }

    public Vocabulary getVocabularyByIdSync(int id) {
        return vocabularyDao.getByIdSync(id);
    }

    public List<Vocabulary> getVocabularyByLevelSync(String level) {
        return vocabularyDao.getVocabularyByLevelSync(level);
    }

    public void applyVocabularyReviewRating(int vocabularyId, String rating) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            long now = System.currentTimeMillis();
            VocabularyReviewProgress progress = vocabularyReviewProgressDao.getByVocabIdSync(
                    AppDatabase.DEFAULT_USER_ID, vocabularyId);
            if (progress == null) {
                progress = SrsScheduler.createNew(AppDatabase.DEFAULT_USER_ID, vocabularyId, now);
            }
            SrsScheduler.applyRating(progress, rating, now);
            if (progress.getId() == 0) {
                vocabularyReviewProgressDao.insert(progress);
            } else {
                vocabularyReviewProgressDao.update(progress);
            }
        });
    }

    public LiveData<Integer> getLearnedVocabularyCount() {
        return vocabularyReviewProgressDao.getLearnedCount(AppDatabase.DEFAULT_USER_ID);
    }

    public interface QuizSaveCallback {
        void onSaved(long attemptId);
    }

    public void saveQuizAttempt(int learningDayId, List<QuizScorer.AnswerRecord> answers,
                                QuizSaveCallback callback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            long attemptId = saveQuizAttemptSync(learningDayId, answers);
            if (callback != null) {
                callback.onSaved(attemptId);
            }
        });
    }

    // Quiz attempts (call from background thread)
    public long saveQuizAttemptSync(int learningDayId, List<QuizScorer.AnswerRecord> answers) {
        QuizScorer.QuizScoreResult result = QuizScorer.calculate(answers);
        long now = System.currentTimeMillis();

        QuizAttempt attempt = new QuizAttempt();
        attempt.setUserId(AppDatabase.DEFAULT_USER_ID);
        attempt.setLearningDayId(learningDayId);
        attempt.setScore(result.getScorePercent());
        attempt.setTotalQuestions(result.getTotalQuestions());
        attempt.setCorrectCount(result.getCorrectCount());
        attempt.setWrongCount(result.getWrongCount());
        attempt.setCreatedAt(now);

        long attemptId = quizAttemptDao.insert(attempt);

        List<QuizAnswer> answerEntities = new ArrayList<>();
        for (QuizScorer.AnswerRecord record : answers) {
            QuizAnswer answer = new QuizAnswer();
            answer.setAttemptId((int) attemptId);
            answer.setQuestionId(record.getQuestionId());
            answer.setSelectedAnswer(record.getSelectedAnswer());
            answer.setCorrectAnswer(record.getCorrectAnswer());
            answer.setCorrect(record.isCorrect());
            answer.setExplanationVi(record.getExplanationVi());
            answerEntities.add(answer);
        }
        quizAnswerDao.insertAll(answerEntities);

        LearningDay day = learningDayDao.getDayByIdSync(learningDayId);
        if (day != null) {
            DayProgress progress = dayProgressDao.getProgressSync(
                    AppDatabase.DEFAULT_USER_ID, day.getLevel(), day.getDayIndex());
            if (progress != null && !progress.isCompleted()) {
                progress.setQuizScore(result.getScorePercent());
                progress.setBestQuizScore(Math.max(progress.getBestQuizScore(), result.getScorePercent()));
                progress.setUpdatedAt(now);
                dayProgressDao.update(progress);
            } else if (progress != null) {
                progress.setBestQuizScore(Math.max(progress.getBestQuizScore(), result.getScorePercent()));
                progress.setUpdatedAt(now);
                dayProgressDao.update(progress);
            }
        }
        return attemptId;
    }

    public List<QuizAnswer> getQuizAnswersForAttempt(int attemptId) {
        return quizAnswerDao.getAnswersForAttempt(attemptId);
    }

    public QuizAttempt getQuizAttemptSync(int attemptId) {
        return quizAttemptDao.getByIdSync(attemptId);
    }

    public LiveData<Integer> getBestQuizScore() {
        return quizAttemptDao.getBestScore(AppDatabase.DEFAULT_USER_ID);
    }

    // Speaking
    public void insertSpeakingResult(SpeakingResult result) {
        AppDatabase.databaseWriteExecutor.execute(() -> speakingResultDao.insert(result));
    }

    public LiveData<List<SpeakingResult>> getAllSpeakingResults() {
        return speakingResultDao.getAllResults();
    }

    public LiveData<Double> getAverageSpeakingScore() {
        return speakingResultDao.getAverageScoreForUser(AppDatabase.DEFAULT_USER_ID);
    }

    public LiveData<Integer> getSpeakingAttemptCount() {
        return speakingResultDao.getAttemptCount(AppDatabase.DEFAULT_USER_ID);
    }

    public LiveData<SpeakingResult> getLowestRecentSpeakingResult() {
        return speakingResultDao.getLowestRecentResult(AppDatabase.DEFAULT_USER_ID);
    }

    public SpeakingResult getLatestSpeakingForDaySync(int learningDayId) {
        return speakingResultDao.getLatestForDaySync(AppDatabase.DEFAULT_USER_ID, learningDayId);
    }

    // Lesson
    public LiveData<List<Lesson>> getAllLessons() {
        return lessonDao.getAllLessons();
    }

    public LiveData<List<Lesson>> getLessonsByLevel(String level) {
        return lessonDao.getLessonsByLevel(level);
    }

    public LiveData<List<Lesson>> getLessonsByLevelAndCategory(String level, String category) {
        return lessonDao.getLessonsByLevelAndCategory(level, category);
    }

    public LiveData<Lesson> getLessonById(int id) {
        return lessonDao.getLessonById(id);
    }

    public LiveData<Integer> getCompletedLessonsCount() {
        return lessonDao.getCompletedLessonsCount();
    }

    public void updateLessonStatus(int lessonId, boolean isCompleted) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            lessonDao.updateLessonStatus(lessonId, isCompleted);
            int completedCount = lessonDao.getCompletedLessonsCountSync();
            UserProfile profile = userProfileDao.getUserProfileSync();
            if (profile != null) {
                profile.setTotalLessonsCompleted(completedCount);
                userProfileDao.update(profile);
            }
        });
    }

    // Vocabulary
    public LiveData<List<Vocabulary>> getAllVocabulary() {
        return vocabularyDao.getAllVocabulary();
    }

    public LiveData<List<Vocabulary>> getVocabularyByLevel(String level) {
        return vocabularyDao.getVocabularyByLevel(level);
    }

    public LiveData<List<Vocabulary>> getVocabularyByLevelAndTopic(String level, String topic) {
        return vocabularyDao.getVocabularyByLevelAndTopic(level, topic);
    }

    public LiveData<List<Vocabulary>> searchVocabulary(String query) {
        return vocabularyDao.searchVocabulary(query);
    }

    public LiveData<List<Vocabulary>> getFavoriteVocabulary() {
        return vocabularyDao.getFavoriteVocabulary();
    }

    public LiveData<Integer> getMasteredWordsCount() {
        return vocabularyDao.getMasteredWordsCount();
    }

    public void updateVocabularyFavorite(int vocabId, boolean isFavorite) {
        AppDatabase.databaseWriteExecutor.execute(() -> vocabularyDao.updateFavoriteStatus(vocabId, isFavorite));
    }

    public void updateVocabularyMastery(int vocabId, int masteryLevel) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            vocabularyDao.updateMasteryLevel(vocabId, masteryLevel);
            int masteredCount = vocabularyDao.getMasteredWordsCountSync();
            UserProfile profile = userProfileDao.getUserProfileSync();
            if (profile != null) {
                profile.setTotalWordsLearned(masteredCount);
                userProfileDao.update(profile);
            }
        });
    }

    // Grammar Point
    public LiveData<List<GrammarPoint>> getAllGrammarPoints() {
        return grammarPointDao.getAllGrammarPoints();
    }

    public LiveData<List<GrammarPoint>> getGrammarPointsByLevel(String level) {
        return grammarPointDao.getGrammarPointsByLevel(level);
    }

    public List<GrammarPoint> getGrammarPointsByLevelSync(String level) {
        return grammarPointDao.getGrammarPointsByLevelSync(level);
    }

    public List<Conversation> getConversationsByLevelSync(String level) {
        return conversationDao.getConversationsByLevelSync(level);
    }

    public boolean hasStudiedTodaySync() {
        long startOfDay = getStartOfTodayMillis();
        List<DayProgress> a0 = dayProgressDao.getProgressForLevelSync(
                AppDatabase.DEFAULT_USER_ID, LevelConstants.A0);
        List<DayProgress> a1 = dayProgressDao.getProgressForLevelSync(
                AppDatabase.DEFAULT_USER_ID, LevelConstants.A1);
        List<DayProgress> a2 = dayProgressDao.getProgressForLevelSync(
                AppDatabase.DEFAULT_USER_ID, LevelConstants.A2);
        return hasActivitySince(a0, startOfDay) || hasActivitySince(a1, startOfDay)
                || hasActivitySince(a2, startOfDay);
    }

    private boolean hasActivitySince(List<DayProgress> list, long since) {
        if (list == null) {
            return false;
        }
        for (DayProgress p : list) {
            if (p.getLastOpenedAt() >= since || p.getCompletedAt() >= since) {
                return true;
            }
        }
        return false;
    }

    private long getStartOfTodayMillis() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
        cal.set(java.util.Calendar.MINUTE, 0);
        cal.set(java.util.Calendar.SECOND, 0);
        cal.set(java.util.Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    public int getVocabularyDueCountSync() {
        ensureVocabularyReviewInitializedSync();
        return vocabularyReviewProgressDao.getDueCountSync(
                AppDatabase.DEFAULT_USER_ID, System.currentTimeMillis());
    }

    public LiveData<GrammarPoint> getGrammarPointById(int id) {
        return grammarPointDao.getGrammarPointById(id);
    }

    // Conversation
    public LiveData<List<Conversation>> getConversationsByLevel(String level) {
        return conversationDao.getConversationsByLevel(level);
    }

    public LiveData<Conversation> getConversationById(int id) {
        return conversationDao.getConversationById(id);
    }

    public LiveData<List<ConversationLine>> getLinesForConversation(int conversationId) {
        return conversationDao.getLinesForConversation(conversationId);
    }

    // Quiz Question
    public LiveData<List<QuizQuestion>> getQuestionsForLesson(int lessonId) {
        return quizQuestionDao.getQuestionsForLesson(lessonId);
    }

    // Reset Progress
    public void resetProgress() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            lessonDao.resetProgress();
            vocabularyDao.resetProgress();
            speakingResultDao.deleteAll();
            dayProgressDao.resetLevelProgress(AppDatabase.DEFAULT_USER_ID, LevelConstants.A0);
            dayProgressDao.resetLevelProgress(AppDatabase.DEFAULT_USER_ID, LevelConstants.A1);
            dayProgressDao.resetLevelProgress(AppDatabase.DEFAULT_USER_ID, LevelConstants.A2);
            dayProgressDao.unlockFirstDay(AppDatabase.DEFAULT_USER_ID, LevelConstants.A0);
            vocabularyReviewProgressDao.deleteAllForUser(AppDatabase.DEFAULT_USER_ID);
            UserProfile profile = userProfileDao.getUserProfileSync();
            if (profile != null) {
                profile.setStreak(0);
                profile.setTotalLessonsCompleted(0);
                profile.setTotalWordsLearned(0);
                profile.setCurrentLevel(LevelConstants.A0);
                userProfileDao.update(profile);
            }
        });
    }
}
