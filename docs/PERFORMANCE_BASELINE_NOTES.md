# Ghi chú Hiệu năng Khởi động (Performance Baseline)

Tài liệu này ghi lại các nguyên tắc tối ưu hóa hiệu năng (đặc biệt là tốc độ khởi động) để đảm bảo ứng dụng không bị đánh giá thấp về trải nghiệm người dùng (UX) khi cài trên máy tính thật.

## 1. Mục Tiêu Tối Ưu Tốc Độ Khởi Động (Startup)
- **Splash Screen Nhanh:** Tránh chặn (block) Main Thread tại SplashActivity hoặc Application `onCreate`.
- **Database Init:** Các queries Room Database không bao giờ được chạy trên luồng giao diện chính. Quá trình tạo Database (Room `fallbackToDestructiveMigration`) phải diễn ra ở background thread.
- **Seed Data:** Quá trình chèn dữ liệu ban đầu (SeedDataProvider) không được làm đơ UI. Nếu chạy quá lâu, hãy sử dụng coroutine/WorkManager để chèn ngầm.
- **Lazy Initialization:** Không khởi tạo `TextToSpeech` (TTS) hay `SpeechRecognizer` ngay ở màn hình Home, chỉ khởi tạo khi vào màn hình luyện nói.

## 2. Các Mốc Đo Lường Cần Kiểm Tra (Thủ Công)
Khi QA (Kiểm thử), Tester cần đếm thầm thời gian ở các mốc sau (trên máy cấu hình tầm trung):
1. **Cold Start:** Từ lúc bấm icon App lần đầu tiên sau khi cài đến khi vào màn hình `HomeFragment`.
2. **Tab Switching:** Thời gian chuyển đổi giữa Tab Home và Tab Lessons.
3. **Mở LessonDay:** Thời gian từ lúc bấm vào "Ngày 1" đến khi load xong danh sách từ vựng/ngữ pháp/quiz.
4. **JSON Export:** Chức năng Backup Export/Import đối với dữ liệu lớn (~100 ngày) không được làm ứng dụng hiện thông báo "App is not responding" (ANR).

## 3. Lời Khuyên Áp Dụng Baseline Profiles (Trong tương lai)
Baseline Profiles có thể tăng tốc thời gian khởi động lên đến 30% bằng cách biên dịch trước các đoạn mã (AOT).
Tuy nhiên:
- **Chỉ nên thiết lập ở các Sprint cuối cùng** khi cấu trúc Activity/Fragment đã hoàn toàn ổn định (không thêm tính năng mới).
- Ở Sprint này, tập trung xử lý dứt điểm các đoạn code chặn (Block) Main Thread.
