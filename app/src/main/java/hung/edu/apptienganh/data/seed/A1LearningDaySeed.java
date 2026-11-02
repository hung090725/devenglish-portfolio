package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.LearningDay;

public final class A1LearningDaySeed {

    private A1LearningDaySeed() {}

    public static List<LearningDay> buildLearningDays(int grammarIdOffset, int conversationIdOffset) {
        List<LearningDay> days = new ArrayList<>();
        days.add(day(1, "Ngày 15: Review A0 + daily life sentences",
                "Ôn tập A0 và học câu giao tiếp đời sống hàng ngày.",
                "Củng cố chào hỏi, to be, hiện tại đơn và mở rộng câu nói thực tế.",
                "Ôn /θ/, /ð/ và nhấn âm trong routine, breakfast, usually.",
                "wake up, breakfast, usually, sometimes, after school, homework, tired, early, late, daily",
                "Present simple review", grammarIdOffset + 1,
                "What time do you wake up?|Mấy giờ bạn thức dậy?\nI usually have breakfast at seven.|Tôi thường ăn sáng lúc bảy giờ.",
                "I do homework after school.|Tôi làm bài sau giờ học.\nShe sometimes feels tired.|Cô ấy đôi khi thấy mệt.",
                "I usually wake up at six.\nI have breakfast and go to work.\nI sometimes read in the evening.",
                22, 15, conversationIdOffset + 1));
        days.add(day(2, "Ngày 16: Present simple full review",
                "Hệ thống lại hiện tại đơn với mọi chủ ngữ.",
                "Chia động từ đúng với I/you/we/they và he/she/it.",
                "works /wɜːrks/, watches /ˈwɒtʃ.ɪz/, studies /ˈstʌd.iz/.",
                "work, live, study, play, watch, every day, always, never, often, usually",
                "Present simple review", grammarIdOffset + 1,
                "He works in an office.|Anh ấy làm việc ở văn phòng.\nThey never eat fast food.|Họ không bao giờ ăn đồ ăn nhanh.",
                "She studies English every morning.|Cô ấy học tiếng Anh mỗi sáng.\nDo you watch TV at night?|Bạn có xem TV buổi tối không?",
                "I live in Hanoi.\nShe works in a hospital.\nWe study English every day.",
                20, 16, conversationIdOffset + 1));
        days.add(day(3, "Ngày 17: Like / want / need",
                "Diễn tả sở thích và nhu cầu bằng like, want, need.",
                "Dùng like/want/need + danh từ hoặc to + V.",
                "like /laɪk/, want /wɒnt/, need /niːd/ – âm /æ/ trong want.",
                "like, want, need, love, prefer, coffee, travel, help, new, bag",
                "Like / want / need + noun / to V", grammarIdOffset + 2,
                "I like coffee but I need water.|Tôi thích cà phê nhưng cần nước.\nShe wants to travel abroad.|Cô ấy muốn đi du lịch nước ngoài.",
                "Do you need any help?|Bạn cần giúp gì không?\nWe need a new bag.|Chúng tôi cần một cái túi mới.",
                "I like reading books.\nI want to learn English.\nI need some rest.",
                20, 17, conversationIdOffset + 6));
        days.add(day(4, "Ngày 18: There is / There are",
                "Nói về sự vật có ở đâu đó.",
                "Phân biệt there is và there are.",
                "there is /ðeər ɪz/, there are /ðeər ɑːr/.",
                "there is, there are, room, table, chair, fridge, park, bank, near, some",
                "There is / There are", grammarIdOffset + 3,
                "There is a bank near my house.|Có ngân hàng gần nhà tôi.\nThere are two parks in this town.|Có hai công viên trong thị trấn.",
                "Is there a clinic near here?|Có phòng khám gần đây không?\nThere are many shops on this street.|Có nhiều cửa hàng trên phố này.",
                "There is a book on the table.\nThere are three chairs in the room.\nThere is no milk in the fridge.",
                20, 18, conversationIdOffset + 2));
        days.add(day(5, "Ngày 19: Countable / uncountable nouns",
                "Phân biệt danh từ đếm được và không đếm được.",
                "Dùng many/much, a/an và some đúng cách.",
                "countable /ˈkaʊn.tə.bəl/, uncountable /ʌnˈkaʊn.tə.bəl/.",
                "apple, bottle, rice, water, money, many, much, some, few, little",
                "Countable nouns", grammarIdOffset + 4,
                "I bought two apples.|Tôi mua hai quả táo.\nThere is not much sugar.|Không còn nhiều đường.",
                "How many bottles do you need?|Bạn cần bao nhiêu chai?\nWe have some rice at home.|Nhà có một ít gạo.",
                "I have many books.\nThere is a little milk.\nHow much water do you drink?",
                20, 19, conversationIdOffset + 5));
        days.add(day(6, "Ngày 20: Some / any",
                "Dùng some và any trong câu khẳng định, phủ định, hỏi.",
                "Some trong câu khẳng định; any trong hỏi/phủ định.",
                "some /sʌm/, any /ˈeni/ – âm cuối /m/ trong some.",
                "some, any, questions, milk, friends, apples, money, fridge, have, need",
                "Some / any", grammarIdOffset + 6,
                "I have some friends in London.|Tôi có vài người bạn ở London.\nDo you have any questions?|Bạn có câu hỏi nào không?",
                "There isn't any milk.|Không còn sữa.\nCan I have some water?|Cho tôi xin nước được không?",
                "I need some help.\nAre there any apples?\nThere aren't any eggs.",
                20, 20, conversationIdOffset + 3));
        days.add(day(7, "Ngày 21: Can / cannot",
                "Nói về khả năng và xin phép bằng can.",
                "Can + V nguyên mẫn; can't = cannot.",
                "can /kæn/, can't /kɑːnt/ – âm /æ/ ngắn.",
                "can, can't, swim, drive, speak, help, pay, card, borrow, open",
                "Can / cannot", grammarIdOffset + 7,
                "Can you help me?|Bạn giúp tôi được không?\nI can't swim very well.|Tôi bơi chưa giỏi lắm.",
                "Can I pay by card?|Tôi trả bằng thẻ được không?\nShe can speak three languages.|Cô ấy nói được ba thứ tiếng.",
                "I can speak English.\nCan you open the door?\nWe can't come today.",
                20, 21, conversationIdOffset + 12));
        days.add(day(8, "Ngày 22: Prepositions of place",
                "Dùng in, on, at, under, next to chỉ vị trí.",
                "Mô tả đồ vật và người ở đâu.",
                "in /ɪn/, on /ɒn/, at /æt/, under /ˈʌn.dər/, next to /nekst tuː/.",
                "in, on, at, under, behind, next to, between, table, kitchen, station",
                "Prepositions of place", grammarIdOffset + 8,
                "The keys are on the table.|Chìa khóa ở trên bàn.\nShe is in the kitchen.|Cô ấy ở trong bếp.",
                "The bus stop is next to the bank.|Trạm xe buýt ở cạnh ngân hàng.\nThe cat is under the chair.|Con mèo ở dưới ghế.",
                "The book is on the desk.\nI am at the bus stop.\nThe shop is between the bank and the cafe.",
                20, 22, conversationIdOffset + 2));
        days.add(day(9, "Ngày 23: Prepositions of time",
                "Dùng in, on, at với thời gian.",
                "Nói về giờ, ngày, tháng và mùa.",
                "at seven /æt ˈsev.ən/, on Monday /ɒn ˈmʌn.deɪ/, in May /ɪn meɪ/.",
                "at, on, in, morning, afternoon, evening, Monday, May, o'clock, weekend",
                "Prepositions of time", grammarIdOffset + 9,
                "The class starts at eight.|Lớp bắt đầu lúc tám giờ.\nWe have a test on Friday.|Chúng ta có bài kiểm tra thứ Sáu.",
                "My birthday is in May.|Sinh nhật tôi vào tháng Năm.\nI study in the morning.|Tôi học buổi sáng.",
                "I wake up at six o'clock.\nWe meet on Sunday.\nIt is cold in winter.",
                20, 23, conversationIdOffset + 1));
        days.add(day(10, "Ngày 24: Adverbs of frequency",
                "Dùng always, usually, often, sometimes, never.",
                "Trạng từ tần suất đứng đúng vị trí trong câu.",
                "always /ˈɔːl.weɪz/, usually /ˈjuːʒu.ə.li/, never /ˈnev.ər/.",
                "always, usually, often, sometimes, never, every day, twice, week, practice, gym",
                "Adverbs of frequency", grammarIdOffset + 10,
                "I always brush my teeth.|Tôi luôn đánh răng.\nShe sometimes eats out.|Cô ấy đôi khi ăn ngoài.",
                "They never watch TV late.|Họ không bao giờ xem TV khuya.\nHe often goes to the gym.|Anh ấy hay đi gym.",
                "I usually get up early.\nWe sometimes play football.\nI never drink soda.",
                20, 24, conversationIdOffset + 6));
        days.add(day(11, "Ngày 25: Present continuous basic",
                "Diễn tả hành động đang diễn ra.",
                "Cấu trúc am/is/are + V-ing.",
                "studying /ˈstʌd.i.ɪŋ/, cooking /ˈkʊk.ɪŋ/, waiting /ˈweɪ.tɪŋ/.",
                "now, at the moment, right now, studying, cooking, waiting, raining, working, look, listen",
                "Present continuous", grammarIdOffset + 11,
                "I am studying now.|Tôi đang học bây giờ.\nShe is cooking dinner.|Cô ấy đang nấu bữa tối.",
                "Look! It is raining.|Nhìn kìa! Trời đang mưa.\nThey are waiting for the bus.|Họ đang đợi xe buýt.",
                "I am reading a book now.\nHe is working at the moment.\nWe are learning English.",
                20, 25, conversationIdOffset + 7));
        days.add(day(12, "Ngày 26: Past simple with be: was/were",
                "Nói về quá khứ với was/were.",
                "Phân biệt was và were theo chủ ngữ.",
                "was /wɒz/, were /wɜːr/, yesterday /ˈjes.tər.deɪ/.",
                "was, were, yesterday, last week, ago, tired, happy, at home, airport, Sunday",
                "Past simple: was/were", grammarIdOffset + 12,
                "I was at home yesterday.|Tôi ở nhà hôm qua.\nThey were happy last night.|Họ vui đêm qua.",
                "She was not tired.|Cô ấy không mệt.\nWe were at the airport last Sunday.|Chúng tôi ở sân bay Chủ nhật trước.",
                "I was at school yesterday.\nYou were late last Monday.\nThey were in Da Nang last summer.",
                20, 26, conversationIdOffset + 10));
        days.add(day(13, "Ngày 27: Basic travel conversation",
                "Giao tiếp khi đi du lịch: sân bay, khách sạn, hỏi đường.",
                "Kết hợp từ vựng travel và các cấu trúc A1.",
                "passport /ˈpæs.pɔːrt/, suitcase /ˈsuːt.keɪs/, ticket /ˈtɪk.ɪt/.",
                "passport, ticket, gate, suitcase, hotel, room, check-in, flight, travel, abroad",
                "Can / cannot", grammarIdOffset + 7,
                "May I see your passport?|Cho tôi xem hộ chiếu.\nI would like to book a room.|Tôi muốn đặt phòng.",
                "Where is the gate?|Cổng ở đâu?\nMy flight is at ten a.m.|Chuyến bay lúc mười giờ sáng.",
                "I am flying to Singapore.\nI have one suitcase.\nI need a double room with breakfast.",
                22, 27, conversationIdOffset + 10));
        days.add(day(14, "Ngày 28: A1 final review + speaking test",
                "Tổng ôn khóa A1 và kiểm tra nói cuối khóa.",
                "Ôn ngữ pháp, từ vựng và hội thoại 14 ngày A1.",
                "Ôn phát âm -ed, -s và nối âm trong câu dài.",
                "there is, can, usually, because, was, were, need, some, next to, travel",
                "Present simple review", grammarIdOffset + 1,
                "I usually wake up early because I need to study.|Tôi thường dậy sớm vì cần học.\nThere is a clinic near the school.|Có phòng khám gần trường.",
                "Can you help me find my wallet?|Bạn giúp tôi tìm ví được không?\nWe were at the airport yesterday.|Hôm qua chúng tôi ở sân bay.",
                "Hello, I am Hung. I live in Vietnam.\nI can speak English a little.\nI want to travel and learn more.",
                25, 28, conversationIdOffset + 12));
        return days;
    }

    private static LearningDay day(int dayIndex, String title, String objectiveVi, String summaryVi,
                                   String pronunciation, String vocab, String grammar, int grammarId,
                                   String conversation, String listening, String speaking,
                                   int minutes, int order, int convId) {
        return new LearningDay("A1", dayIndex, title, objectiveVi, summaryVi, pronunciation,
                vocab, grammar, grammarId, conversation, listening, speaking,
                minutes, order, convId);
    }
}
