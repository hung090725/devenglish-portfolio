package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.Conversation;
import hung.edu.apptienganh.data.local.entity.ConversationLine;

public final class A1ConversationSeed {

    private A1ConversationSeed() {}

    public static List<Conversation> buildConversations() {
        List<Conversation> list = new ArrayList<>();
        list.add(new Conversation("A1", "Daily routine", "Daily Routine Chat",
                "Hai người bạn nói về thói quen hàng ngày."));
        list.add(new Conversation("A1", "Directions", "Ask for Directions",
                "Du khách hỏi đường đến nhà ga."));
        list.add(new Conversation("A1", "Cafe", "Buy a Drink",
                "Gọi đồ uống tại quán cà phê."));
        list.add(new Conversation("A1", "Restaurant", "At a Restaurant",
                "Đặt món và thanh toán tại nhà hàng."));
        list.add(new Conversation("A1", "Shop", "At a Shop",
                "Mua quần áo tại cửa hàng."));
        list.add(new Conversation("A1", "Hobbies", "Talk About Hobbies",
                "Nói về sở thích giải trí."));
        list.add(new Conversation("A1", "Weather", "Talk About Weather",
                "Thảo luận thời tiết hôm nay."));
        list.add(new Conversation("A1", "Family", "Family Activities",
                "Nói về hoạt động cuối tuần của gia đình."));
        list.add(new Conversation("A1", "Health", "Talk About Health",
                "Hỏi thăm sức khỏe và triệu chứng nhẹ."));
        list.add(new Conversation("A1", "Airport", "Travel at the Airport",
                "Làm thủ tục tại sân bay."));
        list.add(new Conversation("A1", "Hotel", "Book a Hotel Room",
                "Đặt phòng khách sạn qua điện thoại."));
        list.add(new Conversation("A1", "Help", "Ask for Help",
                "Nhờ người khác giúp đỡ khi gặp khó khăn."));
        return list;
    }

