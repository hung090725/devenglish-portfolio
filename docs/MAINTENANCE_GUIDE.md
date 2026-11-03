# MAINTENANCE GUIDE

Tài liệu này cung cấp hướng dẫn bảo trì dài hạn cho dự án EnglishPath AI (No-Backend, No-Play Console).

## 1. Cấu trúc Project
- `app/src/main/java/.../ui`: Chứa toàn bộ giao diện (Activity/Fragment) phân theo tính năng.
- `app/src/main/java/.../data`: Chứa local DB (Room), Dao, Entities và Repository.
- `app/src/main/java/.../domain`: Chứa logic nghiệp vụ, Constants (VD: LevelConstants).
- `docs/`: Chứa toàn bộ tài liệu dự án, QA reports và auditing.
- `tools/qa/`: Chứa các script chạy kiểm thử nội bộ.

## 2. Các Package Chính
- `ui.home`: Màn hình chính, tổng hợp tiến trình.
- `ui.practice`: Practice Center (Kỹ năng, Công cụ, Ôn tập).
- `ui.lessondetail` & `ui.lessons`: Hệ thống bài học cốt lõi (A0 - C1).
- `data.local.seed`: Dữ liệu bài học nạp vào DB lần đầu.

## 3. Cách thêm Level mới
1. Cập nhật `LevelConstants.java`.
2. Tạo file Seed mới trong `data/local/seed/` (VD: `C2LessonSeed.java`).
3. Cập nhật `SeedDataProvider.java` để nạp list bài học mới vào DB.
4. Cập nhật giao diện `HomeFragment` và `LessonsFragment` để hiển thị Level mới.

## 4. Cách thêm Vocabulary / Grammar / Quiz
- Từ vựng: Thêm vào file `[Level]VocabSeedHelper.java`.
- Ngữ pháp: Thêm vào file `[Level]GrammarSeed.java`.
- Quiz: Thêm vào `[Level]QuizSeed.java`.

## 5. Cách thêm Migration Room
Khi thay đổi Entity, phải:
1. Tăng `version` trong `@Database(entities = {...}, version = X)`.
2. Thêm file/biến `Migration` tương ứng trong `DatabaseMigrations.java`.
3. Cập nhật `AppDatabase.java` bằng cách `.addMigrations(...)`.

## 6. Cách chạy QA Gate
- Chạy file: `tools\qa\run_solo_qa_gate.bat`.
- Quá trình này sẽ thực thi: clean, assembleDebug, lint, unit test, UI test compile và assembleRelease.

## 7. Cách Build APK / AAB
- **APK Debug:** `.\gradlew assembleDebug` (tạo ra `/app/build/outputs/apk/debug/app-debug.apk`).
- **APK Release:** `.\gradlew assembleRelease`
- **AAB Release:** `.\gradlew bundleRelease`

## 8. Backup / Restore Test
- App hỗ trợ xuất dữ liệu ra JSON. Luôn test tính năng này sau mỗi lần nâng cấp Room Database để đảm bảo Backward Compatibility (Người dùng không mất dữ liệu).

## 9. Đọc Diagnostics
- App có màn hình Diagnostics (Chỉ hiện ở bản Debug). Nếu có lỗi, truy cập màn hình này để xuất file JSON chứa log/exception gần nhất.

## 10. Sửa lỗi thường gặp
- **Cannot resolve R:** Xóa cache IDE (File > Invalidate Caches) hoặc Sync lại Gradle. Đảm bảo cấu hình JDK đúng (trỏ vào `jbr` của Android Studio).
- **Lỗi Migration Room:** Database bị wipe nếu thiếu Migration. Cần thêm Migration script đúng chuẩn.
- **Crash khi thêm Level mới:** Có thể quên khai báo tổng số ngày (Day) của Level trong `LevelConstants`.

## 11. Các nguyên tắc cấm kỵ (Red Lines)
1. **KHÔNG** upload file Keystore (`.jks` / `.keystore`) lên Github hoặc tài liệu.
2. **KHÔNG** thay đổi `applicationId` (hung.edu.apptienganh) - việc này sẽ khiến bản cập nhật cài thành 1 app hoàn toàn mới, mất sạch dữ liệu của người dùng cũ.
3. Luôn kiểm tra Checklist trước khi Commit mã nguồn.
