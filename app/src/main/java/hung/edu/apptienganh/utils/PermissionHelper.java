package hung.edu.apptienganh.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionHelper {

    public interface PermissionCallback {
        void onGranted();
        void onDenied();
    }

    public static boolean hasPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermissionWithExplanation(
            Activity activity,
            String permission,
            int requestCode,
            String explanationTitle,
            String explanationMessage,
            PermissionCallback callback) {
            
        if (hasPermission(activity, permission)) {
            if (callback != null) callback.onGranted();
            return;
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            new AlertDialog.Builder(activity)
                    .setTitle(explanationTitle)
                    .setMessage(explanationMessage)
                    .setPositiveButton("Cấp quyền", (dialog, which) -> {
                        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
                    })
                    .setNegativeButton("Từ chối", (dialog, which) -> {
                        if (callback != null) callback.onDenied();
                    })
                    .setCancelable(false)
                    .show();
        } else {
            // Lần đầu xin quyền hoặc người dùng đã chọn "Don't ask again"
            // Android không cho biết chính xác, nên ta mặc định xin quyền.
            // Activity sẽ xử lý onRequestPermissionsResult để hiện openSettings nếu cần.
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
        }
    }

    public static void showSettingsDialog(Activity activity, String title, String message, PermissionCallback callback) {
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Mở Cài đặt", (dialog, which) -> openSettings(activity))
                .setNegativeButton("Hủy", (dialog, which) -> {
                    if (callback != null) callback.onDenied();
                })
                .setCancelable(false)
                .show();
    }

    public static void openSettings(Context context) {
        android.content.Intent intent = new android.content.Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        android.net.Uri uri = android.net.Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static boolean hasMicrophonePermission(Context context) {
        return hasPermission(context, android.Manifest.permission.RECORD_AUDIO);
    }

    public static boolean hasNotificationPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return hasPermission(context, android.Manifest.permission.POST_NOTIFICATIONS);
        }
        return true;
    }
}
