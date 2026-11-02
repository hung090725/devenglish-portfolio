package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.QuizQuestion;

@Dao
public interface QuizQuestionDao {
    @Query("SELECT * FROM quiz_questions WHERE lessonId = :lessonId ORDER BY id ASC")
    LiveData<List<QuizQuestion>> getQuestionsForLesson(int lessonId);

    @Query("SELECT * FROM quiz_questions WHERE lessonId = :lessonId ORDER BY id ASC")
    List<QuizQuestion> getQuestionsForLessonSync(int lessonId);

    @Query("SELECT * FROM quiz_questions WHERE learningDayId = :learningDayId ORDER BY id ASC")
    LiveData<List<QuizQuestion>> getQuestionsForLearningDay(int learningDayId);

    @Query("SELECT * FROM quiz_questions WHERE learningDayId = :learningDayId ORDER BY id ASC")
    List<QuizQuestion> getQuestionsForLearningDaySync(int learningDayId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<QuizQuestion> questions);
}
