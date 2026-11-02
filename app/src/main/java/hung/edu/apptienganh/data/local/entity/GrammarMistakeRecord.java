package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grammar_mistake_records")
public class GrammarMistakeRecord {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int grammarPointId;
    private String level;
    private long lastMistakeAt;
    private int mistakeCount;
    private boolean isResolved;

    public GrammarMistakeRecord(int grammarPointId, String level, long lastMistakeAt, int mistakeCount, boolean isResolved) {
        this.grammarPointId = grammarPointId;
        this.level = level;
        this.lastMistakeAt = lastMistakeAt;
        this.mistakeCount = mistakeCount;
        this.isResolved = isResolved;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getGrammarPointId() { return grammarPointId; }
    public void setGrammarPointId(int grammarPointId) { this.grammarPointId = grammarPointId; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public long getLastMistakeAt() { return lastMistakeAt; }
    public void setLastMistakeAt(long lastMistakeAt) { this.lastMistakeAt = lastMistakeAt; }

    public int getMistakeCount() { return mistakeCount; }
    public void setMistakeCount(int mistakeCount) { this.mistakeCount = mistakeCount; }

    public boolean isResolved() { return isResolved; }
    public void setResolved(boolean resolved) { isResolved = resolved; }
}
