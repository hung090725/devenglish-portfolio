package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "learning_insights")
public class LearningInsight {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String type;
    public String title;
    public String descriptionVi;
    public String targetId;
    public String priority;
    public long createdAt;
    
    public LearningInsight(String type, String title, String descriptionVi, String targetId, String priority, long createdAt) {
        this.type = type; this.title = title; this.descriptionVi = descriptionVi; 
        this.targetId = targetId; this.priority = priority; this.createdAt = createdAt;
    }
}
