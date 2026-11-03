# ACCESSIBILITY RELEASE PASS

**Ngày đánh giá:** (Sprint 21)

## 1. Mục tiêu
Rà soát khả năng truy cập (Accessibility) trước thềm Release Candidate để đảm bảo mọi người dùng, kể cả người dùng khiếm thị hoặc hạn chế khả năng thao tác, đều có thể sử dụng ứng dụng EnglishPath AI.

## 2. Tiêu chuẩn đã đạt
1. **Content Description:** Tất cả các icon button (mic, loa, back, cài đặt) đều có thuộc tính `android:contentDescription` rõ ràng trong XML hoặc được bổ sung linh hoạt trong mã nguồn. 
2. **Kích thước điểm chạm (Touch Target):** Mọi nút bấm và khu vực tương tác đều được thiết kế với chiều cao/chiều rộng tối thiểu 48dp (chuẩn Material Design).
3. **Màu sắc & Trạng thái:** Không chỉ sử dụng màu sắc để thông báo lỗi (ví dụ: Text màu đỏ luôn đi kèm với icon cảnh báo hoặc thông báo "Sai rồi").
4. **Trường nhập liệu (Input Fields):** Tất cả các `EditText` đều có `android:hint` hoặc label nổi phía trên để mô tả trường dữ liệu.
5. **Đọc màn hình (Screen Reader):** Các tiến trình học tập (Progress Bar) được gắn nhãn để TalkBack đọc được phần trăm hoàn thành. Text trên checklist, quiz, flashcard được hiển thị trực tiếp thành chữ thay vì chỉ dùng biểu tượng.

## 3. Các thay đổi đã thực hiện (Sprint 21)
- Bổ sung `contentDescription` vào một số icon bị thiếu.
- Cải thiện độ tương phản (Contrast ratio) cho các text phụ (Secondary text) trên nền tối.

Hệ thống Accessibility đã sẵn sàng cho bản Release.
