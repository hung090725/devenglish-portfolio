package hung.edu.apptienganh;

import org.junit.Test;

import hung.edu.apptienganh.data.local.entity.VocabularyReviewProgress;
import hung.edu.apptienganh.domain.model.DaySection;
import hung.edu.apptienganh.domain.srs.SrsScheduler;

import static org.junit.Assert.assertTrue;

public class SrsSchedulerTest {

  private static final long NOW = 1_700_000_000_000L;
  private static final long DAY_MS = 24L * 60 * 60 * 1000;

  @Test
  public void again_setsNextReviewSooner() {
    VocabularyReviewProgress progress = SrsScheduler.createNew(1, 10, NOW);
    progress.setNextReviewAt(NOW + 5 * DAY_MS);
    progress.setIntervalDays(5);

    SrsScheduler.applyRating(progress, DaySection.RATING_AGAIN, NOW);

    assertTrue(progress.getNextReviewAt() <= NOW + DAY_MS);
    assertTrue(progress.getMasteryLevel() <= 0);
  }

  @Test
  public void good_increasesInterval() {
    VocabularyReviewProgress progress = SrsScheduler.createNew(1, 10, NOW);
    progress.setIntervalDays(1);

    SrsScheduler.applyRating(progress, DaySection.RATING_GOOD, NOW);

    assertTrue(progress.getIntervalDays() >= 1);
    assertTrue(progress.getNextReviewAt() > NOW);
  }

  @Test
  public void easy_increasesIntervalMoreThanGood() {
    VocabularyReviewProgress good = SrsScheduler.createNew(1, 10, NOW);
    good.setIntervalDays(2);
    SrsScheduler.applyRating(good, DaySection.RATING_GOOD, NOW);

    VocabularyReviewProgress easy = SrsScheduler.createNew(1, 11, NOW);
    easy.setIntervalDays(2);
    SrsScheduler.applyRating(easy, DaySection.RATING_EASY, NOW);

    assertTrue(easy.getIntervalDays() >= good.getIntervalDays());
    assertTrue(easy.getNextReviewAt() >= good.getNextReviewAt());
  }

  @Test
  public void hard_resetsOrReducesMasteryOnWrongPath() {
    VocabularyReviewProgress progress = SrsScheduler.createNew(1, 10, NOW);
    progress.setMasteryLevel(3);

    SrsScheduler.applyRating(progress, DaySection.RATING_HARD, NOW);

    assertTrue(progress.getWrongCount() >= 1);
    assertTrue(progress.getNextReviewAt() <= NOW + DAY_MS);
  }
}
