# Báo Cáo Phục Hồi Lỗi (Error Recovery Final Audit)

## Các kịch bản lỗi phổ biến đã kiểm thử

1. **Lỗi TTS (Text-To-Speech Unavailable)**:
   - *Kịch bản*: Máy người dùng không cài Google TTS hoặc TTS đang lỗi dữ liệu giọng nói.
   - *Phục hồi*: Bắt `try/catch` ngoại lệ. Hiển thị thông báo "Dịch vụ phát âm không khả dụng" thay vì văng app.
   
2. **Lỗi Speech Recognizer**:
   - *Kịch bản*: Mất kết nối internet khi đang ghi âm (SpeechRecognizer cần mạng trên một số dòng máy).
   - *Phục hồi*: Báo lỗi Timeout/Network Error, trả về điểm số thấp nhất (0) và yêu cầu thử lại.

3. **Lỗi Database / Dữ liệu rỗng**:
   - *Kịch bản*: Room chưa kịp khởi tạo Seed.
   - *Phục hồi*: Loading spinner hiển thị tự nhiên. Không NullPointerException.

4. **Lỗi File Backup/Restore**:
   - *Kịch bản*: File JSON restore bị hỏng (corrupted) hoặc cấu trúc không hợp lệ.
   - *Phục hồi*: Try/Catch GSON/Moshi parse error. Báo lỗi "File sao lưu không hợp lệ", bảo vệ vẹn nguyên dữ liệu hiện tại đang có.

## Kết luận
Khả năng phục hồi (Resilience) của ứng dụng cực kỳ tốt. Chịu tải lỗi offline hoàn hảo.
