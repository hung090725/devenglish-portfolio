package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import hung.edu.apptienganh.data.local.entity.ExamSession;
import hung.edu.apptienganh.data.local.entity.ExamQuestion;
import hung.edu.apptienganh.data.local.entity.ExamResult;
import java.util.List;

@Dao
public interface ExamDao {
    @Insert
    long insertSession(ExamSession session);
    
    @Insert
    void insertQuestions(List<ExamQuestion> questions);
    
    @Insert
    void insertResult(ExamResult result);
    
    @Query("SELECT * FROM exam_sessions ORDER BY startedAt DESC")
    List<ExamSession> getAllSessions();
}
