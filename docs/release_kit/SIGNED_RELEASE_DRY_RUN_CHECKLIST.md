# Danh Sách Kiểm Tra Ký App (Signed Release Dry-Run Checklist)

Dự án hiện tại KHÔNG lưu mật khẩu hay file keystore thật trong codebase. Nếu muốn tạo bản Signed Release để cài trên thiết bị vật lý, hãy làm các bước thủ công sau:

1. [ ] Chạy `tools\qa\run_solo_qa_gate.bat` để đảm bảo app xanh 100%.
2. [ ] Lấy file `app-release-unsigned.apk`.
3. [ ] Chạy lệnh `apksigner` với keystore cá nhân của bạn:
   `apksigner sign --ks my-release-key.jks --out app-release.apk app-release-unsigned.apk`
4. [ ] Cài file `app-release.apk` lên điện thoại.
5. [ ] Mở app, xác minh không bị crash khi mở.
6. [ ] Mở phần Cài đặt, xem Version Name có đúng với `build.gradle.kts` không.
