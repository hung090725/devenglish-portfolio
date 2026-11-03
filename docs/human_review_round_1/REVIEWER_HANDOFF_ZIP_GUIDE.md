# REVIEWER HANDOFF ZIP GUIDE

Tài liệu này hướng dẫn bạn (người gửi gói tin) cách trích xuất file để gửi cho Reviewer mà vẫn giữ an toàn tuyệt đối cho hệ thống cốt lõi của dự án.

## 1. CÁC FILE NÊN GỬI TRONG ZIP
Chỉ nén các file sau vào file `EnglishPath_ReviewPack_Round1.zip` để gửi cho chuyên gia:
- `README_REVIEW_ROUND_1.md`
- `REVIEW_ITEMS_PRIORITY_1_CRITICAL_HIGH.md`
- `REVIEW_ITEMS_PRIORITY_2_MEDIUM.md`
- `REVIEW_ITEMS_PRIORITY_3_LOW.md`
- `REVIEW_FEEDBACK_FORM_ROUND_1.csv`
- `REVIEW_DECISION_GUIDE.md`
- `REVIEW_EXAMPLES_OK_FIX_UNSURE.md`

## 2. CÁC FILE TUYỆT ĐỐI KHÔNG GỬI
Vì lý do bảo mật và tránh làm Reviewer bối rối, **KHÔNG** đính kèm:
- Toàn bộ thư mục mã nguồn (`app/src/main/...`) nếu không cần thiết.
- File Keystore (`.jks`) và cấu hình ký (`signing.properties`).
- Bản sao lưu cá nhân (`backup/`) hoặc báo cáo Diagnostics.
- Không gửi file APK nếu Reviewer chỉ có nhiệm vụ duyệt nội dung trên file Text/CSV.

## 3. CÁCH NHẬN LẠI FEEDBACK
- Yêu cầu Reviewer gửi trả lại duy nhất file `REVIEW_FEEDBACK_FORM_ROUND_1.csv` đã điền hoàn tất.
- Khi nhận được file, đặt nó vào thư mục `docs/human_review_round_1/import/`.
- Chạy công cụ Parser & Validator (chức năng trong App) để tự động đối chiếu và áp dụng Patch.
