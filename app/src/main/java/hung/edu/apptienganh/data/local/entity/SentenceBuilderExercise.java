package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sentence_builder_exercises")
public class SentenceBuilderExercise {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String level;
    public String scrambledWords;
    public String correctSentence;
    public String meaningVi;
    public String grammarFocus;
    public String explanationVi;
    public int orderIndex;
    
    public SentenceBuilderExercise() {}
}
