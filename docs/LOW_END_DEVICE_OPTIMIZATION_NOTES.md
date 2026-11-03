# Ghi Chú Tối Ưu Thiết Bị Yếu (Low-End Device Optimization Notes)

## Các bước đã thực hiện để hỗ trợ máy đời cũ
1. **RecyclerView Optimization**: Tái sử dụng View hiệu quả, không load ảnh HD, giới hạn animation phức tạp trên các danh sách dài.
2. **Text-To-Speech (TTS)**: Xử lý fallback gracefully nếu máy bị lỗi TTS hoặc không có bộ nhớ đệm cho TTS.
3. **Database Caching**: Bộ nhớ đệm giới hạn ở mức đủ dùng, không lưu cache quá nhiều làm tràn RAM trên các thiết bị chỉ có 2GB RAM.
4. **Layout Hierarchy**: 100% sử dụng ConstraintLayout và LinearLayout đơn giản, độ sâu phân cấp layout thường ở mức <= 3 để tránh lãng phí vòng lặp tính toán giao diện (measure/layout cycle).

## Kết luận
Tương thích ổn định từ Android 7 (API 24) trở lên, phù hợp cho học sinh/sinh viên sử dụng smartphone giá rẻ.
