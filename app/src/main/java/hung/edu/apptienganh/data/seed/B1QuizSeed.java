package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.QuizQuestion;

public final class B1QuizSeed {

    private B1QuizSeed() {}

    /** Build 5 quiz questions for a given B1 day (dayIndex 1-14, learningDayId from DB). */
    public static List<QuizQuestion> buildDayQuizzes(int dayIndex, int learningDayId) {
        List<QuizQuestion> q = new ArrayList<>();
        switch (dayIndex) {
            case 1: seedDay43(q, learningDayId); break;
            case 2: seedDay44(q, learningDayId); break;
            case 3: seedDay45(q, learningDayId); break;
            case 4: seedDay46(q, learningDayId); break;
            case 5: seedDay47(q, learningDayId); break;
            case 6: seedDay48(q, learningDayId); break;
            case 7: seedDay49(q, learningDayId); break;
            case 8: seedDay50(q, learningDayId); break;
            case 9: seedDay51(q, learningDayId); break;
            case 10: seedDay52(q, learningDayId); break;
            case 11: seedDay53(q, learningDayId); break;
            case 12: seedDay54(q, learningDayId); break;
            case 13: seedDay55(q, learningDayId); break;
            case 14: seedDay56(q, learningDayId); break;
        }
        return q;
    }

    // Day 43 – Review A2 + Storytelling
    private static void seedDay43(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Eventually' nghĩa là?", "Ngay lập tức", "Cuối cùng", "Hiếm khi", "Đột ngột", "B", "Eventually = cuối cùng, sau một quá trình dài.");
        add(q, id, "VOCABULARY", "'Memorable' nghĩa là?", "Buồn tẻ", "Đáng nhớ", "Bình thường", "Ngắn ngủi", "B", "Memorable = đáng nhớ vì có ý nghĩa hoặc đặc biệt.");
        add(q, id, "GRAMMAR", "Linking words: First I walked to school. ___ I took the bus.", "However", "Then", "Although", "Unless", "B", "Then = sau đó; dùng để kể trình tự sự việc.");
        add(q, id, "GRAMMAR", "Cách kể chuyện quá khứ: Last summer, we ___ (go) on a road trip.", "go", "went", "have gone", "going", "B", "Quá khứ đơn: went. Dấu hiệu 'last summer'.");
        add(q, id, "CONVERSATION", "Kể chuyện trình tự đúng:", "First I ate, after that I slept, finally I worked.", "First I ate, then I slept, after that I worked.", "After that I worked, then I ate, first I slept.", "I worked first, after that finally I slept, then I ate.", "B", "Trình tự đúng: First → then → after that → finally.");
    }

    // Day 44 – Present Perfect Basic
    private static void seedDay44(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Achievement' nghĩa là?", "Thất bại", "Thành tựu", "Thử thách", "Kế hoạch", "B", "Achievement = thành tựu, điều đạt được sau nỗ lực.");
        add(q, id, "VOCABULARY", "'Experience' trong 'life experience' nghĩa là?", "Thí nghiệm", "Kinh nghiệm / trải nghiệm", "Kỹ năng", "Kiến thức lý thuyết", "B", "Experience = kinh nghiệm sống, trải nghiệm thực tế.");
        add(q, id, "GRAMMAR", "I ___ never ___ sushi before. Chọn đúng:", "had / eaten", "have / eaten", "have / eat", "am / eating", "B", "Present perfect: have + V3. 'Eaten' là V3 của 'eat'.");
        add(q, id, "GRAMMAR", "Câu nào là present perfect đúng?", "I have visit Paris last year.", "I visited Paris last year.", "I have visited Paris.", "I am visiting Paris.", "C", "Present perfect không đi kèm thời gian xác định 'last year'.");
        add(q, id, "CONVERSATION", "Hỏi về trải nghiệm đúng:", "Did you ever been to Japan?", "Have you ever been to Japan?", "Have you ever gone to Japan yesterday?", "Do you ever been to Japan?", "B", "Present perfect: Have you ever + V3?");
    }

