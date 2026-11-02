package hung.edu.apptienganh.engine.audit.content;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import hung.edu.apptienganh.data.local.entity.Vocabulary;

public class VocabularyMasterAuditor {
    public VocabularyAuditReport audit(List<Vocabulary> items) {
        List<VocabularyAuditIssue> issues = new ArrayList<>();
        Set<String> uniqueWords = new HashSet<>();
        Set<String> uniqueExamples = new HashSet<>();

        for (Vocabulary v : items) {
            String word = v.getWord() != null ? v.getWord() : "";
            String idInfo = "Vocab-" + v.getId() + "-" + word;
            
            if (word.isEmpty()) {
                issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "EMPTY_WORD", "Word is empty", true));
            } else if (!word.equals(word.trim())) {
                issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "WHITESPACE", "Word has trailing/leading whitespace", false));
            }
            
            if (v.getType() == null || v.getType().isEmpty()) {
                issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "EMPTY_TYPE", "Type is empty", true));
            }
            
            if (v.getIpaUk() == null || v.getIpaUk().isEmpty()) {
                issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "EMPTY_IPA", "IPA UK is empty", true));
            }
            
            if (v.getMeaningVi() == null || v.getMeaningVi().isEmpty()) {
                issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "EMPTY_MEANING", "Meaning Vi is empty", true));
            } else if (v.getMeaningVi().length() < 3) {
                issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "VAGUE_MEANING", "Meaning Vi might be too short", false));
            }
            
            if (v.getContextMeaningVi() != null && v.getContextMeaningVi().equals(v.getMeaningVi())) {
                issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "DUPLICATE_CONTEXT_MEANING", "Context meaning is identical to meaning", false));
            }
            
            if (v.getExampleEn() == null || v.getExampleEn().isEmpty()) {
                issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "EMPTY_EXAMPLE", "Example EN is empty", true));
            } else {
                String[] wordsInExample = v.getExampleEn().split("\\s+");
                if (wordsInExample.length < 3) {
                    issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "SHORT_EXAMPLE", "Example EN has fewer than 3 words", false));
                }
                if (!uniqueExamples.add(v.getExampleEn().toLowerCase())) {
                    issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "DUPLICATE_EXAMPLE", "Example EN is duplicated across vocabularies", false));
                }
            }
            
            if (v.getExampleVi() == null || v.getExampleVi().isEmpty()) {
                issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "EMPTY_EXAMPLE_VI", "Example VI is empty", true));
            }
            
            if (v.getCollocations() == null || v.getCollocations().isEmpty()) {
                issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "EMPTY_COLLOCATION", "Collocations are empty", false));
            }
            
            if (v.getCommonMistake() == null || v.getCommonMistake().isEmpty()) {
                issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "EMPTY_MISTAKE", "Common mistake is empty", false));
            }

            String wordLevelKey = word.toLowerCase() + "_" + v.getLevel();
            if (!uniqueWords.add(wordLevelKey)) {
                issues.add(new VocabularyAuditIssue(idInfo, v.getLevel(), "DUPLICATE_WORD", "Duplicate word in the same level", true));
            }
        }
        
        return new VocabularyAuditReport(issues, items.size());
    }
}
