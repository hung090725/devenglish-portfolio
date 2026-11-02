package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.GrammarPoint;

@Dao
public interface GrammarPointDao {
    @Query("SELECT * FROM grammar_points ORDER BY id ASC")
    LiveData<List<GrammarPoint>> getAllGrammarPoints();

    @Query("SELECT * FROM grammar_points WHERE level = :level ORDER BY id ASC")
    LiveData<List<GrammarPoint>> getGrammarPointsByLevel(String level);

    @Query("SELECT * FROM grammar_points WHERE level = :level ORDER BY id ASC")
    List<GrammarPoint> getGrammarPointsByLevelSync(String level);

    @Query("SELECT * FROM grammar_points WHERE id = :id LIMIT 1")
    LiveData<GrammarPoint> getGrammarPointById(int id);

    @Query("SELECT COUNT(*) FROM grammar_points WHERE level = :level")
    int getCountByLevelSync(String level);

    @Query("SELECT COUNT(*) FROM grammar_points")
    int getGrammarCountSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GrammarPoint> grammarPoints);
}
