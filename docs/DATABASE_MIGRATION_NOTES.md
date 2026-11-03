# Database Migration Notes

Tài liệu này ghi chú các phiên bản cơ sở dữ liệu (Database Schema) và các thay đổi tương ứng, đảm bảo an toàn khi cập nhật ứng dụng trên môi trường Production.

## Lịch Sử Phiên Bản

### Version 1 (Sprint 1 & 2)
- Khởi tạo khung database cơ bản.
- Entity: `UserProfile`, `LearningDay`, `Vocabulary`, `GrammarPoint`, `Conversation`, `DayProgress`.
- Tính năng: Học A0 ngày 1-14.

### Version 2 (Sprint 3)
- Entity thêm: `QuizQuestion`, `QuizAttempt`, `SpeakingResult`, `VocabularyReviewProgress`.
- Thay đổi: Hỗ trợ SRS, lưu lịch sử quiz và phát âm.

### Version 3 (Sprint 3 - Fixes)
- Migration: `MIGRATION_2_3`.
- Thay đổi: Bổ sung các cột mới cần thiết cho `DayProgress` (ví dụ: `wrongQuizCount`, `masteryScore`). 
- Lưu ý: Giữ nguyên dữ liệu học tập cũ.

### Version 4 (Sprint 4)
- Migration: `MIGRATION_3_4`.
- Entity thêm: Bài học khóa A1 (155 từ vựng, ngữ pháp, hội thoại).
- Cột thêm: Tối ưu `UserProfile` để theo dõi tiến độ tổng thể.

## Chiến Lược Dev Fallback (Destructive Migration)
- Trong quá trình code (Dev), cờ `DEV_DATABASE_RESET_ENABLED = true/false` trong `AppDatabase` được sử dụng để dọn dẹp DB khi schema thay đổi quá nhiều.
- **Trạng thái Production**: Tính năng `fallbackToDestructiveMigration()` **ĐÃ BỊ TẮT** mặc định trên luồng production. Bất kỳ bản cập nhật nào tăng version đều BẮT BUỘC phải có `Migration` hợp lệ, nếu không app sẽ crash (fail-fast), tránh nguy cơ ngầm xóa dữ liệu của người dùng.

## Checklist Trước Khi Release (DB)
1. Kiểm tra `AppDatabase.VERSION`: Có tăng đúng chưa?
2. Có file Migration mới tương ứng chưa? (vd: v4 -> v5 thì cần `MIGRATION_4_5`).
3. Đã update mảng `DatabaseMigrations.MIGRATIONS` chưa?
4. ContentSeedMeta: Chắc chắn cơ chế seed data (nạp từ vựng) không bị trùng lặp nếu user cập nhật app.
5. Chạy test `MigrationTest` trong thư mục `androidTest` để đảm bảo lệnh SQL hợp lệ.
