package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.PlacementResult;

@Dao
public interface PlacementResultDao {
    @Query("SELECT * FROM placement_results ORDER BY timestamp DESC")
    LiveData<List<PlacementResult>> getAllResults();

    @Query("SELECT * FROM placement_results ORDER BY timestamp DESC")
    List<PlacementResult> getAllResultsSync();

    @Query("SELECT * FROM placement_results ORDER BY timestamp DESC LIMIT 1")
    PlacementResult getLatestResultSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PlacementResult result);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PlacementResult> results);
}