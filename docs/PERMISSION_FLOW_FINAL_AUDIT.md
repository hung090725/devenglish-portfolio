# Báo Cáo Quy Trình Xin Quyền (Permission Flow Final Audit)

EnglishPath AI yêu cầu rất ít quyền, đảm bảo tính riêng tư tối đa.

## Các quyền được sử dụng
1. **RECORD_AUDIO**: Dùng duy nhất trong module SpeakingScorer để ghi âm chấm điểm phát âm. 
   - **Xử lý từ chối**: Nếu người dùng từ chối, hiện Toast giải thích lịch sự. Không crash app. Màn hình Speaking sẽ hiển thị rỗng hoặc không thể nhấn nút Ghi âm.
2. **INTERNET**: Chỉ dùng cho SpeechRecognizer (Google API) khi chấm điểm. Tuyệt đối không dùng để gửi dữ liệu cá nhân hay chạy Analytics tracking ngầm.

## Kết luận
Luồng xin quyền an toàn, tuân thủ chính sách bảo mật Google Play (mặc dù hiện tại app không đẩy lên Play Store). Không xin quyền Storage bừa bãi. Quyền sao lưu dùng SAF (Storage Access Framework) an toàn.
