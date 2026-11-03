# REVIEWED CONTENT LOCK REPORT

Báo cáo cơ chế khóa nội dung (Content Lock) nhằm bảo vệ thành quả của Human Reviewer khỏi các đợt chạy tự động của Auditor.

## Cơ chế hoạt động
Khi một đoạn nội dung (ví dụ: nghĩa của một từ vựng, hoặc một đoạn hội thoại) được Reviewer dán nhãn `REVIEWED_OK` hoặc `FIXED_BY_HUMAN_REVIEW`, hệ thống sẽ đưa ID của nó vào `ReviewedContentLock`.

- **Auditor Tự Động**: Nếu Auditor phát hiện lỗi trên một mục đã bị khóa, nó sẽ không được phép sinh ra `CorrectionPatch` để tự động sửa đổi. Thay vào đó, nó sẽ sinh ra một cảnh báo `REVIEWED_WARNING`.
- **Ngoại lệ (CRITICAL)**: Nếu StrictContentGate phát hiện lỗi thực sự vỡ logic (ví dụ `correctAnswer` bị lỗi rỗng bằng một cách nào đó dù đã khóa), Gate vẫn sẽ văng lỗi `FAIL` để Dev can thiệp.

## Trạng Thái Khóa Hiện Tại
- **Số lượng item đang bị khóa**: 0
*(Hệ thống đang chờ lô kết quả phản hồi đầu tiên từ Reviewer để kích hoạt các ổ khóa này).*
