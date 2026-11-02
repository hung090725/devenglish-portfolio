package hung.edu.apptienganh.ui.itenglish;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import hung.edu.apptienganh.R;

/**
 * DEVENGLISH AI - Central Hub for English for IT Students & Developers.
 * Serves as the launchpad for:
 * 1. Code-to-English AI Lab (Priority 1)
 * 2. Technical Documentation Reader (Priority 2)
 * 3. IT English Interview Simulator (Priority 3)
 * 4. Bug Report English Trainer
 * 5. GitHub & Daily Standup Simulator
 */
public class ItEnglishHubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it_english_hub);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        CardView cardCodeToEnglish = findViewById(R.id.cardCodeToEnglish);
        cardCodeToEnglish.setOnClickListener(v ->
                startActivity(new Intent(this, CodeToEnglishActivity.class)));

        CardView cardTechDocReader = findViewById(R.id.cardTechDocReader);
        cardTechDocReader.setOnClickListener(v ->
                startActivity(new Intent(this, TechDocReaderActivity.class)));

        CardView cardInterviewSimulator = findViewById(R.id.cardInterviewSimulator);
        cardInterviewSimulator.setOnClickListener(v ->
                startActivity(new Intent(this, ItInterviewSimulatorActivity.class)));

        CardView cardBugReportTrainer = findViewById(R.id.cardBugReportTrainer);
        cardBugReportTrainer.setOnClickListener(v -> {
            Intent intent = new Intent(this, ItPracticeToolsActivity.class);
            intent.putExtra(ItPracticeToolsActivity.EXTRA_INITIAL_TAB, ItPracticeToolsActivity.TAB_BUG_REPORT);
            startActivity(intent);
        });

        CardView cardGitAndStandup = findViewById(R.id.cardGitAndStandup);
        cardGitAndStandup.setOnClickListener(v -> {
            Intent intent = new Intent(this, ItPracticeToolsActivity.class);
            intent.putExtra(ItPracticeToolsActivity.EXTRA_INITIAL_TAB, ItPracticeToolsActivity.TAB_GIT_COMMIT);
            startActivity(intent);
        });
    }
}
