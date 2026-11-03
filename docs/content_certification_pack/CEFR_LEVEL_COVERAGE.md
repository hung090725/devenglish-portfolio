# Tỷ lệ Phủ CEFR (CEFR Level Coverage)

Dự án hiện có 6 cấp độ CEFR chuẩn hóa được lưu trong `SeedDataProvider`.
1. **A0 (Starter)**: Nền tảng (Bảng chữ cái, Phonics, từ đơn giản).
2. **A1 (Beginner)**: Giao tiếp nền (Từ vựng thường ngày, chia động từ cơ bản).
3. **A2 (Elementary)**: Giao tiếp du lịch, hỏi đường, câu ghép sơ cấp.
4. **B1 (Intermediate)**: Giao tiếp công sở, email, miêu tả chi tiết.
5. **B2 (Upper-Intermediate)**: Đảo ngữ, thành ngữ, mệnh đề quan hệ phức tạp.
6. **C1 (Advanced - Foundation)**: Academic reading, văn phong học thuật, cấu trúc tiệm cận người bản xứ.

Tất cả dữ liệu này đều đã trải qua bộ `CefrAuditor` để xác minh số lượng phân bổ không bị quá tải ở 1 level cụ thể.
