package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "vocabulary_review_progress",
        indices = {@Index(value = {"userId", "vocabularyId"}, unique = true)}
)
public class VocabularyReviewProgress {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int vocabularyId;
    private int userId;
    private int masteryLevel;
    private float easeFactor;
    private int intervalDays;
    private int reviewCount;
    private int correctStreak;
    private int wrongCount;
    private long nextReviewAt;
    private long lastReviewedAt;
    private long updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(int vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMasteryLevel() {
        return masteryLevel;
    }

    public void setMasteryLevel(int masteryLevel) {
        this.masteryLevel = masteryLevel;
    }

    public float getEaseFactor() {
        return easeFactor;
    }

    public void setEaseFactor(float easeFactor) {
        this.easeFactor = easeFactor;
    }

    public int getIntervalDays() {
        return intervalDays;
    }

    public void setIntervalDays(int intervalDays) {
        this.intervalDays = intervalDays;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getCorrectStreak() {
        return correctStreak;
    }

    public void setCorrectStreak(int correctStreak) {
        this.correctStreak = correctStreak;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public void setWrongCount(int wrongCount) {
        this.wrongCount = wrongCount;
    }

    public long getNextReviewAt() {
        return nextReviewAt;
    }

    public void setNextReviewAt(long nextReviewAt) {
        this.nextReviewAt = nextReviewAt;
    }

    public long getLastReviewedAt() {
        return lastReviewedAt;
    }

    public void setLastReviewedAt(long lastReviewedAt) {
        this.lastReviewedAt = lastReviewedAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
