package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pronunciation_drills")
public class PronunciationDrill {
    @PrimaryKey(autoGenerate = true) public int id;
    public String level;
    public String type;
    public String targetSound;
    public String explanationVi;
    public PronunciationDrill() {}
}
