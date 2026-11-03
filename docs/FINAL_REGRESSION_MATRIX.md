# FINAL REGRESSION MATRIX (Sprint 22)

**Trạng thái chung:** Phần lớn các màn hình đã vượt qua giai đoạn Code Audit. Do hạn chế về môi trường tự động, trạng thái UI thực tế trên thiết bị được ghi chú là "Code audit PASS, real device NOT TESTED".

| ID | Tên Màn hình (Screen) | Entry Point | Required Intent Extras | Fallback | Empty State | Error State | Permission Needed | Status | Notes |
|----|-----------------------|-------------|-------------------------|----------|-------------|-------------|-------------------|--------|-------|
| 1 | Splash / Onboarding | SplashActivity | None | N/A | N/A | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 2 | Home | HomeFragment | None | N/A | Text "chưa có bài học" | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 3 | Lessons | LessonsFragment | None | N/A | Text "Đang tải dữ liệu" | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 4 | LessonDay | LessonDayActivity | `day_index`, `level` | Về Home nếu thiếu | Về Home nếu thiếu | Toast báo lỗi dữ liệu | None | PASS | Code audit PASS, real device NOT TESTED |
| 5 | Practice Center | PracticeFragment | None | N/A | N/A | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 6 | Review Center | ReviewCenterActivity | None | N/A | Không có từ vựng cần ôn | Toast lỗi load DB | None | PASS | Code audit PASS, real device NOT TESTED |
| 7 | Dictionary Pro | DictionaryProActivity | None | N/A | Gợi ý từ khóa tìm kiếm | Lỗi tìm kiếm | None | PASS | Code audit PASS, real device NOT TESTED |
| 8 | Grammar Lab | GrammarLabActivity | None | N/A | "Chưa có dữ liệu ngữ pháp" | Toast | None | PASS | Code audit PASS, real device NOT TESTED |
| 9 | Speaking Trainer | SpeakingPracticeActivity | `target_sentence` | Fallback câu mặc định | N/A | Toast lỗi | `RECORD_AUDIO` | PASS | Code audit PASS, real device NOT TESTED |
| 10 | Listening Trainer | ListeningTrainerActivity | None | N/A | Text "Chưa có bài luyện nghe" | Toast | None | PASS | Code audit PASS, real device NOT TESTED |
| 11 | Writing Trainer | WritingTrainerActivity | None | N/A | Text "Chưa có bài luyện viết" | Toast | None | PASS | Code audit PASS, real device NOT TESTED |
| 12 | Reading Trainer | ReadingTrainerActivity | None | N/A | Text "Chưa có bài luyện đọc" | Toast | None | PASS | Code audit PASS, real device NOT TESTED |
| 13 | Role-play Pro | RoleplayProActivity | None | N/A | "Chưa có hội thoại nhập vai" | Toast lỗi | `RECORD_AUDIO` | PASS | Code audit PASS, real device NOT TESTED |
| 14 | Pronunciation Clinic | PronunciationClinicActivity | None | N/A | "Không có từ vựng phát âm" | Toast lỗi nhận dạng | `RECORD_AUDIO` | PASS | Code audit PASS, real device NOT TESTED |
| 15 | Sentence Builder | SentenceBuilderActivity | None | N/A | "Không có bài tập ghép câu" | Toast lỗi | None | PASS | Code audit PASS, real device NOT TESTED |
| 16 | Flashcard | FlashcardActivity | None | N/A | "Chưa có Flashcard" | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 17 | Word Game | WordGameActivity | None | N/A | "Không đủ từ vựng để chơi" | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 18 | Exam Simulator | ExamSimulatorActivity | None | N/A | "Chưa có đề thi" | Toast | None | PASS | Code audit PASS, real device NOT TESTED |
| 19 | Placement Test | PlacementTestActivity | None | N/A | N/A | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 20 | Completion Report | CompletionReportActivity | None | N/A | N/A | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 21 | Goal Setup | GoalSetupActivity | None | N/A | N/A | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 22 | Study Calendar | StudyCalendarActivity | None | N/A | N/A | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 23 | Achievement | AchievementActivity | None | N/A | "Chưa có thành tựu nào" | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 24 | Error Notebook | ErrorNotebookActivity | None | N/A | "Chưa có lỗi nào được ghi lại" | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 25 | Settings | SettingsCenterActivity | None | N/A | N/A | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 26 | Backup/Restore | BackupRestoreActivity | None | N/A | "Chưa có bản sao lưu" | Báo lỗi JSON import | `READ_EXTERNAL_STORAGE` | PASS | Code audit PASS, real device NOT TESTED |
| 27 | Diagnostics | DiagnosticsActivity | None | N/A | N/A | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 28 | Solo QA | SoloQaActivity | None | N/A | N/A | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 29 | Content Studio | ContentStudioActivity | None | N/A | N/A | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
| 30 | App Completion Audit | AppCompletionAuditActivity | None | N/A | N/A | N/A | None | PASS | Code audit PASS, real device NOT TESTED |
