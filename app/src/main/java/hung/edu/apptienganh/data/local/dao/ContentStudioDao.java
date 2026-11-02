package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import hung.edu.apptienganh.data.local.entity.ContentIssue;
import hung.edu.apptienganh.data.local.entity.ContentAuditReport;
import java.util.List;

@Dao
public interface ContentStudioDao {
    @Insert
    void insertIssue(ContentIssue issue);
    
    @Insert
    void insertAuditReport(ContentAuditReport report);
    
    @Query("SELECT * FROM content_issues ORDER BY createdAt DESC")
    List<ContentIssue> getAllIssues();
}
