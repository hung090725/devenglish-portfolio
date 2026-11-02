package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weakness_items")
public class WeaknessItem {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String category;
    public String level;
    public String title;
    public String descriptionVi;
    public int score;
    public String priority;
    public String recommendedAction;
    public String targetActivity;
    public String targetId;
    
    public WeaknessItem() {}
}
