package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.GrammarMistakeRecord;

@Dao
public interface GrammarMistakeRecordDao {
    @Query("SELECT * FROM grammar_mistake_records WHERE isResolved = 0 ORDER BY lastMistakeAt DESC")
    LiveData<List<GrammarMistakeRecord>> getUnresolvedMistakes();

    @Query("SELECT * FROM grammar_mistake_records WHERE grammarPointId = :id LIMIT 1")
    GrammarMistakeRecord getMistakeByGrammarIdSync(int id);
    
    @Query("SELECT * FROM grammar_mistake_records")
    List<GrammarMistakeRecord> getAllSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GrammarMistakeRecord record);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GrammarMistakeRecord> records);

    @Update
    void update(GrammarMistakeRecord record);
}