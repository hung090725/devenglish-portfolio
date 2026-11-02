package hung.edu.apptienganh.ui.feedback;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.entity.BetaFeedback;

public class BetaFeedbackActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "extra_category";
    public static final String EXTRA_SCREEN_NAME = "extra_screen_name";

    private Spinner spinnerCategory;
    private Spinner spinnerSeverity;
    private TextInputEditText edtScreenName;
    private TextInputEditText edtDescription;
    private TextInputEditText edtSteps;
    private TextInputEditText edtExpected;
    private TextInputEditText edtActual;
    private Button btnSaveFeedback;

    private final String[] categories = {
            "Crash", "UI issue", "TTS issue", "Microphone issue", 
            "Reminder issue", "Lesson content issue", "Quiz issue", 
            "Progress lost", "Other"
    };

    private final String[] severities = {
            "Low", "Medium", "High", "Critical"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beta_feedback);

        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerSeverity = findViewById(R.id.spinner_severity);
        edtScreenName = findViewById(R.id.edt_screen_name);
        edtDescription = findViewById(R.id.edt_description);
        edtSteps = findViewById(R.id.edt_steps);
        edtExpected = findViewById(R.id.edt_expected);
        edtActual = findViewById(R.id.edt_actual);
        btnSaveFeedback = findViewById(R.id.btn_save_feedback);

        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(catAdapter);

        ArrayAdapter<String> sevAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, severities);
        sevAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeverity.setAdapter(sevAdapter);

        if (getIntent().hasExtra(EXTRA_CATEGORY)) {
            String cat = getIntent().getStringExtra(EXTRA_CATEGORY);
            for (int i = 0; i < categories.length; i++) {
                if (categories[i].equals(cat)) {
                    spinnerCategory.setSelection(i);
                    break;
                }
            }
        }

        if (getIntent().hasExtra(EXTRA_SCREEN_NAME)) {
            edtScreenName.setText(getIntent().getStringExtra(EXTRA_SCREEN_NAME));
        }

        btnSaveFeedback.setOnClickListener(v -> saveFeedback());
    }

    private void saveFeedback() {
        String desc = edtDescription.getText() != null ? edtDescription.getText().toString().trim() : "";
        if (desc.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mô tả lỗi", Toast.LENGTH_SHORT).show();
            return;
        }

        String cat = spinnerCategory.getSelectedItem().toString();
        String sev = spinnerSeverity.getSelectedItem().toString();
        String screen = edtScreenName.getText() != null ? edtScreenName.getText().toString().trim() : "";
        String steps = edtSteps.getText() != null ? edtSteps.getText().toString().trim() : "";
        String expected = edtExpected.getText() != null ? edtExpected.getText().toString().trim() : "";
        String actual = edtActual.getText() != null ? edtActual.getText().toString().trim() : "";

        String versionName = "Unknown";
        long versionCode = 0;
        try {
            android.content.pm.PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = pInfo.versionName;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                versionCode = pInfo.getLongVersionCode();
            } else {
                versionCode = pInfo.versionCode;
            }
        } catch (Exception ignored) {}

        BetaFeedback feedback = new BetaFeedback(
                cat, screen, desc, steps, expected, actual, sev,
                android.os.Build.MODEL + " API " + android.os.Build.VERSION.SDK_INT,
                versionName, versionCode, System.currentTimeMillis(), "Open"
        );

        AppDatabase.databaseWriteExecutor.execute(() -> {
            AppDatabase.getInstance(this).betaFeedbackDao().insert(feedback);
            runOnUiThread(() -> {
                Toast.makeText(this, "Đã lưu phản hồi. Xin cảm ơn!", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}
