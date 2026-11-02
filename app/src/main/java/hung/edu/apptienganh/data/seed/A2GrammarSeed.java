package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.GrammarPoint;

public final class A2GrammarSeed {

    private A2GrammarSeed() {}

    public static List<GrammarPoint> buildGrammarPoints() {
        List<GrammarPoint> list = new ArrayList<>();

        list.add(grammar("A2",
                "Past Simple – Regular Verbs",
                "Subject + verb-ed",
                "Dùng để kể sự kiện, hành động đã xảy ra và kết thúc trong quá khứ.",
                "yesterday, last week, ago, in 2020",
                "I walked to school yesterday. / She cooked dinner last night.",
                "I walk to school yesterday. / She cook dinner last night.",
                "Thêm -ed vào động từ có quy tắc. Chú ý: study → studied, stop → stopped. Người Việt hay quên đổi dạng.",
                "Complete: She ___ (watch) TV last night. → watched"));

        list.add(grammar("A2",
                "Past Simple – Irregular Verbs",
                "Subject + irregular past form",
                "Một số động từ có dạng quá khứ riêng, không thêm -ed.",
                "yesterday, last week, ago, in the past",
                "I went to the market. / She ate noodles for lunch. / We had a great time.",
                "I go to the market yesterday. / She eat noodles. / We have a great time.",
                "Không có quy tắc; cần học thuộc. Người Việt thường dùng dạng nguyên mẫu cho câu quá khứ.",
                "What is the past tense of 'go'? → went"));

        list.add(grammar("A2",
                "Past Simple – Negative",
                "Subject + did not (didn't) + base verb",
                "Phủ định câu quá khứ bằng cách thêm 'did not' và giữ động từ nguyên mẫu.",
                "yesterday, last week, ago",
                "I didn't go to school yesterday. / She didn't eat breakfast.",
                "I didn't went to school. / She didn't ate breakfast.",
                "Lỗi cực phổ biến của người Việt: thêm 'didn't + V-ed'. Sau 'didn't' dùng V nguyên mẫu.",
                "Correct: She didn't ___ (come) to class. → didn't come"));

        list.add(grammar("A2",
                "Past Simple – Questions",
                "Did + subject + base verb...?",
                "Hỏi về hành động trong quá khứ dùng 'Did'.",
                "yesterday, last week, ago",
                "Did you go to the party? / Did she enjoy the film?",
                "Did you went to the party? / Did she enjoyed the film?",
                "Sau 'Did' dùng V nguyên mẫu, không thêm -ed hay dạng bất quy tắc.",
                "Form a question: (you / eat breakfast / this morning)? → Did you eat breakfast this morning?"));

        list.add(grammar("A2",
                "Future with Going To",
                "Subject + am/is/are + going to + base verb",
                "Nói về kế hoạch đã định, ý định rõ ràng trong tương lai.",
                "next week, next year, this summer, tomorrow",
                "I am going to visit my family next week. / She is going to study abroad.",
                "I going to visit my family. / She is go to study abroad.",
                "Người Việt hay bỏ 'am/is/are' hoặc quên 'to'. Câu hỏi: Are you going to...?",
                "Complete: They ___ (going to / move) to a new house next month. → are going to move"));

        list.add(grammar("A2",
                "Future with Will",
                "Subject + will + base verb",
                "Dùng cho quyết định tức thì, lời hứa, dự đoán không chắc chắn.",
                "I think, probably, maybe, I promise",
                "I will help you. / It will probably rain tonight. / I won't forget.",
                "I will to help you. / I will helped you. / It will raining tonight.",
                "Sau 'will' dùng V nguyên mẫu, không thêm 'to' và không thêm -ing/-ed.",
                "Choose: I think she ___ (will / is going to) win because she is the best."));

        list.add(grammar("A2",
                "Comparatives",
                "Adjective + -er + than / more + adjective + than",
                "So sánh hai sự vật hoặc người.",
                "than",
                "This phone is cheaper than that one. / She is more intelligent than her brother.",
                "This phone is more cheap than that. / She is more intelligent as her brother.",
                "Tính từ ngắn 1-2 âm tiết: -er. Dài hơn: 'more'. Dùng 'than' không phải 'as' trong so sánh hơn.",
                "Complete: Ho Chi Minh City is ___ (big) ___ Hanoi. → bigger than"));

        list.add(grammar("A2",
                "Superlatives",
                "the + adjective + -est / the most + adjective",
                "Nói về người/vật nổi trội nhất trong nhóm.",
                "in the world, in my class, of all",
                "She is the tallest in our class. / This is the most expensive restaurant in town.",
                "She is the tallest of our class. / This is the most expensive restaurant in the town.",
                "Sau superlative dùng 'in' (không gian) hoặc 'of' (nhóm người/thời gian). Người Việt hay dùng 'of the class'.",
                "Make a sentence using superlative: (beautiful / city / this / world) → This is the most beautiful city in the world."));

        list.add(grammar("A2",
                "Much / Many / A lot of",
                "much + uncountable / many + countable / a lot of + both",
                "Nói về số lượng lớn của danh từ đếm được và không đếm được.",
                "How much, How many, too much, too many, a lot of",
                "How many people came? / I don't have much time. / There is a lot of traffic.",
                "How much people came? / I don't have many time. / There is much traffic.",
                "Much/many dùng trong câu phủ định và câu hỏi. A lot of dùng được cho cả hai loại danh từ.",
                "Choose: There isn't ___ (much/many) sugar left in the jar. → much"));

        list.add(grammar("A2",
                "Should / Should not",
                "Subject + should + base verb",
                "Đưa ra lời khuyên hoặc gợi ý không bắt buộc.",
                "I think you should..., You shouldn't...",
                "You should drink more water. / You shouldn't stay up too late.",
                "You should to drink more water. / You shouldn't staying up late.",
                "Sau 'should' dùng V nguyên mẫu, không thêm 'to' hoặc '-ing'.",
                "Give advice: (feel sick / see a doctor) → You should see a doctor."));

        list.add(grammar("A2",
                "Must / Have to",
                "Subject + must + base verb / Subject + have to + base verb",
                "Nói về nghĩa vụ bắt buộc; 'must' mang ý nghĩa cá nhân, 'have to' mang ý nghĩa quy định bên ngoài.",
                "must, have to, has to",
                "You must wear a helmet. / I have to finish this by Monday. / She has to work on Saturday.",
                "You must to wear a helmet. / I must to finish this. / She have to work.",
                "'Must' không có 'to'; 'have/has to' cần thêm 'to'. 'Has to' dùng với he/she/it.",
                "Fill in: She ___ (have to/must) present her report today. She has no choice."));

        list.add(grammar("A2",
                "Have to – Negative and Questions",
                "Subject + don't/doesn't have to + base verb / Do/Does + subject + have to...?",
                "'Don't/doesn't have to' nghĩa là không cần thiết, không bắt buộc (khác với 'must not').",
                "don't have to, doesn't have to",
                "You don't have to bring cash. / Does she have to work tomorrow?",
                "You don't must bring cash. / You must not bring cash. (wrong meaning)",
                "Người Việt hay nhầm 'don't have to' (không cần) với 'must not' (không được phép). Nghĩa hoàn toàn khác nhau.",
                "Translate: Bạn không cần phải làm bài tập. → You don't have to do the homework."));

        list.add(grammar("A2",
                "Because / So / But / Although",
                "Clause + because/so/but/although + clause",
                "Dùng liên từ để nối ý tương phản, nguyên nhân và kết quả.",
                "because, so, but, although",
                "I stayed home because I was sick. / She was tired but she kept working. / Although it rained, we went out.",
                "I stayed home because I was sick so I couldn't go. (tránh dùng hai liên từ cùng lúc)",
                "Không dùng 'because' và 'so' trong cùng một câu để giải thích và kết luận cùng lúc.",
                "Join: It was very cold. We went for a walk. → Although it was very cold, we went for a walk."));

        list.add(grammar("A2",
                "Sequence Words: First / Then / After that / Finally",
                "First + clause. Then + clause. After that + clause. Finally + clause.",
                "Dùng để mô tả các bước hoặc trình tự sự việc.",
                "first, then, next, after that, finally",
                "First I boiled some water. Then I added the noodles. After that I stirred it. Finally I served it.",
                "I first boiled the water, then I added the noodles finally I served it.",
                "Dùng dấu phẩy sau mỗi trạng từ đầu câu. Không cần viết hoa 'then/after that' ở giữa câu.",
                "Arrange: (finally / washed / first / then / hands / rinsed / he / soap / applied) → First he applied soap, then he washed, after that he rinsed, finally..."));

        list.add(grammar("A2",
                "Object Pronouns",
                "me / you / him / her / it / us / them",
                "Dùng đại từ tân ngữ thay thế danh từ khi nó là tân ngữ trong câu.",
                "tell me, give her, call him, help them, show us",
                "Give it to me please. / She called him last night. / We love them.",
                "Give it to I please. / She called he last night. / We love they.",
                "Người Việt hay dùng chủ ngữ thay tân ngữ (I/he/they thay vì me/him/them). Vị trí sau động từ hoặc giới từ.",
                "Replace the noun: Please help (John and Mary). → Please help them."));

        list.add(grammar("A2",
                "Adverbs of Manner",
                "Adjective + -ly (or irregular forms like well, fast, hard)",
                "Mô tả cách thức thực hiện hành động; thường đứng sau động từ hoặc tân ngữ.",
                "quickly, carefully, slowly, well, loudly, fast, hard",
                "She speaks English fluently. / He ran quickly to the bus stop. / Drive carefully.",
                "She speaks English fluent. / He ran quick. / Drive careful.",
                "Người Việt hay dùng tính từ thay trạng từ (fluent thay vì fluently, quick thay vì quickly).",
                "Change to adverb: She is a careful driver. → She drives carefully."));

        return list;
    }

    private static GrammarPoint grammar(String level, String title, String formula,
                                        String usageVi, String signalWords,
                                        String correctExamples, String wrongExamples,
                                        String explanationVi, String shortPracticeQuestions) {
        GrammarPoint gp = new GrammarPoint();
        gp.setLevel(level);
        gp.setTitle(title);
        gp.setFormula(formula);
        gp.setUsageVi(usageVi);
        gp.setSignalWords(signalWords);
        gp.setCorrectExamples(correctExamples);
        gp.setWrongExamples(wrongExamples);
        gp.setExplanationVi(explanationVi);
        gp.setShortPracticeQuestions(shortPracticeQuestions);
        return gp;
    }
}
