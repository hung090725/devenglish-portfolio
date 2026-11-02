package hung.edu.apptienganh;

import org.junit.Test;

import hung.edu.apptienganh.data.seed.A1ConversationSeed;
import hung.edu.apptienganh.data.seed.A1GrammarSeed;
import hung.edu.apptienganh.data.seed.A1LearningDaySeed;
import hung.edu.apptienganh.data.seed.A1QuizSeed;
import hung.edu.apptienganh.data.seed.A1VocabularySeed;
import hung.edu.apptienganh.data.local.entity.LearningDay;
import hung.edu.apptienganh.data.local.entity.QuizQuestion;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class A1SeedCountTest {

    @Test
    public void a1LearningDays_atLeast14() {
        List<LearningDay> days = A1LearningDaySeed.buildLearningDays(14, 10);
        assertTrue(days.size() >= 14);
        Set<Integer> orderIndexes = new HashSet<>();
        for (LearningDay day : days) {
            assertEquals("A1", day.getLevel());
            assertFalse(day.getTitle().trim().isEmpty());
            assertFalse(day.getObjectiveVi().trim().isEmpty());
            orderIndexes.add(day.getOrderIndex());
        }
        assertEquals(14, orderIndexes.size());
    }

    @Test
    public void a1Vocabulary_atLeast150() {
        assertTrue(A1VocabularySeed.buildVocabularyList().size() >= 150);
    }

    @Test
    public void a1Grammar_atLeast14() {
        assertTrue(A1GrammarSeed.buildGrammarPoints().size() >= 14);
    }

    @Test
    public void a1Conversations_atLeast12() {
        assertTrue(A1ConversationSeed.buildConversations().size() >= 12);
        assertTrue(A1ConversationSeed.buildConversationLines(10).size() >= 96);
    }

    @Test
    public void a1Quiz_atLeast70_withValidAnswers() {
        int total = 0;
        for (int day = 1; day <= 14; day++) {
            List<QuizQuestion> quizzes = A1QuizSeed.buildDayQuizzes(day, day + 100);
            assertEquals(5, quizzes.size());
            for (QuizQuestion q : quizzes) {
                assertTrue(isValidAnswer(q));
            }
            total += quizzes.size();
        }
        assertTrue(total >= 70);
    }

    private boolean isValidAnswer(QuizQuestion q) {
        String answer = q.getCorrectAnswer();
        return "A".equals(answer) || "B".equals(answer)
                || "C".equals(answer) || "D".equals(answer);
    }
}
