package hung.edu.apptienganh.domain.srs;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.Vocabulary;
import hung.edu.apptienganh.data.local.entity.VocabularyReviewProgress;

import static org.junit.Assert.assertEquals;

public class VocabularyReviewFilterTest {

    @Test
    public void filter_ByDueOnly_ReturnsDueItems() {
        long now = 100000;
        
        VocabularyReviewProgress p1 = new VocabularyReviewProgress();
        p1.setUserId(1);
        p1.setVocabularyId(1);
        p1.setNextReviewAt(now - 1000);
        p1.setMasteryLevel(4); // Mastered, but past due
        
        VocabularyReviewProgress p2 = new VocabularyReviewProgress();
        p2.setUserId(1);
        p2.setVocabularyId(2);
        p2.setNextReviewAt(now + 10000);
        p2.setMasteryLevel(4); // Mastered and not due
        
        VocabularyReviewProgress p3 = new VocabularyReviewProgress();
        p3.setUserId(1);
        p3.setVocabularyId(3);
        p3.setMasteryLevel(1); // Not mastered yet, so considered due regardless of nextReviewAt

        VocabularyReviewFilter.VocabularyLookup lookup = id -> {
            Vocabulary v = new Vocabulary();
            v.setId(id);
            v.setLevel("A0");
            return v;
        };

        List<VocabularyReviewProgress> result = VocabularyReviewFilter.apply(
                Arrays.asList(p1, p2, p3),
                VocabularyReviewFilter.FILTER_DUE_ONLY,
                now,
                lookup
        );

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getVocabularyId());
        assertEquals(3, result.get(1).getVocabularyId());
    }

    @Test
    public void filter_ByLevel_ReturnsOnlyMatchingLevel() {
        VocabularyReviewProgress p1 = new VocabularyReviewProgress();
        p1.setUserId(1);
        p1.setVocabularyId(1);
        
        VocabularyReviewProgress p2 = new VocabularyReviewProgress();
        p2.setUserId(1);
        p2.setVocabularyId(2);

        VocabularyReviewFilter.VocabularyLookup lookup = id -> {
            Vocabulary v = new Vocabulary();
            v.setId(id);
            v.setLevel(id == 1 ? "A0" : "A1");
            return v;
        };

        List<VocabularyReviewProgress> result = VocabularyReviewFilter.apply(
                Arrays.asList(p1, p2),
                VocabularyReviewFilter.FILTER_A0,
                0,
                lookup
        );

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getVocabularyId());
    }

    @Test
    public void filter_EmptyList_ReturnsEmptyList() {
        VocabularyReviewFilter.VocabularyLookup lookup = id -> null;
        List<VocabularyReviewProgress> result = VocabularyReviewFilter.apply(
                java.util.Collections.emptyList(),
                VocabularyReviewFilter.FILTER_DUE_ONLY,
                0,
                lookup
        );
        assertEquals(0, result.size());
        
        List<VocabularyReviewProgress> resultNull = VocabularyReviewFilter.apply(
                null,
                VocabularyReviewFilter.FILTER_ALL,
                0,
                lookup
        );
        assertEquals(0, resultNull.size());
    }
}
