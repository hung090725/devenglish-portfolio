# Debug vs Release Behavior

Tài liệu này định nghĩa sự khác biệt về hành vi của app `EnglishPath AI` giữa môi trường Debug (dùng cho Developer/Tester) và môi trường Release (dùng cho người dùng cuối).

## Môi Trường Debug
Được kích hoạt khi build app qua Android Studio (Run) hoặc chạy `:app:assembleDebug`.

- **App Diagnostics:** Trong màn hình **Profile**, xuất hiện nút "App Diagnostics (Tester)".
- **Tính năng của Diagnostics:** Bấm vào nút này sẽ hiển thị một Dialog chứa chuỗi JSON báo cáo trạng thái người dùng (Level, Streak, số từ đến hạn, số quiz đã làm). Dễ dàng copy nội dung để gửi kèm báo cáo lỗi.
- **Logging:** `Log.d` và `Log.e` được phép in ra console (Logcat) chi tiết để hỗ trợ sửa lỗi.
- **Database:** Có thể dùng công cụ App Inspection của Android Studio để xem trực tiếp database SQLite/Room.

## Môi Trường Release
Được kích hoạt khi build APK/AAB qua lệnh `:app:assembleRelease` hoặc generate Signed Bundle.

- **App Diagnostics:** Nút "App Diagnostics (Tester)" bị **ẨN HOÀN TOÀN**. Người dùng cuối không thể xem hoặc xuất chuỗi JSON này để tránh rò rỉ hoặc gây bối rối.
- **Logging:** Hạn chế log nhạy cảm.
- **ProGuard / R8:** Code được thu gọn và làm mờ (nếu cấu hình `isMinifyEnabled = true`), gây khó khăn cho việc dịch ngược.

## Làm Thế Nào Để Tester Lấy Diagnostics?
1. Tester bắt buộc phải cài đặt bản build có đuôi `-debug.apk` (hoặc build trực tiếp từ Android Studio).
2. Vào **Profile** -> Cuộn xuống dưới cùng -> Bấm **App Diagnostics**.
3. Chụp ảnh màn hình JSON hoặc sao chép nội dung text nếu có hỗ trợ.
