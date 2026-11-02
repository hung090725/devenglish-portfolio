package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grammar_practice_results")
public class GrammarPracticeResult {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int exerciseId;
    public boolean isCorrect;
    public int userAnswerIndex;
    public long createdAt;
    
    public GrammarPracticeResult() {}
}
