package hung.edu.apptienganh.ai;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class AiTutorEngineTest {

    @Test
    public void testAiMessageProperties() {
        AiMessage msg = new AiMessage(AiMessage.SENDER_USER, "Hello world", "Tip");
        assertTrue(msg.isUser());
        assertEquals("Hello world", msg.getText());
        assertEquals("Tip", msg.getFeedbackVi());

        AiMessage aiMsg = new AiMessage(AiMessage.SENDER_AI, "Hi there");
        assertFalse(aiMsg.isUser());
    }

    @Test
    public void testOfflineChatCoffeeTopic() {
        AiTutorEngine engine = AiTutorEngine.getInstance();
        AiMessage response = engine.generateOfflineChatResponse("At a Coffee Shop", null, "I want to order an iced latte please.");
        assertNotNull(response);
        assertFalse(response.isUser());
        assertNotNull(response.getText());
        assertTrue(response.getText().toLowerCase().contains("latte") || response.getText().toLowerCase().contains("size") || response.getText().toLowerCase().contains("choice"));
    }

    @Test
    public void testOfflineChatDetectsMistake() {
        AiTutorEngine engine = AiTutorEngine.getInstance();
        AiMessage response = engine.generateOfflineChatResponse("General Chat", null, "i am agree with you.");
        assertNotNull(response);
        assertNotNull(response.getFeedbackVi());
        assertTrue(response.getFeedbackVi().contains("agree") || response.getFeedbackVi().contains("viết hoa"));
    }

    @Test
    public void testOfflineWritingReview() {
        AiTutorEngine engine = AiTutorEngine.getInstance();
        AiWritingReview review = engine.generateOfflineWritingReview("My Daily Routine", "i very like playing football because it makes me healthy.");
        assertNotNull(review);
        assertTrue(review.getScore() > 0);
        assertNotNull(review.getSummaryVi());
        assertNotNull(review.getNaturalRewriteEn());
        assertFalse(review.getCorrections().isEmpty() || review.getGrammarTips().isEmpty());
    }

    @Test
    public void testOfflineExplanation() {
        AiTutorEngine engine = AiTutorEngine.getInstance();
        List<String> options = new ArrayList<>();
        options.add("go");
        options.add("went");
        options.add("gone");
        options.add("goes");

        String explanation = engine.generateOfflineExplanation("She _____ to school yesterday.", options, "went", "go");
        assertNotNull(explanation);
        assertTrue(explanation.contains("yesterday"));
        assertTrue(explanation.contains("went"));
    }
}
