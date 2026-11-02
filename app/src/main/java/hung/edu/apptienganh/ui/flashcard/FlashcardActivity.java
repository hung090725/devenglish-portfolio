package hung.edu.apptienganh.ui.flashcard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import hung.edu.apptienganh.R;

public class FlashcardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        
        TextView tvCardBack = findViewById(R.id.tvCardBack);
        Button btnFlipCard = findViewById(R.id.btnFlipCard);
        
        btnFlipCard.setOnClickListener(v -> {
            tvCardBack.setVisibility(View.VISIBLE);
        });
    }
}
