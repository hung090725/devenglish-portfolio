# Screenshot & Video Evidence Guide

Hướng dẫn quản lý bằng chứng (hình ảnh/video) khi test trên máy thật.

1. **Khi nào cần quay/chụp?**
   - Khi luồng hoạt động (flow) quá phức tạp hoặc có animation lạ.
   - BẮT BUỘC chụp màn hình khi xảy ra lỗi (Crash, UI cắt xén, Fail).

2. **Quy định đặt tên file:**
   - Cú pháp: `[Device]_[TestID]_[Status]_[NgayThang]`
   - Ví dụ: `S23_Test09_FAIL_20260614.mp4` hoặc `Pixel6_Test01_PASS_20260614.png`.

3. **Nơi lưu trữ:**
   - Do Github giới hạn dung lượng, các video nặng nên được lưu trữ trên Google Drive hoặc thư mục lưu trữ cục bộ ngoài project.
   - Trong file `.md` chỉ cần dán link hoặc ghi tên file tham chiếu.
