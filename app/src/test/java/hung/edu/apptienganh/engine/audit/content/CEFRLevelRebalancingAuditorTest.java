package hung.edu.apptienganh.engine.audit.content;

import hung.edu.apptienganh.engine.audit.content.review.ContentReviewItem;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class CEFRLevelRebalancingAuditorTest {

    @Test
    public void testAuditSentenceForLevelRebalancing() {
        CEFRLevelRebalancingAuditor auditor = new CEFRLevelRebalancingAuditor();
        
        // A0 sentence too long
        List<ContentReviewItem> warnings = auditor.auditSentenceForLevelRebalancing("This is a very very very very very long sentence for level A0", "A0", "1");
        assertEquals(1, warnings.size());
        assertEquals("CEFR-1", warnings.get(0).getReviewId());
        
        // A2 sentence with C1 words
        warnings = auditor.auditSentenceForLevelRebalancing("Smartphones are ubiquitous", "A2", "2");
        assertEquals(1, warnings.size());
        
        // B2 sentence too short
        warnings = auditor.auditSentenceForLevelRebalancing("I like dogs", "B2", "3");
        assertEquals(1, warnings.size());
        
        // Valid A0
        warnings = auditor.auditSentenceForLevelRebalancing("I like dogs", "A0", "4");
        assertEquals(0, warnings.size());
    }
}
