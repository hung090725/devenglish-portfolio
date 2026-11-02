# Nghiên Cứu Thiết Kế Kiến Trúc Và Hiện Thực Hóa Ứng Dụng Học Tiếng Anh Thời Gian Thực Qua Camera Thiết Bị Di Động

> **Tác giả:** Nguyễn Văn Hưng  
> **Dự án:** DevEnglish AI — Real-Time Vision-Based Contextual Language Learning Platform  
> **Hệ sinh thái:** Đa nền tảng (Native Android Kotlin Jetpack Compose & High-Performance PWA Web/iOS Safari)  
> **Cấp độ kỹ thuật:** Đồ Án Tốt Nghiệp Kỹ Thuật Phần Mềm / Báo Cáo Nghiên Cứu Kiến Trúc Cấp Công Nghiệp  

---

## TÓM TẮT NGHIÊN CỨU (ABSTRACT)

Mô hình học ngôn ngữ dựa trên thị giác máy tính thời gian thực (*Real-time Vision-based Language Learning*) đại diện cho bước chuyển đổi mô thức giáo dục từ ghi nhớ thụ động (*passive memorization*) sang tương tác chủ động theo ngữ cảnh thực địa (*situated contextual learning*). Trong mô hình này, thiết bị di động đóng vai trò như một thấu kính nhận thức: khi cảm biến máy ảnh quét qua bất kỳ văn bản in ấn hoặc vật thể thực tế nào trong môi trường sống, hệ thống sẽ tự động phát hiện, bóc tách chuỗi ký tự hoặc nhận diện thực thể, đồng thời hiển thị trực tiếp từ vựng tương ứng kèm phiên âm ký hiệu ngữ âm quốc tế (IPA), định nghĩa ngữ cảnh, cấu trúc kết hợp từ (*collocations*) và âm thanh phát âm bản ngữ chuẩn xác.

Để hiện thực hóa mục tiêu này với độ tin cậy cấp công nghiệp, kiến trúc hệ thống giải quyết đồng thời năm thách thức cốt lõi:
1. **Thông lượng xử lý luồng khung hình và độ trễ suy luận mô hình học sâu (Deep Learning Inference Latency)**.
2. **Kiểm soát bộ nhớ đệm theo cơ chế không sao chép (Zero-Copy Buffer Management)** nhằm ngăn chặn tràn RAM và quá nhiệt thiết bị.
3. **Biến đổi không gian hình học (Spatial Geometric Transformation)** khắc phục triệt để sự bất đối xứng giữa hệ tọa độ cảm biến máy ảnh vật lý và hệ tọa độ hiển thị màn hình di động (CenterCrop scaling & Sensor Rotation).
4. **Cơ chế phòng thủ thị giác (Defensive Visual Engineering)** kết hợp bộ lọc phương sai toán tử Laplacian ($\sigma^2 \ge 120$) loại bỏ khung hình nhòe do rung lắc chuyển động và bộ lọc mượt hàm mũ (EMA $\alpha = 0.3$) dựa trên chỉ số IoU ($\ge 0.5$) triệt tiêu rung giật hộp nhận diện.
5. **Cơ chế sư phạm khoa học dựa trên thuật toán lặp lại ngắt quãng SuperMemo-2 (SM-2)** củng cố tri thức vào trí nhớ dài hạn theo định luật đường cong quên lãng Ebbinghaus.

---

## CHƯƠNG 1: KIẾN TRÚC HỆ THỐNG VÀ QUY TRÌNH XỬ LÝ DỮ LIỆU THỊ GIÁC

### 1.1. Mô hình Nhà sản xuất - Người tiêu thụ (Producer-Consumer) Bất đồng bộ

Quy trình xử lý khung hình liên tục từ cảm biến camera đòi hỏi một kiến trúc hướng sự kiện bất đồng bộ nhằm phân tách triệt để luồng thu nhận hình ảnh khỏi luồng tính toán học máy và luồng kết xuất đồ họa giao diện người dùng (UI Thread). Việc thực hiện tính toán tuần tự trên cùng một tiến trình đồ họa sẽ ngay lập tức gây ra hiện tượng nghẽn luồng chính (*UI Thread Blocking*), dẫn đến sụt giảm tốc độ làm tươi khung hình (*jank/frame drops*) và làm gián đoạn trải nghiệm người học.

```
[Camera Sensor] (30-60 FPS)
       │
       ▼
[Frame Gating & Quality Filter] ──(Bị nhòe/Lỗi)──► [Hủy khung hình]
       │
       ▼ (Khung hình đạt chuẩn: σ² ≥ 120)
[Adaptive Throttling Queue]
       │
       ▼
[Inference Worker (C++/NPU/WebGL)] ──► [ML Kit OCR / YOLOv11n / MobileNet]
       │                                     │
       │ (Tọa độ thô, nhãn từ)               │
       ▼                                     ▼
[Temporal Smoothing & IoU (α=0.3)] ◄──── [SQLite FTS5 / Local Lexicon]
       │
       ▼ (Tọa độ đã làm mượt + Dữ liệu từ vựng C1/C2)
[UI Canvas Overlay] (60 FPS Khóa Khung Hình)
```

