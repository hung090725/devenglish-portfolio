package hung.edu.apptienganh.engine;

import hung.edu.apptienganh.data.local.entity.ExamResult;
import hung.edu.apptienganh.data.local.entity.ExamSession;

public class ExamScorer {
    public ExamResult scoreSession(ExamSession session) {
        ExamResult result = new ExamResult();
        result.sessionId = session.id;
        result.scorePercent = (session.totalQuestions > 0) ? (session.correctCount * 100 / session.totalQuestions) : 0;
        result.cefrSuggestion = "A2";
        result.strengths = "Vocabulary";
        result.weaknesses = "Grammar";
        result.recommendedNextActions = "Review B1 modules";
        result.createdAt = System.currentTimeMillis();
        return result;
    }
}
