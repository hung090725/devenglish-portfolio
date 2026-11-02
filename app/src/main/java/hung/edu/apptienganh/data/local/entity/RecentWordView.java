package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recent_word_views")
public class RecentWordView {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int wordId;
    public long viewedAt;
    
    public RecentWordView(int wordId, long viewedAt) {
        this.wordId = wordId;
        this.viewedAt = viewedAt;
    }
}
