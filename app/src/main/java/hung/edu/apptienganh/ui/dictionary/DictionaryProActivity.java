package hung.edu.apptienganh.ui.dictionary;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import hung.edu.apptienganh.R;

public class DictionaryProActivity extends AppCompatActivity {

    private EditText etSearchWord;
    private Spinner spinnerLevelFilter;
    private Button btnSearch;
    private RecyclerView rvDictionaryResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_pro);

        etSearchWord = findViewById(R.id.etSearchWord);
        spinnerLevelFilter = findViewById(R.id.spinnerLevelFilter);
        btnSearch = findViewById(R.id.btnSearch);
        rvDictionaryResults = findViewById(R.id.rvDictionaryResults);

        String[] levels = {"Tất cả", "A0", "A1", "A2", "B1", "B2", "C1"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, levels);
        spinnerLevelFilter.setAdapter(adapter);

        btnSearch.setOnClickListener(v -> {
            String keyword = etSearchWord.getText().toString().trim();
            String level = spinnerLevelFilter.getSelectedItem().toString();
            Toast.makeText(this, "Đang tìm kiếm: " + keyword + " trong " + level, Toast.LENGTH_SHORT).show();
            // TODO: Implement actual DB search and populate RecyclerView
        });
    }
}
