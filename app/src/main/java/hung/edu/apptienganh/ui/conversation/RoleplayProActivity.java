package hung.edu.apptienganh.ui.conversation;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hung.edu.apptienganh.R;

public class RoleplayProActivity extends AppCompatActivity {

    private TextView tvRoleplayTitle, tvSituation, tvRecognizedText;
    private Spinner spinnerRoleSelect;
    private LinearLayout layoutChatHistory;
    private Button btnMic, btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roleplay_pro);

        tvRoleplayTitle = findViewById(R.id.tvRoleplayTitle);
        tvSituation = findViewById(R.id.tvSituation);
        spinnerRoleSelect = findViewById(R.id.spinnerRoleSelect);
        layoutChatHistory = findViewById(R.id.layoutChatHistory);
        btnMic = findViewById(R.id.btnMic);
        btnSend = findViewById(R.id.btnSend);
        tvRecognizedText = findViewById(R.id.tvRecognizedText);

        String[] roles = {"Role A", "Role B"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roles);
        spinnerRoleSelect.setAdapter(adapter);

        btnMic.setOnClickListener(v -> Toast.makeText(this, "Đang ghi âm...", Toast.LENGTH_SHORT).show());
        btnSend.setOnClickListener(v -> Toast.makeText(this, "Đã gửi câu trả lời.", Toast.LENGTH_SHORT).show());
    }
}
