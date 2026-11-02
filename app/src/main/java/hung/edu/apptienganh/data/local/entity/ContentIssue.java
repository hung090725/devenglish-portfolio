package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "content_issues")
public class ContentIssue {
    @PrimaryKey(autoGenerate = true) public int id;
    public String issueType;
    public String level;
    public String targetId;
    public String description;
    public boolean isResolved;
    public long createdAt;
    public ContentIssue() {}
}
