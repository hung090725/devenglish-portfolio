package hung.edu.apptienganh.engine.audit.content;

import java.util.ArrayList;
import java.util.List;
import hung.edu.apptienganh.data.local.entity.Conversation;
import hung.edu.apptienganh.data.local.entity.ConversationLine;

public class ConversationNaturalnessAuditor {
    public ConversationAuditReport audit(List<Conversation> conversations, List<ConversationLine> allLines) {
        List<ConversationAuditIssue> issues = new ArrayList<>();
        
        for (Conversation c : conversations) {
            String idInfo = "Conv-" + c.getId() + "-" + c.getTitle();
            String level = c.getLevel() != null ? c.getLevel() : "Unknown";
            
            if (c.getTitle() == null || c.getTitle().isEmpty()) {
                issues.add(new ConversationAuditIssue(idInfo, level, "EMPTY_TITLE", "Conversation title is empty", true));
            }
            if (c.getSituationVi() == null || c.getSituationVi().isEmpty()) {
                issues.add(new ConversationAuditIssue(idInfo, level, "EMPTY_SITUATION", "Situation VI is empty", true));
            }
            
            // Filter lines for this conversation
            long lineCount = allLines.stream().filter(l -> l.getConversationId() == c.getId()).count();
            if (lineCount < 4) {
                issues.add(new ConversationAuditIssue(idInfo, level, "TOO_SHORT", "Conversation has fewer than 4 lines", false));
            }
        }
        
        for (ConversationLine line : allLines) {
            String idInfo = "Line-" + line.getId() + "-(Conv-" + line.getConversationId() + ")";
            String level = "Unknown"; // Need to join with conv to get exact level, but acceptable for now
            
            if (line.getRole() == null || line.getRole().isEmpty()) {
                issues.add(new ConversationAuditIssue(idInfo, level, "EMPTY_SPEAKER", "Speaker role is empty", true));
            }
            if (line.getSentenceEn() == null || line.getSentenceEn().isEmpty()) {
                issues.add(new ConversationAuditIssue(idInfo, level, "EMPTY_SENTENCE", "Sentence EN is empty", true));
            }
            if (line.getSentenceVi() == null || line.getSentenceVi().isEmpty()) {
                issues.add(new ConversationAuditIssue(idInfo, level, "EMPTY_SENTENCE_VI", "Sentence VI is empty", true));
            }
            if (line.getIpa() == null || line.getIpa().isEmpty()) {
                issues.add(new ConversationAuditIssue(idInfo, level, "EMPTY_IPA", "Line IPA is empty", false));
            }
        }
        
        return new ConversationAuditReport(issues, conversations.size());
    }
}
