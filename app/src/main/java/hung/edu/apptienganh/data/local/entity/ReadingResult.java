package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reading_results")
public class ReadingResult {
    @PrimaryKey(autoGenerate = true) public int id;
    public int questionId;
    public boolean isCorrect;
    public int userAnswerIndex;
    public long createdAt;
    public ReadingResult() {}
}
