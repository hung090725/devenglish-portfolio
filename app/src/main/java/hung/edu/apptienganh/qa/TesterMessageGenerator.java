package hung.edu.apptienganh.qa;

public class TesterMessageGenerator {

    public static String generateZaloMessage(String versionName) {
        return "Chào bạn, mình vừa hoàn thành bản thử nghiệm (Beta " + versionName + ") của ứng dụng học tiếng Anh EnglishPath AI.\n" +
                "Bạn rảnh thì tải file app-release.apk về cài thử và cho mình xin ý kiến nhé! 😄\n\n" +
                "📌 Cách cài đặt trên Android:\n" +
                "1. Nhấp vào file apk để tải về máy.\n" +
                "2. Nếu máy cảnh báo, chọn 'Cài đặt' -> Bật 'Cho phép từ nguồn này'.\n" +
                "3. Quay lại và bấm Cài đặt.\n\n" +
                "💡 Một số lưu ý:\n" +
                "- Nhớ cấp quyền Micro để dùng chức năng Luyện nói.\n" +
                "- Thử học bài A0 Ngày 1, làm Quiz và xem chức năng Loa/Mic.\n" +
                "- Nếu có lỗi, hãy bấm Cài đặt -> Gửi phản hồi Beta, hoặc gửi ảnh/video + file Diagnostics JSON cho mình.\n\n" +
                "Cảm ơn bạn nhiều! 🙏 (Vui lòng không share file ra ngoài vì đây là bản test nội bộ)";
    }

    public static String generateTelegramMessage(String versionName) {
        return generateZaloMessage(versionName); // Tạm thời dùng chung nội dung
    }

    public static String generateShortBugReportInstruction() {
        return "Hướng dẫn báo lỗi nhanh:\n1. Chụp màn hình lỗi.\n2. Vào App -> Cài Đặt -> Copy Diagnostics.\n3. Gửi cả 2 cho Dev.";
    }

    public static String generateInstallInstruction() {
        return "Lưu ý cài đặt:\nCần bật 'Install unknown apps' (Cài đặt ứng dụng không xác định) cho Zalo/Trình duyệt để cài đặt file APK.";
    }
}
