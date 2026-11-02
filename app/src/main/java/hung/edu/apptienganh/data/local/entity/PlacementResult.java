package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "placement_results")
public class PlacementResult {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long timestamp;
    private int score;
    private int totalQuestions;
    private String suggestedLevel; // A0, A1, A2, B1, B2, C1
    private String explanationVi;

    public PlacementResult(long timestamp, int score, int totalQuestions, String suggestedLevel, String explanationVi) {
        this.timestamp = timestamp;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.suggestedLevel = suggestedLevel;
        this.explanationVi = explanationVi;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }

    public String getSuggestedLevel() { return suggestedLevel; }
    public void setSuggestedLevel(String suggestedLevel) { this.suggestedLevel = suggestedLevel; }

    public String getExplanationVi() { return explanationVi; }
    public void setExplanationVi(String explanationVi) { this.explanationVi = explanationVi; }
}
