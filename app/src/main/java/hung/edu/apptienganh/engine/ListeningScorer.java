package hung.edu.apptienganh.engine;

public class ListeningScorer {
    public int scoreDictation(String expected, String actual) {
        if (expected == null || actual == null) return 0;
        String e = expected.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().trim();
        String a = actual.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().trim();
        return e.equals(a) ? 100 : 0;
    }
}
