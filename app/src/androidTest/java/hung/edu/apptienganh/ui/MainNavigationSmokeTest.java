package hung.edu.apptienganh.ui;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.MainActivity;

@RunWith(AndroidJUnit4.class)
public class MainNavigationSmokeTest {

    @Test
    public void testBottomNavigation() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            // Check Home
            Espresso.onView(ViewMatchers.withId(R.id.navigation_home))
                    .perform(ViewActions.click());
            Espresso.onView(ViewMatchers.withId(R.id.txt_welcome))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

            // Check Lessons
            Espresso.onView(ViewMatchers.withId(R.id.navigation_lessons))
                    .perform(ViewActions.click());
            Espresso.onView(ViewMatchers.withId(R.id.txt_lessons_title))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
            Espresso.onView(ViewMatchers.withId(R.id.chip_a2))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

            // Check Profile
            Espresso.onView(ViewMatchers.withId(R.id.navigation_profile))
                    .perform(ViewActions.click());
            Espresso.onView(ViewMatchers.withId(R.id.txt_user_name))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        }
    }
}
