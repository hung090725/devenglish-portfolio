package hung.edu.apptienganh.reminder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import hung.edu.apptienganh.MainActivity;
import hung.edu.apptienganh.R;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.repository.AppRepository;
import hung.edu.apptienganh.domain.model.LevelConstants;
import hung.edu.apptienganh.domain.path.ContinueDayResolver;

public class DailyStudyReminderWorker extends Worker {

    public static final String CHANNEL_ID = "englishpath_study_reminder";
    private static final int NOTIFICATION_ID = 4101;

    public DailyStudyReminderWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        if (!ReminderPreferences.isReminderEnabled(getApplicationContext())) {
            return Result.success();
        }

        AppRepository repository = new AppRepository(getApplicationContext());
        boolean studiedToday = repository.hasStudiedTodaySync();
        int dueWords = repository.getVocabularyDueCountSync();

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        ContinueDayResolver.ContinueTarget target = ContinueDayResolver.resolve(
                db.dayProgressDao(),
                db.learningDayDao(),
                AppDatabase.DEFAULT_USER_ID);

        int globalDay = LevelConstants.globalDayNumber(target.getLevel(), target.getDayIndex());
        String message;
        if (!studiedToday && dueWords > 0) {
            message = "Đến giờ học tiếng Anh rồi. Hôm nay bạn còn " + dueWords + " từ cần ôn.";
        } else if (!studiedToday) {
            message = "Bạn đang ở ngày " + globalDay + ". Học tiếp 10 phút nhé.";
        } else if (dueWords > 0) {
            message = "Hôm nay bạn còn " + dueWords + " từ cần ôn.";
        } else {
            return Result.success();
        }

        showNotification(message);
        return Result.success();
    }

    private void showNotification(String message) {
        Context context = getApplicationContext();
        createChannel(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_home)
                .setContentTitle("EnglishPath AI")
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        try {
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build());
        } catch (SecurityException ignored) {
            // Permission denied at runtime — do not crash.
        }
    }

    private static void createChannel(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "Nhắc học tiếng Anh",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Nhắc học và ôn từ vựng hàng ngày");
        NotificationManager manager = context.getSystemService(NotificationManager.class);
        if (manager != null) {
            manager.createNotificationChannel(channel);
        }
    }
}
