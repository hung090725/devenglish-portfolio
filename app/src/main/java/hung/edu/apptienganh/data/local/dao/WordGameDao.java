package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import hung.edu.apptienganh.data.local.entity.WordGameSession;
import hung.edu.apptienganh.data.local.entity.WordGameResult;
import java.util.List;

@Dao
public interface WordGameDao {
    @Insert
    long insertSession(WordGameSession session);
    
    @Insert
    void insertResults(List<WordGameResult> results);
    
    @Query("SELECT * FROM word_game_sessions ORDER BY createdAt DESC")
    List<WordGameSession> getAllSessions();
}
