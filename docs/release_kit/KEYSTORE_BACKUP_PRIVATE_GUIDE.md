# Hướng Dẫn Bảo Mật Keystore (Keystore Private Guide)

## Lệnh cấm (STRICT PROHIBITION)
Tuyệt đối KHÔNG BAO GIỜ:
1. Đặt file `.jks` hoặc `.keystore` vào bất kỳ thư mục nào trong dự án này.
2. Viết mật khẩu keystore vào `gradle.properties` hoặc `build.gradle.kts`.
3. Commit `signing.properties` nếu có.

## Cách bảo quản Keystore cá nhân
1. Tạo một thư mục Cục bộ ngoài dự án (ví dụ `C:\AndroidKeys\`).
2. Lưu file `englishpath.jks` tại đó.
3. Nén thành file zip có password và đẩy lên cloud (Google Drive/Dropbox).
4. Lưu mật khẩu của keystore vào phần mềm quản lý mật khẩu (Bitwarden, 1Password, iCloud Keychain).
