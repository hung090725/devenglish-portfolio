package hung.edu.apptienganh.domain.srs;

import hung.edu.apptienganh.data.local.entity.VocabularyReviewProgress;

public final class SrsScheduler {

    private static final long DAY_MS = 24L * 60 * 60 * 1000;

    private SrsScheduler() {}

    public static boolean isDue(VocabularyReviewProgress progress, long now) {
        if (progress == null) return false;
        if (progress.getMasteryLevel() < 3) return true;
        return progress.getNextReviewAt() <= now;
    }

    public static boolean needsReview(VocabularyReviewProgress progress, long now) {
        return isDue(progress, now);
    }

    public static VocabularyReviewProgress applyRating(VocabularyReviewProgress progress, String rating, long now) {
        if (progress == null) return null;

        int mastery = progress.getMasteryLevel();
        int interval = progress.getIntervalDays();
        float ease = progress.getEaseFactor();
        int reviewCount = progress.getReviewCount();
        int correctStreak = progress.getCorrectStreak();
        int wrongCount = progress.getWrongCount();

        switch (rating) {
            case "AGAIN":
                mastery = Math.max(0, mastery - 1);
                interval = 0;
                correctStreak = 0;
                wrongCount++;
                ease = Math.max(1.3f, ease - 0.2f);
                progress.setNextReviewAt(now);
                break;
            case "HARD":
                mastery = Math.min(5, mastery + 1);
                interval = Math.max(1, interval);
                correctStreak = 0;
                wrongCount++;
                ease = Math.max(1.3f, ease - 0.05f);
                progress.setNextReviewAt(now + DAY_MS);
                break;
            case "GOOD":
                mastery = Math.min(5, mastery + 1);
                interval = interval <= 0 ? 1 : Math.min(30, interval * 2);
                correctStreak++;
                interval = Math.max(1, interval);
                progress.setNextReviewAt(now + interval * DAY_MS);
                break;
            case "EASY":
                mastery = Math.min(5, mastery + 2);
                interval = interval <= 0 ? 3 : Math.min(60, interval * 3);
                correctStreak++;
                ease = Math.min(3.0f, ease + 0.1f);
                progress.setNextReviewAt(now + interval * DAY_MS);
                break;
            default:
                break;
        }

        progress.setMasteryLevel(mastery);
        progress.setIntervalDays(interval);
        progress.setEaseFactor(ease);
        progress.setReviewCount(reviewCount + 1);
        progress.setCorrectStreak(correctStreak);
        progress.setWrongCount(wrongCount);
        progress.setLastReviewedAt(now);
        progress.setUpdatedAt(now);
        return progress;
    }

    public static VocabularyReviewProgress createNew(int userId, int vocabularyId, long now) {
        VocabularyReviewProgress p = new VocabularyReviewProgress();
        p.setUserId(userId);
        p.setVocabularyId(vocabularyId);
        p.setMasteryLevel(0);
        p.setEaseFactor(2.5f);
        p.setIntervalDays(0);
        p.setReviewCount(0);
        p.setCorrectStreak(0);
        p.setWrongCount(0);
        p.setNextReviewAt(now);
        p.setLastReviewedAt(0);
        p.setUpdatedAt(now);
        return p;
    }
}
