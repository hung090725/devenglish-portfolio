package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "vocabularies")
public class Vocabulary {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String word;
    private String ipaUk;
    private String ipaUs;
    private String type;             // noun, verb, adjective, adverb...
    private String meaningVi;
    private String contextMeaningVi; // Nghĩa trong ngữ cảnh
    private String exampleEn;
    private String exampleVi;
    private String collocations;      // Phân tách bằng dấu phẩy hoặc xuống dòng
    private String commonMistake;
    private String level;            // A0, A1, A2...
    private String topic;            // Family, School...
    private boolean isFavorite;
    private int masteryLevel;        // 0 to 100%

    public Vocabulary() {
    }

    @Ignore
    public Vocabulary(String word, String ipaUk, String ipaUs, String type, String meaningVi, String contextMeaningVi, String exampleEn, String exampleVi, String collocations, String commonMistake, String level, String topic, boolean isFavorite, int masteryLevel) {
        this.word = word;
        this.ipaUk = ipaUk;
        this.ipaUs = ipaUs;
        this.type = type;
        this.meaningVi = meaningVi;
        this.contextMeaningVi = contextMeaningVi;
        this.exampleEn = exampleEn;
        this.exampleVi = exampleVi;
        this.collocations = collocations;
        this.commonMistake = commonMistake;
        this.level = level;
        this.topic = topic;
        this.isFavorite = isFavorite;
        this.masteryLevel = masteryLevel;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getIpaUk() {
        return ipaUk;
    }

    public void setIpaUk(String ipaUk) {
        this.ipaUk = ipaUk;
    }

    public String getIpaUs() {
        return ipaUs;
    }

    public void setIpaUs(String ipaUs) {
        this.ipaUs = ipaUs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeaningVi() {
        return meaningVi;
    }

    public void setMeaningVi(String meaningVi) {
        this.meaningVi = meaningVi;
    }

    public String getContextMeaningVi() {
        return contextMeaningVi;
    }

    public void setContextMeaningVi(String contextMeaningVi) {
        this.contextMeaningVi = contextMeaningVi;
    }

    public String getExampleEn() {
        return exampleEn;
    }

    public void setExampleEn(String exampleEn) {
        this.exampleEn = exampleEn;
    }

    public String getExampleVi() {
        return exampleVi;
    }

    public void setExampleVi(String exampleVi) {
        this.exampleVi = exampleVi;
    }

    public String getCollocations() {
        return collocations;
    }

    public void setCollocations(String collocations) {
        this.collocations = collocations;
    }

    public String getCommonMistake() {
        return commonMistake;
    }

    public void setCommonMistake(String commonMistake) {
        this.commonMistake = commonMistake;
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getMasteryLevel() {
        return masteryLevel;
    }

    public void setMasteryLevel(int masteryLevel) {
        this.masteryLevel = masteryLevel;
    }
}
