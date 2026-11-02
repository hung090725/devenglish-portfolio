package hung.edu.apptienganh.ui.quiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.entity.QuizAnswer;

public class QuizWrongAnswerAdapter extends ListAdapter<QuizAnswer, QuizWrongAnswerAdapter.ViewHolder> {

    public interface OnAskAiClickListener {
        void onAskAi(QuizAnswer answer);
    }

    private final OnAskAiClickListener askAiListener;

    public QuizWrongAnswerAdapter() {
        this(null);
    }

    public QuizWrongAnswerAdapter(OnAskAiClickListener askAiListener) {
        super(DIFF_CALLBACK);
        this.askAiListener = askAiListener;
    }

    private static final DiffUtil.ItemCallback<QuizAnswer> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<QuizAnswer>() {
                @Override
                public boolean areItemsTheSame(@NonNull QuizAnswer oldItem, @NonNull QuizAnswer newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull QuizAnswer oldItem, @NonNull QuizAnswer newItem) {
                    return oldItem.getQuestionId() == newItem.getQuestionId()
                            && oldItem.getSelectedAnswer().equals(newItem.getSelectedAnswer());
                }
            };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quiz_wrong_answer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuizAnswer answer = getItem(position);
        holder.txtQuestionLabel.setText("Câu hỏi #" + answer.getQuestionId());
        holder.txtYourAnswer.setText("Bạn chọn: " + emptySafe(answer.getSelectedAnswer()));
        holder.txtCorrectAnswer.setText("Đáp án đúng: " + emptySafe(answer.getCorrectAnswer()));
        holder.txtExplanation.setText(answer.getExplanationVi());

        holder.btnAskAi.setOnClickListener(v -> {
            if (askAiListener != null) {
                askAiListener.onAskAi(answer);
            }
        });
    }

    private static String emptySafe(String s) {
        return s == null || s.isEmpty() ? "(trống)" : s;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtQuestionLabel, txtYourAnswer, txtCorrectAnswer, txtExplanation;
        Button btnAskAi;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestionLabel = itemView.findViewById(R.id.txt_question_label);
            txtYourAnswer = itemView.findViewById(R.id.txt_your_answer);
            txtCorrectAnswer = itemView.findViewById(R.id.txt_correct_answer);
            txtExplanation = itemView.findViewById(R.id.txt_explanation);
            btnAskAi = itemView.findViewById(R.id.btnAskAi);
        }
    }
}
