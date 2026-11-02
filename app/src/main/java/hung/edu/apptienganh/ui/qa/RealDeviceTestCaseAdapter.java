package hung.edu.apptienganh.ui.qa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.entity.RealDeviceTestCase;

public class RealDeviceTestCaseAdapter extends RecyclerView.Adapter<RealDeviceTestCaseAdapter.ViewHolder> {

    private List<RealDeviceTestCase> items = new ArrayList<>();
    private OnItemStatusChangedListener listener;

    public interface OnItemStatusChangedListener {
        void onStatusChanged(RealDeviceTestCase item);
    }

    public void setListener(OnItemStatusChangedListener listener) {
        this.listener = listener;
    }

    public void setItems(List<RealDeviceTestCase> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_real_device_test_case, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RealDeviceTestCase item = items.get(position);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        Button btnNotTested, btnPass, btnFail, btnBlocked;
        EditText edtNote;

        ViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.txt_test_title);
            btnNotTested = view.findViewById(R.id.btn_status_not_tested);
            btnPass = view.findViewById(R.id.btn_status_pass);
            btnFail = view.findViewById(R.id.btn_status_fail);
            btnBlocked = view.findViewById(R.id.btn_status_blocked);
            edtNote = view.findViewById(R.id.edt_test_note);
        }

        void bind(RealDeviceTestCase item, OnItemStatusChangedListener listener) {
            txtTitle.setText(item.getOrderIndex() + ". " + item.getTitle());
            edtNote.setText(item.getNote());

            // Reset alpha
            btnNotTested.setAlpha(item.getStatus().equals("NOT_TESTED") ? 1.0f : 0.3f);
            btnPass.setAlpha(item.getStatus().equals("PASS") ? 1.0f : 0.3f);
            btnFail.setAlpha(item.getStatus().equals("FAIL") ? 1.0f : 0.3f);
            btnBlocked.setAlpha(item.getStatus().equals("BLOCKED") ? 1.0f : 0.3f);

            View.OnClickListener clickListener = v -> {
                if (v == btnNotTested) item.setStatus("NOT_TESTED");
                else if (v == btnPass) item.setStatus("PASS");
                else if (v == btnFail) item.setStatus("FAIL");
                else if (v == btnBlocked) item.setStatus("BLOCKED");

                item.setNote(edtNote.getText().toString());
                item.setUpdatedAt(System.currentTimeMillis());

                if (listener != null) {
                    listener.onStatusChanged(item);
                }
            };

            btnNotTested.setOnClickListener(clickListener);
            btnPass.setOnClickListener(clickListener);
            btnFail.setOnClickListener(clickListener);
            btnBlocked.setOnClickListener(clickListener);

            edtNote.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    String newNote = edtNote.getText().toString();
                    if (!newNote.equals(item.getNote())) {
                        item.setNote(newNote);
                        item.setUpdatedAt(System.currentTimeMillis());
                        if (listener != null) {
                            listener.onStatusChanged(item);
                        }
                    }
                }
            });
        }
    }
}
