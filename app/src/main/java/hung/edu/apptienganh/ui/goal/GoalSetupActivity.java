package hung.edu.apptienganh.ui.goal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import hung.edu.apptienganh.R;

public class GoalSetupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_setup);
        
        Button btnSaveGoal = findViewById(R.id.btnSaveGoal);
        btnSaveGoal.setOnClickListener(v -> {
            Toast.makeText(this, "Learning goal saved!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
