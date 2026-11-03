# SPRINT 27: PATCH APPLICATION REPORT

Báo cáo kết quả quá trình áp dụng các bản vá (Content Correction Patches) đã được kiểm duyệt với độ tin cậy cao (HIGH Confidence) vào hệ thống dữ liệu.

## Tổng Quan Kết Quả
- **Tổng số Patch đưa vào**: 2
- **Số Patch APPLIED (Thành công)**: 2
- **Số Patch REJECTED (Bị từ chối)**: 0
- **Số Patch NEEDS_REVIEW (Chờ duyệt)**: 0

## Danh sách APPLIED
| Patch ID | Loại Content | File Chịu Ảnh Hưởng (Mô phỏng/Seed) | Chi tiết sửa đổi |
|---|---|---|---|
| PATCH-001 | Vocabulary | `SeedDataProvider.java` (Logic mock) | Thay đổi level của "serendipity" từ `A1` thành `C1`. |
| PATCH-002 | Quiz | `SeedDataProvider.java` (Logic mock) | Thay đổi câu hỏi từ "Which of the following..." thành "What is..." để phù hợp A0. |

## Lý Do Các Nhóm Sửa Đổi
1. **Thay đổi Level (Vocab-101)**: Do thuật toán và quy tắc ưu tiên đánh giá từ vựng khó không được phép xuất hiện ở Level sơ cấp A1 nhằm đảm bảo lộ trình CEFR.
2. **Đơn giản hóa Câu Hỏi (Quiz-A0-5)**: Người dùng ở level A0 không có đủ ngữ pháp để hiểu một mệnh đề quan hệ rườm rà.

## Rủi Ro Còn Lại
- Hiện tại hệ thống không tự động thay đổi các câu hội thoại có cấu trúc "máy móc" vì nguy cơ làm mất ngữ cảnh (Context Mismatch) là khá lớn. Các phần này vẫn cần một Human Reviewer xem xét.
