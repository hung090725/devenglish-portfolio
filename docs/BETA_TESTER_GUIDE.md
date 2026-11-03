# Hướng Dẫn Dành Cho Beta Tester

Chào mừng bạn đã tham gia chương trình Beta Test của **EnglishPath AI**! Ứng dụng này được thiết kế để giúp người học từ mất gốc lấy lại nền tảng tiếng Anh, thông qua từ vựng, ngữ pháp, và đặc biệt là công nghệ nhận diện giọng nói AI để sửa lỗi phát âm.

Dưới đây là những bước hướng dẫn để bạn có thể tải, cài đặt và trải nghiệm ứng dụng hiệu quả nhất.

## 1. Cài đặt App & Chuẩn bị
1. **Ghi nhận thiết bị**: Trước khi bắt đầu, hãy lưu lại Tên máy (VD: Samsung S23), Phiên bản Android (VD: Android 14) và TTS engine (Google hay Samsung).
2. Tải file `app-debug.apk` được gửi qua nhóm.
3. Mở file để cài đặt. Nếu Android báo "Cài đặt ứng dụng từ nguồn không xác định" (Unknown sources), hãy chọn "Cài đặt vẫn tiếp tục" (Install anyway).
4. Mở ứng dụng EnglishPath AI.

## 2. Các Luồng Cần Trải Nghiệm Thử

### Bước 1: Khởi động và Khai báo
- Đi qua màn hình giới thiệu (Onboarding).
- Tại màn hình kiểm tra trình độ, hãy chọn **"Tôi mất gốc" (A0)**.

### Bước 2: Học Bài Mới (Tab Trang Chủ)
- Nhấn vào **"Học Tiếp"** hoặc chọn bài **Ngày 1 (A0)** ở mục Đề xuất.
- **Nghe thử:** Bấm vào các biểu tượng chiếc loa để nghe từ vựng và câu ví dụ (thử cả giọng UK và US).
- **Luyện nói:** Ở phía dưới màn hình, có nút chiếc Micro màu gradient. Hãy nhấn nút này, cấp quyền ghi âm cho app, và thử đọc lại từ vựng hoặc câu hiển thị trên màn hình. App sẽ chấm điểm ngay lập tức.
- **Quiz:** Cuối ngày, hãy làm một bài kiểm tra ngắn. Nhớ chọn sai thử 1-2 câu để xem app giải thích ngữ pháp tiếng Việt.
- **Hoàn tất:** Khi học xong, hãy nhấn **"Mark Completed"**. Lúc này Ngày 2 sẽ được mở khóa. Bạn hãy thử nhấn vào Ngày 3 (đang bị khóa) xem app có chặn đúng không nhé.

### Bước 3: Ôn Tập (Tab Ôn Từ Vựng)
- Nhấn vào **"Ôn Từ Vựng"** ở Trang chủ (hoặc tab Practice).
- Bạn sẽ thấy những từ bạn vừa học. Hãy dùng các nút **Again, Hard, Good, Easy** để tự chấm độ thuộc bài của mình. Các từ "Easy" sẽ biến mất và được nhắc lại vào những ngày sau.

### Bước 4: Nhắc Nhở Báo Thức
- Vào tab **Hồ sơ (Profile)** (icon ngoài cùng bên phải ở menu dưới).
- Chọn **Cài đặt nhắc học**.
- Cấp quyền thông báo (nếu máy hỏi), sau đó bật chế độ nhắc.
- Hãy chọn 1 mốc thời gian cách thời điểm hiện tại khoảng 1-2 phút, sau đó thoát hẳn app ra ngoài xem thông báo (Push Notification) có gửi đến không.

## 3. Cách Gửi Báo Cáo Lỗi Cho Developer
Nếu bạn gặp vấn đề, vui lòng ưu tiên sử dụng tính năng báo lỗi có sẵn trong ứng dụng:

1. **Vào Hồ Sơ (Profile) -> Cuộn xuống dưới cùng -> Bấm "Gửi phản hồi Beta".**
2. Điền đầy đủ thông tin: Phân loại lỗi (Category), Mức độ (Severity), mô tả lỗi, bước tái hiện và kết quả mong đợi.
3. Bấm **"Lưu Feedback"**.
4. Sau khi lưu, hãy bấm **"App Diagnostics (Tester)"** ở cùng màn hình Profile.
5. Sao chép đoạn chuỗi JSON (Diagnostics JSON).
6. Điền thông tin theo mẫu **BETA_DEVICE_TEST_REPORT_TEMPLATE** (kèm hình ảnh, video minh chứng và Diagnostics JSON đã copy) và gửi cho đội Dev.

**🚨 Hãy báo cáo NGAY LẬP TỨC nếu bạn gặp:**
- App bị văng (Crash / Force Close).
- Chức năng luyện nói (Micro) không phản hồi dù đã cấp quyền.
- Chức năng phát âm (Loa) bấm không có tiếng.
- Bị mất tiến độ học bài cũ khi tắt app mở lại.
- Bị khóa bài học sai logic (VD: A0 chưa xong mà A1 đã mở).

Cảm ơn bạn rất nhiều vì đã góp phần hoàn thiện EnglishPath AI! ❤️
