package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_game_results")
public class WordGameResult {
    @PrimaryKey(autoGenerate = true) public int id;
    public int sessionId;
    public int vocabularyId;
    public boolean isCorrect;
    public long timeTakenMs;
    public WordGameResult() {}
}
