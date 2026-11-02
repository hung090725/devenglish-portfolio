package hung.edu.apptienganh.camera;

import static org.junit.Assert.*;

import org.junit.Test;

public class VisualVocabularyDatabaseTest {

    @Test
    public void testLookupLaptopExactAndEnrichedFields() {
        VisualWordInfo info = VisualVocabularyDatabase.lookup("laptop", 0.95f);
        assertNotNull(info);
        assertEquals("Laptop", info.getWordEn());
        assertEquals("/ˈlæp.tɒp/", info.getIpa());
        assertEquals("Máy tính xách tay", info.getMeaningVi());
        assertNotNull(info.getExampleSentenceEn());
        assertNotNull(info.getExampleSentenceVi());
        assertNotNull(info.getCollocationEn());

        // Test 15-idea enrichments
        assertNotNull(info.getIdiomEn());
        assertNotNull(info.getIdiomVi());
        assertNotNull(info.getDialogueEn());
        assertNotNull(info.getWordFamilyEn());
        assertNotNull(info.getAcademicC1WordEn());
        assertNotNull(info.getQuizQuestionEn());
        assertFalse(info.getQuizOptions().isEmpty());

        // Test Deep Learning & Encyclopedic Features (Ideas 16 to 35)
        assertNotNull(info.getEtymologyRoot());
        assertTrue(info.getEtymologyRoot().contains("Lap"));
        assertNotNull(info.getComponentAnatomy());
        assertTrue(info.getComponentAnatomy().contains("Touchpad") || info.getComponentAnatomy().contains("Screen"));
        assertNotNull(info.getConfusingWordAlert());
        assertTrue(info.getConfusingWordAlert().contains("Desktop"));
        assertNotNull(info.getBritishVsAmerican());
        assertTrue(info.getBritishVsAmerican().contains("UK"));
        assertNotNull(info.getGrammarRuleNotes());
        assertTrue(info.getGrammarRuleNotes().contains("ON"));
        assertNotNull(info.getIeltsSpeakingSample());
        assertTrue(info.getIeltsSpeakingSample().contains("IELTS"));
        assertNotNull(info.getPhrasalVerbs());
        assertTrue(info.getPhrasalVerbs().contains("Boot up"));
        assertNotNull(info.getSensoryAdjectives());
        assertNotNull(info.getMindmapTree());
        assertNotNull(info.getMnemonicHook());
        assertNotNull(info.getEspSpecializedVocab());
        assertNotNull(info.getShoppingInquiries());
        assertNotNull(info.getAiTutorFact());
    }

    @Test
    public void testLookupContainsPattern() {
        VisualWordInfo info = VisualVocabularyDatabase.lookup("computer keyboard", 0.88f);
        assertNotNull(info);
        assertEquals("Keyboard", info.getWordEn());
        assertTrue(info.getMeaningVi().contains("Bàn phím"));
    }

    @Test
    public void testLookupDrinkAndFood() {
        VisualWordInfo info = VisualVocabularyDatabase.lookup("coffee cup", 0.92f);
        assertNotNull(info);
        assertEquals("Coffee Cup", info.getWordEn());
        assertTrue(info.getMeaningVi().contains("Tách cà phê"));

        VisualWordInfo bottle = VisualVocabularyDatabase.lookup("water bottle", 0.94f);
        assertNotNull(bottle);
        assertTrue(bottle.getWordEn().contains("Water Bottle"));
        assertTrue(bottle.getMeaningVi().contains("Bình nước"));
    }

    @Test
    public void testLookupFurnitureAndOffice() {
        VisualWordInfo book = VisualVocabularyDatabase.lookup("book", 0.90f);
        assertNotNull(book);
        assertEquals("Book", book.getWordEn());
        assertTrue(book.getMeaningVi().contains("Quyển sách"));

        VisualWordInfo backpack = VisualVocabularyDatabase.lookup("backpack", 0.91f);
        assertNotNull(backpack);
        assertEquals("Backpack", backpack.getWordEn());
        assertTrue(backpack.getMeaningVi().contains("Ba lô"));

        VisualWordInfo glasses = VisualVocabularyDatabase.lookup("glasses", 0.89f);
        assertNotNull(glasses);
        assertEquals("Eyeglasses", glasses.getWordEn());
        assertTrue(glasses.getMeaningVi().contains("Kính mắt") || glasses.getMeaningVi().contains("mắt kính"));
    }

    @Test
    public void testLookupVehiclesAndClothing() {
        VisualWordInfo car = VisualVocabularyDatabase.lookup("car", 0.92f);
        assertNotNull(car);
        assertTrue(car.getWordEn().contains("Car"));
        assertTrue(car.getMeaningVi().contains("ô tô"));

        VisualWordInfo shoes = VisualVocabularyDatabase.lookup("shoes", 0.89f);
        assertNotNull(shoes);
        assertEquals("Shoes", shoes.getWordEn());
        assertTrue(shoes.getMeaningVi().contains("giày"));
    }

    @Test
    public void testSpeechSimilarityScoring() {
        int exactScore = SpeechEvaluationHelper.calculateSimilarityScore("laptop", "laptop");
        assertEquals(100, exactScore);

        int closeScore = SpeechEvaluationHelper.calculateSimilarityScore("labtop", "laptop");
        assertTrue(closeScore >= 80);

        int partialScore = SpeechEvaluationHelper.calculateSimilarityScore("phone", "smartphone");
        assertTrue(partialScore >= 90);
    }

    @Test
    public void testDynamicFallback() {
        VisualWordInfo info = VisualVocabularyDatabase.lookup("telescope", 0.75f);
        assertNotNull(info);
        assertEquals("Telescope", info.getWordEn());
        assertNotNull(info.getIpa());
        assertTrue(info.getMeaningVi().contains("Telescope"));
        assertNotNull(info.getExampleSentenceEn());
        assertTrue(info.getExampleSentenceEn().contains("telescope"));
        assertNotNull(info.getExampleSentenceVi());
        assertNotNull(info.getIdiomEn());
        assertNotNull(info.getQuizQuestionEn());

        // Dynamic fallback should also generate deep encyclopedic knowledge
        assertNotNull(info.getEtymologyRoot());
        assertNotNull(info.getComponentAnatomy());
        assertNotNull(info.getConfusingWordAlert());
        assertNotNull(info.getBritishVsAmerican());
        assertNotNull(info.getGrammarRuleNotes());
        assertNotNull(info.getIeltsSpeakingSample());
        assertNotNull(info.getPhrasalVerbs());
        assertNotNull(info.getSensoryAdjectives());
        assertNotNull(info.getMindmapTree());
        assertNotNull(info.getMnemonicHook());
        assertNotNull(info.getEspSpecializedVocab());
        assertNotNull(info.getShoppingInquiries());
        assertNotNull(info.getAiTutorFact());
    }
}
