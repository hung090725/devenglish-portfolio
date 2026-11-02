package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.VocabularyReviewProgress;

@Dao
public interface VocabularyReviewProgressDao {

    @Query("SELECT * FROM vocabulary_review_progress WHERE userId = :userId")
    LiveData<List<VocabularyReviewProgress>> getAllForUser(int userId);

    @Query("SELECT * FROM vocabulary_review_progress WHERE userId = :userId")
    List<VocabularyReviewProgress> getAllForUserSync(int userId);

    @Query("SELECT * FROM vocabulary_review_progress WHERE userId = :userId AND (nextReviewAt <= :now OR masteryLevel < 3)")
    List<VocabularyReviewProgress> getDueSync(int userId, long now);

    @Query("SELECT COUNT(*) FROM vocabulary_review_progress WHERE userId = :userId AND (nextReviewAt <= :now OR masteryLevel < 3)")
    LiveData<Integer> getDueCount(int userId, long now);

    @Query("SELECT COUNT(*) FROM vocabulary_review_progress WHERE userId = :userId AND (nextReviewAt <= :now OR masteryLevel < 3)")
    int getDueCountSync(int userId, long now);

    @Query("SELECT COUNT(*) FROM vocabulary_review_progress WHERE userId = :userId AND masteryLevel > 0")
    LiveData<Integer> getLearnedCount(int userId);

    @Query("SELECT COUNT(*) FROM vocabulary_review_progress WHERE userId = :userId")
    int getTotalCountSync(int userId);

    @Query("SELECT * FROM vocabulary_review_progress WHERE userId = :userId AND vocabularyId = :vocabId LIMIT 1")
    VocabularyReviewProgress getByVocabIdSync(int userId, int vocabId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<VocabularyReviewProgress> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(VocabularyReviewProgress progress);

    @Update
    void update(VocabularyReviewProgress progress);

    @Query("DELETE FROM vocabulary_review_progress WHERE userId = :userId")
    void deleteAllForUser(int userId);
}
