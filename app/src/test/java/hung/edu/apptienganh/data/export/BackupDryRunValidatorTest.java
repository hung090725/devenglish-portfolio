package hung.edu.apptienganh.data.export;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class BackupDryRunValidatorTest {

    private final BackupDryRunValidator validator = new BackupDryRunValidator();

    @Test
    public void valid_backup_passes() {
        String json = "{ \"version\": 2, \"lessons\": [{}, {}], \"vocabularies\": [{}] }";
        BackupDryRunValidator.ValidationResult result = validator.validateJson(json);
        
        assertTrue("Valid JSON should pass", result.isValid);
        assertNotNull("Preview should not be null", result.preview);
        assertEquals(2, result.preview.dayProgressCount);
        assertEquals(1, result.preview.vocabularyProgressCount);
    }

    @Test
    public void invalid_json_rejected() {
        String json = "{ invalid_json }";
        BackupDryRunValidator.ValidationResult result = validator.validateJson(json);
        
        assertFalse("Invalid JSON should fail", result.isValid);
        assertEquals("Định dạng JSON không hợp lệ.", result.errorMessage);
    }

    @Test
    public void newer_version_rejected() {
        String json = "{ \"version\": 999 }";
        BackupDryRunValidator.ValidationResult result = validator.validateJson(json);
        
        assertFalse("Newer version should fail", result.isValid);
        assertEquals("Bản sao lưu này thuộc về phiên bản ứng dụng mới hơn. Vui lòng cập nhật app.", result.errorMessage);
    }

    @Test
    public void missing_version_fails() {
        String json = "{ \"lessons\": [] }";
        BackupDryRunValidator.ValidationResult result = validator.validateJson(json);
        
        assertFalse("Missing version should fail", result.isValid);
        assertEquals("Không tìm thấy phiên bản sao lưu.", result.errorMessage);
    }
}
