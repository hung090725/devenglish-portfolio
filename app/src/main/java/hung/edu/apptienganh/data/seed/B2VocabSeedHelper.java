package hung.edu.apptienganh.data.seed;

import hung.edu.apptienganh.data.local.entity.Vocabulary;
import java.util.ArrayList;
import java.util.List;

public class B2VocabSeedHelper {
    public static List<Vocabulary> getVocabularies() {
        List<Vocabulary> list = new ArrayList<>();
        for (int i = 1; i <= 250; i++) {
            list.add(new Vocabulary("B2_word_" + i, "/b2_ipa_" + i + "/", "/b2_ipa_" + i + "/", "noun", "Nghĩa B2 " + i,
            "Nghĩa ngữ cảnh B2 " + i, "This is B2 example " + i + ".", "Đây là ví dụ B2 " + i + ".", "collocations", "common mistake", "B2", "Workplace", false, 0));
        }
        return list;
    }
}
