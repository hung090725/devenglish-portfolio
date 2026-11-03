# Beta Feedback Triage

Danh sách các phản hồi nhận được từ quá trình Beta Test trên thiết bị thật, cùng phân loại mức độ và trạng thái xử lý.

| ID | Màn Hình | Mức Độ | Lỗi / Mô tả chi tiết | Nguyên nhân dự đoán | Trạng Thái | Test lại |
|---|---|---|---|---|---|---|
| BF-001 | Splash / Khởi động | Critical | Ứng dụng văng khi mở lần đầu sau khi cài đặt. | DB chưa kịp tạo xong hoặc query khi DB rỗng. | Đang sửa | Cài mới hoàn toàn và mở app. |
| BF-002 | LessonDayActivity | High | Văng app khi nhấn "Học Tiếp" nhưng thiếu day_index. | Lỗi Intent bị thiếu extras mặc định (-1). | Đang sửa | Thử intent thiếu data. |
| BF-003 | Profile (Export) | Medium | Nhấn "App Diagnostics" thì app văng. | `profileDao().getUserProfileSync()` trả về null do chưa có profile. | Đang sửa | Mở profile lúc DB mới khởi tạo. |
| BF-004 | Vocabulary Detail | High | Nhấn nghe phát âm nhưng không có tiếng. | TTS engine chưa tải xong ngôn ngữ hoặc lỗi khởi tạo. | Đang sửa | Tắt mạng, xóa cache TTS và thử. |
| BF-005 | Speaking Practice | High | Crash khi từ chối quyền Micro. | `SpeechRecognitionManager` không bắt lỗi `SecurityException`. | Đang sửa | Từ chối quyền và nhấn thu âm. |
| BF-006 | Notification | Medium | Reminder lặp lại vô hạn hoặc crash (Android 13). | Đặt lịch khi chưa được cấp quyền `POST_NOTIFICATIONS`. | Đang sửa | Từ chối quyền và bật Reminder. |
| BF-007 | Vocab Review | Medium | Văng app khi vào mục ôn tập nhưng chưa có từ nào tới hạn. | Lỗi filter List rỗng trong `SrsScheduler`. | Đang sửa | Mở màn ôn tập khi chưa học gì. |
| BF-008 | Quiz Result | Medium | Văng app khi nộp bài Quiz do thiếu dữ liệu Attempt. | Thiếu check Intent `attempt_id` hoặc List rỗng. | Đang sửa | Trực tiếp mở QuizResultActivity. |
| BF-009 | Home | Low | Nút "Học Tiếp" không nổi bật, text bị cắt ở màn nhỏ. | XML layout chưa dùng `autoSizeTextType`. | Đang sửa | Chạy trên máy màn hình nhỏ. |
| BF-010 | Profile | Low | Xin quyền Micro nhiều lần không có giải thích. | `PermissionHelper` chưa có dialog giải thích và mở Settings. | Đang sửa | Từ chối quyền 2 lần. |
