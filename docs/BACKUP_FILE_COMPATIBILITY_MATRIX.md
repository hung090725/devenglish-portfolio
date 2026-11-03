# Ma trận Tương thích File Backup (Backup File Compatibility Matrix)

Hệ thống Backup được thiết kế từ bản `1.2.6-beta`.

| Phiên bản App tạo Backup | Phiên bản App khôi phục | Tương thích | Ghi chú |
|---|---|---|---|
| 1.2.6-beta | 1.2.17-beta | CÓ | Cấu trúc Core Progress không đổi. |
| 1.2.8-beta | 1.2.17-beta | CÓ | Cấu trúc Core Progress không đổi. |
| 1.2.17-beta | 1.2.17-beta | CÓ | Hoàn hảo. |
| 1.2.17-beta | 1.2.6-beta | KHÔNG | File backup mới có thể chứa cấu trúc mở rộng (Scorecard, Patch state) mà bản cũ không hiểu. Sẽ báo lỗi "Phiên bản không tương thích". |

> Luật vàng: Luôn có thể Restore từ bản cũ lên bản mới (Forward Compatibility). Không đảm bảo Backward Compatibility.
