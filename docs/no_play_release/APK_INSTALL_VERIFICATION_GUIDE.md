# APK Install Verification Guide

Hướng dẫn cài đặt file APK trên máy thật cho Tester (không qua Google Play).

## 1. Cách cài đặt bằng giao diện (File Manager/Zalo/Telegram)
- Gửi file `app-release.apk` qua Zalo/Telegram.
- Mở file trên điện thoại, hệ thống sẽ hỏi cài đặt.
- Bấm "Install".

## 2. Cách cài bằng ADB
Dành cho máy cắm cáp USB vào máy tính:
1. Gõ lệnh kiểm tra thiết bị: `adb devices`
2. Cài đặt file: `adb install -r app-release.apk`
*(Cờ `-r` giúp ghi đè lên phiên bản cũ mà không mất dữ liệu).*

## 3. Cách xử lý “App not installed”
- Thường do cài đè phiên bản có chữ ký (keystore) khác nhau.
- Cách xử lý: Gỡ app cũ ra trước rồi cài lại.

## 4. Cách xử lý Android chặn Unknown Sources
- Màn hình sẽ hiện popup cảnh báo. Bấm "Settings" trên popup.
- Gạt công tắc "Allow from this source" (Cho phép cài đặt từ nguồn này).
- Quay lại và bấm "Install".

## 5. Cách xử lý version thấp hơn không cho cài đè
- Android không cho phép cài bản có `versionCode` nhỏ hơn đè lên bản lớn hơn.
- Cách xử lý: Buộc phải gỡ bản cũ. Lưu ý cần Backup JSON trước khi gỡ.

## 6. Cách gỡ App
- Nhấn giữ icon ứng dụng ngoài màn hình -> Chọn "Uninstall".

## 7. Cách Clear Data
- Settings -> Apps -> EnglishPath AI -> Storage -> "Clear Data" & "Clear Cache".
- Thao tác này sẽ xóa mọi dữ liệu nội bộ y như mới cài đặt.

## 8. Cách kiểm tra App Version
- Mở App -> Tab "Profile" -> Kéo xuống dưới cùng xem chữ "Version 0.9.x-beta".

## 9. Cách copy Diagnostics
- Mở App -> Tab "Profile" -> "Settings" -> Kéo xuống cùng -> "Export Diagnostics" -> "Copy".

## 10. Cách Backup trước khi gỡ
- Mở App -> Tab "Profile" -> "Backup & Restore" -> "Export Backup" -> Chọn chia sẻ hoặc Copy nội dung lưu vào Ghi chú.
