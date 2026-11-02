package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "conversation_lines",
    foreignKeys = @ForeignKey(
        entity = Conversation.class,
        parentColumns = "id",
        childColumns = "conversationId",
        onDelete = ForeignKey.CASCADE
    ),
    indices = {@Index("conversationId")}
)
public class ConversationLine {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private int conversationId;
    private String role;        // A hoặc B
    private String sentenceEn;
    private String ipa;
    private String sentenceVi;
    private int orderIndex;

    public ConversationLine() {
    }

    @Ignore
    public ConversationLine(int conversationId, String role, String sentenceEn, String ipa, String sentenceVi, int orderIndex) {
        this.conversationId = conversationId;
        this.role = role;
        this.sentenceEn = sentenceEn;
        this.ipa = ipa;
        this.sentenceVi = sentenceVi;
        this.orderIndex = orderIndex;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSentenceEn() {
        return sentenceEn;
    }

    public void setSentenceEn(String sentenceEn) {
        this.sentenceEn = sentenceEn;
    }

    public String getIpa() {
        return ipa;
    }

    public void setIpa(String ipa) {
        this.ipa = ipa;
    }

    public String getSentenceVi() {
        return sentenceVi;
    }

    public void setSentenceVi(String sentenceVi) {
        this.sentenceVi = sentenceVi;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }
}
