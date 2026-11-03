# Hướng Dẫn Gửi Bản Test Cho First Tester

Tài liệu này lưu trữ sẵn các đoạn tin nhắn và hướng dẫn mẫu để bạn (Developer) có thể gửi cho người quen (Tester) khi nhờ họ cài ứng dụng.

## 1. Tin nhắn mẫu (Gửi qua Zalo/Telegram)
```text
Chào bạn,
Bạn giúp mình test app học tiếng Anh beta (bản nội bộ) tầm 10 phút nha. 
Đây là file APK cài đặt (app-release.apk). 

Lưu ý: 
1. Nếu hệ thống báo "Chặn nguồn không xác định", bạn nhấn "Cho phép" là được nha.
2. Nếu app bị Crash hoặc lỗi màn hình, bạn vào app -> Chọn Tab Profile (Hồ sơ) -> Chọn Cài đặt (Bánh răng) -> Kéo xuống cùng chọn "Copy Diagnostics" rồi dán gửi lại cho mình nha. 

Cảm ơn bạn nhiều!
```

## 2. File APK cần gửi
- File đính kèm: `app/build/outputs/apk/release/app-release.apk`

## 3. Checklist tester cần làm (Gợi ý nhẹ nhàng)
Không nên yêu cầu Tester làm quá nhiều để tránh họ nản. Chỉ cần họ:
- [ ] Cài đặt thành công.
- [ ] Học thử bài A0 ngày 1 (xem có lỗi gì không).
- [ ] Cho app nghe giọng nói thử (Microphone).
- [ ] Nghe thử âm thanh từ vựng (TTS).

## 4. Cách gửi ảnh/video lỗi
- Nếu bị lỗi giao diện, Tester chỉ cần chụp ảnh màn hình hoặc quay lại màn hình điện thoại (Screen Record) và gửi trực tiếp qua Zalo/Telegram.

## 5. Cách không làm mất tiến độ (Dành cho Tester đã test đợt trước)
- Nếu Tester đã có app cũ, dặn họ: Mở App -> Hồ Sơ -> Backup & Restore -> Copy chuỗi JSON lại -> Gỡ app cũ -> Cài app mới -> Dán lại JSON.
- Tuy nhiên, để đơn giản nhất, đối với First Tester, hãy khuyến khích họ cài mới hoàn toàn nếu chưa có tiến độ quan trọng.
