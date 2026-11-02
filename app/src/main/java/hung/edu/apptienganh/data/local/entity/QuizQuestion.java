package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "quiz_questions",
    indices = {@Index("lessonId"), @Index("learningDayId")}
)
public class QuizQuestion {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int lessonId;
    private int learningDayId;
    private String questionType;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private String explanationVi;

    public QuizQuestion() {
    }

    @Ignore
    public QuizQuestion(int lessonId, String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer, String explanationVi) {
        this(lessonId, 0, "GRAMMAR", question, optionA, optionB, optionC, optionD, correctAnswer, explanationVi);
    }

    @Ignore
    public QuizQuestion(int lessonId, int learningDayId, String questionType, String question,
                        String optionA, String optionB, String optionC, String optionD,
                        String correctAnswer, String explanationVi) {
        this.lessonId = lessonId;
        this.learningDayId = learningDayId;
        this.questionType = questionType;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.explanationVi = explanationVi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getLearningDayId() {
        return learningDayId;
    }

    public void setLearningDayId(int learningDayId) {
        this.learningDayId = learningDayId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplanationVi() {
        return explanationVi;
    }

    public void setExplanationVi(String explanationVi) {
        this.explanationVi = explanationVi;
    }
}
