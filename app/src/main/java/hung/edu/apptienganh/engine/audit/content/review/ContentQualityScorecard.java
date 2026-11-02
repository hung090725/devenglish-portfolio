package hung.edu.apptienganh.engine.audit.content.review;

public class ContentQualityScorecard {
    private String category;
    private int score; // 0-100
    private String status; // PASS, WARNING, FAIL
    private int issueCount;
    private int criticalCount;
    private String recommendation;

    public ContentQualityScorecard(String category, int score, String status, int issueCount, int criticalCount, String recommendation) {
        this.category = category;
        this.score = score;
        this.status = status;
        this.issueCount = issueCount;
        this.criticalCount = criticalCount;
        this.recommendation = recommendation;
    }

    public String getCategory() { return category; }
    public int getScore() { return score; }
    public String getStatus() { return status; }
    public int getIssueCount() { return issueCount; }
    public int getCriticalCount() { return criticalCount; }
    public String getRecommendation() { return recommendation; }
}
