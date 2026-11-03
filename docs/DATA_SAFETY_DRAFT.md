# Data Safety Form - Google Play Console Draft

Khi điền thông tin Data Safety trên Google Play Console cho **EnglishPath AI**, hãy tuân theo cấu trúc sau:

## 1. Ứng dụng có thu thập hoặc chia sẻ dữ liệu người dùng không?
- **Trả lời:** Có (Vì ứng dụng có sử dụng quyền truy cập vào dịch vụ Audio/Microphone của Android).
- Tùy chọn nâng cao: Dữ liệu có được mã hóa trên đường truyền không? **Có**.
- Bạn có cung cấp cách để xóa dữ liệu không? **Có** (Người dùng có thể Reset tiến độ hoặc Clear Data/Uninstall).

## 2. Loại Dữ Liệu
### 2.1. Audio (Microphone)
Ứng dụng sử dụng Microphone để chấm điểm phát âm qua API `SpeechRecognizer` của Android.
- **Thu thập dữ liệu Audio?** Có.
- **Chia sẻ dữ liệu Audio?** Không.
- **Xử lý tạm thời (Processed ephemerally)?** Có. (Âm thanh chỉ được xử lý real-time để lấy kết quả text/score, KHÔNG lưu file âm thanh trên máy, KHÔNG gửi lên server riêng của nhà phát triển).
- **Có bắt buộc không?** Tùy chọn (Optional). Người dùng có thể bỏ qua bài luyện nói.
- **Mục đích sử dụng:** App Functionality (Chức năng cốt lõi của ứng dụng).
- *Lưu ý:* Việc chuyển đổi giọng nói thành văn bản có thể được xử lý bởi Google Services mặc định trên máy (TTS/SpeechRecognizer API của hệ thống thiết bị).

### 2.2. Tiến trình học (App Activity / Progress)
- **Thu thập dữ liệu?** Không (Dữ liệu học tập, điểm quiz, diagnostics JSON chỉ được lưu trong database nội bộ (Room DB) của điện thoại, KHÔNG tải lên server của nhà phát triển).

### 2.3. Các loại dữ liệu khác
Khai báo "Không thu thập" đối với toàn bộ các mục sau:
- Không thu thập vị trí (Location).
- Không thu thập thông tin cá nhân (PII như Tên, Email, SĐT...).
- Không thu thập tài chính (Financial info).
- Không thu thập tin nhắn, danh bạ (Contacts/Messages).

## 3. Khai báo khác
- **Quảng cáo (Ads):** Ứng dụng bản hiện tại hoàn toàn không có SDK quảng cáo. Khai báo: "No Ads".
- **Quyền Nhắc nhở (Notification):** Ứng dụng xin quyền thông báo cục bộ (`POST_NOTIFICATIONS`) để gửi nhắc nhở học hàng ngày, không tải dữ liệu lên server.
