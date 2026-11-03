@echo off
echo ====================================================
echo BUNDLETOOL COMMANDS TEMPLATE
echo LƯU Ý: Đây chỉ là template mẫu. KHÔNG điền mật khẩu 
echo hay đường dẫn keystore thật vào file này.
echo ====================================================

REM Để chạy lệnh thực tế, bạn cần tải file bundletool-all.jar từ GitHub của Google.

REM 1. Lệnh tạo APKS (Đã ký)
REM java -jar bundletool-all.jar build-apks ^
REM --bundle=..\..\app\build\outputs\bundle\release\app-release.aab ^
REM --output=release.apks ^
REM --ks=C:\du_ong_dan_den_keystore_cua_ban.jks ^
REM --ks-pass=pass:MAT_KHAU_CUA_BAN ^
REM --ks-key-alias=KEY_ALIAS_CUA_BAN ^
REM --key-pass=pass:MAT_KHAU_CUA_BAN

REM 2. Lệnh cài đặt APKS lên máy thật (kết nối qua USB)
REM java -jar bundletool-all.jar install-apks --apks=release.apks
