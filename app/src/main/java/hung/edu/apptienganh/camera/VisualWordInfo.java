package hung.edu.apptienganh.camera;

import java.util.ArrayList;
import java.util.List;

/**
 * Encyclopedic linguistic model for visual vocabulary items containing
 * phonetics, CEFR levels (A1-C1), etymology, component anatomy,
 * confusing words, British vs American variations, IELTS speaking simulator,
 * applied grammar, phrasal verbs, ESP specialized vocab, and AI tutor facts.
 */
public class VisualWordInfo {

    private String wordEn;
    private String ipa;
    private String meaningVi;
    private String wordType;
    private String cefrLevel;
    private String collocationEn;
    private String collocationVi;
    private String exampleSentenceEn;
    private String exampleSentenceVi;
    private float confidence;
    private String rawLabel;
    private String detailedAiNotesVi;

    // Advanced Educational Features (15 Ideas)
    private String idiomEn;
    private String idiomVi;
    private String dialogueEn;
    private String dialogueVi;
    private String wordFamilyEn;
    private String wordFamilyVi;
    private String academicC1WordEn;
    private String academicC1MeaningVi;
    private String quizQuestionEn;
    private List<String> quizOptions = new ArrayList<>();
    private int quizAnswerIndex = 0;
    private String syllableStress;

    // Deep Learning & Encyclopedic Features (Ideas 16 to 35)
    private String etymologyRoot;          // Ý tưởng 16: Từ nguyên học (Latin/Greek roots)
    private String componentAnatomy;       // Ý tưởng 17: Phân tách bộ phận con
    private String confusingWordAlert;     // Ý tưởng 18: Phân biệt từ dễ nhầm lẫn (Desk vs Table)
    private String polysemyNotes;          // Ý tưởng 19: Đa nghĩa & nghĩa bóng
    private String britishVsAmerican;      // Ý tưởng 20: Anh-Anh vs Anh-Mỹ
    private String sensoryAdjectives;      // Ý tưởng 21: Đám mây tính từ 5 giác quan
    private String mindmapTree;            // Ý tưởng 22: Sơ đồ tư duy
    private String mnemonicHook;           // Ý tưởng 23: Mẹo siêu trí nhớ
    private String atmosphericMood;        // Ý tưởng 24: Từ vựng không gian & cảm xúc
    private String grammarRuleNotes;       // Ý tưởng 25: Ngữ pháp đếm được/không đếm được & giới từ
    private String ieltsSpeakingSample;    // Ý tưởng 26: Câu hỏi & câu trả lời mẫu IELTS Band 7.5+
    private String phrasalVerbs;           // Ý tưởng 27: Cụm động từ tự nhiên
    private String espSpecializedVocab;    // Ý tưởng 28: Tiếng Anh chuyên ngành (IT/Business)
    private String shoppingInquiries;      // Ý tưởng 29: Kịch bản mua sắm quốc tế
    private String actionVerbsInMotion;    // Ý tưởng 31: Động từ vận động
    private String aiTutorFact;            // Ý tưởng 35: Lời bình luận của Gia sư AI

    public VisualWordInfo() {}

    public VisualWordInfo(String wordEn, String ipa, String meaningVi, String wordType,
                          String cefrLevel, String collocationEn, String collocationVi,
                          String exampleSentenceEn, String exampleSentenceVi) {
        this.wordEn = wordEn;
        this.ipa = ipa;
        this.meaningVi = meaningVi;
        this.wordType = wordType;
        this.cefrLevel = cefrLevel;
        this.collocationEn = collocationEn;
        this.collocationVi = collocationVi;
        this.exampleSentenceEn = exampleSentenceEn;
        this.exampleSentenceVi = exampleSentenceVi;
    }

    public String getWordEn() {
        return wordEn;
    }

    public void setWordEn(String wordEn) {
        this.wordEn = wordEn;
    }

    public String getIpa() {
        return ipa;
    }

    public void setIpa(String ipa) {
        this.ipa = ipa;
    }

    public String getMeaningVi() {
        return meaningVi;
    }

