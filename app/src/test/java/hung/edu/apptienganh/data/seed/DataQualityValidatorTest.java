package hung.edu.apptienganh.data.seed;

import org.junit.Test;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.GrammarPoint;
import hung.edu.apptienganh.data.local.entity.QuizQuestion;
import hung.edu.apptienganh.data.local.entity.Vocabulary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DataQualityValidatorTest {

    @Test
    public void validateVocabularyQuality() {
        List<Vocabulary> a0 = A0VocabularySeed.buildVocabularyList();
        List<Vocabulary> a1 = A1VocabularySeed.buildVocabularyList();
        List<Vocabulary> a2 = A2VocabularySeed.buildVocabularyList();
        List<Vocabulary> B1 = B1VocabularySeed.buildVocabularyList();
        
        List<Vocabulary> b2 = B2VocabSeedHelper.getVocabularies();
        List<Vocabulary> c1 = C1VocabSeedHelper.getVocabularies();

        validateVocabList(a0);
        validateVocabList(a1);
        validateVocabList(a2);
        validateVocabList(B1);
        validateVocabList(b2);
        validateVocabList(c1);

        assertTrue("A2 vocabulary count should be >= 130", a2.size() >= 130);
        assertTrue("B1 vocabulary count should be >= 200", B1.size() >= 200);
        assertTrue("B2 vocabulary count should be >= 200", b2.size() >= 200);
        assertTrue("C1 vocabulary count should be >= 200", c1.size() >= 200);
    }

    private void validateVocabList(List<Vocabulary> list) {
        for (Vocabulary v : list) {
            assertNotNull("Word cannot be null", v.getWord());
            assertFalse("Word cannot be empty", v.getWord().trim().isEmpty());
            
            assertNotNull("IPA cannot be null", v.getIpaUk()); // Or IpaUs, but at least not null. We'll check general
            
            assertNotNull("Type cannot be null", v.getType());
            assertNotNull("MeaningVi cannot be null", v.getMeaningVi());
            assertFalse("MeaningVi cannot be empty", v.getMeaningVi().trim().isEmpty());
            
            assertNotNull("ExampleEn cannot be null", v.getExampleEn());
            assertFalse("ExampleEn cannot be empty", v.getExampleEn().trim().isEmpty());
        }
    }

    @Test
    public void validateGrammarQuality() {
        List<GrammarPoint> a0 = A0GrammarSeed.buildGrammarPoints();
        List<GrammarPoint> a1 = A1GrammarSeed.buildGrammarPoints();
        List<GrammarPoint> a2 = A2GrammarSeed.buildGrammarPoints();
        List<GrammarPoint> b1 = B1GrammarSeed.buildGrammarPoints();

        List<GrammarPoint> b2 = B2GrammarSeed.getGrammarPoints();
        List<GrammarPoint> c1 = C1GrammarSeed.getGrammarPoints();

        validateGrammarList(a0);
        validateGrammarList(a1);
        validateGrammarList(a2);
        validateGrammarList(b1);
        validateGrammarList(b2);
        validateGrammarList(c1);

        assertTrue("A2 grammar count should be >= 16", a2.size() >= 16);
        assertTrue("B1 grammar count should be >= 18", b1.size() >= 18);
        assertTrue("B2 grammar count should be >= 18", b2.size() >= 18);
        assertTrue("C1 grammar count should be >= 18", c1.size() >= 18);
    }

    private void validateGrammarList(List<GrammarPoint> list) {
        for (GrammarPoint g : list) {
            assertNotNull("Title cannot be null", g.getTitle());
            assertFalse("Title cannot be empty", g.getTitle().trim().isEmpty());
            assertNotNull("Formula cannot be null", g.getFormula());
            assertFalse("Formula cannot be empty", g.getFormula().trim().isEmpty());
            
            assertNotNull("UsageVi cannot be null", g.getUsageVi());
            assertFalse("UsageVi cannot be empty", g.getUsageVi().trim().isEmpty());
            
            assertNotNull("Correct examples cannot be null", g.getCorrectExamples());
            assertFalse("Correct examples should not be empty", g.getCorrectExamples().trim().isEmpty());
            
            assertNotNull("Wrong examples cannot be null", g.getWrongExamples());
            assertFalse("Wrong examples should not be empty", g.getWrongExamples().trim().isEmpty());
            
            assertNotNull("ExplanationVi cannot be null", g.getExplanationVi());
            assertFalse("ExplanationVi should not be empty", g.getExplanationVi().trim().isEmpty());
        }
    }

    @Test
    public void validateConversationQuality() {
        assertTrue("A2 conversation count should be >= 14", A2ConversationSeed.buildConversations().size() >= 14);
        assertTrue("B1 conversation count should be >= 16", B1ConversationSeed.buildConversations().size() >= 16);
        assertTrue("B2 conversation count should be >= 14", B2LessonSeed.getConversations().size() >= 14);
        assertTrue("C1 conversation count should be >= 14", C1LessonSeed.getConversations().size() >= 14);
    }

    @Test
    public void validateQuizQuality() {
        List<QuizQuestion> a0 = A0QuizSeed.buildDayQuizzes();
        validateQuizList(a0);

        int a2QuizCount = 0;
        for (int day = 1; day <= 14; day++) {
            List<QuizQuestion> dayQuizzes = A2QuizSeed.buildDayQuizzes(day, day + 28);
            validateQuizList(dayQuizzes);
            a2QuizCount += dayQuizzes.size();
        }
        assertTrue("A2 quiz count should be >= 70", a2QuizCount >= 70);

        int b1QuizCount = 0;
        for (int day = 1; day <= 14; day++) {
            List<QuizQuestion> dayQuizzes = B1QuizSeed.buildDayQuizzes(day, day + 42);
            validateQuizList(dayQuizzes);
            b1QuizCount += dayQuizzes.size();
        }
        assertTrue("B1 quiz count should be >= 70", b1QuizCount >= 70);

        List<QuizQuestion> b2Quizzes = B2LessonSeed.getQuizQuestions();
        validateQuizList(b2Quizzes);
        assertTrue("B2 quiz count should be >= 70", b2Quizzes.size() >= 70);

        List<QuizQuestion> c1Quizzes = C1LessonSeed.getQuizQuestions();
        validateQuizList(c1Quizzes);
        assertTrue("C1 quiz count should be >= 70", c1Quizzes.size() >= 70);
    }

    private void validateQuizList(List<QuizQuestion> list) {
        for (QuizQuestion q : list) {
            assertNotNull("Question text cannot be null", q.getQuestion());
            assertFalse("Question cannot be empty", q.getQuestion().trim().isEmpty());
            
            assertNotNull("OptionA cannot be null", q.getOptionA());
            assertFalse("OptionA cannot be empty", q.getOptionA().trim().isEmpty());
            
            assertNotNull("ExplanationVi cannot be null", q.getExplanationVi());
            assertFalse("ExplanationVi cannot be empty", q.getExplanationVi().trim().isEmpty());

            String correct = q.getCorrectAnswer();
            assertNotNull("Correct answer cannot be null", correct);
            assertTrue("Correct answer must be A, B, C, or D", 
                    correct.equals("A") || correct.equals("B") || correct.equals("C") || correct.equals("D"));
        }
    }
}
