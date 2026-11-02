package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.LearningDay;

@Dao
public interface LearningDayDao {

    @Query("SELECT * FROM learning_days WHERE level = :level ORDER BY dayIndex ASC")
    LiveData<List<LearningDay>> getDaysByLevel(String level);

    @Query("SELECT * FROM learning_days WHERE level = :level AND dayIndex = :dayIndex LIMIT 1")
    LiveData<LearningDay> getDayByIndex(String level, int dayIndex);

    @Query("SELECT * FROM learning_days WHERE level = :level AND dayIndex = :dayIndex LIMIT 1")
    LearningDay getDayByIndexSync(String level, int dayIndex);

    @Query("SELECT * FROM learning_days WHERE id = :id LIMIT 1")
    LearningDay getDayByIdSync(int id);

    @Query("SELECT COUNT(*) FROM learning_days WHERE level = :level")
    int getDayCountSync(String level);

    @Query("SELECT * FROM learning_days WHERE level = :level ORDER BY dayIndex ASC")
    List<LearningDay> getDaysByLevelSync(String level);

    @Query("SELECT * FROM learning_days ORDER BY id ASC")
    List<LearningDay> getAllLearningDaysSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<LearningDay> days);
}
