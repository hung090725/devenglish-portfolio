package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.Vocabulary;

public final class B1VocabularySeed {

    private B1VocabularySeed() {}

    public static List<Vocabulary> buildVocabularyList() {
        List<Vocabulary> list = B1VocabSeedHelper.buildB1VocabularyList();
        list.addAll(B1VocabSeedHelper2.buildB1VocabularyList());
        return list;
    }
}
