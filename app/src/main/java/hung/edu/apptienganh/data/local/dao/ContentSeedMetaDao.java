package hung.edu.apptienganh.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import hung.edu.apptienganh.data.local.entity.ContentSeedMeta;

@Dao
public interface ContentSeedMetaDao {

    @Query("SELECT COUNT(*) FROM content_seed_meta WHERE seedKey = :seedKey")
    int countByKeySync(String seedKey);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ContentSeedMeta meta);
}
