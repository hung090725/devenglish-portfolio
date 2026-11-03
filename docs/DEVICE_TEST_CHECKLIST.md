# Device Test Checklist - Sprint 9 All-In-One (A2 + Closed Testing)

## 0. Thông tin thiết bị Test (BẮT BUỘC ĐIỀN)
- **Tên Máy / Model**: ______________________
- **Phiên bản Android**: ______________________
- **TTS Engine đang dùng** (Google/Samsung/v.v.): ______________________

## 1. Cài đặt & Khởi động
- [ ] Cài app bản Beta (Clean install).
- [ ] Mở app lần đầu: Kiểm tra màn hình Splash (không crash).
- [ ] Kiểm tra LevelTest: Chọn trình độ A0 -> Vào màn hình chính.
- [ ] Từ chối quyền Microphone: Ứng dụng KHÔNG crash, hiện thông báo rõ ràng.
- [ ] Từ chối quyền Notification: Ứng dụng KHÔNG crash.

## 2. Test Lộ trình Học (A0, A1, A2)
- [ ] Kiểm tra HomeFragment: Có thanh progress tính tổng tiến trình của A0, A1, A2 (42 ngày).
- [ ] Vào LessonsFragment: Kiểm tra chip A0, A1, A2 hiển thị đúng khóa.
- [ ] Unlock logic: Khóa A1 bị lock đến khi hoàn thành A0 ngày 14. Khóa A2 bị lock đến khi hoàn thành A1 ngày 28.
- [ ] Mở Ngày 1 (A0). Test nút "Luyện nói", cấp quyền Micro -> Đọc câu tiếng Anh -> Hiển thị điểm số.
- [ ] Thử không cấp quyền Micro -> Nhấn lại nút "Luyện nói" -> Yêu cầu mở Cài đặt.
- [ ] Test tính năng Text-to-Speech (TTS): Nhấn nút loa nghe phát âm và đoạn hội thoại.
- [ ] Làm bài Quiz: Chọn đúng sai, nộp bài, xem giải thích.
- [ ] Nhấn "Học Tiếp" từ trang chủ: Ứng dụng mở đúng bài học đang dở dang. Nếu hoàn thành A1, continue day nhảy sang A2 Ngày 1.

## 3. Test Hệ thống ôn tập (SRS)
- [ ] Mở tab "Ôn Tập" (Vocabulary Review).
- [ ] Nếu rỗng: Hiện thông báo "Không có từ trong bộ lọc này", KHÔNG crash.
- [ ] Filter từ vựng: Test bộ lọc (All, Due, Weak). Có chứa từ vựng A2 nếu học đến.

## 4. Test Nhắc học (Reminder)
- [ ] Mở Profile -> Cài đặt nhắc học.
- [ ] Bật "Nhắc học": Nếu Android 13+, kiểm tra hộp thoại xin quyền `POST_NOTIFICATIONS`.
- [ ] Từ chối quyền Notification: Xem app có crash không (phải không crash).
- [ ] Cho phép quyền -> Chọn một giờ -> Đợi thông báo (hoặc đổi giờ tới 1 phút tiếp theo).

## 5. UI Polish & Trải nghiệm
- [ ] ProfileFragment hiển thị đầy đủ tiến độ phần trăm cho A0, A1 và A2.
- [ ] Nút "Học Tiếp", "Luyện Nói", "Ôn Từ Vựng" ở màn hình chính hiển thị rõ ràng, text không bị cắt.
- [ ] Test phím Back: Trở về màn hình trước mượt mà.

## 6. Export Diagnostics & Beta Feedback
- [ ] Vào Profile -> Nhấn "Gửi Phản Hồi": Gửi một lỗi thử nghiệm.
- [ ] Vào Profile -> Nhấn "App Diagnostics": Kiểm tra hộp thoại JSON không crash (chứa device info, DB version).
- [ ] Nút "Copy" Diagnostics hoạt động.

## 7. Google Play Console Closed Testing (Chuẩn Bị)
- [ ] Bundle đã sinh file AAB hợp lệ (`bundleRelease`).
- [ ] Mở Play Console -> Tạo App -> Hoàn thành danh sách khai báo.
- [ ] Khai báo quyền: RECORD_AUDIO, POST_NOTIFICATIONS, INTERNET.
- [ ] Điền thông tin Data Safety (Audio record - not shared, app progress - local).
- [ ] Cập nhật 20 testers email vào Closed Testing track.
- [ ] Tạo release trên track Closed Testing (Alpha).
- [ ] Chọn AAB mới nhất, điền release notes (Sprint 9: Mở khóa A2, 42 ngày học cơ bản).
- [ ] Bấm Save -> Gửi đi review (Thường tốn vài giờ -> 7 ngày).
