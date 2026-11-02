package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.QuizWrongAnswerReview;

@Dao
public interface QuizWrongAnswerReviewDao {
    @Query("SELECT * FROM quiz_wrong_answer_reviews WHERE isResolved = 0 ORDER BY lastMistakeAt DESC")
    LiveData<List<QuizWrongAnswerReview>> getUnresolvedMistakes();

    @Query("SELECT * FROM quiz_wrong_answer_reviews WHERE quizQuestionId = :id LIMIT 1")
    QuizWrongAnswerReview getByQuestionIdSync(int id);
    
    @Query("SELECT * FROM quiz_wrong_answer_reviews")
    List<QuizWrongAnswerReview> getAllSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(QuizWrongAnswerReview review);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<QuizWrongAnswerReview> reviews);

    @Update
    void update(QuizWrongAnswerReview review);
}