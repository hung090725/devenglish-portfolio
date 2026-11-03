# Lưu ý bảo mật khi phân phối APK (No-Play)

Mặc dù không đẩy lên CH Play, bạn vẫn cần lưu ý các vấn đề bảo mật quan trọng:

## 1. Quản lý Keystore
- Bạn **tuyệt đối không gửi file .jks (keystore)**, mật khẩu (alias password, key password) cho bất kỳ ai, kể cả tester.
- Keystore dùng để định danh nhà phát triển. Nếu mất keystore, bạn sẽ không thể update app trên Play Store sau này.

## 2. Dữ liệu cá nhân (Backup)
- Tính năng Export Backup JSON tạo ra dữ liệu học tập cá nhân.
- Không gửi file JSON backup của bạn cho cộng đồng, vì nó chứa điểm số và progress của chính bạn.
- Khi cần report lỗi, chỉ dùng "App Diagnostics", nó đã được loại bỏ thông tin nhạy cảm.

## 3. Không Public APK tùy tiện
- Nếu đây là bản Closed Beta, chỉ chia sẻ link APK cho những người bạn tin tưởng.
- Không đăng file APK lên các group công khai hoặc forum mở trừ khi bạn thực sự muốn Open Beta.
