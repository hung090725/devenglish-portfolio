package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import hung.edu.apptienganh.data.local.entity.LearningEvent;

@Dao
public interface AnalyticsDao {
    @Query("SELECT * FROM learning_events ORDER BY createdAt DESC")
    List<LearningEvent> getAllEventsSync();
    
    @Insert
    void insertEvent(LearningEvent event);
}
