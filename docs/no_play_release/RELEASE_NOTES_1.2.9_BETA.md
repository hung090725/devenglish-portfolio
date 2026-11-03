## [1.2.9-beta] - 2026-06-14

### Sprint 28: Human Review Round 1 & Reviewer Feedback Patch

Bản cập nhật này không mang đến tính năng học tập mới nào cho người dùng cuối. Nhiệm vụ duy nhất của nó là tự động hóa và chuẩn hóa quy trình nhận phản hồi (feedback) từ chuyên gia (Human Reviewer) về ứng dụng EnglishPath AI.

**Điểm nhấn kỹ thuật:**
- Khởi tạo gói **Human Review Round 1 Pack** chứa các TODO chưa thể tự động sửa.
- Xây dựng **Reviewer Feedback Import Format** cùng bộ Parser & Validator viết bằng Java, đảm bảo phản hồi chuyên gia (CSV) được xử lý đúng chuẩn.
- Tích hợp **Feedback To Patch Converter** để biến các ý kiến (FIX, REWRITE, MOVE_LEVEL, REMOVE) thành ContentCorrectionPatch.
- Kích hoạt **Reviewed Content Lock** - Khóa các mục đã được dán nhãn `REVIEWED_OK` để Auditor không đụng chạm đến.
- Cập nhật trạng thái TODO list và cung cấp Cẩm nang **Reviewer Handoff Zip Guide** bảo vệ mã nguồn.

Hệ thống tiếp tục giữ vững nguyên tắc: **Không tự ý sửa những nội dung mà máy móc không chắc chắn, và không tuyên bố "Đúng 100%" khi vẫn còn mục NEEDS_REVIEW.**
