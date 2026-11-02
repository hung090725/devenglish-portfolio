package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_vocabulary")
public class FavoriteVocabulary {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int vocabularyId;
    private String level;
    private long addedAt;

    public FavoriteVocabulary(int vocabularyId, String level, long addedAt) {
        this.vocabularyId = vocabularyId;
        this.level = level;
        this.addedAt = addedAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVocabularyId() { return vocabularyId; }
    public void setVocabularyId(int vocabularyId) { this.vocabularyId = vocabularyId; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public long getAddedAt() { return addedAt; }
    public void setAddedAt(long addedAt) { this.addedAt = addedAt; }
}
