# HUMAN REVIEW ROUND 1 - HƯỚNG DẪN REVIEWER

Cảm ơn bạn đã tham gia Review nội dung tiếng Anh cho ứng dụng EnglishPath AI.

## Vai trò của bạn
* Người review chỉ kiểm tra tiếng Anh, ý nghĩa và trình độ (CEFR).
* **Không cần viết code.**
* **Không sửa trực tiếp vào mã nguồn.**
* Bạn chỉ cần điền vào `REVIEW_FEEDBACK_FORM_ROUND_1.csv` (hoặc `.md`).

## Các trạng thái (Decision) bạn có thể đưa ra:
1. **OK**: Nội dung hiện tại đã đúng ngữ pháp, tự nhiên và phù hợp với cấp độ. Giữ nguyên.
2. **FIX**: Có lỗi rõ ràng. Đề xuất sửa đổi ngắn ở mục `correctedText`.
3. **UNSURE**: Bạn không chắc chắn. Hệ thống sẽ giữ nguyên và đợi ý kiến từ Reviewer khác.
4. **MOVE_LEVEL**: Ngôn ngữ đúng, nhưng đang nằm sai cấp độ CEFR. (Ví dụ: Từ B2 bị xếp nhầm vào A1). Hãy điền cấp độ đúng vào form.
5. **REWRITE**: Nội dung quá tệ, cần viết lại hoàn toàn. (Cung cấp nội dung mới ở `correctedText`).
6. **REMOVE**: Nội dung không phù hợp, nhạy cảm hoặc sai quá nhiều, nên xóa bỏ.

> [!NOTE]
> Nếu không chắc chắn, hãy cứ chọn UNSURE. Đừng cố gắng "đoán" đáp án. Sự an toàn của dữ liệu là ưu tiên hàng đầu.
