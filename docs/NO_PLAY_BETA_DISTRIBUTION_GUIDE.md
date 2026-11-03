# NO_PLAY_BETA_DISTRIBUTION_GUIDE.md
# Hướng dẫn phân phối Beta không qua Google Play

Phiên bản: **0.9.0-beta** | Package: `hung.edu.apptienganh`

---

## Tổng quan

Hướng dẫn này dành cho việc phân phối APK beta **EnglishPath AI** trực tiếp đến tester
mà **không cần** tải lên Google Play Console.

---

## 1. Build APK Debug (Để test nhanh)

```powershell
cd C:\Users\Admin\StudioProjects\apptienganh
.\gradlew assembleDebug
```

APK output: `app\build\outputs\apk\debug\app-debug.apk`

---

## 2. Build APK Release (Để phân phối)

```powershell
.\gradlew assembleRelease
```

APK output: `app\build\outputs\apk\release\app-release.apk`

> **Lưu ý:** Release APK cần được ký (sign) trước khi cài đặt trên thiết bị thực.
> Debug APK được ký tự động và có thể cài đặt ngay.

---

## 3. Phân phối qua GitHub Releases

### 3.1. Tạo Release trên GitHub
1. Vào: https://github.com/hung090725/apptienganh/releases/new
2. Tag: `v0.9.0-beta`
3. Release title: `EnglishPath AI v0.9.0-beta`
4. Upload file: `app-debug.apk` hoặc `app-release.apk`
5. Mô tả: dán nội dung từ `GITHUB_RELEASE_DRAFT.md`

### 3.2. Tester cài đặt
1. Tải APK từ link GitHub Release
2. Trên điện thoại: Bật **Cài đặt từ nguồn không rõ** (Settings > Security)
3. Mở file APK đã tải → Cài đặt

---

## 4. Phân phối qua Google Drive / Zalo / Telegram

### 4.1. Upload APK
1. Upload `app-debug.apk` lên Google Drive
2. Đặt quyền: **Bất kỳ ai có link đều xem được**
3. Sao chép link chia sẻ

### 4.2. Gửi cho tester
- Zalo group, Telegram group, hoặc email cá nhân
- Kèm hướng dẫn: xem `TESTER_INSTALL_APK_GUIDE.md`

---

## 5. Phân phối qua ADB (Developer)

```powershell
# Kết nối thiết bị qua USB
adb devices

# Cài đặt APK
adb install app\build\outputs\apk\debug\app-debug.apk

# Kiểm tra log
adb logcat -s EnglishPathAI
```

---

## 6. Checklist trước khi phân phối

- [ ] Build thành công (assembleDebug hoặc assembleRelease)
- [ ] Test thủ công trên thiết bị thực
- [ ] Kiểm tra A0, A1, A2, B1 data đầy đủ
- [ ] Kiểm tra Backup/Restore hoạt động
- [ ] Version được cập nhật đúng (0.9.0-beta)
- [ ] APK size hợp lý (< 10MB cho debug)

---

## 7. Thu thập phản hồi

- Dùng tính năng **Beta Feedback** trong app
- Hoặc form theo mẫu `TESTER_FEEDBACK_FORM_TEMPLATE.md`
- Tập trung: crash, lỗi dữ liệu, UI/UX

---

*Tài liệu tạo tự động bởi Sprint 11 automation.*
