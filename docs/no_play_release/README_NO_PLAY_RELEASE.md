# Hướng Dẫn Phát Hành EnglishPath AI Không Qua Google Play (Sideload)

Tài liệu này dành cho việc phát hành bản Beta nội bộ của ứng dụng EnglishPath AI trực tiếp đến người dùng (bạn bè, người thân, hoặc máy cá nhân) thông qua file APK, không yêu cầu Google Play Store.

## Tại sao lại phát hành ngoài Play Store?
Ở giai đoạn Solo QA (Sprint 13), mục tiêu chính là thử nghiệm app trên thiết bị thật một cách nhanh chóng mà không phải chờ quá trình xét duyệt phức tạp của Play Console hoặc tạo 12 tester.

## Cách sử dụng thư mục này
Thư mục này chứa các tài liệu cần thiết để gửi cho Tester:
1. `APK_DISTRIBUTION_STEPS.md`: Hướng dẫn cho bạn (Developer) cách chuẩn bị và upload file APK.
2. `RELEASE_NOTES_0.9.1_BETA.md`: Danh sách tính năng và thay đổi mới nhất ở bản Beta.
3. `ZALO_TELEGRAM_TESTER_MESSAGE.md`: Tin nhắn mẫu để bạn copy-paste gửi cho Tester qua Zalo/Telegram kèm file cài đặt.

> **LƯU Ý BẢO MẬT**: Không bao giờ gửi kèm file Keystore (`.jks`) hay mã nguồn dự án cho bất kỳ ai. Chỉ gửi duy nhất file `app-release.apk`.
