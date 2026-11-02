package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.Achievement;

@Dao
public interface AchievementDao {
    @Query("SELECT * FROM achievements")
    LiveData<List<Achievement>> getAllAchievements();

    @Query("SELECT * FROM achievements")
    List<Achievement> getAllAchievementsSync();

    @Query("SELECT * FROM achievements WHERE code = :code LIMIT 1")
    Achievement getAchievementByCodeSync(String code);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Achievement> achievements);

    @Update
    void update(Achievement achievement);
}