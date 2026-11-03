# Cấu Trúc Dự Án (Project Structure Guide)

- `app/src/main/java/hung/edu/apptienganh`: Mã nguồn Java gốc.
  - `/data`: Room DB, DAO, Entities và Seed Data (quan trọng nhất).
  - `/domain`: Lớp xử lý logic nghiệp vụ, thuật toán chấm điểm (Scorer), chấm điểm phát âm.
  - `/ui`: Các giao diện Activity, Fragment, ViewModel.
  - `/engine/audit`: Bộ kiểm toán nội dung tĩnh (Auditor) chặn mọi dữ liệu xấu từ đầu nguồn.
- `app/src/main/res`: Giao diện XML (layout, drawable, values).
- `docs/`: Thư mục lưu trữ toàn bộ tài liệu báo cáo chất lượng và lịch sử phát triển.
- `tools/qa/`: Nơi chứa script kiểm tra tự động `run_solo_qa_gate.bat`.
