package hung.edu.apptienganh.ui.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import hung.edu.apptienganh.MainActivity;
import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.ui.onboarding.OnboardingActivity;

import androidx.core.splashscreen.SplashScreen;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Khởi tạo sớm database để seed data chạy trong lúc hiển thị splash
        AppDatabase.getInstance(this);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (isDestroyed() || isFinishing()) return;
            SharedPreferences prefs = getSharedPreferences("englishpath_prefs", MODE_PRIVATE);
            boolean isFirstTime = prefs.getBoolean("is_first_time", true);

            if (isFirstTime) {
                startActivity(new Intent(SplashActivity.this, OnboardingActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
            finish();
        }, 1500);
    }
}
