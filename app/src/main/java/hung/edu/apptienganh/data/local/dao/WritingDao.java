package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import hung.edu.apptienganh.data.local.entity.WritingPrompt;
import hung.edu.apptienganh.data.local.entity.WritingAttempt;

@Dao
public interface WritingDao {
    @Query("SELECT * FROM writing_prompts WHERE level = :level ORDER BY orderIndex ASC")
    List<WritingPrompt> getPromptsByLevelSync(String level);
    
    @Insert
    void insertPrompt(WritingPrompt prompt);
    
    @Insert
    void insertAttempt(WritingAttempt attempt);
    
    @Query("SELECT * FROM writing_attempts ORDER BY createdAt DESC")
    List<WritingAttempt> getAllAttemptsSync();
}
