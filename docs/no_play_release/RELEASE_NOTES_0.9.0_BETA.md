# Ghi chú phát hành (Release Notes) - 0.9.0-beta

**App Name:** EnglishPath AI  
**Version:** 0.9.0-beta  

## Mục tiêu bản beta:
- Kiểm thử toàn bộ lộ trình học từ A0 đến B1 (Tổng 56 ngày).
- Kiểm thử độ ổn định của Text-To-Speech (Phát âm).
- Kiểm thử độ nhạy của Microphone (Luyện nói).
- Kiểm thử thuật toán lặp lại ngắt quãng (SRS) khi ôn từ vựng.
- Kiểm thử bộ câu hỏi Quiz trắc nghiệm.
- Đánh giá tính năng Sao lưu & Khôi phục tiến độ học tập.
- Đánh giá hệ thống thu thập Feedback và Diagnostics.

## Tính năng chính:
- **56 ngày học A0–B1:** Phân bổ hợp lý từ mất gốc đến giao tiếp cơ bản.
- **Từ vựng chi tiết:** Có đầy đủ phiên âm IPA, nghĩa tiếng Việt, và câu ví dụ minh họa.
- **Ngữ pháp:** Trình bày bằng tiếng Việt dễ hiểu kèm ví dụ so sánh đúng/sai.
- **Hội thoại:** Mô phỏng các tình huống giao tiếp A-B thực tế.
- **Luyện nói:** Đánh giá phát âm bằng SpeechRecognizer và trả về % điểm số.
- **Ôn từ SRS:** Nhắc lại từ vựng dựa trên trí nhớ (Again, Hard, Good, Easy).
- **Nhắc học (Reminder):** Gửi thông báo nhắc học theo giờ người dùng tùy chỉnh.
- **Backup/Restore:** Sao lưu toàn bộ tiến độ thành chuỗi JSON qua Clipboard (100% offline).

## Lỗi đã biết (Known Issues):
- Tính năng Text-To-Speech phụ thuộc nhiều vào Engine TTS mặc định của nhà sản xuất điện thoại (Samsung TTS, Google TTS, Xiaomi TTS). Ở một số dòng máy cũ có thể phát âm không chuẩn giọng Anh-Mỹ.
- Độ nhạy Microphone phụ thuộc vào phần cứng và môi trường xung quanh (ồn ào sẽ làm giảm điểm số).
- Tính năng gửi Thông báo nhắc học (Notification) có thể yêu cầu bạn phải tự cấp quyền bằng tay trên các máy chạy Android 13 trở lên.

## Cách báo lỗi:
Tester gặp lỗi vui lòng làm theo mẫu trong file `TESTER_FEEDBACK_FORM_TEMPLATE.md` và gửi kèm JSON từ mục **App Diagnostics**.

*Lưu ý: Ứng dụng này giúp bạn xây dựng thói quen và nền tảng tiếng Anh, không hứa hẹn "giỏi tiếng Anh ngay lập tức" hay cam kết đạt điểm IELTS/TOEIC cụ thể. Sự tiến bộ phụ thuộc vào thời gian và nỗ lực luyện tập mỗi ngày của bạn.*
