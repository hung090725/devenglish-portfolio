package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exam_questions")
public class ExamQuestion {
    @PrimaryKey(autoGenerate = true) public int id;
    public int sessionId;
    public String questionType;
    public int questionId;
    public String userAnswer;
    public boolean isCorrect;
    public ExamQuestion() {}
}
