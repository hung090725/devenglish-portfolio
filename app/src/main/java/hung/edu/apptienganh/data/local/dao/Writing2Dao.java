package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import hung.edu.apptienganh.data.local.entity.WritingTemplate;
import hung.edu.apptienganh.data.local.entity.WritingChecklistItem;
import java.util.List;

@Dao
public interface Writing2Dao {
    @Insert
    long insertTemplate(WritingTemplate template);
    
    @Insert
    void insertChecklistItems(List<WritingChecklistItem> items);
    
    @Query("SELECT * FROM writing_templates")
    List<WritingTemplate> getAllTemplates();
}
