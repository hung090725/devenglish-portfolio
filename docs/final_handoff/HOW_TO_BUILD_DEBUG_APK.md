# Hướng Dẫn Build Debug APK

1. Mở Android Studio.
2. Chọn Build Variant: `debug`.
3. Nhấn nút Run (Play xanh lá) trên thanh công cụ để cài trực tiếp vào máy ảo/điện thoại qua cáp.
Hoặc mở Terminal (gõ lệnh):
```bash
./gradlew assembleDebug
```
APK sẽ nằm ở: `app/build/outputs/apk/debug/app-debug.apk`
