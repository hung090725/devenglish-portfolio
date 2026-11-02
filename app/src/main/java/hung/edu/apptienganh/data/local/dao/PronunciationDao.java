package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import hung.edu.apptienganh.data.local.entity.PronunciationDrill;
import hung.edu.apptienganh.data.local.entity.MinimalPairExercise;
import hung.edu.apptienganh.data.local.entity.PronunciationAttempt;
import java.util.List;

@Dao
public interface PronunciationDao {
    @Insert
    void insertDrills(List<PronunciationDrill> drills);
    
    @Insert
    void insertMinimalPairs(List<MinimalPairExercise> pairs);
    
    @Insert
    void insertAttempt(PronunciationAttempt attempt);
    
    @Query("SELECT * FROM pronunciation_drills")
    List<PronunciationDrill> getAllDrills();
}
