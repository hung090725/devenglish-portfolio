# DEPENDENCY AUDIT REPORT

**Ngày đánh giá:** (Sprint 21)

## 1. Mục tiêu
Kiểm tra lại toàn bộ các thư viện (dependencies) được sử dụng trong `build.gradle.kts` để đảm bảo không có thư viện thừa, lặp lặp hoặc gây phình to dung lượng ứng dụng không cần thiết.

## 2. Phân loại Dependencies
### 2.1. Thư viện Production (Sẽ build vào APK/AAB)
- `androidx.appcompat` (Giao diện cơ sở)
- `com.google.android.material` (Các thành phần Material Design 3)
- `androidx.activity` (Activity KTX, quản lý vòng đời)
- `androidx.constraintlayout` (Dàn trang Layout)
- `androidx.room.runtime` & `androidx.room.compiler` (Database nội bộ SQLite)
- `androidx.lifecycle.livedata` & `androidx.lifecycle.viewmodel` (Kiến trúc MVVM)
- `androidx.work.runtime` (WorkManager cho nhắc lịch học nền)
- `androidx.core.splashscreen` (Màn hình Splash chuẩn Android 12+)

### 2.2. Thư viện Test (Chỉ chạy trên môi trường phát triển)
- **Test (Unit Tests):** `junit`, `mockito-core`, `robolectric`, `androidx.work:work-testing`
- **AndroidTest (Instrumentation Tests):** `androidx.test.ext:junit`, `espresso-core`, `espresso-contrib`, `androidx.test:runner`, `androidx.test:rules`, `androidx.room.testing`, `uiautomator`

## 3. Khuyến nghị và Cập nhật
- Các thư viện Test đều được khai báo đúng cấu trúc (`testImplementation` / `androidTestImplementation`).
- Không phát hiện thư viện bên thứ 3 nào không rõ nguồn gốc hoặc dư thừa.
- Các phiên bản Room, Lifecycle, WorkManager đang chạy mượt mà, **KHÔNG** nâng cấp ồ ạt trong đợt Lockdown này để tránh rủi ro vỡ Build. 

## 4. Trạng thái
Dự án hoàn toàn sạch sẽ, dependencies đúng chuẩn. Sẵn sàng cho quá trình Release.
