package hung.edu.apptienganh.engine;

public class SpeakingScorer {
    public int scoreSpeaking(String expected, String actual, boolean strictMode) {
        if (expected == null || actual == null) return 0;
        String e = expected.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().trim();
        String a = actual.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().trim();
        if (e.equals(a)) return 100;
        // Basic partial scoring
        return strictMode ? 0 : 50;
    }
}