    // Day 45 – Present Perfect vs Past Simple
    private static void seedDay45(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Recently' nghĩa là?", "Thường xuyên", "Gần đây", "Ngay lúc đó", "Trước đây lâu", "B", "Recently = mới đây, gần đây → thường đi với present perfect.");
        add(q, id, "VOCABULARY", "'Specific' nghĩa là?", "Mơ hồ", "Cụ thể / xác định", "Tổng quát", "Ngẫu nhiên", "B", "Specific = cụ thể, rõ ràng. Dấu hiệu của past simple là thời gian specific.");
        add(q, id, "GRAMMAR", "Chọn thì đúng: I ___ that film last night. (thời gian xác định)", "have watched", "watched", "have been watching", "am watching", "B", "Past simple dùng với 'last night' – thời gian cụ thể trong quá khứ.");
        add(q, id, "GRAMMAR", "Chọn thì đúng: She ___ already ___ her homework.", "has / finished", "had / finish", "is / finishing", "did / finish", "A", "Present perfect: have/has + V3. 'Already' là signal word của present perfect.");
        add(q, id, "CONVERSATION", "Sửa lỗi: 'I have visited Paris last year.'", "I visited Paris last year.", "I have visit Paris last year.", "I am visiting Paris last year.", "I will visit Paris last year.", "A", "Present perfect không dùng với 'last year'; dùng past simple.");
    }

    // Day 46 – Experiences and Life Events
    private static void seedDay46(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Inspire' nghĩa là?", "Làm nản lòng", "Truyền cảm hứng", "Giải thích", "Khuyên nhủ", "B", "Inspire = truyền cảm hứng, khơi dậy động lực.");
        add(q, id, "VOCABULARY", "'Proud' nghĩa là?", "Xấu hổ", "Tự hào", "Lo lắng", "Ngạc nhiên", "B", "Proud = tự hào; 'feel proud of' = tự hào về điều gì.");
        add(q, id, "GRAMMAR", "Have you ever ___ (try) rock climbing?", "try", "tried", "been trying", "to try", "B", "Present perfect + ever: Have you ever + V3. 'Tried' là V3 của 'try'.");
        add(q, id, "GRAMMAR", "It was the first time I ___ (speak) to a foreigner.", "speak", "have spoken", "spoke", "am speaking", "C", "Sau 'it was the first time' dùng past simple: spoke.");
        add(q, id, "CONVERSATION", "Câu nào đúng về trải nghiệm:", "I never have tried bungee jumping.", "I have never tried bungee jumping.", "I never tried bungee jumping ever.", "I haven't never tried bungee jumping.", "B", "Cấu trúc: I have never + V3. 'Never' đứng sau have/has.");
    }

    // Day 47 – Future Plans and Predictions
    private static void seedDay47(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Definitely' nghĩa là?", "Có lẽ", "Chắc chắn", "Thỉnh thoảng", "Không bao giờ", "B", "Definitely = chắc chắn, nhất định.");
        add(q, id, "VOCABULARY", "'Predict' nghĩa là?", "Nhớ lại", "Dự đoán", "Quyết định", "Lên kế hoạch", "B", "Predict = dự đoán về tương lai.");
        add(q, id, "GRAMMAR", "Dùng 'will' hay 'going to': Ồ không, cốc nước sắp rơi! ___", "It will fall.", "It is going to fall.", "It falls.", "It fell.", "B", "Going to = thấy bằng chứng ngay trước mắt rằng điều đó sắp xảy ra.");
        add(q, id, "GRAMMAR", "She ___ probably ___ (move) to Hanoi next year.", "is / moved", "will / move", "going / to move", "might / moved", "B", "Will + probably + V nguyên mẫu cho dự đoán không chắc chắn.");
        add(q, id, "CONVERSATION", "Nói về kế hoạch tương lai đúng nhất:", "I am thinking to apply for a scholarship next year.", "I am going to apply for a scholarship next year.", "I will going to apply for a scholarship.", "I apply for a scholarship next year.", "B", "Going to + V nguyên mẫu cho kế hoạch đã định.");
    }

    // Day 48 – First Conditional
    private static void seedDay48(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Unless' trong câu điều kiện nghĩa là?", "Nếu", "Nếu không / Trừ khi", "Mặc dù", "Bởi vì", "B", "Unless = if not; Unless you study = if you don't study.");
        add(q, id, "VOCABULARY", "'Consequence' nghĩa là?", "Nguyên nhân", "Hậu quả", "Điều kiện", "Giải pháp", "B", "Consequence = hậu quả sau một hành động hoặc quyết định.");
        add(q, id, "GRAMMAR", "First conditional đúng: If you ___ hard, you ___ succeed.", "work / will", "worked / would", "will work / will", "work / would", "A", "First conditional: If + present simple, will + V nguyên mẫu.");
        add(q, id, "GRAMMAR", "Sửa lỗi: 'If it will rain, I will stay home.'", "If it rains, I will stay home.", "If it rained, I would stay home.", "If it will rain, I stay home.", "If it is raining, I will stay home.", "A", "Mệnh đề 'if' dùng present simple, không dùng 'will'.");
        add(q, id, "CONVERSATION", "Câu first conditional tự nhiên nhất:", "If I will get the job, I move to the city.", "Unless I get the job, I won't move to the city.", "If I got the job, I will move to the city.", "If I get the job, I will moving to the city.", "B", "Unless = nếu không; Unless I get the job = If I don't get the job.");
    }

