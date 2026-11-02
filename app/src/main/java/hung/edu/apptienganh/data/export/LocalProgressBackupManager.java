package hung.edu.apptienganh.data.export;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.dao.DayProgressDao;
import hung.edu.apptienganh.data.local.entity.BetaFeedback;
import hung.edu.apptienganh.data.local.entity.DayProgress;
import hung.edu.apptienganh.data.local.entity.UserProfile;
import hung.edu.apptienganh.data.local.entity.VocabularyReviewProgress;
import hung.edu.apptienganh.domain.model.DaySection;
import hung.edu.apptienganh.domain.model.LevelConstants;

/**
 * LocalProgressBackupManager
 *
 * Tạo và khôi phục bản sao lưu tiến trình học tập dưới dạng JSON.
 */
public class LocalProgressBackupManager {

    public static final int BACKUP_FORMAT_VERSION = 3; // Upgraded to version 3

    private final Context context;
    private final AppDatabase db;

    public LocalProgressBackupManager(Context context) {
        this.context = context.getApplicationContext();
        this.db = AppDatabase.getInstance(this.context);
    }

    /**
     * Tạo JSON backup toàn diện.
     */
    public String exportToJson() {
        try {
            JSONObject root = new JSONObject();
            root.put("version", BACKUP_FORMAT_VERSION);
            
            // Meta data
            try {
                android.content.pm.PackageInfo pInfo = context.getPackageManager()
                        .getPackageInfo(context.getPackageName(), 0);
                root.put("appVersionName", pInfo.versionName);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                    root.put("appVersionCode", pInfo.getLongVersionCode());
                } else {
                    root.put("appVersionCode", pInfo.versionCode);
                }
            } catch (Exception ignored) {}
            
            root.put("databaseVersion", db.getOpenHelper().getReadableDatabase().getVersion());
            root.put("exportedAt", iso8601Now());
            root.put("reminderEnabled", hung.edu.apptienganh.reminder.ReminderPreferences.isReminderEnabled(context));

            // User Profile
            UserProfile profile = db.userProfileDao().getUserProfileSync();
            if (profile != null) {
                JSONObject pObj = new JSONObject();
                pObj.put("name", profile.getName());
                pObj.put("currentLevel", profile.getCurrentLevel());
                pObj.put("streak", profile.getStreak());
                pObj.put("totalLessonsCompleted", profile.getTotalLessonsCompleted());
                root.put("userProfile", pObj);
            }

            // Day Progress
            List<DayProgress> allProgress = db.dayProgressDao().getAllProgressSync(AppDatabase.DEFAULT_USER_ID);
            JSONArray progressArray = new JSONArray();
            if (allProgress != null) {
                for (DayProgress dp : allProgress) {
                    JSONObject item = new JSONObject();
                    item.put("level", dp.getLevel());
                    item.put("dayIndex", dp.getDayIndex());
                    item.put("completed", dp.isCompleted());
                    item.put("unlocked", dp.isUnlocked());
                    item.put("score", dp.getScore());
                    item.put("quizScore", dp.getQuizScore());
                    item.put("speakingScore", dp.getSpeakingScore());
                    item.put("bestQuizScore", dp.getBestQuizScore());
                    item.put("status", dp.getStatus());
                    item.put("currentSectionIndex", dp.getCurrentSectionIndex());
                    progressArray.put(item);
                }
            }
            root.put("dayProgress", progressArray);

            // Vocabulary Review Progress
            List<VocabularyReviewProgress> vocabProgress = db.vocabularyReviewProgressDao().getAllForUserSync(AppDatabase.DEFAULT_USER_ID);
            JSONArray vocabArray = new JSONArray();
            if (vocabProgress != null) {
                for (VocabularyReviewProgress vp : vocabProgress) {
                    JSONObject item = new JSONObject();
                    item.put("vocabularyId", vp.getVocabularyId());
                    item.put("masteryLevel", vp.getMasteryLevel());
                    item.put("easeFactor", vp.getEaseFactor());
                    item.put("intervalDays", vp.getIntervalDays());
                    item.put("reviewCount", vp.getReviewCount());
                    item.put("correctStreak", vp.getCorrectStreak());
                    item.put("wrongCount", vp.getWrongCount());
                    item.put("nextReviewAt", vp.getNextReviewAt());
                    vocabArray.put(item);
                }
            }
            root.put("vocabularyReviewProgress", vocabArray);

            // Summaries
            root.put("quizAttemptSummary", "totalAttempts:" + db.quizAttemptDao().getCountSync(AppDatabase.DEFAULT_USER_ID));
            root.put("speakingResultSummary", "totalAttempts:" + db.speakingResultDao().getCountSync(AppDatabase.DEFAULT_USER_ID));
            root.put("achievementsCount", db.achievementDao().getAllAchievementsSync().size());
            root.put("favoriteVocabsCount", db.favoriteVocabularyDao().getAllSync().size());

            // Beta Feedback
            List<BetaFeedback> feedbacks = db.betaFeedbackDao().getRecentFeedbackSync(100);
            JSONArray feedbackArray = new JSONArray();
            if (feedbacks != null) {
                for (BetaFeedback fb : feedbacks) {
                    JSONObject item = new JSONObject();
                    item.put("category", fb.getCategory());
                    item.put("severity", fb.getSeverity());
                    item.put("description", fb.getDescription());
                    feedbackArray.put(item);
                }
            }
            root.put("betaFeedback", feedbackArray);

            return root.toString(2);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean exportToClipboard() {
        String json = exportToJson();
        if (json == null) return false;
        ClipboardManager clipboard = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard == null) return false;
        ClipData clip = ClipData.newPlainText("EnglishPath Backup", json);
        clipboard.setPrimaryClip(clip);
        return true;
    }

