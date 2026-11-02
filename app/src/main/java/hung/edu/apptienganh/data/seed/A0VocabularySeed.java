package hung.edu.apptienganh.data.seed;

import java.util.List;

import hung.edu.apptienganh.data.local.entity.Vocabulary;

public final class A0VocabularySeed {

    private A0VocabularySeed() {}

    public static List<Vocabulary> buildVocabularyList() {
        return VocabSeedHelper.buildAll();
    }
}
