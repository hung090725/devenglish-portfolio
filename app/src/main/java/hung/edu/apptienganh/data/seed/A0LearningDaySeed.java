package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.LearningDay;

public final class A0LearningDaySeed {

    private A0LearningDaySeed() {}

    public static List<LearningDay> buildLearningDays() {
        List<LearningDay> days = new ArrayList<>();
        days.add(day(1, "Ngày 1: Bảng chữ cái & Chào hỏi",
                "Nhận biết bảng chữ cái, chào hỏi cơ bản và nói câu I am...",
                "Hôm nay bạn học 26 chữ cái, cách chào hỏi lịch sự và giới thiệu tên bằng động từ to be.",
                "Âm /eɪ/ (A), /biː/ (B), /siː/ (C). Chào: /həˈloʊ/, /ɡʊd ˈmɔːr.nɪŋ/.",
                "hello, hi, good morning, good night, name, I, am, student",
                "Verb to be: am/is/are", 2,
                "Hello!|Xin chào!\nI am a student.|Tôi là học sinh.\nNice to meet you.|Rất vui được gặp bạn.",
                "A is for apple.|A là chữ apple.\nGood morning, teacher.|Chào buổi sáng, thầy.",
                "Hello, my name is Hung.\nI am a student.\nNice to meet you.",
                20, 1, 1));
        days.add(day(2, "Ngày 2: Đại từ & Giới thiệu bản thân",
                "Dùng đại từ nhân xưng, hỏi tên, tuổi và quê quán.",
                "Bạn sẽ biết cách hỏi What is your name?, How old are you? và Where are you from?",
                "Đại từ I /aɪ/, you /juː/, he /hiː/, she /ʃiː/.",
                "I, you, he, she, my, your, name, age, from, live",
                "Personal pronouns", 1,
                "What is your name?|Tên bạn là gì?\nI am twelve years old.|Tôi mười hai tuổi.\nI am from Vietnam.|Tôi đến từ Việt Nam.",
                "My name is Linh.|Tên tôi là Linh.\nHow old are you?|Bạn bao nhiêu tuổi?",
                "My name is Hung.\nI am twenty years old.\nI am from Hanoi.",
                20, 2, 2));
        days.add(day(3, "Ngày 3: Động từ To be",
                "Dùng am, is, are trong câu khẳng định và hỏi.",
                "Nắm vững cách chia to be với các chủ ngữ khác nhau.",
                "am /æm/ với I; is /ɪz/ với he/she/it; are /ɑːr/ với they.",
                "am, is, are, happy, sad, student, teacher, fine, good",
                "Verb to be", 2,
                "I am fine.|Tôi khỏe.\nShe is a teacher.|Cô ấy là giáo viên.\nThey are friends.|Họ là bạn bè.",
                "Are you a student?|Bạn có phải học sinh không?\nHe is happy.|Anh ấy vui.",
                "I am fine, thank you.\nShe is my teacher.\nWe are happy.",
                20, 3, 3));
        days.add(day(4, "Ngày 4: Từ vựng Gia đình",
                "Học từ vựng về các thành viên gia đình.",
                "Biết nói về bố, mẹ, anh chị em trong gia đình.",
                "father /ˈfɑː.ðər/, mother /ˈmʌð.ər/, family /ˈfæm.ə.li/.",
                "father, mother, brother, sister, family, parents, son, daughter",
                "Possessive adjectives", 3,
                "This is my father.|Đây là bố tôi.\nMy mother is kind.|Mẹ tôi tốt bụng.\nI love my family.|Tôi yêu gia đình.",
                "She is my sister.|Cô ấy là chị gái tôi.\nHis father is tall.|Bố anh ấy cao.",
                "This is my mother.\nI have one brother.\nI love my family.",
                20, 4, 6));
        days.add(day(5, "Ngày 5: Trường học & Học tập",
                "Từ vựng về trường lớp, đồ dùng học tập.",
                "Giới thiệu trường học, lớp học và môn học yêu thích.",
                "school /skuːl/, student /ˈstjuː.dənt/, teacher /ˈtiː.tʃər/.",
                "school, class, student, teacher, book, pen, lesson, homework, classroom",
                "Singular and plural nouns", 5,
                "I go to school every day.|Tôi đi học mỗi ngày.\nOur teacher is friendly.|Giáo viên thân thiện.\nOpen your book, please.|Mở sách ra.",
                "We have an English lesson.|Chúng ta có bài tiếng Anh.\nSit at your desk.|Ngồi vào bàn.",
                "I am a student.\nI go to school by bus.\nMy favorite subject is English.",
                20, 5, 7));
        days.add(day(6, "Ngày 6: Số đếm & Tuổi",
                "Đếm số từ 1–20 và hỏi tuổi.",
                "Nói về số lượng và tuổi một cách tự nhiên.",
                "one /wʌn/, ten /ten/, twenty /ˈtwen.ti/.",
                "one, two, three, ten, eleven, twenty, number, age, year, old",
                "Singular and plural nouns", 5,
                "I am ten years old.|Tôi mười tuổi.\nHow old are you?|Bạn bao nhiêu tuổi?\nThere are four people in my family.|Gia đình tôi có 4 người.",
                "She is twelve.|Cô ấy mười hai tuổi.\nCount from one to ten.|Đếm từ một đến mười.",
                "I am fifteen years old.\nHow old are you?\nThere are five books on the desk.",
                18, 6, 4));
        days.add(day(7, "Ngày 7: Ôn tập Tuần 1",
                "Ôn phát âm, từ vựng và ngữ pháp tuần 1.",
                "Củng cố chào hỏi, to be, gia đình, trường học và số đếm.",
                "Ôn lại âm /æ/, /ɪ/, /ʌ/ trong cat, sit, sun.",
                "hello, family, school, student, am, is, are, one, two, three",
                "Verb to be", 2,
                "Good morning! How are you?|Chào buổi sáng! Bạn khỏe không?\nI am a student.|Tôi là học sinh.",
                "My family is small.|Gia đình tôi nhỏ.\nI am fine.|Tôi khỏe.",
                "Hello, I am Hung.\nI am a student.\nI love my family.",
                25, 7, 1));
        days.add(day(8, "Ngày 8: Hiện tại đơn – I/You/We/They",
                "Thì hiện tại đơn với chủ ngữ số nhiều và I/you.",
                "Diễn tả thói quen hàng ngày với động từ nguyên mẫu.",
                "live /lɪv/, study /ˈstʌd.i/, play /pleɪ/ – không thêm -s.",
                "live, study, play, eat, drink, go, every day, usually, always",
                "Simple present: I/You/We/They", 7,
                "I live in Hanoi.|Tôi sống ở Hà Nội.\nWe study English.|Chúng tôi học tiếng Anh.\nThey play soccer.|Họ chơi đá bóng.",
                "I eat breakfast at seven.|Tôi ăn sáng lúc bảy giờ.\nYou speak English well.|Bạn nói tiếng Anh tốt.",
                "I live in Vietnam.\nWe study English every day.\nThey play in the park.",
                20, 8, 10));
        days.add(day(9, "Ngày 9: Hiện tại đơn – He/She/It",
                "Chia động từ thêm -s/-es với he/she/it.",
                "Nói về thói quen của người khác đúng ngữ pháp.",
                "works /wɜːrks/, watches /ˈwɒtʃ.ɪz/, goes /ɡoʊz/.",
                "works, watches, goes, likes, drinks, he, she, it, every morning",
                "Simple present: He/She/It", 8,
                "He works in a hospital.|Anh ấy làm ở bệnh viện.\nShe likes music.|Cô ấy thích âm nhạc.\nIt rains a lot.|Trời mưa nhiều.",
                "My father goes to work early.|Bố tôi đi làm sớm.\nShe drinks milk every day.|Cô ấy uống sữa mỗi ngày.",
                "She works in a school.\nHe likes football.\nMy sister watches TV in the evening.",
                20, 9, 10));
        days.add(day(10, "Ngày 10: Thói quen hàng ngày",
                "Mô tả một ngày điển hình bằng tiếng Anh.",
                "Kết hợp thời gian và động từ để nói về routine.",
                "wake up /weɪk ʌp/, breakfast /ˈbrek.fəst/, evening /ˈiːv.nɪŋ/.",
                "wake up, sleep, eat, breakfast, morning, evening, homework, watch, read",
                "Simple present: I/You/We/They", 7,
                "I wake up at six.|Tôi dậy lúc sáu giờ.\nI go to school at seven.|Tôi đi học lúc bảy giờ.",
                "What time do you wake up?|Mấy giờ bạn thức dậy?\nI do homework in the evening.|Tôi làm bài tối.",
                "I wake up at six o'clock.\nI eat breakfast and go to school.\nI do my homework in the evening.",
                20, 10, 10));
        days.add(day(11, "Ngày 11: Đồ ăn & Đồ uống",
                "Từ vựng thực phẩm và gọi món đơn giản.",
                "Biết nói về món ăn yêu thích và gọi đồ uống.",
                "apple /ˈæp.əl/, water /ˈwɔː.tər/, coffee /ˈkɒf.i/.",
                "apple, bread, water, milk, rice, chicken, coffee, tea, juice, eat, drink",
                "Articles: a/an/the", 4,
                "I would like a cup of tea.|Tôi muốn một tách trà.\nThe food is delicious.|Đồ ăn rất ngon.",
                "Can I have some water?|Cho tôi xin nước được không?\nI eat an apple every day.|Tôi ăn một quả táo mỗi ngày.",
                "I like rice and chicken.\nCan I have a cup of tea, please?\nThe food is delicious.",
                20, 11, 8));
        days.add(day(12, "Ngày 12: Thời gian & Lịch trình",
                "Hỏi giờ, nói về lịch học hàng ngày.",
                "Dùng giới từ in/on/at với thời gian.",
                "o'clock /əˈklɒk/, morning /ˈmɔː.nɪŋ/, Monday /ˈmʌn.deɪ/.",
                "morning, afternoon, evening, time, clock, hour, minute, today, tomorrow, week",
                "Basic prepositions: in/on/at", 12,
                "What time is it?|Mấy giờ rồi?\nClass starts at eight.|Lớp bắt đầu lúc tám giờ.\nWe have English on Monday.|Chúng ta học tiếng Anh thứ Hai.",
                "It is three o'clock.|Ba giờ rồi.\nI study in the morning.|Tôi học buổi sáng.",
                "What time is it?\nIt is seven o'clock.\nWe have English on Monday.",
                20, 12, 9));
        days.add(day(13, "Ngày 13: Hội thoại – Giới thiệu bản thân",
                "Ghép từ vựng và ngữ pháp để tự giới thiệu hoàn chỉnh.",
                "Tự tin giới thiệu tên, tuổi, quê quán và gia đình.",
                "Luyện nói liền mạch: My name is... I am... I am from...",
                "name, age, from, live, family, student, like, study, English, Vietnam",
                "Personal pronouns", 1,
                "Hello, my name is Hung.|Xin chào, tên tôi là Hưng.\nI am from Vietnam.|Tôi đến từ Việt Nam.\nNice to meet you.|Rất vui được gặp bạn.",
                "I am a student at this school.|Tôi là học sinh trường này.\nI like English.|Tôi thích tiếng Anh.",
                "Hello, my name is Hung.\nI am twenty years old.\nI am from Vietnam. Nice to meet you.",
                22, 13, 2));
        days.add(day(14, "Ngày 14: Ôn tập A0 & Kiểm tra nói",
                "Tổng ôn khóa A0 và kiểm tra speaking cuối khóa.",
                "Ôn toàn bộ từ vựng, ngữ pháp và hội thoại 14 ngày.",
                "Ôn phát âm các âm khó: /θ/, /ð/, /ŋ/, ending sounds.",
                "hello, family, school, am, is, live, study, like, every day, from",
                "Verb to be", 2,
                "Hello! My name is Hung. I am a student.|Xin chào! Tên tôi là Hưng. Tôi là học sinh.\nI live in Vietnam and I like English.|Tôi sống ở Việt Nam và thích tiếng Anh.",
                "How are you? I am fine.|Bạn khỏe không? Tôi khỏe.\nThank you for studying with me.|Cảm ơn bạn đã học cùng tôi.",
                "Hello, my name is Hung. I am a student.\nI am from Vietnam. I like English.\nNice to meet you!",
                25, 14, 2));
        return days;
    }

    private static LearningDay day(int dayIndex, String title, String objectiveVi, String summaryVi,
                                   String pronunciation, String vocab, String grammar, int grammarId,
                                   String conversation, String listening, String speaking,
                                   int minutes, int order, int convId) {
        return new LearningDay("A0", dayIndex, title, objectiveVi, summaryVi, pronunciation,
                vocab, grammar, grammarId, conversation, listening, speaking,
                minutes, order, convId);
    }
}
