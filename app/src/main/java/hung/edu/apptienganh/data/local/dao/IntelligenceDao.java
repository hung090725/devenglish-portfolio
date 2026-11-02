package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import hung.edu.apptienganh.data.local.entity.LearningInsight;
import hung.edu.apptienganh.data.local.entity.WeaknessItem;

@Dao
public interface IntelligenceDao {
    @Query("SELECT * FROM learning_insights ORDER BY createdAt DESC")
    List<LearningInsight> getAllInsightsSync();
    
    @Insert
    void insertInsights(List<LearningInsight> insights);
    
    @Query("DELETE FROM learning_insights")
    void clearInsights();

    @Query("SELECT * FROM weakness_items ORDER BY score DESC")
    List<WeaknessItem> getAllWeaknessesSync();
    
    @Insert
    void insertWeaknesses(List<WeaknessItem> items);
    
    @Query("DELETE FROM weakness_items")
    void clearWeaknesses();
}
