package hung.edu.apptienganh.ui.lessons;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.entity.Lesson;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    public interface OnLessonClickListener {
        void onLessonClick(Lesson lesson);
    }

    private List<Lesson> lessons = new ArrayList<>();
    private final OnLessonClickListener listener;

    public LessonAdapter(OnLessonClickListener listener) {
        this.listener = listener;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        holder.bind(lesson, listener);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtDesc;
        ImageView imgStatus;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.img_lesson_icon);
            txtTitle = itemView.findViewById(R.id.txt_lesson_title);
            txtDesc = itemView.findViewById(R.id.txt_lesson_desc);
            imgStatus = itemView.findViewById(R.id.img_status);
        }

        void bind(final Lesson lesson, final OnLessonClickListener listener) {
            txtTitle.setText(lesson.getTitle());
            txtDesc.setText(lesson.getDescription());

            // Đặt icon dựa theo loại bài học
            switch (lesson.getCategory()) {
                case "PRONUNCIATION":
                    imgIcon.setImageResource(R.drawable.ic_pronunciation);
                    break;
                case "VOCABULARY":
                    imgIcon.setImageResource(R.drawable.ic_vocabulary);
                    break;
                case "GRAMMAR":
                    imgIcon.setImageResource(R.drawable.ic_grammar);
                    break;
                case "CONVERSATION":
                    imgIcon.setImageResource(R.drawable.ic_conversation);
                    break;
                case "LISTENING":
                    imgIcon.setImageResource(R.drawable.ic_listening);
                    break;
                default:
                    imgIcon.setImageResource(R.drawable.ic_lessons);
                    break;
            }

            // Đặt trạng thái hoàn thành
            if (lesson.isCompleted()) {
                imgStatus.setImageResource(android.R.drawable.checkbox_on_background);
                imgStatus.setImageTintList(ColorStateList.valueOf(Color.parseColor("#10B981"))); // Success Green
            } else {
                imgStatus.setImageResource(android.R.drawable.checkbox_off_background);
                imgStatus.setImageTintList(ColorStateList.valueOf(Color.parseColor("#94A3B8"))); // Slate 400
            }

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onLessonClick(lesson);
                }
            });
        }
    }
}
