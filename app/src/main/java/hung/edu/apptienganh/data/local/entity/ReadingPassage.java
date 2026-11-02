package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reading_passages")
public class ReadingPassage {
    @PrimaryKey(autoGenerate = true) public int id;
    public String level;
    public String topic;
    public String title;
    public String passageText;
    public String vocabularyHints;
    public String explanationVi;
    public ReadingPassage() {}
}
