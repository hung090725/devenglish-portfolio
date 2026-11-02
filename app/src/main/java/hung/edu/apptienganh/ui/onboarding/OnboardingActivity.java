package hung.edu.apptienganh.ui.onboarding;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.R;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private LinearLayout layoutDots;
    private Button btnAction;
    private List<OnboardingItem> items;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.view_pager);
        layoutDots = findViewById(R.id.layout_dots);
        btnAction = findViewById(R.id.btn_action);

        prepareOnboardingItems();
        setupViewPager();
        setupDots(items.size());
        setCurrentDot(0);

        btnAction.setOnClickListener(v -> {
            int current = viewPager.getCurrentItem();
            if (current < items.size() - 1) {
                viewPager.setCurrentItem(current + 1);
            } else {
                // Trang cuối -> Chuyển sang LevelTestActivity
                startActivity(new Intent(OnboardingActivity.this, LevelTestActivity.class));
                finish();
            }
        });
    }

    private void prepareOnboardingItems() {
        items = new ArrayList<>();
        items.add(new OnboardingItem(
                getString(R.string.onboarding_title_1),
                getString(R.string.onboarding_desc_1),
                R.drawable.ic_lessons
        ));
        items.add(new OnboardingItem(
                getString(R.string.onboarding_title_2),
                getString(R.string.onboarding_desc_2),
                R.drawable.ic_mic
        ));
        items.add(new OnboardingItem(
                getString(R.string.onboarding_title_3),
                getString(R.string.onboarding_desc_3),
                R.drawable.ic_dictionary
        ));
    }

    private void setupViewPager() {
        OnboardingAdapter adapter = new OnboardingAdapter(items);
        viewPager.setAdapter(adapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentDot(position);
                if (position == items.size() - 1) {
                    btnAction.setText(getString(R.string.start_learning));
                } else {
                    btnAction.setText("Tiếp Tục");
                }
            }
        });
    }

    private void setupDots(int size) {
        dots = new ImageView[size];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 0, 8, 0);

        for (int i = 0; i < size; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageResource(android.R.drawable.presence_invisible); // placeholder
            dots[i].setLayoutParams(params);
            layoutDots.addView(dots[i]);
        }
    }

    private void setCurrentDot(int index) {
        for (int i = 0; i < dots.length; i++) {
            ImageView dot = dots[i];
            if (i == index) {
                dot.setImageResource(android.R.drawable.presence_online); // Chấm sáng
                dot.setColorFilter(Color.parseColor("#6366F1")); // Indigo
            } else {
                dot.setImageResource(android.R.drawable.presence_invisible); // Chấm mờ
                dot.setColorFilter(Color.parseColor("#94A3B8")); // Slate 400
            }
        }
    }

    // Model class
    private static class OnboardingItem {
        String title;
        String desc;
        int iconRes;

        OnboardingItem(String title, String desc, int iconRes) {
            this.title = title;
            this.desc = desc;
            this.iconRes = iconRes;
        }
    }

    // RecyclerView Adapter
    private static class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.ViewHolder> {
        private final List<OnboardingItem> items;

        OnboardingAdapter(List<OnboardingItem> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_onboarding, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            OnboardingItem item = items.get(position);
            holder.title.setText(item.title);
            holder.desc.setText(item.desc);
            holder.icon.setImageResource(item.iconRes);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView icon;
            TextView title;
            TextView desc;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                icon = itemView.findViewById(R.id.img_onboarding);
                title = itemView.findViewById(R.id.txt_title);
                desc = itemView.findViewById(R.id.txt_description);
            }
        }
    }
}
