package hung.edu.apptienganh.ui.dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.entity.Vocabulary;
import hung.edu.apptienganh.data.repository.AppRepository;
import hung.edu.apptienganh.speech.TtsManager;
import hung.edu.apptienganh.ui.vocabulary.VocabularyDetailActivity;

public class DictionaryFragment extends Fragment implements VocabularyAdapter.OnVocabularyClickListener {

    private EditText edtSearch;
    private RecyclerView rvVocabList;
    private TextView txtNoResult;
    
    private AppRepository repository;
    private VocabularyAdapter adapter;
    private TtsManager ttsManager;
    private LiveData<List<Vocabulary>> currentVocabLiveData;
    private final Observer<List<Vocabulary>> vocabObserver = vocabularies -> {
        if (vocabularies != null && !vocabularies.isEmpty()) {
            rvVocabList.setVisibility(View.VISIBLE);
            txtNoResult.setVisibility(View.GONE);
            adapter.setVocabList(vocabularies);
        } else {
            rvVocabList.setVisibility(View.GONE);
            txtNoResult.setVisibility(View.VISIBLE);
            adapter.setVocabList(new ArrayList<>());
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);

        edtSearch = view.findViewById(R.id.edt_search);
        rvVocabList = view.findViewById(R.id.rv_vocab_list);
        txtNoResult = view.findViewById(R.id.txt_no_result);

        repository = new AppRepository(requireContext());
        ttsManager = new TtsManager(requireContext());

        setupRecyclerView();
        setupSearch();

        // Ban đầu tải toàn bộ từ vựng
        loadVocabulary("");

        return view;
    }

    private void setupRecyclerView() {
        rvVocabList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new VocabularyAdapter(this);
        rvVocabList.setAdapter(adapter);
    }

    private void setupSearch() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadVocabulary(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void loadVocabulary(String query) {
        if (currentVocabLiveData != null) {
            currentVocabLiveData.removeObserver(vocabObserver);
        }

        if (query.isEmpty()) {
            currentVocabLiveData = repository.getAllVocabulary();
        } else {
            currentVocabLiveData = repository.searchVocabulary(query);
        }
        currentVocabLiveData.observe(getViewLifecycleOwner(), vocabObserver);
    }

    @Override
    public void onVocabularyClick(Vocabulary vocabulary) {
        Intent intent = new Intent(getActivity(), VocabularyDetailActivity.class);
        intent.putExtra("vocab_word", vocabulary.getWord());
        startActivity(intent);
    }

    @Override
    public void onSpeakClick(Vocabulary vocabulary) {
        if (ttsManager != null) {
            ttsManager.speakEnglishUs(vocabulary.getWord());
        }
    }

    @Override
    public void onFavoriteClick(Vocabulary vocabulary) {
        boolean newFavorite = !vocabulary.isFavorite();
        repository.updateVocabularyFavorite(vocabulary.getId(), newFavorite);
        Toast.makeText(getContext(), newFavorite ? "Đã thêm vào danh sách ôn tập" : "Đã xóa khỏi danh sách ôn tập", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (ttsManager != null) {
            ttsManager.shutdown();
            ttsManager = null;
        }
    }
}
