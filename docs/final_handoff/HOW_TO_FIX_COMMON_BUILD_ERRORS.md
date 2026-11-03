# Sửa Lỗi Build Thường Gặp

1. **Lỗi: "Room cannot verify the data integrity..."**
   - *Nguyên nhân*: Bạn sửa entity (`UserProfile`, `Vocabulary`) nhưng không tăng version trong `@Database`.
   - *Cách sửa*: Vào `AppDatabase.java`, tăng `version` lên 1 số, và viết class `Migration`.

2. **Lỗi: "Missing translation" trong Lint**
   - *Nguyên nhân*: Bạn thêm string mới vào `strings.xml` nhưng không thêm vào `strings_en.xml` (hoặc ngược lại).
   - *Cách sửa*: Bổ sung dịch thuật hoặc thêm `tools:ignore="MissingTranslation"`.

3. **Lỗi OutOfMemory (OOM) khi build**
   - *Nguyên nhân*: Máy tính thiếu RAM cho Gradle.
   - *Cách sửa*: Tăng `org.gradle.jvmargs=-Xmx2048m` (hoặc cao hơn) trong `gradle.properties`.
