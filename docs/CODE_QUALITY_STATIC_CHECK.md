# CODE QUALITY STATIC CHECK REPORT

**Ngày đánh giá:** (Sprint 21)

## 1. Công cụ sử dụng
- **Android Lint:** Kiểm tra cú pháp, lỗi tiềm ẩn, security, accessibility, i18n, icons...
- **IDE Inspections (Android Studio):** Phát hiện biến không sử dụng (unused variables), code chết (dead code).

## 2. Kết quả rà soát
- **Errors:** 0 (Đã fix hoặc suppress các lỗi sai lệch từ thư viện).
- **Warnings (Cảnh báo):**
  - *Hardcoded strings:* Còn một số ít text dùng trực tiếp trong Java. Các text quan trọng trên UI Home/Practice đã được di chuyển hoặc tối ưu. Do ứng dụng tập trung thị trường Việt Nam và không phát triển đa ngôn ngữ (chỉ học tiếng Anh), Hardcoded strings mức Warning được phép chấp nhận.
  - *Unused imports:* Đã dọn dẹp các thư viện/class bị thừa (ví dụ `import android.widget.Button` thừa ở một số file Fragment/Activity).
- **Performance:** Không phát hiện vòng lặp vô tận (infinite loops) hoặc memory leak tiềm ẩn qua phân tích tĩnh.
- **Bảo mật:** Không lưu API keys hay mật khẩu trong mã nguồn.

## 3. Xác nhận
Code Quality đạt chuẩn Release Candidate cho phiên bản 1.2.2-beta. Mọi code mới commit cần tuân thủ không sinh thêm lỗi Lint nghiêm trọng.
