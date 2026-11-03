# CEFR LEVEL REBALANCING REPORT

Hệ thống đã chạy thuật toán `CEFRLevelRebalancingAuditor` để quét toàn bộ dữ liệu. Dưới đây là các câu/từ có rủi ro bị gán sai cấp độ CEFR. Xin lưu ý hệ thống KHÔNG TỰ ĐỘNG CHUYỂN (MOVE) cấp độ nếu không có sự xác nhận của Human Reviewer.

| ID | Level | Text | Reason | Suggested Level | Confidence | Action |
|---|---|---|---|---|---|---|
| CEFR-Vocab-102 | A0 | "Although the weather was bad, we decided to go out." | Mệnh đề phụ (Although) xuất hiện ở A0. | A2/B1 | HIGH | REVIEW |
| CEFR-Conv-12 | A1 | "It's ubiquitous in modern society." | Từ vựng C1 "ubiquitous" xuất hiện ở A1. | C1 | HIGH | MOVE |
| CEFR-Gram-4 | B2 | "I am a boy." | Quá đơn giản cho B2. | A0 | HIGH | MOVE |

*(Chỉ được phép MOVE khi Confidence là HIGH và Action là MOVE. Với các trường hợp REVIEW, phải xem xét lại ngữ cảnh).*
