package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "flashcard_results")
public class FlashcardResult {
    @PrimaryKey(autoGenerate = true) public int id;
    public int vocabularyId;
    public String mode;
    public String response;
    public boolean isCorrect;
    public String difficulty;
    public long createdAt;
    public FlashcardResult() {}
}
