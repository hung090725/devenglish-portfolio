package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "error_notes")
public class ErrorNote {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String sourceType;
    public String level;
    public String title;
    public String originalText;
    public String correctedText;
    public String explanationVi;
    public String userNote;
    public boolean isResolved;
    public long createdAt;
    public long resolvedAt;
    
    public ErrorNote() {}
}
