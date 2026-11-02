package hung.edu.apptienganh.ui.dictionary;

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
import hung.edu.apptienganh.data.local.entity.Vocabulary;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.ViewHolder> {

    public interface OnVocabularyClickListener {
        void onVocabularyClick(Vocabulary vocabulary);
        void onSpeakClick(Vocabulary vocabulary);
        void onFavoriteClick(Vocabulary vocabulary);
    }

    private List<Vocabulary> vocabList = new ArrayList<>();
    private final OnVocabularyClickListener listener;

    public VocabularyAdapter(OnVocabularyClickListener listener) {
        this.listener = listener;
    }

    public void setVocabList(List<Vocabulary> vocabList) {
        this.vocabList = vocabList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vocabulary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vocabulary vocabulary = vocabList.get(position);
        holder.bind(vocabulary, listener);
    }

    @Override
    public int getItemCount() {
        return vocabList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtWord, txtType, txtIpa, txtMeaning;
        ImageView btnSpeak, btnFavorite;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txt_vocab_word);
            txtType = itemView.findViewById(R.id.txt_vocab_type);
            txtIpa = itemView.findViewById(R.id.txt_vocab_ipa);
            txtMeaning = itemView.findViewById(R.id.txt_vocab_meaning);
            btnSpeak = itemView.findViewById(R.id.btn_quick_speak);
            btnFavorite = itemView.findViewById(R.id.btn_favorite);
        }

        void bind(final Vocabulary vocabulary, final OnVocabularyClickListener listener) {
            txtWord.setText(vocabulary.getWord());
            txtType.setText("(" + vocabulary.getType() + ")");
            txtIpa.setText(vocabulary.getIpaUk()); // Dùng phiên âm UK làm chính
            txtMeaning.setText(vocabulary.getMeaningVi());

            // Đặt icon ngôi sao dựa theo favorite
            if (vocabulary.isFavorite()) {
                btnFavorite.setImageResource(android.R.drawable.btn_star_big_on);
            } else {
                btnFavorite.setImageResource(android.R.drawable.btn_star_big_off);
            }

            itemView.setOnClickListener(v -> {
                if (listener != null) listener.onVocabularyClick(vocabulary);
            });

            btnSpeak.setOnClickListener(v -> {
                if (listener != null) listener.onSpeakClick(vocabulary);
            });

            btnFavorite.setOnClickListener(v -> {
                if (listener != null) listener.onFavoriteClick(vocabulary);
            });
        }
    }
}
