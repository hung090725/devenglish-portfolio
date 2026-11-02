package hung.edu.apptienganh.ui.onboarding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import hung.edu.apptienganh.MainActivity;
import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.entity.UserProfile;

public class LevelTestActivity extends AppCompatActivity {

    private CardView cardOpt1, cardOpt2, cardOpt3, cardOpt4;
    private Button btnConfirm;
    private String selectedLevel = "A0"; // Mặc định là A0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_test);

        cardOpt1 = findViewById(R.id.card_opt1);
        cardOpt2 = findViewById(R.id.card_opt2);
        cardOpt3 = findViewById(R.id.card_opt3);
        cardOpt4 = findViewById(R.id.card_opt4);
        btnConfirm = findViewById(R.id.btn_confirm);

        // Đặt mặc định chọn option 1
        selectOption(1);

        cardOpt1.setOnClickListener(v -> selectOption(1));
        cardOpt2.setOnClickListener(v -> selectOption(2));
        cardOpt3.setOnClickListener(v -> selectOption(3));
        cardOpt4.setOnClickListener(v -> selectOption(4));

        btnConfirm.setOnClickListener(v -> {
            // Lưu trạng thái đã hoàn thành onboarding và level đã chọn
            SharedPreferences prefs = getSharedPreferences("englishpath_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("is_first_time", false);
            editor.putString("selected_level", selectedLevel);
            editor.apply();

            // Cập nhật level của người dùng trong Room Database
            AppDatabase db = AppDatabase.getInstance(this);
            AppDatabase.databaseWriteExecutor.execute(() -> {
                UserProfile profile = waitForUserProfile(db, 5);
                if (profile != null) {
                    profile.setCurrentLevel(selectedLevel);
                    db.userProfileDao().update(profile);
                } else {
                    UserProfile defaultProfile = new UserProfile("Học Viên", selectedLevel, 15, 0, 0, 0);
                    db.userProfileDao().insert(defaultProfile);
                }
            });

            Toast.makeText(this, "Thiết lập trình độ " + selectedLevel + " thành công!", Toast.LENGTH_SHORT).show();

            // Chuyển sang MainActivity
            startActivity(new Intent(LevelTestActivity.this, MainActivity.class));
            finish();
        });
    }

    private UserProfile waitForUserProfile(AppDatabase db, int maxRetries) {
        UserProfile profile = db.userProfileDao().getUserProfileSync();
        int retries = 0;
        while (profile == null && retries < maxRetries) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            profile = db.userProfileDao().getUserProfileSync();
            retries++;
        }
        return profile;
    }

    private void selectOption(int opt) {
        // Reset backgrounds
        int unselectedColor = Color.parseColor("#FFFFFF");
        // Kiểm tra xem có đang ở chế độ Dark Mode không để đặt màu phù hợp
        boolean isDarkMode = (getResources().getConfiguration().uiMode & 
                android.content.res.Configuration.UI_MODE_NIGHT_MASK) == 
                android.content.res.Configuration.UI_MODE_NIGHT_YES;
        
        if (isDarkMode) {
            unselectedColor = Color.parseColor("#1E293B"); // surface_dark
        }

        cardOpt1.setCardBackgroundColor(unselectedColor);
        cardOpt2.setCardBackgroundColor(unselectedColor);
        cardOpt3.setCardBackgroundColor(unselectedColor);
        cardOpt4.setCardBackgroundColor(unselectedColor);

        // Highlight selected
        int selectedColor = Color.parseColor("#EEF2FF"); // Indigo 50
        if (isDarkMode) {
            selectedColor = Color.parseColor("#312E81"); // Indigo 900
        }

        switch (opt) {
            case 1:
                cardOpt1.setCardBackgroundColor(selectedColor);
                selectedLevel = "A0";
                break;
            case 2:
                cardOpt2.setCardBackgroundColor(selectedColor);
                selectedLevel = "A1";
                break;
            case 3:
                cardOpt3.setCardBackgroundColor(selectedColor);
                selectedLevel = "B1";
                break;
            case 4:
                cardOpt4.setCardBackgroundColor(selectedColor);
                selectedLevel = "C1";
                break;
        }
    }
}
