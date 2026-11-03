# EnglishPath AI 0.9.4-beta (Solo Release Candidate)

**Ngày phát hành:** [YYYY-MM-DD]
**Người phát hành:** Developer Nội Bộ
**Ghi chú:** Đây là bản thử nghiệm Release Candidate (ứng cử viên phát hành) dành cho việc test trên các máy thật cá nhân. Bản này **CHƯA CÓ TESTER BÊN NGOÀI** và chưa sẵn sàng gọi là "Production-ready" nếu chưa có Log test thực tế.

## 🚀 Tính năng nổi bật
- Giữ nguyên các nội dung học thuật mức độ A0, A1, A2, B1.
- Không có nội dung mới (Đóng băng tính năng lớn).
- Cập nhật cơ chế **In-App Real Device Test Session** cho phép Developer tự tick PASS/FAIL/BLOCKED ngay trong ứng dụng và xuất báo cáo nội bộ.

## 🛠 Sửa lỗi & Cải thiện
- Cải thiện quản lý quyền (Permissions) trên máy thật, đặc biệt là quyền Ghi âm (Microphone) và Thông báo (Notifications). Thêm hướng dẫn mở Cài đặt nếu người dùng từ chối vĩnh viễn.
- Xử lý an toàn vòng đời Text-to-Speech (TTS) khi thoát màn hình đột ngột.
- Rà soát tính toàn vẹn của chức năng Backup & Restore bằng file JSON.

## ⚠️ Lưu ý cho Tester
- Vui lòng cài đặt qua **APK ngoài Play** (app-release.apk). Xem thêm tài liệu `APK_INSTALL_VERIFICATION_GUIDE.md`.
- Nếu có dữ liệu học tập cũ, vui lòng **Export Backup JSON** TRƯỚC KHI gỡ phiên bản hiện tại.
- Ứng dụng có thể có một vài dòng UI bị sai lệch nhẹ trên một số màn hình kích thước dị biệt. Hãy báo lại nếu thấy giao diện bị cắt không thể thao tác được.
