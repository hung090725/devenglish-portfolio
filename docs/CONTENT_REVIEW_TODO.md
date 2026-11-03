# DANH SÁCH NỘI DUNG CẦN REVIEW THỦ CÔNG (CONTENT REVIEW TODO)

Danh sách này chứa các mục tiếng Anh mà hệ thống `StrictContentGate` hoặc bộ Auditor tự động gắn cờ `CONTENT_REVIEW_REQUIRED` (Pass with warnings). Các mục này không bị lỗi format nghiêm trọng (không trống, không sai đáp án logic) nhưng có rủi ro sai cấp độ CEFR, dịch thô, hoặc ngữ nghĩa thiếu tự nhiên.

Vui lòng yêu cầu một chuyên gia tiếng Anh xem qua và sửa lại seed data tương ứng.

| ID | Type | Level | Current Text | Issue | Suggested Action | Priority | Status |
|---|---|---|---|---|---|---|---|
| Vocab-101-serendipity | Vocabulary | A1 | serendipity | Từ quá khó so với A1. | Chuyển sang C1. | HIGH | FIXED |
| Vocab-34-make | Vocabulary | B2 | make | Quá dễ để lặp lại ở B2. | Đã sửa thành "make up for". | MED | FIXED |
| Quiz-A0-5 | Quiz | A0 | Which of the following... | Ngôn ngữ câu hỏi quá học thuật. | Đổi thành "What is...?" | MED | FIXED |
| Conv-12-Meeting | Conversation | B1 | "How do you do?" | Hơi lỗi thời/máy móc. | Đã sửa thành "Nice to meet you." | LOW | FIXED |
| Gram-B2-Inversion | GrammarPoint | B2 | Không sai nhưng ví dụ quá dài. | Ví dụ dài 20 từ gây khó hiểu. | Đã rút ngắn còn 8 chữ. | LOW | FIXED |
| Reading-C1-AI | ReadingPassage | C1 | ... | Có vẻ độ khó tương đương C2. | Đã viết lại C1 Foundation. | MED | FIXED |

*(Hiện tại hệ thống không còn cảnh báo mức độ Warning nào. Tất cả đã được FIXED)*
