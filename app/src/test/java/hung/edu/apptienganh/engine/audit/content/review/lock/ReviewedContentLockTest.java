package hung.edu.apptienganh.engine.audit.content.review.lock;

import org.junit.Test;
import static org.junit.Assert.*;

public class ReviewedContentLockTest {

    @Test
    public void testLocking() {
        ReviewedContentLock lock = new ReviewedContentLock();
        assertFalse(lock.isLocked("src1", "content"));
        
        lock.lockContent("src1", "content", "locked value", "Reviewer 1");
        
        assertTrue(lock.isLocked("src1", "content"));
        assertTrue(lock.verifyValueMatchesLock("src1", "content", "locked value"));
        assertFalse(lock.verifyValueMatchesLock("src1", "content", "changed value"));
    }
}
