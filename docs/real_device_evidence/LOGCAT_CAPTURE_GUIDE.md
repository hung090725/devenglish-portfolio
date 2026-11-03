# Logcat Capture Guide

Hướng dẫn thu thập log hệ thống (Logcat) trên máy thật phục vụ gỡ lỗi.

## 1. Bật Developer Options
- Vào Settings (Cài đặt) -> About phone (Giới thiệu điện thoại).
- Chạm 7 lần vào "Build number" (Số phiên bản) để bật Developer Options.

## 2. Bật USB Debugging
- Quay lại Settings -> Developer Options (Tùy chọn nhà phát triển).
- Bật "USB debugging" (Gỡ lỗi USB).
- Cắm cáp điện thoại vào máy tính, chọn "Allow USB debugging" khi có hộp thoại hiện lên trên điện thoại.

## 3. Kiểm tra kết nối thiết bị
Mở Terminal/CMD trên máy tính gõ:
```bash
adb devices
```
Đảm bảo thiết bị báo chữ `device` (không phải `unauthorized`).

## 4. Lọc Log theo package name
Để chỉ xem log của app, sử dụng lệnh:
```bash
adb logcat | findstr hung.edu.apptienganh
```

## 5. Lưu Log ra file
Để lưu toàn bộ log (của cả máy) từ buffer hiện tại vào file txt:
```bash
adb logcat -d > englishpath_logcat.txt
```

## 6. Xóa log (Clear Log)
Nên thực hiện xóa buffer trước khi tái hiện lỗi để file log sạch sẽ:
```bash
adb logcat -c
```

## 7. Quy trình chuẩn khi app crash
1. Chạy lệnh: `adb logcat -c`
2. Mở lại app trên điện thoại.
3. Thao tác để tái hiện lỗi Crash / Lỗi lạ.
4. Chạy lệnh xuất log: `adb logcat -d > crash_log.txt`
5. Xuất thêm Diagnostics trong app (nếu app không crash chết luôn).
6. Nén file và gửi cho Developer.

## 8. Lưu ý bảo mật
KHÔNG chia sẻ file Logcat cho người lạ, không public lên Github Issues mà không che/xóa các thông tin nhạy cảm (token, mật khẩu, đường dẫn máy...).
