package hung.edu.apptienganh.ui.qa;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.R;

public class SoloManualChecklistActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "englishpath_solo_checklist";
    private SharedPreferences prefs;

    private RecyclerView rvChecklist;
    private Button btnReset, btnExport;
    private ChecklistAdapter adapter;

    private final String[] DEFAULT_TASKS = {
            "Cài app mới bằng APK", "Mở app lần đầu", "Qua Splash", "Qua Onboarding",
            "Chọn level", "Vào Home", "Mở Lessons", "Mở A0 ngày 1", "Bấm loa TTS",
            "Bấm microphone luyện nói", "Làm quiz", "Mark Completed", "Kiểm tra mở ngày tiếp theo",
            "Kiểm tra A1 bị khóa khi chưa xong A0", "Kiểm tra A2 bị khóa khi chưa xong A1",
            "Kiểm tra B1 bị khóa khi chưa xong A2", "Vào Vocabulary Review", "Thử Again",
            "Thử Hard", "Thử Good", "Thử Easy", "Bật reminder", "Tắt reminder",
            "Mở Backup/Restore", "Export backup JSON", "Import backup JSON hợp lệ",
            "Import JSON sai để kiểm tra báo lỗi", "Export diagnostics", "Gửi feedback nội bộ",
            "Test offline", "Test xoay màn hình nếu app hỗ trợ", "Tắt app mở lại",
            "Gỡ app và cài lại", "Restore backup sau cài lại"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_manual_checklist);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        rvChecklist = findViewById(R.id.rv_checklist);
        btnReset = findViewById(R.id.btn_reset_checklist);
        btnExport = findViewById(R.id.btn_export_checklist);

        rvChecklist.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChecklistAdapter(getTasks());
        rvChecklist.setAdapter(adapter);

        btnReset.setOnClickListener(v -> resetChecklist());
        btnExport.setOnClickListener(v -> exportChecklist());
    }

    private List<TaskItem> getTasks() {
        List<TaskItem> list = new ArrayList<>();
        for (int i = 0; i < DEFAULT_TASKS.length; i++) {
            boolean isChecked = prefs.getBoolean("task_" + i, false);
            list.add(new TaskItem(i, DEFAULT_TASKS[i], isChecked));
        }
        return list;
    }

    private void saveTask(int id, boolean isChecked) {
        prefs.edit().putBoolean("task_" + id, isChecked).apply();
    }

    private void resetChecklist() {
        prefs.edit().clear().apply();
        adapter.setTasks(getTasks());
        Toast.makeText(this, "Checklist reset", Toast.LENGTH_SHORT).show();
    }

    private void exportChecklist() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== MANUAL TEST CHECKLIST ===\\n");
        List<TaskItem> tasks = adapter.getTasks();
        int completed = 0;
        for (TaskItem t : tasks) {
            sb.append(t.isChecked ? "[x] " : "[ ] ").append(t.title).append("\\n");
            if (t.isChecked) completed++;
        }
        sb.append("\\nTotal: ").append(completed).append("/").append(tasks.size()).append(" completed.\\n");

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Manual Test Checklist", sb.toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
    }

    class TaskItem {
        int id;
        String title;
        boolean isChecked;

        TaskItem(int id, String title, boolean isChecked) {
            this.id = id;
            this.title = title;
            this.isChecked = isChecked;
        }
    }

    class ChecklistAdapter extends RecyclerView.Adapter<ChecklistAdapter.ViewHolder> {
        private List<TaskItem> tasks;

        ChecklistAdapter(List<TaskItem> tasks) {
            this.tasks = tasks;
        }

        public void setTasks(List<TaskItem> tasks) {
            this.tasks = tasks;
            notifyDataSetChanged();
        }

        public List<TaskItem> getTasks() {
            return tasks;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_multiple_choice, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            TaskItem task = tasks.get(position);
            holder.checkBox.setText(task.title);
            holder.checkBox.setChecked(task.isChecked);

            holder.checkBox.setOnClickListener(v -> {
                boolean isChecked = !holder.checkBox.isChecked();
                holder.checkBox.setChecked(isChecked);
                task.isChecked = isChecked;
                saveTask(task.id, isChecked);
            });
        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            android.widget.CheckedTextView checkBox;
            ViewHolder(View itemView) {
                super(itemView);
                checkBox = itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