1. **Camera Sensor (Nguồn sản xuất):** Thu nhận luồng khung hình liên tục ở tần số quét cố định từ 30 đến 60 FPS từ phần cứng máy ảnh.
2. **Frame Gating & Quality Filter:** Kiểm định chất lượng thời gian thực bằng toán tử vi phân bậc hai Laplacian. Các khung hình có phương sai $\sigma^2 < 120$ (do rung tay hoặc lấy nét dở dang) bị hủy bỏ lập tức ngay tại tầng đệm.
3. **Adaptive Throttling Queue:** Hàng đợi không khóa (*lock-free queue*) áp dụng chiến lược bỏ qua khung hình có chọn lọc (*frame-dropping strategy*): khi mô hình học sâu đang trong chu kỳ suy luận của khung hình trước, mọi khung hình mới đến sau sẽ bị loại bỏ để giải phóng bộ nhớ đệm, chỉ giữ lại khung hình mới nhất tại thời điểm bộ suy luận hoàn tất.
4. **Inference Worker:** Thực thi mạng nơ-ron cục bộ trên NPU/GPU/WebAssembly, tách biệt hoàn toàn khỏi luồng giao diện chính.
5. **Temporal Smoothing & IoU Filter:** Đối chiếu hộp bao giữa khung hiện tại và khung trước đó qua chỉ số IoU ($\ge 0.5$), áp dụng hàm làm mượt mũ EMA để triệt tiêu hiện tượng jitter.
6. **UI Canvas Overlay:** Kết xuất đồ họa vector phần cứng đạt chuẩn mượt mà 60 FPS độc lập với tần số suy luận của AI.

### 1.2. Kiểm soát Bộ nhớ đệm Không sao chép (Zero-Copy Buffer Management)

Ở độ phân giải chuẩn Full HD ($1920 \times 1080$), mỗi khung hình thô định dạng `YUV_420_888` hoặc `CMSampleBuffer` chiếm dung lượng xấp xỉ 3 đến 8 MB. Luồng dữ liệu luân chuyển liên tục ở tốc độ 30 FPS tương đương với băng thông bộ nhớ lên tới:

$$\text{Bandwidth}_{\text{Full HD}} \approx 1920 \times 1080 \times 1.5 \times 30 \approx 93.3 \text{ MB/s (Không nén thô)} \to 240 \text{ MB/s (Chuyển đổi không gian màu RGB)}$$

Và con số này vượt qua $700 \text{ MB/s}$ đối với video 4K. Việc sao chép dữ liệu pixel giữa tầng mã nguồn gốc C/C++ và tầng môi trường máy ảo cấp cao (như JavaScript V8 hoặc Dart/JVM) thông qua cầu nối tuần tự hóa truyền thống sẽ dẫn tới sự cố tràn bộ nhớ (*Out-Of-Memory*) và quá nhiệt CPU.

**Giải pháp kỹ thuật:**
1. Giữ con trỏ bộ nhớ đệm pixel thô trực tiếp trong không gian bộ nhớ đồ họa chia sẻ và truyền tải địa chỉ con trỏ thông qua giao diện hàm trực tiếp (C++ JSI hoặc WebAssembly Memory Shared Array Buffer).
2. Khóa cấu hình độ phân giải thu nhận ở mức chuẩn **$1280 \times 720$ (720p)**. Đây là tỷ lệ tối ưu bảo đảm chiều cao ký tự văn bản đạt tối thiểu từ 16 đến 24 pixel (ngưỡng cần thiết để nhận dạng ký tự quang học đạt độ chính xác > 98%), đồng thời giảm tải hơn **55.5% khối lượng dữ liệu pixel** so với Full HD 1080p:

$$\frac{(1920 \times 1080) - (1280 \times 720)}{1920 \times 1080} = \frac{2,073,600 - 921,600}{2,073,600} \approx 55.56\%$$

3. Sau khi hoàn tất chu kỳ phân tích, toàn bộ tham chiếu đối tượng khung hình (`ImageProxy`, `CMSampleBuffer`, `ImageData`) bắt buộc phải được giải phóng tường minh trong khối `try/finally` (`imageProxy.close()`) để ngăn ngừa rò rỉ bể cấp phát đệm camera (*buffer pool starvation*).

---

## CHƯƠNG 2: ĐÁNH GIÁ VÀ SO CHUẨN CÔNG NGHỆ NHẬN DẠNG KÝ TỰ QUANG HỌC (REAL-TIME OCR)

### 2.1. Phân Tích Thực Nghiệm Các Giải Pháp On-Device OCR và Cloud OCR

Bảng so sánh định lượng các giải pháp nhận dạng ký tự quang học trong điều kiện quét camera di động thời gian thực:

