package hung.edu.apptienganh.ui.achievement;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import hung.edu.apptienganh.R;

public class AchievementActivity extends AppCompatActivity {

    private RecyclerView rvAchievements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        rvAchievements = findViewById(R.id.rvAchievements);

        // TODO: Load achievements from AppDatabase and display in RecyclerView
    }
}
