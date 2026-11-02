package hung.edu.apptienganh;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.reminder.ReminderPreferences;
import hung.edu.apptienganh.reminder.ReminderScheduler;
import hung.edu.apptienganh.ui.dictionary.DictionaryFragment;
import hung.edu.apptienganh.ui.home.HomeFragment;
import hung.edu.apptienganh.ui.lessons.LessonsFragment;
import hung.edu.apptienganh.ui.practice.PracticeFragment;
import hung.edu.apptienganh.ui.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase.getInstance(this);
        if (ReminderPreferences.isReminderEnabled(this)) {
            ReminderScheduler.applyReminderSettings(this);
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Load HomeFragment làm mặc định
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        navView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                fragment = new HomeFragment();
            } else if (itemId == R.id.navigation_lessons) {
                fragment = new LessonsFragment();
            } else if (itemId == R.id.navigation_practice) {
                fragment = new PracticeFragment();
            } else if (itemId == R.id.navigation_dictionary) {
                fragment = new DictionaryFragment();
            } else if (itemId == R.id.navigation_profile) {
                fragment = new ProfileFragment();
            }

            if (fragment != null) {
                loadFragment(fragment);
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.commit();
    }
}