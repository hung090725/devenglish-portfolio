package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_game_sessions")
public class WordGameSession {
    @PrimaryKey(autoGenerate = true) public int id;
    public String gameMode;
    public int score;
    public int durationSeconds;
    public long createdAt;
    public WordGameSession() {}
}
