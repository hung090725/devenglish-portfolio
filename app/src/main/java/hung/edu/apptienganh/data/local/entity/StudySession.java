package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "study_sessions")
public class StudySession {
    @PrimaryKey(autoGenerate = true) public int id;
    public String dateString;
    public String activityType;
    public int durationMinutes;
    public long createdAt;
    public StudySession() {}
}
