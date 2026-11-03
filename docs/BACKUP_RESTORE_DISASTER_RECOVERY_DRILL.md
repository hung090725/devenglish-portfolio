# Diễn Tập Phục Hồi Thảm Họa (Disaster Recovery Drill)

## Kịch bản: Người dùng bị mất máy hoặc đổi máy mới

1. **Backup Data**: Người dùng đã xuất file `EnglishPath_Backup.json` lưu trên Google Drive từ máy cũ.
2. **Khởi tạo máy mới**: Cài đặt app bản `1.2.17-beta` (hoặc mới hơn). App khởi tạo Seed A0-C1 sạch. Progress = 0.
3. **Thực hiện Restore**:
   - Chọn Import Backup.
   - Trình phân tích kiểm tra định dạng JSON.
   - Nếu hợp lệ: Cảnh báo "Việc này sẽ ghi đè progress hiện tại. Bạn có chắc chắn?".
   - Chọn "Đồng ý".
   - App đọc dữ liệu UserProfile, DayProgress, LessonProgress, VocabularyDao (chỉ ghi nhận từ vựng đã học), GrammarProgress.
4. **Kết quả kỳ vọng**: Toàn bộ chuỗi ngày học (Streak), điểm kinh nghiệm (XP) và bài đã mở khóa được giữ nguyên. App không bị crash.

## Kịch bản: Restore bị gián đoạn (App crash giữa chừng)
- *Phân tích*: Việc Import được bao bọc trong một `@Transaction`. Nếu lỗi xảy ra giữa chừng, Room DB sẽ tự động Rollback lại trạng thái trước khi Import, đảm bảo người dùng không bị kẹt ở trạng thái "nửa nạc nửa mỡ".
