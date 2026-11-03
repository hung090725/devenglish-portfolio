# Hướng Dẫn Chạy AndroidTest (UI Tests)

Dự án có hai loại test giao diện (UI) chính chạy trong thư mục `androidTest`: **Espresso** và **UI Automator**.

## Lệnh Compile Kiểm Tra
Để đảm bảo các test viết ra không bị lỗi compile (Smoke Test Code):
```bash
.\gradlew.bat :app:compileDebugAndroidTestJavaWithJavac
```

## Lệnh Chạy Toàn Bộ Test
Để chạy toàn bộ test giao diện (yêu cầu thiết bị thật hoặc Emulator đang cắm qua USB):
```bash
.\gradlew.bat :app:connectedDebugAndroidTest
```

## Lưu Ý Cực Kì Quan Trọng
Trước khi chạy UI Test trên thiết bị thật / Emulator, bạn **BẮT BUỘC** phải tắt toàn bộ hiệu ứng Animation để tránh test bị flaky (đôi lúc fail, đôi lúc pass).
Vào **Developer Options** trên máy, thiết lập 3 dòng sau thành **Animation off**:
- Window animation scale
- Transition animation scale
- Animator duration scale

## Phân Loại Test
- **Espresso Test**: Kiểm tra các luồng bên trong nội bộ app (VD: Click nút trong màn hình, gõ text vào ô EditText). Cực kỳ nhanh và chính xác. Thích hợp test luồng Quiz, Backup/Restore.
- **UI Automator Test**: (VD: `UiAutomatorPermissionSmokeTest`). Cho phép app thao tác vượt ra ngoài giới hạn ứng dụng, ví dụ click vào nút "Allow" / "Deny" của hộp thoại xin quyền (Permission Dialog) do hệ điều hành bật lên.

*Lưu ý: Test liên quan đến TTS và Microphone bắt buộc phải cắm thiết bị thật, vì Emulator thường không có sẵn Microphone hay cấu hình tiếng Anh chuẩn.*
