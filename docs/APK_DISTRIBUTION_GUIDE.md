# Hướng dẫn Phân phối APK ngoài Play Store (Phiên bản 0.9.0-beta)

Tài liệu này hướng dẫn cách xuất và phân phối ứng dụng Android EnglishPath AI trực tiếp bằng file APK mà không cần qua Google Play Console.

## 1. Mục đích
Dành cho các dự án chưa có ngân sách đăng ký Play Console, hoặc muốn test nội bộ với nhóm tester mà không cần quy trình Closed Testing phức tạp của Google.

## 2. Chuẩn bị Build Release
Trước khi build, hãy chắc chắn bạn đã chạy qua quy trình QA trong `SOLO_DEVELOPER_QA_GUIDE.md`.
Kiểm tra version code và version name trong `app/build.gradle`.

## 3. Lệnh Build APK
Sử dụng Gradle wrapper để build APK đã được ký (signed APK):

```bash
# Build Release APK
./gradlew assembleRelease
```

File APK sẽ được tạo tại: `app/build/outputs/apk/release/app-release.apk`

## 4. Chia sẻ APK cho Tester
1. Đổi tên file APK cho rõ ràng, ví dụ: `EnglishPath-v0.9.0-beta.apk`.
2. Upload lên một nền tảng lưu trữ an toàn (Google Drive, Dropbox, GitHub Releases).
3. Gửi link tải cho Tester cùng với hướng dẫn.

## 5. Hướng dẫn Tester cài đặt
Người dùng/Tester cần thực hiện:
1. Tải file `.apk` về điện thoại Android.
2. Mở file APK. Hệ thống sẽ hỏi quyền cài đặt từ nguồn ngoài (Install unknown apps).
3. Cho phép trình duyệt/file manager quyền cài đặt ứng dụng.
4. Chọn "Cài đặt" (Install) và mở ứng dụng.

## 6. Theo dõi lỗi và Feedback
Vì không có Play Console (Android Vitals), yêu cầu tester:
- Nếu app crash, hãy dùng chức năng `Export Diagnostics` trong tab QA, copy nội dung và gửi qua Zalo/Email cho lập trình viên.
- Chụp ảnh màn hình đối với lỗi UI.
- Gửi file backup JSON định kỳ để lập trình viên kiểm tra độ ổn định của Database.
