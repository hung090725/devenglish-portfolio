package hung.edu.apptienganh.engine.audit;

public class AppCompletionCategory {
    public String name;
    public int score; // 0-100
    public String status; // PASS, WARNING, FAIL
    public String summaryVi;
    public String issues;
    public String recommendations;

    public AppCompletionCategory(String name, int score, String status, String summaryVi, String issues, String recommendations) {
        this.name = name;
        this.score = score;
        this.status = status;
        this.summaryVi = summaryVi;
        this.issues = issues;
        this.recommendations = recommendations;
    }
}
