package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "speaking_weak_sentences")
public class SpeakingWeakSentence {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String level;
    private String sentenceEn;
    private String sentenceVi;
    private String ipa;
    private long lastMistakeAt;
    private int lowestScore;
    private boolean isResolved;

    public SpeakingWeakSentence(String level, String sentenceEn, String sentenceVi, String ipa, long lastMistakeAt, int lowestScore, boolean isResolved) {
        this.level = level;
        this.sentenceEn = sentenceEn;
        this.sentenceVi = sentenceVi;
        this.ipa = ipa;
        this.lastMistakeAt = lastMistakeAt;
        this.lowestScore = lowestScore;
        this.isResolved = isResolved;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getSentenceEn() { return sentenceEn; }
    public void setSentenceEn(String sentenceEn) { this.sentenceEn = sentenceEn; }

    public String getSentenceVi() { return sentenceVi; }
    public void setSentenceVi(String sentenceVi) { this.sentenceVi = sentenceVi; }

    public String getIpa() { return ipa; }
    public void setIpa(String ipa) { this.ipa = ipa; }

    public long getLastMistakeAt() { return lastMistakeAt; }
    public void setLastMistakeAt(long lastMistakeAt) { this.lastMistakeAt = lastMistakeAt; }

    public int getLowestScore() { return lowestScore; }
    public void setLowestScore(int lowestScore) { this.lowestScore = lowestScore; }

    public boolean isResolved() { return isResolved; }
    public void setResolved(boolean resolved) { isResolved = resolved; }
}
