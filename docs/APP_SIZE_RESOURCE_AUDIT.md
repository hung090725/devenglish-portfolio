# APP SIZE & RESOURCE AUDIT

**Ngày đánh giá:** (Sprint 21)

## 1. Mục tiêu
Kiểm tra dung lượng ứng dụng (APK/AAB) và phát hiện các tài nguyên thừa thãi, rác (unused resources) để tối ưu hóa trước bản Release Candidate.

## 2. Kích thước (Ước tính)
- **APK Debug:** ~ 15-20 MB (chứa các công cụ gỡ lỗi, báo cáo).
- **APK Release:** ~ 8-12 MB (sau khi áp dụng ProGuard/R8 minify).
- **AAB Release:** Kích thước tải về thực tế từ Store dự kiến < 10 MB.

## 3. Quản lý Tài nguyên (Resources)
- Các tài nguyên như âm thanh (TTS engine local), ảnh tĩnh, icon SVG (VectorDrawable) đều đã được nén tối đa.
- Loại bỏ các icon bị lặp (duplicate drawables) hoặc không còn sử dụng trong mã nguồn.
- Tài nguyên strings.xml đã được dọn dẹp các import thừa.

## 4. Tối ưu Dependencies
- Đã bật R8 (`isMinifyEnabled = true`) trong `build.gradle` (nếu áp dụng cho môi trường Release). 
- Các dependency dùng cho debug (như Robolectric, Mockito) chỉ được cấu hình dưới dạng `testImplementation` và `androidTestImplementation`, nên không ảnh hưởng đến size của app release.

## 5. Hành động tiếp theo
- Khuyến nghị kích hoạt `shrinkResources = true` cùng `isMinifyEnabled = true` trước khi ký AAB đẩy lên Store.
- Tiếp tục theo dõi kích thước database `Room` để tránh phình to khi có thêm seed data mới.
