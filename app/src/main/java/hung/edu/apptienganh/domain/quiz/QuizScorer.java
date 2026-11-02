package hung.edu.apptienganh.domain.quiz;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.QuizQuestion;

public final class QuizScorer {

    public static class QuizScoreResult {
        private final int scorePercent;
        private final int totalQuestions;
        private final int correctCount;
        private final int wrongCount;

        public QuizScoreResult(int scorePercent, int totalQuestions, int correctCount, int wrongCount) {
            this.scorePercent = scorePercent;
            this.totalQuestions = totalQuestions;
            this.correctCount = correctCount;
            this.wrongCount = wrongCount;
        }

        public int getScorePercent() {
            return scorePercent;
        }

        public int getTotalQuestions() {
            return totalQuestions;
        }

        public int getCorrectCount() {
            return correctCount;
        }

        public int getWrongCount() {
            return wrongCount;
        }
    }

    public static class AnswerRecord {
        private final int questionId;
        private final String selectedAnswer;
        private final String correctAnswer;
        private final boolean correct;
        private final String explanationVi;

        public AnswerRecord(int questionId, String selectedAnswer, String correctAnswer,
                            boolean correct, String explanationVi) {
            this.questionId = questionId;
            this.selectedAnswer = selectedAnswer == null ? "" : selectedAnswer;
            this.correctAnswer = correctAnswer == null ? "" : correctAnswer;
            this.correct = correct;
            this.explanationVi = explanationVi == null ? "" : explanationVi;
        }

        public int getQuestionId() {
            return questionId;
        }

        public String getSelectedAnswer() {
            return selectedAnswer;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public boolean isCorrect() {
            return correct;
        }

        public String getExplanationVi() {
            return explanationVi;
        }
    }

    public static QuizScoreResult calculate(List<AnswerRecord> answers) {
        if (answers == null || answers.isEmpty()) {
            return new QuizScoreResult(0, 0, 0, 0);
        }
        int total = answers.size();
        int correct = 0;
        for (AnswerRecord a : answers) {
            if (a.isCorrect()) correct++;
        }
        int wrong = total - correct;
        int percent = (int) Math.round(((double) correct / total) * 100.0);
        return new QuizScoreResult(percent, total, correct, wrong);
    }

    public static AnswerRecord recordAnswer(QuizQuestion question, String selected) {
        if (question == null) {
            return new AnswerRecord(0, selected, "", false, "");
        }
        String sel = selected == null ? "" : selected.trim();
        boolean correct = !sel.isEmpty() && sel.equalsIgnoreCase(question.getCorrectAnswer());
        return new AnswerRecord(
                question.getId(),
                sel,
                question.getCorrectAnswer(),
                correct,
                question.getExplanationVi()
        );
    }

    public static List<AnswerRecord> wrongAnswers(List<AnswerRecord> answers) {
        List<AnswerRecord> wrong = new ArrayList<>();
        if (answers == null) return wrong;
        for (AnswerRecord a : answers) {
            if (!a.isCorrect()) wrong.add(a);
        }
        return wrong;
    }
}
