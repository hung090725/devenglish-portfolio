package hung.edu.apptienganh.qa;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class SoloBuildQaReportBuilderTest {

    @Test
    public void testGenerateReport() throws IOException {
        SoloBuildQaReportBuilder builder = new SoloBuildQaReportBuilder("0.9.3-beta");
        builder.generateReport();

        String userDir = System.getProperty("user.dir");
        File projectDir = new File(userDir).getParentFile();
        if (projectDir == null) {
            projectDir = new File(userDir);
        }

        File reportFile = new File(projectDir, "docs/no_play_release/SOLO_QA_BUILD_REPORT.md");
        assertTrue("Report file should be generated", reportFile.exists());
    }
}
