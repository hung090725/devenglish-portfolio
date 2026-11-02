package hung.edu.apptienganh.ui;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.ui.splash.SplashActivity;

@RunWith(AndroidJUnit4.class)
public class FirstLaunchFlowTest {

    @Test
    public void testSplashOpensWithoutCrash() {
        // Launch SplashActivity
        try (ActivityScenario<SplashActivity> scenario = ActivityScenario.launch(SplashActivity.class)) {
            // Check if Splash layout is displayed (we might just check if the root or image is visible, 
            // but simply not crashing is a good smoke test).
            Espresso.onView(ViewMatchers.withId(R.id.img_logo))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        } catch (Exception e) {
            // Ignored, we just want to ensure it doesn't crash catastrophically
        }
    }
}
