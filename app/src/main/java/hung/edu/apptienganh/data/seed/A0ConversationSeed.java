package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.Conversation;
import hung.edu.apptienganh.data.local.entity.ConversationLine;

public final class A0ConversationSeed {

    private A0ConversationSeed() {}

    public static List<Conversation> buildConversations() {
        List<Conversation> list = new ArrayList<>();
        list.add(new Conversation("A0", "Greeting", "Greeting", "Hai người bạn gặp nhau buổi sáng và chào hỏi."));
        list.add(new Conversation("A0", "Self introduction", "Introduce Yourself", "Tự giới thiệu bản thân với bạn mới ở lớp."));
        list.add(new Conversation("A0", "Ask name", "Asking Name", "Hỏi và xác nhận tên người đối diện."));
        list.add(new Conversation("A0", "Ask age", "Asking Age", "Hỏi tuổi và trả lời lịch sự."));
        list.add(new Conversation("A0", "Nationality", "Where Are You From?", "Hỏi quê quán và quốc tịch."));
        list.add(new Conversation("A0", "Family", "Talking About Family", "Giới thiệu các thành viên trong gia đình."));
        list.add(new Conversation("A0", "School", "Talking About School", "Nói về trường học và môn học yêu thích."));
        list.add(new Conversation("A0", "Food", "Order a Drink", "Gọi đồ uống tại quán cà phê."));
        list.add(new Conversation("A0", "Time", "Ask the Time", "Hỏi giờ và trả lời."));
        list.add(new Conversation("A0", "Daily routine", "Daily Routine", "Nói về thói quen hàng ngày."));
        return list;
    }

