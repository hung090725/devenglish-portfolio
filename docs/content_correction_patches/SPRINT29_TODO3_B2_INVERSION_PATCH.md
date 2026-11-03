# SPRINT29_TODO3_B2_INVERSION_PATCH

## Tóm tắt
- **ID**: Gram-B2-Inversion
- **Vấn đề**: Câu ví dụ của cấu trúc đảo ngữ (Inversion) quá dài (20 chữ) gây khó hiểu cho trình độ B2.
- **Giải pháp**: Cắt ngắn ví dụ, đưa về một câu súc tích nhưng vẫn thể hiện rõ chủ điểm.

## Nội dung Patch
- **oldValue**: Không sai nhưng ví dụ quá dài.
- **newValue**: "Never have I seen such a clear solution."
- **explanationVi**: Đảo ngữ dùng để nhấn mạnh. Sau trạng từ phủ định/giới hạn, trợ động từ đứng trước chủ ngữ.
- **Confidence**: HIGH
- **Lý do**: Ví dụ ngắn hơn nhiều (8 từ) so với ví dụ trước, giúp người học nhận dạng cấu trúc `Never + trợ động từ + chủ ngữ` một cách rõ ràng.
