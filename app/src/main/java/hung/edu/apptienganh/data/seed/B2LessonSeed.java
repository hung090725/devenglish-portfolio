package hung.edu.apptienganh.data.seed;

import hung.edu.apptienganh.data.local.entity.LearningDay;
import hung.edu.apptienganh.data.local.entity.Conversation;
import hung.edu.apptienganh.data.local.entity.ConversationLine;
import hung.edu.apptienganh.data.local.entity.QuizQuestion;
import java.util.ArrayList;
import java.util.List;

public class B2LessonSeed {
    public static List<LearningDay> getLearningDays() {
        List<LearningDay> list = new ArrayList<>();
        for (int i = 1; i <= 14; i++) {
            list.add(new LearningDay("B2", i, "Ngày " + (56 + i) + " B2 Title", "Objective " + i, "Summary " + i,
            "Pronunciation " + i, "vocab list", "grammar point", i, "conversation", "listening", "speaking practice", 
            25, i, i));
        }
        return list;
    }

    public static List<Conversation> getConversations() {
        List<Conversation> list = new ArrayList<>();
        for (int i = 1; i <= 18; i++) {
            list.add(new Conversation("B2", "Topic " + i, "Situation " + i, "Situation Vi " + i));
        }
        return list;
    }

    public static List<ConversationLine> getConversationLines(int conversationId, int dayIndex) {
        List<ConversationLine> lines = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            String role = (i % 2 == 1) ? "Role A" : "Role B";
            lines.add(new ConversationLine(conversationId, role, "B2 sentence " + i, "/ipa/", "Câu B2 " + i, i));
        }
        return lines;
    }

    public static List<QuizQuestion> getQuizQuestions() {
        List<QuizQuestion> list = new ArrayList<>();
        for (int i = 1; i <= 70; i++) {
            int dayIndex = ((i - 1) / 5) + 1;
            list.add(new QuizQuestion(1, dayIndex, "grammar", "B2 Question " + i + "?",
            "Option A", "Option B", "Option C", "Option D", "A", "Explanation " + i));
        }
        return list;
    }
}
