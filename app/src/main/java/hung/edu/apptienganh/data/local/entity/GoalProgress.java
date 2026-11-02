package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "goal_progress")
public class GoalProgress {
    @PrimaryKey(autoGenerate = true) public int id;
    public int goalId;
    public int progressPercent;
    public long lastStudiedAt;
    public int currentStreak;
    public GoalProgress() {}
}
