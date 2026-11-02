package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sentence_builder_results")
public class SentenceBuilderResult {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int exerciseId;
    public String userSentence;
    public boolean isCorrect;
    public int score;
    public long createdAt;
    
    public SentenceBuilderResult() {}
}
