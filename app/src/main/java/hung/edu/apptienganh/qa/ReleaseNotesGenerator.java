package hung.edu.apptienganh.qa;

public class ReleaseNotesGenerator {

    public static String generateMarkdownReleaseNotes(String versionName) {
        return "# Release Notes - EnglishPath AI (Version " + versionName + ")\n\n" +
                "## 1. Tính năng chính\n" +
                "- Hỗ trợ toàn bộ từ vựng và ngữ pháp trình độ A0, A1, A2, B1.\n" +
                "- Hệ thống Spaced Repetition (SRS) giúp nhắc nhở ôn tập.\n" +
                "- Luyện phát âm (TTS & Microphone) với hệ thống chấm điểm chi tiết.\n" +
                "- Backup & Restore dữ liệu cục bộ qua JSON an toàn.\n\n" +
                "## 2. Dành cho Solo QA & Tester\n" +
                "- Nâng cấp cơ chế kiểm thử nội bộ (Solo QA Dashboard).\n" +
                "- Xử lý lỗi Permission cho Microphone an toàn (chống crash).\n" +
                "- Đảm bảo Main Thread không bị block khi truy vấn DB nặng.\n\n" +
                "## 3. Known Limitations\n" +
                "- Chưa có tester đánh giá diện rộng.\n" +
                "- Một số dòng máy cũ có thể không có sẵn English TTS Engine.\n" +
                "- Chưa lưu dữ liệu trên server (Cloud).\n\n" +
                "## 4. Cách báo lỗi (Bug Report)\n" +
                "Mở Cài đặt -> Copy Diagnostics và gửi kèm ảnh/video lỗi cho Developer.\n";
    }

    public static String generatePlainTextReleaseNotes(String versionName) {
        return "Release Notes - EnglishPath AI (v" + versionName + ")\n" +
                "Tính năng: A0-B1, SRS, TTS/Mic, Backup/Restore.\n" +
                "Known Limitations: Cần English TTS engine, dữ liệu lưu cục bộ.\n" +
                "Báo lỗi: Copy Diagnostics trong Cài đặt và gửi kèm ảnh.";
    }
}
