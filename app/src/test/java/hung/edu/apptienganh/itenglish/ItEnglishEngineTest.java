package hung.edu.apptienganh.itenglish;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import hung.edu.apptienganh.ui.itenglish.engine.ItEnglishEngine;
import hung.edu.apptienganh.ui.itenglish.model.CodeSnippet;
import hung.edu.apptienganh.ui.itenglish.model.InterviewQuestion;
import hung.edu.apptienganh.ui.itenglish.model.ItEvaluationResult;
import hung.edu.apptienganh.ui.itenglish.model.TechDocArticle;

public class ItEnglishEngineTest {

    private ItEnglishEngine engine;

    @Before
    public void setUp() {
        engine = ItEnglishEngine.getInstance();
    }

    @Test
    public void testCodeSnippetsLoaded() {
        List<CodeSnippet> snippets = engine.getAllCodeSnippets();
        assertNotNull(snippets);
        assertTrue("Must have at least 5 coding snippets", snippets.size() >= 5);

        CodeSnippet javaSnippet = snippets.get(0);
        assertEquals("Java", javaSnippet.getLanguage());
        assertNotNull(javaSnippet.getCode());
        assertFalse(javaSnippet.getRequiredKeyTerms().isEmpty());
    }

    @Test
    public void testCodeToEnglishIndependentDualRubric() {
        CodeSnippet javaSnippet = engine.getAllCodeSnippets().get(0);

        // Good technical and good English
        String goodAnswer = "This function takes two integer arguments a and b, and returns their sum as an integer.";
        ItEvaluationResult result = engine.evaluateCodeExplanation(javaSnippet, goodAnswer);

        assertTrue("Technical score should be high", result.getTechnicalScore() >= 80);
        assertTrue("English score should be high", result.getEnglishScore() >= 80);
        assertNotNull(result.getImprovedExpressionEn());
        assertFalse("Matched terms should not be empty", result.getMatchedTerms().isEmpty());

        // Empty answer safety check
        ItEvaluationResult emptyResult = engine.evaluateCodeExplanation(javaSnippet, "");
        assertEquals(0, emptyResult.getTechnicalScore());
        assertEquals(0, emptyResult.getEnglishScore());
    }

    @Test
    public void testTechDocArticlesExtractionAndQuiz() {
        List<TechDocArticle> articles = engine.getAllTechArticles();
        assertNotNull(articles);
        assertTrue("Must have at least 2 documentation articles", articles.size() >= 2);

        TechDocArticle reactDoc = articles.get(0);
        assertEquals("React / Frontend", reactDoc.getTechnology());
        assertFalse("Must have extracted vocabularies", reactDoc.getVocabularies().isEmpty());
        assertFalse("Must have comprehension quizzes", reactDoc.getQuizzes().isEmpty());

        // Validate vocabulary fields
        assertNotNull(reactDoc.getVocabularies().get(0).getTerm());
        assertNotNull(reactDoc.getVocabularies().get(0).getIpa());
        assertNotNull(reactDoc.getVocabularies().get(0).getMeaningVi());
    }

    @Test
    public void testInterviewSimulatorRubric() {
        List<InterviewQuestion> questions = engine.getAllInterviewQuestions();
        assertNotNull(questions);
        assertTrue("Must have interview questions across rounds", questions.size() >= 4);

        InterviewQuestion introQ = questions.get(0);
        String spokenAnswer = "I graduated with a computer science degree and have experience building backend REST microservices with Spring Boot.";

        Map<String, Object> eval = engine.evaluateInterviewResponse(introQ, spokenAnswer);
        assertNotNull(eval);
        assertTrue((int) eval.get("overallScore") >= 60);
        assertTrue((int) eval.get("technicalScore") >= 60);
        assertNotNull(eval.get("feedbackVi"));
        assertNotNull(eval.get("sampleAnswerEn"));
    }

    @Test
    public void testBugReportEvaluation() {
        Map<String, Object> goodBug = engine.evaluateBugReport(
                "[Auth] Login button fails on Safari 16",
                "1. Go to /login\n2. Click button",
                "User redirected to home",
                "Button does not respond",
                "macOS Safari 16"
        );
        assertTrue("Good bug report should pass", (boolean) goodBug.get("isPass"));
        assertTrue((int) goodBug.get("score") >= 70);

        Map<String, Object> poorBug = engine.evaluateBugReport(
                "broken",
                "click it",
                "",
                "",
                ""
        );
        assertFalse("Poor bug report should not pass", (boolean) poorBug.get("isPass"));
    }

    @Test
    public void testGitCommitConventionalValidation() {
        Map<String, Object> validCommit = engine.evaluateGitCommit("feat(auth): implement JWT token authentication filter");
        assertTrue("Should be conventional commit compliant", (boolean) validCommit.get("isStandard"));
        assertTrue((int) validCommit.get("score") >= 80);

        Map<String, Object> badCommit = engine.evaluateGitCommit("fixed some bugs and updating stuff");
        assertFalse("Should warn on past tense and missing type", (boolean) badCommit.get("isStandard"));
    }

    @Test
    public void testDailyStandupScrumEvaluation() {
        Map<String, Object> standup = engine.evaluateDailyStandup(
                "Yesterday I completed the user profile API and unit tests.",
                "Today I will implement the notification webhook service.",
                "No blockers at the moment."
        );
        assertTrue("Complete standup should score high", (int) standup.get("score") >= 80);
    }
}
