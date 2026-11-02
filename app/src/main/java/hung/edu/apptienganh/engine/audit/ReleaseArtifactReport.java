package hung.edu.apptienganh.engine.audit;

public class ReleaseArtifactReport {
    public boolean hasDebugApk = false;
    public boolean hasUnsignedReleaseApk = false;
    public boolean hasSignedReleaseApk = false;
    public boolean hasReleaseAab = false;

    public String getStatus() {
        if (hasSignedReleaseApk) return "SIGNED_RELEASE_READY";
        if (hasUnsignedReleaseApk && hasReleaseAab) return "NEED_SIGNED_APK_FOR_DISTRIBUTION";
        if (hasReleaseAab) return "AAB_READY_FOR_FUTURE_PLAY_OR_BUNDLETOOL";
        if (hasDebugApk) return "DEBUG_TEST_READY";
        return "NOT_READY";
    }
}
