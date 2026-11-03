# Hướng Dẫn Cập Nhật Nội Dung An Toàn

Dự án này sử dụng mô hình "Static Seed + Content Patches". Để thay đổi dữ liệu tiếng Anh đã bị lỗi:

**Cách 1: Nếu lỗi cực nhỏ (Sai chính tả rõ ràng)**
- Bạn có thể vào trực tiếp `app/src/main/java/hung/edu/apptienganh/data/seed/` và tìm class tương ứng (ví dụ `B2VocabSeedHelper.java`) để sửa chuỗi string.
- Dữ liệu Seed sẽ tự động chèn đè bản sửa chữa lên máy ảo nếu bạn gỡ app cài lại, nhưng trên máy người dùng cũ thì KHÔNG tự động cập nhật nếu họ không clear data.

**Cách 2: Áp dụng Patch (Khuyên dùng)**
- Điền dữ liệu sửa lỗi vào file JSON trong `docs/content_correction_patches`.
- Hệ thống `PatchApplicationService` (khi hoạt động) sẽ tự phân bổ bản vá mà không bắt người dùng xóa data cũ.
