package hung.edu.apptienganh.ui.itenglish.model;

import java.util.List;

/**
 * Data model for Code-to-English challenges.
 */
public class CodeSnippet {
    private String id;
    private String title;
    private String language; // Java, Python, JavaScript, SQL, C#
    private String code;
    private String level;    // Junior, Intern, Mid
    private String expectedCoreConcept;
    private List<String> requiredKeyTerms;
    private String idealExplanationEn;
    private String explanationVi;

    public CodeSnippet(String id, String title, String language, String code, String level,
                       String expectedCoreConcept, List<String> requiredKeyTerms,
                       String idealExplanationEn, String explanationVi) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.code = code;
        this.level = level;
        this.expectedCoreConcept = expectedCoreConcept;
        this.requiredKeyTerms = requiredKeyTerms;
        this.idealExplanationEn = idealExplanationEn;
        this.explanationVi = explanationVi;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getLanguage() { return language; }
    public String getCode() { return code; }
    public String getLevel() { return level; }
    public String getExpectedCoreConcept() { return expectedCoreConcept; }
    public List<String> getRequiredKeyTerms() { return requiredKeyTerms; }
    public String getIdealExplanationEn() { return idealExplanationEn; }
    public String getExplanationVi() { return explanationVi; }
}
