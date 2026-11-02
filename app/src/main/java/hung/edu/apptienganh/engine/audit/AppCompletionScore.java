package hung.edu.apptienganh.engine.audit;

import java.util.List;

public class AppCompletionScore {
    public int overallScore;
    public String overallStatus;
    public List<AppCompletionCategory> categories;
    public long generatedAt;

    public AppCompletionScore(int overallScore, String overallStatus, List<AppCompletionCategory> categories) {
        this.overallScore = overallScore;
        this.overallStatus = overallStatus;
        this.categories = categories;
        this.generatedAt = System.currentTimeMillis();
    }
}
