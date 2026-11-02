package hung.edu.apptienganh;

import org.junit.Test;

import hung.edu.apptienganh.domain.scorer.SpeakingScorer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SpeakingScorerTest {

    @Test
    public void calculateScore_exactMatch_returnsFullScore() {
        SpeakingScorer.SpeakingScoreResult result =
                SpeakingScorer.calculateScore("My name is Hung.", "My name is Hung.");

        assertEquals(100, result.getScore());
        assertEquals(4, result.getCorrectWords().size());
        assertTrue(result.getMissingWords().isEmpty());
        assertTrue(result.getExtraWords().isEmpty());
        assertNotNull(result.getFeedbackVi());
    }

    @Test
    public void calculateScore_ignoresCaseAndPunctuation() {
        SpeakingScorer.SpeakingScoreResult result =
                SpeakingScorer.calculateScore("Hello, World!", "hello world");

        assertEquals(100, result.getScore());
        assertEquals(2, result.getCorrectWords().size());
    }

    @Test
    public void calculateScore_nullOrEmptyRecognized_returnsZero() {
        SpeakingScorer.SpeakingScoreResult emptyRecognized =
                SpeakingScorer.calculateScore("My name is Hung.", "");
        SpeakingScorer.SpeakingScoreResult nullRecognized =
                SpeakingScorer.calculateScore("My name is Hung.", null);

        assertEquals(0, emptyRecognized.getScore());
        assertEquals(0, nullRecognized.getScore());
        assertEquals(4, emptyRecognized.getMissingWords().size());
        assertTrue(emptyRecognized.getFeedbackVi().contains("chậm"));
    }

    @Test
    public void calculateScore_nullOrEmptyTarget_returnsZero() {
        SpeakingScorer.SpeakingScoreResult nullTarget =
                SpeakingScorer.calculateScore(null, "hello");
        SpeakingScorer.SpeakingScoreResult emptyTarget =
                SpeakingScorer.calculateScore("   ", "hello");

        assertEquals(0, nullTarget.getScore());
        assertEquals(0, emptyTarget.getScore());
    }

    @Test
    public void calculateScore_partialMatch_returnsExpectedScore() {
        SpeakingScorer.SpeakingScoreResult result =
                SpeakingScorer.calculateScore("I am a student", "I am student");

        assertEquals(75, result.getScore());
        assertEquals(3, result.getCorrectWords().size());
        assertEquals(1, result.getMissingWords().size());
        assertEquals("a", result.getMissingWords().get(0));
    }

    @Test
    public void calculateScore_extraWord_detected() {
        SpeakingScorer.SpeakingScoreResult result =
                SpeakingScorer.calculateScore("I am a student", "I am a good student");

        assertFalse(result.getExtraWords().isEmpty());
        assertTrue(result.getExtraWords().contains("good"));
    }

    @Test
    public void calculateScore_providesFeedbackVi() {
        SpeakingScorer.SpeakingScoreResult result =
                SpeakingScorer.calculateScore("Hello world", "hello");

        assertNotNull(result.getFeedbackVi());
        assertFalse(result.getFeedbackVi().isEmpty());
    }
}
