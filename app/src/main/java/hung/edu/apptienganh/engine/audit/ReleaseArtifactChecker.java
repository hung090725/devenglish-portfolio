package hung.edu.apptienganh.engine.audit;

import java.io.File;

public class ReleaseArtifactChecker {
    private final String buildDirPath;

    public ReleaseArtifactChecker() {
        // Defaults to running from project root or app dir, we pass paths dynamically for testing if needed
        this("app/build/outputs");
    }

    public ReleaseArtifactChecker(String buildDirPath) {
        this.buildDirPath = buildDirPath;
    }

    public ReleaseArtifactReport check() {
        ReleaseArtifactReport report = new ReleaseArtifactReport();
        
        File debugApk = new File(buildDirPath, "apk/debug/app-debug.apk");
        File unsignedReleaseApk = new File(buildDirPath, "apk/release/app-release-unsigned.apk");
        File signedReleaseApk = new File(buildDirPath, "apk/release/app-release.apk");
        File releaseAab = new File(buildDirPath, "bundle/release/app-release.aab");

        if (debugApk.exists()) report.hasDebugApk = true;
        if (unsignedReleaseApk.exists()) report.hasUnsignedReleaseApk = true;
        if (signedReleaseApk.exists()) report.hasSignedReleaseApk = true;
        if (releaseAab.exists()) report.hasReleaseAab = true;

        return report;
    }
}
