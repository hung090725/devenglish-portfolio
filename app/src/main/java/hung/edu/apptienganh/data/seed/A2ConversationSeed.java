package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.Conversation;
import hung.edu.apptienganh.data.local.entity.ConversationLine;

public final class A2ConversationSeed {

    private A2ConversationSeed() {}

    public static List<Conversation> buildConversations() {
        List<Conversation> list = new ArrayList<>();
        list.add(new Conversation("A2", "Past events", "Talking About Last Weekend",
                "Hai người bạn kể về những gì họ đã làm cuối tuần trước."));
        list.add(new Conversation("A2", "Future plans", "Planning a Trip",
                "Hai người bạn lên kế hoạch cho chuyến đi nghỉ hè sắp tới."));
        list.add(new Conversation("A2", "Social", "Making an Invitation",
                "Mời bạn bè đến bữa tiệc sinh nhật."));
        list.add(new Conversation("A2", "Hotel problem", "At a Hotel With a Problem",
                "Khách tại khách sạn phàn nàn về phòng có vấn đề."));
        list.add(new Conversation("A2", "Shopping", "Returning an Item at a Shop",
                "Khách đến cửa hàng để đổi trả sản phẩm bị lỗi."));
        list.add(new Conversation("A2", "Health", "Asking for Medical Advice",
                "Người bệnh hỏi bác sĩ về triệu chứng và cách điều trị."));
        list.add(new Conversation("A2", "Study", "Talking About Study Plans",
                "Hai sinh viên nói về kế hoạch học tập trong học kỳ tới."));
        list.add(new Conversation("A2", "Comparisons", "Comparing Two Phones",
                "Hai người bạn so sánh hai chiếc điện thoại để quyết định mua cái nào."));
        list.add(new Conversation("A2", "Advice", "Giving Advice to a Friend",
                "Một người bạn đang gặp khó khăn và nhờ lời khuyên."));
        list.add(new Conversation("A2", "Future goals", "Talking About Future Goals",
                "Hai người nói về kế hoạch nghề nghiệp và ước mơ tương lai."));
        list.add(new Conversation("A2", "Emergency", "Reporting a Lost Item",
                "Hành khách báo cáo mất đồ tại sân bay."));
        list.add(new Conversation("A2", "Restaurant", "Ordering Food With a Problem",
                "Khách phàn nàn về món ăn không đúng yêu cầu tại nhà hàng."));
        list.add(new Conversation("A2", "Airport", "Asking for Help at the Airport",
                "Du khách cần giúp đỡ khi gặp vấn đề với chuyến bay."));
        list.add(new Conversation("A2", "Hobbies", "Discussing Hobbies and Opinions",
                "Hai người chia sẻ và so sánh sở thích cá nhân."));
        return list;
    }

