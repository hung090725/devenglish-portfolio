# HUMAN REVIEW RULES (Luật Kiểm Quyệt Nội Dung)

Quy trình Review do con người thực hiện là công cụ quan trọng nhất để EnglishPath AI duy trì độ tin cậy. Dưới đây là các nguyên tắc bất di bất dịch dành cho Reviewer khi thao tác trên Database của App.

## 1. Mục tiêu cốt lõi
- Mục tiêu của quá trình review là **giảm thiểu lỗi tiếng Anh**, nâng cao sự tự nhiên của các câu văn, làm mượt ngữ cảnh dịch thuật và gán nhãn độ khó đúng CEFR.
- **KHÔNG claim (khẳng định) tuyệt đối 100%**: Nội dung do AI sinh ra hoặc do con người nhập liệu luôn có rủi ro sai sót. Chúng ta tiến tới 99% chứ không được phép nói quá về sự hoàn hảo tuyệt đối nếu chưa được ít nhất 2 chuyên gia độc lập kiểm chứng.

## 2. Quy tắc Sửa Đổi (Correction Rules)
- **Không tự bịa IPA:** Nếu không chắc chắn về phát âm chuẩn của từ, không tự ý gõ bừa IPA. Hãy tra từ điển chuẩn (Oxford, Cambridge). Nếu lười, cứ để field IPA rỗng để chờ update sau, chứ không được gõ IPA sai làm hỏng phát âm người học.
- **Không tự sửa nghĩa nếu không chắc:** Nếu thấy từ vựng hoặc đoạn hội thoại có vẻ "kỳ quái", đừng tự động sửa theo ý hiểu nếu bạn không sống trong môi trường văn hóa đó.
- **Giữ OPEN khi chưa rõ:** Nếu bạn không đủ thời gian hoặc kiến thức để xử lý một Warning, hãy để nguyên trạng thái của nó là `OPEN` hoặc `NEEDS_REVIEW` trong file `CONTENT_REVIEW_TODO.md`.

## 3. Khi nào được sửa trực tiếp?
Chỉ được phép trực tiếp sửa đổi (Apply Patch / Update Seed) khi lỗi là RÕ RÀNG VÀ CHẮC CHẮN. Cụ thể:
1. **Spelling sai**: Lỗi chính tả cơ bản (vd: *aple* -> *apple*).
2. **Grammar sai rõ**: Chia sai động từ (vd: *He have* -> *He has*).
3. **CorrectAnswer sai**: Trắc nghiệm chỉ nhầm đáp án (vd: A đúng nhưng lại tick C).
4. **Option quiz trùng**: 2 phương án bị trùng lặp y hệt nhau.
5. **Field rỗng**: Bổ sung nội dung cho một trường bắt buộc bị để trống (vd: *instructionVi*).
6. **Level sai rõ**: Một từ quá học thuật (vd: *ubiquitous*) bị xếp nhầm vào A1.
7. **Dịch tiếng Việt sai rõ**: Dịch máy ngớ ngẩn hoặc sai hoàn toàn ngữ cảnh (vd: *bark* là "vỏ cây" nhưng lại dịch thành "sủa" trong ngữ cảnh thực vật học).

## 4. Cấu trúc một Correction (Patch Format)
Mọi chỉnh sửa thủ công nếu ghi nhận lại vào sổ lỗi đều phải đảm bảo lưu trữ 6 thông tin sau để truy vết (Traceability):
1. **Original text**: Đoạn text gốc bị lỗi.
2. **Corrected text**: Đoạn text sau khi sửa.
3. **Reason**: Lý do sửa ngắn gọn (vd: "Sai chính tả", "Level A1 không dùng thì HTHT").
4. **Confidence**: `HIGH` / `MEDIUM` / `LOW`
5. **Status**: `APPLIED` (nếu đã sửa vào DB/Seed) / `NEEDS_REVIEW` (nếu cần xem thêm) / `REJECTED` (không sửa).

> [!WARNING]
> Mọi vi phạm nguyên tắc trên đều có thể làm hỏng logic của Strict Content Gate hoặc khiến người dùng học sai kiến thức. Hãy tuân thủ nghiêm ngặt!
