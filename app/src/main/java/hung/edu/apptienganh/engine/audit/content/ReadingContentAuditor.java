package hung.edu.apptienganh.engine.audit.content;

import java.util.ArrayList;
import java.util.List;
import hung.edu.apptienganh.data.local.entity.ReadingPassage;

public class ReadingContentAuditor {
    public ReadingAuditReport audit(List<ReadingPassage> items) {
        List<ReadingAuditIssue> issues = new ArrayList<>();
        
        for (ReadingPassage r : items) {
            String idInfo = "Reading-" + r.id + "-" + r.title;
            String level = r.level != null ? r.level : "Unknown";
            
            if (r.title == null || r.title.isEmpty()) {
                issues.add(new ReadingAuditIssue(idInfo, level, "EMPTY_TITLE", "Title is empty", true));
            }
            if (r.passageText == null || r.passageText.isEmpty()) {
                issues.add(new ReadingAuditIssue(idInfo, level, "EMPTY_PASSAGE", "Passage text is empty", true));
            } else {
                String[] words = r.passageText.split("\\s+");
                if (level.equals("A0") || level.equals("A1")) {
                    if (words.length > 150) {
                        issues.add(new ReadingAuditIssue(idInfo, level, "TOO_LONG_FOR_LEVEL", "Passage is too long for A0/A1", false));
                    }
                } else if (words.length > 1000) {
                    issues.add(new ReadingAuditIssue(idInfo, level, "TOO_LONG", "Passage is too long overall", false));
                }
            }
            
            if (r.vocabularyHints == null || r.vocabularyHints.isEmpty()) {
                issues.add(new ReadingAuditIssue(idInfo, level, "EMPTY_HINTS", "Vocabulary hints are empty", false));
            }
        }
        
        return new ReadingAuditReport(issues, items.size());
    }
}
