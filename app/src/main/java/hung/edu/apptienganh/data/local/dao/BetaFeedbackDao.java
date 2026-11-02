package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.BetaFeedback;

@Dao
public interface BetaFeedbackDao {
    @Insert
    long insert(BetaFeedback feedback);

    @Query("SELECT * FROM beta_feedback ORDER BY createdAt DESC")
    LiveData<List<BetaFeedback>> getAllFeedback();

    @Query("SELECT * FROM beta_feedback ORDER BY createdAt DESC LIMIT :limit")
    List<BetaFeedback> getRecentFeedbackSync(int limit);
}
