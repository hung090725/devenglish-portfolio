# SPRINT 27: CORRECTION POLICY ENFORCEMENT

Tài liệu này định nghĩa chặt chẽ những gì ĐƯỢC PHÉP và KHÔNG ĐƯỢC PHÉP tự động sửa (hoặc sửa trực tiếp mà không cần chờ review chuyên gia) trong quá trình Controlled Content Correction Pass.

## 1. Được phép sửa trực tiếp (High Confidence)
Chỉ được tạo Patch và đánh dấu trạng thái `APPLIED` đối với các trường hợp sau:
1. Field bắt buộc bị rỗng và có thể nội suy/suy luận thay thế chắc chắn từ dữ liệu hiện có (vd: thiếu title nhưng có topicName).
2. `correctAnswer` đang là `a, b, c, d` lowercase -> đổi thành `A, B, C, D`.
3. `correctAnswer` ngoài A/B/C/D (vd: "apple") -> Chỉ sửa thành A/B/C/D nếu có thể xác định CHẮC CHẮN đáp án đúng dựa vào các options.
4. Option quiz trùng nhau -> Sửa nếu có option thay thế chắc chắn, nếu không thì đưa vào TODO.
5. Spelling sai rõ (lỗi đánh máy cơ bản tiếng Anh).
6. Dấu câu, viết hoa viết thường (Capitalization) sai rõ.
7. `estimatedMinutes <= 0` -> Đặt mặc định hợp lý (A0-A2: 5 phút, B1-B2: 10 phút, C1: 15 phút).
8. Level string sai chính tả rõ (vd "A 1" -> "A1", "b2" -> "B2").
9. Placeholder `TODO`, `N/A`, `unknown` -> Xóa và để rỗng (hoặc đưa vào TODO), không tự bịa nội dung.

## 2. KHÔNG ĐƯỢC phép sửa trực tiếp (Medium/Low Confidence)
Phải tạo Patch nhưng để trạng thái `NEEDS_REVIEW` hoặc đẩy thẳng vào TODO:
1. IPA chưa chắc (không tự tra từ điển tự động ghép bừa).
2. Nghĩa tiếng Việt của từ (meaningVi) nếu từ có nhiều nghĩa và chưa rõ ngữ cảnh bài học.
3. Câu hội thoại cần viết lại nhiều (naturalness issue).
4. CEFR level chỉ nghi ngờ (Medium/Low confidence).
5. Collocation chưa chắc tự nhiên.
6. Common mistake chưa chắc hữu ích.
7. Writing sample cần chuyên gia rà soát logic.
8. Reading inference question (câu hỏi suy luận) mà đáp án chưa chắc chắn 100%.

> [!WARNING]
> Mọi Correction Patch được sinh ra phải tuân thủ tuyệt đối quy luật này. Nếu vi phạm, có thể làm ô nhiễm dữ liệu chuẩn (Gold Master) của ứng dụng.
