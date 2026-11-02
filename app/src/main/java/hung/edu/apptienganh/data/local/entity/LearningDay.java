package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "learning_days",
        indices = {@Index(value = {"level", "dayIndex"}, unique = true)}
)
public class LearningDay {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String level;
    private int dayIndex;
    private String title;
    private String objectiveVi;
    private String lessonSummaryVi;
    private String pronunciationFocus;
    private String vocabularyList;
    private String grammarPoint;
    private int grammarPointId;
    private String conversationSentences;
    private String listeningSentences;
    private String speakingPracticeSentences;
    private int estimatedMinutes;
    private int orderIndex;
    private int conversationId;

    public LearningDay() {
    }

    @Ignore
    public LearningDay(String level, int dayIndex, String title, String objectiveVi,
                       String lessonSummaryVi, String pronunciationFocus, String vocabularyList,
                       String grammarPoint, int grammarPointId, String conversationSentences,
                       String listeningSentences, String speakingPracticeSentences,
                       int estimatedMinutes, int orderIndex, int conversationId) {
        this.level = level;
        this.dayIndex = dayIndex;
        this.title = title;
        this.objectiveVi = objectiveVi;
        this.lessonSummaryVi = lessonSummaryVi;
        this.pronunciationFocus = pronunciationFocus;
        this.vocabularyList = vocabularyList;
        this.grammarPoint = grammarPoint;
        this.grammarPointId = grammarPointId;
        this.conversationSentences = conversationSentences;
        this.listeningSentences = listeningSentences;
        this.speakingPracticeSentences = speakingPracticeSentences;
        this.estimatedMinutes = estimatedMinutes;
        this.orderIndex = orderIndex;
        this.conversationId = conversationId;
    }

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

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjectiveVi() {
        return objectiveVi;
    }

    public void setObjectiveVi(String objectiveVi) {
        this.objectiveVi = objectiveVi;
    }

    public String getLessonSummaryVi() {
        return lessonSummaryVi;
    }

    public void setLessonSummaryVi(String lessonSummaryVi) {
        this.lessonSummaryVi = lessonSummaryVi;
    }

    public String getPronunciationFocus() {
        return pronunciationFocus;
    }

    public void setPronunciationFocus(String pronunciationFocus) {
        this.pronunciationFocus = pronunciationFocus;
    }

    public String getVocabularyList() {
        return vocabularyList;
    }

    public void setVocabularyList(String vocabularyList) {
        this.vocabularyList = vocabularyList;
    }

    public String getGrammarPoint() {
        return grammarPoint;
    }

    public void setGrammarPoint(String grammarPoint) {
        this.grammarPoint = grammarPoint;
    }

    public int getGrammarPointId() {
        return grammarPointId;
    }

    public void setGrammarPointId(int grammarPointId) {
        this.grammarPointId = grammarPointId;
    }

    public String getConversationSentences() {
        return conversationSentences;
    }

    public void setConversationSentences(String conversationSentences) {
        this.conversationSentences = conversationSentences;
    }

    public String getListeningSentences() {
        return listeningSentences;
    }

    public void setListeningSentences(String listeningSentences) {
        this.listeningSentences = listeningSentences;
    }

    public String getSpeakingPracticeSentences() {
        return speakingPracticeSentences;
    }

    public void setSpeakingPracticeSentences(String speakingPracticeSentences) {
        this.speakingPracticeSentences = speakingPracticeSentences;
    }

    public int getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public void setEstimatedMinutes(int estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }
}
