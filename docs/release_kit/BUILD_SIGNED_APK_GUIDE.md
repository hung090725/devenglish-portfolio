# HƯỚNG DẪN BUILD SIGNED APK / AAB 

Đây là hướng dẫn từng bước để bạn tạo chữ ký điện tử (Keystore) và xuất ra bản ứng dụng hoàn chỉnh có thể cài đặt được trên bất cứ thiết bị Android nào (Signed Release APK).

## Bước 1: Tạo Keystore bằng Android Studio
1. Mở Android Studio.
2. Trên thanh menu, chọn **Build** -> **Generate Signed Bundle / APK...**
3. Chọn **APK** (nếu muốn chia sẻ trực tiếp) hoặc **Android App Bundle** (nếu muốn tải lên Play Store) -> Bấm **Next**.
4. Dưới mục Key store path, bấm **Create new...**
   - **Key store path:** Bấm icon thư mục và CHỌN LƯU VÀO Ổ D:\ HOẶC USB (Tuyệt đối không lưu vào folder dự án). Ví dụ: `D:\englishpath_keystore\release_key.jks`.
   - **Password / Confirm:** Đặt mật khẩu cho Keystore (Ví dụ: `Abc@12345`).
   - **Alias:** Đặt tên alias, thường là `key0` hoặc `englishpath_alias`.
   - **Password / Confirm (của Alias):** Có thể đặt giống password của Keystore cho dễ nhớ.
   - **Validity (years):** Mặc định 25 năm.
   - **Certificate:** Điền thông tin cơ bản (First and Last name, Organization...).
5. Bấm **OK**.

## Bước 2: Tự động hóa quá trình Build (Tùy chọn)
Để mỗi lần chạy lệnh Gradle đều tự động ký file mà không cần dùng giao diện Android Studio:
1. Tạo một file tên là `signing.properties` nằm CÙNG CẤP với thư mục dự án (C:\Users\Admin\StudioProjects\apptienganh\signing.properties).
2. Copy nội dung từ file `signing.properties.example` sang `signing.properties` và sửa lại với thông tin thật của bạn.
3. Chạy `.\gradlew assembleRelease`. File output sẽ là `app-release.apk` (đã signed).

## Bước 3: Kiểm tra output
Sau khi quá trình Build hoàn tất:
- Truy cập thư mục: `app/build/outputs/apk/release/`
- Nếu cấu hình đúng, bạn sẽ thấy file: `app-release.apk`.
- Nếu cấu hình KHÔNG CÓ keystore, bạn sẽ thấy file: `app-release-unsigned.apk`.

## Bước 4: Cài đặt lên điện thoại
1. Chép file `app-release.apk` vào bộ nhớ điện thoại (qua Cáp USB, Zalo, Google Drive).
2. Dùng trình quản lý File trên điện thoại bấm vào APK để cài.

## Khắc phục sự cố cài đặt (Troubleshooting)
- **"App not installed":** Xóa hẳn bản cũ đã cài trên máy (bản Debug) rồi mới cài bản mới.
- **"Signature mismatch":** Do bản cũ trên máy được ký bằng Keystore A, nhưng bản mới bạn lại ký bằng Keystore B. Phải xóa app cũ (chấp nhận mất dữ liệu) rồi cài lại.
- **VersionCode thấp hơn:** Máy đã cài bản versionCode 14, nhưng file APK mới của bạn chỉ có versionCode 13. Hệ thống sẽ chặn cài đè hạ cấp (downgrade). Phải nâng versionCode trong `build.gradle.kts`.
- **"Unknown sources blocked" / "Bị chặn bởi Play Protect":** Đây là cảnh báo bình thường của Android đối với app không tải từ Google Play. Bạn bấm "More details" -> "Install anyway" (Vẫn cài đặt).

> **CẢNH BÁO TỐI THƯỢNG:** 
> Không bao giờ gửi Keystore (.jks) và Password cho bất kỳ ai. Người có được chúng sẽ có thể làm giả ứng dụng của bạn và tung bản cập nhật độc hại cho người dùng.
