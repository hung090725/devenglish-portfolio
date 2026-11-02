package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.QuizAttempt;

@Dao
public interface QuizAttemptDao {

    @Insert
    long insert(QuizAttempt attempt);

    @Query("SELECT * FROM quiz_attempts WHERE userId = :userId AND learningDayId = :learningDayId ORDER BY createdAt DESC LIMIT 1")
    QuizAttempt getLatestForDaySync(int userId, int learningDayId);

    @Query("SELECT MAX(score) FROM quiz_attempts WHERE userId = :userId")
    LiveData<Integer> getBestScore(int userId);

    @Query("SELECT MAX(score) FROM quiz_attempts WHERE userId = :userId")
    Integer getBestScoreSync(int userId);

    @Query("SELECT COUNT(*) FROM quiz_attempts WHERE userId = :userId")
    int getCountSync(int userId);

    @Query("SELECT * FROM quiz_attempts WHERE id = :attemptId LIMIT 1")
    QuizAttempt getByIdSync(int attemptId);
}
