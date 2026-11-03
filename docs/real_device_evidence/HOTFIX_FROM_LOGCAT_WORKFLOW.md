# Quy Trình Sửa Lỗi Hotfix Từ Logcat

Khi test trên thiết bị thật (Solo QA) và phát hiện ứng dụng hoạt động không như mong đợi (Crash, UI đơ, Logic sai), bạn cần sử dụng Logcat để tìm lỗi.

## 1. Kết nối và dọn dẹp Logcat
Đầu tiên, hãy cắm cáp USB và mở terminal:
```bash
adb devices
# Xóa sạch log cũ để dễ theo dõi
adb logcat -c
```

## 2. Tái hiện lỗi
- Mở app trên điện thoại và thực hiện lại các thao tác dẫn đến lỗi.
- Đợi đến khi lỗi xuất hiện.

## 3. Lưu Logcat
Xuất logcat ra một file text:
```bash
adb logcat -d > englishpath_crash_log.txt
```

## 4. Lọc Logcat theo Package
Tìm nhanh các lỗi thuộc về ứng dụng:
```bash
adb logcat | findstr hung.edu.apptienganh
```
*(Nếu dùng macOS/Linux thì dùng `grep hung.edu.apptienganh`)*

## 5. Thu thập thông tin báo lỗi
Một báo cáo lỗi hoàn chỉnh phải gồm:
- **Logcat file**: (Đoạn log chứa `FATAL EXCEPTION` hoặc `E/AndroidRuntime`).
- **Diagnostics JSON**: (Copy từ Backup Restore).
- **Ảnh / Video**: (Ghi lại luồng UI bị sai).
- **Bước tái hiện**: (Steps to reproduce).

> [!CAUTION]
> Tuyệt đối không gửi file log chứa dữ liệu cá nhân nhạy cảm, key, hay token lên các diễn đàn công cộng nếu không có sự che chắn (redact).

## 6. Phân loại mức độ lỗi
- **CRITICAL**: App bị crash hoàn toàn, hoặc mất dữ liệu học tập. Yêu cầu sửa ngay lập tức.
- **HIGH**: App không crash nhưng tính năng cốt lõi (Học bài, Mic, TTS) bị hỏng, không thể qua bài. Yêu cầu sửa ngay.
- **MEDIUM**: Giao diện sai lệch, logic hiển thị điểm sai, thao tác bất tiện.
- **LOW**: Lỗi chính tả, padding/margin nhỏ, màu sắc không hợp lý.
