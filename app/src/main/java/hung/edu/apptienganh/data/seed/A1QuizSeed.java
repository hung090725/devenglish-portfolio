package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.QuizQuestion;

public final class A1QuizSeed {

    private A1QuizSeed() {}

    /** Builds quizzes for one A1 day; learningDayId must be the actual DB id. */
    public static List<QuizQuestion> buildDayQuizzes(int dayIndex, int learningDayId) {
        List<QuizQuestion> q = new ArrayList<>();
        switch (dayIndex) {
            case 1: seedDay15(q, learningDayId); break;
            case 2: seedDay16(q, learningDayId); break;
            case 3: seedDay17(q, learningDayId); break;
            case 4: seedDay18(q, learningDayId); break;
            case 5: seedDay19(q, learningDayId); break;
            case 6: seedDay20(q, learningDayId); break;
            case 7: seedDay21(q, learningDayId); break;
            case 8: seedDay22(q, learningDayId); break;
            case 9: seedDay23(q, learningDayId); break;
            case 10: seedDay24(q, learningDayId); break;
            case 11: seedDay25(q, learningDayId); break;
            case 12: seedDay26(q, learningDayId); break;
            case 13: seedDay27(q, learningDayId); break;
            case 14: seedDay28(q, learningDayId); break;
            default: break;
        }
        return q;
    }

    private static void add(List<QuizQuestion> q, int learningDayId, String type, String question,
                            String a, String b, String c, String d, String ans, String explain) {
        q.add(new QuizQuestion(0, learningDayId, type, question, a, b, c, d, ans, explain));
    }

    private static void seedDay15(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Usually' nghĩa là gì?", "Không bao giờ", "Thường", "Đôi khi", "Luôn luôn", "B", "'Usually' = thường.");
        add(q, id, "VOCABULARY", "'Breakfast' là bữa ăn nào?", "Bữa trưa", "Bữa tối", "Bữa sáng", "Bữa phụ", "C", "Breakfast = bữa sáng.");
        add(q, id, "GRAMMAR", "He ___ to work every day.", "go", "goes", "going", "gone", "B", "He → goes.");
        add(q, id, "GRAMMAR", "They ___ TV in the evening.", "watches", "watch", "watchs", "watching", "B", "They → watch.");
        add(q, id, "CONVERSATION", "Hỏi giờ thức dậy:", "What time do you wake up?", "Where do you live?", "How old are you?", "What is your name?", "A", "What time = hỏi giờ.");
    }

    private static void seedDay16(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Never' nghĩa là?", "Luôn luôn", "Không bao giờ", "Thường", "Đôi khi", "B", "Never = không bao giờ.");
        add(q, id, "VOCABULARY", "'Office' là gì?", "Bệnh viện", "Văn phòng", "Nhà ga", "Chợ", "B", "Office = văn phòng.");
        add(q, id, "GRAMMAR", "She ___ English every morning.", "study", "studies", "studying", "studys", "B", "She → studies.");
        add(q, id, "GRAMMAR", "Chọn câu đúng:", "I lives in Hanoi.", "I live in Hanoi.", "I living in Hanoi.", "I live at Hanoi wrong", "B", "I → live.");
        add(q, id, "LISTENING", "Do you ___ TV at night?", "watch", "watches", "watching", "watched", "A", "Do you + V nguyên mẫn.");
    }

    private static void seedDay17(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Prefer' nghĩa là?", "Cần", "Thích hơn", "Ghét", "Quên", "B", "Prefer = thích hơn.");
        add(q, id, "VOCABULARY", "'Travel' nghĩa là?", "Làm việc", "Du lịch", "Ngủ", "Mua sắm", "B", "Travel = du lịch.");
        add(q, id, "GRAMMAR", "I want ___ English.", "learn", "learning", "to learn", "learns", "C", "want + to V.");
        add(q, id, "GRAMMAR", "She needs ___ water.", "some", "any", "many", "much many", "A", "need some + noun (affirmative).");
        add(q, id, "CONVERSATION", "Nói sở thích:", "I like reading books.", "I am reading like.", "I like to reading wrong", "Reading I like books wrong", "A", "like + V-ing hoặc like + noun.");
    }