    public static List<ConversationLine> buildConversationLines(int conversationIdOffset) {
        List<ConversationLine> lines = new ArrayList<>();
        int c;

        // Conv 1: Talking About Last Weekend
        c = conversationIdOffset + 1;
        lines.add(line(c, "A", "Hey, what did you do last weekend?", "/heɪ, wʌt dɪd juː duː lɑːst ˈwiːk.end?/", "Này, cuối tuần trước bạn đã làm gì?", 1));
        lines.add(line(c, "B", "I went to the beach with my family.", "/aɪ went tuː ðə biːtʃ wɪð maɪ ˈfæm.ə.li./", "Tôi đã đi biển cùng gia đình.", 2));
        lines.add(line(c, "A", "That sounds amazing! Did you swim?", "/ðæt saʊndz əˈmeɪ.zɪŋ! dɪd juː swɪm?/", "Nghe tuyệt quá! Bạn có bơi không?", 3));
        lines.add(line(c, "B", "Yes, and we ate fresh seafood at a local restaurant.", "/jes, ænd wiː eɪt freʃ ˈsiː.fuːd æt ə ˈloʊ.kəl ˈres.tɚ.ɑːnt./", "Có, và chúng tôi ăn hải sản tươi ở nhà hàng địa phương.", 4));
        lines.add(line(c, "A", "What about the weather? Was it sunny?", "/wʌt əˈbaʊt ðə ˈweð.ər? wɒz ɪt ˈsʌn.i?/", "Thời tiết thế nào? Có nắng không?", 5));
        lines.add(line(c, "B", "It was perfect – sunny and warm.", "/ɪt wɒz ˈpɜː.fɪkt – ˈsʌn.i ænd wɔːrm./", "Hoàn hảo – nắng và ấm.", 6));
        lines.add(line(c, "A", "I stayed home and watched films.", "/aɪ steɪd hoʊm ænd wɒtʃt fɪlmz./", "Tôi ở nhà và xem phim.", 7));
        lines.add(line(c, "B", "You should come with us next time!", "/juː ʃʊd kʌm wɪð ʌs nekst taɪm!/", "Lần sau bạn đi cùng chúng tôi nhé!", 8));

        // Conv 2: Planning a Trip
        c = conversationIdOffset + 2;
        lines.add(line(c, "A", "I am thinking of going somewhere this summer.", "/aɪ æm ˈθɪŋ.kɪŋ əv ˈɡoʊ.ɪŋ ˈsʌm.weər ðɪs ˈsʌm.ər./", "Tôi đang nghĩ đến việc đi đâu đó hè này.", 1));
        lines.add(line(c, "B", "Where are you planning to go?", "/weər ɑːr juː ˈplæn.ɪŋ tuː ɡoʊ?/", "Bạn định đi đâu?", 2));
        lines.add(line(c, "A", "I am going to visit Da Nang with my friends.", "/aɪ æm ˈɡoʊ.ɪŋ tuː ˈvɪz.ɪt dɑː næŋ wɪð maɪ frendz./", "Tôi sẽ đến thăm Đà Nẵng cùng bạn bè.", 3));
        lines.add(line(c, "B", "How long are you going to stay?", "/haʊ lɒŋ ɑːr juː ˈɡoʊ.ɪŋ tuː steɪ?/", "Bạn định ở lại bao lâu?", 4));
        lines.add(line(c, "A", "About four days. We will go sightseeing and try local food.", "/əˈbaʊt fɔːr deɪz. wiː wɪl ɡoʊ ˈsaɪt.siː.ɪŋ ænd traɪ ˈloʊ.kəl fuːd./", "Khoảng bốn ngày. Chúng tôi sẽ tham quan và thử đồ ăn địa phương.", 5));
        lines.add(line(c, "B", "Have you booked your hotel yet?", "/hæv juː bʊkt jɔːr hoʊˈtel jet?/", "Bạn đã đặt khách sạn chưa?", 6));
        lines.add(line(c, "A", "Not yet. I am going to search for a good deal online.", "/nɒt jet. aɪ æm ˈɡoʊ.ɪŋ tuː sɜːrtʃ fɔːr ə ɡʊd diːl ˈɒn.laɪn./", "Chưa. Tôi sẽ tìm khuyến mãi tốt trên mạng.", 7));
        lines.add(line(c, "B", "Great idea! I hope you have a wonderful trip.", "/ɡreɪt aɪˈdɪə! aɪ hoʊp juː hæv ə ˈwʌn.dər.fəl trɪp./", "Ý hay! Tôi mong bạn có chuyến đi tuyệt vời.", 8));

        // Conv 3: Making an Invitation
        c = conversationIdOffset + 3;
        lines.add(line(c, "A", "Hi! Would you like to come to my birthday party on Saturday?", "/haɪ! wʊd juː laɪk tuː kʌm tuː maɪ ˈbɜːrθ.deɪ ˈpɑːr.ti ɒn ˈsæt.ər.deɪ?/", "Chào! Bạn có muốn đến dự sinh nhật của tôi thứ Bảy không?", 1));
        lines.add(line(c, "B", "That sounds fun! What time does it start?", "/ðæt saʊndz fʌn! wʌt taɪm dʌz ɪt stɑːrt?/", "Nghe vui quá! Mấy giờ bắt đầu?", 2));
        lines.add(line(c, "A", "It starts at six o'clock in the evening.", "/ɪt stɑːrts æt sɪks əˈklɒk ɪn ðə ˈiːv.nɪŋ./", "Bắt đầu lúc sáu giờ tối.", 3));
        lines.add(line(c, "B", "Where is it going to be?", "/weər ɪz ɪt ˈɡoʊ.ɪŋ tuː biː?/", "Địa điểm ở đâu?", 4));
        lines.add(line(c, "A", "At my house. I will send you the address.", "/æt maɪ haʊs. aɪ wɪl send juː ðə ˈæd.res./", "Ở nhà tôi. Tôi sẽ gửi địa chỉ cho bạn.", 5));
        lines.add(line(c, "B", "I would love to come. Should I bring anything?", "/aɪ wʊd lʌv tuː kʌm. ʃʊd aɪ brɪŋ ˈeni.θɪŋ?/", "Tôi rất muốn đến. Tôi có cần mang gì không?", 6));
        lines.add(line(c, "A", "Just yourself! There will be food and music.", "/dʒʌst jɔːrˈself! ðeər wɪl biː fuːd ænd ˈmjuː.zɪk./", "Chỉ cần có mặt! Sẽ có đồ ăn và âm nhạc.", 7));
        lines.add(line(c, "B", "Perfect! I will see you on Saturday.", "/ˈpɜːr.fɪkt! aɪ wɪl siː juː ɒn ˈsæt.ər.deɪ./", "Tuyệt! Thứ Bảy gặp bạn nhé.", 8));

        // Conv 4: Hotel with a Problem
        c = conversationIdOffset + 4;
        lines.add(line(c, "A", "Excuse me, I have a problem with my room.", "/ɪkˈskjuːz miː, aɪ hæv ə ˈprɒb.ləm wɪð maɪ ruːm./", "Xin lỗi, phòng tôi có vấn đề.", 1));
        lines.add(line(c, "B", "I am sorry to hear that. What is the problem?", "/aɪ æm ˈsɒr.i tuː hɪər ðæt. wʌt ɪz ðə ˈprɒb.ləm?/", "Xin lỗi vì sự bất tiện. Vấn đề là gì ạ?", 2));
        lines.add(line(c, "A", "The air conditioner is not working.", "/ðə eər kənˈdɪʃ.ə.nər ɪz nɒt ˈwɜːr.kɪŋ./", "Máy lạnh không hoạt động.", 3));
        lines.add(line(c, "B", "I will send someone to fix it immediately.", "/aɪ wɪl send ˈsʌm.wʌn tuː fɪks ɪt ɪˈmiː.di.ət.li./", "Tôi sẽ cử người sửa ngay.", 4));
        lines.add(line(c, "A", "Also, there is no hot water in the shower.", "/ˈɔːl.soʊ, ðeər ɪz noʊ hɒt ˈwɔː.tər ɪn ðə ˈʃaʊ.ər./", "Ngoài ra, vòi hoa sen không có nước nóng.", 5));
        lines.add(line(c, "B", "I am very sorry. Would you like to change rooms?", "/aɪ æm ˈver.i ˈsɒr.i. wʊd juː laɪk tuː tʃeɪndʒ ruːmz?/", "Tôi rất xin lỗi. Bạn có muốn đổi phòng không?", 6));
        lines.add(line(c, "A", "Yes, that would be great.", "/jes, ðæt wʊd biː ɡreɪt./", "Vâng, vậy tốt quá.", 7));
        lines.add(line(c, "B", "I will upgrade you to a superior room at no extra charge.", "/aɪ wɪl ˈʌp.ɡreɪd juː tuː ə suːˈpɪər.i.ər ruːm æt noʊ ˈek.strə tʃɑːrdʒ./", "Tôi sẽ nâng phòng cho bạn mà không tính thêm phí.", 8));

        // Conv 5: Returning an Item
        c = conversationIdOffset + 5;
        lines.add(line(c, "A", "Hi, I bought this jacket here yesterday.", "/haɪ, aɪ bɔːt ðɪs ˈdʒæk.ɪt hɪər ˈjes.tər.deɪ./", "Xin chào, hôm qua tôi mua chiếc áo khoác này ở đây.", 1));
        lines.add(line(c, "B", "How can I help you?", "/haʊ kæn aɪ help juː?/", "Tôi có thể giúp gì cho bạn?", 2));
        lines.add(line(c, "A", "The zip is broken. I would like to exchange it.", "/ðə zɪp ɪz ˈbroʊ.kən. aɪ wʊd laɪk tuː ɪksˈtʃeɪndʒ ɪt./", "Dây kéo bị hỏng. Tôi muốn đổi cái khác.", 3));
        lines.add(line(c, "B", "Do you have your receipt?", "/duː juː hæv jɔːr rɪˈsiːt?/", "Bạn có hóa đơn không?", 4));
        lines.add(line(c, "A", "Yes, here it is.", "/jes, hɪər ɪt ɪz./", "Vâng, đây ạ.", 5));
        lines.add(line(c, "B", "I am sorry about that. We can exchange it for a new one.", "/aɪ æm ˈsɒr.i əˈbaʊt ðæt. wiː kæn ɪksˈtʃeɪndʒ ɪt fɔːr ə njuː wʌn./", "Xin lỗi về sự cố này. Chúng tôi có thể đổi cái mới cho bạn.", 6));
        lines.add(line(c, "A", "What if the same size is not in stock?", "/wʌt ɪf ðə seɪm saɪz ɪz nɒt ɪn stɒk?/", "Nếu không còn size đó thì sao?", 7));
        lines.add(line(c, "B", "Then I can offer you a full refund.", "/ðen aɪ kæn ˈɒf.ər juː ə fʊl ˈriː.fʌnd./", "Thì chúng tôi sẽ hoàn tiền đầy đủ cho bạn.", 8));

        // Conv 6: Medical Advice
        c = conversationIdOffset + 6;
        lines.add(line(c, "A", "Good morning, doctor. I have not been feeling well.", "/ɡʊd ˈmɔːr.nɪŋ, ˈdɒk.tər. aɪ hæv nɒt biːn ˈfiː.lɪŋ wel./", "Chào buổi sáng, bác sĩ. Tôi không được khỏe.", 1));
        lines.add(line(c, "B", "What symptoms do you have?", "/wʌt ˈsɪmp.təmz duː juː hæv?/", "Bạn có triệu chứng gì?", 2));
        lines.add(line(c, "A", "I have a cough, a sore throat, and I feel dizzy.", "/aɪ hæv ə kɒf, ə sɔːr θroʊt, ænd aɪ fiːl ˈdɪz.i./", "Tôi ho, đau họng và chóng mặt.", 3));
        lines.add(line(c, "B", "How long have you had these symptoms?", "/haʊ lɒŋ hæv juː hæd ðiːz ˈsɪmp.təmz?/", "Bạn có triệu chứng này bao lâu rồi?", 4));
        lines.add(line(c, "A", "Since yesterday morning.", "/sɪns ˈjes.tər.deɪ ˈmɔːr.nɪŋ./", "Từ sáng hôm qua.", 5));
        lines.add(line(c, "B", "You should rest and drink plenty of water.", "/juː ʃʊd rest ænd drɪŋk ˈplen.ti əv ˈwɔː.tər./", "Bạn nên nghỉ ngơi và uống nhiều nước.", 6));
        lines.add(line(c, "A", "Do I need antibiotics?", "/duː aɪ niːd ˌæn.ti.baɪˈɒt.ɪks?/", "Tôi có cần uống kháng sinh không?", 7));
        lines.add(line(c, "B", "Not yet. Let us wait two days. If it gets worse, come back.", "/nɒt jet. let ʌs weɪt tuː deɪz. ɪf ɪt ɡets wɜːrs, kʌm bæk./", "Chưa cần. Chờ hai ngày xem sao. Nếu nặng hơn hãy quay lại.", 8));

        // Conv 7: Study Plans
        c = conversationIdOffset + 7;
        lines.add(line(c, "A", "Are you planning to study anything new this semester?", "/ɑːr juː ˈplæn.ɪŋ tuː ˈstʌd.i ˈeni.θɪŋ njuː ðɪs ˈsem.ɪs.tər?/", "Kỳ này bạn có định học thêm gì không?", 1));
        lines.add(line(c, "B", "Yes, I am going to take an online coding course.", "/jes, aɪ æm ˈɡoʊ.ɪŋ tuː teɪk ən ˈɒn.laɪn ˈkoʊ.dɪŋ kɔːrs./", "Có, tôi sẽ học một khóa lập trình trực tuyến.", 2));
        lines.add(line(c, "A", "That is a great idea. Is it difficult?", "/ðæt ɪz ə ɡreɪt aɪˈdɪə. ɪz ɪt ˈdɪf.ɪ.kəlt?/", "Hay đấy. Có khó không?", 3));
        lines.add(line(c, "B", "It is challenging but very interesting.", "/ɪt ɪz ˈtʃæl.ɪn.dʒɪŋ bʌt ˈver.i ˈɪn.trɪs.tɪŋ./", "Đầy thử thách nhưng rất thú vị.", 4));
        lines.add(line(c, "A", "What about your exams? Do you have any soon?", "/wʌt əˈbaʊt jɔːr ɪɡˈzæmz? duː juː hæv ˈeni suːn?/", "Còn kỳ thi thì sao? Bạn sắp có thi không?", 5));
        lines.add(line(c, "B", "Yes, I have a test next Friday. I must revise this week.", "/jes, aɪ hæv ə test nekst ˈfraɪ.deɪ. aɪ mʌst rɪˈvaɪz ðɪs wiːk./", "Có, thứ Sáu tới tôi có bài kiểm tra. Tôi phải ôn bài tuần này.", 6));
        lines.add(line(c, "A", "I can help you study if you want.", "/aɪ kæn help juː ˈstʌd.i ɪf juː wɒnt./", "Tôi có thể giúp bạn học nếu bạn muốn.", 7));
        lines.add(line(c, "B", "That would be amazing, thank you so much!", "/ðæt wʊd biː əˈmeɪ.zɪŋ, θæŋk juː soʊ mʌtʃ!/", "Tuyệt vời quá, cảm ơn bạn nhiều lắm!", 8));

        // Conv 8: Comparing Two Phones
        c = conversationIdOffset + 8;
        lines.add(line(c, "A", "I am going to buy a new phone. Can you help me choose?", "/aɪ æm ˈɡoʊ.ɪŋ tuː baɪ ə njuː foʊn. kæn juː help miː tʃuːz?/", "Tôi sắp mua điện thoại mới. Bạn có thể giúp tôi chọn không?", 1));
        lines.add(line(c, "B", "Sure! Which ones are you looking at?", "/ʃʊr! wɪtʃ wʌnz ɑːr juː ˈlʊk.ɪŋ æt?/", "Được! Bạn đang xem cái nào vậy?", 2));
        lines.add(line(c, "A", "I like model A and model B. Model A is cheaper but smaller.", "/aɪ laɪk ˈmɒd.əl eɪ ænd ˈmɒd.əl biː. ˈmɒd.əl eɪ ɪz ˈtʃiː.pər bʌt ˈsmɔː.lər./", "Tôi thích mẫu A và mẫu B. Mẫu A rẻ hơn nhưng nhỏ hơn.", 3));
        lines.add(line(c, "B", "Is the camera quality important to you?", "/ɪz ðə ˈkæm.rə ˈkwɒl.ɪ.ti ɪmˈpɔːr.tənt tuː juː?/", "Chất lượng camera có quan trọng với bạn không?", 4));
        lines.add(line(c, "A", "Yes, very much. I love taking photos.", "/jes, ˈver.i mʌtʃ. aɪ lʌv ˈteɪ.kɪŋ ˈfoʊ.toʊz./", "Rất quan trọng. Tôi rất thích chụp ảnh.", 5));
        lines.add(line(c, "B", "Then model B is better for you. Its camera is more advanced.", "/ðen ˈmɒd.əl biː ɪz ˈbet.ər fɔːr juː. ɪts ˈkæm.rə ɪz mɔːr ədˈvɑːnst./", "Vậy thì mẫu B phù hợp hơn. Camera của nó tiên tiến hơn.", 6));
        lines.add(line(c, "A", "But it is more expensive.", "/bʌt ɪt ɪz mɔːr ɪkˈspen.sɪv./", "Nhưng nó đắt hơn.", 7));
        lines.add(line(c, "B", "It is worth it if you use the camera a lot.", "/ɪt ɪz wɜːrθ ɪt ɪf juː juːz ðə ˈkæm.rə ə lɒt./", "Xứng đáng nếu bạn dùng camera nhiều.", 8));

        // Conv 9: Giving Advice
        c = conversationIdOffset + 9;
        lines.add(line(c, "A", "I have been feeling very stressed lately.", "/aɪ hæv biːn ˈfiː.lɪŋ ˈver.i strest ˈleɪt.li./", "Dạo này tôi cảm thấy rất căng thẳng.", 1));
        lines.add(line(c, "B", "I am sorry to hear that. What is causing it?", "/aɪ æm ˈsɒr.i tuː hɪər ðæt. wʌt ɪz ˈkɔː.zɪŋ ɪt?/", "Tôi tiếc khi nghe vậy. Nguyên nhân là gì?", 2));
        lines.add(line(c, "A", "I have too much work and not enough sleep.", "/aɪ hæv tuː mʌtʃ wɜːrk ænd nɒt ɪˈnʌf sliːp./", "Tôi có quá nhiều việc và không ngủ đủ giấc.", 3));
        lines.add(line(c, "B", "You should try to manage your time better.", "/juː ʃʊd traɪ tuː ˈmæn.ɪdʒ jɔːr taɪm ˈbet.ər./", "Bạn nên cố gắng quản lý thời gian tốt hơn.", 4));
        lines.add(line(c, "A", "That is easier said than done.", "/ðæt ɪz ˈiː.zi.ər sed ðæn dʌn./", "Nói thì dễ hơn làm.", 5));
        lines.add(line(c, "B", "Why don't you make a daily schedule?", "/waɪ doʊnt juː meɪk ə ˈdeɪ.li ˈʃed.juːl?/", "Sao bạn không lập lịch học mỗi ngày?", 6));
        lines.add(line(c, "A", "I have also stopped exercising.", "/aɪ hæv ˈɔːl.soʊ stɒpt ˈek.sɚ.saɪ.zɪŋ./", "Tôi cũng bỏ tập thể dục rồi.", 7));
        lines.add(line(c, "B", "You must take care of your health. Even a short walk helps.", "/juː mʌst teɪk keər əv jɔːr helθ. ˈiː.vən ə ʃɔːrt wɔːk helpz./", "Bạn phải chăm sóc sức khỏe. Dù chỉ đi bộ ngắn cũng có ích.", 8));

        // Conv 10: Future Goals
        c = conversationIdOffset + 10;
        lines.add(line(c, "A", "Where do you see yourself in five years?", "/weər duː juː siː jɔːrˈself ɪn faɪv jɪərz?/", "Năm năm nữa bạn thấy mình ở đâu?", 1));
        lines.add(line(c, "B", "I hope to be working as an engineer.", "/aɪ hoʊp tuː biː ˈwɜːr.kɪŋ æz ən ˌen.dʒɪˈnɪər./", "Tôi mong được làm kỹ sư.", 2));
        lines.add(line(c, "A", "That is a great goal. Are you studying for it now?", "/ðæt ɪz ə ɡreɪt ɡoʊl. ɑːr juː ˈstʌd.i.ɪŋ fɔːr ɪt naʊ?/", "Mục tiêu hay đó. Bạn đang học cho nó không?", 3));
        lines.add(line(c, "B", "Yes, I am going to start a degree course in September.", "/jes, aɪ æm ˈɡoʊ.ɪŋ tuː stɑːrt ə dɪˈɡriː kɔːrs ɪn sepˈtem.bər./", "Có, tháng Chín tôi sẽ bắt đầu học bằng đại học.", 4));
        lines.add(line(c, "A", "Do you also want to work abroad?", "/duː juː ˈɔːl.soʊ wɒnt tuː wɜːrk əˈbrɔːd?/", "Bạn có muốn làm việc ở nước ngoài không?", 5));
        lines.add(line(c, "B", "Maybe. I will look for opportunities in Europe.", "/ˈmeɪ.bi. aɪ wɪl lʊk fɔːr ˌɒp.əˈtjuː.nɪ.tiz ɪn ˈjʊər.əp./", "Có lẽ vậy. Tôi sẽ tìm cơ hội ở châu Âu.", 6));
        lines.add(line(c, "A", "That is ambitious! I am sure you will succeed.", "/ðæt ɪz æmˈbɪʃ.əs! aɪ æm ʃʊər juː wɪl səkˈsiːd./", "Tham vọng đấy! Tôi chắc bạn sẽ thành công.", 7));
        lines.add(line(c, "B", "Thank you. What about you? What are your plans?", "/θæŋk juː. wʌt əˈbaʊt juː? wʌt ɑːr jɔːr plænz?/", "Cảm ơn. Còn bạn thì sao? Kế hoạch của bạn là gì?", 8));

        // Conv 11: Reporting a Lost Item
        c = conversationIdOffset + 11;
        lines.add(line(c, "A", "Excuse me, I think I left my bag on the plane.", "/ɪkˈskjuːz miː, aɪ θɪŋk aɪ left maɪ bæɡ ɒn ðə pleɪn./", "Xin lỗi, tôi nghĩ tôi bỏ quên túi trên máy bay.", 1));
        lines.add(line(c, "B", "I see. Can you describe the bag?", "/aɪ siː. kæn juː dɪˈskraɪb ðə bæɡ?/", "Tôi hiểu. Bạn có thể mô tả cái túi không?", 2));
        lines.add(line(c, "A", "It is a black backpack with a red zip.", "/ɪt ɪz ə blæk ˈbæk.pæk wɪð ə red zɪp./", "Đó là ba lô đen có dây kéo đỏ.", 3));
        lines.add(line(c, "B", "What seat were you in?", "/wʌt siːt wɜːr juː ɪn?/", "Bạn ngồi ghế số mấy?", 4));
        lines.add(line(c, "A", "I was in seat 14B on flight VN123.", "/aɪ wɒz ɪn siːt fɔːˈtiːn biː ɒn flaɪt viː en wʌn tuː θriː./", "Tôi ngồi ghế 14B trên chuyến bay VN123.", 5));
        lines.add(line(c, "B", "Please fill in this form and we will search for it.", "/pliːz fɪl ɪn ðɪs fɔːrm ænd wiː wɪl sɜːrtʃ fɔːr ɪt./", "Vui lòng điền vào mẫu này và chúng tôi sẽ tìm cho bạn.", 6));
        lines.add(line(c, "A", "What should I do if you find it?", "/wʌt ʃʊd aɪ duː ɪf juː faɪnd ɪt?/", "Nếu tìm được thì tôi phải làm gì?", 7));
        lines.add(line(c, "B", "We will contact you within 24 hours.", "/wiː wɪl ˈkɒn.tækt juː wɪˈðɪn ˌtwen.ti ˈfɔːr ˈaʊərz./", "Chúng tôi sẽ liên hệ với bạn trong vòng 24 giờ.", 8));

        // Conv 12: Ordering Food With a Problem
        c = conversationIdOffset + 12;
        lines.add(line(c, "A", "Excuse me, I think there is a mistake with my order.", "/ɪkˈskjuːz miː, aɪ θɪŋk ðeər ɪz ə mɪˈsteɪk wɪð maɪ ˈɔːr.dər./", "Xin lỗi, tôi nghĩ có nhầm lẫn với đơn của tôi.", 1));
        lines.add(line(c, "B", "I am sorry. What did you order?", "/aɪ æm ˈsɒr.i. wʌt dɪd juː ˈɔːr.dər?/", "Xin lỗi. Bạn đã gọi gì?", 2));
        lines.add(line(c, "A", "I ordered the grilled chicken, not the fried one.", "/aɪ ˈɔːr.dərd ðə ɡrɪld ˈtʃɪk.ɪn, nɒt ðə fraɪd wʌn./", "Tôi gọi gà nướng, không phải gà chiên.", 3));
        lines.add(line(c, "B", "I apologise. I will bring the correct dish immediately.", "/aɪ əˈpɒl.ə.dʒaɪz. aɪ wɪl brɪŋ ðə kəˈrekt dɪʃ ɪˈmiː.di.ət.li./", "Xin lỗi. Tôi sẽ mang món đúng ra ngay.", 4));
        lines.add(line(c, "A", "Thank you. Also, I am allergic to peanuts.", "/θæŋk juː. ˈɔːl.soʊ, aɪ æm əˈlɜːr.dʒɪk tuː ˈpiː.nʌts./", "Cảm ơn. Ngoài ra, tôi bị dị ứng với đậu phộng.", 5));
        lines.add(line(c, "B", "I will make sure the kitchen is aware of your allergy.", "/aɪ wɪl meɪk ʃʊər ðə ˈkɪtʃ.ɪn ɪz əˈweər əv jɔːr ˈæl.ər.dʒi./", "Tôi sẽ thông báo cho bếp biết về dị ứng của bạn.", 6));
        lines.add(line(c, "A", "I appreciate it. Can I also get some extra bread?", "/aɪ əˈpriː.ʃi.eɪt ɪt. kæn aɪ ˈɔːl.soʊ ɡet sʌm ˈeks.trə bred?/", "Tôi trân trọng điều đó. Tôi có thể lấy thêm bánh mì không?", 7));
        lines.add(line(c, "B", "Of course, I will bring it right away.", "/əv kɔːrs, aɪ wɪl brɪŋ ɪt raɪt əˈweɪ./", "Được, tôi sẽ mang ra ngay.", 8));

        // Conv 13: Help at the Airport
        c = conversationIdOffset + 13;
        lines.add(line(c, "A", "Excuse me, my flight has been cancelled. What should I do?", "/ɪkˈskjuːz miː, maɪ flaɪt hæz biːn ˈkæn.səld. wʌt ʃʊd aɪ duː?/", "Xin lỗi, chuyến bay của tôi bị hủy. Tôi phải làm gì?", 1));
        lines.add(line(c, "B", "I am sorry to hear that. Please go to the airline desk.", "/aɪ æm ˈsɒr.i tuː hɪər ðæt. pliːz ɡoʊ tuː ðə ˈeər.laɪn desk./", "Xin lỗi vì sự bất tiện. Vui lòng đến quầy hãng bay.", 2));
        lines.add(line(c, "A", "Where is it? I am completely lost.", "/weər ɪz ɪt? aɪ æm kəmˈpliːt.li lɒst./", "Nó ở đâu? Tôi bị lạc hoàn toàn rồi.", 3));
        lines.add(line(c, "B", "Follow the signs to Terminal B, then turn left.", "/ˈfɒl.oʊ ðə saɪnz tuː ˈtɜːr.mɪ.nəl biː, ðen tɜːrn left./", "Đi theo biển chỉ dẫn đến Nhà ga B, rồi rẽ trái.", 4));
        lines.add(line(c, "A", "Can they book me on the next flight?", "/kæn ðeɪ bʊk miː ɒn ðə nekst flaɪt?/", "Họ có thể đặt chỗ cho tôi trên chuyến bay tiếp theo không?", 5));
        lines.add(line(c, "B", "Yes, they should be able to rebook you at no extra cost.", "/jes, ðeɪ ʃʊd biː ˈeɪ.bəl tuː ˌriːˈbʊk juː æt noʊ ˈeks.trə kɒst./", "Có, họ nên có thể đặt lại cho bạn miễn phí.", 6));
        lines.add(line(c, "A", "What about my luggage? Is it safe?", "/wʌt əˈbaʊt maɪ ˈlʌɡ.ɪdʒ? ɪz ɪt seɪf?/", "Còn hành lý của tôi thì sao? Nó có an toàn không?", 7));
        lines.add(line(c, "B", "Your luggage is stored safely. It will be transferred to your new flight.", "/jɔːr ˈlʌɡ.ɪdʒ ɪz stɔːrd ˈseɪf.li. ɪt wɪl biː trænsˈfɜːrd tuː jɔːr njuː flaɪt./", "Hành lý của bạn được bảo quản an toàn. Nó sẽ được chuyển sang chuyến bay mới.", 8));

        // Conv 14: Hobbies and Opinions
        c = conversationIdOffset + 14;
        lines.add(line(c, "A", "What do you like to do when you are not working?", "/wʌt duː juː laɪk tuː duː wen juː ɑːr nɒt ˈwɜːr.kɪŋ?/", "Bạn thích làm gì khi không làm việc?", 1));
        lines.add(line(c, "B", "I love hiking and photography.", "/aɪ lʌv ˈhaɪ.kɪŋ ænd fəˈtɒɡ.rə.fi./", "Tôi yêu thích leo núi và nhiếp ảnh.", 2));
        lines.add(line(c, "A", "That is great! Do you prefer outdoor activities?", "/ðæt ɪz ɡreɪt! duː juː prɪˈfɜːr ˈaʊt.dɔːr ækˈtɪv.ɪ.tiz?/", "Tuyệt! Bạn thích hoạt động ngoài trời hơn à?", 3));
        lines.add(line(c, "B", "Yes, in my opinion, outdoor activities are more relaxing than indoor ones.", "/jes, ɪn maɪ əˈpɪn.jən, ˈaʊt.dɔːr ækˈtɪv.ɪ.tiz ɑːr mɔːr rɪˈlæk.sɪŋ ðæn ˈɪn.dɔːr wʌnz./", "Vâng, theo tôi, hoạt động ngoài trời thư giãn hơn trong nhà.", 4));
        lines.add(line(c, "A", "I disagree a little. I find reading more relaxing.", "/aɪ ˌdɪs.əˈɡriː ə ˈlɪt.əl. aɪ faɪnd ˈriː.dɪŋ mɔːr rɪˈlæk.sɪŋ./", "Tôi hơi không đồng ý. Tôi thấy đọc sách thư giãn hơn.", 5));
        lines.add(line(c, "B", "That is fair. Different people prefer different things.", "/ðæt ɪz feər. ˈdɪf.ər.ənt ˈpiː.pəl prɪˈfɜːr ˈdɪf.ər.ənt θɪŋz./", "Điều đó hợp lý. Mỗi người thích mỗi thứ khác nhau.", 6));
        lines.add(line(c, "A", "Do you think hobbies are important for mental health?", "/duː juː θɪŋk ˈhɒb.iz ɑːr ɪmˈpɔːr.tənt fɔːr ˈmen.tl helθ?/", "Bạn có nghĩ sở thích quan trọng cho sức khỏe tinh thần không?", 7));
        lines.add(line(c, "B", "Absolutely. Having hobbies makes life much more enjoyable.", "/ˌæb.səˈluːt.li. ˈhæv.ɪŋ ˈhɒb.iz meɪks laɪf mʌtʃ mɔːr ɪnˈdʒɔɪ.ə.bəl./", "Chắc chắn rồi. Có sở thích giúp cuộc sống vui vẻ hơn nhiều.", 8));

        return lines;
    }

    private static ConversationLine line(int convId, String role, String en, String ipa, String vi, int order) {
        return new ConversationLine(convId, role, en, ipa, vi, order);
    }
}
