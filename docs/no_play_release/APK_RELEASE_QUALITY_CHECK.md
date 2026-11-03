# Checklist Chất Lượng APK Trước Khi Phát Hành Nội Bộ

Để đảm bảo phiên bản APK phát hành ngoài Play Store hoạt động mượt mà và không gây gián đoạn trải nghiệm người dùng, Developer (Solo QA) cần kiểm tra các mục sau trước khi gửi file `app-release.apk` cho Tester:

## 1. Kiểm tra File và Version
- [ ] `versionName` trong file `build.gradle` chính xác (vd: 0.9.3-beta).
- [ ] `versionCode` đã được tăng lên so với bản trước.
- [ ] Tồn tại file `app/build/outputs/apk/release/app-release.apk`.
- [ ] Tồn tại file `app/build/outputs/apk/debug/app-debug.apk`.
- [ ] Tồn tại file `app/build/outputs/bundle/release/app-release.aab`.

## 2. Kiểm tra Cài đặt (Installation)
- [ ] File APK cài đặt thành công trên điện thoại Android thật.
- [ ] Màn hình First Launch không bị crash khi chưa có dữ liệu.

## 3. Solo QA Dashboard
- [ ] Vào màn hình Solo QA (bản debug/tester).
- [ ] Cả 4 bài test nội bộ (Content, Progress, Backup, Reminder) đều PASS xanh.

## 4. Tính Năng Cốt Lõi
- [ ] **Backup & Restore**: Export thành công JSON từ bản cũ, gỡ app, cài bản mới, Import lại khôi phục đúng tiến độ.
- [ ] **Microphone & TTS**: Mở màn hình Luyện Nói (Speaking/Vocab), Micro thu âm tốt, TTS phát âm chuẩn.
- [ ] **Quyền truy cập (Permissions)**: Cố tình từ chối quyền Micro (Don't ask again), app hiển thị hướng dẫn vào Settings đúng như thiết kế.
- [ ] **Quiz & SRS**: Hoàn thành một bài học (Ngày 1), điểm số lưu lại, và gợi ý ôn tập (SRS) hoạt động không lỗi.

## 5. Cảnh Báo An Toàn
- [ ] Tuyệt đối **Không gửi file `.jks` (Keystore)** hoặc password đi kèm.
- [ ] Không đăng tải công khai file APK lên group/diễn đàn khi ứng dụng chưa hoàn thiện 100% hoặc chưa có cơ chế bảo mật backend.
- [ ] **Diagnostics Report**: Mở tính năng xuất lỗi (Diagnostics copy) và đảm bảo copy thành công.

> [!NOTE]
> Nếu tất cả các mục trên đều đạt, bạn đã sẵn sàng đóng gói và gửi tin nhắn (kèm theo hướng dẫn) cho Tester.
