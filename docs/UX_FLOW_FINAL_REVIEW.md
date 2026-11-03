# UX FLOW FINAL REVIEW

**Ngày đánh giá:** (Sprint 21)
**Mục tiêu:** Rà soát toàn bộ luồng người dùng (User Flows) chính, đảm bảo trải nghiệm người dùng không bị gãy, có đủ trạng thái empty/error, và các nút điều hướng rõ ràng. KHÔNG thực hiện redesign lớn, chỉ tinh chỉnh nhỏ (polish).

## 1. Các Luồng Chính Đã Kiểm Tra
- **First launch & Onboarding:** Có nút Skip/Tiếp tục rõ ràng, giải thích lợi ích app.
- **Placement test:** Luồng làm bài mượt mà, có thông báo khi hoàn thành.
- **Home:** (Sẽ được đơn giản hóa trong Phase riêng) Đảm bảo CTA chính nổi bật.
- **Lessons (A0-C1):** Có nút quay lại, trạng thái bài đã học/chưa học rõ ràng.
- **Practice Center:** Phân nhóm logic, icon/title dễ hiểu.
- **Review Center:** Empty state rõ ràng nếu chưa có dữ liệu.
- **Dictionary Pro:** Hướng dẫn tìm kiếm khi mới mở.
- **Grammar Lab, Role-play Pro, Skills Trainers (Speaking, Listening, Writing, Reading):** Các công cụ luyện tập có hướng dẫn và nút thoát rõ.
- **Exam Simulator:** Cảnh báo trước khi nộp bài.
- **Goal setup & Profile:** Cho phép cập nhật thông tin dễ dàng.
- **Settings & Backup/Restore:** Cảnh báo an toàn dữ liệu đủ mạnh.
- **Diagnostics/Solo QA:** Ẩn với người dùng cuối, chỉ dành cho QA/Tester.

## 2. Các Lỗi UX Phổ Biến Cần Khắc Phục (Thực thi ở các Phase sau)
- **Thiếu Empty State:** Các tính năng như Error Notebook, Review Center cần có thông báo hướng dẫn người mới khi danh sách rỗng (ví dụ: "Bạn chưa có từ vựng nào cần ôn tập. Hãy học một bài mới nhé!").
- **Nút Back:** Đảm bảo mọi màn hình (Activity/Fragment) đều có nút "Quay lại" trên toolbar hoặc giao diện.
- **Spacing:** Điều chỉnh khoảng cách các thẻ (card) trên Home và Practice Center để không bị dính vào nhau.
- **Instruction (Hướng dẫn):** Thêm subtitle ngắn gọn cho các module khó hiểu.

## 3. Hành Động Tiếp Theo
- Đơn giản hóa màn hình Home (Phase 3).
- Cấu trúc lại Practice Center (Phase 4).
- Cập nhật text Tiếng Việt thân thiện (Phase 5).
- Đồng nhất Design System (Phase 6).
