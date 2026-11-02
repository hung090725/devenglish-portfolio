package hung.edu.apptienganh.engine.audit.content.review.lock;

import java.util.HashMap;
import java.util.Map;

public class ReviewedContentLock {

    private Map<String, ReviewedContentLockEntry> lockedEntries = new HashMap<>();

    public void lockContent(String sourceId, String fieldName, String value, String reviewerName) {
        String key = sourceId + "_" + fieldName;
        String lockedAt = String.valueOf(System.currentTimeMillis());
        lockedEntries.put(key, new ReviewedContentLockEntry(sourceId, fieldName, value, lockedAt, reviewerName));
    }

    public boolean isLocked(String sourceId, String fieldName) {
        return lockedEntries.containsKey(sourceId + "_" + fieldName);
    }

    public boolean verifyValueMatchesLock(String sourceId, String fieldName, String currentValue) {
        ReviewedContentLockEntry entry = lockedEntries.get(sourceId + "_" + fieldName);
        if (entry == null) return true; // Not locked
        return entry.getLockedValue().equals(currentValue);
    }
}
