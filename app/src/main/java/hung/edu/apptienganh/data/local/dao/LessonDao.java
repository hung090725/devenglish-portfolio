package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.Lesson;

@Dao
public interface LessonDao {
    @Query("SELECT * FROM lessons ORDER BY orderIndex ASC")
    LiveData<List<Lesson>> getAllLessons();

    @Query("SELECT * FROM lessons WHERE level = :level ORDER BY orderIndex ASC")
    LiveData<List<Lesson>> getLessonsByLevel(String level);

    @Query("SELECT * FROM lessons WHERE level = :level AND category = :category ORDER BY orderIndex ASC")
    LiveData<List<Lesson>> getLessonsByLevelAndCategory(String level, String category);

    @Query("SELECT * FROM lessons WHERE id = :id LIMIT 1")
    LiveData<Lesson> getLessonById(int id);

    @Query("SELECT COUNT(*) FROM lessons WHERE isCompleted = 1")
    LiveData<Integer> getCompletedLessonsCount();

    @Query("SELECT COUNT(*) FROM lessons WHERE isCompleted = 1")
    int getCompletedLessonsCountSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Lesson> lessons);

    @Update
    void update(Lesson lesson);

    @Query("UPDATE lessons SET isCompleted = :isCompleted WHERE id = :lessonId")
    void updateLessonStatus(int lessonId, boolean isCompleted);

    @Query("UPDATE lessons SET isCompleted = 0")
    void resetProgress();
}
