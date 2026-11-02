package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.SpeakingWeakSentence;

@Dao
public interface SpeakingWeakSentenceDao {
    @Query("SELECT * FROM speaking_weak_sentences WHERE isResolved = 0 ORDER BY lastMistakeAt DESC")
    LiveData<List<SpeakingWeakSentence>> getUnresolvedSentences();

    @Query("SELECT * FROM speaking_weak_sentences WHERE sentenceEn = :sentence LIMIT 1")
    SpeakingWeakSentence getBySentenceSync(String sentence);
    
    @Query("SELECT * FROM speaking_weak_sentences")
    List<SpeakingWeakSentence> getAllSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SpeakingWeakSentence sentence);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SpeakingWeakSentence> sentences);

    @Update
    void update(SpeakingWeakSentence sentence);
}