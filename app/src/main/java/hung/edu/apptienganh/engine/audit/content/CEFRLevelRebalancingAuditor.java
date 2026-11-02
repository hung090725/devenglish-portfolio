package hung.edu.apptienganh.engine.audit.content;

import hung.edu.apptienganh.engine.audit.content.review.ContentReviewItem;
import java.util.ArrayList;
import java.util.List;

public class CEFRLevelRebalancingAuditor {

    public List<ContentReviewItem> auditSentenceForLevelRebalancing(String sentence, String currentLevel, String itemId) {
        List<ContentReviewItem> warnings = new ArrayList<>();
        if (sentence == null || currentLevel == null) return warnings;

        int wordCount = sentence.split("\\s+").length;
        
        switch (currentLevel.toUpperCase()) {
            case "A0":
                if (wordCount > 10) {
                    warnings.add(new ContentReviewItem(
                        "CEFR-" + itemId, "Sentence", "A0", itemId, "sentenceEn", sentence, 
                        "Sentence too long for A0 (>10 words)", "Move to A1/A2 or rewrite shorter", "HIGH", "NEEDS_REVIEW"
                    ));
                }
                break;
            case "A1":
                if (wordCount > 15) {
                    warnings.add(new ContentReviewItem(
                        "CEFR-" + itemId, "Sentence", "A1", itemId, "sentenceEn", sentence, 
                        "Sentence too long for A1 (>15 words)", "Move to A2 or split", "HIGH", "NEEDS_REVIEW"
                    ));
                }
                break;
            case "A2":
                if (sentence.contains("ubiquitous") || sentence.contains("pragmatic")) {
                    warnings.add(new ContentReviewItem(
                        "CEFR-" + itemId, "Sentence", "A2", itemId, "sentenceEn", sentence, 
                        "Contains C1/C2 vocabulary", "Move to C1 or replace word", "HIGH", "NEEDS_REVIEW"
                    ));
                }
                break;
            case "B2":
                if (wordCount < 4) {
                    warnings.add(new ContentReviewItem(
                        "CEFR-" + itemId, "Sentence", "B2", itemId, "sentenceEn", sentence, 
                        "Sentence too short/simple for B2", "Move to A1/A2 or make complex", "MEDIUM", "NEEDS_REVIEW"
                    ));
                }
                break;
        }
        return warnings;
    }
}
