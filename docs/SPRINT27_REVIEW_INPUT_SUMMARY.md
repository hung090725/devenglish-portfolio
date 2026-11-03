# SPRINT 27: REVIEW INPUT SUMMARY

Báo cáo này tổng hợp dữ liệu đầu vào từ các báo cáo TODO trước khi bắt đầu thực hiện Controlled Content Correction Pass trong Sprint 27.

## 1. Tổng số TODO
**Tổng cộng:** 6 items (dựa trên `CONTENT_REVIEW_TODO.md` và `CONTENT_REVIEW_TODO_TRIAGE.md`).

## 2. Số lượng theo Severity
- **CRITICAL**: 0
- **HIGH**: 1 (Từ vựng "serendipity" gán sai cấp độ A1).
- **MEDIUM**: 3 ("make" ở B2, câu hỏi Quiz A0 rườm rà, Reading C1 khó tương đương C2).
- **LOW**: 2 (Conversation "How do you do" hơi máy móc, Grammar ví dụ dài).

## 3. Số lượng theo Type
- **Vocabulary**: 2
- **IPA**: 0
- **Grammar**: 1
- **Conversation**: 1
- **Quiz**: 1
- **Reading**: 1
- **Writing / Listening / Speaking**: 0
- **CEFR Level**: (Các lỗi Vocab và Reading trên đều liên quan tới CEFR Level, tổng 3).

## 4. Những mục có thể sửa chắc chắn (High Confidence)
Dựa trên chính sách an toàn, hệ thống có thể tự tin sửa:
1. `Vocab-101-serendipity`: Chuyển level từ A1 sang C1 (Vì từ này rõ ràng là từ vựng nâng cao hiếm gặp).
2. `Quiz-A0-5`: Thay thế "Which of the following..." bằng "What is..." để phù hợp trình độ A0.
3. Bất kỳ placeholder TODO/N/A nào nếu tìm thấy thêm trong database.

## 5. Những mục cần giữ lại review thủ công (Needs Human Review)
1. `Vocab-34-make`: Chưa chắc nên thay bằng "manufacture" hay từ nào khác hợp ngữ cảnh cụ thể của bài học.
2. `Conv-12-Meeting`: "How do you do?" tuy máy móc nhưng nếu đây là bài học về Formal English thì vẫn đúng. Cần chuyên gia xem bài.
3. `Gram-B2-Inversion`: Cắt bớt câu ví dụ như thế nào cho hay, cần người thật làm.
4. `Reading-C1-AI`: Cấu trúc câu ghép nào nên đơn giản hóa? Cần người thật làm.

## 6. Những mục KHÔNG ĐƯỢC tự sửa
- KHÔNG tự sửa IPA (vì không có lỗi IPA rõ ràng trong danh sách, và bot không tự bịa IPA).
- KHÔNG tự viết lại cả đoạn Reading (tránh sai nghĩa gốc).
