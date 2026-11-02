package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reading_questions")
public class ReadingQuestion {
    @PrimaryKey(autoGenerate = true) public int id;
    public int passageId;
    public String type;
    public String questionText;
    public String optionsJson;
    public int correctOptionIndex;
    public String explanationVi;
    public ReadingQuestion() {}
}