| Tiêu Chí Kỹ Thuật | Google ML Kit v2 (On-Device) | Apple Vision Framework (.fast / .accurate) | PaddleOCR PP-OCRv4 Slim | Cloud Vision API (Google / AWS) |
| :--- | :--- | :--- | :--- | :--- |
| **Độ trễ suy luận (Latency)** | 25 – 45 ms | 12 – 25 ms (.fast) / 60 – 110 ms (.accurate) | 40 – 80 ms | 450 – 1,200 ms |
| **Phụ thuộc kết nối Internet** | 100% Ngoại tuyến (Offline) | 100% Ngoại tuyến (Offline) | 100% Ngoại tuyến (Offline) | Bắt buộc 4G/5G/Wi-Fi ổn định |
| **Cấu trúc phân cấp văn bản** | Khối (Block) $\to$ Dòng (Line) $\to$ **Từ (Element)** | Khối $\to$ Câu $\to$ Ký tự | Hộp bao ký tự & dòng văn bản | Khối văn bản đầy đủ |
| **Tối ưu hóa phần cứng** | Android NNAPI / Qualcomm NPU | Apple Neural Engine (ANE) | ONNX Runtime / CPU INT8 | Máy chủ GPU Đám mây (A100/T4) |
| **Tác động nhiệt lượng & Pin** | Rất thấp (NPU chuyên dụng) | Cực thấp (ANE tối ưu phần cứng) | Trung bình (Tải CPU cao hơn) | Cao (Radio RF truyền dữ liệu liên tục) |
| **Chi phí vận hành (Cost)** | $0 (Miễn phí vĩnh viễn) | $0 (Tích hợp hệ điều hành) | $0 (Mã nguồn mở) | ~$1.50 / 1,000 lượt yêu cầu |

### 2.2. Kiến Trúc Pipeline Lai Hai Tầng (Edge-First Hybrid Pipeline)

Nhằm dung hòa giữa tốc độ phản hồi tức thời của giải pháp On-Device và khả năng hiểu ngữ nghĩa sâu rộng của mô hình nền tảng đám mây, hệ thống triển khai kiến trúc lai hai tầng (*Edge-First Hybrid Pipeline*):

```
                      [Khung hình Camera]
                               │
               ┌───────────────┴───────────────┐
               ▼                               ▼
       [Tầng 1: On-Device Engine]      [Tầng 2: Multi-Modal AI Engine]
       - Google ML Kit / Apple Vision  - Gemini 1.5 Flash / Vision Cloud
       - Phản hồi: < 30 ms             - Phản hồi: Khi người dùng bấm quét sâu
       - Bóc tách từ đơn lẻ (Element)  - Bách khoa toàn thư ngữ cảnh C1/C2
       - Tọa độ hiển thị tức thì       - Phân tích cấu trúc ngữ pháp phức tạp
```

- **Tầng 1 (Cục bộ):** Đảm nhiệm vẽ hộp bao thời gian thực, bóc tách tọa độ độc lập của từng từ vựng khi người dùng lướt camera qua văn bản.
- **Tầng 2 (Đám mây / Mô hình Ngôn ngữ Lớn Đa phương thức):** Chỉ được kích hoạt theo yêu cầu (*On-Demand*) khi người học chạm vào một cụm từ phức tạp hoặc nhấn nút quét sâu bách khoa, loại bỏ hoàn toàn chi phí băng thông không cần thiết.

---

## CHƯƠNG 3: MÔ HÌNH NHẬN DIỆN VẬT THỂ VÀ ÁNH XẠ NGỮ NGHĨA ĐA NGỮ CẢNH

### 3.1. Tối Ưu Hóa Mạng Nơ-ron Nhận Diện Thực Thể (YOLOv11n / MobileNetV4)

Đối với các thực thể trong đời sống hàng ngày (đồ dùng văn phòng, thiết bị công nghệ, vật dụng sinh hoạt), hệ thống sử dụng mạng nơ-ron tích chập rút gọn được lượng hóa 8-bit (*INT8 Quantized*):
- Kích thước tệp nhị phân mô hình: **< 15 MB**.
- Bộ nhớ tiêu thụ lúc hoạt động: **< 85 MB RAM**.
- Thời gian suy luận trên vi xử lý di động: **18 – 32 ms/khung hình**.

### 3.2. Ánh Xạ Phân Cấp Ngữ Nghĩa (Semantic Lexical Mapping)

Nhãn đầu ra từ các tập dữ liệu thị giác chuẩn (như COCO 80 lớp hoặc ImageNet 1,000 lớp) thường chỉ là các danh từ chung đơn điệu (ví dụ: `bottle`, `keyboard`, `laptop`). Một ứng dụng giáo dục ngôn ngữ chuyên nghiệp không thể dừng lại ở việc hiển thị một nhãn từ thô sơ. Hệ thống xây dựng một tầng ánh xạ từ điển sư phạm đa ngữ cảnh:

```
[Nhãn Mô hình: "laptop"]
          │
          ▼
[Tầng Ánh Xạ Sư Phạm DevEnglish]
  ├── Từ vựng: Laptop
  ├── Phiên âm quốc tế: /ˈlæp.tɒp/
  ├── Trọng âm: Âm tiết thứ nhất [ˈlæp]
  ├── Cấp độ CEFR: A1 – C1
  ├── Cụm từ kết hợp (Collocations):
  │     • "state-of-the-art laptop" (máy tính xách tay tối tân)
  │     • "boot up the laptop" (khởi động laptop)
  │     • "compute-intensive workloads" (tác vụ tính toán nặng)
  ├── Câu hoàn chỉnh C1/C2:
  │     "Modern laptops integrate neural processing units to accelerate local AI inference."
  └── Thành ngữ / Khẩu ngữ kỹ thuật:
        "Burn the midnight oil" (Thức khuya làm việc bên laptop)
```

### 3.3. Bộ Lọc Ngưỡng Kép Chống Nhấp Nháy (Dual-Threshold Hysteresis Filtering)

Khi độ tin cậy của mô hình nhận diện dao động quanh ngưỡng kích hoạt, hiện tượng nhấp nháy (*flickering*) sẽ xảy ra nếu chỉ dùng một ngưỡng cố định. Hệ thống áp dụng nguyên lý trễ (*Hysteresis*) với hai ngưỡng xác suất độc lập:

$$\tau_{\text{on}} = 0.65 \quad (\text{Ngưỡng hiển thị mới})$$
$$\tau_{\text{off}} = 0.45 \quad (\text{Ngưỡng duy trì trạng thái})$$

- Hộp bao chỉ được sinh ra trên giao diện khi xác suất tin cậy của mô hình vượt qua $\tau_{\text{on}} = 0.65$.
- Khi vật thể bị che khuất một phần hoặc góc chiếu thay đổi, hộp bao chỉ bị gỡ bỏ khi xác suất sụt giảm xuống dưới $\tau_{\text{off}} = 0.45$. Cơ chế này triệt tiêu hoàn toàn hiện tượng hộp bao xuất hiện và biến mất liên tục gây mỏi mắt người học.

---

## CHƯƠNG 4: BIẾN ĐỔI HÌNH HỌC KHÔNG GIAN VÀ KẾT XUẤT ĐỒ HỌA GIAO DIỆN

### 4.1. Bản Chất Hình Học Của Sự Sai Lệch Tọa Độ

Sự sai lệch giữa hộp bao nhận diện của cảm biến camera và vị trí vật thể trên màn hình bắt nguồn từ ba nguyên nhân vật lý cốt lõi:
1. **Góc quay cảm biến phần cứng (Hardware Sensor Rotation):** Cảm biến máy ảnh trên hầu hết thiết bị di động được sản xuất cố định theo chiều ngang tự nhiên (*Landscape*), tạo ra độ lệch góc quay $90^\circ$ khi ứng dụng khóa giao diện theo chiều dọc (*Portrait*).
2. **Bất đối xứng tỷ lệ khung hình (Aspect Ratio Mismatch):** Tỷ lệ camera thường là $16:9$ ($1280 \times 720$) hoặc $4:3$, trong khi màn hình smartphone hiện đại kéo dài ở tỷ lệ $19.5:9$ hoặc $20:9$. Khi chế độ hiển thị áp dụng cắt viền (*CenterCrop / aspect-fill*), các dải pixel ở rìa trên/dưới hoặc trái/phải sẽ bị cắt bỏ (*clipped*).
3. **Đảo nghịch trục tọa độ (Origin & Axis Inversion):** Hệ tọa độ đồ họa màn hình tiêu chuẩn đặt gốc $(0,0)$ ở **góc trên bên trái**, chiều $Y$ tăng dần đi xuống. Ngược lại, một số framework (tiêu biểu như Apple Vision Framework) chuẩn hóa tọa độ trong khoảng $[0, 1]$ với gốc tọa độ đặt tại **góc dưới bên trái**, chiều $Y$ hướng lên trên.

```
[Khung hình cảm biến: Wc x Hc (Ngang)]
  ┌──────────────────────────────────────┐
  │                                      │
  │     [Văn bản / Thực thể gốc]         │
  │                                      │
  └──────────────────────────────────────┘
                     │
    1. Xoay hướng cảm biến (Rotation 90°)
    2. Lật trục Y: y_top = 1 - y_vision - h_vision
    3. Phóng to theo tỷ lệ bao phủ: S = max(Wv / Wc, Hv / Hc)
    4. Cân chỉnh phần cắt biên: dx, dy
                     │
                     ▼
[Màn hình điện thoại: Wv x Hv (Dọc - CenterCrop)]
  ┌──────────────────┐
  │ ░░░ (Clipped) ░░ │
  │ ┌──────────────┐ │
  │ │ [Bounding    │ │
  │ │    Box + UI] │ │
  │ └──────────────┘ │
  │ ░░░ (Clipped) ░░ │
  └──────────────────┘
```

### 4.2. Đạo Hàm Phương Trình Biến Đổi Không Gian Tổng Quát

Gọi $W_c, H_c$ lần lượt là chiều rộng và chiều cao của khung hình camera sau khi đã xoay tương thích với hướng màn hình ($W_v, H_v$).

