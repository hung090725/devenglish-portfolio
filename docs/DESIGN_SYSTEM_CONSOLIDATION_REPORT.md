# DESIGN SYSTEM CONSOLIDATION REPORT

**Ngày đánh giá:** (Sprint 21)

## 1. Trạng thái Hệ thống Thiết kế (Design System)
Ứng dụng EnglishPath AI hiện tại đã áp dụng một hệ thống thiết kế nhất quán và tuân thủ các nguyên tắc Material Design / UX hiện đại.

## 2. Màu sắc (Colors & Themes)
- **Màu chủ đạo:** Sử dụng tông màu Xanh Tím gradient (Indigo 500 - `purple_start` đến Indigo 600 - `purple_end`).
- **Màu văn bản:** Có hệ thống phân cấp rõ ràng (`text_primary`, `text_secondary`) giúp đảm bảo khả năng đọc (readability) trong mọi màn hình.
- **Màu nền:** `window_background` và `surface_background` được quy chuẩn để hỗ trợ hiển thị tốt giữa các CardView.
- **Màu trạng thái (Semantic Colors):** `success` (Xanh lá), `warning` (Vàng cam), `error` (Đỏ) đã được sử dụng đồng bộ cho các bài kiểm tra, tiến độ và thông báo lỗi.

## 3. Khung Giao Diện (Layout & Spacing)
- **CardView:** Bán kính góc (`cardCornerRadius`) được giữ thống nhất là 16dp - 20dp cho các khối nội dung lớn.
- **Padding/Margin:** Giữ khoảng cách chuẩn (8dp, 16dp, 24dp) giữa các thành phần giao diện, tránh tình trạng giao diện bị quá chật chội hoặc rời rạc.
- **Nút bấm (Buttons):** Chiều cao và bo góc (radius) nhất quán trên toàn app.

## 4. Các màn "Pro"
Các tính năng như Dictionary Pro, Role-play Pro đã được điều chỉnh để không bị lệch tông màu so với các màn hình cơ bản, duy trì cảm giác cao cấp nhưng không xa lạ.

Hệ thống thiết kế đã ổn định và sẵn sàng cho Release Candidate.
