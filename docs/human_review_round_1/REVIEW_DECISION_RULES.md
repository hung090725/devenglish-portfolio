# REVIEW DECISION RULES

Các quy tắc kỹ thuật để Validator của hệ thống quyết định có chấp nhận Feedback từ Reviewer hay không.

## 1. Các Quyết Định Hợp Lệ (Valid Decisions)
1. `OK`: Nội dung đúng, giữ nguyên.
2. `FIX`: Có lỗi rõ và cung cấp `correctedText`.
3. `UNSURE`: Reviewer không chắc.
4. `MOVE_LEVEL`: Đúng tiếng Anh nhưng sai trình độ, cần cung cấp `correctedLevel`.
5. `REWRITE`: Nội dung cần viết lại nhiều, cần cung cấp `correctedText`.
6. `REMOVE`: Nội dung không phù hợp hoặc quá rủi ro, cần cung cấp `reason`.

## 2. Các Trường Hợp Từ Chối Áp Dụng (Do Not Apply)
Hệ thống Validator sẽ **BÁO LỖI** và **TỪ CHỐI** áp dụng Patch nếu vi phạm các luật sau:
- `reviewerDecision` bị bỏ trống.
- `reviewerConfidence` đánh dấu là `LOW` (Không áp dụng thay đổi nếu thiếu tự tin).
- `correctedText` rỗng khi decision là `FIX` hoặc `REWRITE`.
- `oldValue` (Nội dung gốc trong DB) không khớp với dữ liệu hiện tại (Có ai đó đã sửa nó trước đó).
- `correctedText` làm cho một field bắt buộc (như `question`, `correctAnswer`) trở thành rỗng.
- Đối với Quiz, `correctAnswer` (nếu sửa) phải nằm trong khoảng A, B, C, D (In hoa).
- Đối với Level, `correctedLevel` (nếu có) phải nằm trong khoảng A0, A1, A2, B1, B2, C1.
- Không áp dụng tự sinh IPA nếu không có nguồn chắc chắn đi kèm.
