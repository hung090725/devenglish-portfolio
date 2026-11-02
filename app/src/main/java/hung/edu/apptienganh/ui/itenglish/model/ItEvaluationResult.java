package hung.edu.apptienganh.ui.itenglish.model;

import java.util.List;

/**
 * Result model evaluating both Technical Correctness and English Expression independently.
 */
public class ItEvaluationResult {
    private int technicalScore;          // 0 - 100
    private int englishScore;            // 0 - 100
    private String technicalFeedbackVi;  // Nhận xét kiến thức lập trình
    private String englishFeedbackVi;    // Nhận xét ngữ pháp & từ vựng tiếng Anh
    private String improvedExpressionEn; // Cách diễn đạt chuyên nghiệp tự nhiên hơn
    private List<String> matchedTerms;   // Các thuật ngữ chuyên ngành đã dùng đúng
    private List<String> missingTerms;   // Các thuật ngữ nên bổ sung

    public ItEvaluationResult(int technicalScore, int englishScore,
                              String technicalFeedbackVi, String englishFeedbackVi,
                              String improvedExpressionEn,
                              List<String> matchedTerms, List<String> missingTerms) {
        this.technicalScore = technicalScore;
        this.englishScore = englishScore;
        this.technicalFeedbackVi = technicalFeedbackVi;
        this.englishFeedbackVi = englishFeedbackVi;
        this.improvedExpressionEn = improvedExpressionEn;
        this.matchedTerms = matchedTerms;
        this.missingTerms = missingTerms;
    }

    public int getTechnicalScore() { return technicalScore; }
    public int getEnglishScore() { return englishScore; }
    public int getOverallScore() { return Math.round((technicalScore + englishScore) / 2.0f); }
    public String getTechnicalFeedbackVi() { return technicalFeedbackVi; }
    public String getEnglishFeedbackVi() { return englishFeedbackVi; }
    public String getImprovedExpressionEn() { return improvedExpressionEn; }
    public List<String> getMatchedTerms() { return matchedTerms; }
    public List<String> getMissingTerms() { return missingTerms; }
}