    // Day 49 – Suggestions and Recommendations
    private static void seedDay49(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Recommend' nghĩa là?", "Yêu cầu", "Đề xuất / khuyên dùng", "Từ chối", "Phàn nàn", "B", "Recommend = đề xuất, giới thiệu ai/cái gì với người khác.");
        add(q, id, "VOCABULARY", "'Consider' nghĩa là?", "Bỏ qua", "Suy xét / cân nhắc", "Quyết định ngay", "Phủ nhận", "B", "Consider = cân nhắc kỹ trước khi quyết định.");
        add(q, id, "GRAMMAR", "Gợi ý tự nhiên nhất: 'Bạn nên thử ứng dụng này.'", "You might want to try this app.", "You must to try this app.", "You should trying this app.", "I suggest you try this app must.", "A", "'Might want to + V' là cách gợi ý lịch sự và tự nhiên ở B1.");
        add(q, id, "GRAMMAR", "'I suggest' dùng với cấu trúc nào?", "I suggest to go early.", "I suggest going early.", "I suggest go early.", "I suggest gone early.", "B", "Suggest + V-ing (không dùng to + V sau suggest).");
        add(q, id, "CONVERSATION", "Đề xuất lịch sự nhất:", "You must join an English club immediately.", "Have you considered joining an English club?", "Why don't join an English club?", "You should to join an English club.", "B", "'Have you considered + V-ing?' là cách đề xuất rất lịch sự.");
    }

    // Day 50 – Agreeing and Disagreeing
    private static void seedDay50(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Absolutely' dùng để làm gì?", "Phủ nhận hoàn toàn", "Đồng ý mạnh mẽ", "Đặt câu hỏi", "Giải thích lý do", "B", "Absolutely = hoàn toàn đúng; dùng để đồng ý mạnh.");
        add(q, id, "VOCABULARY", "'Fair enough' có nghĩa là?", "Công bằng như nhau", "Được rồi / Chấp nhận được", "Không công bằng", "Chưa đủ", "B", "Fair enough = câu chấp nhận lý do/quan điểm của người khác dù không hoàn toàn đồng ý.");
        add(q, id, "GRAMMAR", "Bày tỏ không đồng ý lịch sự nhất:", "You are completely wrong!", "I see your point, but I have a slightly different view.", "No, that is not true at all.", "I disagree totally with everything you said.", "B", "'I see your point, but...' là cách không đồng ý lịch sự và được chấp nhận trong B1.");
        add(q, id, "GRAMMAR", "'However' dùng đúng nhất trong câu nào?", "She is tired however she keeps working.", "She is tired, however she keeps working.", "She is tired. However, she keeps working.", "However she is tired she keeps working.", "C", "'However' thường đứng đầu câu mới sau dấu chấm, theo sau là dấu phẩy.");
        add(q, id, "CONVERSATION", "Đồng ý một phần đúng nhất:", "I completely agree, there is nothing to add.", "I totally disagree with your point.", "That is a fair point, although I think cost is also important.", "You are right, however I think you are wrong.", "C", "'That is a fair point, although...' = đồng ý một phần và thêm quan điểm khác.");
    }

    // Day 51 – Giving Opinions with Reasons
    private static void seedDay51(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Evidence' nghĩa là?", "Ý kiến", "Bằng chứng", "Lý do", "Quan điểm", "B", "Evidence = bằng chứng, thông tin chứng minh điều gì đó.");
        add(q, id, "VOCABULARY", "'Therefore' nghĩa là?", "Tuy nhiên", "Do đó / Vì vậy", "Ngoài ra", "Mặt khác", "B", "Therefore = do đó, vì vậy; dùng để nêu kết quả/kết luận từ lý do trước.");
        add(q, id, "GRAMMAR", "Sai cấu trúc nào khi đưa ra ý kiến?", "In my opinion, exercise is important.", "I believe that exercise is important.", "According to me, exercise is important.", "Personally, I think exercise is important.", "C", "'According to me' là lỗi tiếng Anh; đúng là 'In my opinion' hoặc 'I think'.");
        add(q, id, "GRAMMAR", "Chọn linking word đúng: Exercise is healthy. ___ , it reduces stress.", "However", "Furthermore", "Unless", "Although", "B", "Furthermore = ngoài ra, hơn nữa; dùng để thêm thông tin bổ sung.");
        add(q, id, "CONVERSATION", "Đưa ý kiến có lý do tự nhiên nhất:", "English is important. Learn it.", "In my opinion, English is important because it opens career opportunities.", "I think English important for jobs.", "English is important. However, some disagree. I agree.", "B", "Cấu trúc: In my opinion + mệnh đề + because + lý do.");
    }

