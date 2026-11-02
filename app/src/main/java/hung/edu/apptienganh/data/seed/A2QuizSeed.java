package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.QuizQuestion;

/**
 * A2 Quiz Seed – 5 questions per day × 14 days = 70+ questions.
 * Mix: 2 vocabulary, 2 grammar, 1 conversation/listening per day.
 */
public final class A2QuizSeed {

    private A2QuizSeed() {}

    /** Builds quizzes for one A2 day; learningDayId must be the actual DB id. */
    public static List<QuizQuestion> buildDayQuizzes(int dayIndex, int learningDayId) {
        List<QuizQuestion> q = new ArrayList<>();
        switch (dayIndex) {
            case 1: seedDay29(q, learningDayId); break;
            case 2: seedDay30(q, learningDayId); break;
            case 3: seedDay31(q, learningDayId); break;
            case 4: seedDay32(q, learningDayId); break;
            case 5: seedDay33(q, learningDayId); break;
            case 6: seedDay34(q, learningDayId); break;
            case 7: seedDay35(q, learningDayId); break;
            case 8: seedDay36(q, learningDayId); break;
            case 9: seedDay37(q, learningDayId); break;
            case 10: seedDay38(q, learningDayId); break;
            case 11: seedDay39(q, learningDayId); break;
            case 12: seedDay40(q, learningDayId); break;
            case 13: seedDay41(q, learningDayId); break;
            case 14: seedDay42(q, learningDayId); break;
            default: break;
        }
        return q;
    }

    private static void add(List<QuizQuestion> q, int id, String type, String question,
                            String a, String b, String c, String d, String ans, String explain) {
        q.add(new QuizQuestion(0, id, type, question, a, b, c, d, ans, explain));
    }

    // Day 29 – Review A1 + longer daily conversations
    private static void seedDay29(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Remember' nghĩa là?", "Quên", "Nhớ lại", "Giải thích", "Mô tả", "B", "Remember = nhớ lại.");
        add(q, id, "VOCABULARY", "'Experience' nghĩa là?", "Kinh nghiệm / trải nghiệm", "Thí nghiệm", "Bài kiểm tra", "Kỹ năng", "A", "Experience = kinh nghiệm.");
        add(q, id, "GRAMMAR", "She ___ cooking dinner when I arrived.", "was", "were", "is", "has", "A", "She → was + V-ing (past continuous).");
        add(q, id, "GRAMMAR", "Chọn câu đúng:", "Did you went to the market?", "Did you go to the market?", "Do you went to the market?", "You went to the market?", "B", "Did + V nguyên mẫu.");
        add(q, id, "CONVERSATION", "Kể về cuối tuần trước:", "What did you do last weekend?", "What do you do last weekend?", "What you did last weekend?", "What you do weekend?", "A", "did + V nguyên mẫu.");
    }

    // Day 30 – Past simple regular verbs
    private static void seedDay30(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Walked' là dạng quá khứ của?", "run", "walk", "work", "talk", "B", "walked = quá khứ của walk.");
        add(q, id, "VOCABULARY", "'Cooked' phát âm đuôi -ed là?", "/d/", "/t/", "/ɪd/", "/ed/", "B", "Sau phụ âm vô thanh → /t/.");
        add(q, id, "GRAMMAR", "She ___ dinner last night.", "cook", "cooked", "cooks", "cooking", "B", "Quá khứ → cooked.");
        add(q, id, "GRAMMAR", "They ___ football after class.", "played", "plays", "playing", "play yesterday", "A", "Quá khứ → played.");
        add(q, id, "CONVERSATION", "Kể về hôm qua:", "I walked to school yesterday.", "I walk to school yesterday.", "I was walk to school yesterday.", "I walking to school yesterday.", "A", "Quá khứ đơn: walked.");
    }

