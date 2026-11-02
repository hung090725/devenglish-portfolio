package hung.edu.apptienganh.ui.lessons;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.entity.DayProgress;
import hung.edu.apptienganh.data.local.entity.LearningDay;
import hung.edu.apptienganh.domain.model.DaySection;

public class LearningDayAdapter extends ListAdapter<LearningDayAdapter.DayItem, LearningDayAdapter.ViewHolder> {

    public interface OnDayClickListener {
        void onDayClick(LearningDay day, boolean unlocked);
    }

    public static final String STATUS_LOCKED = "Locked";
    public static final String STATUS_AVAILABLE = "Available";
    public static final String STATUS_IN_PROGRESS = "In progress";
    public static final String STATUS_COMPLETED = "Completed";

    private final OnDayClickListener listener;

    public LearningDayAdapter(OnDayClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    public void setDays(List<LearningDay> learningDays, List<DayProgress> progressList) {
        Map<Integer, DayProgress> progressMap = new HashMap<>();
        if (progressList != null) {
            for (DayProgress p : progressList) {
                progressMap.put(p.getDayIndex(), p);
            }
        }
        List<DayItem> items = new ArrayList<>();
        if (learningDays != null) {
            for (LearningDay day : learningDays) {
                items.add(new DayItem(day, progressMap.get(day.getDayIndex())));
            }
        }
        submitList(items);
    }

    private static final DiffUtil.ItemCallback<DayItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<DayItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull DayItem oldItem, @NonNull DayItem newItem) {
            return oldItem.day.getDayIndex() == newItem.day.getDayIndex();
        }

        @Override
        public boolean areContentsTheSame(@NonNull DayItem oldItem, @NonNull DayItem newItem) {
            DayProgress op = oldItem.progress;
            DayProgress np = newItem.progress;
            if (op == null && np == null) return true;
            if (op == null || np == null) return false;
            return op.isCompleted() == np.isCompleted()
                    && op.isUnlocked() == np.isUnlocked()
                    && safeStatus(op).equals(safeStatus(np))
                    && op.getCurrentSectionIndex() == np.getCurrentSectionIndex();
        }

        private String safeStatus(DayProgress p) {
            return p.getStatus() != null ? p.getStatus() : "";
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_learning_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DayItem item = getItem(position);
        LearningDay day = item.day;
        DayProgress progress = item.progress;

        boolean unlocked = progress == null ? day.getDayIndex() == 1 : progress.isUnlocked();
        boolean completed = progress != null && progress.isCompleted();
        boolean inProgress = progress != null && DaySection.STATUS_IN_PROGRESS.equals(progress.getStatus());

        String status;
        int statusColor;
        if (completed) {
            status = STATUS_COMPLETED;
            statusColor = Color.parseColor("#10B981");
            holder.imgStatus.setImageResource(android.R.drawable.checkbox_on_background);
        } else if (!unlocked) {
            status = STATUS_LOCKED;
            statusColor = Color.parseColor("#94A3B8");
            holder.imgStatus.setImageResource(android.R.drawable.ic_lock_lock);
        } else if (inProgress) {
            status = STATUS_IN_PROGRESS;
            statusColor = Color.parseColor("#F59E0B");
            holder.imgStatus.setImageResource(android.R.drawable.ic_menu_edit);
        } else {
            status = STATUS_AVAILABLE;
            statusColor = Color.parseColor("#6366F1");
            holder.imgStatus.setImageResource(android.R.drawable.ic_menu_edit);
        }

        holder.txtDayNumber.setText(String.valueOf(
                hung.edu.apptienganh.domain.model.LevelConstants.globalDayNumber(
                        day.getLevel(), day.getDayIndex())));
        holder.txtDayTitle.setText(day.getTitle());
        holder.txtDayObjective.setText(day.getObjectiveVi());
        holder.txtDayStatus.setText(status);
        holder.txtDayStatus.setTextColor(statusColor);

        float alpha = unlocked ? 1f : 0.55f;
        holder.itemView.setAlpha(alpha);
        holder.itemView.setBackgroundColor(inProgress
                ? Color.parseColor("#1AF59E0B") : Color.TRANSPARENT);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDayClick(day, unlocked);
            }
        });
    }

    static class DayItem {
        final LearningDay day;
        final DayProgress progress;

        DayItem(LearningDay day, DayProgress progress) {
            this.day = day;
            this.progress = progress;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDayNumber, txtDayTitle, txtDayObjective, txtDayStatus;
        ImageView imgStatus;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDayNumber = itemView.findViewById(R.id.txt_day_number);
            txtDayTitle = itemView.findViewById(R.id.txt_day_title);
            txtDayObjective = itemView.findViewById(R.id.txt_day_objective);
            txtDayStatus = itemView.findViewById(R.id.txt_day_status);
            imgStatus = itemView.findViewById(R.id.img_day_status);
        }
    }
}