    // Day 52 – Describing Problems in Detail
    private static void seedDay52(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Challenge' dùng tích cực nghĩa là?", "Rào cản không thể vượt", "Thách thức khơi dậy nỗ lực", "Vấn đề nan giải", "Trở ngại tiêu cực", "B", "Challenge có thể là tích cực: something difficult that motivates you.");
        add(q, id, "VOCABULARY", "'Resolve' nghĩa là?", "Gây ra vấn đề", "Giải quyết triệt để", "Trì hoãn", "Phàn nàn", "B", "Resolve = giải quyết hoàn toàn; mạnh hơn 'solve'.");
        add(q, id, "GRAMMAR", "Mô tả vấn đề đúng nhất:", "The problem it is that we have no time.", "The main problem is that we do not have enough time.", "There is problem of no time.", "The problem is not having enough time of us.", "B", "Cấu trúc: The main problem is that + mệnh đề.");
        add(q, id, "GRAMMAR", "Passive: 'The issue ___ (not / address) yet.'", "is not addressed", "has not been addressed", "was not addressing", "does not address", "B", "Present perfect passive: has/have + been + V3 để nói về điều chưa được giải quyết.");
        add(q, id, "CONVERSATION", "Giải thích vấn đề tự nhiên nhất:", "Many people have problems everywhere all the time.", "The root cause of this issue is a lack of clear communication.", "Problem exist because bad things happen always.", "We should solve the problem which is the problem.", "B", "Cấu trúc chuyên nghiệp: 'The root cause of... is...'");
    }

    // Day 53 – Work and Study Discussions
    private static void seedDay53(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Deadline' nghĩa là?", "Thời gian tự do", "Hạn chót phải hoàn thành", "Lịch làm việc", "Ngày bắt đầu", "B", "Deadline = hạn chót, thời điểm cuối phải nộp/hoàn thành.");
        add(q, id, "VOCABULARY", "'Productive' nghĩa là?", "Lười biếng", "Năng suất cao / hiệu quả", "Bận bịu nhưng không hiệu quả", "Sáng tạo", "B", "Productive = làm được nhiều việc hữu ích trong thời gian cho phép.");
        add(q, id, "GRAMMAR", "Cấu trúc trang trọng hơn trong môi trường công việc:", "I am very busy with lots of stuff.", "I am currently managing several high-priority tasks.", "Got many things to do, very busy now.", "I have many things. They are important.", "B", "Ngôn ngữ công việc cần rõ ràng và chuyên nghiệp hơn.");
        add(q, id, "GRAMMAR", "Nói về thói quen công việc dùng thì nào? 'She ___ always ___ overtime.'", "is / working", "works / —", "has / worked", "does / work", "B", "Thói quen hiện tại dùng present simple: She always works overtime.");
        add(q, id, "CONVERSATION", "Nói về khối lượng công việc tự nhiên nhất:", "My work is too much for me this month.", "I have been dealing with a very heavy workload this month.", "I have many works this month, it is heavy.", "Work is heavy and I cannot do it this month.", "B", "Dùng 'heavy workload' thay vì 'many works'; 'dealing with' tự nhiên hơn.");
    }

