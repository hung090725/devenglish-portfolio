# FINAL TODO DECISION POLICY (SPRINT 29)

Quy tắc xử lý 4 mục TODO cuối cùng của dự án. Hệ thống cam kết không "đoán bừa" chỉ để lấy thành tích điểm tuyệt đối.

## Các Nguyên Tắc Cốt Lõi
1. **Confidence Level**: Chỉ áp dụng bản vá (Patch) nếu mức độ tự tin (Confidence) đạt mức `HIGH`.
2. **Fallback**: Nếu mức độ tự tin chỉ ở `MEDIUM` hoặc `LOW`, hoặc có rủi ro sai lệch ngữ nghĩa, bắt buộc phải giữ lại trạng thái `NEEDS_HUMAN_REVIEW`.
3. **IPA**: Không tự tiện sửa hệ thống phiên âm (IPA) nếu không có từ điển hoặc nguồn đối chiếu chắc chắn.
4. **Context (Ngữ cảnh)**: Không sửa đổi nghĩa của từ nếu không nắm rõ ngữ cảnh mà bài học đang nhắm tới.
5. **Level Consistency**: Không sửa đổi một đoạn hội thoại cho tự nhiên hơn nhưng vô tình nâng mức độ từ vựng vượt quá cấp độ đang học (ví dụ B1).
6. **Ceiling Limit**: Không làm cho nội dung C1 trở nên quá hàn lâm, tiệm cận mức C2. Không làm B1 thành quá trang trọng một cách không cần thiết.
7. **Scorecard Honesty**: Tuyệt đối không tự tuyên bố điểm chất lượng là "100/100" hay "100% Correct" nếu trên hệ thống vẫn còn sót lại bất kỳ một mục `NEEDS_HUMAN_REVIEW` nào.
8. **Final Disclaimer**: Cho dù Sprint 29 có xử lý thành công 100% cả 4 TODO và dọn dẹp sạch sẽ danh sách, hệ thống vẫn phải giữ nguyên dòng thông báo: *"automatic + internal review only, not certified by external human reviewer"*.
