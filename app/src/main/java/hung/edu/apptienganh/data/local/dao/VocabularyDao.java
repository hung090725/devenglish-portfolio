package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.Vocabulary;

@Dao
public interface VocabularyDao {
    @Query("SELECT * FROM vocabularies ORDER BY word ASC")
    LiveData<List<Vocabulary>> getAllVocabulary();

    @Query("SELECT * FROM vocabularies ORDER BY id ASC")
    List<Vocabulary> getAllVocabularySync();

    @Query("SELECT * FROM vocabularies WHERE level = :level ORDER BY word ASC")
    LiveData<List<Vocabulary>> getVocabularyByLevel(String level);

    @Query("SELECT * FROM vocabularies WHERE level = :level ORDER BY id ASC")
    List<Vocabulary> getVocabularyByLevelSync(String level);

    @Query("SELECT * FROM vocabularies WHERE id = :id LIMIT 1")
    Vocabulary getByIdSync(int id);

    @Query("SELECT * FROM vocabularies WHERE level = :level AND topic = :topic ORDER BY word ASC")
    LiveData<List<Vocabulary>> getVocabularyByLevelAndTopic(String level, String topic);

    @Query("SELECT * FROM vocabularies WHERE word LIKE '%' || :query || '%' OR meaningVi LIKE '%' || :query || '%' ORDER BY word ASC")
    LiveData<List<Vocabulary>> searchVocabulary(String query);

    @Query("SELECT * FROM vocabularies WHERE word = :word LIMIT 1")
    Vocabulary getVocabularyByWordSync(String word);

    @Query("SELECT * FROM vocabularies WHERE isFavorite = 1 ORDER BY word ASC")
    LiveData<List<Vocabulary>> getFavoriteVocabulary();

    @Query("SELECT COUNT(*) FROM vocabularies WHERE masteryLevel >= 80")
    LiveData<Integer> getMasteredWordsCount();

    @Query("SELECT COUNT(*) FROM vocabularies WHERE masteryLevel >= 80")
    int getMasteredWordsCountSync();

    @Query("SELECT COUNT(*) FROM vocabularies")
    int getVocabularyCountSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Vocabulary> vocabularies);

    @Update
    void update(Vocabulary vocabulary);

    @Query("UPDATE vocabularies SET isFavorite = :isFavorite WHERE id = :vocabId")
    void updateFavoriteStatus(int vocabId, boolean isFavorite);

    @Query("UPDATE vocabularies SET masteryLevel = :masteryLevel WHERE id = :vocabId")
    void updateMasteryLevel(int vocabId, int masteryLevel);

    @Query("UPDATE vocabularies SET masteryLevel = 0, isFavorite = 0")
    void resetProgress();
}
