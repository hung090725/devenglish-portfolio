package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "content_seed_meta",
        indices = {@Index(value = {"seedKey"}, unique = true)}
)
public class ContentSeedMeta {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String seedKey;
    private long seededAt;

    public ContentSeedMeta() {
    }

    @Ignore
    public ContentSeedMeta(String seedKey, long seededAt) {
        this.seedKey = seedKey;
        this.seededAt = seededAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeedKey() {
        return seedKey;
    }

    public void setSeedKey(String seedKey) {
        this.seedKey = seedKey;
    }

    public long getSeededAt() {
        return seededAt;
    }

    public void setSeededAt(long seededAt) {
        this.seededAt = seededAt;
    }
}
