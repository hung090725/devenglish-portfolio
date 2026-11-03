# QUY TẮC ĐÓNG BĂNG NỘI DUNG (CONTENT FREEZE RULES)

Bắt đầu từ Sprint 25, dự án bước vào trạng thái **Content Accuracy Lockdown**. Dưới đây là các quy tắc tối thượng dành cho bất kỳ ai tham gia chỉnh sửa mã nguồn hoặc dữ liệu của ứng dụng EnglishPath AI.

## 1. Cấm Phát Triển Mở Rộng
1. **Không thêm tính năng mới:** Toàn bộ UI, luồng màn hình (flow) và các module (Ví dụ: Flashcard, Word Game, Review Center...) đã bị khóa. Tuyệt đối không code thêm module mới.
2. **Không thêm Level mới:** App giới hạn ở cấu trúc hiện tại: A0, A1, A2, B1, B2, C1 Foundation. Không mở thêm C2 hay các khóa học ngách khác.

## 2. Phạm Vi Được Phép Chỉnh Sửa
Bạn **CHỈ ĐƯỢC PHÉP** sửa mã nguồn và dữ liệu (Seed Data) nếu thuộc một trong các trường hợp sau:
- Lỗi tiếng Anh (sai chính tả, sai ngữ pháp trong bài học).
- Lỗi nghĩa tiếng Việt (dịch tối nghĩa, dịch sai ngữ cảnh).
- Lỗi phiên âm (IPA sai, thiếu, hoặc có chứa ký tự tiếng Việt lạ).
- Lỗi xếp loại trình độ (Từ vựng quá khó đang nằm ở Level quá dễ).
- Lỗi ví dụ (Ví dụ quá dài/quá khó hiểu).
- Lỗi đáp án Quiz (Đáp án sai, đáp án bị trùng).
- Lỗi hội thoại (Lẫn lộn vai trò, sai tình huống).
- Lỗi nội dung Reading/Listening/Writing/Speaking.

## 3. Quy Tắc Chỉnh Sửa Dữ Liệu
1. **Không được bịa IPA:** Nếu không biết phiên âm chuẩn của một từ, tuyệt đối không tự gõ bừa. Phải tra từ điển chuẩn (Oxford, Cambridge) hoặc đẩy vào `CONTENT_REVIEW_TODO.md`.
2. **Không dùng ví dụ sai ngữ pháp:** Example Sentence phải chuẩn 100% ngữ pháp tiếng Anh.
3. **Không chèn từ khó vào Level thấp:** A0/A1 chỉ dùng các từ vựng cực kỳ phổ thông.
4. **Không dịch thô (Word-by-word):** Nghĩa tiếng Việt phải tự nhiên, mượt mà và bám sát ngữ cảnh giao tiếp thực tế.
5. **Đảm bảo tính duy nhất của Quiz:** Một câu hỏi trắc nghiệm tuyệt đối không được có 2 đáp án cùng đúng (trừ khi cố ý làm multi-select). Không dùng các option trùng lặp.
6. **Không claim 100% nếu không chắc:** Mọi từ/câu/cấu trúc có sự mơ hồ, nghi ngờ về độ tự nhiên hoặc cấp độ CEFR đều phải được gắn cờ `CONTENT_REVIEW_REQUIRED` hoặc đẩy vào file TODO. Không được lấp liếm lỗi.
