# Báo Cáo Xử Lý Lỗi Khi Restore (Restore Failure Handling Report)

## Các lỗi đã được xử lý (Handled Exceptions)

1. **JSON Invalid**: File không phải dạng JSON (ví dụ file `.txt` nội dung rác).
   - Xử lý: `JsonSyntaxException` được catch. Báo Toast: "File sao lưu không hợp lệ hoặc bị hỏng."
2. **Missing Fields**: File JSON thiếu object `UserProfile` hoặc `DayProgressList`.
   - Xử lý: Kiểm tra Null. Báo Toast: "File sao lưu thiếu dữ liệu quan trọng."
3. **Duplicate Progress**: File JSON chứa các ID tiến độ trùng lặp.
   - Xử lý: Room `@Insert(onConflict = OnConflictStrategy.REPLACE)` giải quyết triệt để vấn đề ghi đè đúp.
4. **User Cancel**: Người dùng chọn file xong nhưng ấn "Hủy" ở hộp thoại xác nhận.
   - Xử lý: Thoát êm ái, không thay đổi DB.

Kết luận: Chức năng Khôi phục Dữ liệu được bảo vệ nghiêm ngặt.
