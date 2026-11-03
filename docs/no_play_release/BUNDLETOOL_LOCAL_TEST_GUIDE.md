# HƯỚNG DẪN TEST BUNDLETOOL LOCAL

Tài liệu này hướng dẫn cách test định dạng Android App Bundle (`app-release.aab`) giả lập việc cài đặt từ Google Play Console ngay trên máy tính của bạn thông qua `bundletool`.

**LƯU Ý:**
- Quá trình này hoàn toàn LÀ TÙY CHỌN. Nếu bạn đã có Signed `app-release.apk` (xem thêm INSTALLABILITY_MATRIX), bạn không cần phải làm việc này.
- Các lệnh dưới đây chỉ mang tính chất Template mẫu, tuyệt đối không chứa mật khẩu (Password) hay đường dẫn Keystore (Keystore Path) thật của bạn.

## Các lệnh cơ bản

### 1. Build APKS (Universal APK)
Lệnh này sẽ trích xuất ra một file `.apks` chứa APK bao quát (Universal) có thể cài đặt được:

```cmd
bundletool build-apks --bundle=app/build/outputs/bundle/release/app-release.aab --output=my_app.apks --mode=universal --ks=D:\path\to\your\release_key.jks --ks-key-alias=YOUR_KEY_ALIAS
```
Hệ thống sẽ yêu cầu bạn nhập mật khẩu trên cửa sổ dòng lệnh (để tránh rò rỉ).

### 2. Cài đặt trực tiếp lên Connected Device
Lệnh này giả lập Google Play: tự động sinh ra file APKS tối ưu riêng cho thiết bị điện thoại đang cắm vào máy tính (Connected Device) thông qua ADB và cài đặt trực tiếp lên thiết bị đó:

```cmd
bundletool build-apks --connected-device --bundle=app/build/outputs/bundle/release/app-release.aab --output=my_app_device.apks --ks=D:\path\to\your\release_key.jks --ks-key-alias=YOUR_KEY_ALIAS
bundletool install-apks --apks=my_app_device.apks
```

> **Tuyệt đối tuân thủ:**
> - Không sửa tài liệu này để lưu trữ KeyAlias hay Path Keystore của bạn.
> - Luôn chạy lệnh ở terminal cục bộ, không chạy trên CI/CD nếu cấu hình public.
