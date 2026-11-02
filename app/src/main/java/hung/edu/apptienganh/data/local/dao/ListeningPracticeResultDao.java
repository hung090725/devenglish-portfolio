package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.ListeningPracticeResult;

@Dao
public interface ListeningPracticeResultDao {
    @Query("SELECT * FROM listening_practice_results ORDER BY createdAt DESC")
    LiveData<List<ListeningPracticeResult>> getAllResults();

    @Query("SELECT * FROM listening_practice_results ORDER BY createdAt DESC")
    List<ListeningPracticeResult> getAllResultsSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ListeningPracticeResult result);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ListeningPracticeResult> results);
}