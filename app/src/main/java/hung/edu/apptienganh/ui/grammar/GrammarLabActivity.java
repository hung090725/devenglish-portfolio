package hung.edu.apptienganh.ui.grammar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import hung.edu.apptienganh.R;

public class GrammarLabActivity extends AppCompatActivity {

    private EditText etSearchGrammar;
    private Spinner spinnerLevelFilter;
    private Button btnPracticeMode;
    private RecyclerView rvGrammarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_lab);

        etSearchGrammar = findViewById(R.id.etSearchGrammar);
        spinnerLevelFilter = findViewById(R.id.spinnerLevelFilter);
        btnPracticeMode = findViewById(R.id.btnPracticeMode);
        rvGrammarList = findViewById(R.id.rvGrammarList);

        String[] levels = {"Tất cả", "A0", "A1", "A2", "B1", "B2", "C1"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, levels);
        spinnerLevelFilter.setAdapter(adapter);

        btnPracticeMode.setOnClickListener(v -> {
            Toast.makeText(this, "Đang vào Practice Mode...", Toast.LENGTH_SHORT).show();
            // TODO: Start practice mode
        });
    }
}
