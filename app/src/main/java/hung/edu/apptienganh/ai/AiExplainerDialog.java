package hung.edu.apptienganh.ai;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hung.edu.apptienganh.R;

public class AiExplainerDialog {

    public static void show(Context context, String question, List<String> options,
                            String correctAnswer, String userAnswer) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_ai_explainer, null);
        dialog.setContentView(view);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(
                    (int) (context.getResources().getDisplayMetrics().widthPixels * 0.92),
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }

        ImageButton btnClose = view.findViewById(R.id.btnClose);
        LinearLayout layoutLoading = view.findViewById(R.id.layoutExplainerLoading);
        ScrollView scrollContent = view.findViewById(R.id.scrollExplainerContent);
        TextView tvExplainerText = view.findViewById(R.id.tvExplainerText);

        btnClose.setOnClickListener(v -> dialog.dismiss());

        layoutLoading.setVisibility(View.VISIBLE);
        scrollContent.setVisibility(View.GONE);

        AiTutorEngine.getInstance().explainQuestion(context, question, options, correctAnswer, userAnswer, new AiCallback<String>() {
            @Override
            public void onSuccess(String result) {
                layoutLoading.setVisibility(View.GONE);
                scrollContent.setVisibility(View.VISIBLE);
                tvExplainerText.setText(result);
            }

            @Override
            public void onError(String errorMessage) {
                layoutLoading.setVisibility(View.GONE);
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
