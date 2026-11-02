package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_profiles")
public class UserProfile {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String name;
    private String currentLevel; // A0, A1, A2, B1, B2, C1
    private int dailyGoalMinutes;
    private int streak;
    private int totalLessonsCompleted;
    private int totalWordsLearned;

    // Constructor rỗng cần thiết cho Room
    public UserProfile() {
    }

    @Ignore
    public UserProfile(String name, String currentLevel, int dailyGoalMinutes, int streak, int totalLessonsCompleted, int totalWordsLearned) {
        this.name = name;
        this.currentLevel = currentLevel;
        this.dailyGoalMinutes = dailyGoalMinutes;
        this.streak = streak;
        this.totalLessonsCompleted = totalLessonsCompleted;
        this.totalWordsLearned = totalWordsLearned;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getDailyGoalMinutes() {
        return dailyGoalMinutes;
    }

    public void setDailyGoalMinutes(int dailyGoalMinutes) {
        this.dailyGoalMinutes = dailyGoalMinutes;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public int getTotalLessonsCompleted() {
        return totalLessonsCompleted;
    }

    public void setTotalLessonsCompleted(int totalLessonsCompleted) {
        this.totalLessonsCompleted = totalLessonsCompleted;
    }

    public int getTotalWordsLearned() {
        return totalWordsLearned;
    }

    public void setTotalWordsLearned(int totalWordsLearned) {
        this.totalWordsLearned = totalWordsLearned;
    }
}
