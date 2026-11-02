package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "learning_goals")
public class LearningGoal {
    @PrimaryKey(autoGenerate = true) public int id;
    public String type;
    public String title;
    public String descriptionVi;
    public String recommendedLevels;
    public int dailyMinutes;
    public String weeklyPlan;
    public String suggestedModules;
    public boolean isActive;
    public long createdAt;
    public LearningGoal() {}
}
