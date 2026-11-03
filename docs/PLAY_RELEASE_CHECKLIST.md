# Google Play Console Release Checklist

Danh sách kiểm tra trước khi Release bản Alpha (Closed Testing) trên Play Console.

## 1. Store Listing & Nội dung
- [ ] Tên App, Mô tả ngắn, Mô tả dài đã được copy từ `STORE_LISTING_DRAFT.md`.
- [ ] Ảnh Icon App 512x512 đã được upload.
- [ ] Ảnh Feature Graphic 1024x500 đã được upload.
- [ ] Ảnh Screenshots (ít nhất 2-3 tấm: Home, Lessons, Profile) đã được upload.

## 2. Policy & App Content
- [ ] Khai báo Privacy Policy URL.
- [ ] Khai báo Data Safety đúng như `DATA_SAFETY_DRAFT.md` (Microphone: có thu thập/không chia sẻ/ephemeral).
- [ ] Xác nhận Target Audience (Người lớn / Học sinh).
- [ ] Xác nhận không phải là app tin tức (News app).
- [ ] Xác nhận COVID-19 tracing (Không).
- [ ] Ads: Khai báo không có quảng cáo.

## 3. Quá trình Build App (File AAB)
- [ ] Đã chạy thành công `assembleRelease`.
- [ ] Đã chạy thành công `bundleRelease`.
- [ ] Đã kiểm tra file `app/build/outputs/bundle/release/app-release.aab` tồn tại.
- [ ] File AAB đã được ký bằng keystore (Release key). Đảm bảo giữ file keystore an toàn.
- [ ] Phiên bản hiện tại là `0.8.0-beta` (versionName) và `versionCode` đã được nâng lên cao hơn bản trước đó.

## 4. Play Console Closed Testing
- [ ] Tạo Release mới trong track "Alpha" (Closed testing).
- [ ] Upload file `.aab` thành công (không bị báo lỗi trùng versionCode).
- [ ] Thêm Release notes: "Sprint 10: Hoàn thiện lộ trình 42 ngày A0-A2, nâng cấp tiến độ học tập, sửa lỗi."
- [ ] Thêm danh sách 20 tester emails.
- [ ] Gửi Opt-in link cho các tester.

## 5. Review & Publish
- [ ] Kiểm tra lỗi/Cảnh báo từ Google Play Console lúc review.
- [ ] Bấm Save -> Gửi đi review (Thường tốn vài giờ -> 7 ngày).
- [ ] Click "Start rollout to Closed Testing".
