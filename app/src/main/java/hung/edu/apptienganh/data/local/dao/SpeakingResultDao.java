package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.SpeakingResult;

@Dao
public interface SpeakingResultDao {
    @Query("SELECT * FROM speaking_results ORDER BY createdAt DESC")
    LiveData<List<SpeakingResult>> getAllResults();

    @Query("SELECT AVG(score) FROM speaking_results")
    LiveData<Double> getAverageScore();

    @Query("SELECT AVG(score) FROM speaking_results WHERE userId = :userId")
    LiveData<Double> getAverageScoreForUser(int userId);

    @Query("SELECT AVG(score) FROM speaking_results WHERE userId = :userId")
    Double getAverageScoreSync(int userId);

    @Query("SELECT COUNT(*) FROM speaking_results WHERE userId = :userId")
    LiveData<Integer> getAttemptCount(int userId);

    @Query("SELECT COUNT(*) FROM speaking_results WHERE userId = :userId")
    int getCountSync(int userId);

    @Query("SELECT * FROM speaking_results WHERE userId = :userId AND learningDayId = :learningDayId ORDER BY createdAt DESC LIMIT 1")
    SpeakingResult getLatestForDaySync(int userId, int learningDayId);

    @Query("SELECT * FROM speaking_results WHERE userId = :userId ORDER BY score ASC, createdAt DESC LIMIT 1")
    LiveData<SpeakingResult> getLowestRecentResult(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(SpeakingResult result);

    @Query("DELETE FROM speaking_results")
    void deleteAll();
}