    private static void seedDay18(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Fridge' là gì?", "Tủ lạnh", "Bàn", "Ghế", "Giường", "A", "Fridge = tủ lạnh.");
        add(q, id, "VOCABULARY", "'Near' nghĩa là?", "Xa", "Gần", "Trong", "Trên", "B", "Near = gần.");
        add(q, id, "GRAMMAR", "There ___ two chairs.", "is", "are", "am", "be", "B", "Two chairs → are.");
        add(q, id, "GRAMMAR", "There ___ a book on the table.", "are", "is", "am", "be", "B", "A book → is.");
        add(q, id, "CONVERSATION", "Hỏi có phòng khám không:", "Is there a clinic near here?", "There is a clinic?", "Has there clinic?", "Do there a clinic?", "A", "Is there + singular.");
    }

    private static void seedDay19(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Bottle' nghĩa là?", "Chai", "Gói", "Hộp", "Túi", "A", "Bottle = chai.");
        add(q, id, "VOCABULARY", "'Rice' là danh từ?", "Đếm được", "Không đếm được", "Số nhiều bất quy tắc", "Tính từ", "B", "Rice không đếm được.");
        add(q, id, "GRAMMAR", "How ___ apples do you have?", "much", "many", "some", "any much", "B", "Apples đếm được → many.");
        add(q, id, "GRAMMAR", "How ___ water do you drink?", "many", "much", "some many", "any many", "B", "Water không đếm được → much.");
        add(q, id, "LISTENING", "I bought two ___.", "apple", "apples", "appleses", "an apples", "B", "Two → plural.");
    }

    private static void seedDay20(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Question' nghĩa là?", "Câu trả lời", "Câu hỏi", "Bài tập", "Chữ ký", "B", "Question = câu hỏi.");
        add(q, id, "VOCABULARY", "'Friend' số nhiều là?", "friend", "friends", "friendes", "friender", "B", "friends.");
        add(q, id, "GRAMMAR", "Do you have ___ questions?", "some", "any", "much", "many much", "B", "Câu hỏi → any.");
        add(q, id, "GRAMMAR", "I have ___ friends in London.", "any", "some", "much", "little", "B", "Câu khẳng định → some.");
        add(q, id, "CONVERSATION", "Không còn sữa:", "There isn't any milk.", "There isn't some milk.", "There aren't any milk.", "There is any milk not.", "A", "Phủ định + any.");
    }

    private static void seedDay21(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Borrow' nghĩa là?", "Mượn", "Cho", "Mua", "Bán", "A", "Borrow = mượn.");
        add(q, id, "VOCABULARY", "'Drive' nghĩa là?", "Lái xe", "Bơi", "Chạy", "Bay", "A", "Drive = lái xe.");
        add(q, id, "GRAMMAR", "___ you help me?", "Can", "Do", "Are", "Is", "A", "Can you...? = xin phép/giúp.");
        add(q, id, "GRAMMAR", "She ___ swim.", "can to", "can", "cans", "can swims", "B", "can + V nguyên mẫn.");
        add(q, id, "CONVERSATION", "Xin giúp đỡ:", "Can you help me?", "You can help me?", "Help you can me?", "Do you can help?", "A", "Can you help me? chuẩn.");
    }

    private static void seedDay22(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Kitchen' là?", "Phòng ngủ", "Nhà bếp", "Phòng tắm", "Phòng khách", "B", "Kitchen = nhà bếp.");
        add(q, id, "VOCABULARY", "'Behind' nghĩa là?", "Trước", "Sau/đ detrás", "Ở giữa", "Ở trên", "B", "Behind = phía sau.");
        add(q, id, "GRAMMAR", "The cat is ___ the chair.", "in", "on", "under", "at under", "C", "Under = dưới.");
        add(q, id, "GRAMMAR", "She is ___ the kitchen.", "on", "in", "at in", "under", "B", "In the kitchen.");
        add(q, id, "LISTENING", "The shop is ___ the bank and the cafe.", "between", "under", "in", "on", "A", "Between = ở giữa.");
    }

    private static void seedDay23(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Weekend' nghĩa là?", "Tuần", "Cuối tuần", "Ngày lễ", "Buổi sáng", "B", "Weekend = cuối tuần.");
        add(q, id, "VOCABULARY", "'Afternoon' là?", "Buổi sáng", "Buổi trưa/chiều", "Buổi tối", "Ban đêm", "B", "Afternoon = buổi chiều.");
        add(q, id, "GRAMMAR", "We meet ___ Sunday.", "in", "at", "on", "to", "C", "On + day.");
        add(q, id, "GRAMMAR", "I wake up ___ six o'clock.", "in", "on", "at", "by", "C", "At + time.");
        add(q, id, "CONVERSATION", "Sinh nhật tháng Năm:", "My birthday is in May.", "My birthday is on May.", "My birthday is at May.", "My birthday in the May.", "A", "In + month.");
    }

