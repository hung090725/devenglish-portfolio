package hung.edu.apptienganh.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import hung.edu.apptienganh.data.local.entity.ErrorNote;

@Dao
public interface ErrorNotebookDao {
    @Query("SELECT * FROM error_notes ORDER BY createdAt DESC")
    List<ErrorNote> getAllNotesSync();
    
    @Insert
    void insertNote(ErrorNote note);
    
    @Update
    void updateNote(ErrorNote note);
}
