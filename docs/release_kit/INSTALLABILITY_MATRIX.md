# INSTALLABILITY MATRIX

Bảng dưới đây liệt kê các dạng file (Artifact) được sinh ra trong quá trình Build ứng dụng EnglishPath AI và định hướng sử dụng chuẩn xác.

| Artifact | Cài trực tiếp được không? | Có cần Signing không? | Gửi cho Tester / Người dùng? | Mục đích / Ghi chú |
| :--- | :---: | :---: | :---: | :--- |
| **`app-debug.apk`** | CÓ | KHÔNG (Tự ký bằng debug key) | **CÓ (Test nhanh cho bản thân/tester)** | Dành cho DEV và Tester kiểm tra lỗi nội bộ. Chạy tốt nhưng chậm hơn bản Release. Sẽ báo hiệu "App is debuggable". |
| **`app-release-unsigned.apk`** | KHÔNG | KHÔNG | **TUYỆT ĐỐI KHÔNG GỬI** | Android từ chối cài đặt mọi file APK chưa ký (Unsigned). Dành cho hệ thống CI/CD ký sau, hoặc kiểm tra mã nguồn. |
| **`app-release.apk` (Signed)** | CÓ | **CÓ BẮT BUỘC** | **CÓ (Gửi người quen)** | Bản hoàn hảo nhất để gửi cho người dùng cuối (Gia đình, Bạn bè, Khách hàng) tải về từ Drive/Zalo và cài trực tiếp. Ổn định và mượt mà nhất. |
| **`app-release.aab`** | KHÔNG | KHÔNG* | **Để dành Play Console** | Định dạng bundle để nộp lên Google Play Console. Không thể bấm cài trực tiếp trên máy. |
| **`*.apks` (Từ Bundletool)** | CÓ (Qua lệnh CLI) | CÓ | **KHÔNG** | Được tạo ra từ việc chạy Bundletool cục bộ để test mô phỏng quá trình cài của Google Play. Cài qua lệnh CMD, không phải bấm trực tiếp. |

### Nguyên tắc Vàng:
- **Gửi cho bản thân test nhanh:** `app-debug.apk`
- **Gửi người quen (nếu đã ký):** `app-release.apk` (Signed)
- **Để dành Play Console:** `app-release.aab`
- **TUYỆT ĐỐI KHÔNG GỬI:**
  - `app-release-unsigned.apk`
  - File `.jks`
  - File `signing.properties`
  - File text chứa Mật khẩu
  - File backup cá nhân
  - File diagnostics cá nhân (trừ khi developer yêu cầu trực tiếp)