    // Day 31 – Past simple irregular verbs
    private static void seedDay31(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "Dạng quá khứ của 'go' là?", "goed", "gone", "went", "go", "C", "go → went (bất quy tắc).");
        add(q, id, "VOCABULARY", "Dạng quá khứ của 'eat' là?", "eated", "ate", "eaten", "eat", "B", "eat → ate (bất quy tắc).");
        add(q, id, "GRAMMAR", "I ___ to the market last Sunday.", "go", "goes", "went", "goed", "C", "go → went.");
        add(q, id, "GRAMMAR", "She ___ noodles for breakfast.", "eat", "ate", "eated", "eating", "B", "eat → ate.");
        add(q, id, "LISTENING", "We ___ a great time at the beach.", "have", "had", "haved", "has", "B", "have → had.");
    }

    // Day 32 – Future with going to
    private static void seedDay32(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Plan' trong ngữ cảnh tương lai nghĩa là?", "Kế hoạch / dự định", "Máy bay", "Mặt phẳng", "Thực vật", "A", "Plan = kế hoạch.");
        add(q, id, "VOCABULARY", "'Trip' nghĩa là?", "Vấp ngã", "Chuyến đi", "Bẫy", "Thú vị", "B", "Trip = chuyến đi.");
        add(q, id, "GRAMMAR", "I ___ visit my family next week.", "am going to", "go to", "will to", "going", "A", "am going to + V.");
        add(q, id, "GRAMMAR", "Are you ___ travel this summer?", "going to", "will", "go to", "going for", "A", "Are you going to...?");
        add(q, id, "CONVERSATION", "Kế hoạch đã định:", "She is going to study abroad next year.", "She will to study abroad.", "She going to study abroad.", "She is study abroad next year.", "A", "is going to + V.");
    }

    // Day 33 – Future with will
    private static void seedDay33(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Promise' nghĩa là?", "Đe dọa", "Hứa", "Đề xuất", "Yêu cầu", "B", "Promise = hứa.");
        add(q, id, "VOCABULARY", "'Probably' nghĩa là?", "Chắc chắn", "Có lẽ / có thể", "Không bao giờ", "Luôn luôn", "B", "Probably = có lẽ.");
        add(q, id, "GRAMMAR", "I ___ help you with that.", "am going to", "will", "would to", "am will", "B", "Quyết định tức thì → will.");
        add(q, id, "GRAMMAR", "I think it ___ rain tonight.", "is going to", "will", "is will", "wills", "B", "Dự đoán không chắc → will.");
        add(q, id, "CONVERSATION", "Lời hứa:", "I won't forget your birthday.", "I will to forget your birthday.", "I not will forget your birthday.", "I will forgot your birthday.", "A", "won't + V nguyên mẫu.");
    }

    // Day 34 – Comparatives
    private static void seedDay34(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Cheaper' là so sánh của?", "cheap", "cheapest", "cheaply", "cheap more", "A", "cheaper = so sánh hơn của cheap.");
        add(q, id, "VOCABULARY", "'Taller' nghĩa là?", "Thấp nhất", "Cao hơn", "Cao nhất", "Rất cao", "B", "Taller = cao hơn.");
        add(q, id, "GRAMMAR", "This phone is ___ than that one.", "cheaper", "cheapest", "more cheap", "the cheaper", "A", "Dùng -er + than.");
        add(q, id, "GRAMMAR", "She is ___ intelligent than her brother.", "more", "most", "much more wrong", "the more", "A", "Tính từ dài → more + adj + than.");
        add(q, id, "CONVERSATION", "So sánh hai vật:", "The bus is slower but cheaper than the taxi.", "The bus is more slow than taxi.", "The bus slower the taxi.", "Bus is cheap more than taxi.", "A", "slower/cheaper + than.");
    }

    // Day 35 – Superlatives
    private static void seedDay35(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Best' là dạng nào của 'good'?", "So sánh hơn", "So sánh nhất", "Hiện tại", "Quá khứ", "B", "best = so sánh nhất của good.");
        add(q, id, "VOCABULARY", "'Most popular' nghĩa là?", "Phổ biến hơn", "Phổ biến nhất", "Không phổ biến", "Hơi phổ biến", "B", "Most popular = phổ biến nhất.");
        add(q, id, "GRAMMAR", "This is ___ cheapest phone in the shop.", "a", "the", "most", "an", "B", "Superlative dùng 'the'.");
        add(q, id, "GRAMMAR", "She is ___ tallest student in our class.", "a", "more", "the", "most", "C", "the + tallest.");
        add(q, id, "CONVERSATION", "Khen ngợi bằng superlative:", "This is the best coffee I have ever had.", "This is best coffee I have had.", "This is the most best coffee.", "This coffee is more better.", "A", "the best + ever.");
    }

    // Day 36 – much/many/a lot of
    private static void seedDay36(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Plenty' nghĩa là?", "Thiếu", "Đủ / rất nhiều", "Hơi ít", "Quá ít", "B", "Plenty = đủ, rất nhiều.");
        add(q, id, "VOCABULARY", "'Enough' nghĩa là?", "Quá nhiều", "Vừa đủ", "Không đủ", "Ít nhất", "B", "Enough = đủ.");
        add(q, id, "GRAMMAR", "How ___ people came to the party?", "much", "many", "a lot", "few", "B", "People đếm được → many.");
        add(q, id, "GRAMMAR", "I don't have ___ time today.", "many", "much", "a lot many", "few", "B", "Time không đếm được → much.");
        add(q, id, "LISTENING", "There is ___ traffic on this road.", "many", "a lot of", "much many", "few", "B", "Traffic không đếm được → a lot of.");
    }

    // Day 37 – should/must/have to
    private static void seedDay37(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Obligation' nghĩa là?", "Lựa chọn", "Nghĩa vụ / bắt buộc", "Lời khuyên", "Kết quả", "B", "Obligation = nghĩa vụ.");
        add(q, id, "VOCABULARY", "'Rule' trong trường học nghĩa là?", "Thước kẻ", "Quy tắc / nội quy", "Phong tục", "Luật pháp", "B", "Rule = quy tắc.");
        add(q, id, "GRAMMAR", "You ___ wear a helmet on a motorbike.", "should", "must", "have", "must to", "B", "Luật bắt buộc → must.");
        add(q, id, "GRAMMAR", "Do I ___ bring my passport?", "should to", "have to", "must", "have", "B", "Hỏi về bắt buộc bên ngoài → have to.");
        add(q, id, "CONVERSATION", "Luật giao thông:", "You must not smoke here.", "You must to not smoke.", "You should not to smoke here.", "You mustn't to smoke here.", "A", "must not + V nguyên mẫu.");
    }

    // Day 38 – Giving advice
    private static void seedDay38(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Recommend' nghĩa là?", "Yêu cầu", "Đề xuất / khuyên", "Bác bỏ", "Từ chối", "B", "Recommend = đề xuất.");
        add(q, id, "VOCABULARY", "'Cut down on' nghĩa là?", "Chặt xuống", "Giảm bớt", "Tăng thêm", "Bắt đầu", "B", "Cut down on = giảm bớt.");
        add(q, id, "GRAMMAR", "You ___ stop staying up late.", "should", "should to", "are should", "should stopping", "A", "should + V nguyên mẫu.");
        add(q, id, "GRAMMAR", "Why don't you ___ a sports club?", "join", "joining", "to join", "joins", "A", "Why don't you + V nguyên mẫu.");
        add(q, id, "CONVERSATION", "Lời khuyên tốt nhất:", "You should see a doctor if you are sick.", "You should to see a doctor.", "You seeing a doctor if sick.", "You must see doctor if sick.", "A", "should + V (không có 'to').");
    }

    // Day 39 – Making plans and invitations
    private static void seedDay39(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Invite' nghĩa là?", "Từ chối", "Mời", "Chờ đợi", "Hỏi", "B", "Invite = mời.");
        add(q, id, "VOCABULARY", "'Free' trong 'are you free?' nghĩa là?", "Miễn phí", "Tự do / rảnh rỗi", "Thân thiện", "Vui vẻ", "B", "Are you free? = bạn có rảnh không?");
        add(q, id, "GRAMMAR", "Would you ___ come to the cinema?", "like to", "like", "like for", "liking to", "A", "Would you like to + V.");
        add(q, id, "GRAMMAR", "___ we meet at six o'clock?", "Will", "Do", "Shall", "Should", "C", "Shall we...? = Chúng ta...nhé?");
        add(q, id, "CONVERSATION", "Lời mời đúng:", "Would you like to join us for dinner?", "Would you like joining us?", "Do you like to join us for dinner?", "Would you joining for dinner?", "A", "Would you like to + V.");
    }

    // Day 40 – Shopping and problem solving
    private static void seedDay40(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Receipt' nghĩa là?", "Hóa đơn", "Biên lai", "Thực đơn", "Phiếu giảm giá", "B", "Receipt = biên lai mua hàng.");
        add(q, id, "VOCABULARY", "'Refund' nghĩa là?", "Đổi hàng", "Hoàn tiền", "Thanh toán thêm", "Mua thêm", "B", "Refund = hoàn tiền.");
        add(q, id, "GRAMMAR", "Can I ___ this for a smaller size?", "change", "exchange", "trade", "switch exchange", "B", "Exchange = đổi hàng.");
        add(q, id, "GRAMMAR", "The total ___ two hundred thousand dong.", "are", "is", "was are", "be", "B", "Total là số ít → is.");
        add(q, id, "CONVERSATION", "Đổi hàng:", "This shirt is the wrong size. Can I exchange it?", "This shirt wrong size. I exchange it.", "Can I change this shirt for other?", "I want exchange wrong size shirt.", "A", "Câu hoàn chỉnh và lịch sự.");
    }

    // Day 41 – Travel problems
    private static void seedDay41(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Delayed' nghĩa là?", "Bị hủy", "Bị trễ", "Đúng giờ", "Nhanh", "B", "Delayed = bị trễ.");
        add(q, id, "VOCABULARY", "'Luggage' nghĩa là?", "Vé máy bay", "Hành lý", "Hộ chiếu", "Vali đơn", "B", "Luggage = hành lý (không đếm được).");
        add(q, id, "GRAMMAR", "My flight ___ delayed by two hours.", "is was", "was", "were", "has", "B", "My flight → was delayed.");
        add(q, id, "GRAMMAR", "I think I ___ my passport.", "lose", "lost", "am losing wrong", "have lost wrong", "B", "Quá khứ → lost.");
        add(q, id, "CONVERSATION", "Báo mất đồ:", "I need to report a missing bag.", "I need to report lost bag.", "I want report missing bag.", "I lose bag need report.", "A", "report a missing bag.");
    }

    // Day 42 – A2 final review
    private static void seedDay42(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Achievement' nghĩa là?", "Thử thách", "Thành tựu", "Thất bại", "Kế hoạch", "B", "Achievement = thành tựu.");
        add(q, id, "VOCABULARY", "'Gradually' nghĩa là?", "Đột ngột", "Dần dần", "Nhanh chóng", "Ngay lập tức", "B", "Gradually = dần dần.");
        add(q, id, "GRAMMAR", "Last weekend I ___ to a new restaurant.", "go", "went", "have gone", "goes", "B", "Quá khứ → went.");
        add(q, id, "GRAMMAR", "This city is ___ and ___ exciting than my hometown.", "big / most", "bigger / more", "biggest / more", "bigger / much", "B", "So sánh hơn → bigger / more exciting.");
        add(q, id, "CONVERSATION", "Câu A2 chuẩn:", "I am going to learn to drive next year.", "I am going to learning to drive.", "I will to learn drive next year.", "I going learn drive next year.", "A", "am going to + V nguyên mẫu.");
    }
}
