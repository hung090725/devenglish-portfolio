package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "learning_events")
public class LearningEvent {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String eventType;
    public String screenName;
    public String level;
    public String metadataJson;
    public long createdAt;
    
    public LearningEvent() {}
}
