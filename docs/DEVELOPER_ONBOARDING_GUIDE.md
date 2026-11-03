# DEVELOPER ONBOARDING GUIDE

Chào mừng bạn đến với dự án **EnglishPath AI**! Đây là tài liệu hướng dẫn nhanh để bạn có thể setup và đóng góp vào dự án.

## 1. Cài đặt ban đầu
1. **Tải Android Studio** phiên bản mới nhất.
2. **Clone** (hoặc Open) thư mục dự án `apptienganh` bằng Android Studio.
3. Chờ IDE **Sync Gradle** (Đảm bảo đang dùng JDK nhúng sẵn của Android Studio).
4. Nhấn nút Play (Run) hoặc chạy lệnh `.\gradlew assembleDebug` để build thử.

## 2. Kiểm thử chất lượng (QA Gate)
Sau khi viết code hoặc sửa lỗi, bạn BẮT BUỘC phải chạy file:
`tools\qa\run_solo_qa_gate.bat`
File này sẽ chạy hàng loạt lệnh kiểm tra (Lint, Unit Test, AndroidTest, Build) để đảm bảo không có gì bị hỏng.

## 3. Kiến trúc thư mục cơ bản
Dự án sử dụng mô hình **MVVM** (Model-View-ViewModel) + **Room Database**:
- **ui/**: Chứa các Activity/Fragment (màn hình). VD: Để sửa Home, vào `ui/home/HomeFragment.java`.
- **data/local/**: Chứa cấu trúc CSDL nội bộ. 
  - `entity/`: Định nghĩa các bảng.
  - `dao/`: Định nghĩa các câu truy vấn.
- **data/repository/**: Quản lý luồng dữ liệu giữa Room và ViewModel.
- **domain/**: Logic độc lập, Constants.

## 4. Cách thao tác thường xuyên
- **Tìm một màn hình:** Nhấn `Shift + Shift` (Search Everywhere) trên Android Studio và gõ tên tính năng. Hoặc vào `AndroidManifest.xml` để dò tên Activity.
- **Thêm Test:** 
  - Unit Test (chạy nhanh không cần máy ảo): vào thư mục `app/src/test/java/`.
  - AndroidTest (cần thiết bị thật hoặc máy ảo): vào `app/src/androidTest/java/`.
- **Xuất file APK:** Chạy `.\gradlew assembleRelease` để tạo bản phát hành (sẽ nằm trong `app/build/outputs/apk/release/`).

## 5. Lưu ý CỰC KỲ QUAN TRỌNG (Red Lines)
- **TUYỆT ĐỐI KHÔNG** chạm vào file Keystore (`.jks`) hay để lộ nó ra ngoài.
- **TUYỆT ĐỐI KHÔNG** đổi `applicationId` trong `build.gradle.kts`. Việc này sẽ phá hủy luồng cập nhật ứng dụng của người dùng.
- Mọi dữ liệu (từ vựng, bài học) đều nằm offline hoàn toàn (Local First), không gọi API Backend nào cả. Tránh việc kéo thêm SDK/Backend thừa thãi vào dự án.
