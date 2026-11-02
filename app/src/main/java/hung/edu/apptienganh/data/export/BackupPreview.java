package hung.edu.apptienganh.data.export;

public class BackupPreview {
    public String appVersionName;
    public int appVersionCode;
    public int databaseVersion;
    public String exportedAt;
    public int dayProgressCount;
    public int vocabularyProgressCount;
    public int quizAttemptCount;
    public int speakingResultCount;
    public int betaFeedbackCount;
    public boolean isValid;
    public String errorMessage;

    public BackupPreview() {}

    public BackupPreview(boolean isValid, String errorMessage) {
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }
}
