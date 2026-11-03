# RELEASE NOTES: ENGLISHPATH AI v1.2.2-beta

**Ngày phát hành nội bộ:** Sprint 21 Lockdown

## 1. Tóm tắt
Bản cập nhật `1.2.2-beta` tập trung vào quá trình "Lockdown" – đóng băng tính năng, ổn định hệ thống và hoàn thiện trải nghiệm người dùng cuối. Không có tính năng (feature) mới lớn nào được thêm vào, thay vào đó là sự tập trung tối đa vào độ mượt mà, thân thiện và không lỗi.

## 2. Thay đổi chính
- **Cải thiện Giao diện (UI/UX Polish):**
  - Màn hình Home và Practice Center đã được "dọn dẹp", hiển thị gọn gàng, các nút bấm chuyển sang tiếng Việt 100% giúp người mới bắt đầu (A0) dễ hiểu.
  - Các thông báo lỗi khô khan như "Permission denied", "Import failed" đã được thay bằng ngôn ngữ tự nhiên.
  - Hợp nhất Design System (màu sắc, bo góc card, typography) trên mọi màn hình, bao gồm cả các module Pro (Dictionary, Role-play).

- **Khả năng Truy cập (Accessibility):**
  - Gắn nhãn (labels) cho toàn bộ các nút Icon (Micro, Loa, Quay lại).
  - Tăng độ tương phản chữ trên nền để dễ đọc hơn.
  - Chuẩn hóa touch target (khu vực bấm tối thiểu 48dp).

- **Chất lượng Mã nguồn (Code Quality & Performance):**
  - Vượt qua vòng đánh giá tĩnh (Static Code Check) với 0 Errors.
  - Dependencies được rà soát, loại bỏ cấu hình dư thừa, giảm nhẹ kích thước đóng gói.
  - Vượt qua quá trình Build AndroidTest, Unit Test và Lint (Solo QA Gate).

## 3. Hành động tiếp theo cho Developer
Bản dựng AAB đã sẵn sàng trong thư mục `/app/build/outputs/bundle/release/app-release.aab`. Bạn có thể sử dụng file này để distribute cho Tester hoặc nạp vào thiết bị kiểm thử thông qua Bundletool.
