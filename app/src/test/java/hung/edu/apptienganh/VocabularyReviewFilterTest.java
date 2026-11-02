package hung.edu.apptienganh;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.Vocabulary;
import hung.edu.apptienganh.data.local.entity.VocabularyReviewProgress;
import hung.edu.apptienganh.domain.srs.VocabularyReviewFilter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VocabularyReviewFilterTest {

    private static final long NOW = 1_700_000_000_000L;

    @Test
    public void dueOnly_returnsDueItems() {
        VocabularyReviewProgress due = progress(1, 1, 0, NOW - 1000);
        VocabularyReviewProgress notDue = progress(2, 3, 5, NOW + 100000);

        List<VocabularyReviewProgress> result = VocabularyReviewFilter.apply(
                Arrays.asList(due, notDue),
                VocabularyReviewFilter.FILTER_DUE_ONLY,
                NOW,
                id -> vocab(id, id == 1 ? "A0" : "A1"));

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getVocabularyId());
    }

    @Test
    public void weakWords_filtersLowMasteryOrHighWrong() {
        VocabularyReviewProgress weak = progress(1, 1, 0, NOW + 10000);
        weak.setWrongCount(4);
        VocabularyReviewProgress strong = progress(2, 4, 0, NOW - 1000);

        List<VocabularyReviewProgress> result = VocabularyReviewFilter.apply(
                Arrays.asList(weak, strong),
                VocabularyReviewFilter.FILTER_WEAK,
                NOW,
                id -> vocab(id, "A0"));

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getVocabularyId());
    }

    @Test
    public void byLevelA1() {
        VocabularyReviewProgress a0 = progress(1, 2, 0, NOW);
        VocabularyReviewProgress a1 = progress(2, 2, 0, NOW);

        List<VocabularyReviewProgress> result = VocabularyReviewFilter.apply(
                Arrays.asList(a0, a1),
                VocabularyReviewFilter.FILTER_A1,
                NOW,
                id -> vocab(id, id == 2 ? "A1" : "A0"));

        assertEquals(1, result.size());
        assertTrue(result.get(0).getVocabularyId() == 2);
    }

    private VocabularyReviewProgress progress(int vocabId, int mastery, int wrong, long nextReview) {
        VocabularyReviewProgress p = new VocabularyReviewProgress();
        p.setVocabularyId(vocabId);
        p.setMasteryLevel(mastery);
        p.setWrongCount(wrong);
        p.setNextReviewAt(nextReview);
        return p;
    }

    private Vocabulary vocab(int id, String level) {
        Vocabulary v = new Vocabulary();
        v.setId(id);
        v.setLevel(level);
        v.setMeaningVi("nghĩa");
        return v;
    }
}
