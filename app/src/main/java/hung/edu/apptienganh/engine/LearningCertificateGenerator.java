package hung.edu.apptienganh.engine;

public class LearningCertificateGenerator {
    public String generateReportText(String level, int totalLessons, int vocabScore, int grammarScore) {
        return "Internal Learning Certificate\n" +
               "Level Completed: " + level + "\n" +
               "Lessons Completed: " + totalLessons + "\n" +
               "Vocab Avg: " + vocabScore + "%\n" +
               "Grammar Avg: " + grammarScore + "%\n" +
               "--- \n" +
               "Disclaimer: Đây là chứng nhận nội bộ, không thay thế chứng chỉ chính thức.";
    }
}
