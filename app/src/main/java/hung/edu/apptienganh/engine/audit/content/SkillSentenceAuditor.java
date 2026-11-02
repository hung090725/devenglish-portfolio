package hung.edu.apptienganh.engine.audit.content;

import java.util.ArrayList;
import java.util.List;

public class SkillSentenceAuditor {

    public static class SkillSentenceItem {
        public String id;
        public String level;
        public String sentenceEn;
        public String sentenceVi;
        public String ipa;
        
        public SkillSentenceItem(String id, String level, String sentenceEn, String sentenceVi, String ipa) {
            this.id = id;
            this.level = level;
            this.sentenceEn = sentenceEn;
            this.sentenceVi = sentenceVi;
            this.ipa = ipa;
        }
    }

    public SkillSentenceAuditReport audit(List<SkillSentenceItem> items) {
        List<SkillSentenceAuditIssue> issues = new ArrayList<>();
        
        for (SkillSentenceItem s : items) {
            String level = s.level != null ? s.level : "Unknown";
            
            if (s.sentenceEn == null || s.sentenceEn.isEmpty()) {
                issues.add(new SkillSentenceAuditIssue(s.id, level, "EMPTY_SENTENCE", "Sentence EN is empty", true));
            } else {
                if (s.sentenceEn.matches(".*[<>{}\\[\\]\\\\].*")) {
                    issues.add(new SkillSentenceAuditIssue(s.id, level, "INVALID_CHARS", "Sentence contains strange characters that might break TTS", true));
                }
                
                String[] words = s.sentenceEn.split("\\s+");
                if ((level.equals("A0") || level.equals("A1")) && words.length > 20) {
                    issues.add(new SkillSentenceAuditIssue(s.id, level, "TOO_LONG", "Sentence is too long for A0/A1", false));
                }
            }
            
            if (s.sentenceVi == null || s.sentenceVi.isEmpty()) {
                issues.add(new SkillSentenceAuditIssue(s.id, level, "EMPTY_SENTENCE_VI", "Sentence VI is empty", true));
            }
            
            if (s.ipa != null && s.ipa.isEmpty()) {
                // If ipa is provided but empty string
                issues.add(new SkillSentenceAuditIssue(s.id, level, "EMPTY_IPA_FIELD", "IPA field exists but is empty", false));
            }
        }
        
        return new SkillSentenceAuditReport(issues, items.size());
    }
}
