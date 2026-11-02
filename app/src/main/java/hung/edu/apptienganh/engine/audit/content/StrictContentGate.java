package hung.edu.apptienganh.engine.audit.content;

import java.util.List;
import hung.edu.apptienganh.data.local.entity.*;

public class StrictContentGate {

    public StrictContentGateResult runGate(
            List<Vocabulary> vocabularies,
            List<GrammarPoint> grammarPoints,
            List<Conversation> conversations,
            List<ConversationLine> conversationLines,
            List<QuizQuestion> quizzes,
            List<ReadingPassage> readingPassages,
            List<WritingPrompt> writingPrompts,
            List<SkillSentenceAuditor.SkillSentenceItem> skillSentences) {
            
        StrictContentGateResult result = new StrictContentGateResult();
        
        // 1. Vocabulary Master Audit
        if (vocabularies != null) {
            VocabularyAuditReport vocabReport = new VocabularyMasterAuditor().audit(vocabularies);
            for (VocabularyAuditIssue issue : vocabReport.issues) {
                result.addIssue(issue.isCritical, issue.targetId + " - " + issue.description);
            }
            
            VocabularyAuditReport ipaReport = new IpaConsistencyAuditor().audit(vocabularies);
            for (VocabularyAuditIssue issue : ipaReport.issues) {
                result.addIssue(issue.isCritical, issue.targetId + " - " + issue.description);
            }
        }
        
        // 2. Grammar Audit
        if (grammarPoints != null) {
            GrammarAuditReport grammarReport = new GrammarMasterAuditor().audit(grammarPoints);
            for (GrammarAuditIssue issue : grammarReport.issues) {
                result.addIssue(issue.isCritical, issue.targetId + " - " + issue.description);
            }
        }
        
        // 3. Conversation Audit
        if (conversations != null && conversationLines != null) {
            ConversationAuditReport convReport = new ConversationNaturalnessAuditor().audit(conversations, conversationLines);
            for (ConversationAuditIssue issue : convReport.issues) {
                result.addIssue(issue.isCritical, issue.targetId + " - " + issue.description);
            }
        }
        
        // 4. Quiz Audit
        if (quizzes != null) {
            QuizAuditReport quizReport = new QuizValidityAuditor().audit(quizzes);
            for (QuizAuditIssue issue : quizReport.issues) {
                result.addIssue(issue.isCritical, issue.targetId + " - " + issue.description);
            }
        }
        
        // 5. Reading Audit
        if (readingPassages != null) {
            ReadingAuditReport readingReport = new ReadingContentAuditor().audit(readingPassages);
            for (ReadingAuditIssue issue : readingReport.issues) {
                result.addIssue(issue.isCritical, issue.targetId + " - " + issue.description);
            }
        }
        
        // 6. Writing Prompt Audit
        if (writingPrompts != null) {
            WritingPromptAuditReport writingReport = new WritingPromptAuditor().audit(writingPrompts);
            for (WritingPromptAuditIssue issue : writingReport.issues) {
                result.addIssue(issue.isCritical, issue.targetId + " - " + issue.description);
            }
        }
        
        // 7. Skill Sentence Audit
        if (skillSentences != null) {
            SkillSentenceAuditReport skillReport = new SkillSentenceAuditor().audit(skillSentences);
            for (SkillSentenceAuditIssue issue : skillReport.issues) {
                result.addIssue(issue.isCritical, issue.targetId + " - " + issue.description);
            }
        }
        
        return result;
    }
}
