package hung.edu.apptienganh.manager;

import hung.edu.apptienganh.data.local.entity.LearningEvent;

public class LocalAnalyticsTracker {
    public void trackEvent(String eventType, String screenName, String level) {
        LearningEvent event = new LearningEvent();
        event.eventType = eventType;
        event.screenName = screenName;
        event.level = level;
        event.createdAt = System.currentTimeMillis();
        // Assume save to DAO
    }
}
