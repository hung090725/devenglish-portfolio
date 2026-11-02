package hung.edu.apptienganh.domain.path;

import hung.edu.apptienganh.data.local.dao.DayProgressDao;
import hung.edu.apptienganh.data.local.dao.LearningDayDao;
import hung.edu.apptienganh.data.local.entity.DayProgress;
import hung.edu.apptienganh.domain.model.DaySection;
import hung.edu.apptienganh.domain.model.LevelConstants;

public final class ContinueDayResolver {

    public static final class ContinueTarget {
        private final String level;
        private final int dayIndex;
        private final int sectionIndex;

        public ContinueTarget(String level, int dayIndex, int sectionIndex) {
            this.level = level;
            this.dayIndex = dayIndex;
            this.sectionIndex = sectionIndex;
        }

        public String getLevel() {
            return level;
        }

        public int getDayIndex() {
            return dayIndex;
        }

        public int getSectionIndex() {
            return sectionIndex;
        }
    }

    private ContinueDayResolver() {}

    public static ContinueTarget resolve(
            DayProgressDao dayProgressDao,
            LearningDayDao learningDayDao,
            int userId) {

        // Check A0 in-progress first
        DayProgress a0InProgress = dayProgressDao.getInProgressDaySync(userId, LevelConstants.A0);
        if (a0InProgress != null) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.A0);
        }

        if (!isA0Completed(dayProgressDao, learningDayDao, userId)) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.A0);
        }

        // A0 done – check A1
        if (!isA1Unlocked(dayProgressDao, userId)) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.A0);
        }

        DayProgress a1InProgress = dayProgressDao.getInProgressDaySync(userId, LevelConstants.A1);
        if (a1InProgress != null) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.A1);
        }

        if (!isA1Completed(dayProgressDao, learningDayDao, userId)) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.A1);
        }

        // A1 done – check A2
        if (!isA2Unlocked(dayProgressDao, userId)) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.A1);
        }

        DayProgress a2InProgress = dayProgressDao.getInProgressDaySync(userId, LevelConstants.A2);
        if (a2InProgress != null) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.A2);
        }

        if (!isA2Completed(dayProgressDao, learningDayDao, userId)) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.A2);
        }

        // A2 done - check B1
        if (!isB1Unlocked(dayProgressDao, userId)) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.A2);
        }

        DayProgress b1InProgress = dayProgressDao.getInProgressDaySync(userId, LevelConstants.B1);
        if (b1InProgress != null) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.B1);
        }

        if (!isB1Completed(dayProgressDao, learningDayDao, userId)) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.B1);
        }

        // B1 done - check B2
        if (!isB2Unlocked(dayProgressDao, userId)) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.B1);
        }

        DayProgress b2InProgress = dayProgressDao.getInProgressDaySync(userId, LevelConstants.B2);
        if (b2InProgress != null) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.B2);
        }

        if (!isB2Completed(dayProgressDao, learningDayDao, userId)) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.B2);
        }

        // B2 done - check C1
        if (!isC1Unlocked(dayProgressDao, userId)) {
            return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.B2);
        }

        return resolveForLevel(dayProgressDao, learningDayDao, userId, LevelConstants.C1);
    }

    public static ContinueTarget resolveForLevel(
            DayProgressDao dayProgressDao,
            LearningDayDao learningDayDao,
            int userId,
            String level) {

        DayProgress inProgress = dayProgressDao.getInProgressDaySync(userId, level);
        if (inProgress != null) {
            return new ContinueTarget(level, inProgress.getDayIndex(),
                    inProgress.getCurrentSectionIndex());
        }

        int total = learningDayDao.getDayCountSync(level);
        for (int day = 1; day <= total; day++) {
            DayProgress p = dayProgressDao.getProgressSync(userId, level, day);
            if (p == null || !p.isCompleted()) {
                int section = DaySection.OBJECTIVE;
                if (p != null && DaySection.STATUS_IN_PROGRESS.equals(p.getStatus())) {
                    section = p.getCurrentSectionIndex();
                } else if (p != null && p.isUnlocked()) {
                    section = DaySection.OBJECTIVE;
                }
                return new ContinueTarget(level, day, section);
            }
        }

        return new ContinueTarget(level, Math.max(1, total), DaySection.OBJECTIVE);
    }

    public static boolean isA0Completed(
            DayProgressDao dayProgressDao,
            LearningDayDao learningDayDao,
            int userId) {
        int total = learningDayDao.getDayCountSync(LevelConstants.A0);
        if (total <= 0) return false;
        DayProgress last = dayProgressDao.getProgressSync(userId, LevelConstants.A0, total);
        return last != null && last.isCompleted();
    }

    public static boolean isA1Completed(
            DayProgressDao dayProgressDao,
            LearningDayDao learningDayDao,
            int userId) {
        int total = learningDayDao.getDayCountSync(LevelConstants.A1);
        if (total <= 0) return false;
        DayProgress last = dayProgressDao.getProgressSync(userId, LevelConstants.A1, total);
        return last != null && last.isCompleted();
    }

    public static boolean isA1Unlocked(DayProgressDao dayProgressDao, int userId) {
        DayProgress first = dayProgressDao.getProgressSync(userId, LevelConstants.A1, 1);
        return first != null && first.isUnlocked();
    }

    public static boolean isA2Completed(
            DayProgressDao dayProgressDao,
            LearningDayDao learningDayDao,
            int userId) {
        int total = learningDayDao.getDayCountSync(LevelConstants.A2);
        if (total <= 0) return false;
        DayProgress last = dayProgressDao.getProgressSync(userId, LevelConstants.A2, total);
        return last != null && last.isCompleted();
    }

    public static boolean isA2Unlocked(DayProgressDao dayProgressDao, int userId) {
        DayProgress first = dayProgressDao.getProgressSync(userId, LevelConstants.A2, 1);
        return first != null && first.isUnlocked();
    }

    public static boolean isB1Completed(
            DayProgressDao dayProgressDao,
            LearningDayDao learningDayDao,
            int userId) {
        int total = learningDayDao.getDayCountSync(LevelConstants.B1);
        if (total <= 0) return false;
        DayProgress last = dayProgressDao.getProgressSync(userId, LevelConstants.B1, total);
        return last != null && last.isCompleted();
    }

    public static boolean isB1Unlocked(DayProgressDao dayProgressDao, int userId) {
        DayProgress first = dayProgressDao.getProgressSync(userId, LevelConstants.B1, 1);
        return first != null && first.isUnlocked();
    }

    public static boolean isB2Completed(
            DayProgressDao dayProgressDao,
            LearningDayDao learningDayDao,
            int userId) {
        int total = learningDayDao.getDayCountSync(LevelConstants.B2);
        if (total <= 0) return false;
        DayProgress last = dayProgressDao.getProgressSync(userId, LevelConstants.B2, total);
        return last != null && last.isCompleted();
    }

    public static boolean isB2Unlocked(DayProgressDao dayProgressDao, int userId) {
        DayProgress first = dayProgressDao.getProgressSync(userId, LevelConstants.B2, 1);
        return first != null && first.isUnlocked();
    }

    public static boolean isC1Unlocked(DayProgressDao dayProgressDao, int userId) {
        DayProgress first = dayProgressDao.getProgressSync(userId, LevelConstants.C1, 1);
        return first != null && first.isUnlocked();
    }
}
