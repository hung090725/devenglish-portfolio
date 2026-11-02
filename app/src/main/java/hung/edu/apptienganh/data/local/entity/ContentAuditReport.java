package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "content_audit_reports")
public class ContentAuditReport {
    @PrimaryKey(autoGenerate = true) public int id;
    public int totalIssues;
    public long generatedAt;
    public String summaryText;
    public ContentAuditReport() {}
}
