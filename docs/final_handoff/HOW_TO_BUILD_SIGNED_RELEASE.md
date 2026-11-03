# Hướng Dẫn Build Signed Release APK

1. Chạy lệnh:
```bash
./gradlew assembleRelease
```
2. Lấy file chưa ký tại `app/build/outputs/apk/release/app-release-unsigned.apk`.
3. Ký file bằng apksigner (Android SDK build-tools):
```bash
apksigner sign --ks [đường_dẫn_keystore_của_bạn] --out app-release.apk app-release-unsigned.apk
```
*Lưu ý: Không đặt file keystore vào trong source code.*
