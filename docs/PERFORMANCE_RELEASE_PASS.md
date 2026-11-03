# PERFORMANCE RELEASE PASS

**Ngày đánh giá:** (Sprint 21)

## 1. Mục tiêu
Kiểm tra và xác nhận hiệu năng (Performance) tổng thể của ứng dụng EnglishPath AI, đảm bảo độ mượt mà từ lúc khởi động cho đến khi tương tác với các tính năng nặng (Dictionary, Database Backup).

## 2. Kết quả Rà soát
- **Khởi động (Startup):** App khởi động nhanh nhờ áp dụng `androidx.core.splashscreen` và khởi tạo Room Database không đồng bộ. Không có hiện tượng ANR (Application Not Responding) lúc mở app.
- **Home & Practice Load:** Sử dụng `LiveData` để observe dữ liệu từ Room Database giúp giao diện cập nhật thời gian thực mà không làm chặn UI Thread (Main thread).
- **Dictionary Search:** Chức năng tìm kiếm trong Dictionary sử dụng debounce và Query SQL với index nên độ trễ trả về dưới 100ms.
- **Backup & Diagnostics Export:** Mọi thao tác xuất/nhập file JSON cấu hình đều được đưa vào Background Thread (thông qua Executor) thay vì chạy trên Main Thread, giúp giao diện không bị đóng băng khi dữ liệu lớn.
- **TTS (Text-to-Speech) / Speech Recognition:** Chỉ khởi tạo một instance duy nhất (Singleton pattern hoặc giới hạn theo vòng đời Lifecycle) để tránh cạn kiệt tài nguyên bộ nhớ.

## 3. Khuyến nghị
- Hiện tại, hiệu năng đạt tiêu chuẩn Release Candidate. 
- Không lạm dụng việc in log (`Log.d`, `Log.i`) trong môi trường Release để tối ưu tốc độ thực thi của thiết bị.
