package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import hung.edu.apptienganh.data.local.entity.GrammarPracticeExercise;
import hung.edu.apptienganh.data.local.entity.GrammarPracticeResult;

@Dao
public interface GrammarPracticeDao {
    @Query("SELECT * FROM grammar_practice_exercises WHERE level = :level")
    List<GrammarPracticeExercise> getExercisesByLevelSync(String level);
    
    @Insert
    void insertExercise(GrammarPracticeExercise exercise);
    
    @Insert
    void insertResult(GrammarPracticeResult result);
    
    @Query("SELECT * FROM grammar_practice_results ORDER BY createdAt DESC")
    List<GrammarPracticeResult> getAllResultsSync();
}
