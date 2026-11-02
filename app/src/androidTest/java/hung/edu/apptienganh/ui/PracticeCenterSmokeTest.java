package hung.edu.apptienganh.ui;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hung.edu.apptienganh.MainActivity;
import hung.edu.apptienganh.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class PracticeCenterSmokeTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testPracticeCenterRoutingCompile() {
        // Just verify compile works for all button IDs in PracticeCenter
        onView(withId(R.id.navigation_practice)).perform(click());
        
        onView(withId(R.id.btnReviewCenter)).check(matches(isDisplayed()));
        onView(withId(R.id.btnSpeaking)).check(matches(isDisplayed()));
        onView(withId(R.id.btnDictionary)).check(matches(isDisplayed()));
        onView(withId(R.id.btnExamSimulator)).check(matches(isDisplayed()));
        onView(withId(R.id.btnSettings)).check(matches(isDisplayed()));
    }
}
