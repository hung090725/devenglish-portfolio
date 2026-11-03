# Hướng Dẫn Lưu Trữ Artifact (Artifact Vault Guide)

## Artifacts là gì?
Artifacts là các file thành phẩm sau khi chạy `assembleRelease` hoặc `bundleRelease`.

## Nơi lưu trữ (Local Vault)
Sau khi chạy xong lệnh QA Gate hoặc Build, hãy lấy các file sau và nén lại (ZIP) rồi lưu trữ an toàn trên Google Drive cá nhân:
1. `app/build/outputs/apk/release/app-release-unsigned.apk` (Bản APK chưa ký)
2. `app/build/outputs/bundle/release/app-release.aab` (Bản AAB dùng nếu sau này lên Play Console)
3. `docs/no_play_release/RELEASE_NOTES_*.md`

Lưu ý: Không bao giờ commit APK/AAB vào git repo.
