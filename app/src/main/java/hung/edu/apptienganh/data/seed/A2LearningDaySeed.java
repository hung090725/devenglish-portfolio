package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.LearningDay;

public final class A2LearningDaySeed {

    private A2LearningDaySeed() {}

    public static List<LearningDay> buildLearningDays(int grammarIdOffset, int conversationIdOffset) {
        List<LearningDay> days = new ArrayList<>();

        days.add(day(1, "Ngày 29: Review A1 + longer daily conversations",
                "Ôn tập A1 và luyện hội thoại đời sống dài hơn.",
                "Kết hợp ngữ pháp A1 vào hội thoại thực tế phức tạp hơn.",
                "Ôn nối âm: /d/ + /j/ → /dʒ/ trong 'did you', 'would you'.",
                "review, conversation, daily, routine, habit, improve, practice, describe, experience, remember",
                "Present simple review", grammarIdOffset + 1,
                "What do you usually do on weekends?|Cuối tuần bạn thường làm gì?\nI went to the market yesterday.|Tôi đã đi chợ hôm qua.",
                "She usually cooks dinner at home.|Cô ấy thường nấu cơm tại nhà.\nDid you go anywhere last Sunday?|Chủ nhật trước bạn có đi đâu không?",
                "I usually spend weekends with my family.\nLast weekend I visited my grandparents.\nI want to travel more in the future.",
                22, 29, conversationIdOffset + 1));

        days.add(day(2, "Ngày 30: Past simple regular verbs",
                "Kể chuyện quá khứ với động từ có quy tắc.",
                "Thêm -ed vào động từ để nói về quá khứ.",
                "Phát âm đuôi -ed: /t/ (watched), /d/ (played), /ɪd/ (wanted).",
                "walked, cooked, played, worked, studied, listened, visited, cleaned, watched, talked",
                "Past simple regular verbs", grammarIdOffset + 1,
                "I walked to school yesterday.|Hôm qua tôi đi bộ đến trường.\nShe cooked dinner last night.|Tối qua cô ấy nấu cơm.",
                "We played football after class.|Chúng tôi đá bóng sau giờ học.\nHe watched a film last weekend.|Anh ấy xem phim cuối tuần rồi.",
                "I studied English last night.\nShe cleaned her room this morning.\nWe visited our friends last week.",
                22, 30, conversationIdOffset + 1));

        days.add(day(3, "Ngày 31: Past simple irregular verbs",
                "Kể chuyện quá khứ với động từ bất quy tắc.",
                "Học dạng quá khứ của các động từ phổ biến bất quy tắc.",
                "went /went/, came /keɪm/, ate /eɪt/, drank /dræŋk/, saw /sɔː/.",
                "went, came, ate, drank, saw, had, bought, took, met, got",
                "Past simple irregular verbs", grammarIdOffset + 2,
                "I went to the market last Sunday.|Chủ nhật rồi tôi đi chợ.\nShe ate noodles for breakfast.|Cô ấy ăn mì cho bữa sáng.",
                "We had a great time at the beach.|Chúng tôi vui lắm ở bãi biển.\nHe met his old friend yesterday.|Anh ấy gặp người bạn cũ hôm qua.",
                "I went to the cinema last Friday.\nShe bought new shoes yesterday.\nWe had lunch together.",
                22, 31, conversationIdOffset + 2));

        days.add(day(4, "Ngày 32: Future with going to",
                "Nói về kế hoạch tương lai với going to.",
                "Dùng am/is/are + going to + V để nói kế hoạch đã định.",
                "going /ˈɡoʊ.ɪŋ/, to /tuː/ – chú ý nối âm 'going to' → 'gonna'.",
                "going to, plan, trip, visit, study, buy, move, next week, next year, this summer",
                "Future with going to", grammarIdOffset + 3,
                "I am going to visit my family next week.|Tuần tới tôi sẽ thăm gia đình.\nShe is going to study abroad next year.|Cô ấy sẽ du học năm sau.",
                "Are you going to travel this summer?|Hè này bạn có định đi du lịch không?\nWe are going to move to a new apartment.|Chúng tôi sẽ chuyển đến căn hộ mới.",
                "I am going to learn driving this year.\nShe is going to start a new job next month.\nWe are going to have a party on Saturday.",
                20, 32, conversationIdOffset + 3));

        days.add(day(5, "Ngày 33: Future with will",
                "Nói về tương lai tự phát, hứa hẹn bằng will.",
                "Dùng will + V cho quyết định tức thì, dự đoán, lời hứa.",
                "will /wɪl/ – rút gọn thành 'll; won't = will not.",
                "will, won't, promise, think, probably, maybe, offer, decide, moment, help",
                "Future with will", grammarIdOffset + 4,
                "I will help you with that.|Tôi sẽ giúp bạn điều đó.\nShe won't be late tomorrow.|Cô ấy sẽ không đến muộn ngày mai.",
                "I think it will rain tonight.|Tôi nghĩ tối nay trời sẽ mưa.\nDon't worry, I'll call you back.|Đừng lo, tôi sẽ gọi lại.",
                "I will do my best.\nShe will probably travel next summer.\nI won't forget your birthday.",
                20, 33, conversationIdOffset + 4));

        days.add(day(6, "Ngày 34: Comparatives",
                "So sánh hai sự vật, người dùng tính từ so sánh hơn.",
                "Thêm -er hoặc dùng more trước tính từ dài.",
                "taller /ˈtɔː.lər/, cheaper /ˈtʃiː.pər/, more expensive /mɔːr ɪkˈspen.sɪv/.",
                "taller, cheaper, faster, smaller, more expensive, better, worse, older, bigger, longer",
                "Comparatives", grammarIdOffset + 5,
                "This phone is cheaper than that one.|Cái điện thoại này rẻ hơn cái kia.\nShe is taller than her sister.|Cô ấy cao hơn chị gái.",
                "The bus is slower but cheaper than the taxi.|Xe buýt chậm hơn nhưng rẻ hơn taxi.\nThis hotel is more comfortable than the last one.|Khách sạn này thoải mái hơn khách sạn trước.",
                "My school is farther than my friend's school.\nHis bag is bigger than mine.\nThe new version is better than the old one.",
                20, 34, conversationIdOffset + 8));

        days.add(day(7, "Ngày 35: Superlatives",
                "Nói về sự vật, người nhất trong nhóm.",
                "Dùng the + -est hoặc the most để nói điều tốt/xấu nhất.",
                "tallest /ˈtɔː.lɪst/, most expensive /moʊst ɪkˈspen.sɪv/.",
                "tallest, cheapest, fastest, best, worst, most popular, most beautiful, biggest, oldest, longest",
                "Superlatives", grammarIdOffset + 6,
                "This is the cheapest phone in the shop.|Đây là điện thoại rẻ nhất trong cửa hàng.\nShe is the tallest student in our class.|Cô ấy là học sinh cao nhất lớp.",
                "What is the most popular dish here?|Món nào nổi tiếng nhất ở đây?\nThis is the best coffee I have ever had.|Đây là cà phê ngon nhất tôi từng uống.",
                "He is the fastest runner in the team.\nThis is the most beautiful place I have visited.\nThat was the worst film I have ever seen.",
                20, 35, conversationIdOffset + 6));

        days.add(day(8, "Ngày 36: Countable/uncountable review + much/many/a lot of",
                "Ôn danh từ đếm được/không đếm được và lượng từ.",
                "Dùng much/many/a lot of đúng cách theo loại danh từ.",
                "much /mʌtʃ/, many /ˈmeni/, a lot of /ə ˈlɒt əv/.",
                "much, many, a lot of, a little, a few, how much, how many, too much, enough, plenty",
                "Much / many / a lot of", grammarIdOffset + 9,
                "How many people came to the party?|Có bao nhiêu người đến dự tiệc?\nI don't have much time today.|Hôm nay tôi không có nhiều thời gian.",
                "There is a lot of traffic on this road.|Đường này kẹt xe lắm.\nShe eats too much sugar.|Cô ấy ăn quá nhiều đường.",
                "I drink a lot of water every day.\nThere are a few good restaurants nearby.\nWe don't have much money left.",
                22, 36, conversationIdOffset + 5));

        days.add(day(9, "Ngày 37: Modal verbs: should / must / have to",
                "Dùng should, must, have to để khuyên bảo và bắt buộc.",
                "Phân biệt mức độ bắt buộc của ba modal này.",
                "should /ʃʊd/, must /mʌst/, have to /ˈhæf tuː/.",
                "should, must, have to, advice, rule, obligation, health, study, rest, doctor",
                "Should / must / have to", grammarIdOffset + 10,
                "You should drink more water.|Bạn nên uống nhiều nước hơn.\nYou must wear a helmet on a motorbike.|Bạn phải đội mũ bảo hiểm khi đi xe máy.",
                "Do I have to bring my passport?|Tôi có phải mang hộ chiếu không?\nYou must not smoke here.|Bạn không được hút thuốc ở đây.",
                "You should see a doctor if you are sick.\nI have to finish this report today.\nStudents must not cheat in exams.",
                22, 37, conversationIdOffset + 6));

        days.add(day(10, "Ngày 38: Giving advice",
                "Đưa ra lời khuyên bằng should, why don't you, how about.",
                "Kết hợp modal verbs với các cách đưa lời khuyên tự nhiên.",
                "why don't you /waɪ doʊnt juː/, how about /haʊ əˈbaʊt/.",
                "advice, suggest, recommend, try, rest, exercise, quit, cut down, why don't you, how about",
                "Giving advice", grammarIdOffset + 10,
                "Why don't you try getting more sleep?|Sao bạn không ngủ nhiều hơn?\nYou should eat more vegetables.|Bạn nên ăn nhiều rau hơn.",
                "How about taking a walk every evening?|Đi bộ mỗi tối thì sao?\nI think you should talk to your teacher.|Tôi nghĩ bạn nên nói chuyện với thầy giáo.",
                "You should stop staying up late.\nWhy don't you join a sports club?\nHow about cutting down on fast food?",
                20, 38, conversationIdOffset + 6));

        days.add(day(11, "Ngày 39: Making plans and invitations",
                "Mời và hỏi về kế hoạch bằng cách tự nhiên.",
                "Dùng would you like to, shall we, let's để mời và đề nghị.",
                "would you like /wʊd juː laɪk/, shall we /ʃæl wiː/.",
                "invite, plan, suggest, meet, dinner, cinema, weekend, join, free, together",
                "Future with will", grammarIdOffset + 4,
                "Would you like to go to the cinema on Saturday?|Thứ Bảy bạn có muốn đi rạp phim không?\nLet's have lunch together tomorrow.|Ngày mai chúng ta ăn trưa cùng nhau nhé.",
                "Shall we meet at six o'clock?|Chúng ta gặp nhau lúc sáu giờ nhé?\nI am afraid I can't make it this weekend.|Rất tiếc tôi không rảnh cuối tuần này.",
                "Would you like to join us for dinner?\nLet's go for a walk in the park.\nShall we plan something for next Friday?",
                20, 39, conversationIdOffset + 3));

        days.add(day(12, "Ngày 40: Shopping and problem solving",
                "Giao tiếp khi mua sắm và giải quyết vấn đề.",
                "Tự tin mua sắm, hỏi giá, đổi hàng, khiếu nại.",
                "receipt /rɪˈsiːt/, exchange /ɪksˈtʃeɪndʒ/, refund /ˈriː.fʌnd/.",
                "receipt, exchange, refund, size, wrong, broken, manager, customer service, discount, total",
                "Should / must / have to", grammarIdOffset + 10,
                "This shirt is the wrong size. Can I exchange it?|Cái áo này sai size. Tôi đổi được không?\nI would like a refund please.|Tôi muốn hoàn tiền.",
                "The total is two hundred thousand dong.|Tổng cộng là hai trăm nghìn đồng.\nCould I speak to the manager please?|Cho tôi nói chuyện với quản lý được không?",
                "Excuse me, this product is damaged.\nCan I get a discount if I buy two?\nI need to return this item, I have the receipt.",
                22, 40, conversationIdOffset + 5));

        days.add(day(13, "Ngày 41: Travel problems and asking for help",
                "Xử lý tình huống có vấn đề khi đi du lịch.",
                "Hỏi đường, báo mất đồ, xử lý chuyến bay muộn.",
                "delayed /dɪˈleɪd/, cancelled /ˈkæn.səld/, lost /lɒst/.",
                "delayed, cancelled, lost, stolen, missed, luggage, report, excuse me, could you help, police",
                "Past simple irregular verbs", grammarIdOffset + 2,
                "My flight was delayed by two hours.|Chuyến bay của tôi bị trễ hai tiếng.\nI think I lost my passport.|Tôi nghĩ tôi mất hộ chiếu rồi.",
                "Could you help me find the information desk?|Bạn có thể giúp tôi tìm quầy thông tin không?\nI need to report a missing bag.|Tôi cần báo cáo thất lạc hành lý.",
                "My bag was stolen at the airport.\nCould you tell me where the nearest police station is?\nI missed my connecting flight.",
                22, 41, conversationIdOffset + 13));

        days.add(day(14, "Ngày 42: A2 final review + speaking test",
                "Tổng ôn khóa A2 và kiểm tra nói cuối khóa.",
                "Ôn tập toàn bộ ngữ pháp, từ vựng và hội thoại A2.",
                "Ôn phát âm -ed, so sánh và nối âm trong câu dài.",
                "went, should, going to, will, better, most, lost, advice, plan, travel",
                "Past simple irregular verbs", grammarIdOffset + 2,
                "Last weekend I went to a new restaurant with my friends.|Cuối tuần rồi tôi đi nhà hàng mới với bạn bè.\nI am going to learn to drive next year.|Năm sau tôi sẽ học lái xe.",
                "You should rest more if you feel tired.|Bạn nên nghỉ ngơi nhiều hơn nếu thấy mệt.\nThis city is bigger and more exciting than my hometown.|Thành phố này lớn hơn và sôi động hơn quê tôi.",
                "Hello, my name is Hung and I am from Vietnam.\nLast year I started learning English seriously.\nI am going to travel to Japan next summer.",
                25, 42, conversationIdOffset + 14));

        return days;
    }

    private static LearningDay day(int dayIndex, String title, String objectiveVi, String summaryVi,
                                   String pronunciation, String vocab, String grammar, int grammarId,
                                   String conversation, String listening, String speaking,
                                   int minutes, int order, int convId) {
        return new LearningDay("A2", dayIndex, title, objectiveVi, summaryVi, pronunciation,
                vocab, grammar, grammarId, conversation, listening, speaking,
                minutes, order, convId);
    }
}
