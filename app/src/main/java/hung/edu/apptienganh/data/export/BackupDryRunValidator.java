package hung.edu.apptienganh.data.export;

import org.json.JSONException;
import org.json.JSONObject;

public class BackupDryRunValidator {

    public static final int CURRENT_BACKUP_VERSION = 2; // Ví dụ version hiện tại là 2

    public static class ValidationResult {
        public boolean isValid;
        public String errorMessage;
        public BackupPreview preview;

        public ValidationResult(boolean isValid, String errorMessage, BackupPreview preview) {
            this.isValid = isValid;
            this.errorMessage = errorMessage;
            this.preview = preview;
        }
    }

    public ValidationResult validateJson(String jsonString) {
        if (jsonString == null || jsonString.trim().isEmpty()) {
            return new ValidationResult(false, "File sao lưu trống hoặc hỏng.", null);
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            
            // Check version
            if (!jsonObject.has("version")) {
                return new ValidationResult(false, "Không tìm thấy phiên bản sao lưu.", null);
            }

            int version = jsonObject.getInt("version");
            if (version > CURRENT_BACKUP_VERSION) {
                return new ValidationResult(false, "Bản sao lưu này thuộc về phiên bản ứng dụng mới hơn. Vui lòng cập nhật app.", null);
            }

            // Preview counts
            int lessonsCount = 0;
            if (jsonObject.has("lessons")) {
                lessonsCount = jsonObject.getJSONArray("lessons").length();
            }

            int vocabCount = 0;
            if (jsonObject.has("vocabularies")) {
                vocabCount = jsonObject.getJSONArray("vocabularies").length();
            }

            BackupPreview preview = new BackupPreview();
            preview.dayProgressCount = lessonsCount;
            preview.vocabularyProgressCount = vocabCount;
            preview.isValid = true;

            return new ValidationResult(true, "Hợp lệ", preview);

        } catch (JSONException e) {
            return new ValidationResult(false, "Định dạng JSON không hợp lệ.", null);
        }
    }
}
