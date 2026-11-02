package hung.edu.apptienganh.ai;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.R;

public class AiChatAdapter extends RecyclerView.Adapter<AiChatAdapter.MessageViewHolder> {

    public interface OnSpeakClickListener {
        void onSpeak(String text);
    }

    private final List<AiMessage> messages = new ArrayList<>();
    private final OnSpeakClickListener speakListener;

    public AiChatAdapter(OnSpeakClickListener speakListener) {
        this.speakListener = speakListener;
    }

    public void addMessage(AiMessage message) {
        messages.add(message);
        notifyItemInserted(messages.size() - 1);
    }

    public void setMessages(List<AiMessage> newMessages) {
        messages.clear();
        if (newMessages != null) {
            messages.addAll(newMessages);
        }
        notifyDataSetChanged();
    }

    public List<AiMessage> getMessages() {
        return messages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ai_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        AiMessage message = messages.get(position);

        if (message.isUser()) {
            holder.layoutUserContainer.setVisibility(View.VISIBLE);
            holder.layoutAiContainer.setVisibility(View.GONE);
            holder.tvUserText.setText(message.getText());

            if (message.getFeedbackVi() != null && !message.getFeedbackVi().trim().isEmpty()) {
                holder.cardFeedback.setVisibility(View.VISIBLE);
                holder.tvFeedback.setText(message.getFeedbackVi());
            } else {
                holder.cardFeedback.setVisibility(View.GONE);
            }
        } else {
            holder.layoutAiContainer.setVisibility(View.VISIBLE);
            holder.layoutUserContainer.setVisibility(View.GONE);
            holder.tvAiText.setText(message.getText());

            holder.btnSpeakAi.setOnClickListener(v -> {
                if (speakListener != null) {
                    speakListener.onSpeak(message.getText());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutAiContainer;
        LinearLayout layoutUserContainer;
        TextView tvAiText;
        ImageButton btnSpeakAi;
        TextView tvUserText;
        CardView cardFeedback;
        TextView tvFeedback;

        MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutAiContainer = itemView.findViewById(R.id.layoutAiContainer);
            layoutUserContainer = itemView.findViewById(R.id.layoutUserContainer);
            tvAiText = itemView.findViewById(R.id.tvAiText);
            btnSpeakAi = itemView.findViewById(R.id.btnSpeakAi);
            tvUserText = itemView.findViewById(R.id.tvUserText);
            cardFeedback = itemView.findViewById(R.id.cardFeedback);
            tvFeedback = itemView.findViewById(R.id.tvFeedback);
        }
    }
}
