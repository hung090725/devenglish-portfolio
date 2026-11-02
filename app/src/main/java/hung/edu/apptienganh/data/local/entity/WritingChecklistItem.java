package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "writing_checklist_items")
public class WritingChecklistItem {
    @PrimaryKey(autoGenerate = true) public int id;
    public int templateId;
    public String descriptionVi;
    public boolean isRequired;
    public WritingChecklistItem() {}
}