    private static void seedDay24(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Practice' nghĩa là?", "Luyện tập", "Nghỉ ngơi", "Ngủ", "Ăn", "A", "Practice = luyện tập.");
        add(q, id, "VOCABULARY", "'Often' nghĩa là?", "Hiếm khi", "Thường xuyên", "Không bao giờ", "Luôn luôn", "B", "Often = thường xuyên.");
        add(q, id, "GRAMMAR", "He ___ goes to the gym.", "go", "goes often wrong", "often", "going", "C", "Often đứng trước V (hoặc sau to be).");
        add(q, id, "GRAMMAR", "They ___ watch TV late.", "always", "never", "never always", "never don't", "B", "Never = không bao giờ.");
        add(q, id, "LISTENING", "I ___ brush my teeth.", "always", "always am", "am always brush", "brush always am", "A", "Always + V.");
    }

    private static void seedDay25(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Moment' nghĩa là?", "Giờ", "Khoảnh khắc/lúc này", "Ngày", "Tuần", "B", "At the moment = lúc này.");
        add(q, id, "VOCABULARY", "'Rain' động từ nghĩa là?", "Nắng", "Mưa", "Gió", "Lạnh", "B", "Rain = mưa.");
        add(q, id, "GRAMMAR", "She ___ cooking dinner.", "is", "are", "am", "be", "A", "She is cooking.");
        add(q, id, "GRAMMAR", "Look! It ___ raining.", "is", "are", "am", "be", "A", "It is raining.");
        add(q, id, "CONVERSATION", "Hành động đang diễn ra:", "I am studying now.", "I study now always.", "I am study now.", "I studying am now.", "A", "am/is/are + V-ing.");
    }

    private static void seedDay26(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Yesterday' nghĩa là?", "Hôm nay", "Hôm qua", "Ngày mai", "Tuần trước", "B", "Yesterday = hôm qua.");
        add(q, id, "VOCABULARY", "'Airport' là?", "Bến xe", "Sân bay", "Bến cảng", "Ga tàu", "B", "Airport = sân bay.");
        add(q, id, "GRAMMAR", "I ___ at home yesterday.", "was", "were", "am", "be", "A", "I → was.");
        add(q, id, "GRAMMAR", "They ___ happy last night.", "was", "were", "are", "is", "B", "They → were.");
        add(q, id, "CONVERSATION", "Ở sân bay Chủ nhật trước:", "We were at the airport last Sunday.", "We was at the airport.", "We are at the airport yesterday.", "We were at airport the Sunday.", "A", "We were + past.");
    }

    private static void seedDay27(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Passport' là?", "Vé máy bay", "Hộ chiếu", "Vali", "Bản đồ", "B", "Passport = hộ chiếu.");
        add(q, id, "VOCABULARY", "'Suitcase' nghĩa là?", "Vali", "Túi xách nhỏ", "Vé", "Ghế", "A", "Suitcase = vali.");
        add(q, id, "GRAMMAR", "May I see your passport, ___?", "please", "please you", "you please wrong", "pleases", "A", "please cuối câu lịch sự.");
        add(q, id, "GRAMMAR", "I would like ___ book a room.", "to", "for", "at", "book to", "A", "would like to + V.");
        add(q, id, "CONVERSATION", "Hỏi cổng lên máy bay:", "Is my gate number on the ticket?", "Where gate my is?", "Gate number where ticket?", "My gate is ticket where?", "A", "Câu hỏi chuẩn.");
    }

    private static void seedDay28(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Wallet' nghĩa là?", "Ví", "Chìa khóa", "Điện thoại", "Kính", "A", "Wallet = ví.");
        add(q, id, "VOCABULARY", "'Abroad' nghĩa là?", "Trong nước", "Ở nước ngoài", "Ở nhà", "Ở trường", "B", "Abroad = ở nước ngoài.");
        add(q, id, "GRAMMAR", "I stay home ___ it is raining.", "but", "because", "and because", "so because", "B", "Because = vì.");
        add(q, id, "GRAMMAR", "Open the door, ___.", "please", "you please", "please you open", "opening", "A", "Imperative + please.");
        add(q, id, "CONVERSATION", "Tự giới thiệu A1:", "Hello, I am Hung. I live in Vietnam.", "Hello, I Hung am live Vietnam.", "Hello, I am Hung live in Vietnam am.", "Hello Hung I Vietnam live.", "A", "Câu tự giới thiệu đúng ngữ pháp.");
    }
}
