package hung.edu.apptienganh.ui.dailyplan;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import hung.edu.apptienganh.R;

public class SmartDailyPlanActivity extends AppCompatActivity {

    private TextView tvDailyProgress;
    private RecyclerView rvDailyTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_daily_plan);

        tvDailyProgress = findViewById(R.id.tvDailyProgress);
        rvDailyTasks = findViewById(R.id.rvDailyTasks);

        // TODO: Load daily tasks based on learning algorithms
    }
}
