package hung.edu.apptienganh.engine.audit.content;

import hung.edu.apptienganh.engine.audit.content.review.ContentReviewItem;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class QuizAnswerVerificationAuditorTest {

    @Test
    public void testValidQuiz() {
        QuizAnswerVerificationAuditor auditor = new QuizAnswerVerificationAuditor();
        List<ContentReviewItem> warnings = auditor.auditQuiz("q1", "A1", "Question?", "A", "B", "C", "D", "B");
        assertTrue(warnings.isEmpty());
    }

    @Test
    public void testEmptyAnswer() {
        QuizAnswerVerificationAuditor auditor = new QuizAnswerVerificationAuditor();
        List<ContentReviewItem> warnings = auditor.auditQuiz("q1", "A1", "Question?", "A", "B", "C", "D", "");
        assertEquals(1, warnings.size());
        assertEquals("CRITICAL", warnings.get(0).getConfidence());
    }

    @Test
    public void testLowercaseAnswer() {
        QuizAnswerVerificationAuditor auditor = new QuizAnswerVerificationAuditor();
        List<ContentReviewItem> warnings = auditor.auditQuiz("q1", "A1", "Question?", "A", "B", "C", "D", "a");
        assertEquals(1, warnings.size());
        assertEquals("HIGH", warnings.get(0).getConfidence());
    }

    @Test
    public void testDuplicateOptions() {
        QuizAnswerVerificationAuditor auditor = new QuizAnswerVerificationAuditor();
        List<ContentReviewItem> warnings = auditor.auditQuiz("q1", "A1", "Question?", "A", "A", "C", "D", "B");
        assertEquals(1, warnings.size());
        assertEquals("HIGH", warnings.get(0).getConfidence());
    }
}
