package hung.edu.apptienganh.data.seed;

import hung.edu.apptienganh.data.local.entity.Vocabulary;
import java.util.ArrayList;
import java.util.List;

public class C1VocabSeedHelper {
    public static List<Vocabulary> getVocabularies() {
        List<Vocabulary> list = new ArrayList<>();
        for (int i = 1; i <= 250; i++) {
            list.add(new Vocabulary("C1_word_" + i, "/c1_ipa_" + i + "/", "/c1_ipa_" + i + "/", "noun", "Nghĩa C1 " + i,
            "Nghĩa ngữ cảnh C1 " + i, "This is C1 example " + i + ".", "Đây là ví dụ C1 " + i + ".", "collocations", "common mistake", "C1", "Abstract ideas", false, 0));
        }
        return list;
    }
}
