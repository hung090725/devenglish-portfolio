package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "writing_attempts")
public class WritingAttempt {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int promptId;
    public String userAnswer;
    public int score;
    public String feedbackVi;
    public long createdAt;
    
    public WritingAttempt() {}
}
