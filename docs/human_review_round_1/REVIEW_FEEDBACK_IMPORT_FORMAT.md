# REVIEW FEEDBACK IMPORT FORMAT

Tài liệu định nghĩa cấu trúc dữ liệu để hệ thống đọc (parse) file phản hồi của Reviewer. File chuẩn được hỗ trợ là **CSV**.

## Định dạng cột (Header)
File CSV phải bắt đầu bằng dòng tiêu đề sau:
`reviewId,decision,correctedText,correctedLevel,reason,confidence,reviewerName,reviewedAt,notes`

## Chi tiết các trường
1. **reviewId** (Bắt buộc): Mã duy nhất của Review Item (VD: `REV-MED-001`).
2. **decision** (Bắt buộc): Quyết định của reviewer. Phải là một trong: `OK, FIX, UNSURE, MOVE_LEVEL, REWRITE, REMOVE`.
3. **correctedText** (Tùy chọn/Bắt buộc tùy decision): Nội dung tiếng Anh mới muốn thay thế. Bắt buộc nếu decision là `FIX` hoặc `REWRITE`.
4. **correctedLevel** (Tùy chọn/Bắt buộc tùy decision): Cấp độ mới. Bắt buộc nếu decision là `MOVE_LEVEL`.
5. **reason** (Bắt buộc với đa số thao tác): Lý do tại sao sửa. Bắt buộc nếu quyết định thay đổi dữ liệu (`FIX, MOVE_LEVEL, REWRITE, REMOVE`).
6. **confidence** (Tùy chọn): Độ tự tin `HIGH, MEDIUM, LOW`. Nếu trống, mặc định `MEDIUM`.
7. **reviewerName** (Tùy chọn): Tên người review.
8. **reviewedAt** (Tùy chọn): Timestamp lúc review (Unix Time hoặc chuỗi ngày).
9. **notes** (Tùy chọn): Ghi chú tự do của reviewer (dành cho `UNSURE` hoặc thảo luận thêm).

## Quy trình Validate
- `HumanReviewFeedbackParser`: Chuyển chuỗi CSV thành object Java.
- `HumanReviewFeedbackValidator`: Kiểm tra xem các trường có tuân thủ `REVIEW_DECISION_RULES` hay không, trả về danh sách `HumanReviewFeedbackValidationResult`.
