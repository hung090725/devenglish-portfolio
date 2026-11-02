package hung.edu.apptienganh.data.seed;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.Vocabulary;

public final class A1VocabularySeed {

    private A1VocabularySeed() {}

    public static List<Vocabulary> buildVocabularyList() {
        return A1VocabSeedHelper.buildAll();
    }
}
