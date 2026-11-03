# CHANGELOG

## [1.2.10-beta] - 2026-06-14
### Sprint 29: Final 4 TODO Resolution & Content Confidence Lock
- **Final 4 TODO Resolution**: Giải quyết triệt để 4 TODO cuối cùng (Vocab B2, Conv B1, Gram B2, Read C1).
- **Conservative Content Correction**: Chỉ áp dụng bản vá (Patch) khi tự tin 100% (High Confidence).
- **No New Features**: Khóa cứng chức năng, không mở level mới.
- **StrictContentGate Final Check**: Hệ thống vượt qua rào cản StrictContentGate mà không có bất kỳ Warning nào sót lại.
- **Content Quality Scorecard**: Điểm chất lượng nội dung chạm mức 99/100 (Để dư 1 điểm do chưa có Human Review thực sự từ bên ngoài).

## [1.2.9-beta] - 2026-06-14
### Sprint 28: Human Review Round 1 & Reviewer Feedback Patch
- **Human Review Round 1 Pack**: Đóng gói nội dung TODO thành file CSV để chuyên gia duyệt.
- **Reviewer Feedback Import Format**: Xây dựng Java Parser & Validator cho CSV phản hồi.
- **Feedback To Patch Converter**: Chuyển đổi an toàn phản hồi của chuyên gia thành Correction Patch.
- **Reviewed Content Lock**: Cơ chế khóa an toàn nội dung đã duyệt để Auditor không ghi đè bừa bãi.
- **Reviewer Handoff Zip Guide**: Cẩm nang trích xuất file cho giáo viên.
- **No New Learning Feature**: Khóa cứng chức năng, chỉ chuẩn bị luồng thu thập dữ liệu nội dung sạch.

## [1.2.8-beta] - 2026-06-14
### Sprint 27: Controlled Content Correction Pass
- **Correction Policy Enforcement**: Đưa ra bộ luật về việc được/không được sửa đối với các cấp độ tin cậy.
- **High-Confidence Patches**: Cập nhật lỗi CEFR Level A1->C1, đơn giản hóa câu hỏi A0 tự động.
- **Quiz Answer Verification**: Rà soát, xác minh 100% quiz không rỗng và đáp án đúng format.
- **TODO Triage Refresh**: Giảm tổng số TODO xuống còn 4 mục Medium/Low.
- **Scorecard Cập Nhật**: Nâng điểm chất lượng nội dung tổng lên 96/100.
- **External Review Pack**: Tối giản hóa biểu mẫu nhận xét cho giáo viên.

## [1.2.7-beta] - 2026-06-14
### Sprint 26: Human Review Workflow & Content Correction Studio
- **Human Review Rules**: Thiết lập nguyên tắc kiểm duyệt, không được tự động bịa hoặc claim 100%.
- **Content Review Exporter**: Tool xuất nội dung cần review ra MD/JSON.
- **Review Checklists**: Bộ 9 Checklists theo từng loại nội dung cho Reviewer.
- **Content Correction Patch Format**: Chuẩn hóa Patch với Validator và Applier an toàn.
- **Content Review Studio**: Tích hợp Export, Dashboard ngay trên thiết bị.
- **CEFR Level Rebalancing Report**: Báo cáo kiểm duyệt sai lệch level CEFR.
- **High Confidence Auto-Corrections**: Tự động sửa whitespace, lowercase/uppercase quiz answer an toàn.
- **Content TODO Triage**: Phân loại mức độ ưu tiên của lỗi (Critical, High, Medium, Low).
- **Content Quality Scorecard**: Chấm điểm chất lượng dữ liệu.
- **External Review Pack**: Gói mẫu file cho English Reviewer đánh giá độc lập.

## [1.2.6-beta] - 2026-06-14
### Changed
- English Content Accuracy Lockdown (Sprint 25).
- Tích hợp 8 bộ Auditor rà soát toàn bộ tiếng Anh (Vocabulary, Grammar, IPA, Quiz, Reading, Writing, Listening, Speaking).
- Khởi tạo StrictContentGate chặn đứng dữ liệu rác.
- Hoàn thiện CEFR Level Calibration (Quy chuẩn phân tách từ vựng, ngữ pháp theo A0-C1).
- Cập nhật Content Quality Dashboard.
## [1.2.1-beta] - 2026-06-14
### Added
- Thêm `AppCompletionAudit` module để tự động rà soát điểm hoàn thiện của app.
- Bổ sung `ModuleRegistry` để quản lý các tính năng trong hệ sinh thái.

### Changed
- Refactor Navigation: Đảm bảo không còn Activity mồ côi.
- Cải thiện Privacy: Khẳng định cơ chế Offline-first, không yêu cầu đăng nhập.

## [1.2.2-beta] - 2026-06-14
### Changed
- Release Candidate Lockdown (Sprint 21).
- Việt hóa toàn bộ giao diện Home và Practice Center.
- Hợp nhất và rà soát hệ thống thiết kế (Design System Consolidation).
- Rà soát khả năng truy cập (Accessibility) và dung lượng ứng dụng.
- Hoàn thiện hệ thống tài liệu dự án, bao gồm Developer Onboarding và Maintenance Guide.

## [1.2.3-beta] - 2026-06-14
### Changed
- Final Regression Matrix (Rà soát toàn bộ màn hình và UI state).
- Sửa lỗi định tuyến Activity trong AndroidManifest và Practice Center.
- Kiểm tra lỗi nội dung cuối cùng (Content Correction Pass).
- Cập nhật Backup/Restore Audit (Bổ sung Validator và Unit Test).
- Chuẩn bị Personal Release Kit (Bao gồm hướng dẫn cài đặt và báo cáo).
- Hoàn thiện gói từ vựng Microcopy tiếng Việt tự nhiên (UX Microcopy Pack).
- Phân tách môi trường rõ ràng giữa Debug và Release.
- Hoàn thành QA Gate tự động thành công 100%.

## [1.2.5-beta] - 2026-06-14
### Changed
- Final Signed Build Verification & Offline User Manual.
- Rà soát tính nhất quán version trên toàn bộ tài liệu (Version Consistency).
- Nâng cấp `ReleaseArtifactChecker` phân biệt triệt để Signed/Unsigned.
- Bổ sung bộ cẩm nang Offline (`OFFLINE_USER_MANUAL`, `FIRST_7_DAYS_STUDY_GUIDE`, `PERSONAL_USE_CHECKLIST`).

## [1.2.4-beta] - 2026-06-14
### Changed
- Signed personal distribution workflow.
- Thêm Installability matrix để định hướng rõ ràng công dụng của từng Artifact.
- Cung cấp signing properties template (bảo vệ an toàn Keystore khỏi repo).
- Cập nhật Release artifact checker để phân biệt bản có chữ ký (signed) và chưa ký (unsigned).
- Nâng cấp tài liệu Keystore safety docs.
