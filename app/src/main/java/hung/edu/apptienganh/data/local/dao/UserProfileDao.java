package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import hung.edu.apptienganh.data.local.entity.UserProfile;

@Dao
public interface UserProfileDao {
    @Query("SELECT * FROM user_profiles LIMIT 1")
    LiveData<UserProfile> getUserProfile();

    @Query("SELECT * FROM user_profiles LIMIT 1")
    UserProfile getUserProfileSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(UserProfile userProfile);

    @Update
    void update(UserProfile userProfile);

    @Query("DELETE FROM user_profiles")
    void deleteAll();
}