    public static List<ConversationLine> buildConversationLines(int conversationIdOffset) {
        List<ConversationLine> lines = new ArrayList<>();
        int c;

        c = conversationIdOffset + 1;
        lines.add(line(c, "A", "What time do you wake up?", "/wʌt taɪm duː juː weɪk ʌp?/", "Mấy giờ bạn thức dậy?", 1));
        lines.add(line(c, "B", "I usually wake up at six.", "/aɪ ˈjuːʒu.ə.li weɪk ʌp æt sɪks./", "Tôi thường dậy lúc sáu giờ.", 2));
        lines.add(line(c, "A", "Do you have breakfast at home?", "/duː juː hæv ˈbrek.fəst æt hoʊm?/", "Bạn ăn sáng ở nhà không?", 3));
        lines.add(line(c, "B", "Yes, I always eat breakfast with my family.", "/jes, aɪ ˈɔːl.weɪz iːt ˈbrek.fəst wɪð maɪ ˈfæm.ə.li./", "Vâng, tôi luôn ăn sáng với gia đình.", 4));
        lines.add(line(c, "A", "What do you do after school?", "/wʌt duː juː duː ˈæf.tər skuːl?/", "Sau giờ học bạn làm gì?", 5));
        lines.add(line(c, "B", "I do homework and sometimes play sports.", "/aɪ duː ˈhoʊm.wɜːrk ænd ˈsʌm.taɪmz pleɪ spɔːrts./", "Tôi làm bài tập và đôi khi chơi thể thao.", 6));
        lines.add(line(c, "A", "That sounds healthy.", "/ðæt saʊndz ˈhel.θi./", "Nghe có vẻ lành mạnh đấy.", 7));
        lines.add(line(c, "B", "Thanks! What about you?", "/θæŋks! wʌt əˈbaʊt juː?/", "Cảm ơn! Còn bạn thì sao?", 8));

        c = conversationIdOffset + 2;
        lines.add(line(c, "A", "Excuse me, where is the train station?", "/ɪkˈskjuːz miː, weər ɪz ðə treɪn ˈsteɪ.ʃən?/", "Xin lỗi, nhà ga ở đâu?", 1));
        lines.add(line(c, "B", "Go straight and turn left at the bank.", "/ɡoʊ streɪt ænd tɜːrn left æt ðə bæŋk./", "Đi thẳng rồi rẽ trái ở ngân hàng.", 2));
        lines.add(line(c, "A", "Is it far from here?", "/ɪz ɪt fɑːr frʌm hɪər?/", "Có xa đây không?", 3));
        lines.add(line(c, "B", "No, it is about ten minutes on foot.", "/noʊ, ɪt ɪz əˈbaʊt ten ˈmɪn.ɪts ɒn fʊt./", "Không, khoảng mười phút đi bộ.", 4));
        lines.add(line(c, "A", "Thank you very much.", "/θæŋk juː ˈver.i mʌtʃ./", "Cảm ơn bạn rất nhiều.", 5));
        lines.add(line(c, "B", "You are welcome. Have a nice day!", "/juː ɑːr ˈwel.kəm. hæv ə naɪs deɪ!/", "Không có gì. Chúc bạn một ngày tốt lành!", 6));
        lines.add(line(c, "A", "Can I take bus number five?", "/kæn aɪ teɪk bʌs ˈnʌm.bər faɪv?/", "Tôi có thể đi xe buýt số năm không?", 7));
        lines.add(line(c, "B", "Yes, the bus stop is next to the bank.", "/jes, ðə bʌs stɒp ɪz nekst tuː ðə bæŋk./", "Có, trạm xe buýt ở cạnh ngân hàng.", 8));

        c = conversationIdOffset + 3;
        lines.add(line(c, "A", "Hello, can I have a latte, please?", "/həˈloʊ, kæn aɪ hæv ə ˈlɑː.teɪ, pliːz?/", "Xin chào, cho tôi một ly latte được không?", 1));
        lines.add(line(c, "B", "Sure. Small, medium, or large?", "/ʃʊr. smɔːl, ˈmiː.di.əm, ɔːr lɑːrdʒ?/", "Được. Size nhỏ, vừa hay lớn?", 2));
        lines.add(line(c, "A", "Medium, please.", "/ˈmiː.di.əm, pliːz./", "Size vừa, làm ơn.", 3));
        lines.add(line(c, "B", "For here or to go?", "/fɔːr hɪər ɔːr tuː ɡoʊ?/", "Uống tại quán hay mang đi?", 4));
        lines.add(line(c, "A", "To go, please.", "/tuː ɡoʊ, pliːz./", "Mang đi, làm ơn.", 5));
        lines.add(line(c, "B", "That will be three dollars.", "/ðæt wɪl biː θriː ˈdɒl.ərz./", "Tổng cộng ba đô la.", 6));
        lines.add(line(c, "A", "Can I pay by card?", "/kæn aɪ peɪ baɪ kɑːrd?/", "Tôi trả bằng thẻ được không?", 7));
        lines.add(line(c, "B", "Yes, of course. Here is your drink.", "/jes, əv kɔːrs. hɪər ɪz jɔːr drɪŋk./", "Được. Đây là đồ uống của bạn.", 8));

        c = conversationIdOffset + 4;
        lines.add(line(c, "A", "A table for two, please.", "/ə ˈteɪ.bəl fɔːr tuː, pliːz./", "Cho tôi bàn cho hai người.", 1));
        lines.add(line(c, "B", "This way, please. Here are the menus.", "/ðɪs weɪ, pliːz. hɪər ɑːr ðə ˈmen.juːz./", "Mời đi lối này. Đây là thực đơn.", 2));
        lines.add(line(c, "A", "I would like chicken soup and a salad.", "/aɪ wʊd laɪk ˈtʃɪk.ɪn suːp ænd ə ˈsæl.əd./", "Tôi muốn súp gà và salad.", 3));
        lines.add(line(c, "B", "Anything to drink?", "/ˈeni.θɪŋ tuː drɪŋk?/", "Bạn uống gì không?", 4));
        lines.add(line(c, "A", "Just water, please.", "/dʒʌst ˈwɔː.tər, pliːz./", "Chỉ nước lọc thôi.", 5));
        lines.add(line(c, "B", "Your food will be ready soon.", "/jɔːr fuːd wɪl biː ˈred.i suːn./", "Món ăn sẽ sẵn sàng sớm.", 6));
        lines.add(line(c, "A", "Can I have the bill, please?", "/kæn aɪ hæv ðə bɪl, pliːz?/", "Cho tôi hóa đơn được không?", 7));
        lines.add(line(c, "B", "Of course. Thank you for coming.", "/əv kɔːrs. θæŋk juː fɔːr ˈkʌm.ɪŋ./", "Vâng. Cảm ơn bạn đã ghé.", 8));

        c = conversationIdOffset + 5;
        lines.add(line(c, "A", "How much is this shirt?", "/haʊ mʌtʃ ɪz ðɪs ʃɜːrt?/", "Áo sơ mi này bao nhiêu tiền?", 1));
        lines.add(line(c, "B", "It is twenty-five dollars.", "/ɪt ɪz ˈtwen.ti faɪv ˈdɒl.ərz./", "Hai mươi lăm đô la.", 2));
        lines.add(line(c, "A", "Do you have it in blue?", "/duː juː hæv ɪt ɪn bluː?/", "Có màu xanh dương không?", 3));
        lines.add(line(c, "B", "Yes, here you are. What size do you need?", "/jes, hɪər juː ɑːr. wʌt saɪz duː juː niːd?/", "Có, đây ạ. Bạn cần size nào?", 4));
        lines.add(line(c, "A", "Medium, please.", "/ˈmiː.di.əm, pliːz./", "Size M, làm ơn.", 5));
        lines.add(line(c, "B", "The fitting room is over there.", "/ðə ˈfɪt.ɪŋ ruːm ɪz ˈoʊ.vər ðeər./", "Phòng thử ở kia.", 6));
        lines.add(line(c, "A", "It fits well. I will take it.", "/ɪt fɪts wel. aɪ wɪl teɪk ɪt./", "Vừa đẹp. Tôi lấy áo này.", 7));
        lines.add(line(c, "B", "Great! Cash or card?", "/ɡreɪt! kæʃ ɔːr kɑːrd?/", "Tuyệt! Tiền mặt hay thẻ?", 8));

        c = conversationIdOffset + 6;
        lines.add(line(c, "A", "What do you like to do in your free time?", "/wʌt duː juː laɪk tuː duː ɪn jɔːr friː taɪm?/", "Lúc rảnh bạn thích làm gì?", 1));
        lines.add(line(c, "B", "I like reading and playing guitar.", "/aɪ laɪk ˈriː.dɪŋ ænd ˈpleɪ.ɪŋ ɡɪˈtɑːr./", "Tôi thích đọc sách và chơi guitar.", 2));
        lines.add(line(c, "A", "That is cool! How often do you play?", "/ðæt ɪz kuːl! haʊ ˈɒf.ən duː juː pleɪ?/", "Hay đấy! Bạn chơi thường xuyên không?", 3));
        lines.add(line(c, "B", "I practice three times a week.", "/aɪ ˈpræk.tɪs θriː taɪmz ə wiːk./", "Tôi luyện ba lần một tuần.", 4));
        lines.add(line(c, "A", "Do you like sports too?", "/duː juː laɪk spɔːrts tuː?/", "Bạn cũng thích thể thao không?", 5));
        lines.add(line(c, "B", "Yes, I sometimes play badminton.", "/jes, aɪ ˈsʌm.taɪmz pleɪ ˈbæd.mɪn.tən./", "Có, đôi khi tôi chơi cầu lông.", 6));
        lines.add(line(c, "A", "We should play together sometime.", "/wiː ʃʊd pleɪ təˈɡeð.ər ˈsʌm.taɪm./", "Chúng ta nên chơi cùng nhau lúc nào đó.", 7));
        lines.add(line(c, "B", "Good idea!", "/ɡʊd aɪˈdɪə!/", "Ý hay!", 8));

        c = conversationIdOffset + 7;
        lines.add(line(c, "A", "It is very hot today, isn't it?", "/ɪt ɪz ˈver.i hɒt təˈdeɪ, ˈɪz.ənt ɪt?/", "Hôm nay nóng quá, phải không?", 1));
        lines.add(line(c, "B", "Yes, the weather is terrible.", "/jes, ðə ˈweð.ər ɪz ˈter.ə.bəl./", "Vâng, thời tiết tệ thật.", 2));
        lines.add(line(c, "A", "I think it will rain this afternoon.", "/aɪ θɪŋk ɪt wɪl reɪn ðɪs ˌæf.tərˈnuːn./", "Tôi nghĩ chiều nay sẽ mưa.", 3));
        lines.add(line(c, "B", "Really? I forgot my umbrella.", "/ˈrɪə.li? aɪ fərˈɡɒt maɪ ʌmˈbrel.ə./", "Thật à? Tôi quên mang ô.", 4));
        lines.add(line(c, "A", "You can borrow mine if you need.", "/juː kæn ˈbɒr.oʊ maɪn ɪf juː niːd./", "Cần thì mượn ô của tôi nhé.", 5));
        lines.add(line(c, "B", "Thank you! What is the temperature now?", "/θæŋk juː! wʌt ɪz ðə ˈtem.prə.tʃər naʊ?/", "Cảm ơn! Bây giờ nhiệt độ bao nhiêu?", 6));
        lines.add(line(c, "A", "It is about thirty-five degrees.", "/ɪt ɪz əˈbaʊt ˈθɜːr.ti faɪv dɪˈɡriːz./", "Khoảng ba mươi lăm độ.", 7));
        lines.add(line(c, "B", "No wonder I feel tired.", "/noʊ ˈwʌn.dər aɪ fiːl ˈtaɪərd./", "Không lạ gì tôi thấy mệt.", 8));

        c = conversationIdOffset + 8;
        lines.add(line(c, "A", "What does your family do on weekends?", "/wʌt dʌz jɔːr ˈfæm.ə.li duː ɒn ˈwiːk.endz?/", "Cuối tuần gia đình bạn làm gì?", 1));
        lines.add(line(c, "B", "We usually visit my grandparents.", "/wiː ˈjuːʒu.ə.li ˈvɪz.ɪt maɪ ˈɡræn.peə.rənts./", "Chúng tôi thường thăm ông bà.", 2));
        lines.add(line(c, "A", "That is nice. Do you cook together?", "/ðæt ɪz naɪs. duː juː kʊk təˈɡeð.ər?/", "Hay đấy. Các bạn nấu ăn cùng nhau không?", 3));
        lines.add(line(c, "B", "Yes, my mother and I cook lunch.", "/jes, maɪ ˈmʌð.ər ænd aɪ kʊk lʌntʃ./", "Có, mẹ và tôi nấu bữa trưa.", 4));
        lines.add(line(c, "A", "What do you usually make?", "/wʌt duː juː ˈjuːʒu.ə.li meɪk?/", "Thường nấu món gì?", 5));
        lines.add(line(c, "B", "We often make pho and spring rolls.", "/wiː ˈɒf.ən meɪk fəʊ ænd sprɪŋ roʊlz./", "Chúng tôi hay làm phở và gỏi cuốn.", 6));
        lines.add(line(c, "A", "Sounds delicious!", "/saʊndz dɪˈlɪʃ.əs!/", "Nghe ngon quá!", 7));
        lines.add(line(c, "B", "You should come next Sunday.", "/juː ʃʊd kʌm nekst ˈsʌn.deɪ./", "Bạn nên sang Chủ nhật tới nhé.", 8));

        c = conversationIdOffset + 9;
        lines.add(line(c, "A", "You look tired. Are you okay?", "/juː lʊk ˈtaɪərd. ɑːr juː oʊˈkeɪ?/", "Bạn trông mệt. Ổn không?", 1));
        lines.add(line(c, "B", "I have a headache and a sore throat.", "/aɪ hæv ə ˈhed.eɪk ænd ə sɔːr θroʊt./", "Tôi đau đầu và đau họng.", 2));
        lines.add(line(c, "A", "Did you sleep well last night?", "/dɪd juː sliːp wel lɑːst naɪt?/", "Tối qua bạn ngủ ngon không?", 3));
        lines.add(line(c, "B", "Not really. I went to bed very late.", "/nɒt ˈrɪə.li. aɪ went tuː bed ˈver.i leɪt./", "Không hẳn. Tôi đi ngủ rất muộn.", 4));
        lines.add(line(c, "A", "You should drink more water and rest.", "/juː ʃʊd drɪŋk mɔːr ˈwɔː.tər ænd rest./", "Bạn nên uống nhiều nước và nghỉ ngơi.", 5));
        lines.add(line(c, "B", "Maybe I need to see a doctor.", "/ˈmeɪ.bi aɪ niːd tuː siː ə ˈdɒk.tər./", "Có lẽ tôi cần đi khám bác sĩ.", 6));
        lines.add(line(c, "A", "There is a clinic near the school.", "/ðeər ɪz ə ˈklɪn.ɪk nɪər ðə skuːl./", "Có phòng khám gần trường.", 7));
        lines.add(line(c, "B", "Thanks for caring about me.", "/θæŋks fɔːr ˈkeər.ɪŋ əˈbaʊt miː./", "Cảm ơn bạn quan tâm.", 8));

        c = conversationIdOffset + 10;
        lines.add(line(c, "A", "Good morning. May I see your passport, please?", "/ɡʊd ˈmɔːr.nɪŋ. meɪ aɪ siː jɔːr ˈpæs.pɔːrt, pliːz?/", "Chào buổi sáng. Cho tôi xem hộ chiếu.", 1));
        lines.add(line(c, "B", "Here you are.", "/hɪər juː ɑːr./", "Đây ạ.", 2));
        lines.add(line(c, "A", "Where are you flying today?", "/weər ɑːr juː ˈflaɪ.ɪŋ təˈdeɪ?/", "Hôm nay bạn bay đi đâu?", 3));
        lines.add(line(c, "B", "I am flying to Singapore.", "/aɪ æm ˈflaɪ.ɪŋ tuː ˈsɪŋ.ə.pɔːr./", "Tôi bay đến Singapore.", 4));
        lines.add(line(c, "A", "Do you have any checked bags?", "/duː juː hæv ˈeni tʃekt bæɡz?/", "Bạn có hành lý ký gửi không?", 5));
        lines.add(line(c, "B", "Yes, one suitcase.", "/jes, wʌn ˈsuːt.keɪs./", "Có, một vali.", 6));
        lines.add(line(c, "A", "Please put it on the scale.", "/pliːz pʊt ɪt ɒn ðə skeɪl./", "Vui lòng đặt lên cân.", 7));
        lines.add(line(c, "B", "Is my gate number on the ticket?", "/ɪz maɪ ɡeɪt ˈnʌm.bər ɒn ðə ˈtɪk.ɪt?/", "Số cổng có trên vé không?", 8));

        c = conversationIdOffset + 11;
        lines.add(line(c, "A", "Hello, I would like to book a room for two nights.", "/həˈloʊ, aɪ wʊd laɪk tuː bʊk ə ruːm fɔːr tuː naɪts./", "Xin chào, tôi muốn đặt phòng hai đêm.", 1));
        lines.add(line(c, "B", "Single or double room?", "/ˈsɪŋ.ɡəl ɔːr ˈdʌb.əl ruːm?/", "Phòng đơn hay đôi?", 2));
        lines.add(line(c, "A", "A double room with breakfast, please.", "/ə ˈdʌb.əl ruːm wɪð ˈbrek.fəst, pliːz./", "Phòng đôi có bữa sáng, làm ơn.", 3));
        lines.add(line(c, "B", "Check-in is at two p.m.", "/tʃek ɪn ɪz æt tuː piː em./", "Nhận phòng lúc hai giờ chiều.", 4));
        lines.add(line(c, "A", "Does the room have free Wi-Fi?", "/dʌz ðə ruːm hæv friː ˈwaɪ.faɪ?/", "Phòng có Wi-Fi miễn phí không?", 5));
        lines.add(line(c, "B", "Yes, and there is a gym on the third floor.", "/jes, ænd ðeər ɪz ə dʒɪm ɒn ðə θɜːrd flɔːr./", "Có, và có phòng gym ở tầng ba.", 6));
        lines.add(line(c, "A", "Perfect. What is the total price?", "/ˈpɜːr.fekt. wʌt ɪz ðə ˈtoʊ.təl praɪs?/", "Tuyệt. Tổng giá bao nhiêu?", 7));
        lines.add(line(c, "B", "It is one hundred twenty dollars.", "/ɪt ɪz wʌn ˈhʌn.drəd ˈtwen.ti ˈdɒl.ərz./", "Một trăm hai mươi đô la.", 8));

        c = conversationIdOffset + 12;
        lines.add(line(c, "A", "Excuse me, can you help me?", "/ɪkˈskjuːz miː, kæn juː help miː?/", "Xin lỗi, bạn giúp tôi được không?", 1));
        lines.add(line(c, "B", "Of course. What is the problem?", "/əv kɔːrs. wʌt ɪz ðə ˈprɒb.ləm?/", "Được. Vấn đề là gì?", 2));
        lines.add(line(c, "A", "I cannot find my wallet.", "/aɪ ˈkæn.ɒt faɪnd maɪ ˈwɒl.ɪt./", "Tôi không tìm thấy ví.", 3));
        lines.add(line(c, "B", "Where did you last see it?", "/weər dɪd juː lɑːst siː ɪt?/", "Lần cuối bạn thấy nó ở đâu?", 4));
        lines.add(line(c, "A", "Maybe at the coffee shop near here.", "/ˈmeɪ.bi æt ðə ˈkɒf.i ʃɒp nɪər hɪər./", "Có lẽ ở quán cà phê gần đây.", 5));
        lines.add(line(c, "B", "Let us go and ask the staff.", "/let ʌs ɡoʊ ænd ɑːsk ðə stɑːf./", "Chúng ta đi hỏi nhân viên nhé.", 6));
        lines.add(line(c, "A", "I am really worried.", "/aɪ æm ˈrɪə.li ˈwʌr.id./", "Tôi lo lắng quá.", 7));
        lines.add(line(c, "B", "Don't worry. I will help you.", "/doʊnt ˈwʌr.i. aɪ wɪl help juː./", "Đừng lo. Tôi sẽ giúp bạn.", 8));

        return lines;
    }

    private static ConversationLine line(int convId, String role, String en, String ipa, String vi, int order) {
        return new ConversationLine(convId, role, en, ipa, vi, order);
    }
}
