package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.FavoriteVocabulary;

@Dao
public interface FavoriteVocabularyDao {
    @Query("SELECT * FROM favorite_vocabulary ORDER BY addedAt DESC")
    LiveData<List<FavoriteVocabulary>> getAllFavorites();

    @Query("SELECT * FROM favorite_vocabulary WHERE vocabularyId = :vocabId LIMIT 1")
    FavoriteVocabulary getByVocabIdSync(int vocabId);
    
    @Query("SELECT * FROM favorite_vocabulary")
    List<FavoriteVocabulary> getAllSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoriteVocabulary favorite);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<FavoriteVocabulary> favorites);

    @Query("DELETE FROM favorite_vocabulary WHERE vocabularyId = :vocabId")
    void deleteByVocabId(int vocabId);
}