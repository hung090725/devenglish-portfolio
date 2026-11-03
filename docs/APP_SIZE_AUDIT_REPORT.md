# Báo Cáo Dung Lượng Ứng Dụng (App Size Audit Report)

## Hiện trạng dung lượng
- **App Size (APK)**: Dưới 15MB.
- **Dữ liệu đính kèm**: Toàn bộ dữ liệu chữ (Text content) lưu vào Seed, file siêu nhẹ. Không nhúng video offline hay hàng ngàn file audio MP3 (hệ thống tự tổng hợp TTS cục bộ để giảm dung lượng).
- **Thư viện bên thứ ba**: Đã tinh giản tối đa, chỉ giữ các Dependency thực sự cần thiết (Room, Navigation, ViewModel).

## Kết luận
Bản build rất gọn nhẹ, dễ dàng tải qua mạng 3G/4G và tối ưu không gian lưu trữ cho người dùng lâu dài.
