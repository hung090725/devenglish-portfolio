package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exam_results")
public class ExamResult {
    @PrimaryKey(autoGenerate = true) public int id;
    public int sessionId;
    public int scorePercent;
    public String cefrSuggestion;
    public String strengths;
    public String weaknesses;
    public String recommendedNextActions;
    public long createdAt;
    public ExamResult() {}
}
