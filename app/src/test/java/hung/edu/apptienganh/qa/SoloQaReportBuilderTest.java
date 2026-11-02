package hung.edu.apptienganh.qa;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class SoloQaReportBuilderTest {

    private SoloQaReportBuilder builder;

    @Before
    public void setup() throws Exception {
        Context mockContext = Mockito.mock(Context.class);
        Context mockAppCtx = Mockito.mock(Context.class);
        PackageManager mockPm = Mockito.mock(PackageManager.class);
        PackageInfo pi = new PackageInfo();
        pi.versionName = "0.9.4-beta";
        pi.versionCode = 9;

        when(mockContext.getApplicationContext()).thenReturn(mockAppCtx);
        when(mockAppCtx.getPackageManager()).thenReturn(mockPm);
        when(mockAppCtx.getPackageName()).thenReturn("hung.edu.apptienganh");
        when(mockPm.getPackageInfo(anyString(), anyInt())).thenReturn(pi);

        builder = new SoloQaReportBuilder(mockContext);
    }

    @Test
    public void testEvidenceModeDefaultStatus() {
        String report = builder.buildPlainTextReport();
        assertTrue(report.contains("Session Status: NOT_TESTED"));
        assertTrue(report.contains("Real-device validation is required"));
    }

    @Test
    public void testEvidenceModePassStatus() {
        builder.setRealDeviceTestStats(10, 0, 0, 0);
        String report = builder.buildPlainTextReport();
        assertTrue(report.contains("Session Status: PASS"));
        assertTrue(report.contains("PASS: 10"));
        assertFalse(report.contains("Real-device validation is required"));
    }
}
