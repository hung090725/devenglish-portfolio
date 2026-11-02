package hung.edu.apptienganh.ui.itenglish.model;

import java.util.List;

public class TechDocArticle {
    private String id;
    private String title;
    private String technology; // React, Spring Boot, Docker, SQL, Git
    private String level;      // A2-B1, B1-B2
    private String contentEn;
    private String simplifiedExplanationEn;
    private String summaryVi;
    private List<TechVocabulary> vocabularies;
    private List<TechDocQuiz> quizzes;

    public TechDocArticle(String id, String title, String technology, String level,
                          String contentEn, String simplifiedExplanationEn, String summaryVi,
                          List<TechVocabulary> vocabularies, List<TechDocQuiz> quizzes) {
        this.id = id;
        this.title = title;
        this.technology = technology;
        this.level = level;
        this.contentEn = contentEn;
        this.simplifiedExplanationEn = simplifiedExplanationEn;
        this.summaryVi = summaryVi;
        this.vocabularies = vocabularies;
        this.quizzes = quizzes;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getTechnology() { return technology; }
    public String getLevel() { return level; }
    public String getContentEn() { return contentEn; }
    public String getSimplifiedExplanationEn() { return simplifiedExplanationEn; }
    public String getSummaryVi() { return summaryVi; }
    public List<TechVocabulary> getVocabularies() { return vocabularies; }
    public List<TechDocQuiz> getQuizzes() { return quizzes; }
}
