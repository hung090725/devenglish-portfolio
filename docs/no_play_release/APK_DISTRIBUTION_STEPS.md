# Các Bước Phân Phối File APK Nội Bộ

Tài liệu này dành cho Developer (Solo QA) để chuẩn bị và gửi ứng dụng cho người dùng cài đặt ngoài Play Store.

## Bước 1: Build file APK
Đảm bảo bạn đã build file Release APK thành công bằng lệnh:
```bash
./gradlew assembleRelease
```
File APK của bạn sẽ nằm tại:
`app/build/outputs/apk/release/app-release.apk`

## Bước 2: Chuẩn bị nơi lưu trữ
Để gửi cho người thân, bạn nên tải file `app-release.apk` lên một dịch vụ lưu trữ đám mây dễ truy cập.
**Khuyến nghị:**
- **Google Drive**: Tạo thư mục "EnglishPath Beta", tải file lên và chia sẻ quyền "Bất kỳ ai có liên kết đều có thể xem".
- **Zalo / Telegram**: Bạn cũng có thể gửi trực tiếp file `.apk` vào khung chat (Nhanh và tiện lợi nhất cho thiết bị di động).

## Bước 3: Gửi kèm hướng dẫn
Bên cạnh file APK, đừng quên copy và gửi đoạn hướng dẫn trong file `ZALO_TELEGRAM_TESTER_MESSAGE.md`. Điều này rất quan trọng vì thiết bị Android mặc định sẽ chặn cài đặt từ file APK.

## Bước 4: Hướng dẫn người dùng Backup trước khi nâng cấp/gỡ cài đặt
Nếu Tester của bạn đang dùng một phiên bản APK cũ và bạn gửi cho họ APK mới, hãy dặn họ:
1. Mở ứng dụng (bản cũ).
2. Vào Menu -> Backup & Restore.
3. Ấn "Sao lưu", copy chuỗi văn bản JSON sinh ra và dán vào Zalo "Cloud của tôi" hoặc Ghi chú.
4. Cài đặt APK mới (thường hệ thống sẽ hỏi "Bạn có muốn cập nhật ứng dụng này không?"). Nếu cập nhật trực tiếp thành công thì dữ liệu vẫn còn. Nếu cài lỗi, họ phải xóa bản cũ, cài bản mới, sau đó vào Backup & Restore -> Dán đoạn mã JSON vào -> Nhấn Khôi phục.

## ⚠️ Lưu ý Cực Quan Trọng
- **Không gửi public**: Vì đây là bản Beta có chứa code nháp, không nên post công khai file APK lên các group Facebook/Forum để tránh rủi ro bảo mật hoặc lỗi phát sinh chưa kiểm soát.
- **Không chia sẻ Keystore**: Tuyệt đối giữ bí mật file `.jks` dùng để ký app và mật khẩu của bạn.
