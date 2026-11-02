package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "grammar_points")
public class GrammarPoint {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String level;            // A0, A1, A2...
    private String title;
    private String formula;          // Công thức
    private String usageVi;          // Cách dùng
    private String signalWords;      // Dấu hiệu nhận biết
    private String correctExamples;  // Ví dụ đúng (các câu cách nhau bằng dấu xuống dòng hoặc phân tách)
    private String wrongExamples;    // Ví dụ sai
    private String explanationVi;    // Giải thích lỗi sai bằng tiếng Việt
    private String shortPracticeQuestions; // Câu hỏi luyện tập ngắn (Q|||A)

    public GrammarPoint() {
    }

    @Ignore
    public GrammarPoint(String level, String title, String formula, String usageVi, String signalWords, String correctExamples, String wrongExamples, String explanationVi) {
        this(level, title, formula, usageVi, signalWords, correctExamples, wrongExamples, explanationVi, "");
    }

    @Ignore
    public GrammarPoint(String level, String title, String formula, String usageVi, String signalWords, String correctExamples, String wrongExamples, String explanationVi, String shortPracticeQuestions) {
        this.level = level;
        this.title = title;
        this.formula = formula;
        this.usageVi = usageVi;
        this.signalWords = signalWords;
        this.correctExamples = correctExamples;
        this.wrongExamples = wrongExamples;
        this.explanationVi = explanationVi;
        this.shortPracticeQuestions = shortPracticeQuestions;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getUsageVi() {
        return usageVi;
    }

    public void setUsageVi(String usageVi) {
        this.usageVi = usageVi;
    }

    public String getSignalWords() {
        return signalWords;
    }

    public void setSignalWords(String signalWords) {
        this.signalWords = signalWords;
    }

    public String getCorrectExamples() {
        return correctExamples;
    }

    public void setCorrectExamples(String correctExamples) {
        this.correctExamples = correctExamples;
    }

    public String getWrongExamples() {
        return wrongExamples;
    }

    public void setWrongExamples(String wrongExamples) {
        this.wrongExamples = wrongExamples;
    }

    public String getExplanationVi() {
        return explanationVi;
    }

    public void setExplanationVi(String explanationVi) {
        this.explanationVi = explanationVi;
    }

    public String getShortPracticeQuestions() {
        return shortPracticeQuestions;
    }

    public void setShortPracticeQuestions(String shortPracticeQuestions) {
        this.shortPracticeQuestions = shortPracticeQuestions;
    }
}
