package hung.edu.apptienganh.ui.itenglish.model;

public class TechVocabulary {
    private String term;
    private String ipa;
    private String partOfSpeech;
    private String definitionEn;
    private String meaningVi;
    private String exampleEn;

    public TechVocabulary(String term, String ipa, String partOfSpeech, String definitionEn, String meaningVi, String exampleEn) {
        this.term = term;
        this.ipa = ipa;
        this.partOfSpeech = partOfSpeech;
        this.definitionEn = definitionEn;
        this.meaningVi = meaningVi;
        this.exampleEn = exampleEn;
    }

    public String getTerm() { return term; }
    public String getIpa() { return ipa; }
    public String getPartOfSpeech() { return partOfSpeech; }
    public String getDefinitionEn() { return definitionEn; }
    public String getMeaningVi() { return meaningVi; }
    public String getExampleEn() { return exampleEn; }
}
