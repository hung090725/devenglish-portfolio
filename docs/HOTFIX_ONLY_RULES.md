# Quy Tắc Hotfix-Only (Hotfix-Only Rules)

Ở giai đoạn LTS, chúng ta áp dụng quy tắc "Hotfix-Only" (Chỉ sửa lỗi khẩn cấp).

## Điều kiện để được gọi là Hotfix
Một bản sửa lỗi chỉ được chấp nhận merge vào nhánh chính nếu thỏa mãn:
1. Giải quyết một lỗi gây Crash (Force Close) cho người dùng.
2. Giải quyết một lỗi làm mất dữ liệu người dùng (Backup/Restore hỏng, DB corrupt).
3. Vá lỗ hổng bảo mật nghiêm trọng do các thư viện bên thứ ba.
4. Sửa sai lệch cực độ về mặt nội dung (Ví dụ: Câu hỏi A mà đáp án luôn báo sai).

Tuyệt đối KHÔNG gộp việc sửa màu nút bấm, đổi vị trí text hay làm mới giao diện vào một bản Hotfix.
