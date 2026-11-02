package hung.edu.apptienganh.ui.game;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import hung.edu.apptienganh.R;

public class WordGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_game);
        
        Button btnSubmit = findViewById(R.id.btnSubmitAnswer);
        btnSubmit.setOnClickListener(v -> {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
