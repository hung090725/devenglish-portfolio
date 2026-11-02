package hung.edu.apptienganh.domain.path;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import hung.edu.apptienganh.data.local.dao.DayProgressDao;
import hung.edu.apptienganh.data.local.dao.LearningDayDao;
import hung.edu.apptienganh.data.local.entity.DayProgress;
import hung.edu.apptienganh.domain.model.DaySection;
import hung.edu.apptienganh.domain.model.LevelConstants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ContinueDayResolverTest {

    @Mock
    private DayProgressDao dayProgressDao;

    @Mock
    private LearningDayDao learningDayDao;

    private final int userId = 1;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void resolve_InProgressA0_ReturnsInProgressDay() {
        DayProgress progress = new DayProgress(userId, "A0", 3, true, false, 0, 0, 0, 0, 0, DaySection.STATUS_IN_PROGRESS, DaySection.VOCABULARY, 0, 0);
        when(dayProgressDao.getInProgressDaySync(userId, "A0")).thenReturn(progress);

        ContinueDayResolver.ContinueTarget target = ContinueDayResolver.resolve(dayProgressDao, learningDayDao, userId);

        assertEquals("A0", target.getLevel());
        assertEquals(3, target.getDayIndex());
        assertEquals(DaySection.VOCABULARY, target.getSectionIndex());
    }

    @Test
    public void resolve_A0NotCompleted_ReturnsA0NextAvailable() {
        when(dayProgressDao.getInProgressDaySync(userId, "A0")).thenReturn(null);
        when(learningDayDao.getDayCountSync("A0")).thenReturn(14);
        
        DayProgress p1 = new DayProgress(userId, "A0", 1, true, true, 100, 100, 100, 100, 100, DaySection.STATUS_COMPLETED, 0, 0, 0);
        DayProgress p2 = new DayProgress(userId, "A0", 2, true, false, 0, 0, 0, 0, 0, DaySection.STATUS_AVAILABLE, 0, 0, 0);

        when(dayProgressDao.getProgressSync(userId, "A0", 1)).thenReturn(p1);
        when(dayProgressDao.getProgressSync(userId, "A0", 2)).thenReturn(p2);

        ContinueDayResolver.ContinueTarget target = ContinueDayResolver.resolve(dayProgressDao, learningDayDao, userId);

        assertEquals("A0", target.getLevel());
        assertEquals(2, target.getDayIndex());
        assertEquals(DaySection.OBJECTIVE, target.getSectionIndex());
    }

    @Test
    public void isA0Completed_True_WhenLastDayCompleted() {
        when(learningDayDao.getDayCountSync("A0")).thenReturn(14);
        DayProgress p14 = new DayProgress(userId, "A0", 14, true, true, 100, 100, 100, 100, 100, DaySection.STATUS_COMPLETED, 0, 0, 0);
        when(dayProgressDao.getProgressSync(userId, "A0", 14)).thenReturn(p14);

        assertTrue(ContinueDayResolver.isA0Completed(dayProgressDao, learningDayDao, userId));
    }

    @Test
    public void resolve_EmptyData_ReturnsDefaultA0() {
        when(dayProgressDao.getInProgressDaySync(userId, "A0")).thenReturn(null);
        when(learningDayDao.getDayCountSync("A0")).thenReturn(0);
        
        ContinueDayResolver.ContinueTarget target = ContinueDayResolver.resolve(dayProgressDao, learningDayDao, userId);

        assertEquals("A0", target.getLevel());
        assertEquals(1, target.getDayIndex());
        assertEquals(DaySection.OBJECTIVE, target.getSectionIndex());
    }
}
