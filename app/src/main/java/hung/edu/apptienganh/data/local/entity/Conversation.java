package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "conversations")
public class Conversation {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String level;       // A0, A1, A2...
    private String topic;       // Greeting, Ordering food...
    private String title;
    private String situationVi; // Tình huống bằng tiếng Việt

    public Conversation() {
    }

    @Ignore
    public Conversation(String level, String topic, String title, String situationVi) {
        this.level = level;
        this.topic = topic;
        this.title = title;
        this.situationVi = situationVi;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSituationVi() {
        return situationVi;
    }

    public void setSituationVi(String situationVi) {
        this.situationVi = situationVi;
    }
}
