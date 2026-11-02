package hung.edu.apptienganh.engine.audit.content.review;

import org.junit.Test;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.Assert.*;

public class ContentReviewExporterTest {

    @Test
    public void testExportEmptyList() {
        ContentReviewExporter exporter = new ContentReviewExporter();
        ContentReviewExportResult result = exporter.exportToMarkdown(Collections.emptyList(), "build/tmp");
        assertTrue(result.isSuccess());
        assertEquals(0, result.getTotalItemsExported());
    }

    @Test
    public void testExportMarkdown() throws Exception {
        ContentReviewExporter exporter = new ContentReviewExporter();
        ContentReviewItem item = new ContentReviewItem("1", "Vocab", "A1", "v1", "word", "aple", "spelling", "fix", "HIGH", "NEEDS_REVIEW");
        
        File tempDir = Files.createTempDirectory("export_test").toFile();
        ContentReviewExportResult result = exporter.exportToMarkdown(Arrays.asList(item), tempDir.getAbsolutePath());
        
        assertTrue(result.isSuccess());
        assertEquals(1, result.getTotalItemsExported());
        assertNotNull(result.getExportPath());
        assertTrue(new File(result.getExportPath()).exists());
    }
}
