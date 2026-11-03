# REVIEW DECISION GUIDE

Hướng dẫn cách đưa ra quyết định (Decision) khi review một nội dung.

## 1. OK (Giữ nguyên)
- Dùng khi: Nội dung hoàn toàn chính xác, tự nhiên, và đúng với cấp độ CEFR đã gán.
- Yêu cầu đi kèm: Không cần thiết. (Có thể để trống `correctedText` và `reason`).

## 2. FIX (Sửa lỗi nhỏ)
- Dùng khi: Có lỗi ngữ pháp, lỗi dùng từ, lỗi chính tả, hoặc câu cú thiếu tự nhiên nhưng có thể sửa nhanh bằng cách đổi vài chữ.
- Yêu cầu đi kèm: BẮT BUỘC phải điền vào ô `correctedText` và `reason`.

## 3. UNSURE (Không chắc chắn)
- Dùng khi: Bạn cảm thấy có vấn đề nhưng không biết sửa thế nào cho đúng, hoặc cần thêm ý kiến từ người thứ 2.
- Yêu cầu đi kèm: Nên ghi chú lại ở ô `notes` lý do tại sao bạn không chắc.

## 4. MOVE_LEVEL (Đổi cấp độ)
- Dùng khi: Nội dung tiếng Anh đúng, nhưng bị xếp vào sai cấp độ (VD: từ vựng C1 lại nằm ở A1).
- Yêu cầu đi kèm: BẮT BUỘC phải điền cấp độ CEFR mới vào ô `correctedLevel` (A0, A1, A2, B1, B2, C1).

## 5. REWRITE (Viết lại)
- Dùng khi: Nguyên một đoạn văn quá tệ, sai ngữ pháp trầm trọng hoặc quá khó so với cấp độ. Cần phải viết lại toàn bộ.
- Yêu cầu đi kèm: BẮT BUỘC phải điền nội dung mới vào `correctedText`.

## 6. REMOVE (Xóa bỏ)
- Dùng khi: Nội dung nhạy cảm, không phù hợp, sai kiến thức cơ bản, hoặc trùng lặp hoàn toàn với nội dung khác mà không cần thiết tồn tại.
- Yêu cầu đi kèm: BẮT BUỘC điền `reason`.
