package hung.edu.apptienganh.qa;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SoloQaGeneratorsTest {

    @Test
    public void testTesterMessageGenerator() {
        String msg = TesterMessageGenerator.generateZaloMessage("0.9.3-beta");
        assertTrue(msg.contains("0.9.3-beta"));
        assertTrue(msg.contains("Diagnostics"));
        assertTrue(msg.contains("Beta"));
        assertFalse(msg.contains("keystore")); // Ensure no sensitive data

        String telegramMsg = TesterMessageGenerator.generateTelegramMessage("0.9.3-beta");
        assertTrue(telegramMsg.contains("0.9.3-beta"));
    }

    @Test
    public void testReleaseNotesGenerator() {
        String notes = ReleaseNotesGenerator.generateMarkdownReleaseNotes("0.9.3-beta");
        assertTrue(notes.contains("0.9.3-beta"));
        assertTrue(notes.contains("A0"));
        assertTrue(notes.contains("A1"));
        assertTrue(notes.contains("B1"));
        assertTrue(notes.contains("Backup"));
        assertTrue(notes.contains("Known Limitations"));

        String plain = ReleaseNotesGenerator.generatePlainTextReleaseNotes("0.9.3-beta");
        assertTrue(plain.contains("0.9.3-beta"));
        assertTrue(plain.contains("Backup/Restore"));
    }
}
