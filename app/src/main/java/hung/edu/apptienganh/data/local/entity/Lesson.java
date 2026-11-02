package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "lessons")
public class Lesson {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String level;       // A0, A1, A2, B1, B2, C1
    private String category;    // PRONUNCIATION, VOCABULARY, GRAMMAR, CONVERSATION, LISTENING, SPEAKING
    private String title;
    private String description;
    private String objective;
    private String contentVi;   // Giải thích tiếng Việt
    private String contentEn;   // Nội dung ví dụ hoặc cấu trúc tiếng Anh
    private int orderIndex;
    private boolean isCompleted;

    public Lesson() {
    }

    @Ignore
    public Lesson(String level, String category, String title, String description, String objective, String contentVi, String contentEn, int orderIndex, boolean isCompleted) {
        this.level = level;
        this.category = category;
        this.title = title;
        this.description = description;
        this.objective = objective;
        this.contentVi = contentVi;
        this.contentEn = contentEn;
        this.orderIndex = orderIndex;
        this.isCompleted = isCompleted;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getContentVi() {
        return contentVi;
    }

    public void setContentVi(String contentVi) {
        this.contentVi = contentVi;
    }

    public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
