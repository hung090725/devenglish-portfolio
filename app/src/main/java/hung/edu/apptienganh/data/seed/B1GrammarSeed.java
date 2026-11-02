package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.GrammarPoint;

public final class B1GrammarSeed {

    private B1GrammarSeed() {}

    public static List<GrammarPoint> buildGrammarPoints() {
        List<GrammarPoint> list = new ArrayList<>();

        list.add(g("Present Perfect with Ever/Never",
                "Subject + have/has + ever/never + past participle",
                "Dùng 'ever' để hỏi về trải nghiệm trong cuộc đời; 'never' để phủ định trải nghiệm.",
                "Have you ever...?, I have never..., Has she ever...?",
                "Have you ever visited Japan? / I have never eaten sushi. / Has he ever won a prize?",
                "Have you ever visit Japan? / I never have eaten sushi. / Has he ever win a prize?",
                "Sau 'ever/never' dùng quá khứ phân từ (V3), không dùng V nguyên mẫu. Người Việt hay quên chia đúng dạng động từ.",
                "Complete: ___ you ever ___ (be) to Europe? → Have you ever been to Europe?"));

        list.add(g("Present Perfect with For/Since",
                "Subject + have/has + past participle + for/since",
                "Dùng 'for' với khoảng thời gian; 'since' với thời điểm bắt đầu.",
                "for two years, for a long time, since 2019, since last Monday",
                "I have lived here for three years. / She has worked there since 2020. / They have known each other for a long time.",
                "I live here for three years. / She works there since 2020. / I have lived here since three years.",
                "'For' + khoảng thời gian (for 3 years, for months). 'Since' + thời điểm cụ thể (since 2020, since Monday). Người Việt hay nhầm và dùng 'since' với khoảng thời gian.",
                "Choose: She has studied English ___ (for/since) five years. → for"));

        list.add(g("Present Perfect vs Past Simple",
                "Present perfect: have/has + V3 (no time point) | Past simple: V2 + time expression",
                "Past simple = thời gian xác định đã qua; Present perfect = không xác định hoặc kết nối với hiện tại.",
                "yesterday, last year, ago → past simple | ever, just, already, yet, recently → present perfect",
                "I visited Paris last year. (specific) / I have visited Paris. (experience) / She has just called me. / He called me ten minutes ago.",
                "I have visited Paris last year. / I visited Paris already. / She just called me ten minutes ago (mixing tenses).",
                "Lỗi thường gặp: dùng present perfect với thời gian cụ thể như 'yesterday' hay 'last year'. Dùng past simple khi có thời điểm.",
                "Correct the error: I have seen that film last night. → I saw that film last night."));

        list.add(g("Used to",
                "Subject + used to + base verb",
                "Nói về thói quen hoặc trạng thái trong quá khứ không còn xảy ra nữa.",
                "used to, didn't use to, did you use to...?",
                "I used to walk to school every day. / She used to be very shy. / He didn't use to like vegetables.",
                "I use to walk to school. / I used to walked to school. / I am used to walk to school. (khác nghĩa)",
                "Không thêm -ed sau 'used to' vì động từ theo sau là nguyên mẫu. Không nhầm 'used to' (thói quen quá khứ) với 'be used to' (quen với).",
                "Translate: Hồi nhỏ tôi hay đi xe đạp. → I used to ride a bike when I was young."));

        list.add(g("First Conditional",
                "If + present simple, will + base verb",
                "Điều kiện có thể xảy ra trong tương lai và kết quả của nó.",
                "if, unless, as long as, provided that",
                "If it rains, I will stay at home. / Unless you study, you will not pass. / If I get the job, I will move to the city.",
                "If it will rain, I stay at home. / If it rains, I would stay at home. / If I will pass, I study.",
                "Mệnh đề 'if' dùng hiện tại đơn; mệnh đề chính dùng 'will'. Không dùng 'will' trong mệnh đề 'if'. Người Việt hay dùng 'would' thay 'will'.",
                "Complete: If you ___ (not study), you ___ (fail) the test. → don't study / will fail"));

        list.add(g("Zero Conditional Review",
                "If + present simple, present simple",
                "Dùng cho sự thật hiển nhiên, điều luôn đúng hoặc quy luật tự nhiên.",
                "if, when, always, every time",
                "If you heat water to 100°C, it boils. / When I am tired, I make mistakes. / If plants don't get water, they die.",
                "If you heat water to 100°C, it will boil. (không sai nhưng zero cond. chuẩn hơn) / If you heated water, it boils.",
                "Cả hai mệnh đề đều dùng hiện tại đơn. Khác first conditional ở chỗ: zero conditional là sự thật tuyệt đối.",
                "Identify: Which is zero conditional? A: If I win, I will be happy. B: If you mix blue and yellow, you get green. → B"));

        list.add(g("Modal Verbs for Advice: Should / Ought to",
                "Subject + should/ought to + base verb",
                "Đưa ra lời khuyên nhẹ nhàng, không bắt buộc.",
                "I think you should..., You ought to..., You probably should...",
                "You should see a doctor. / You ought to apologize to her. / I don't think you should stay up so late.",
                "You should to see a doctor. / You ought see a doctor. / You should seeing a doctor.",
                "Sau 'should' và 'ought to' luôn dùng V nguyên mẫu. 'Ought to' kém phổ biến hơn 'should' trong tiếng Anh hàng ngày.",
                "Give advice: (tired / rest) → You should rest when you are tired."));

        list.add(g("Modal Verbs for Obligation: Must / Have to",
                "Subject + must + base verb | Subject + have to/has to + base verb",
                "'Must' = nghĩa vụ cá nhân/nội tâm; 'Have to' = quy tắc từ bên ngoài.",
                "must, have to, has to, had to (past)",
                "I must study tonight — I have an exam. / You have to show your ID at the border. / She had to work overtime last Friday.",
                "I must to study tonight. / You have to showing your ID. / She must working overtime.",
                "Sau 'must' và 'have to' không dùng 'to' thêm và không thêm -ing/-ed vào động từ chính.",
                "Choose: The doctor says I ___ (must/have to) rest for a week. → must (personal obligation based on health)"));

        list.add(g("Relative Clauses: Who / Which / That",
                "Noun + who (person) / which (thing) / that (person or thing) + clause",
                "Thêm thông tin về danh từ đứng trước bằng cách dùng mệnh đề quan hệ.",
                "who, which, that",
                "The man who called you is my uncle. / I love the book which you recommended. / She is the teacher that helped me most.",
                "The man which called you is my uncle. / I love the book who you recommended. / She is the teacher who is helped me.",
                "Who = người; which = vật. 'That' có thể dùng cho cả người lẫn vật nhưng không dùng trong mệnh đề quan hệ không xác định (non-defining).",
                "Complete: She is the doctor ___ (who/which) treated my father. → who"));

        list.add(g("Gerund vs Infinitive (Basic)",
                "Verb + gerund (V-ing) | Verb + infinitive (to + V)",
                "Một số động từ theo sau bởi V-ing; số khác theo sau bởi to + V.",
                "enjoy, finish, avoid, keep → V-ing | want, decide, plan, hope, agree → to + V",
                "I enjoy swimming. / She decided to leave. / We plan to visit Ha Noi. / He avoided answering the question.",
                "I enjoy to swim. / She decided leaving. / We plan visiting Ha Noi. / He avoided to answer.",
                "Không có quy tắc tuyệt đối; phải học thuộc từng động từ. Người Việt thường hay dùng sai dạng.",
                "Choose: She finished ___ (to study/studying) at midnight. → studying"));

        list.add(g("Reported Speech (Basic Statements)",
                "Subject + said (that) + clause (with tense shift)",
                "Chuyển lời nói trực tiếp sang gián tiếp; thì động từ lùi về quá khứ.",
                "said, told, reported",
                "\"I am tired.\" → She said (that) she was tired. / \"I will call you.\" → He said he would call me. / \"I have finished.\" → She said she had finished.",
                "She said that she is tired. / He said he will call me. / She said she has finished.",
                "Khi chuyển sang reported speech: am/is → was, will → would, have → had. Người Việt thường không lùi thì.",
                "Convert: He said, 'I am going to leave tomorrow.' → He said that he was going to leave the next day."));

        list.add(g("Passive Voice – Present Simple",
                "Object + am/is/are + past participle (+ by + agent)",
                "Chủ thể nhận hành động; tác nhân gây ra hành động có thể được thêm vào sau 'by'.",
                "is made, are produced, is written, am told",
                "English is spoken in many countries. / This book is written by a famous author. / Rice is grown in the south.",
                "English is speak in many countries. / This book is wrote by a famous author. / Rice is growing in the south.",
                "Dùng đúng dạng be (am/is/are) theo chủ ngữ và quá khứ phân từ (V3). Người Việt hay dùng V nguyên mẫu sau 'is/are'.",
                "Change to passive: People produce coffee in Da Lat. → Coffee is produced in Da Lat."));

        list.add(g("Passive Voice – Past Simple",
                "Object + was/were + past participle (+ by + agent)",
                "Hành động trong quá khứ tác động lên chủ thể; tác nhân có thể có hoặc không.",
                "was built, were told, was written, was stolen",
                "The bridge was built in 1990. / The windows were broken by the storm. / My wallet was stolen yesterday.",
                "The bridge was build in 1990. / The windows were broke by the storm. / My wallet was steal yesterday.",
                "Dùng was/were theo chủ ngữ số ít/nhiều và quá khứ phân từ. Người Việt thường nhầm với dạng nguyên mẫu.",
                "Change: They built this house in 2000. → This house was built in 2000."));

        list.add(g("Too / Enough",
                "Too + adjective/adverb | adjective/adverb + enough",
                "'Too' = quá mức không tốt; 'enough' = đủ để làm điều gì đó.",
                "too + adj, adj + enough (not too), enough + noun",
                "This coffee is too hot to drink. / She is old enough to drive. / There is enough food for everyone. / He is not tall enough to reach the shelf.",
                "This coffee is enough hot to drink. / She is too old enough to drive. / He is not enough tall.",
                "'Too' đứng TRƯỚC tính từ/trạng từ. 'Enough' đứng SAU tính từ/trạng từ và TRƯỚC danh từ. Lỗi phổ biến nhất của người Việt.",
                "Complete: The bag is ___ (too/enough) heavy for me to carry. → too"));

        list.add(g("So / Such",
                "So + adjective/adverb | Such + (a/an) + adjective + noun",
                "Diễn đạt mức độ cao, thường đi kèm với 'that' để chỉ kết quả.",
                "so tired, so beautiful, such a good idea, such a nice person",
                "She is so kind that everyone loves her. / It was such a good film that I watched it twice. / He speaks so quickly that I cannot understand him.",
                "She is such kind that everyone loves her. / It was so a good film. / He speaks so quickly I cannot understand. (thiếu 'that')",
                "'So' + adj/adv (không có danh từ). 'Such' + danh từ (có thể kèm tính từ). 'That' tùy chọn nhưng hay dùng.",
                "Choose: She is ___ (so/such) a talented singer. → such"));

        list.add(g("Although / However",
                "Although + clause, main clause | Main clause. However, + clause.",
                "'Although' nối hai mệnh đề tương phản trong một câu; 'However' bắt đầu câu mới thể hiện sự tương phản.",
                "although, even though, however, nevertheless",
                "Although it was raining, we went for a walk. / The film was long. However, I really enjoyed it. / Even though she was tired, she kept working.",
                "Although it was raining but we went for a walk. / However it was raining, we went for a walk.",
                "Không dùng 'although' và 'but' cùng nhau trong một câu. 'However' thường đứng sau dấu chấm phẩy hoặc đầu câu mới.",
                "Combine: It was cold. We had a great time. → Although it was cold, we had a great time."));

        list.add(g("Giving Opinions",
                "In my opinion, ... | Personally, I think... | I believe that... | From my point of view,...",
                "Diễn đạt quan điểm cá nhân một cách lịch sự và rõ ràng.",
                "In my opinion, I think, I believe, Personally, From my point of view, As far as I am concerned",
                "In my opinion, learning English is essential. / Personally, I think this plan needs more work. / I believe that education should be free.",
                "According to me, learning English is important. / My opinion is that learning English is important (có thể đúng nhưng kém tự nhiên).",
                "'According to me' là lỗi phổ biến của người Việt; nên nói 'In my opinion' hoặc 'I think'. Không dùng 'according to' với quan điểm cá nhân.",
                "Express opinion: (exercise / health) → In my opinion, exercise is one of the best ways to maintain good health."));

        list.add(g("Linking Ideas in Short Presentations",
                "Firstly... Secondly... Furthermore... In addition... Finally... To sum up...",
                "Sử dụng các từ nối để cấu trúc bài thuyết trình rõ ràng và logic.",
                "firstly, secondly, thirdly, furthermore, in addition, moreover, however, on the other hand, in conclusion, to sum up",
                "Firstly, I will explain the problem. Secondly, I will suggest some solutions. Furthermore, these solutions are cost-effective. In conclusion, I hope you will consider my proposals.",
                "First of all, then, after that, finally (too informal for a presentation) / In conclusion, it shows that (vague).",
                "Bài thuyết trình cần từ nối trang trọng hơn cuộc trò chuyện thường ngày. Tránh dùng 'and then' liên tục.",
                "Put in order: ___ (to sum up) / ___ (firstly) / ___ (furthermore) → Firstly... Furthermore... To sum up..."));

        return list;
    }

    private static GrammarPoint g(String title, String formula, String usageVi,
                                   String signalWords, String correctExamples,
                                   String wrongExamples, String explanationVi,
                                   String shortPractice) {
        GrammarPoint gp = new GrammarPoint();
        gp.setLevel("B1");
        gp.setTitle(title);
        gp.setFormula(formula);
        gp.setUsageVi(usageVi);
        gp.setSignalWords(signalWords);
        gp.setCorrectExamples(correctExamples);
        gp.setWrongExamples(wrongExamples);
        gp.setExplanationVi(explanationVi);
        gp.setShortPracticeQuestions(shortPractice);
        return gp;
    }
}
