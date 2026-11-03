# Real Device Test Session - EnglishPath AI 0.9.3-BETA

## Session Info
- **Tester Name:** [Nhập tên]
- **Device Model:** [Nhập tên máy, VD: Samsung Galaxy S23]
- **Android Version:** [VD: 14]
- **App Version:** 0.9.3-beta
- **Build Type:** release
- **APK Path:** `app/build/outputs/apk/release/app-release.apk`
- **Install Method:** [USB / Zalo / Telegram / Drive / ADB]
- **Test Date:** [YYYY-MM-DD]
- **Test Start Time:** [HH:MM]
- **Test End Time:** [HH:MM]
- **Overall Result:** [PASS / NEED FIX / BLOCKED]

## Test Cases

| ID | Test Group | Steps | Expected | Actual | Status | Evidence File | Note |
|---|---|---|---|---|---|---|---|
| 1 | Install APK | Cài file APK lên thiết bị | Cài đặt thành công, icon xuất hiện | | NOT TESTED | | |
| 2 | First launch | Mở app lần đầu sau cài đặt | Splash screen nhanh, không crash | | NOT TESTED | | |
| 3 | Onboarding | (Nếu có) Lướt qua onboarding | Hoạt động bình thường | | NOT TESTED | | |
| 4 | Level selection | Chọn level trong Profile/Home | Giao diện hiện đúng thông tin level | | NOT TESTED | | |
| 5 | Home | Vuốt, xem ProgressBar và Danh sách bài học | Giao diện không bị cắt | | NOT TESTED | | |
| 6 | Lessons A0/A1/A2/B1 | Mở danh sách các ngày học | Tải danh sách nhanh, mượt mà | | NOT TESTED | | |
| 7 | A0 day 1 | Học bài A0 ngày 1 | Flow hoạt động bình thường, ghi điểm | | NOT TESTED | | |
| 8 | TTS | Nghe phát âm từ vựng | Loa phát đúng tiếng Anh | | NOT TESTED | | |
| 9 | Microphone | Thu âm 1 từ | Hiện dialog xin quyền, thu âm tốt | | NOT TESTED | | |
| 10 | Quiz | Làm bài tập trắc nghiệm | Chấm điểm đúng, không có câu trả lời trống | | NOT TESTED | | |
| 11 | SRS | Ôn tập từ vựng | Hiển thị từ đến hạn đúng hạn | | NOT TESTED | | |
| 12 | Reminder | Kiểm tra Notification Settings | Worker chạy, không crash | | NOT TESTED | | |
| 13 | Backup export | Xuất JSON tiến trình | Copy JSON thành công | | NOT TESTED | | |
| 14 | App uninstall | Gỡ cài đặt app | Gỡ sạch sẽ | | NOT TESTED | | |
| 15 | App reinstall | Cài lại app từ APK | App như mới, trống dữ liệu | | NOT TESTED | | |
| 16 | Backup restore | Import JSON đã copy trước đó | Tiến độ được phục hồi chính xác | | NOT TESTED | | |
| 17 | Diagnostics export | Copy lỗi trong Cài Đặt | Copy JSON text vào clipboard | | NOT TESTED | | |
| 18 | Solo QA | Chạy 4 bài test nội bộ | Màn hình không đơ, kết quả hiện rõ | | NOT TESTED | | |
| 19 | Offline mode | Tắt WiFi/4G, dùng app | App hoạt động bình thường (không cần mạng) | | NOT TESTED | | |
| 20 | App close/reopen | Thoát app qua đa nhiệm, mở lại | App khôi phục nhanh, không crash | | NOT TESTED | | |

*Lưu ý: Không ghi PASS giả. Nếu chưa chạy trên máy thật, hãy giữ là NOT TESTED.*
