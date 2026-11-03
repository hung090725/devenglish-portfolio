# CONTENT QUALITY SCORECARD

Bảng điểm tổng hợp đánh giá chất lượng tiếng Anh của toàn bộ hệ thống EnglishPath AI tính đến thời điểm hiện tại. Bảng điểm được sinh ra dựa trên tỷ lệ dữ liệu sạch so với số lượng Warning sinh ra từ Auditor.

**Điểm tổng**: `92/100` - Vượt qua Strict Gate, nhưng chưa đạt 100% do vẫn còn các mục TODO cần người thật duyệt.

| Category | Score | Status | Issues | Critical | Recommendation |
|---|---|---|---|---|---|
| Vocabulary Accuracy | 95 | PASS | 2 | 0 | Chờ duyệt các từ có IPA chưa tự tin. |
| IPA Completeness | 88 | WARNING | 42 | 0 | Một số từ A1/A2 đang trống IPA, cần bổ sung. |
| Grammar Validity | 100 | PASS | 0 | 0 | Ngữ pháp hiện đã chuẩn và map đúng CEFR. |
| Quiz Correctness | 98 | PASS | 1 | 0 | Có 1 câu hỏi hơi rườm rà cần viết lại. |
| Conversation Naturalness | 90 | WARNING | 3 | 0 | 3 đoạn hội thoại ở B2 bị nhận xét là quá "sách vở". |
| Reading Quality | 100 | PASS | 0 | 0 | Độ dài và từ vựng trong bài đọc đã đạt chuẩn CEFR. |
| Writing Prompt Quality | 100 | PASS | 0 | 0 | Các Sample Answer đều đầy đủ. |
| Skill Sentence Quality | 94 | PASS | 6 | 0 | Một số câu Speaking hơi dài với level tương ứng. |
| CEFR Alignment | 95 | PASS | 2 | 0 | 2 item bị nghi ngờ gán sai level. |
| TODO Hygiene | 80 | WARNING | 56 | 0 | Có 56 mục đang chờ Human Review. |

*(Báo cáo này được cập nhật mỗi khi chạy xong quá trình Build hoặc QA Gate. Điểm số chỉ phản ánh tỷ lệ vượt qua Auditor, không phản ánh sự thật tuyệt đối nếu chưa có sự can thiệp của Human).*
