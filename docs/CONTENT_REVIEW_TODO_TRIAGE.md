# CONTENT REVIEW TODO TRIAGE

Để quy trình Human Review diễn ra hiệu quả, chúng tôi phân loại danh sách các vấn đề cảnh báo (Warnings) thành 4 cấp độ ưu tiên (Priorities) được gọi là Triage.

## Phân loại (Severity Triage)

### 1. CRITICAL (Khẩn cấp)
- Tình trạng: 0 OPEN

### 2. HIGH (Cao)
- Tình trạng: 0 OPEN

### 3. MEDIUM (Trung bình)
**Lý do:** Thiếu tính tự nhiên hoặc sai lệch cấp độ (nhưng người học vẫn có thể hiểu được).
- `Vocab-34-make` (B2): "make" quá dễ ở B2. (ĐÃ SỬA: "make up for")
- `Reading-C1-AI` (C1): Đoạn văn quá khó. (ĐÃ SỬA: Viết lại đoạn văn chuẩn C1 Foundation)
- Tình trạng: 0 OPEN, 2 FIXED.

### 4. LOW (Thấp)
**Lý do:** Ảnh hưởng UX nhỏ, nội dung không sai nhưng thiếu tự nhiên.
- `Conv-12-Meeting` (B1): "How do you do?" -> Cần câu chào hiện đại hơn. (ĐÃ SỬA: "Nice to meet you.")
- `Gram-B2-Inversion` (B2): Câu ví dụ quá dài. (ĐÃ SỬA: Rút ngắn xuống 8 chữ).
- Tình trạng: 0 OPEN, 2 FIXED.

## Danh sách Triage Cuối Cùng (Sprint 29):
| ID | Priority | Type | Level | Issue | Recommended Action | Status | Owner |
|---|---|---|---|---|---|---|---|
| Vocab-34-make | MEDIUM | Vocab | B2 | Quá dễ ở B2 | Dùng "make up for" | FIXED | Internal System |
| Conv-12-Meeting | LOW | Conv | B1 | Ngôn ngữ máy móc | Dùng "Nice to meet you" | FIXED | Internal System |
| Gram-B2-Inversion | LOW | Grammar | B2 | Ví dụ quá dài | Cắt ngắn còn 8 chữ | FIXED | Internal System |
| Reading-C1-AI | MEDIUM | Reading | C1 | Cấu trúc quá khó | Viết lại C1 Foundation | FIXED | Internal System |

*(Tất cả 4 cảnh báo cuối cùng đã được phân giải an toàn).*