    public static class ImportResult {
        public boolean success;
        public int restoredCount;
        public String errorMessage;

        public ImportResult(boolean success, int restoredCount, String errorMessage) {
            this.success = success;
            this.restoredCount = restoredCount;
            this.errorMessage = errorMessage;
        }
    }

    public ImportResult importFromClipboard() {
        ClipboardManager clipboard = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard == null || !clipboard.hasPrimaryClip()) {
            return new ImportResult(false, 0, "Clipboard trống hoặc không hỗ trợ.");
        }
        ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
        if (item == null) {
            return new ImportResult(false, 0, "Không đọc được dữ liệu từ clipboard.");
        }
        String json = item.getText() != null ? item.getText().toString() : null;
        if (json == null || json.isEmpty()) {
            return new ImportResult(false, 0, "Clipboard không chứa văn bản.");
        }
        return importFromJson(json);
    }

    public BackupPreview previewBackup(String json) {
        try {
            JSONObject root = new JSONObject(json);
            BackupPreview preview = new BackupPreview();
            preview.isValid = true;
            
            if (root.has("version")) {
                int version = root.getInt("version");
                if (version > BACKUP_FORMAT_VERSION) {
                    return new BackupPreview(false, "Phiên bản backup (" + version + ") mới hơn phiên bản hiện tại (" + BACKUP_FORMAT_VERSION + "). Vui lòng cập nhật ứng dụng.");
                }
            } else {
                return new BackupPreview(false, "JSON thiếu trường 'version'. Không đúng định dạng Backup.");
            }

            preview.appVersionName = root.optString("appVersionName", "Unknown");
            preview.appVersionCode = root.optInt("appVersionCode", 0);
            preview.databaseVersion = root.optInt("databaseVersion", 0);
            preview.exportedAt = root.optString("exportedAt", "Unknown");

            if (root.has("dayProgress")) {
                JSONArray arr = root.getJSONArray("dayProgress");
                preview.dayProgressCount = arr.length();
            }
            if (root.has("vocabularyReviewProgress")) {
                JSONArray arr = root.getJSONArray("vocabularyReviewProgress");
                preview.vocabularyProgressCount = arr.length();
            }
            if (root.has("quizAttempts")) {
                JSONArray arr = root.getJSONArray("quizAttempts");
                preview.quizAttemptCount = arr.length();
            }
            if (root.has("speakingResults")) {
                JSONArray arr = root.getJSONArray("speakingResults");
                preview.speakingResultCount = arr.length();
            }
            if (root.has("betaFeedbacks")) {
                JSONArray arr = root.getJSONArray("betaFeedbacks");
                preview.betaFeedbackCount = arr.length();
            }
            return preview;
        } catch (JSONException e) {
            return new BackupPreview(false, "Lỗi cú pháp JSON. File backup không hợp lệ hoặc bị hỏng.");
        }
    }

    public ImportResult importFromJson(String json) {
        if (json == null || json.isEmpty()) {
            return new ImportResult(false, 0, "Dữ liệu trống.");
        }
        try {
            JSONObject root = new JSONObject(json);
            int version = root.optInt("version", 0);
            if (version < 1) {
                return new ImportResult(false, 0, "Định dạng backup không hợp lệ (version = " + version + ").");
            }
            if (version > BACKUP_FORMAT_VERSION) {
                return new ImportResult(false, 0, "Bản backup này được tạo từ phiên bản app mới hơn.");
            }

            int restoredCount = 0;
            long now = System.currentTimeMillis();

            // Import UserProfile
            if (root.has("userProfile")) {
                JSONObject pObj = root.getJSONObject("userProfile");
                UserProfile currentProfile = db.userProfileDao().getUserProfileSync();
                if (currentProfile != null) {
                    currentProfile.setName(pObj.optString("name", currentProfile.getName()));
                    currentProfile.setCurrentLevel(pObj.optString("currentLevel", currentProfile.getCurrentLevel()));
                    currentProfile.setStreak(pObj.optInt("streak", currentProfile.getStreak()));
                    currentProfile.setTotalLessonsCompleted(pObj.optInt("totalLessonsCompleted", currentProfile.getTotalLessonsCompleted()));
                    db.userProfileDao().update(currentProfile);
                } else {
                    UserProfile newProfile = new UserProfile(
                            pObj.optString("name", "Học Viên"),
                            pObj.optString("currentLevel", "A0"),
                            15, 0, pObj.optInt("streak", 0),
                            pObj.optInt("totalLessonsCompleted", 0)
                    );
                    db.userProfileDao().insert(newProfile);
                }
            }

            // Import DayProgress (Check for duplicates and update)
            if (root.has("progress") || root.has("dayProgress")) {
                JSONArray progressArray = root.has("dayProgress") ? root.getJSONArray("dayProgress") : root.getJSONArray("progress");
                for (int i = 0; i < progressArray.length(); i++) {
                    JSONObject obj = progressArray.getJSONObject(i);
                    String level = obj.getString("level");
                    int dayIndex = obj.getInt("dayIndex");
                    
                    DayProgress existing = db.dayProgressDao().getProgressSync(AppDatabase.DEFAULT_USER_ID, level, dayIndex);
                    if (existing != null) {
                        existing.setCompleted(obj.optBoolean("completed", existing.isCompleted()));
                        existing.setUnlocked(obj.optBoolean("unlocked", existing.isUnlocked()));
                        existing.setScore(obj.optInt("score", existing.getScore()));
                        existing.setQuizScore(obj.optInt("quizScore", existing.getQuizScore()));
                        existing.setSpeakingScore(obj.optInt("speakingScore", existing.getSpeakingScore()));
                        existing.setBestQuizScore(obj.optInt("bestQuizScore", existing.getBestQuizScore()));
                        existing.setStatus(obj.optString("status", existing.getStatus()));
                        existing.setCurrentSectionIndex(obj.optInt("currentSectionIndex", existing.getCurrentSectionIndex()));
                        existing.setUpdatedAt(now);
                        db.dayProgressDao().update(existing);
                    } else {
                        DayProgress dp = new DayProgress(
                                AppDatabase.DEFAULT_USER_ID, level, dayIndex,
                                obj.optBoolean("unlocked", false),
                                obj.optBoolean("completed", false),
                                obj.optInt("currentSectionIndex", 0),
                                obj.optInt("score", 0),
                                obj.optInt("speakingScore", 0),
                                obj.optInt("quizScore", 0),
                                obj.optInt("bestQuizScore", 0),
                                obj.optString("status", DaySection.STATUS_LOCKED),
                                0, 0, now);
                        db.dayProgressDao().insert(dp);
                    }
                    restoredCount++;
                }
            }

            // Import VocabularyReviewProgress
            if (root.has("vocabularyReviewProgress")) {
                JSONArray vocabArray = root.getJSONArray("vocabularyReviewProgress");
                for (int i = 0; i < vocabArray.length(); i++) {
                    JSONObject obj = vocabArray.getJSONObject(i);
                    int vocabId = obj.getInt("vocabularyId");

                    VocabularyReviewProgress existing = db.vocabularyReviewProgressDao().getByVocabIdSync(AppDatabase.DEFAULT_USER_ID, vocabId);
                    if (existing != null) {
                        existing.setMasteryLevel(obj.optInt("masteryLevel", existing.getMasteryLevel()));
                        existing.setEaseFactor((float) obj.optDouble("easeFactor", existing.getEaseFactor()));
                        existing.setIntervalDays(obj.optInt("intervalDays", existing.getIntervalDays()));
                        existing.setReviewCount(obj.optInt("reviewCount", existing.getReviewCount()));
                        existing.setCorrectStreak(obj.optInt("correctStreak", existing.getCorrectStreak()));
                        existing.setWrongCount(obj.optInt("wrongCount", existing.getWrongCount()));
                        existing.setNextReviewAt(obj.optLong("nextReviewAt", existing.getNextReviewAt()));
                        existing.setUpdatedAt(now);
                        db.vocabularyReviewProgressDao().update(existing);
                    } else {
                        VocabularyReviewProgress vp = new VocabularyReviewProgress();
                        vp.setUserId(AppDatabase.DEFAULT_USER_ID);
                        vp.setVocabularyId(vocabId);
                        vp.setMasteryLevel(obj.optInt("masteryLevel", 0));
                        vp.setEaseFactor((float) obj.optDouble("easeFactor", 2.5));
                        vp.setIntervalDays(obj.optInt("intervalDays", 0));
                        vp.setReviewCount(obj.optInt("reviewCount", 0));
                        vp.setCorrectStreak(obj.optInt("correctStreak", 0));
                        vp.setWrongCount(obj.optInt("wrongCount", 0));
                        vp.setNextReviewAt(obj.optLong("nextReviewAt", now));
                        vp.setUpdatedAt(now);
                        db.vocabularyReviewProgressDao().insert(vp);
                    }
                    restoredCount++;
                }
            }

            if (restoredCount == 0) {
                return new ImportResult(false, 0, "Không có dữ liệu tiến trình trong bản backup này.");
            }

            return new ImportResult(true, restoredCount, null);
        } catch (JSONException e) {
            return new ImportResult(false, 0, "Lỗi định dạng JSON (có thể bạn copy thiếu ký tự): " + e.getMessage());
        } catch (Exception e) {
            return new ImportResult(false, 0, "Lỗi không xác định khi khôi phục: " + e.getMessage());
        }
    }

    public static class ProgressSummary {
        public int a0Completed;
        public int a1Completed;
        public int a2Completed;
        public int b1Completed;
        public int b2Completed;
        public int c1Completed;
        public int totalCompleted;
        public int totalDays;
    }

    public ProgressSummary getProgressSummary() {
        DayProgressDao dao = db.dayProgressDao();
        ProgressSummary summary = new ProgressSummary();
        summary.a0Completed = dao.getCompletedDayCountSync(AppDatabase.DEFAULT_USER_ID, "A0");
        summary.a1Completed = dao.getCompletedDayCountSync(AppDatabase.DEFAULT_USER_ID, "A1");
        summary.a2Completed = dao.getCompletedDayCountSync(AppDatabase.DEFAULT_USER_ID, "A2");
        summary.b1Completed = dao.getCompletedDayCountSync(AppDatabase.DEFAULT_USER_ID, "B1");
        summary.b2Completed = dao.getCompletedDayCountSync(AppDatabase.DEFAULT_USER_ID, "B2");
        summary.c1Completed = dao.getCompletedDayCountSync(AppDatabase.DEFAULT_USER_ID, "C1");
        summary.totalCompleted = summary.a0Completed + summary.a1Completed
                + summary.a2Completed + summary.b1Completed + summary.b2Completed + summary.c1Completed;
        summary.totalDays = LevelConstants.totalDays();
        return summary;
    }

    private static String iso8601Now() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
                .format(new Date());
    }
}
