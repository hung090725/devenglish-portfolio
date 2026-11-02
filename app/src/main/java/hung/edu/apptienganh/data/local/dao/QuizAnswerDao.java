package hung.edu.apptienganh.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.QuizAnswer;

@Dao
public interface QuizAnswerDao {

    @Insert
    void insertAll(List<QuizAnswer> answers);

    @Query("SELECT * FROM quiz_answers WHERE attemptId = :attemptId ORDER BY id ASC")
    List<QuizAnswer> getAnswersForAttempt(int attemptId);
}
