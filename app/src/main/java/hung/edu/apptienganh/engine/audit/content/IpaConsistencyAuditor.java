package hung.edu.apptienganh.engine.audit.content;

import java.util.ArrayList;
import java.util.List;
import hung.edu.apptienganh.data.local.entity.Vocabulary;

public class IpaConsistencyAuditor {
    public VocabularyAuditReport audit(List<Vocabulary> items) {
        List<VocabularyAuditIssue> issues = new ArrayList<>();
        
        for (Vocabulary v : items) {
            String word = v.getWord() != null ? v.getWord() : "";
            String idInfo = "Vocab-" + v.getId() + "-" + word;
            
            checkIpaField(idInfo, v.getLevel(), "UK", v.getIpaUk(), issues);
            checkIpaField(idInfo, v.getLevel(), "US", v.getIpaUs(), issues);
        }
        
        return new VocabularyAuditReport(issues, items.size());
    }

    private void checkIpaField(String idInfo, String level, String region, String ipa, List<VocabularyAuditIssue> issues) {
        if (ipa == null || ipa.trim().isEmpty()) {
            issues.add(new VocabularyAuditIssue(idInfo, level, "EMPTY_IPA_" + region, "IPA " + region + " is empty", true));
            return;
        }
        
        if (!ipa.startsWith("/") || !ipa.endsWith("/")) {
            issues.add(new VocabularyAuditIssue(idInfo, level, "INVALID_IPA_FORMAT", "IPA " + region + " must be enclosed in /.../", true));
        }
        
        if (ipa.contains("TODO") || ipa.contains("unknown") || ipa.contains("N/A")) {
            issues.add(new VocabularyAuditIssue(idInfo, level, "PLACEHOLDER_IPA", "IPA " + region + " contains placeholder text", true));
        }
        
        // Basic check for Vietnamese characters
        if (ipa.matches(".*[àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ].*")) {
            issues.add(new VocabularyAuditIssue(idInfo, level, "VIETNAMESE_CHARS_IN_IPA", "IPA " + region + " contains Vietnamese text", true));
        }
    }
}
