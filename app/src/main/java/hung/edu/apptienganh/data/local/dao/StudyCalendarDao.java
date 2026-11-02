package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import hung.edu.apptienganh.data.local.entity.StudyDaySummary;
import hung.edu.apptienganh.data.local.entity.StudySession;
import java.util.List;

@Dao
public interface StudyCalendarDao {
    @Insert
    void insertSummary(StudyDaySummary summary);
    
    @Insert
    void insertSession(StudySession session);
    
    @Query("SELECT * FROM study_day_summaries ORDER BY dateString DESC")
    List<StudyDaySummary> getAllSummaries();
}
