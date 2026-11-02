package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "writing_prompts")
public class WritingPrompt {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String level;
    public String type;
    public String title;
    public String instructionVi;
    public String promptText;
    public String expectedKeywords;
    public String sampleAnswer;
    public String explanationVi;
    public int orderIndex;
    
    public WritingPrompt() {}
}
