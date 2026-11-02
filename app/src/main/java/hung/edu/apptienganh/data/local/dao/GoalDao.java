package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import hung.edu.apptienganh.data.local.entity.LearningGoal;
import hung.edu.apptienganh.data.local.entity.GoalProgress;
import java.util.List;

@Dao
public interface GoalDao {
    @Insert
    long insertGoal(LearningGoal goal);
    
    @Insert
    void insertProgress(GoalProgress progress);
    
    @Query("SELECT * FROM learning_goals WHERE isActive = 1 LIMIT 1")
    LearningGoal getActiveGoal();
}
