# HƯỚNG DẪN CHẠY VÀ CÀI ĐẶT DEVENGLISH AI TRÊN ĐIỆN THOẠI IPHONE (iOS PWA)

Hệ thống **DevEnglish AI** đã được xây dựng thành phiên bản **Progressive Web App (PWA)** chuẩn đa nền tảng tối ưu riêng cho **iPhone (iOS Safari)**:
- **Tận dụng phần cứng iPhone:** Sử dụng Camera sau (`facingMode: environment`) để quét nhận diện vật thể thời gian thực, bật đèn Flash (Torch), và micro thu âm để luyện nói tiếng Anh.
- **Trải nghiệm như App Native:** Hỗ trợ tính năng **"Thêm vào Màn hình chính" (Add to Home Screen)** trên Safari, mở ra chạy toàn màn hình (không thanh địa chỉ trình duyệt, có icon riêng, hỗ trợ tai thỏ notch và thanh gạt Home Bar).
- **Hoạt động 100% Offline:** Tích hợp **Service Worker** và bộ nhớ đệm cục bộ, khi iPhone mất kết nối mạng vẫn học từ vựng, làm trắc nghiệm và luyện phỏng vấn bình thường.

---

## I. CÁCH 1: TRẢI NGHIỆM TRÊN IPHONE QUA MẠNG WI-FI NỘI BỘ

Nếu máy tính của bạn và điện thoại iPhone đang kết nối vào **cùng một mạng Wi-Fi**:

### Bước 1: Khởi động máy chủ trên máy tính
- Nhấp đúp chuột vào file: [`start_pwa.bat`](start_pwa.bat)
- Hoặc mở terminal và chạy:
  ```bash
  python pwa/serve.py
  ```
- Cửa sổ terminal sẽ hiển thị địa chỉ IP nội bộ của bạn, ví dụ:
  ```text
  👉 Trên máy tính này:       http://localhost:8080
  📱 Trên iPhone (cùng Wifi): http://192.168.1.15:8080
  ```

### Bước 2: Mở trên Safari của iPhone
1. Mở trình duyệt **Safari** trên điện thoại iPhone.
2. Gõ địa chỉ IP hiển thị trên terminal (ví dụ: `http://192.168.1.15:8080`).
3. Giao diện ứng dụng DevEnglish AI tối ưu riêng cho iPhone sẽ xuất hiện ngay lập tức!

---

## II. CÁCH 2: CÀI ĐẶT LÊN MÀN HÌNH CHÍNH IPHONE (ADD TO HOME SCREEN)

Để ứng dụng trở thành một App độc lập ngoài màn hình chính iPhone:
1. Khi đang mở ứng dụng trên **Safari**, bấm vào nút **Chia sẻ (Share ⎋)** ở thanh công cụ dưới đáy Safari (biểu tượng hình vuông có mũi tên hướng lên).
2. Cuộn xuống và chọn dòng: **"Thêm vào Màn hình chính" (Add to Home Screen)**.
3. Bấm **Thêm (Add)** ở góc trên bên phải.
4. **Kết quả:** Icon **DevEnglish AI** màu xanh công nghệ sẽ xuất hiện trên màn hình chính iPhone của bạn. Khi bấm vào, ứng dụng sẽ chạy **toàn màn hình (Standalone Mode)** y hệt như tải từ App Store!

---

## III. LƯU Ý QUAN TRỌNG VỀ QUYỀN CAMERA TRÊN IPHONE SAFARI (HTTPS)

Theo tiêu chuẩn bảo mật của Apple, trình duyệt **Safari trên iOS** chỉ cho phép truy cập Camera qua WebRTC khi kết nối là **HTTPS** hoặc **localhost**:

### Để Camera hoạt động mượt mà trên iPhone:
Bạn có thể chọn 1 trong 3 cách miễn phí và siêu nhanh sau:

#### Phương án A: Dùng Cloudflare Tunnel (Miễn phí, 30 giây, có HTTPS ngay)
Mở một cửa sổ terminal mới trên máy tính và gõ:
```bash
npx cloudflared tunnel --url http://localhost:8080
```
Cloudflare sẽ cung cấp cho bạn một đường link HTTPS an toàn (ví dụ: `https://xxx.trycloudflare.com`). Bạn chỉ cần mở link này trên Safari iPhone là Camera quét từ vựng và micro hoạt động tức thì!

#### Phương án B: Triển khai lên Vercel (Khuyên dùng - 1 Click)
1. Thư mục [`pwa`](pwa) đã có sẵn file cấu hình [`vercel.json`](pwa/vercel.json).
2. Bạn có thể đẩy lên Vercel hoặc Netlify, bạn sẽ có một tên miền riêng `https://devenglish.vercel.app` vĩnh viễn, truy cập mọi lúc mọi nơi trên mọi chiếc iPhone.

#### Phương án C: Triển khai lên GitHub Pages
Tài khoản GitHub của bạn (`hung090725/apptienganh`) có thể bật tính năng GitHub Pages từ nhánh `main` để có link `https://hung090725.github.io/apptienganh/pwa/` hoàn toàn miễn phí.

---

## IV. BẢNG TÍNH NĂNG TRÊN IPHONE

| Tính năng | Hoạt động trên iPhone | Ghi chú kỹ thuật |
| :--- | :---: | :--- |
| **AR Camera Real-Time Lens** | ✅ Có | Dùng camera sau iPhone (`facingMode: environment`), khung nhận diện neon, hiển thị tiếng Anh, IPA, tiếng Việt và nút nghe phát âm. |
| **Luyện Nói & Nhận Diện Giọng Nói** | ✅ Có | Tích hợp Web Speech API (`webkitSpeechRecognition`) trực tiếp qua micro của iPhone. |
| **Phát Âm Chuẩn Bản Xứ (TTS)** | ✅ Có | Tự động nhận diện giọng tiếng Anh chuẩn trên iOS (Samantha, Daniel) với tùy chọn giọng Anh - Mỹ (`en-US`) hoặc Anh - Anh (`en-GB`). |
| **Code-to-English AI Lab** | ✅ Có | IDE Dark Theme, chấm điểm 2 trục độc lập (Technical Correctness vs English Expression). |
| **Technical Documentation Reader** | ✅ Có | Đọc tài liệu công nghệ, giải thích Plain English, bóc tách thuật ngữ và trắc nghiệm tương tác. |
| **IT English Interview Simulator** | ✅ Có | Mô phỏng 4 vòng phỏng vấn tuyển dụng IT với AI chấm 5 tiêu chí và cung cấp câu trả lời Band 7.5+. |
| **Workplace Practice Tools** | ✅ Có | Luyện viết Bug Report chuẩn Jira, kiểm tra Conventional Commits, và họp Daily Standup Scrum. |
| **Bách Khoa Từ Vựng 35+ Vật Thể** | ✅ Có | Bảng tra cứu toàn diện, collocations, idioms, dialogues và bài tập trắc nghiệm nhanh. |
| **Chạy Ngoại Tuyến (Offline)** | ✅ Có | Tích hợp Service Worker (`sw.js`) lưu cache toàn bộ ứng dụng, không cần mạng vẫn học tốt. |
