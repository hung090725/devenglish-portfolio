package hung.edu.apptienganh.engine.audit.content.correction;

import java.util.Arrays;
import java.util.List;

public class ContentCorrectionPatchValidator {

    private static final List<String> VALID_LEVELS = Arrays.asList("A0", "A1", "A2", "B1", "B2", "C1");
    private static final List<String> VALID_ANSWERS = Arrays.asList("A", "B", "C", "D");

    public boolean validate(ContentCorrectionPatch patch, String currentActualValue) {
        if (patch == null) return false;

        // 1. Check if oldValue matches actual data
        if (currentActualValue != null && !currentActualValue.equals(patch.getOldValue())) {
            return false;
        }

        // 2. Cannot apply if newValue is empty for critical fields
        if (patch.getNewValue() == null || patch.getNewValue().trim().isEmpty()) {
            if (isCriticalField(patch.getFieldName())) {
                return false;
            }
        }

        // 3. LOW confidence must be NEEDS_REVIEW
        if ("LOW".equalsIgnoreCase(patch.getConfidence()) && !"NEEDS_REVIEW".equalsIgnoreCase(patch.getStatus())) {
            return false;
        }

        // 4. CorrectAnswer validation
        if ("correctAnswer".equals(patch.getFieldName()) && !VALID_ANSWERS.contains(patch.getNewValue())) {
            return false;
        }

        // 5. Level validation
        if ("level".equals(patch.getFieldName()) && !VALID_LEVELS.contains(patch.getNewValue())) {
            return false;
        }

        return true;
    }

    private boolean isCriticalField(String fieldName) {
        List<String> criticalFields = Arrays.asList("word", "correctAnswer", "question", "title", "promptText");
        return criticalFields.contains(fieldName);
    }
}