    public static List<ConversationLine> buildConversationLines() {
        List<ConversationLine> lines = new ArrayList<>();

        // 1 Greeting
        lines.add(line(1, "A", "Hello! Good morning.", "/həˈloʊ! ɡʊd ˈmɔːr.nɪŋ./", "Xin chào! Chào buổi sáng.", 1));
        lines.add(line(1, "B", "Hi! Good morning. How are you?", "/haɪ! ɡʊd ˈmɔːr.nɪŋ. haʊ ɑːr juː?/", "Chào! Chào buổi sáng. Bạn khỏe không?", 2));
        lines.add(line(1, "A", "I am fine, thank you. And you?", "/aɪ æm faɪn, θæŋk juː. ænd juː?/", "Tôi khỏe, cảm ơn. Còn bạn?", 3));
        lines.add(line(1, "B", "I am good too. Nice to meet you.", "/aɪ æm ɡʊd tuː. naɪs tuː miːt juː./", "Tôi cũng khỏe. Rất vui được gặp bạn.", 4));
        lines.add(line(1, "A", "Nice to meet you too.", "/naɪs tuː miːt juː tuː./", "Tôi cũng rất vui được gặp bạn.", 5));
        lines.add(line(1, "B", "See you later. Goodbye!", "/siː juː ˈleɪ.tər. ɡʊdˈbaɪ!/", "Hẹn gặp lại. Tạm biệt!", 6));

        // 2 Introduce yourself
        lines.add(line(2, "A", "Hello, my name is Hung.", "/həˈloʊ, maɪ neɪm ɪz hʌŋ./", "Xin chào, tên tôi là Hưng.", 1));
        lines.add(line(2, "B", "Hi Hung, I am Mary. Nice to meet you.", "/haɪ hʌŋ, aɪ æm ˈmeə.ri. naɪs tuː miːt juː./", "Chào Hưng, tôi là Mary. Rất vui được gặp bạn.", 2));
        lines.add(line(2, "A", "Nice to meet you too, Mary.", "/naɪs tuː miːt juː tuː, ˈmeə.ri./", "Tôi cũng rất vui được gặp bạn, Mary.", 3));
        lines.add(line(2, "B", "Are you a new student here?", "/ɑːr juː ə njuː ˈstjuː.dənt hɪər?/", "Bạn là học sinh mới ở đây phải không?", 4));
        lines.add(line(2, "A", "Yes, I am. I am in Class 6A.", "/jes, aɪ æm. aɪ æm ɪn klɑːs sɪks eɪ./", "Vâng, tôi là học sinh mới. Tôi học lớp 6A.", 5));
        lines.add(line(2, "B", "Great! Let us study together.", "/ɡreɪt! let ʌs ˈstʌd.i təˈɡeð.ər./", "Tuyệt! Chúng ta cùng học nhé.", 6));

        // 3 Ask name
        lines.add(line(3, "A", "Excuse me, what is your name?", "/ɪkˈskjuːz miː, wʌt ɪz jɔːr neɪm?/", "Xin lỗi, tên bạn là gì?", 1));
        lines.add(line(3, "B", "My name is John. What is yours?", "/maɪ neɪm ɪz dʒɒn. wʌt ɪz jɔːrz?/", "Tên tôi là John. Còn bạn?", 2));
        lines.add(line(3, "A", "My name is Linh.", "/maɪ neɪm ɪz lɪŋ./", "Tên tôi là Linh.", 3));
        lines.add(line(3, "B", "Nice name! How do you spell it?", "/naɪs neɪm! haʊ duː juː spel ɪt?/", "Tên đẹp! Bạn đánh vần thế nào?", 4));
        lines.add(line(3, "A", "L-I-N-H.", "/el-aɪ-en-eɪtʃ./", "L - I - N - H.", 5));
        lines.add(line(3, "B", "Thank you, Linh.", "/θæŋk juː, lɪŋ./", "Cảm ơn bạn, Linh.", 6));

        // 4 Ask age
        lines.add(line(4, "A", "How old are you, Anna?", "/haʊ oʊld ɑːr juː, ˈæn.ə?/", "Bạn bao nhiêu tuổi, Anna?", 1));
        lines.add(line(4, "B", "I am twelve years old.", "/aɪ æm twelv jɪərz oʊld./", "Tôi mười hai tuổi.", 2));
        lines.add(line(4, "A", "When is your birthday?", "/wen ɪz jɔːr ˈbɜːrθ.deɪ?/", "Sinh nhật bạn là ngày nào?", 3));
        lines.add(line(4, "B", "My birthday is in May.", "/maɪ ˈbɜːrθ.deɪ ɪz ɪn meɪ./", "Sinh nhật tôi vào tháng Năm.", 4));
        lines.add(line(4, "A", "That is a nice month.", "/ðæt ɪz ə naɪs mʌnθ./", "Tháng đó đẹp đấy.", 5));
        lines.add(line(4, "B", "Thank you! How old are you?", "/θæŋk juː! haʊ oʊld ɑːr juː?/", "Cảm ơn! Còn bạn bao nhiêu tuổi?", 6));

        // 5 Nationality
        lines.add(line(5, "A", "Where are you from, Tom?", "/weər ɑːr juː frɒm, tɒm?/", "Bạn đến từ đâu, Tom?", 1));
        lines.add(line(5, "B", "I am from America. I live in New York.", "/aɪ æm frɒm əˈmer.ɪ.kə. aɪ lɪv ɪn njuː jɔːrk./", "Tôi đến từ Mỹ. Tôi sống ở New York.", 2));
        lines.add(line(5, "A", "I am from Vietnam. I live in Hanoi.", "/aɪ æm frɒm ˌvjetˈnæm. aɪ lɪv ɪn hæˈnɔɪ./", "Tôi đến từ Việt Nam. Tôi sống ở Hà Nội.", 3));
        lines.add(line(5, "B", "I love Vietnam. The food is delicious.", "/aɪ lʌv ˌvjetˈnæm. ðə fuːd ɪz dɪˈlɪʃ.əs./", "Tôi yêu Việt Nam. Đồ ăn rất ngon.", 4));
        lines.add(line(5, "A", "Thank you! Welcome to Vietnam.", "/θæŋk juː! ˈwel.kəm tuː ˌvjetˈnæm./", "Cảm ơn! Chào mừng đến Việt Nam.", 5));
        lines.add(line(5, "B", "I hope to visit again soon.", "/aɪ hoʊp tuː ˈvɪz.ɪt əˈɡen suːn./", "Tôi hy vọng sớm quay lại.", 6));

        // 6 Family
        lines.add(line(6, "A", "How many people are in your family?", "/haʊ ˈmen.i ˈpiː.pəl ɑːr ɪn jɔːr ˈfæm.ə.li?/", "Gia đình bạn có mấy người?", 1));
        lines.add(line(6, "B", "There are four people in my family.", "/ðeər ɑːr fɔːr ˈpiː.pəl ɪn maɪ ˈfæm.ə.li./", "Gia đình tôi có bốn người.", 2));
        lines.add(line(6, "A", "Who are they?", "/huː ɑːr ðeɪ?/", "Họ là ai?", 3));
        lines.add(line(6, "B", "My father, my mother, my sister and me.", "/maɪ ˈfɑː.ðər, maɪ ˈmʌð.ər, maɪ ˈsɪs.tər ænd miː./", "Bố, mẹ, chị gái và tôi.", 4));
        lines.add(line(6, "A", "Your family sounds lovely.", "/jɔːr ˈfæm.ə.li saʊndz ˈlʌv.li./", "Gia đình bạn nghe thật đáng yêu.", 5));
        lines.add(line(6, "B", "Thank you. I love my family.", "/θæŋk juː. aɪ lʌv maɪ ˈfæm.ə.li./", "Cảm ơn. Tôi yêu gia đình mình.", 6));

        // 7 School
        lines.add(line(7, "A", "What is your favorite subject?", "/wʌt ɪz jɔːr ˈfeɪ.vər.ɪt ˈsʌb.dʒekt?/", "Môn học yêu thích của bạn là gì?", 1));
        lines.add(line(7, "B", "My favorite subject is English.", "/maɪ ˈfeɪ.vər.ɪt ˈsʌb.dʒekt ɪz ˈɪŋ.ɡlɪʃ./", "Môn yêu thích của tôi là tiếng Anh.", 2));
        lines.add(line(7, "A", "Me too! English is fun.", "/miː tuː! ˈɪŋ.ɡlɪʃ ɪz fʌn./", "Tôi cũng vậy! Tiếng Anh rất vui.", 3));
        lines.add(line(7, "B", "Our teacher is very kind.", "/aʊər ˈtiː.tʃər ɪz ˈver.i kaɪnd./", "Giáo viên của chúng ta rất tốt bụng.", 4));
        lines.add(line(7, "A", "Yes, she helps us a lot.", "/jes, ʃiː helps ʌs ə lɒt./", "Vâng, cô ấy giúp chúng ta rất nhiều.", 5));
        lines.add(line(7, "B", "Let us study hard together.", "/let ʌs ˈstʌd.i hɑːrd təˈɡeð.ər./", "Chúng ta cùng học chăm chỉ nhé.", 6));

        // 8 Order a drink
        lines.add(line(8, "A", "Hello. Can I have a cup of tea, please?", "/həˈloʊ. kæn aɪ hæv ə kʌp ɒv tiː, pliːz?/", "Xin chào. Cho tôi một tách trà được không?", 1));
        lines.add(line(8, "B", "Sure. Hot tea or iced tea?", "/ʃʊər. hɒt tiː ɔːr aɪst tiː?/", "Dạ. Trà nóng hay trà đá?", 2));
        lines.add(line(8, "A", "Hot tea, please.", "/hɒt tiː, pliːz./", "Trà nóng, làm ơn.", 3));
        lines.add(line(8, "B", "Would you like sugar?", "/wʊd juː laɪk ˈʃʊɡ.ər?/", "Bạn có muốn đường không?", 4));
        lines.add(line(8, "A", "No, thank you. Just tea.", "/noʊ, θæŋk juː. dʒʌst tiː./", "Không, cảm ơn. Chỉ trà thôi.", 5));
        lines.add(line(8, "B", "Here you are. Enjoy!", "/hɪər juː ɑːr. ɪnˈdʒɔɪ!/", "Đây ạ. Chúc bạn ngon miệng!", 6));

        // 9 Ask the time
        lines.add(line(9, "A", "Excuse me, what time is it?", "/ɪkˈskjuːz miː, wʌt taɪm ɪz ɪt?/", "Xin lỗi, mấy giờ rồi?", 1));
        lines.add(line(9, "B", "It is three o'clock.", "/ɪt ɪz θriː əˈklɒk./", "Ba giờ rồi.", 2));
        lines.add(line(9, "A", "Thank you. Is the class at four?", "/θæŋk juː. ɪz ðə klɑːs æt fɔːr?/", "Cảm ơn. Lớp học lúc bốn giờ phải không?", 3));
        lines.add(line(9, "B", "Yes, the class starts at four.", "/jes, ðə klɑːs stɑːrts æt fɔːr./", "Vâng, lớp bắt đầu lúc bốn giờ.", 4));
        lines.add(line(9, "A", "Great. I am not late.", "/ɡreɪt. aɪ æm nɒt leɪt./", "Tốt quá. Tôi chưa muộn.", 5));
        lines.add(line(9, "B", "See you in class.", "/siː juː ɪn klɑːs./", "Hẹn gặp trong lớp.", 6));

        // 10 Daily routine
        lines.add(line(10, "A", "What time do you wake up?", "/wʌt taɪm duː juː weɪk ʌp?/", "Bạn thức dậy lúc mấy giờ?", 1));
        lines.add(line(10, "B", "I wake up at six o'clock.", "/aɪ weɪk ʌp æt sɪks əˈklɒk./", "Tôi thức dậy lúc sáu giờ.", 2));
        lines.add(line(10, "A", "What do you do in the morning?", "/wʌt duː juː duː ɪn ðə ˈmɔːr.nɪŋ?/", "Buổi sáng bạn làm gì?", 3));
        lines.add(line(10, "B", "I eat breakfast and go to school.", "/aɪ iːt ˈbrek.fəst ænd ɡoʊ tuː skuːl./", "Tôi ăn sáng và đi học.", 4));
        lines.add(line(10, "A", "When do you do your homework?", "/wen duː juː duː jɔːr ˈhoʊm.wɝːk?/", "Khi nào bạn làm bài tập?", 5));
        lines.add(line(10, "B", "I do my homework in the evening.", "/aɪ duː maɪ ˈhoʊm.wɝːk ɪn ði ˈiːv.nɪŋ./", "Tôi làm bài tập vào buổi tối.", 6));

        return lines;
    }

    private static ConversationLine line(int convId, String role, String en, String ipa, String vi, int order) {
        return new ConversationLine(convId, role, en, ipa, vi, order);
    }
}
