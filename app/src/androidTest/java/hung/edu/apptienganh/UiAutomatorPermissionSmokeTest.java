package hung.edu.apptienganh;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

/**
 * Skeleton UI Automator test.
 * Test này dùng để click các dialog hệ thống (ví dụ Permission Dialog) mà Espresso không với tới được.
 */
@RunWith(AndroidJUnit4.class)
public class UiAutomatorPermissionSmokeTest {

    private static final String BASIC_SAMPLE_PACKAGE = "hung.edu.apptienganh";
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice device;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Khởi tạo UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Bắt đầu từ màn hình chính
        device.pressHome();

        // Chờ launcher
        final String launcherPackage = device.getLauncherPackageName();
        assertNotNull(launcherPackage);
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Khởi chạy app
        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }

        // Chờ app hiển thị
        device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void checkPreconditions() {
        assertNotNull(device);
    }

    /**
     * Helper mẫu để click Allow trên Permission Dialog của hệ thống
     * Lưu ý: Tên nút (Allow, Cho phép, While using the app) phụ thuộc vào ngôn ngữ OS và version Android.
     */
    public void allowPermissionIfNeeded() {
        // Ví dụ: Click nút "While using the app" (Android 10+)
        UiObject2 allowButton = device.findObject(By.text(Pattern.compile("(?i)(While using the app|Cho phép|Allow)")));
        if (allowButton != null) {
            allowButton.click();
        }
    }

    /**
     * Helper mẫu để click Deny trên Permission Dialog
     */
    public void denyPermissionIfNeeded() {
        UiObject2 denyButton = device.findObject(By.text(Pattern.compile("(?i)(Deny|Từ chối)")));
        if (denyButton != null) {
            denyButton.click();
        }
    }

    @Test
    public void smokeTestAppLaunchAndPermissions() {
        // Đây là skeleton. Có thể dùng Espresso kết hợp UiAutomator:
        // 1. Dùng Espresso click vào nút Mở màn hình Luyện Nói (VocabularyDetailActivity)
        // 2. Dùng Espresso click nút Microphone
        // 3. Dùng UiAutomator để nhận diện và click Allow
        // 4. Dùng Espresso để kiểm tra UI hiển thị đang thu âm

        // Tạm thời chỉ kiểm tra app đã launch thành công hay chưa
        UiObject2 appWindow = device.wait(Until.findObject(By.pkg(BASIC_SAMPLE_PACKAGE)), LAUNCH_TIMEOUT);
        assertNotNull("App was not launched correctly", appWindow);
    }
}
