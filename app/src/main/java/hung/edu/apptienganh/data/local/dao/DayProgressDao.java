package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.DayProgress;

@Dao
public interface DayProgressDao {

    @Query("SELECT * FROM day_progress WHERE userId = :userId AND level = :level ORDER BY dayIndex ASC")
    LiveData<List<DayProgress>> getProgressForLevel(int userId, String level);

    @Query("SELECT * FROM day_progress WHERE userId = :userId AND level = :level ORDER BY dayIndex ASC")
    List<DayProgress> getProgressForLevelSync(int userId, String level);

    @Query("SELECT * FROM day_progress WHERE userId = :userId ORDER BY level ASC, dayIndex ASC")
    List<DayProgress> getAllProgressSync(int userId);

    @Query("SELECT * FROM day_progress WHERE userId = :userId AND level = :level AND dayIndex = :dayIndex LIMIT 1")
    DayProgress getProgressSync(int userId, String level, int dayIndex);

    @Query("SELECT COUNT(*) FROM day_progress WHERE userId = :userId AND level = :level AND isCompleted = 1")
    LiveData<Integer> getCompletedDayCount(int userId, String level);

    @Query("SELECT COUNT(*) FROM day_progress WHERE userId = :userId AND level = :level AND isCompleted = 1")
    int getCompletedDayCountSync(int userId, String level);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<DayProgress> progressList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(DayProgress progress);

    @Update
    void update(DayProgress progress);

    @Query("SELECT * FROM day_progress WHERE userId = :userId AND level = :level AND status = 'IN_PROGRESS' ORDER BY lastOpenedAt DESC LIMIT 1")
    DayProgress getInProgressDaySync(int userId, String level);

    @Query("UPDATE day_progress SET isCompleted = 0, isUnlocked = 0, score = 0, speakingScore = 0, quizScore = 0, bestQuizScore = 0, completedAt = 0, updatedAt = 0, status = 'LOCKED', currentSectionIndex = 0, lastOpenedAt = 0 WHERE userId = :userId AND level = :level")
    void resetLevelProgress(int userId, String level);

    @Query("SELECT * FROM day_progress WHERE userId = :userId AND level = :level AND status = 'IN_PROGRESS' ORDER BY lastOpenedAt DESC LIMIT 1")
    LiveData<DayProgress> getInProgressDay(int userId, String level);

    @Query("UPDATE day_progress SET isUnlocked = 1, status = 'AVAILABLE' WHERE userId = :userId AND level = :level AND dayIndex = 1")
    void unlockFirstDay(int userId, String level);
}
