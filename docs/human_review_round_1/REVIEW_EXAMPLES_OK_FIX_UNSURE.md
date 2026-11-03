# REVIEW EXAMPLES (OK, FIX, UNSURE)

Dưới đây là một số ví dụ minh họa để bạn dễ hình dung cách điền Feedback Form.

## Ví dụ 1: Decision OK
- **currentText**: "I like apples." (Level A1)
- **issue**: Cảnh báo tự động nghi ngờ câu quá ngắn.
- **decision**: `OK`
- **reason**: Câu này hoàn toàn bình thường ở mức A1.

## Ví dụ 2: Decision FIX
- **currentText**: "She don't like apples."
- **issue**: Sai ngữ pháp cơ bản.
- **decision**: `FIX`
- **correctedText**: "She doesn't like apples."
- **reason**: Chủ ngữ ngôi thứ 3 số ít dùng doesn't.
- **confidence**: `HIGH`

## Ví dụ 3: Decision MOVE_LEVEL
- **currentText**: "Serendipity" (Level A1)
- **issue**: Từ quá khó so với A1.
- **decision**: `MOVE_LEVEL`
- **correctedLevel**: `C1`
- **reason**: Từ hiếm gặp, hợp với C1 hơn.
- **confidence**: `HIGH`

## Ví dụ 4: Decision UNSURE
- **currentText**: "How do you do?" (Level B1)
- **issue**: Ngôn ngữ máy móc.
- **decision**: `UNSURE`
- **reason**: Câu này hơi cũ nhưng nếu trong văn cảnh formal thì vẫn đúng, tôi không rõ app đang hướng tới informal hay formal.
- **confidence**: `LOW`