    public void setMeaningVi(String meaningVi) {
        this.meaningVi = meaningVi;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public String getCefrLevel() {
        return cefrLevel;
    }

    public void setCefrLevel(String cefrLevel) {
        this.cefrLevel = cefrLevel;
    }

    public String getCollocationEn() {
        return collocationEn;
    }

    public void setCollocationEn(String collocationEn) {
        this.collocationEn = collocationEn;
    }

    public String getCollocationVi() {
        return collocationVi;
    }

    public void setCollocationVi(String collocationVi) {
        this.collocationVi = collocationVi;
    }

    public String getExampleSentenceEn() {
        return exampleSentenceEn;
    }

    public void setExampleSentenceEn(String exampleSentenceEn) {
        this.exampleSentenceEn = exampleSentenceEn;
    }

    public String getExampleSentenceVi() {
        return exampleSentenceVi;
    }

    public void setExampleSentenceVi(String exampleSentenceVi) {
        this.exampleSentenceVi = exampleSentenceVi;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public String getRawLabel() {
        return rawLabel;
    }

    public void setRawLabel(String rawLabel) {
        this.rawLabel = rawLabel;
    }

    public String getDetailedAiNotesVi() {
        return detailedAiNotesVi;
    }

    public void setDetailedAiNotesVi(String detailedAiNotesVi) {
        this.detailedAiNotesVi = detailedAiNotesVi;
    }

    public String getIdiomEn() {
        return idiomEn;
    }

    public void setIdiomEn(String idiomEn) {
        this.idiomEn = idiomEn;
    }

    public String getIdiomVi() {
        return idiomVi;
    }

    public void setIdiomVi(String idiomVi) {
        this.idiomVi = idiomVi;
    }

    public String getDialogueEn() {
        return dialogueEn;
    }

    public void setDialogueEn(String dialogueEn) {
        this.dialogueEn = dialogueEn;
    }

    public String getDialogueVi() {
        return dialogueVi;
    }

    public void setDialogueVi(String dialogueVi) {
        this.dialogueVi = dialogueVi;
    }

    public String getWordFamilyEn() {
        return wordFamilyEn;
    }

    public void setWordFamilyEn(String wordFamilyEn) {
        this.wordFamilyEn = wordFamilyEn;
    }

    public String getWordFamilyVi() {
        return wordFamilyVi;
    }

    public void setWordFamilyVi(String wordFamilyVi) {
        this.wordFamilyVi = wordFamilyVi;
    }

    public String getAcademicC1WordEn() {
        return academicC1WordEn;
    }

    public void setAcademicC1WordEn(String academicC1WordEn) {
        this.academicC1WordEn = academicC1WordEn;
    }

    public String getAcademicC1MeaningVi() {
        return academicC1MeaningVi;
    }

    public void setAcademicC1MeaningVi(String academicC1MeaningVi) {
        this.academicC1MeaningVi = academicC1MeaningVi;
    }

    public String getQuizQuestionEn() {
        return quizQuestionEn;
    }

    public void setQuizQuestionEn(String quizQuestionEn) {
        this.quizQuestionEn = quizQuestionEn;
    }

    public List<String> getQuizOptions() {
        return quizOptions;
    }

    public void setQuizOptions(List<String> quizOptions) {
        this.quizOptions = quizOptions;
    }

    public int getQuizAnswerIndex() {
        return quizAnswerIndex;
    }

    public void setQuizAnswerIndex(int quizAnswerIndex) {
        this.quizAnswerIndex = quizAnswerIndex;
    }

    public String getSyllableStress() {
        return syllableStress;
    }

    public void setSyllableStress(String syllableStress) {
        this.syllableStress = syllableStress;
    }

    // Getters and Setters for Ideas 16 to 35
    public String getEtymologyRoot() {
        return etymologyRoot;
    }

    public void setEtymologyRoot(String etymologyRoot) {
        this.etymologyRoot = etymologyRoot;
    }

    public String getComponentAnatomy() {
        return componentAnatomy;
    }

    public void setComponentAnatomy(String componentAnatomy) {
        this.componentAnatomy = componentAnatomy;
    }

    public String getConfusingWordAlert() {
        return confusingWordAlert;
    }

    public void setConfusingWordAlert(String confusingWordAlert) {
        this.confusingWordAlert = confusingWordAlert;
    }

    public String getPolysemyNotes() {
        return polysemyNotes;
    }

    public void setPolysemyNotes(String polysemyNotes) {
        this.polysemyNotes = polysemyNotes;
    }

    public String getBritishVsAmerican() {
        return britishVsAmerican;
    }

    public void setBritishVsAmerican(String britishVsAmerican) {
        this.britishVsAmerican = britishVsAmerican;
    }

    public String getSensoryAdjectives() {
        return sensoryAdjectives;
    }

    public void setSensoryAdjectives(String sensoryAdjectives) {
        this.sensoryAdjectives = sensoryAdjectives;
    }

    public String getMindmapTree() {
        return mindmapTree;
    }

    public void setMindmapTree(String mindmapTree) {
        this.mindmapTree = mindmapTree;
    }

    public String getMnemonicHook() {
        return mnemonicHook;
    }

    public void setMnemonicHook(String mnemonicHook) {
        this.mnemonicHook = mnemonicHook;
    }

    public String getAtmosphericMood() {
        return atmosphericMood;
    }

    public void setAtmosphericMood(String atmosphericMood) {
        this.atmosphericMood = atmosphericMood;
    }

    public String getGrammarRuleNotes() {
        return grammarRuleNotes;
    }

    public void setGrammarRuleNotes(String grammarRuleNotes) {
        this.grammarRuleNotes = grammarRuleNotes;
    }

    public String getIeltsSpeakingSample() {
        return ieltsSpeakingSample;
    }

    public void setIeltsSpeakingSample(String ieltsSpeakingSample) {
        this.ieltsSpeakingSample = ieltsSpeakingSample;
    }

    public String getPhrasalVerbs() {
        return phrasalVerbs;
    }

    public void setPhrasalVerbs(String phrasalVerbs) {
        this.phrasalVerbs = phrasalVerbs;
    }

    public String getEspSpecializedVocab() {
        return espSpecializedVocab;
    }

    public void setEspSpecializedVocab(String espSpecializedVocab) {
        this.espSpecializedVocab = espSpecializedVocab;
    }

    public String getShoppingInquiries() {
        return shoppingInquiries;
    }

    public void setShoppingInquiries(String shoppingInquiries) {
        this.shoppingInquiries = shoppingInquiries;
    }

    public String getActionVerbsInMotion() {
        return actionVerbsInMotion;
    }

    public void setActionVerbsInMotion(String actionVerbsInMotion) {
        this.actionVerbsInMotion = actionVerbsInMotion;
    }

    public String getAiTutorFact() {
        return aiTutorFact;
    }

    public void setAiTutorFact(String aiTutorFact) {
        this.aiTutorFact = aiTutorFact;
    }
}
