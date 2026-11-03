# Hướng Dẫn Closed Testing Trên Google Play Console

Tài liệu này hướng dẫn cách đưa EnglishPath AI lên Google Play Console cho giai đoạn Closed Testing (Alpha).

## Cảnh Báo Bảo Mật (RẤT QUAN TRỌNG)
- **TUYỆT ĐỐI KHÔNG** upload file Keystore (`.jks` hoặc `.keystore`) lên GitHub hoặc gửi qua bất kỳ phần mềm chat nào.
- File Keystore phải được giữ an toàn trên máy tính cá nhân. Nếu mất file này, bạn sẽ không thể cập nhật ứng dụng trên Google Play.

## Quy Trình Setup & Upload (14 Bước)

1. **Vào Play Console:** Đăng nhập vào tài khoản Google Play Console của bạn.
2. **Create App:** Nhấn nút "Create app", nhập tên "EnglishPath AI", ngôn ngữ mặc định (Tiếng Việt), loại là "App", chọn "Free". Xác nhận các điều khoản Developer.
3. **Điền App Details:** Hoàn thành toàn bộ mục "Set up your app" trên Dashboard (bao gồm Data Safety, Privacy Policy URL, Target Audience, Content Rating).
4. **Tạo Closed Testing Track:** Điều hướng đến `Testing > Closed testing`. Chọn Manage track "Alpha".
5. **Upload AAB:** Bấm "Create new release". Tải lên file `app/build/outputs/bundle/release/app-release.aab`. 
   *(Lưu ý: Mỗi lần upload bản mới, bạn bắt buộc phải tăng `versionCode` trong file `build.gradle.kts`)*.
6. **Thêm Tester:** Ở tab "Testers" trong Closed testing track, tạo một danh sách email và dán 20 email của tester vào (hoặc sử dụng Google Group).
7. **Gửi Opt-in Link:** Copy "Opt-in link" được tạo ra từ Play Console và gửi cho các tester.
8. **Nhắc Tester Opt-in:** Đảm bảo 20 tester đều click vào link, nhấn "Become a Tester" và tải app về máy.
9. **Theo Dõi 14 Ngày:** Play Console quy định tài khoản mới phải có 20 tester tham gia kiểm thử (opt-in và giữ app) liên tục trong ít nhất 14 ngày. Bạn hãy theo dõi Dashboard để đảm bảo đủ tiêu chuẩn.
10. **Thu Thập Feedback:** Khuyến khích tester gửi báo cáo lỗi (Crash logs, UI bugs) thông qua tính năng "Beta Feedback" trong app.
11. **Không Xin Production Sớm:** Không gửi yêu cầu quyền Production (Apply for production) khi chưa đủ 14 ngày và 20 tester. Yêu cầu sẽ bị từ chối tự động.
12. **Cách Xử Lý Nếu Bị Từ Chối (Rejected):** 
    - Nếu bản build bị Google từ chối do vi phạm Data Safety: Kiểm tra lại tờ khai (Ví dụ: Microphone phải được khai báo đúng mục đích). Sửa lỗi, tăng `versionCode`, build lại AAB và upload một bản Release mới thay thế.
    - Không tạo app mới nếu chỉ bị từ chối bản update. Cứ update đè lên.
