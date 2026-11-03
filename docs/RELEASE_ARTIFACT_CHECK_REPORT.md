# RELEASE ARTIFACT CHECK REPORT

**Ngày đánh giá:** (Sprint 21)

## 1. Mục tiêu
Đảm bảo tất cả các file cấu trúc, file đóng gói (APK, AAB), tài liệu Release Notes và cấu hình quan trọng đã sẵn sàng cho một đợt phát hành nội bộ (No-Play Release) hoặc lưu trữ dự phòng.

## 2. Kết quả Kiểm tra
- **app-debug.apk / app-release.apk / app-release.aab:** Đã cấu hình Build Variant đúng trong Gradle. Sau khi chạy `bundleRelease` và `assembleRelease`, các file được lưu đúng thư mục `/app/build/outputs/`.
- **versionName / versionCode:** Kiểm tra `build.gradle.kts` đã khớp với `1.2.2-beta`.
- **Changelog & Release Notes:**
  - `docs/CHANGELOG.md` hiện diện.
  - `docs/no_play_release/RELEASE_NOTES_1.2.2_BETA.md` đã được tạo để mô tả Sprint 21 Lockdown.
- **QA Gate Report:** Chạy tự động xuất ra log sau lệnh `run_solo_qa_gate.bat`.
- **Bảo mật:** Không tìm thấy file `keystore.jks`, file properties chứa password cứng hay các chứng chỉ bị rò rỉ trong thư mục báo cáo (reports) hay docs.

## 3. Xác nhận
Quá trình đóng gói đạt tiêu chuẩn No-Play Distribution an toàn.
