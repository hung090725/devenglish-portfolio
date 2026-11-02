package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "study_day_summaries")
public class StudyDaySummary {
    @PrimaryKey(autoGenerate = true) public int id;
    public String dateString;
    public int totalMinutes;
    public int lessonsCompleted;
    public int quizCompleted;
    public int reviewCompleted;
    public int otherAttempts;
    public int currentStreak;
    public StudyDaySummary() {}
}
