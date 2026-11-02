package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import hung.edu.apptienganh.data.local.entity.ReadingPassage;
import hung.edu.apptienganh.data.local.entity.ReadingQuestion;
import hung.edu.apptienganh.data.local.entity.ReadingResult;
import java.util.List;

@Dao
public interface ReadingDao {
    @Insert
    long insertPassage(ReadingPassage passage);
    
    @Insert
    void insertQuestions(List<ReadingQuestion> questions);
    
    @Insert
    void insertResult(ReadingResult result);
    
    @Query("SELECT * FROM reading_passages WHERE level = :level")
    List<ReadingPassage> getPassagesByLevel(String level);
}
