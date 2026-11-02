package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pronunciation_attempts")
public class PronunciationAttempt {
    @PrimaryKey(autoGenerate = true) public int id;
    public int drillId;
    public String recognizedText;
    public int score;
    public String feedbackVi;
    public long createdAt;
    public PronunciationAttempt() {}
}
