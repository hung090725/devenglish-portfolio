# BASELINE PROFILE PREP PLAN

**Trạng thái:** Không bắt buộc trong Sprint 21, là định hướng (Future plan) cho các phiên bản sau.

## 1. Baseline Profile là gì?
Baseline Profile là tính năng của Android nhằm cải thiện tốc độ khởi động ứng dụng (app startup) và tốc độ khung hình (frame rate) mượt mà hơn bằng cách biên dịch trước các đoạn code "nóng" (hot paths) quan trọng nhất.

## 2. Các hành trình cốt lõi (Critical User Journeys - CUJs)
Khi xây dựng Baseline Profile, cần ghi lại các CUJs sau:
- **App Startup:** Từ lúc chạm vào icon ngoài màn hình đến khi hiển thị đầy đủ màn hình Home.
- **Home:** Cuộn lên/xuống danh sách bài học đề xuất.
- **Lessons / LessonDay:** Truy cập một bài học chi tiết, tải dữ liệu lộ trình.
- **Dictionary Search:** Gõ chữ tìm kiếm từ vựng và cuộn kết quả.
- **Review Center:** Mở bộ bài ôn tập.
- **Practice Center:** Mở các công cụ học tập.

## 3. Kế hoạch triển khai (Future Implementation Steps)
1. Thêm module `macrobenchmark` vào dự án.
2. Viết các test script để tự động hóa các thao tác cho các CUJs ở phần 2.
3. Chạy `gradlew :macrobenchmark:baselineProfile` để tự động thu thập `baseline-prof.txt`.
4. Áp dụng file `baseline-prof.txt` vào `src/main` của thư mục `app`.
5. Đóng gói AAB và test lại hiệu năng khởi động.

*(Tài liệu này được dùng làm tham chiếu để không phá vỡ tính ổn định của mã nguồn hiện tại trong giai đoạn Lockdown)*
