# Content Import Schema

Tài liệu này định nghĩa cấu trúc JSON hợp lệ để import nội dung bài học offline vào ứng dụng EnglishPath AI.

## 1. Vocabulary JSON Schema
```json
{
  "word": "example",
  "meaningVi": "ví dụ",
  "ipa": "/ig'za:mpl/",
  "level": "A1",
  "exampleEn": "This is an example.",
  "exampleVi": "Đây là một ví dụ."
}
```

## 2. Reading JSON Schema
```json
{
  "level": "B1",
  "topic": "Technology",
  "title": "The Future of AI",
  "passageText": "AI is evolving rapidly...",
  "questions": [
    {
      "type": "main_idea",
      "questionText": "What is the passage about?",
      "optionsJson": "["AI", "Food", "Travel", "Music"]",
      "correctOptionIndex": 0
    }
  ]
}
```
