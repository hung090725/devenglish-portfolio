# TÀI LIỆU VÀ FILE CẦN ĐÍNH KÈM KHI PHÂN PHỐI CÁ NHÂN

Khi bạn gửi email hoặc nhắn tin chia sẻ ứng dụng cho người khác, đây là danh sách những thứ bạn cần chuẩn bị:

## 1. BẮT BUỘC (Gửi cho Tester / Người quen):
- **File APK Cài đặt:**
  - Lấy file `app-release.apk` (Tuyệt đối không lấy `app-release-unsigned.apk`).
- **Hướng dẫn Cài đặt & Học tập:**
  - `INSTALL_APK_STEP_BY_STEP.md`
  - `OFFLINE_USER_MANUAL.md`
  - `FIRST_7_DAYS_STUDY_GUIDE.md`

## 2. DÀNH CHO CẬP NHẬT (Update Version Mới):
- **Gửi kèm thêm:**
  - `UPDATE_EXISTING_APP_GUIDE.md`
  - `BACKUP_BEFORE_UPDATE.md` (Để họ không làm mất tiến trình học).

## 3. KHÔNG BAO GIỜ GỬI (Danh sách Đen):
Vì lý do bảo mật, **chắc chắn 100%** bạn không đính kèm nhầm các file sau:
1. `*.jks` (File chữ ký Keystore)
2. `signing.properties` (Chứa mật khẩu)
3. Bất kỳ file `Backup_*.json` nào chứa dữ liệu học tập cá nhân của bạn.
4. Bất kỳ file `Diagnostics_*.txt` cá nhân nào (chứa thông tin điện thoại của bạn).
5. File `app-release-unsigned.apk` hoặc mã nguồn dự án.