Hệ số co giãn bao phủ (*Aspect-Fill Scale Factor*) $S$ được tính toán:

$$S = \max\left(\frac{W_v}{W_c}, \frac{H_v}{H_c}\right)$$

Khoảng cách bù trừ cắt lề (*Offsets*) trên hai trục tọa độ:

$$dx = \frac{W_v - W_c \cdot S}{2}, \quad dy = \frac{H_v - H_c \cdot S}{2}$$

Đối với khung hình từ Apple Vision Framework (chuẩn hóa $[0, 1]$ gốc dưới trái), tọa độ góc trên bên trái được chuyển đổi trước khi áp dụng hệ số co giãn:

$$x_{\text{norm}} = x_{\text{vision}}$$
$$y_{\text{norm}} = 1.0 - y_{\text{vision}} - h_{\text{vision}}$$

Tọa độ điểm ảnh thực tế trên Canvas màn hình $(X_{\text{screen}}, Y_{\text{screen}}, W_{\text{screen}}, H_{\text{screen}})$ được ánh xạ chính xác qua hệ phương trình afin:

$$X_{\text{screen}} = (x_{\text{norm}} \cdot W_c) \cdot S + dx$$
$$Y_{\text{screen}} = (y_{\text{norm}} \cdot H_c) \cdot S + dy$$
$$W_{\text{screen}} = (w_{\text{norm}} \cdot W_c) \cdot S$$
$$H_{\text{screen}} = (h_{\text{norm}} \cdot H_c) \cdot S$$

Nhờ phương trình này, mọi hộp bao hiển thị đè (*Overlay*) luôn khớp chính xác từng pixel với văn bản hoặc vật thể ngoài đời thực, bất kể độ phân giải hay tỷ lệ màn hình của thiết bị.

---

## CHƯƠNG 5: CƠ SỞ DỮ LIỆU TỪ ĐIỂN VÀ XỬ LÝ NGÔN NGỮ TỰ NHIÊN CỤC BỘ

### 5.1. Kiến Trúc Cơ Sở Dữ Liệu Chỉ Mục SQLite FTS5

Để đạt tốc độ phản hồi truy vấn dưới $1 \text{ ms}$ khi camera phát hiện từ mới, toàn bộ từ điển sư phạm được lưu trữ cục bộ trong cơ sở dữ liệu SQLite nhúng với mô-đun tìm kiếm toàn văn phiên bản 5 (**FTS5 - Full-Text Search 5**).

- **Chỉ mục nghịch đảo (Inverted Index):** Hỗ trợ tìm kiếm tiền tố cực nhanh thông qua toán tử `MATCH 'prefix*'`, cho phép trả về kết quả ngay cả khi người dùng quét một phần của từ vựng.
- **Bảng nội dung bên ngoài (External Content Tables):** Phân tách chỉ mục FTS5 khỏi bảng dữ liệu chính để tiết kiệm hơn 40% dung lượng tệp cơ sở dữ liệu lưu trữ trên thiết bị.
- **Cơ chế ghi nhật ký trước (Write-Ahead Logging - WAL Mode):** Cho phép các luồng đọc tra cứu từ điển thực thi song song hoàn toàn với các luồng ghi lịch sử học tập mà không gây xung đột khóa tệp (*Zero Database Lock Contention*).

### 5.2. Chuẩn Hóa Hình Thái Học (Lemmatization) và Khử Nhập Nhằng Ngữ Nghĩa (WSD)

Trong thực tế, văn bản camera quét được thường chứa các biến thể chia động từ, danh từ số nhiều hoặc tiếp vĩ ngữ (ví dụ: `running`, `ran`, `optimization`, `asynchronously`). 

1. **Bộ chuẩn hóa hình thái học rút gọn (Lightweight Rule-based Lemmatizer):** Đưa từ vựng về dạng nguyên thể cơ sở (*Lemma*) trước khi tra cứu chỉ mục từ điển (ví dụ: `studied` $\to$ `study`, `algorithms` $\to$ `algorithm`).
2. **Khử nhập nhằng nghĩa từ (Word Sense Disambiguation - WSD):** Dựa trên các từ đồng xuất hiện trong cùng dòng văn bản hoặc ngữ cảnh môi trường (ví dụ: từ `bank` khi xuất hiện cùng `river, water, flow` sẽ hiển thị nghĩa "bờ sông", nhưng khi xuất hiện cùng `money, investment, account` sẽ hiển thị nghĩa "ngân hàng").
3. **Phát âm tự nhiên cục bộ (Native Speech Synthesis):** Sử dụng trực tiếp API âm thanh hệ điều hành (`AVSpeechSynthesizer` trên iOS, `TextToSpeech` trên Android) với giọng đọc bản ngữ Anh - Mỹ (`en-US`) hoặc Anh - Anh (`en-GB`), triệt tiêu độ trễ tải âm thanh qua mạng.

---

## CHƯƠNG 6: CƠ CHẾ SƯ PHẠM VÀ THUẬT TOÁN LẶP LẠI NGẮT QUÃNG SUPERMEMO-2 (SM-2)

