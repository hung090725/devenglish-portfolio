package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.RealDeviceTestCase;

@Dao
public interface RealDeviceTestCaseDao {

    @Query("SELECT * FROM real_device_test_cases ORDER BY orderIndex ASC")
    LiveData<List<RealDeviceTestCase>> getAllTestCases();

    @Query("SELECT * FROM real_device_test_cases ORDER BY orderIndex ASC")
    List<RealDeviceTestCase> getAllTestCasesSync();

    @Query("SELECT COUNT(*) FROM real_device_test_cases")
    int getCountSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RealDeviceTestCase> testCases);

    @Query("DELETE FROM real_device_test_cases")
    void deleteAllTestCasesSync();

    @Update
    void update(RealDeviceTestCase testCase);
}
