# Hướng dẫn Solo Developer QA (Phiên bản 0.9.0-beta)

Tài liệu này mô tả quy trình kiểm thử dành cho cá nhân (Solo QA) không sử dụng Play Console cho EnglishPath AI.

## 1. Mục đích
- Đảm bảo app ổn định trước khi tạo APK Release.
- Tự động hóa và chuẩn hóa các bước kiểm tra thủ công và tự động.
- Tránh phụ thuộc vào các dịch vụ Play Console trả phí.

## 2. Công cụ QA Tích hợp
Từ phiên bản 0.9.0-beta, app đã tích hợp công cụ QA ngay bên trong app:
- **Truy cập:** Mở app -> Tab Hồ sơ (Profile) -> Click nút công cụ (Icon Cài đặt/Dev) -> **Self-Test & QA**.
- **Tính năng chính:**
  - **Auto Diagnostics:** Tự động kiểm tra (Content data, Database, Preferences).
  - **Manual Checklist:** Danh sách 34 bước kiểm thử thủ công có lưu trạng thái (SharedPreferences).
  - **Distribution Assistant:** Hướng dẫn tạo và chia sẻ APK, Copy template mô tả lỗi.

## 3. Quy trình QA trước khi Release APK
Mỗi khi có phiên bản Release Candidate (RC) mới:
1. Build `assembleDebug` và cài đặt lên máy thật.
2. Mở tab QA, chạy **Run Automated Self-Tests**.
3. Mở **Manual Checklist**, thực hiện kiểm tra từng bước một (cài mới, login, test A0, test A1, test reminder, test TTS, test backup/restore).
4. Đảm bảo toàn bộ 34 checklist được check `[x]`.
5. Đảm bảo toàn bộ automated tests đều màu xanh (PASS).
6. Nếu có lỗi, export QA Diagnostics Report (JSON) và lưu vào file log để xử lý.

## 4. Xử lý lỗi
- Nếu một step manual fail: Mở Activity liên quan, bắt logcat, sửa lỗi, build lại APK debug.
- Nếu auto test fail: Kiểm tra `AppDatabase` hoặc các Validator script.

Quy trình này giúp đảm bảo bản Release chia sẻ cho tester hoặc người dùng ngoài Play Store đạt chất lượng tốt nhất.
