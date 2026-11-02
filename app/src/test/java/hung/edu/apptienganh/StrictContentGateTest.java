package hung.edu.apptienganh;

import org.junit.Test;
import java.util.Collections;
import hung.edu.apptienganh.data.local.entity.Vocabulary;
import hung.edu.apptienganh.engine.audit.content.StrictContentGate;
import hung.edu.apptienganh.engine.audit.content.StrictContentGateResult;
import static org.junit.Assert.*;

public class StrictContentGateTest {
    
    @Test
    public void testGateFailsOnCriticalIssue() {
        Vocabulary badVocab = new Vocabulary();
        badVocab.setId(1);
        badVocab.setWord(""); // Empty word -> Critical
        
        StrictContentGate gate = new StrictContentGate();
        StrictContentGateResult result = gate.runGate(
                Collections.singletonList(badVocab),
                null, null, null, null, null, null, null
        );
        
        assertEquals(StrictContentGateResult.Status.FAIL, result.getStatus());
        assertTrue(result.getCriticalIssueCount() > 0);
    }
    
    @Test
    public void testGatePassesWhenClean() {
        Vocabulary goodVocab = new Vocabulary();
        goodVocab.setId(2);
        goodVocab.setWord("apple");
        goodVocab.setType("noun");
        goodVocab.setIpaUk("/ˈæpl/");
        goodVocab.setIpaUs("/ˈæpl/");
        goodVocab.setMeaningVi("quả táo");
        goodVocab.setExampleEn("I ate an apple.");
        goodVocab.setExampleVi("Tôi đã ăn một quả táo.");
        goodVocab.setLevel("A1");
        
        StrictContentGate gate = new StrictContentGate();
        StrictContentGateResult result = gate.runGate(
                Collections.singletonList(goodVocab),
                null, null, null, null, null, null, null
        );
        
        // Collocation and Mistake are empty -> Warning
        assertEquals(StrictContentGateResult.Status.PASS_WITH_WARNINGS, result.getStatus());
        assertEquals(0, result.getCriticalIssueCount());
        assertTrue(result.getWarningIssueCount() > 0);
    }
}
