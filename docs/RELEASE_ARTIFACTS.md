# Release Artifacts (EnglishPath AI)

Danh sách các Artifacts đầu ra của quá trình build và chức năng của chúng:

1. **app-debug.apk**
   - **Đường dẫn:** `app/build/outputs/apk/debug/app-debug.apk`
   - **Chức năng:** Bản build phục vụ quá trình test nội bộ, dễ dàng cài trực tiếp lên thiết bị thật qua cáp USB hoặc gửi cho tester để kiểm tra thủ công.

2. **app-release.apk**
   - **Đường dẫn:** `app/build/outputs/apk/release/app-release.apk`
   - **Chức năng:** Bản cài đặt Release trực tiếp, không qua hệ thống phân phối của Google Play. Hữu ích cho việc phát hành bản cài offline, tuy nhiên không dùng để upload lên Play Console.

3. **app-release.aab**
   - **Đường dẫn:** `app/build/outputs/bundle/release/app-release.aab`
   - **Chức năng:** Android App Bundle. Định dạng BẮT BUỘC để upload lên Google Play Console. Hệ thống Google sẽ dùng file này để generate ra các APK nhỏ và tối ưu gọn nhẹ nhất phù hợp với từng thiết bị của người dùng.

**Lưu ý:**
- Bất kỳ file release nào cũng phải được ký (signed) bằng file keystore hợp lệ.
- TUYỆT ĐỐI không upload keystore lên repo.
