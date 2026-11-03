# CHIẾN LƯỢC KÝ SỐ CHO BẢN PHÂN PHỐI CÁ NHÂN (SIGNING STRATEGY)

Tài liệu này giải thích cách hoạt động của hệ thống APK/AAB và định hướng ký số (signing) cho EnglishPath AI.

## 1. Phân loại Artifacts
- `app-debug.apk`: Chứa chữ ký debug mặc định của Android Studio. Dùng để test nhanh, cài đặt nội bộ trên máy dev. Tuyệt đối không dùng phân phối chính thức.
- `app-release-unsigned.apk`: Bản Build Release hoàn chỉnh nhất nhưng CHƯA CÓ chữ ký. Không thể cài đặt trực tiếp lên điện thoại người dùng bình thường.
- **Signed Release APK**: Đây là bản Release đã được ký bằng chữ ký điện tử (Keystore) do bạn tự tạo. Đây là file DUY NHẤT phù hợp để gửi cho người quen hoặc tester cài đặt lâu dài.
- `app-release.aab` (Android App Bundle): File gốc dùng để đẩy lên Google Play Console (sẽ do Google Play xử lý việc tạo và ký APK gửi đến người dùng).

## 2. Lựa chọn Phân phối (Distribution Options)
- **Hướng A (Test Nhanh):** Nếu bạn chỉ muốn chia sẻ nhanh cho 1-2 người bạn thân qua Zalo để xem thử giao diện, bạn có thể gửi `app-debug.apk`. 
- **Hướng B (Cài Đặt Lâu Dài):** BẮT BUỘC phải tạo Keystore của riêng bạn, tiến hành ký số để ra bản Signed Release APK. 

## 3. Nguyên tắc Bảo mật Keystore Tuyệt Đối
1. **Keystore phải lưu ngoài repo:** Không bao giờ lưu file `.jks` vào trong thư mục dự án (C:\Users\Admin\StudioProjects\apptienganh). Nên lưu ở ổ D:\, USB hoặc Google Drive cá nhân được bảo mật.
2. **Không commit `.jks`:** Đã cấu hình `.gitignore` chặn file `.jks`. Nếu bạn lỡ tay đưa vào, toàn bộ tính bảo mật của ứng dụng coi như bỏ đi.
3. **Không ghi password vào code/docs:** Không bao giờ hardcode password vào `build.gradle.kts` hay bất kỳ file `.md` nào. Chúng ta sử dụng `signing.properties` (được gitignore) để Gradle tự động đọc.
4. **Không mất Keystore:** Nếu bạn làm mất file `.jks` hoặc quên mật khẩu, mọi bản cập nhật sau này (dù mã nguồn y hệt) đều không thể cài đè lên máy của người dùng cũ. Họ sẽ phải gỡ app cũ (mất toàn bộ dữ liệu học) để cài app mới.

## 4. Khuyến nghị Quản lý Mật khẩu
- Lưu Keystore ở USB mã hóa hoặc Cloud riêng tư.
- Tạo một file Text chứa thông tin Alias/Password nhưng chỉ lưu chung với file Keystore ở nơi an toàn. KHÔNG BAO GIỜ commit file này.
- Khuyên dùng các Password Manager (Bitwarden, 1Password) để lưu mật khẩu Keystore.