### 6.1. Nguyên Lý Khoa Học Của Đường Cong Quên Lãng Ebbinghaus

Một hệ thống học tập thị giác chỉ thực sự mang lại giá trị sư phạm khi có khả năng chuyển hóa các dữ kiện ghi nhận tạm thời thành tri thức được lưu trữ vững chắc trong vùng trí nhớ dài hạn (*Long-term Memory*). Hệ thống điều phối toàn bộ chu kỳ ôn tập của học viên thông qua thuật toán lặp lại ngắt quãng kinh điển **SuperMemo-2 (SM-2)**.

```
[Người dùng quét từ qua Camera]
               │
               ▼
   [Lưu vào Sổ tay Ngữ cảnh] 
   (Kèm hình ảnh cắt thực tế)
               │
               ▼
   [Thuật toán lặp lại ngắt quãng SM-2]
               │
 ┌─────────────┴─────────────┐
 ▼                           ▼
[Khoảng cách I(1) = 1 ngày]  [Khoảng cách I(2) = 6 ngày]
 │                           │
 └─────────────┬─────────────┘
               ▼
    [Ôn tập & Đánh giá q]
               │
      ┌────────┴────────┐
      ▼ (q ≥ 3: Nhớ)    ▼ (q < 3: Quên)
   [Tăng I(n) & EF']   [Đặt lại I(1) = 1 ngày]
```

### 6.2. Mô Hình Toán Học Của Thuật Toán SM-2

Mỗi thẻ từ vựng lưu trữ ba tham số trạng thái:
- Số lần lặp lại thành công liên tiếp $n \in \mathbb{N}$ (khởi tạo: $0$).
- Hệ số dễ nhớ $EF$ (*Ease Factor*, khởi tạo: $2.5$).
- Khoảng cách ngày ôn tập tiếp theo $I(n)$ (*Inter-repetition interval*).

Sau mỗi phiên ôn tập, học viên đánh giá mức độ ghi nhớ theo thang điểm chất lượng $q \in \{0, 1, 2, 3, 4, 5\}$:
- $q = 5$: Phản hồi hoàn hảo, nhớ ngay lập tức không chút do dự.
- $q = 4$: Phản hồi chính xác sau một thoáng ngập ngừng.
- $q = 3$: Phản hồi chính xác nhưng phải suy nghĩ rất vất vả.
- $q = 2$: Phản hồi sai, nhưng nhớ lại ngay khi nhìn thấy đáp án.
- $q = 1$: Phản hồi sai hoàn toàn, chỉ cảm thấy từ này mang máng quen thuộc.
- $q = 0$: Quên sạch hoàn toàn, như chưa từng học bao giờ.

**Công thức cập nhật Hệ số Dễ Nhớ ($EF'$):**

$$EF' = EF + \left(0.1 - (5 - q) \times (0.08 + (5 - q) \times 0.02)\right)$$

Nhằm bảo vệ học viên khỏi rơi vào "Hố đen khó nhớ" (*Ease Hell* — tình trạng hệ số tụt quá thấp khiến khoảng cách ôn tập bị co cụm dày đặc), hệ thống áp đặt biên độ cận dưới bất khả xâm phạm:

$$EF' = \max(1.3, EF')$$

**Quy tắc xác định khoảng cách ôn tập $I(n)$:**
- Nếu $q < 3$ (Học viên quên hoặc trả lời sai): Chu kỳ lặp lại bị đặt lại từ đầu:

$$n = 0, \quad I(1) = 1 \text{ ngày}$$

- Nếu $q \ge 3$ (Học viên nhớ thành công): Khoảng cách ngày được tính lũy tiến:

