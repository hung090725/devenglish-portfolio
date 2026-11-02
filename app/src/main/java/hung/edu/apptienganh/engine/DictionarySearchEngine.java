package hung.edu.apptienganh.engine;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class DictionarySearchEngine {
    public static String removeAccent(String s) {
        if (s == null) return "";
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").toLowerCase();
    }
    
    public boolean isMatch(String query, String target) {
        if (query == null || target == null) return false;
        String q = removeAccent(query);
        String t = removeAccent(target);
        return t.contains(q);
    }
}
