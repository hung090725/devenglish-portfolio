# CONTENT CORRECTION PATCH FORMAT

Để sửa đổi nội dung một cách an toàn mà không cần can thiệp trực tiếp vào file Java chứa Seed Data, chúng ta sử dụng cơ chế Patch. 
Mỗi Patch đại diện cho một thay đổi cục bộ đối với 1 field của 1 item.

## 1. Cấu trúc của Patch (ContentCorrectionPatch)

Một đối tượng Patch bao gồm các thuộc tính sau:
- `patchId`: String (ID duy nhất của patch, vd: UUID)
- `type`: String (Loại nội dung: VOCABULARY, GRAMMAR, QUIZ, CONVERSATION, READING, WRITING)
- `level`: String (Cấp độ CEFR)
- `sourceId`: String (ID gốc trong cơ sở dữ liệu hoặc key)
- `fieldName`: String (Tên biến cần sửa, vd: "meaningVi", "correctAnswer")
- `oldValue`: String (Giá trị hiện tại, để Validate xem có bị out-of-sync không)
- `newValue`: String (Giá trị mới)
- `reason`: String (Lý do sửa)
- `confidence`: String (`HIGH` / `MEDIUM` / `LOW`)
- `reviewer`: String (Tên người review)
- `createdAt`: long (Timestamp)
- `status`: String (`DRAFT` / `VALIDATED` / `APPLIED` / `REJECTED` / `NEEDS_REVIEW`)

## 2. Quy tắc Validation (PatchValidator)
Hệ thống sẽ từ chối Apply Patch nếu vi phạm các luật sau:
1. `oldValue` không khớp với dữ liệu thực tế đang có trong RAM/DB.
2. `newValue` bị rỗng (đối với các trường bắt buộc như `word`, `correctAnswer`, `question`).
3. `confidence` là LOW nhưng trạng thái không phải `NEEDS_REVIEW`.
4. `correctAnswer` của Quiz không nằm trong dải [A, B, C, D].
5. `level` không thuộc [A0, A1, A2, B1, B2, C1].

## 3. Quy trình áp dụng (PatchApplier)
1. **Load Patches**: Đọc file chứa danh sách patches.
2. **Validate**: Chạy qua `ContentCorrectionPatchValidator`.
3. **Apply**: Tạo ra một List các Item đã được cập nhật.
4. **Report**: Sinh báo cáo (PatchResult) với tổng số patches được áp dụng thành công và số patches bị từ chối kèm lý do.