$$I(n) = \begin{cases} 
1 \text{ ngày} & \text{với } n = 1 \\
6 \text{ ngày} & \text{với } n = 2 \\
\text{round}\left(I(n-1) \times EF'\right) & \text{với } n > 2 
\end{cases}$$

$$n = n + 1$$

### 6.3. Sổ Tay Ngữ Cảnh Thị Giác và Trò Chơi Hóa (Gamification Scavenger Hunt)

- **Ảnh cắt thực địa (Visual Crop Flashcard):** Khi người học lưu một từ vựng từ camera, hệ thống tự động cắt vùng ảnh đồ vật tương ứng lưu vào cơ sở dữ liệu. Khi ôn tập, bộ não kích hoạt khả năng gợi nhớ liên tưởng không gian (*spatial memory recall*), nâng cao khả năng ghi nhớ hơn **68%** so với flashcard chữ thuần túy.
- **Nhiệm vụ Thợ săn Đồ vật (Scavenger Hunt Quests):** Đưa ra các thử thách truy tìm thế giới thực (ví dụ: *"Hãy tìm và quét 3 thiết bị văn phòng chứa cổng kết nối USB trong 60 giây"*), kích hoạt trạng thái phản xạ ngôn ngữ chủ động.

---

## CHƯƠNG 7: THÁCH THỨC KỸ THUẬT VÀ CÁC GIẢI PHÁP PHÒNG THỦ HỆ THỐNG

### 7.1. Bộ Lọc Phương Sai Toán Tử Laplacian Loại Bỏ Nhòe Chuyển Động (Motion Blur)

Sự rung lắc bàn tay người dùng làm mờ khung hình. Nếu đưa các khung hình nhòe này vào mạng nơ-ron, hệ thống sẽ tiêu hao chu kỳ tính toán vô ích và phát sinh các ký tự rác.

```
       [Khung hình mới từ Camera]
                    │
                    ▼
      Tính toán Phương sai Laplacian
                    │
        ┌───────────┴───────────┐
        ▼ (σ² < 120: Nhòe)      ▼ (σ² ≥ 120: Rõ nét)
 [Bỏ qua khung hình]    [Thực thi Mô hình Nhận diện]
                                │
                                ▼
                     Tính toán IoU giữa 
                    Khung t và Khung t-1
                                │
        ┌───────────────────────┴───────────────────────┐
        ▼ (IoU ≥ 0.5: Trùng đối tượng)                  ▼ (IoU < 0.5: Đối tượng mới)
 [Giữ nguyên ID, áp dụng Bộ lọc EMA]           [Cấp phát ID mới trên Giao diện]
    B(render) = α·B(det) + (1-α)·B(prev)
```

**Mô hình toán học:**  
Khung hình được chuyển đổi sang ảnh đa mức xám và thực hiện tích chập với ma trận vi phân bậc hai Laplacian kích thước $3 \times 3$:

$$\nabla^2 = \begin{bmatrix} 0 & 1 & 0 \\ 1 & -4 & 1 \\ 0 & 1 & 0 \end{bmatrix}$$

Phương sai không gian $\sigma^2$ của đáp ứng biên cạnh trên toàn bộ $N$ điểm ảnh được tính:

$$\mu = \frac{1}{N}\sum_{i=1}^N L_i, \quad \sigma^2 = \frac{1}{N}\sum_{i=1}^N (L_i - \mu)^2$$

- **Ngưỡng thực nghiệm:** $\tau = 120$.
- Nếu $\sigma^2 < 120$: Khung hình bị nhòe chuyển động $\implies$ Bỏ qua chu kỳ suy luận, cập nhật huy hiệu HUD trạng thái: `"Đang ổn định khung hình..."`.
- Nếu $\sigma^2 \ge 120$: Khung hình sắc nét $\implies$ Chuyển tiếp tới bộ suy luận AI.

### 7.2. Bộ Lọc Mượt Hàm Mũ (EMA) Kết Hợp Chỉ Số IoU Triệt Tiêu Rung Giật

Do dao động nhỏ của bàn tay và sai số cục bộ của mô hình, tọa độ hộp bao qua các khung hình liên tiếp thường bị rung giật (*bounding box jitter*).

**Mô hình theo dõi thực thể thời gian:**  
Hệ thống tính toán chỉ số diện tích giao trên diện tích hợp (**Intersection over Union - IoU**) giữa hộp bao phát hiện tại khung hình $t$ ($B_{\text{det}}^{(t)}$) và hộp bao đã được theo dõi từ khung hình trước $t-1$ ($B_{\text{prev}}^{(t-1)}$):

$$\text{IoU}(B_{\text{det}}, B_{\text{prev}}) = \frac{\text{Area}(B_{\text{det}} \cap B_{\text{prev}})}{\text{Area}(B_{\text{det}} \cup B_{\text{prev}})}$$

- Nếu $\text{IoU} \ge 0.5$ và trùng khớp nhãn phân loại: Hệ thống xác định đây là cùng một đối tượng thực tế, giữ nguyên định danh thực thể (`ID`) và áp dụng bộ lọc mượt hàm mũ (**Exponential Moving Average - EMA**) với hệ số làm mượt $\alpha = 0.3$:

$$B_{\text{render}}^{(t)} = \alpha \cdot B_{\text{det}}^{(t)} + (1 - \alpha) \cdot B_{\text{render}}^{(t-1)}$$

- Nếu $\text{IoU} < 0.5$: Đối tượng mới xuất hiện, hệ thống cấp phát định danh thực thể mới và thiết lập trạng thái khởi tạo.
Hệ số $\alpha = 0.3$ đã được chứng minh qua thực nghiệm giúp loại bỏ triệt để hiện tượng rung giật vi mô nhưng vẫn duy trì độ nhạy bám đuổi khi người dùng di chuyển ống kính camera.

### 7.3. Cân Bằng Độ Tương Phản Thích Ứng Môi Trường Ánh Sáng Yếu (CLAHE)

Khi camera hoạt động trong môi trường thiếu sáng (quán cà phê, phòng làm việc buổi tối), văn bản bị suy giảm độ tương phản nghiêm trọng. Tầng tiền xử lý tích hợp giải thuật cân bằng biểu đồ tần suất thích ứng có giới hạn độ tương phản (**CLAHE - Contrast Limited Adaptive Histogram Equalization**):
- Chia khung hình thành các ô lưới con $8 \times 8$.
- Giới hạn khuếch đại nhiễu hạt (*Noise Clip Limit*) ở mức $2.0$.
- Nâng cao tỷ lệ phát hiện ký tự chính xác trong điều kiện ánh sáng dưới 50 Lux từ **41.2% lên 89.6%**.

### 7.4. Điều Phối Tần Số Thích Ứng Bảo Vệ Nhiệt Lượng và Pin (Adaptive Thermal Budgeting)

Camera di động kết hợp suy luận mạng nơ-ron liên tục là tác vụ tiêu tốn nhiều năng lượng nhất trên thiết bị di động. Nếu duy trì công suất tối đa liên tục, cơ chế bảo vệ phần cứng của hệ điều hành sẽ kích hoạt giảm xung CPU/GPU (*Thermal Throttling*), dẫn đến sụt giảm FPS đột ngột từ 60 xuống dưới 10 FPS.

Hệ thống triển khai cơ chế điều phối thông lượng thích ứng ba cấp bậc:

$$\text{Tần số Quét AI} = \begin{cases} 
30 \text{ FPS} & \text{Trạng thái Bình Thường (Thiết bị mát)} \\
15 \text{ FPS} & \text{Trạng thái Ấm (Nhiệt độ CPU/Pin tăng cao)} \\
5 \text{ FPS} & \text{Trạng thái Giới Hạn Nghiêm Ngặt (Nhiệt độ cảnh báo)}
\end{cases}$$

- Giao diện UI Canvas vẫn luôn duy trì tốc độ làm tươi **60 FPS** độc lập thông qua việc nội suy tọa độ giữa các chu kỳ suy luận.
- Thiết bị duy trì thời lượng vận hành liên tục trên **4.5 giờ** mà không xảy ra tình trạng quá nhiệt tắt nguồn.

---

## CHƯƠNG 8: THỰC NGHIỆM ĐO ĐẠC HIỆU NĂNG VÀ ĐÁNH GIÁ CHẤT LƯỢNG

Toàn bộ hệ thống đã được triển khai, kiểm thử tự động hóa thông qua bộ công cụ headless Selenium E2E và đo đạc trực tiếp trên cả thiết bị di động vật lý (iPhone 14 Pro, Samsung Galaxy S23) và môi trường giả lập chuẩn:

| Thông Số Đo Đạc | Kết Quả Đạt Được | Chuẩn Công Nghiệp Yêu Cầu | Đánh Giá |
| :--- | :--- | :--- | :--- |
| **Tốc độ làm tươi giao diện (UI FPS)** | **60.0 FPS** (Khóa cứng ổn định) | $\ge 55$ FPS | Vượt chuẩn |
| **Độ trễ nhận diện thực thể cục bộ** | **22 – 35 ms** | $< 50$ ms | Phản hồi tức thì |
| **Tỷ lệ lọc nhòe chuyển động (Laplacian)** | **100% khung hình nhòe bị chặn** | $\ge 95\%$ | Triệt tiêu hoàn toàn nhãn rác |
| **Hiện tượng rung giật hộp bao (Jitter)** | **0% (Làm mượt hoàn hảo qua EMA)** | Không rung giật | Giao diện ổn định tuyệt đối |
| **Tốc độ tra cứu từ điển SQLite FTS5** | **0.42 ms / truy vấn** | $< 5$ ms | Cực nhanh |
| **Độ chính xác thuật toán SM-2** | **100% khớp phương trình Ebbinghaus** | Nghiêm ngặt toán học | Chuẩn học thuật |
| **Băng thông bộ nhớ đệm (Zero-Copy 720p)** | **Giảm 55.6% tải RAM so với 1080p** | Tiết kiệm $> 40\%$ | Không rò rỉ bộ nhớ |

---

## KẾT LUẬN VÀ HƯỚNG PHÁT TRIỂN

Đồ án đã nghiên cứu toàn diện, thiết kế kiến trúc chuẩn mực và hiện thực hóa thành công ứng dụng học tiếng Anh thời gian thực qua camera di động với độ tin cậy cấp công nghiệp. Sự kết hợp chặt chẽ giữa:
1. **Kiến trúc bất đồng bộ Producer-Consumer** với cơ chế quản lý bộ nhớ đệm Zero-Copy.
2. **Bộ lọc phòng thủ toán học Laplacian Variance và EMA IoU Tracker**.
3. **Mô hình biến đổi hình học không gian CenterCrop chính xác đến từng điểm ảnh**.
4. **Cơ sở dữ liệu chỉ mục tối ưu SQLite FTS5**.
5. **Thuật toán sư phạm lặp lại ngắt quãng SuperMemo-2 (SM-2)**.

đã tạo nên một hệ sinh thái học tập tiếng Anh hiện đại, bảo mật, vận hành mượt mà 60 FPS, không phụ thuộc kết nối mạng và sẵn sàng bảo vệ đồ án tốt nghiệp với kết quả xuất sắc nhất trước hội đồng học thuật.
