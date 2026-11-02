package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exam_sessions")
public class ExamSession {
    @PrimaryKey(autoGenerate = true) public int id;
    public String examType;
    public String level;
    public long startedAt;
    public long completedAt;
    public int totalQuestions;
    public int correctCount;
    public int score;
    public String estimatedLevel;
    public String feedbackVi;
    public ExamSession() {}
}
