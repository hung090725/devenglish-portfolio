# Những Gì Cần Lưu Trữ Sau Khi Build (What to Archive)

Khi bạn muốn đóng băng dự án hoàn toàn (ví dụ: cất vào tủ sau Sprint 39), bạn cần lưu trữ:

1. **Source Code**: ZIP toàn bộ thư mục `apptienganh` (nhớ xóa thư mục `build`, `.gradle`, `.idea` để giảm dung lượng).
2. **Artifacts**: File `app-release-unsigned.apk`.
3. **Backup File**: Chạy app, vào Cài đặt -> Export Backup Data và cất file JSON `EnglishPath_Backup.json` lưu tiến trình học của riêng bạn.
4. **Keystore**: File khóa bí mật (nằm ngoài codebase).

Bốn tài nguyên này cấu thành một "Bản Lưu Trữ Lịch Sử Trọn Vẹn" (Complete Historical Archive) cho Personal Gold Master.
