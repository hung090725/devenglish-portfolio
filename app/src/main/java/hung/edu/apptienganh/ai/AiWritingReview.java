package hung.edu.apptienganh.ai;

import java.util.ArrayList;
import java.util.List;

public class AiWritingReview {

    public static class SentenceCorrection {
        public String original;
        public String improved;
        public String explanationVi;

        public SentenceCorrection(String original, String improved, String explanationVi) {
            this.original = original;
            this.improved = improved;
            this.explanationVi = explanationVi;
        }
    }

    private int score;
    private String summaryVi;
    private String naturalRewriteEn;
    private List<String> grammarTips = new ArrayList<>();
    private List<SentenceCorrection> corrections = new ArrayList<>();

    public AiWritingReview() {}

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSummaryVi() {
        return summaryVi;
    }

    public void setSummaryVi(String summaryVi) {
        this.summaryVi = summaryVi;
    }

    public String getNaturalRewriteEn() {
        return naturalRewriteEn;
    }

    public void setNaturalRewriteEn(String naturalRewriteEn) {
        this.naturalRewriteEn = naturalRewriteEn;
    }

    public List<String> getGrammarTips() {
        return grammarTips;
    }

    public void setGrammarTips(List<String> grammarTips) {
        this.grammarTips = grammarTips;
    }

    public List<SentenceCorrection> getCorrections() {
        return corrections;
    }

    public void setCorrections(List<SentenceCorrection> corrections) {
        this.corrections = corrections;
    }
}
