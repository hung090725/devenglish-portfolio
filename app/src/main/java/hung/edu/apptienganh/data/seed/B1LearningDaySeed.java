package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.LearningDay;

public final class B1LearningDaySeed {

    private B1LearningDaySeed() {}

    public static List<LearningDay> buildLearningDays(int grammarIdOffset, int conversationIdOffset) {
        List<LearningDay> days = new ArrayList<>();

        days.add(day(1, "Ngày 43: Review A2 + telling longer stories",
                "Ôn tập A2 và kể chuyện dài hơn bằng thì quá khứ.",
                "Kết hợp past simple và mô tả chi tiết để kể chuyện tự nhiên.",
                "Linking words: first /fɜːst/, then /ðen/, after that /ˈɑːftər ðæt/, finally /ˈfaɪ.nəl.i/.",
                "experience, journey, adventure, exciting, boring, surprised, disappointed, actually, finally, eventually",
                "Past simple review + storytelling", grammarIdOffset + 1,
                "Last summer I went on a road trip with my friends.|Mùa hè rồi tôi đi phượt cùng bạn bè.\nFirst we drove to Da Lat, then we visited the flower gardens.|Đầu tiên chúng tôi lái xe lên Đà Lạt, rồi thăm vườn hoa.",
                "It was one of the best experiences of my life.|Đó là một trong những trải nghiệm tuyệt nhất cuộc đời tôi.\nWe had some problems with the car, but eventually we got there.|Chúng tôi gặp sự cố xe hơi nhưng cuối cùng cũng đến nơi.",
                "I went on a long trip last year and it was amazing.\nFirst I planned everything carefully, then I packed my bag.\nFinally, I arrived and felt so happy.",
                22, 43, conversationIdOffset + 1));

        days.add(day(2, "Ngày 44: Present perfect basic",
                "Kết nối quá khứ với hiện tại bằng thì hiện tại hoàn thành.",
                "Dùng have/has + V3 để nói về hành động đã xảy ra liên quan đến hiện tại.",
                "have /hæv/, has /hæz/; been /biːn/, gone /ɡɒn/, done /dʌn/, seen /siːn/.",
                "have been, has visited, have eaten, have tried, have finished, ever, never, already, just, yet",
                "Present perfect basic", grammarIdOffset + 2,
                "I have visited Ha Noi three times.|Tôi đã thăm Hà Nội ba lần.\nShe has never eaten sushi before.|Cô ấy chưa bao giờ ăn sushi.",
                "Have you ever been to a foreign country?|Bạn đã từng đi nước ngoài chưa?\nI have just finished my report.|Tôi vừa hoàn thành báo cáo.",
                "I have learned a lot of English this year.\nHave you ever tried cooking a new dish?\nShe has already called me twice.",
                22, 44, conversationIdOffset + 2));

        days.add(day(3, "Ngày 45: Present perfect vs past simple",
                "Phân biệt khi nào dùng present perfect, khi nào dùng past simple.",
                "Past simple = thời gian xác định trong quá khứ; Present perfect = không rõ thời gian.",
                "already /ɔːlˈrɛdi/, yet /jet/, just /dʒʌst/; yesterday, last week → past simple.",
                "already, yet, just, ever, never, yesterday, last year, ago, recently, how long",
                "Present perfect vs past simple", grammarIdOffset + 3,
                "I visited Paris last year. (specific) vs I have visited Paris. (experience)|Tôi thăm Paris năm ngoái. vs Tôi từng thăm Paris.\nHave you eaten yet? vs Did you eat dinner? (last night)|Bạn đã ăn chưa? vs Bạn ăn tối chưa? (tối qua)",
                "She has lived here for five years.|Cô ấy sống ở đây được năm năm rồi.\nHe moved here in 2019.|Anh ấy chuyển đến đây vào năm 2019.",
                "I have studied English for two years.\nI started studying English two years ago.\nHave you finished the project yet?",
                22, 45, conversationIdOffset + 3));

        days.add(day(4, "Ngày 46: Experiences and life events",
                "Chia sẻ trải nghiệm và sự kiện cuộc sống bằng present perfect.",
                "Dùng ever/never để hỏi và trả lời về trải nghiệm cuộc đời.",
                "experience /ɪkˈspɪə.ri.əns/, achievement /əˈtʃiːv.mənt/, memorable /ˈmem.ər.ə.bəl/.",
                "achievement, experience, memorable, proud, nervous, exciting, once, twice, several times, the first time",
                "Present perfect with ever/never", grammarIdOffset + 2,
                "Have you ever won a competition?|Bạn đã từng đoạt giải thi đua chưa?\nI have never ridden a horse before.|Tôi chưa bao giờ cưỡi ngựa.",
                "It was the first time I had spoken to a foreigner.|Đó là lần đầu tiên tôi nói chuyện với người nước ngoài.\nI felt so proud when I passed the exam.|Tôi rất tự hào khi vượt qua kỳ thi.",
                "Have you ever done something you were really proud of?\nI have never sung in public but I want to try.\nThat was the most memorable day of my life.",
                22, 46, conversationIdOffset + 4));

        days.add(day(5, "Ngày 47: Future plans and predictions",
                "Nói về kế hoạch và dự đoán tương lai ở cấp B1.",
                "Kết hợp will, going to và present continuous để nói về tương lai.",
                "definitely /ˈdef.ɪ.nɪt.li/, probably /ˈprɒb.ə.bli/, might /maɪt/.",
                "definitely, probably, might, expect, predict, plan to, intend to, hope to, suppose, by the time",
                "Future plans and predictions", grammarIdOffset + 4,
                "I am definitely going to apply for a new job next month.|Tháng tới tôi chắc chắn sẽ xin việc mới.\nShe will probably move to another city next year.|Cô ấy có lẽ sẽ chuyển thành phố năm sau.",
                "I think technology will change our lives dramatically.|Tôi nghĩ công nghệ sẽ thay đổi cuộc sống mạnh mẽ.\nHe might travel to Japan if he gets enough money.|Anh ấy có thể đi Nhật nếu có đủ tiền.",
                "In five years, I hope to have my own business.\nI think electric cars will become very common.\nMight you consider working abroad someday?",
                22, 47, conversationIdOffset + 5));

        days.add(day(6, "Ngày 48: First conditional",
                "Nói về điều kiện có thể xảy ra trong tương lai.",
                "Cấu trúc: If + present simple, will + V.",
                "if /ɪf/, unless /ʌnˈles/, provided /prəˈvaɪ.dɪd/; result clause với will.",
                "if, unless, provided, as long as, will, result, consequence, condition, possible, happen",
                "First conditional", grammarIdOffset + 5,
                "If it rains tomorrow, I will stay at home.|Nếu ngày mai trời mưa, tôi sẽ ở nhà.\nIf you study hard, you will pass the exam.|Nếu bạn học chăm, bạn sẽ đậu kỳ thi.",
                "Unless you leave now, you will miss the bus.|Nếu bạn không đi ngay, bạn sẽ lỡ xe buýt.\nIf I get the job, I will move to the city.|Nếu tôi được nhận việc, tôi sẽ chuyển lên thành phố.",
                "If I save enough money, I will buy a new phone.\nUnless we hurry, we will be late.\nIf the weather is nice, we will go to the beach.",
                22, 48, conversationIdOffset + 6));

        days.add(day(7, "Ngày 49: Suggestions and recommendations",
                "Đưa ra gợi ý và lời khuyên ở mức lịch sự B1.",
                "Dùng how about, what about, you could, I would suggest để gợi ý.",
                "suggest /səˈdʒest/, recommend /ˌrek.əˈmend/, consider /kənˈsɪd.ər/.",
                "suggest, recommend, consider, option, alternative, worth, might want to, you could, how about, what if",
                "Suggestions and recommendations", grammarIdOffset + 6,
                "I would suggest taking the train instead of driving.|Tôi gợi ý đi tàu thay vì lái xe.\nYou might want to consider applying earlier.|Bạn nên xem xét nộp đơn sớm hơn.",
                "Why not try a different approach?|Sao không thử cách tiếp cận khác?\nHave you considered asking for help?|Bạn đã nghĩ đến việc nhờ giúp chưa?",
                "I recommend reading that book – it is really helpful.\nYou could try speaking with native speakers online.\nWhat about joining an English club?",
                20, 49, conversationIdOffset + 7));

        days.add(day(8, "Ngày 50: Agreeing and disagreeing politely",
                "Đồng ý và không đồng ý một cách lịch sự bằng tiếng Anh.",
                "Sử dụng các cụm từ để đồng ý một phần, phản đối nhẹ nhàng, và giải thích.",
                "absolutely /ˈæb.sə.luːt.li/, actually /ˈæk.tʃu.ə.li/, rather /ˈrɑː.ðər/.",
                "agree, disagree, absolutely, exactly, point taken, fair enough, actually, however, on the other hand, but",
                "Agreeing and disagreeing", grammarIdOffset + 7,
                "I completely agree with you on that point.|Tôi hoàn toàn đồng ý với bạn về điểm đó.\nI see your point, but I think there is another way.|Tôi hiểu ý bạn, nhưng tôi nghĩ có cách khác.",
                "That is a fair point, however I am not so sure.|Điểm đó hợp lý, tuy nhiên tôi không chắc lắm.\nActually, I have a slightly different view on this.|Thật ra tôi có quan điểm hơi khác về vấn đề này.",
                "I agree that exercise is important, but not everyone has time.\nYou make a good point, although I would add that cost matters too.\nI respectfully disagree – I think we should try a new method.",
                20, 50, conversationIdOffset + 8));

        days.add(day(9, "Ngày 51: Giving opinions with reasons",
                "Đưa ra ý kiến và giải thích lý do một cách rõ ràng.",
                "Cấu trúc: In my opinion + because/since/therefore để diễn đạt quan điểm.",
                "opinion /əˈpɪn.jən/, believe /bɪˈliːv/, therefore /ˈðeə.fɔːr/.",
                "in my opinion, I believe, because, since, therefore, as a result, for instance, for example, personally, I think",
                "Giving opinions", grammarIdOffset + 7,
                "In my opinion, learning English is very important because it opens many doors.|Theo ý kiến tôi, học tiếng Anh rất quan trọng vì nó mở ra nhiều cơ hội.\nPersonally, I think public transport should be improved.|Cá nhân tôi nghĩ phương tiện công cộng cần được cải thiện.",
                "I believe that technology has made our lives easier, for example we can shop online.|Tôi tin rằng công nghệ đã giúp cuộc sống dễ dàng hơn, ví dụ chúng ta có thể mua sắm online.\nSince remote work is becoming common, cities might become less crowded.|Vì làm việc từ xa đang phổ biến, các thành phố có thể bớt đông đúc hơn.",
                "In my opinion, reading books is better than watching TV.\nI think social media has both benefits and drawbacks.\nPersonally, I believe that eating healthy food is more important than dieting.",
                22, 51, conversationIdOffset + 9));

        days.add(day(10, "Ngày 52: Describing problems in detail",
                "Mô tả vấn đề và tình huống khó khăn chi tiết hơn.",
                "Học cách trình bày vấn đề, nguyên nhân, hậu quả và giải pháp.",
                "issue /ˈɪʃ.uː/, challenge /ˈtʃæl.ɪndʒ/, solution /səˈluː.ʃən/.",
                "issue, challenge, solution, deal with, handle, overcome, cause, affect, serious, urgent",
                "Describing problems", grammarIdOffset + 8,
                "The main problem is that there is not enough time.|Vấn đề chính là không có đủ thời gian.\nThis issue affects many people in our community.|Vấn đề này ảnh hưởng đến nhiều người trong cộng đồng.",
                "We need to find a solution as soon as possible.|Chúng ta cần tìm giải pháp càng sớm càng tốt.\nThe root cause of this problem is lack of communication.|Nguyên nhân gốc rễ của vấn đề này là thiếu giao tiếp.",
                "The main challenge I face is managing my time effectively.\nThis problem is serious and needs to be dealt with urgently.\nI think the best solution is to talk to someone you trust.",
                22, 52, conversationIdOffset + 10));

        days.add(day(11, "Ngày 53: Work and study discussions",
                "Thảo luận về công việc và học tập bằng tiếng Anh.",
                "Học cách nói về môi trường làm việc, thách thức học tập và mục tiêu.",
                "colleague /ˈkɒl.iːɡ/, deadline /ˈded.laɪn/, presentation /ˌprez.ənˈteɪ.ʃən/.",
                "colleague, deadline, presentation, responsibility, workload, assignment, stress, balance, productive, career",
                "Work and study discussions", grammarIdOffset + 9,
                "I have a presentation to prepare by Friday and I am quite stressed.|Tôi phải chuẩn bị thuyết trình vào thứ Sáu và tôi khá căng thẳng.\nMy colleague helped me a lot with the project.|Đồng nghiệp của tôi giúp tôi rất nhiều trong dự án.",
                "The hardest part of studying is staying focused.|Phần khó nhất của việc học là duy trì sự tập trung.\nI try to balance work and personal life as much as possible.|Tôi cố gắng cân bằng công việc và cuộc sống cá nhân nhiều nhất có thể.",
                "I am currently working on a very challenging project.\nMy biggest challenge at work is managing multiple deadlines.\nI find it helps to make a to-do list every morning.",
                22, 53, conversationIdOffset + 11));

        days.add(day(12, "Ngày 54: Travel stories and complaints",
                "Kể chuyện du lịch và cách phàn nàn lịch sự.",
                "Dùng past perfect và past simple để kể câu chuyện du lịch phức tạp.",
                "complaint /kəmˈpleɪnt/, unfortunately /ʌnˈfɔː.tʃə.nɪt.li/, eventually /ɪˈven.tʃu.ə.li/.",
                "unfortunately, eventually, complaint, accommodation, flight, delayed, rebook, customer service, refund, disappointing",
                "Travel stories and complaints", grammarIdOffset + 10,
                "Unfortunately, our flight was delayed for four hours.|Thật không may, chuyến bay của chúng tôi bị hoãn bốn tiếng.\nWhen we finally arrived, the hotel said they had no record of our booking.|Khi chúng tôi cuối cùng đến nơi, khách sạn nói không có đặt phòng của chúng tôi.",
                "I would like to make a complaint about the service.|Tôi muốn phàn nàn về dịch vụ.\nThe trip was disappointing overall, but the scenery was beautiful.|Chuyến đi nhìn chung thất vọng nhưng cảnh đẹp.",
                "The holiday started badly when we missed our connecting flight.\nWe had to wait at the airport for six hours.\nEventually, the airline gave us hotel vouchers and we felt a little better.",
                22, 54, conversationIdOffset + 12));

        days.add(day(13, "Ngày 55: Short presentations",
                "Học cách trình bày ngắn trước đám đông.",
                "Cấu trúc: giới thiệu, nội dung chính, kết luận với linking words.",
                "introduction /ˌɪn.trəˈdʌk.ʃən/, conclusion /kənˈkluː.ʒən/, to sum up /tuː sʌm ʌp/.",
                "introduce, present, outline, firstly, secondly, furthermore, in conclusion, to sum up, audience, main point",
                "Short presentations", grammarIdOffset + 11,
                "Good morning everyone. Today I am going to talk about the importance of learning English.|Chào buổi sáng mọi người. Hôm nay tôi sẽ nói về tầm quan trọng của việc học tiếng Anh.\nFirstly, I would like to explain why English is so important in today's world.|Đầu tiên, tôi muốn giải thích tại sao tiếng Anh lại quan trọng trong thế giới ngày nay.",
                "Furthermore, speaking English opens up more job opportunities.|Hơn nữa, nói tiếng Anh mở ra nhiều cơ hội việc làm hơn.\nIn conclusion, I believe everyone should invest time in learning English.|Tóm lại, tôi tin mọi người nên đầu tư thời gian học tiếng Anh.",
                "Hello, my name is Minh and today I will talk about my hobby.\nMy main point is that learning a new skill helps you grow as a person.\nTo sum up, I hope you consider trying something new this year.",
                22, 55, conversationIdOffset + 13));

        days.add(day(14, "Ngày 56: B1 final review + speaking test",
                "Tổng ôn khóa B1 và kiểm tra nói cuối khóa.",
                "Ôn tập toàn bộ ngữ pháp, từ vựng, hội thoại B1.",
                "Ôn phát âm nâng cao: schwa /ə/ trong từ nhiều âm tiết.",
                "experience, achievement, probably, unless, suggest, agree, opinion, presentation, challenge, journey",
                "B1 final review", grammarIdOffset + 1,
                "I have learned a lot this year and I feel more confident speaking English.|Tôi đã học được nhiều thứ năm nay và tôi cảm thấy tự tin hơn khi nói tiếng Anh.\nIf I keep practising, my English will definitely improve.|Nếu tôi tiếp tục luyện tập, tiếng Anh của tôi chắc chắn sẽ tiến bộ.",
                "In my opinion, consistency is the most important thing in learning.|Theo tôi, sự kiên trì là điều quan trọng nhất trong việc học.\nI would suggest practising English a little bit every day.|Tôi gợi ý luyện tiếng Anh một chút mỗi ngày.",
                "Hello, my name is Linh and I have been studying English for two years.\nI used to be very shy, but now I am more confident.\nIn the future, I plan to use English in my career.",
                25, 56, conversationIdOffset + 14));

        return days;
    }

    private static LearningDay day(int dayIndex, String title, String objectiveVi, String summaryVi,
                                   String pronunciation, String vocab, String grammar, int grammarId,
                                   String conversation, String listening, String speaking,
                                   int minutes, int order, int convId) {
        return new LearningDay("B1", dayIndex, title, objectiveVi, summaryVi, pronunciation,
                vocab, grammar, grammarId, conversation, listening, speaking,
                minutes, order, convId);
    }
}
