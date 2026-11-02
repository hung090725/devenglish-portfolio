package hung.edu.apptienganh;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.QuizQuestion;
import hung.edu.apptienganh.domain.quiz.QuizScorer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class QuizScorerTest {

  @Test
  public void calculate_correctCountsAndPercent() {
    List<QuizScorer.AnswerRecord> answers = new ArrayList<>();
    answers.add(new QuizScorer.AnswerRecord(1, "A", "A", true, ""));
    answers.add(new QuizScorer.AnswerRecord(2, "B", "B", true, ""));
    answers.add(new QuizScorer.AnswerRecord(3, "A", "C", false, "Giải thích"));

    QuizScorer.QuizScoreResult result = QuizScorer.calculate(answers);

    assertEquals(3, result.getTotalQuestions());
    assertEquals(2, result.getCorrectCount());
    assertEquals(1, result.getWrongCount());
    assertEquals(67, result.getScorePercent());
  }

  @Test
  public void recordAnswer_emptyAnswerNotCrash() {
    QuizQuestion q = new QuizQuestion();
    q.setId(1);
    q.setCorrectAnswer("B");
    q.setExplanationVi("Test");

    QuizScorer.AnswerRecord record = QuizScorer.recordAnswer(q, "");
    assertFalse(record.isCorrect());

    QuizScorer.AnswerRecord nullRecord = QuizScorer.recordAnswer(q, null);
    assertFalse(nullRecord.isCorrect());
  }

  @Test
  public void calculate_emptyList_returnsZero() {
    QuizScorer.QuizScoreResult result = QuizScorer.calculate(new ArrayList<>());
    assertEquals(0, result.getScorePercent());
    assertEquals(0, result.getTotalQuestions());
  }
}
