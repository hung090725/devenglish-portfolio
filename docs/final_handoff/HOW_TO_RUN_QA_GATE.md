# Hướng Dẫn Chạy QA Gate

QA Gate là công cụ quan trọng nhất để biết bạn có vô tình làm hỏng ứng dụng hay không. Mọi thay đổi code đều phải qua ải này.

1. Mở Terminal tại thư mục gốc (`C:\Users\Admin\StudioProjects\apptienganh`).
2. Chạy lệnh:
```bash
tools\qa\run_solo_qa_gate.bat
```
3. Chờ khoảng 1-2 phút.
4. Nếu kết quả hiện "SUCCESS" màu xanh lá => An toàn.
5. Nếu "FAIL" => Mở file log trong `build\reports\solo_qa_gate\solo_qa_gate_report.txt` để xem nguyên nhân và sửa.
