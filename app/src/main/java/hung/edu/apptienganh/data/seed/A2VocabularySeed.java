package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.Vocabulary;

public final class A2VocabularySeed {

    private A2VocabularySeed() {}

    public static List<Vocabulary> buildVocabularyList() {
        return A2VocabSeedHelper.buildA2VocabularyList();
    }
}
