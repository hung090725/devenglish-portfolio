package hung.edu.apptienganh.data.export;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.entity.BetaFeedback;
import hung.edu.apptienganh.data.local.entity.DayProgress;
import hung.edu.apptienganh.data.local.entity.UserProfile;
import hung.edu.apptienganh.data.local.entity.VocabularyReviewProgress;

public class LocalProgressExporter {

    public static String exportProgressJson(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);

        try {
            JSONObject root = new JSONObject();

            // System & App Info
            try {
                android.content.pm.PackageInfo pInfo = context.getPackageManager()
                        .getPackageInfo(context.getPackageName(), 0);
                root.put("appVersionName", pInfo.versionName);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                    root.put("appVersionCode", pInfo.getLongVersionCode());
                } else {
                    root.put("appVersionCode", pInfo.versionCode);
                }
            } catch (Exception ignored) {
            }

            root.put("deviceModel", android.os.Build.MODEL);
            root.put("androidVersion", android.os.Build.VERSION.RELEASE);
            root.put("testTimestamp", System.currentTimeMillis());
            root.put("databaseVersion", db.getOpenHelper().getReadableDatabase().getVersion());
            root.put("reminderEnabled", hung.edu.apptienganh.reminder.ReminderPreferences.isReminderEnabled(context));

            // Export UserProfile
            UserProfile profile = null;
            try {
                profile = db.userProfileDao().getUserProfileSync();
            } catch (Exception ignored) {
            }

            if (profile != null) {
                root.put("currentLevel", profile.getCurrentLevel());
                root.put("streak", profile.getStreak());
                root.put("totalLessonsCompleted", profile.getTotalLessonsCompleted());
            } else {
                root.put("currentLevel", "unknown");
            }

            // Progress A0 / A1
            int a0Completed = db.dayProgressDao().getCompletedDayCountSync(AppDatabase.DEFAULT_USER_ID,
                    hung.edu.apptienganh.domain.model.LevelConstants.A0);
            int a1Completed = db.dayProgressDao().getCompletedDayCountSync(AppDatabase.DEFAULT_USER_ID,
                    hung.edu.apptienganh.domain.model.LevelConstants.A1);
            root.put("A0_progress", a0Completed + "/14");
            root.put("A1_progress", a1Completed + "/14");

            // Due words
            long now = System.currentTimeMillis();
            int dueWordsCount = db.vocabularyReviewProgressDao().getDueCountSync(AppDatabase.DEFAULT_USER_ID, now);
            root.put("dueWordsCount", dueWordsCount);

            // Stats summary
            List<VocabularyReviewProgress> vocabProgressList = db.vocabularyReviewProgressDao()
                    .getAllForUserSync(AppDatabase.DEFAULT_USER_ID);
            root.put("vocabularyReviewStats", "Total reviews: " + vocabProgressList.size());

            root.put("quizAttemptCount", db.quizAttemptDao().getCountSync(AppDatabase.DEFAULT_USER_ID));
            root.put("speakingAttemptCount", db.speakingResultDao().getCountSync(AppDatabase.DEFAULT_USER_ID));

            // Beta Feedback
            List<BetaFeedback> recentFeedbacks = db.betaFeedbackDao().getRecentFeedbackSync(5);
            if (recentFeedbacks != null && !recentFeedbacks.isEmpty()) {
                JSONArray feedbackArr = new JSONArray();
                for (BetaFeedback fb : recentFeedbacks) {
                    JSONObject fObj = new JSONObject();
                    fObj.put("id", fb.getId());
                    fObj.put("category", fb.getCategory());
                    fObj.put("severity", fb.getSeverity());
                    fObj.put("screenName", fb.getScreenName());
                    feedbackArr.put(fObj);
                }
                root.put("recentBetaFeedbacks", feedbackArr);
            }

            // Crash marker
            android.content.SharedPreferences prefs = context.getSharedPreferences("englishpath_prefs",
                    Context.MODE_PRIVATE);
            boolean lastCrash = prefs.getBoolean("last_crash_detected", false);
            root.put("lastCrashMarker", lastCrash);

            return root.toString(4); // Indent with 4 spaces
        } catch (Exception e) {
            e.printStackTrace();
            return "{ \"error\": \"Failed to export progress\" }";
        }
    }
}
