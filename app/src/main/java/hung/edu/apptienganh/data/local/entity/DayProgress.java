package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "day_progress",
        indices = {@Index(value = {"userId", "level", "dayIndex"}, unique = true)}
)
public class DayProgress {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userId;
    private String level;
    private int dayIndex;
    private boolean isUnlocked;
    private boolean isCompleted;
    private long completedAt;
    private int score;
    private int speakingScore;
    private int quizScore;
    private int bestQuizScore;
    private String status;
    private int currentSectionIndex;
    private long lastOpenedAt;
    private long updatedAt;

    public DayProgress() {
    }

    @Ignore
    public DayProgress(int userId, String level, int dayIndex, boolean isUnlocked,
                       boolean isCompleted, long completedAt, int score,
                       int speakingScore, int quizScore, long updatedAt) {
        this(userId, level, dayIndex, isUnlocked, isCompleted, completedAt, score,
                speakingScore, quizScore, 0, resolveStatus(isUnlocked, isCompleted),
                0, 0, updatedAt);
    }

    @Ignore
    public DayProgress(int userId, String level, int dayIndex, boolean isUnlocked,
                       boolean isCompleted, long completedAt, int score,
                       int speakingScore, int quizScore, int bestQuizScore,
                       String status, int currentSectionIndex, long lastOpenedAt,
                       long updatedAt) {
        this.userId = userId;
        this.level = level;
        this.dayIndex = dayIndex;
        this.isUnlocked = isUnlocked;
        this.isCompleted = isCompleted;
        this.completedAt = completedAt;
        this.score = score;
        this.speakingScore = speakingScore;
        this.quizScore = quizScore;
        this.bestQuizScore = bestQuizScore;
        this.status = status;
        this.currentSectionIndex = currentSectionIndex;
        this.lastOpenedAt = lastOpenedAt;
        this.updatedAt = updatedAt;
    }

    private static String resolveStatus(boolean unlocked, boolean completed) {
        if (completed) return "COMPLETED";
        if (unlocked) return "AVAILABLE";
        return "LOCKED";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public long getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(long completedAt) {
        this.completedAt = completedAt;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSpeakingScore() {
        return speakingScore;
    }

    public void setSpeakingScore(int speakingScore) {
        this.speakingScore = speakingScore;
    }

    public int getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(int quizScore) {
        this.quizScore = quizScore;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getBestQuizScore() {
        return bestQuizScore;
    }

    public void setBestQuizScore(int bestQuizScore) {
        this.bestQuizScore = bestQuizScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCurrentSectionIndex() {
        return currentSectionIndex;
    }

    public void setCurrentSectionIndex(int currentSectionIndex) {
        this.currentSectionIndex = currentSectionIndex;
    }

    public long getLastOpenedAt() {
        return lastOpenedAt;
    }

    public void setLastOpenedAt(long lastOpenedAt) {
        this.lastOpenedAt = lastOpenedAt;
    }
}
