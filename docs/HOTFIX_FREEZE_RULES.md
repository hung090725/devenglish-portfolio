# Hotfix Freeze Rules

*Tài liệu quy định đóng băng tính năng trong giai đoạn Solo Release Candidate (v0.9.x-beta).*

## Quy Định Chung
- **KHÔNG** thêm level mới (ví dụ: Không mở B2).
- **KHÔNG** thêm tính năng lớn, flow mới.
- **KHÔNG** thay đổi kiến trúc ứng dụng (Ví dụ: Không chuyển sang Jetpack Compose, không thay đổi MVVM, không đổi sang backend online).
- **KHÔNG** thay đổi applicationId hoặc package name.
- **KHÔNG** sửa dữ liệu học hàng loạt nếu không có lỗi từ Data Quality Validator.
- **KHÔNG** refactor cấu trúc thư mục quy mô lớn.

## Các Loại Lỗi Được Phép Sửa (Hotfix Allowed)
Chỉ được phép sửa đổi code trong các trường hợp sau:
1. Lỗi crash ứng dụng.
2. Lỗi xin quyền (Permissions) trên máy thật.
3. Lỗi Backup/Restore (Dữ liệu không khôi phục được hoặc báo lỗi JSON).
4. Data Corruption (Dữ liệu bị sai lệch, ghi đè không mong muốn).
5. Giao diện (UI) bị cắt xén quá nặng trên máy thật.
6. Tính năng TTS hoặc Microphone không chạy và không có cơ chế fallback.
7. Lỗi Worker chạy sai lịch (Reminder).
8. Lỗi Diagnostics không xuất được text.

## Quy Định Sửa Đổi Database (Nếu có)
- Tối kỵ việc sửa đổi schema Database (Entities).
- NẾU BẮT BUỘC PHẢI SỬA: Phải nâng `DATABASE_VERSION` và viết lệnh `Migration` rõ ràng trong `DatabaseMigrations.java`.
- KHÔNG dùng `fallbackToDestructiveMigration` để xóa dữ liệu người dùng trừ khi là bản Debug rác.

## Bắt Buộc Sau Khi Hotfix
- Mọi hotfix phải có test (Unit Test / UI Automator Test) hoặc ít nhất phải checklist lại bằng tay trên máy thật.
- Sau khi hotfix, **PHẢI CHẠY FULL QA GATE** (`run_solo_qa_gate.bat`) và đảm bảo 100% xanh thì mới được release phiên bản mới.
