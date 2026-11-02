package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "speaking_results")
public class SpeakingResult {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userId;
    private int learningDayId;
    private String targetSentence;
    private String recognizedText;
    private int score;
    private String correctWords;
    private String missingWords;
    private String extraWords;
    private String wrongWords;
    private String feedbackVi;
    private long createdAt;

    public SpeakingResult() {
    }

    @Ignore
    public SpeakingResult(String targetSentence, String recognizedText, int score, String correctWords, String wrongWords, long createdAt) {
        this.targetSentence = targetSentence;
        this.recognizedText = recognizedText;
        this.score = score;
        this.correctWords = correctWords;
        this.wrongWords = wrongWords;
        this.createdAt = createdAt;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTargetSentence() {
        return targetSentence;
    }

    public void setTargetSentence(String targetSentence) {
        this.targetSentence = targetSentence;
    }

    public String getRecognizedText() {
        return recognizedText;
    }

    public void setRecognizedText(String recognizedText) {
        this.recognizedText = recognizedText;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCorrectWords() {
        return correctWords;
    }

    public void setCorrectWords(String correctWords) {
        this.correctWords = correctWords;
    }

    public String getWrongWords() {
        return wrongWords;
    }

    public void setWrongWords(String wrongWords) {
        this.wrongWords = wrongWords;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLearningDayId() {
        return learningDayId;
    }

    public void setLearningDayId(int learningDayId) {
        this.learningDayId = learningDayId;
    }

    public String getMissingWords() {
        return missingWords;
    }

    public void setMissingWords(String missingWords) {
        this.missingWords = missingWords;
    }

    public String getExtraWords() {
        return extraWords;
    }

    public void setExtraWords(String extraWords) {
        this.extraWords = extraWords;
    }

    public String getFeedbackVi() {
        return feedbackVi;
    }

    public void setFeedbackVi(String feedbackVi) {
        this.feedbackVi = feedbackVi;
    }
}
