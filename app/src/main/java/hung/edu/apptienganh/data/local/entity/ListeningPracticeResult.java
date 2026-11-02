package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "listening_practice_results")
public class ListeningPracticeResult {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String level;
    private String sentenceEn;
    private String userAnswer;
    private int score; // 0-100 based on similarity
    private long createdAt;

    public ListeningPracticeResult(String level, String sentenceEn, String userAnswer, int score, long createdAt) {
        this.level = level;
        this.sentenceEn = sentenceEn;
        this.userAnswer = userAnswer;
        this.score = score;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getSentenceEn() { return sentenceEn; }
    public void setSentenceEn(String sentenceEn) { this.sentenceEn = sentenceEn; }

    public String getUserAnswer() { return userAnswer; }
    public void setUserAnswer(String userAnswer) { this.userAnswer = userAnswer; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}
