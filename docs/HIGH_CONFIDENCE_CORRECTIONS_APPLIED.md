# HIGH CONFIDENCE CORRECTIONS APPLIED

Tài liệu này ghi nhận lại các sửa đổi đã được Hệ thống tự động thực hiện (Auto-Applied) dựa trên các quy luật an toàn tuyệt đối (High Confidence). Các thay đổi này đã được ghi đè trực tiếp vào cơ sở dữ liệu Seed mà KHÔNG CẦN CHỜ Human Reviewer duyệt.

## Các quy luật tự động:
1. **Trim Whitespace**: Xóa dấu cách thừa ở đầu và cuối chuỗi đối với tất cả các trường dữ liệu kiểu String.
2. **Normalize Quiz Answer**: Chuyển các đáp án `a, b, c, d` viết thường thành `A, B, C, D` viết hoa.
3. **Punctuation Fix**: Sửa lỗi chấm phẩy cuối câu nếu có 2 dấu chấm `..` thành `.`
4. **Estimated Minutes Fix**: Cập nhật các trường `estimatedMinutes` bị gán `<= 0` thành giá trị mặc định (`A0-A2: 5`, `B1-B2: 10`, `C1: 15`).
5. **Placeholder Fix**: Thay thế các placeholder rác do AI sinh ra (`"TODO"`, `"N/A"`, `"unknown"`) thành chuỗi rỗng `""` và đẩy item đó vào danh sách `CONTENT_REVIEW_TODO.md` để người học không nhìn thấy text kỳ cục.

## Lịch sử Auto-Correct:
| Thời gian | Tổng số thao tác | Loại lỗi chủ yếu | Trạng thái DB |
|---|---|---|---|
| 2026-06-14 | 142 records | Trim Whitespace (120), Normalize Answer (22) | SAFE & COMMITTED |

*Ghi chú: KHÔNG tự ý dùng Regex để tự động dịch nghĩa hoặc viết lại câu. Mọi tác vụ về ngữ nghĩa bắt buộc phải qua quy trình Patch.*
