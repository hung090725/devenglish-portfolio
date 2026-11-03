# HƯỚNG DẪN TẠO BẢN CÀI ĐẶT SIGNED APK CÁ NHÂN

## 1. Vì sao APK Release cần được ký (Signed)?
Mọi ứng dụng Android đều bắt buộc phải có chữ ký điện tử (Keystore) để hệ điều hành nhận diện nhà phát triển. Chữ ký này giúp bảo đảm ứng dụng không bị kẻ gian sửa đổi nội dung và phân phối lại.

## 2. Vì sao không gửi bản `app-release-unsigned.apk`?
Bản `unsigned` là bản "chưa có chữ ký". Điện thoại Android sẽ TỪ CHỐI cài đặt mọi file APK chưa được ký để bảo vệ thiết bị. Gửi file này cho Tester hay người thân là vô nghĩa vì họ không thể mở được.

## 3. Cách tạo Keystore bằng Android Studio
1. Mở dự án trong Android Studio.
2. Trên thanh menu, chọn **Build** -> **Generate Signed Bundle / APK...**
3. Chọn **APK** -> Bấm **Next**.
4. Dưới mục Key store path, bấm **Create new...**
5. Điền thông tin bảo mật để sinh ra file `.jks`.

## 4. Cách lưu Keystore ngoài Project
Tuyệt đối KHÔNG chọn thư mục dự án (C:\Users\Admin\StudioProjects\apptienganh) để lưu file `.jks`. Hãy lưu ở một phân vùng an toàn như `D:\keystores\` hoặc một USB mã hóa.

## 5. Cách tạo `signing.properties`
1. Copy file `signing.properties.example` thành `signing.properties`.
2. Sửa thông tin bên trong khớp với Keystore bạn vừa tạo.
3. *Lưu ý:* File này đã được `.gitignore` chặn lại để không bao giờ bị lộ lên mạng.

## 6. Cách chạy Build
Mở Terminal trong Android Studio và gõ:
```cmd
.\gradlew.bat :app:assembleRelease
```

## 7. Cách kiểm tra Output
Vào thư mục: `app/build/outputs/apk/release/`
Nếu thành công, bạn sẽ thấy file: **`app-release.apk`**.

## 8. Cách phân biệt Signed và Unsigned
Nếu cấu hình Gradle đọc được file `signing.properties`, nó sẽ xuất ra `app-release.apk` (Signed). Nếu không có cấu hình (hoặc thiếu file), Gradle sẽ bỏ qua việc ký và sinh ra `app-release-unsigned.apk`.

## 9. Cách xử lý lỗi cài đặt
- **App not installed:** Gỡ sạch bản cũ trên máy (có thể là bản debug) trước khi cài.
- **Signature mismatch:** Bản cũ trên máy có chữ ký khác bản bạn vừa build. Bắt buộc gỡ app cũ và cài lại (sẽ mất dữ liệu nếu chưa backup).
- **Version code thấp hơn:** Android không cho phép cài bản version 14 đè lên máy đang có version 15. Phải tăng versionCode trong `build.gradle.kts`.
- **Unknown sources blocked:** Bấm "More details" -> "Install anyway" (Vẫn cài đặt) khi Play Protect cảnh báo.

## 10. CẢNH BÁO TỐI THƯỢNG
- **KHÔNG** gửi file `.jks` cho bất kỳ ai.
- **KHÔNG** gửi file `signing.properties`.
- **KHÔNG** ghi mật khẩu trong Docs, Chat hay mã nguồn.
- **MẤT KEYSTORE LÀ MẤT APP:** Nếu bạn mất file `.jks` hoặc quên mật khẩu, các bản cập nhật sau này sẽ KHÔNG THỂ cài đè lên máy người dùng cũ. Họ bắt buộc phải gỡ app cũ (mất toàn bộ dữ liệu) để cài app với Keystore mới.
