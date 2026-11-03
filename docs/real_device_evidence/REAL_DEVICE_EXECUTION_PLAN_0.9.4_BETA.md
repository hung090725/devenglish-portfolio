# Kế Hoạch Kiểm Thử Trên Thiết Bị Thật (Version 0.9.4-beta)

Tài liệu này hướng dẫn Developer tự thực hiện quá trình kiểm thử (Solo QA) trực tiếp trên điện thoại thật. Quy trình này **không được mô phỏng (fake) kết quả**. Tất cả kết quả PASS/FAIL phải dựa trên bằng chứng thực tế.

## Thứ tự thực hiện

### 1. Chuẩn bị APK
Sử dụng bản build Release mới nhất:
- Đường dẫn: `app/build/outputs/apk/release/app-release.apk`

### 2. Xử lý khi không cài được
Nếu thiết bị từ chối `app-release.apk` (do chưa sign key release chuẩn hoặc chính sách bảo mật), hãy chuyển sang bản Debug:
- Đường dẫn: `app/build/outputs/apk/debug/app-debug.apk`

### 3. Cài đặt APK lên thiết bị
Bạn có thể cài đặt thông qua 2 cách:
**Cách A:** Gửi file qua Zalo/Telegram/Google Drive, tải xuống điện thoại và bấm cài đặt (Nhớ cấp quyền Install unknown apps).
**Cách B (ADB):** Kết nối điện thoại với máy tính qua cáp USB (đã bật USB Debugging), sau đó chạy:
```bash
adb devices
adb install -r app/build/outputs/apk/release/app-release.apk
```

### 4. Thời gian test
- Tiến hành chạy app và test đầy đủ các chức năng theo checklist trong khoảng **30 – 45 phút**.

### 5. Ghi nhận kết quả
Trong lúc test, sử dụng chức năng "Solo QA" -> "Real Device Test Session" ngay trong app để tick trạng thái `PASS`, `FAIL`, `BLOCKED`, `NOT TESTED`.
- Bấm **Export Report** trong app.
- Dán nội dung đó để ghi đè vào file `docs/real_device_evidence/REAL_DEVICE_TEST_SESSION_0.9.4_BETA.md` (nếu cần tracking thủ công).

### 6. Xử lý khi có lỗi
Mọi lỗi (FAIL) phát hiện trong quá trình test cần phải:
1. **Quay video / Chụp ảnh màn hình** lỗi trên điện thoại.
2. **Copy Diagnostics JSON**: Mở ứng dụng -> Cài đặt -> Copy Diagnostics.
3. **Lấy Logcat (nếu có ADB)**: Chạy `adb logcat -d > error_log.txt`.
4. Ghi chú rõ **các bước tái hiện lỗi (Steps to reproduce)**.
5. Đánh giá mức độ nghiêm trọng (Critical / High / Medium / Low).

> [!WARNING]
> Tuyệt đối **không được đánh dấu PASS** nếu tính năng đó chưa được trực tiếp test trên điện thoại thật. Nếu chưa kịp test, hãy để `NOT TESTED`.
