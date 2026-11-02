package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import hung.edu.apptienganh.data.local.entity.FlashcardResult;
import java.util.List;

@Dao
public interface FlashcardDao {
    @Insert
    void insertResult(FlashcardResult result);
    
    @Query("SELECT * FROM flashcard_results ORDER BY createdAt DESC")
    List<FlashcardResult> getAllResults();
}
