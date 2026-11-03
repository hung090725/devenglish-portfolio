# Beta Readiness Checklist - Sprint 6B

Tài liệu này là kịch bản kiểm thử đầy đủ dành cho Tester trước khi release bản Beta. Hãy đánh dấu [x] sau khi pass.

## 1. Build & Khởi động (First launch)
- [ ] Lệnh `assembleDebug` thành công.
- [ ] Lệnh `testDebugUnitTest` thành công.
- [ ] Lệnh `lintDebug` không có errors.
- [ ] Mở app lần đầu: Splash hiển thị mượt, tự động chuyển sang Onboarding.
- [ ] Onboarding hiển thị rõ ràng -> Qua màn hình Level Test.
- [ ] Chọn A0 ở Level Test -> Vào HomeFragment thành công.
- [ ] Đóng app hẳn (Kill) -> Mở lại -> Bỏ qua Onboarding, nhảy thẳng vào Home.

## 2. Core Learning
- [ ] A0 Ngày 1 hiển thị chữ "In Progress" (hoặc đang mở).
- [ ] Các ngày khác của A0 bị khóa. Nhấn vào bị từ chối truy cập.
- [ ] Tab A1 bị khóa hoàn toàn.
- [ ] Học hoàn tất A0 Ngày 1 (chạy qua Grammar, Vocab, Practice, Quiz -> Nhấn Mark Completed).
- [ ] Ra màn hình Home: A0 Ngày 2 đã được mở khóa.
- [ ] Thử hoàn thành tất cả 14 ngày của A0 (hoặc chỉnh sửa DB debug để mô phỏng): A1 Ngày 1 tự động mở khóa.

## 3. Text-to-Speech (TTS)
- [ ] Bấm nút nghe phát âm UK và US ở chi tiết từ vựng. Cả hai giọng đọc rõ ràng.
- [ ] Bấm nghe nguyên câu tiếng Anh trong phần giao tiếp.
- [ ] (Trường hợp đặc biệt): Máy bị tắt engine TTS của Google, app không crash mà hiện toast báo lỗi hoặc không có phản hồi âm thanh nhưng vẫn chạy tiếp.

## 4. SpeechRecognizer (Microphone)
- [ ] Mở phần luyện nói, bấm nút Micro lần đầu: Hệ thống hỏi quyền Microphone. (Có kèm text giải thích của app).
- [ ] **Từ chối quyền:** App không crash, quay lại màn hình cũ hoặc thông báo chức năng này bị vô hiệu hóa.
- [ ] **Cho phép quyền:** Nhấn lại Micro, giao diện hiển thị trạng thái đang nghe.
- [ ] Đọc đúng câu mẫu: Hiển thị điểm > 80% (tùy độ chuẩn) và tô xanh chữ đúng.
- [ ] Đọc sai hoặc không khớp: Điểm thấp, tô đỏ chữ sai hoặc thiếu.
- [ ] Tắt mạng (Offline): Nhấn Micro, app không crash, có thể hiển thị thông báo "Offline/Không nhận diện được".

## 5. Spaced Repetition System (SRS)
- [ ] Mở Tab "Ôn Tập" (Vocabulary Review).
- [ ] Filter theo A0, A1: Danh sách cập nhật đúng từ vựng thuộc từng Level.
- [ ] Làm bài ôn tập: Thử đánh giá từ vựng bằng các nút `Again, Hard, Good, Easy`.
- [ ] Sau khi ôn, từ vựng biến mất khỏi danh sách (vì Next Review Date đã dời sang tương lai).
- [ ] Đảm bảo không sinh ra các bản ghi (progress) trùng lặp cho cùng một từ.

## 6. Quiz & Speaking History
- [ ] Làm một bài Quiz: Đóng bài giữa chừng, mở lại vẫn hiển thị đúng.
- [ ] Làm xong Quiz: Điểm số được lưu. (Màn hình Home/Profile hiển thị `bestQuizScore` được cập nhật).
- [ ] Màn hình kết quả Quiz hiển thị rõ giải thích Tiếng Việt nếu làm sai.
- [ ] Profile cập nhật lượt luyện nói và hiển thị "Câu điểm thấp gần đây".

## 7. Reminder (Nhắc học)
- [ ] Vào Profile -> Cài đặt nhắc học -> Bật Reminder.
- [ ] (Android 13+): Nếu chưa có quyền Notifications, app hỏi quyền và giải thích ngắn gọn.
- [ ] Chỉnh giờ nhắc học (vd: 1 phút sau thời điểm hiện tại).
- [ ] Kill app. Chờ 1 phút -> Push Notification hiện lên.
- [ ] Tắt nhắc học -> Sẽ không nhận được thông báo nữa.

## 8. Database Migration
- [ ] Cài app version cũ (Sprint 2/3).
- [ ] Update đè APK version mới nhất (Sprint 6B).
- [ ] Mở app: Không bị crash. Tiến trình cũ (số ngày học, điểm) được giữ nguyên.

## 9. Chế độ Offline & Giao diện
- [ ] Chuyển máy bay (Airplane mode) -> Mở app -> Vẫn học bài học ngày cũ bình thường.
- [ ] Xoay màn hình dọc/ngang (ở các màn hình chính): Layout không bị vỡ hoặc che mất nút bấm quan trọng.
- [ ] Mở Dark Mode (nếu OS hỗ trợ): Chữ vẫn đọc được, không bị đen chìm vào nền.
- [ ] Dùng máy màn hình nhỏ (vd 4.7 inch): Text và button quan trọng có thể scroll đến được, không bị tràn.

## 10. Diagnostics
- [ ] Chỉ có ở bản Debug: Bấm Profile -> Nút "App Diagnostics (Tester)".
- [ ] Kiểm tra JSON hiển thị ra (có appVersion, versionCode, databaseVersion, A0 progress, due count...). Dễ dàng copy.
