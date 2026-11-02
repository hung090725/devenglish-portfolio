package hung.edu.apptienganh.ui.calendar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import hung.edu.apptienganh.R;

public class StudyCalendarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_calendar);
        
        TextView tvStats = findViewById(R.id.tvCalendarStats);
        tvStats.setText("Current Streak: 5 Days\nTotal Minutes: 300\nLessons Completed: 12");
        
        Button btnViewHistory = findViewById(R.id.btnViewHistory);
        btnViewHistory.setOnClickListener(v -> {
            Toast.makeText(this, "Opening detailed history...", Toast.LENGTH_SHORT).show();
        });
    }
}
