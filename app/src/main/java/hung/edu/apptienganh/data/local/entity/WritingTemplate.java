package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "writing_templates")
public class WritingTemplate {
    @PrimaryKey(autoGenerate = true) public int id;
    public String type;
    public String title;
    public String descriptionVi;
    public String structureJson;
    public String usefulPhrasesJson;
    public WritingTemplate() {}
}
