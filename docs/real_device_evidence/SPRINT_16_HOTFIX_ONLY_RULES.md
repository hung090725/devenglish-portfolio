# Quy Tắc Hotfix - Sprint 16

Sprint 16 tập trung **Độc Quyền** vào việc chuẩn hóa trải nghiệm trên thiết bị thật và phát hành bản cài ổn định.

## Các Quy Tắc Tuyệt Đối

1. **Không mở thêm cấp độ mới**: Level B2 tạm thời đóng băng. Tuyệt đối không sinh dữ liệu (Seed Data) cho B2.
2. **Không thêm tính năng mới**: Không thêm màn hình, không đổi kiến trúc hệ thống (như chuyển Kotlin hay thêm Backend).
3. **Chỉ sửa lỗi dựa trên bằng chứng (Evidence-based Fixing)**:
   - Chỉ sửa code khi có Logcat crash, Diagnostics lỗi, ảnh chụp, hoặc báo cáo thất bại từ Checklist Real Device.
   - Các phạm vi ưu tiên sửa lỗi: Crash, Permission (Mic/Thông báo), Backup/Restore (JSON lỗi), Text-to-Speech (bị ngắt), Microphone (không nhận diện), Reminder (không chạy đúng giờ), Giao diện bị vỡ quá nặng ngăn cản trải nghiệm.
   - Lỗi logic như tiến độ không lưu, unlock bài sai.
4. **Cấu trúc một Hotfix**:
   Mọi lần commit sửa code phải ghi nhận:
   - **Lỗi gốc (Root cause)**: Gây ra bởi logic nào?
   - **File đã sửa**: Ví dụ `BackupRestoreActivity.java`.
   - **Test lại**: Đã chạy thử chưa? Bằng chứng là gì?
   - **Kết quả**: Hết lỗi.
5. **Dữ liệu SQLite**: Không chỉnh sửa dữ liệu hàng loạt (mass update) trừ khi bộ công cụ Validator phát hiện ra lỗi sai dữ liệu hiển thị.
6. **Tiêu chuẩn "Production-ready"**: Không được gọi ứng dụng là "Sẵn sàng phát hành" nếu `PERSONAL_TEST_RESULT_SUMMARY.md` vẫn còn đánh dấu `NOT TESTED`.
