# FINAL DEBUG / RELEASE SEPARATION BEHAVIOR

**Sprint 22 - Personal Release Kit**

Tài liệu này xác nhận hành vi hiển thị và hoạt động của các tính năng gỡ lỗi (Debug) so với bản phát hành (Release).

## 1. Solo QA & Kiểm thử nội bộ
- **Chỉ xuất hiện trên Debug Build:** Các Activity như `SoloQaActivity`, `SoloManualChecklistActivity` vẫn nằm trong codebase nhưng không được hiển thị dưới dạng nút bấm rõ ràng trên giao diện Release. Người dùng cần bấm 7 lần vào version trong Settings để kích hoạt (nếu có) hoặc nó bị ẩn hoàn toàn tùy build flavor.
- **Logcat (Logs):** Không in ra log nhạy cảm (như query database, raw text, progress logic) trong bản Release (thông qua `BuildConfig.DEBUG`).

## 2. Tính năng Diagnostics (Chẩn đoán)
- **An toàn cho Release:** Công cụ Diagnostics chỉ xuất ra trạng thái sức khỏe của ứng dụng (Device model, Android version, Database size, Room version).
- **Không chứa dữ liệu cá nhân:** Tuyệt đối không xuất điểm số, lịch sử luyện tập cụ thể hay thông tin định danh nào của người dùng vào báo cáo chẩn đoán. Mọi user đều có thể dùng tính năng này an toàn.

## 3. Quản lý Dữ liệu (Reset & Clear)
- Các nút "Xóa sạch toàn bộ tiến trình" (Reset Progress) nằm trong cài đặt nâng cao và có 2 bước xác nhận bằng tiếng Việt rõ ràng, đảm bảo người dùng bình thường không thể vô tình bấm nhầm.

## 4. Xử lý Lỗi (Crash Handling)
- Bản Release không hiển thị thông báo lỗi (Exception Stacktrace) đầy màn hình. Thay vào đó, ứng dụng sẽ có Fallback UI báo lỗi thân thiện ("Rất tiếc, đã có lỗi xảy ra. Vui lòng thử lại.").
- Các lỗi NullPointerException từ Intent Extras thiếu (nếu có) sẽ tự động đẩy người dùng về Home an toàn thay vì crash ứng dụng.
