package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import hung.edu.apptienganh.data.local.entity.SentenceBuilderExercise;
import hung.edu.apptienganh.data.local.entity.SentenceBuilderResult;

@Dao
public interface SentenceBuilderDao {
    @Query("SELECT * FROM sentence_builder_exercises WHERE level = :level ORDER BY orderIndex ASC")
    List<SentenceBuilderExercise> getExercisesByLevelSync(String level);
    
    @Insert
    void insertExercise(SentenceBuilderExercise exercise);
    
    @Insert
    void insertResult(SentenceBuilderResult result);
    
    @Query("SELECT * FROM sentence_builder_results ORDER BY createdAt DESC")
    List<SentenceBuilderResult> getAllResultsSync();
}
