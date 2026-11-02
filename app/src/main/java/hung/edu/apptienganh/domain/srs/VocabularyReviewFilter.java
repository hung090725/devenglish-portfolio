package hung.edu.apptienganh.domain.srs;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.Vocabulary;
import hung.edu.apptienganh.data.local.entity.VocabularyReviewProgress;

public final class VocabularyReviewFilter {

    public static final String FILTER_ALL = "ALL";
    public static final String FILTER_A0 = "A0";
    public static final String FILTER_A1 = "A1";
    public static final String FILTER_A2 = "A2";
    public static final String FILTER_B1 = "B1";
    public static final String FILTER_DUE_ONLY = "DUE_ONLY";
    public static final String FILTER_WEAK = "WEAK";

    private static final int WEAK_MASTERY_THRESHOLD = 2;
    private static final int WEAK_WRONG_THRESHOLD = 3;

    private VocabularyReviewFilter() {}

    public static List<VocabularyReviewProgress> apply(
            List<VocabularyReviewProgress> source,
            String filter,
            long now,
            VocabularyLookup lookup) {

        if (source == null || source.isEmpty()) {
            return new ArrayList<>();
        }

        List<VocabularyReviewProgress> result = new ArrayList<>();
        for (VocabularyReviewProgress progress : source) {
            Vocabulary vocab = lookup.getVocabulary(progress.getVocabularyId());
            if (vocab == null) {
                continue;
            }
            if (matches(filter, progress, vocab, now)) {
                result.add(progress);
            }
        }
        return result;
    }

    private static boolean matches(
            String filter,
            VocabularyReviewProgress progress,
            Vocabulary vocab,
            long now) {

        if (FILTER_ALL.equals(filter)) {
            return true;
        }
        if (FILTER_A0.equals(filter)) {
            return "A0".equals(vocab.getLevel());
        }
        if (FILTER_A1.equals(filter)) {
            return "A1".equals(vocab.getLevel());
        }
        if (FILTER_A2.equals(filter)) {
            return "A2".equals(vocab.getLevel());
        }
        if (FILTER_B1.equals(filter)) {
            return "B1".equals(vocab.getLevel());
        }
        if (FILTER_DUE_ONLY.equals(filter)) {
            return progress.getMasteryLevel() < 3 || progress.getNextReviewAt() <= now;
        }
        if (FILTER_WEAK.equals(filter)) {
            return progress.getMasteryLevel() <= WEAK_MASTERY_THRESHOLD
                    || progress.getWrongCount() >= WEAK_WRONG_THRESHOLD;
        }
        return true;
    }

    public interface VocabularyLookup {
        Vocabulary getVocabulary(int vocabularyId);
    }
}
