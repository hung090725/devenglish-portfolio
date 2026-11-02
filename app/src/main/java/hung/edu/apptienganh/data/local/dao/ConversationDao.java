package hung.edu.apptienganh.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.Conversation;
import hung.edu.apptienganh.data.local.entity.ConversationLine;

@Dao
public interface ConversationDao {
    @Query("SELECT * FROM conversations ORDER BY id ASC")
    LiveData<List<Conversation>> getAllConversations();

    @Query("SELECT * FROM conversations WHERE level = :level ORDER BY id ASC")
    LiveData<List<Conversation>> getConversationsByLevel(String level);

    @Query("SELECT * FROM conversations WHERE level = :level ORDER BY id ASC")
    List<Conversation> getConversationsByLevelSync(String level);

    @Query("SELECT COUNT(*) FROM conversations WHERE level = :level")
    int getCountByLevelSync(String level);

    @Query("SELECT * FROM conversations WHERE id = :id LIMIT 1")
    LiveData<Conversation> getConversationById(int id);

    @Query("SELECT * FROM conversation_lines WHERE conversationId = :conversationId ORDER BY orderIndex ASC")
    LiveData<List<ConversationLine>> getLinesForConversation(int conversationId);

    @Query("SELECT * FROM conversation_lines WHERE conversationId = :conversationId ORDER BY orderIndex ASC")
    List<ConversationLine> getLinesForConversationSync(int conversationId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllConversations(List<Conversation> conversations);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllConversationLines(List<ConversationLine> lines);
}
