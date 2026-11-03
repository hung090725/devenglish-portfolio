# Release Notes - EnglishPath AI (v0.9.3-beta)

## 1. Tính năng chính
- Hỗ trợ toàn bộ từ vựng và ngữ pháp trình độ A0, A1, A2, B1.
- Hệ thống Spaced Repetition (SRS) giúp nhắc nhở ôn tập.
- Luyện phát âm (TTS & Microphone) với hệ thống chấm điểm chi tiết.
- Backup & Restore dữ liệu cục bộ qua JSON an toàn.

## 2. Dành cho Solo QA & Tester
- Thêm **Release Dashboard** vào Solo QA (Copy tester message, release notes).
- Nâng cấp cơ chế kiểm thử nội bộ (Solo QA) với Strict Mode Data Quality.
- Xuất log QA Report chi tiết hơn (Build Type, DB Version, Recommendations).
- Bổ sung AndroidTest UI Automator Skeleton để test Permission Dialogs.
- Bổ sung công cụ 1-click test `run_solo_qa_gate.bat`.

## 3. Known Limitations
- Chưa có tester đánh giá diện rộng.
- Một số dòng máy cũ có thể không có sẵn English TTS Engine.
- Chưa lưu dữ liệu trên server (Cloud).

## 4. Cách báo lỗi (Bug Report)
Mở Cài đặt -> Copy Diagnostics và gửi kèm ảnh/video lỗi cho Developer.
