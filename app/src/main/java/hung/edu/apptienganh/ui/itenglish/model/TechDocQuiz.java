package hung.edu.apptienganh.ui.itenglish.model;

import java.util.List;

public class TechDocQuiz {
    private String question;
    private List<String> options;
    private int correctIndex;
    private String explanationVi;

    public TechDocQuiz(String question, List<String> options, int correctIndex, String explanationVi) {
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
        this.explanationVi = explanationVi;
    }

    public String getQuestion() { return question; }
    public List<String> getOptions() { return options; }
    public int getCorrectIndex() { return correctIndex; }
    public String getExplanationVi() { return explanationVi; }
}
