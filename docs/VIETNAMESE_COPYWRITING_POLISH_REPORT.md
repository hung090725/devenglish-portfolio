# VIETNAMESE COPYWRITING POLISH REPORT

**Ngày đánh giá:** (Sprint 21)

## 1. Mục tiêu
Rà soát toàn bộ các chuỗi văn bản (strings) tiếng Việt trong ứng dụng, đảm bảo thân thiện, dễ hiểu, tránh dùng từ ngữ quá kỹ thuật hoặc dịch máy.

## 2. Các thay đổi chính đã thực hiện
- **Màn hình Home:** 
  - Đã đổi thông báo khi không có bài học từ "Tuyệt vời! Bạn đã hoàn thành tất cả bài học đề xuất." thành "Tuyệt vời! Bạn đã hoàn thành các bài học đề xuất. Hãy ôn tập lại hoặc tiếp tục lộ trình chính nhé." để hướng dẫn người dùng bước tiếp theo.
- **Màn hình Practice Center:**
  - Đã Việt hóa toàn bộ các nút bấm và nhóm tính năng (VD: "Skills" -> "Kỹ năng", "Language Tools" -> "Công cụ ngôn ngữ", "Review Center" -> "Trung tâm Ôn tập").
  - Loại bỏ các tên tiếng Anh khó hiểu đối với người mới học (A0).
- **Thông báo lỗi & Quyền truy cập:**
  - Quy chuẩn lại cách xin quyền Micro: "Bạn chưa cấp quyền micro. App cần quyền này để luyện nói." thay vì "Permission denied".
  - Quy chuẩn lỗi Backup: "Không thể khôi phục. Nội dung backup không đúng định dạng." thay vì "Import failed".

## 3. Các File Đã Kiểm Tra & Cập Nhật
- `fragment_home.xml`
- `fragment_practice.xml`
- `strings.xml`

Mọi văn bản hiển thị trên UI đã đạt chuẩn Release Candidate.
