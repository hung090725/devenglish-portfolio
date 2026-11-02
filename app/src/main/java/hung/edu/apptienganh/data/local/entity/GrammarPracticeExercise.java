package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grammar_practice_exercises")
public class GrammarPracticeExercise {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int grammarPointId;
    public String level;
    public String type;
    public String questionText;
    public String optionsJson;
    public int correctOptionIndex;
    public String explanationVi;
    
    public GrammarPracticeExercise() {}
}