    // Day 54 – Travel Stories and Complaints
    private static void seedDay54(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Accommodation' nghĩa là?", "Phương tiện di chuyển", "Chỗ ở", "Hành lý", "Vé máy bay", "B", "Accommodation = nơi ở khi đi du lịch (khách sạn, nhà nghỉ...).");
        add(q, id, "VOCABULARY", "'Complaint' nghĩa là?", "Lời khen", "Phàn nàn / Khiếu nại", "Yêu cầu", "Đề nghị", "B", "Complaint = phàn nàn chính thức hoặc không chính thức về điều gì đó.");
        add(q, id, "GRAMMAR", "Kể chuyện du lịch – câu nào đúng?", "When we arrived, the hotel said they had no record of our booking.", "When we arrived, the hotel said they have no record of our booking.", "When we arrived, the hotel says they had no record of our booking.", "When we arrived, the hotel told they had no record of our booking.", "A", "Sau 'said' dùng 'had' (reported speech: quá khứ hoàn thành). 'Say' + that + mệnh đề.");
        add(q, id, "GRAMMAR", "Phàn nàn lịch sự: 'I ___ like to make a complaint about the service.'", "will", "would", "am going to", "must", "B", "'Would like to' lịch sự hơn 'want to' trong ngữ cảnh phàn nàn chính thức.");
        add(q, id, "CONVERSATION", "Phàn nàn lịch sự và hiệu quả nhất:", "Your hotel is terrible! Fix it now!", "I am afraid there seems to be a problem with my room.", "Room no good. I want change.", "You have bad service and I am complaining!", "B", "'I am afraid there seems to be...' là cách phàn nàn rất lịch sự ở mức B1+.");
    }

    // Day 55 – Short Presentations
    private static void seedDay55(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Furthermore' trong bài thuyết trình nghĩa là?", "Ngược lại", "Hơn nữa / Ngoài ra", "Tóm lại", "Đầu tiên", "B", "Furthermore = hơn nữa; thêm điểm bổ sung trang trọng.");
        add(q, id, "VOCABULARY", "'Outline' trong thuyết trình nghĩa là?", "Chi tiết đầy đủ", "Đề cương / phác thảo cấu trúc", "Kết luận", "Hỏi đáp", "B", "Outline = giới thiệu cấu trúc chính của bài thuyết trình.");
        add(q, id, "GRAMMAR", "Câu mở đầu thuyết trình đúng nhất:", "Hi, today I say about environment.", "Good morning. Today I am going to talk about environmental issues.", "Hello. I want talk about environment today.", "Today I will talking about the environment.", "B", "Mở đầu chuẩn: Good morning + Today I am going to talk about...");
        add(q, id, "GRAMMAR", "Kết bài thuyết trình đúng nhất:", "So, that's it. Bye.", "In conclusion, I hope you will consider these points.", "The end, thank you, goodbye.", "Conclusion: points are important, I said them.", "B", "'In conclusion' + tóm tắt + lời kết là cấu trúc chuẩn.");
        add(q, id, "CONVERSATION", "Từ nối nào phù hợp nhất để bắt đầu điểm thứ hai?", "Anyway,", "Secondly,", "So what,", "But like,", "B", "Secondly = thứ hai; phù hợp trong bài thuyết trình có cấu trúc rõ ràng.");
    }

    // Day 56 – B1 Final Review
    private static void seedDay56(List<QuizQuestion> q, int id) {
        add(q, id, "VOCABULARY", "'Persevere' nghĩa là?", "Từ bỏ", "Kiên trì vượt khó", "Thay đổi hướng đi", "Bắt đầu lại", "B", "Persevere = tiếp tục làm điều gì dù khó khăn không bỏ cuộc.");
        add(q, id, "VOCABULARY", "'Bias' nghĩa là?", "Quan điểm khách quan", "Định kiến / thiên vị không công bằng", "Ý kiến trung lập", "Phân tích chính xác", "B", "Bias = xu hướng thiên vị, nhìn không công bằng.");
        add(q, id, "GRAMMAR", "Tổng ôn – chọn đúng: 'Although it ___ raining, we ___ out.'", "is / go", "was / went", "was / have gone", "is / went", "B", "Although + past simple; main clause + past simple khi kể chuyện quá khứ.");
        add(q, id, "GRAMMAR", "Present perfect passive: 'The report ___ not ___ yet.'", "is / written", "has / been written", "was / write", "have / written", "B", "Present perfect passive: has/have + been + V3.");
        add(q, id, "CONVERSATION", "Câu nào thể hiện B1 tốt nhất?", "I learn English. It good.", "I have been learning English for two years and my confidence has improved significantly.", "I am learning English for two years. I am more confident.", "I learned English two years. Better now.", "B", "B1 = present perfect + for, từ vựng phong phú, cấu trúc rõ ràng.");
    }

    private static void add(List<QuizQuestion> list, int dayId, String type, String question,
                             String optA, String optB, String optC, String optD,
                             String correct, String explanation) {
        list.add(new QuizQuestion(dayId, question, optA, optB, optC, optD, correct, explanation));
    }
}
