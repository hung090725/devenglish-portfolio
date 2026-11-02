package hung.edu.apptienganh;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import hung.edu.apptienganh.data.local.dao.DayProgressDao;
import hung.edu.apptienganh.data.local.dao.LearningDayDao;
import hung.edu.apptienganh.data.local.entity.DayProgress;
import hung.edu.apptienganh.domain.model.DaySection;
import hung.edu.apptienganh.domain.model.LevelConstants;
import hung.edu.apptienganh.domain.path.ContinueDayResolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ContinueDayResolverTest {

    private DayProgressDao dayProgressDao;
    private LearningDayDao learningDayDao;
    private static final int USER = 1;

    @Before
    public void setUp() {
        dayProgressDao = Mockito.mock(DayProgressDao.class);
        learningDayDao = Mockito.mock(LearningDayDao.class);
        when(learningDayDao.getDayCountSync(LevelConstants.A0)).thenReturn(14);
        when(learningDayDao.getDayCountSync(LevelConstants.A1)).thenReturn(14);
    }

    @Test
    public void prefersInProgressDay() {
        DayProgress inProgress = progress(LevelConstants.A0, 3, DaySection.STATUS_IN_PROGRESS, true, false);
        when(dayProgressDao.getInProgressDaySync(USER, LevelConstants.A0)).thenReturn(inProgress);

        ContinueDayResolver.ContinueTarget target = ContinueDayResolver.resolveForLevel(
                dayProgressDao, learningDayDao, USER, LevelConstants.A0);

        assertEquals(3, target.getDayIndex());
    }

    @Test
    public void usesFirstAvailableWhenNoInProgress() {
        when(dayProgressDao.getInProgressDaySync(USER, LevelConstants.A0)).thenReturn(null);
        when(dayProgressDao.getProgressSync(USER, LevelConstants.A0, 1))
                .thenReturn(progress(LevelConstants.A0, 1, DaySection.STATUS_COMPLETED, true, true));
        when(dayProgressDao.getProgressSync(USER, LevelConstants.A0, 2))
                .thenReturn(progress(LevelConstants.A0, 2, DaySection.STATUS_AVAILABLE, true, false));

        ContinueDayResolver.ContinueTarget target = ContinueDayResolver.resolveForLevel(
                dayProgressDao, learningDayDao, USER, LevelConstants.A0);

        assertEquals(2, target.getDayIndex());
    }

    @Test
    public void a1LockedWhenA0NotCompleted() {
        when(dayProgressDao.getInProgressDaySync(USER, LevelConstants.A0)).thenReturn(null);
        when(dayProgressDao.getProgressSync(USER, LevelConstants.A0, 14))
                .thenReturn(progress(LevelConstants.A0, 14, DaySection.STATUS_AVAILABLE, true, false));
        when(dayProgressDao.getProgressSync(USER, LevelConstants.A1, 1))
                .thenReturn(progress(LevelConstants.A1, 1, DaySection.STATUS_LOCKED, false, false));

        ContinueDayResolver.ContinueTarget target = ContinueDayResolver.resolve(
                dayProgressDao, learningDayDao, USER);

        assertEquals(LevelConstants.A0, target.getLevel());
    }

    @Test
    public void a1UnlockedRequiresFirstDayUnlocked() {
        DayProgress firstA1 = progress(LevelConstants.A1, 1, DaySection.STATUS_AVAILABLE, true, false);
        when(dayProgressDao.getProgressSync(USER, LevelConstants.A1, 1)).thenReturn(firstA1);
        assertTrue(ContinueDayResolver.isA1Unlocked(dayProgressDao, USER));

        firstA1.setUnlocked(false);
        assertFalse(ContinueDayResolver.isA1Unlocked(dayProgressDao, USER));
    }

    private DayProgress progress(String level, int day, String status, boolean unlocked, boolean completed) {
        DayProgress p = new DayProgress(USER, level, day, unlocked, completed,
                0, 0, 0, 0, 0, status, 0, 0, 0);
        if (DaySection.STATUS_IN_PROGRESS.equals(status)) {
            p.setCurrentSectionIndex(2);
        }
        return p;
    }
}
