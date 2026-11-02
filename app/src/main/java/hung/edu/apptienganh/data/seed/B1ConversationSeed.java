package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.Conversation;
import hung.edu.apptienganh.data.local.entity.ConversationLine;

public final class B1ConversationSeed {

    private B1ConversationSeed() {}

    public static List<Conversation> buildConversations() {
        List<Conversation> list = new ArrayList<>();
        list.add(new Conversation("B1", "Experiences", "Talking About Life Experiences",
                "Hai người kể về những trải nghiệm đáng nhớ trong cuộc sống."));
        list.add(new Conversation("B1", "Work", "Discussing a Work Problem",
                "Hai đồng nghiệp thảo luận về vấn đề phát sinh tại công việc."));
        list.add(new Conversation("B1", "Education", "Giving Study Advice",
                "Một người hỏi lời khuyên học tập từ người có kinh nghiệm hơn."));
        list.add(new Conversation("B1", "Goals", "Talking About Future Goals",
                "Hai bạn chia sẻ về kế hoạch và mục tiêu tương lai."));
        list.add(new Conversation("B1", "Opinions", "Agreeing and Disagreeing",
                "Hai người thảo luận và đưa ra quan điểm đối lập về một vấn đề."));
        list.add(new Conversation("B1", "Complaint", "Making a Complaint Politely",
                "Khách hàng phàn nàn lịch sự với nhân viên về dịch vụ kém chất lượng."));
        list.add(new Conversation("B1", "Travel", "Explaining a Travel Problem",
                "Du khách giải thích vấn đề gặp phải trong chuyến du lịch."));
        list.add(new Conversation("B1", "Health", "Talking About Health Habits",
                "Hai người nói chuyện về thói quen sức khỏe và cách duy trì sức khỏe."));
        list.add(new Conversation("B1", "SocialMedia", "Giving an Opinion About Social Media",
                "Hai người tranh luận về lợi ích và tác hại của mạng xã hội."));
        list.add(new Conversation("B1", "Project", "Planning a Group Project",
                "Nhóm sinh viên lên kế hoạch và phân chia công việc dự án nhóm."));
        list.add(new Conversation("B1", "Money", "Talking About Money Problems",
                "Hai người bạn nói về áp lực tài chính và cách quản lý tiền bạc."));
        list.add(new Conversation("B1", "Clarification", "Asking for Clarification",
                "Người học hỏi lại những điều chưa rõ từ buổi thuyết trình."));
        list.add(new Conversation("B1", "Presentation", "Giving a Short Presentation",
                "Sinh viên thực hành thuyết trình ngắn trước lớp."));
        list.add(new Conversation("B1", "Environment", "Discussing Environmental Problems",
                "Hai người nói về tác động của con người lên môi trường và giải pháp."));
        list.add(new Conversation("B1", "Interview", "Talking About a Job Interview",
                "Người vừa đi phỏng vấn kể lại trải nghiệm với người bạn."));
        list.add(new Conversation("B1", "Difficult situation", "Describing a Difficult Situation",
                "Người kể lại một tình huống khó khăn họ đã vượt qua như thế nào."));
        return list;
    }

    public static List<ConversationLine> buildConversationLines(int conversationIdOffset) {
        List<ConversationLine> lines = new ArrayList<>();
        int c;

        // Conv 1: Life Experiences
        c = conversationIdOffset + 1;
        lines.add(line(c, "A", "Have you ever done anything really adventurous?", "/hæv juː ˈev.ər dʌn ˈeni.θɪŋ ˈriː.li ədˈven.tʃər.əs?/", "Bạn đã từng làm điều gì thực sự phiêu lưu chưa?", 1));
        lines.add(line(c, "B", "Actually, yes. I went backpacking alone through Southeast Asia last year.", "/ˈæk.tʃu.ə.li, jes. aɪ went ˈbæk.pæk.ɪŋ əˈloʊn θruː ˌsaʊθˈiːst ˈeɪ.ʒə lɑːst jɪər./", "Thực ra là có. Năm ngoái tôi đi du lịch balo một mình qua Đông Nam Á.", 2));
        lines.add(line(c, "A", "That sounds incredible! Were you nervous?", "/ðæt saʊndz ɪnˈkred.ɪ.bəl! wɜːr juː ˈnɜːr.vəs?/", "Nghe tuyệt vời quá! Bạn có lo lắng không?", 3));
        lines.add(line(c, "B", "Very much at first. But I overcame my fear pretty quickly.", "/ˈver.i mʌtʃ æt fɜːrst. bʌt aɪ ˌoʊ.vərˈkeɪm maɪ fɪər ˈprɪt.i ˈkwɪk.li./", "Lúc đầu rất lo. Nhưng tôi đã vượt qua nỗi sợ khá nhanh.", 4));
        lines.add(line(c, "A", "What was the most memorable moment?", "/wʌt wɒz ðə moʊst ˈmem.ər.ə.bəl ˈmoʊ.mənt?/", "Khoảnh khắc đáng nhớ nhất là gì?", 5));
        lines.add(line(c, "B", "Meeting a local family in Vietnam. They invited me for a home-cooked meal.", "/ˈmiː.tɪŋ ə ˈloʊ.kəl ˈfæm.ə.li ɪn ˌvjet.ˈnæm. ðeɪ ɪnˈvaɪ.tɪd miː fɔːr ə ˈhoʊm.kʊkt miːl./", "Gặp một gia đình địa phương ở Việt Nam. Họ mời tôi ăn bữa cơm nhà.", 6));
        lines.add(line(c, "A", "That is so heartwarming. I have never done anything like that.", "/ðæt ɪz soʊ ˈhɑːrt.wɔːr.mɪŋ. aɪ hæv ˈnev.ər dʌn ˈeni.θɪŋ laɪk ðæt./", "Ấm lòng quá. Tôi chưa bao giờ làm điều gì như vậy.", 7));
        lines.add(line(c, "B", "You should try it one day. It will change the way you see the world.", "/juː ʃʊd traɪ ɪt wʌn deɪ. ɪt wɪl tʃeɪndʒ ðə weɪ juː siː ðə wɜːrld./", "Bạn nên thử một ngày nào đó. Nó sẽ thay đổi cách nhìn thế giới của bạn.", 8));

        // Conv 2: Work Problem
        c = conversationIdOffset + 2;
        lines.add(line(c, "A", "I am really struggling with this project.", "/aɪ æm ˈriː.li ˈstrʌɡ.lɪŋ wɪð ðɪs ˈprɒdʒ.ekt./", "Tôi đang thực sự gặp khó khăn với dự án này.", 1));
        lines.add(line(c, "B", "What is the main issue?", "/wʌt ɪz ðə meɪn ˈɪʃ.uː?/", "Vấn đề chính là gì?", 2));
        lines.add(line(c, "A", "The deadline is Friday, but two team members are absent.", "/ðə ˈded.laɪn ɪz ˈfraɪ.deɪ, bʌt tuː tiːm ˈmem.bərz ɑːr ˈæb.sənt./", "Hạn chót là thứ Sáu nhưng hai thành viên nhóm vắng mặt.", 3));
        lines.add(line(c, "B", "That is tough. Have you spoken to the manager about it?", "/ðæt ɪz tʌf. hæv juː ˈspoʊ.kən tuː ðə ˈmæn.ɪ.dʒər əˈbaʊt ɪt?/", "Thật khó khăn. Bạn đã nói chuyện với quản lý chưa?", 4));
        lines.add(line(c, "A", "Not yet. I am afraid she might be disappointed.", "/nɒt jet. aɪ æm əˈfreɪd ʃiː maɪt biː ˌdɪs.əˈpɔɪn.tɪd./", "Chưa. Tôi sợ cô ấy có thể thất vọng.", 5));
        lines.add(line(c, "B", "I would suggest being honest with her. That is always the best approach.", "/aɪ wʊd səˈdʒest ˈbiː.ɪŋ ˈɒn.ɪst wɪð hɜːr. ðæt ɪz ˈɔːl.weɪz ðə best əˈproʊtʃ./", "Tôi gợi ý bạn thành thật với cô ấy. Đó luôn là cách tiếp cận tốt nhất.", 6));
        lines.add(line(c, "A", "You are right. I will talk to her this afternoon.", "/juː ɑːr raɪt. aɪ wɪl tɔːk tuː hɜːr ðɪs ˌɑːf.tərˈnuːn./", "Bạn đúng. Tôi sẽ nói chuyện với cô ấy chiều nay.", 7));
        lines.add(line(c, "B", "Good luck. Let me know if you need any help.", "/ɡʊd lʌk. let miː noʊ ɪf juː niːd ˈeni help./", "Chúc may mắn. Cần giúp gì thì cứ nói nhé.", 8));

        // Conv 3: Study Advice
        c = conversationIdOffset + 3;
        lines.add(line(c, "A", "I have an important exam next month but I do not know how to prepare.", "/aɪ hæv ən ɪmˈpɔːr.tənt ɪɡˈzæm nekst mʌnθ bʌt aɪ duː nɒt noʊ haʊ tuː prɪˈpeər./", "Tháng sau tôi có kỳ thi quan trọng nhưng không biết chuẩn bị thế nào.", 1));
        lines.add(line(c, "B", "What subject is it?", "/wʌt ˈsʌb.dʒɪkt ɪz ɪt?/", "Môn gì vậy?", 2));
        lines.add(line(c, "A", "English. I especially find grammar and writing difficult.", "/ˈɪŋ.ɡlɪʃ. aɪ ɪˈspeʃ.ə.li faɪnd ˈɡræm.ər ænd ˈraɪ.tɪŋ ˈdɪf.ɪ.kəlt./", "Tiếng Anh. Tôi đặc biệt thấy ngữ pháp và viết khó.", 3));
        lines.add(line(c, "B", "I would recommend practising a little every day rather than studying a lot at once.", "/aɪ wʊd ˌrek.əˈmend ˈpræk.tɪ.sɪŋ ə ˈlɪt.əl ˈev.ri deɪ ˈrɑː.ðər ðæn ˈstʌd.i.ɪŋ ə lɒt æt wʌns./", "Tôi khuyên bạn nên luyện tập một chút mỗi ngày thay vì học nhiều một lúc.", 4));
        lines.add(line(c, "A", "That makes sense. Should I also use practice tests?", "/ðæt meɪks sens. ʃʊd aɪ ˈɔːl.soʊ juːz ˈpræk.tɪs tests?/", "Nghe có lý. Tôi có nên dùng bài thi thử không?", 5));
        lines.add(line(c, "B", "Absolutely. Past papers are extremely useful for understanding the exam format.", "/ˌæb.səˈluːt.li. pɑːst ˈpeɪ.pərz ɑːr ɪkˈstriːm.li ˈjuːs.fəl fɔːr ˌʌn.dɚˈstænd.ɪŋ ðə ɪɡˈzæm ˈfɔːr.mæt./", "Chắc chắn rồi. Đề thi cũ cực kỳ hữu ích để hiểu định dạng kỳ thi.", 6));
        lines.add(line(c, "A", "Have you ever failed an exam?", "/hæv juː ˈev.ər feɪld ən ɪɡˈzæm?/", "Bạn đã từng trượt kỳ thi chưa?", 7));
        lines.add(line(c, "B", "Yes, once. But I learned from it and passed the retake easily.", "/jes, wʌns. bʌt aɪ lɜːrnd frɒm ɪt ænd pɑːst ðə ˈriː.teɪk ˈiː.zɪ.li./", "Có, một lần. Nhưng tôi đã học từ thất bại đó và vượt qua lần thi lại dễ dàng.", 8));

        // Conv 4: Future Goals
        c = conversationIdOffset + 4;
        lines.add(line(c, "A", "If everything goes well, I am going to apply for a master's degree next year.", "/ɪf ˈev.ri.θɪŋ ɡoʊz wel, aɪ æm ˈɡoʊ.ɪŋ tuː əˈplaɪ fɔːr ə ˈmɑːs.tərz dɪˈɡriː nekst jɪər./", "Nếu mọi việc suôn sẻ, năm sau tôi sẽ nộp đơn xin học thạc sĩ.", 1));
        lines.add(line(c, "B", "Wow, that is a big step. Which field are you interested in?", "/waʊ, ðæt ɪz ə bɪɡ step. wɪtʃ fiːld ɑːr juː ˈɪn.trɪs.tɪd ɪn?/", "Ồ, đó là bước lớn. Bạn quan tâm đến lĩnh vực nào?", 2));
        lines.add(line(c, "A", "Business management. I want to eventually run my own company.", "/ˈbɪz.nɪs ˈmæn.ɪdʒ.mənt. aɪ wɒnt tuː ɪˈven.tʃu.ə.li rʌn maɪ oʊn ˈkʌm.pə.ni./", "Quản trị kinh doanh. Tôi muốn cuối cùng điều hành công ty riêng.", 3));
        lines.add(line(c, "B", "That is ambitious. Have you thought about where you want to study?", "/ðæt ɪz æmˈbɪʃ.əs. hæv juː θɔːt əˈbaʊt weər juː wɒnt tuː ˈstʌd.i?/", "Tham vọng đấy. Bạn đã nghĩ đến việc học ở đâu chưa?", 4));
        lines.add(line(c, "A", "Ideally abroad, but it depends on whether I can get a scholarship.", "/aɪˈdɪə.li əˈbrɔːd, bʌt ɪt dɪˈpendz ɒn ˈweð.ər aɪ kæn ɡet ə ˈskɒl.ər.ʃɪp./", "Lý tưởng là ở nước ngoài, nhưng phụ thuộc vào việc tôi có được học bổng không.", 5));
        lines.add(line(c, "B", "I believe if you work hard, you will definitely find a way.", "/aɪ bɪˈliːv ɪf juː wɜːrk hɑːrd, juː wɪl ˈdef.ɪ.nɪt.li faɪnd ə weɪ./", "Tôi tin rằng nếu bạn nỗ lực, bạn chắc chắn sẽ tìm ra cách.", 6));
        lines.add(line(c, "A", "Thank you. That means a lot. What are your goals?", "/θæŋk juː. ðæt miːnz ə lɒt. wʌt ɑːr jɔːr ɡoʊlz?/", "Cảm ơn. Điều đó có ý nghĩa rất nhiều. Mục tiêu của bạn là gì?", 7));
        lines.add(line(c, "B", "I want to become a teacher and inspire young people to reach their potential.", "/aɪ wɒnt tuː bɪˈkʌm ə ˈtiː.tʃər ænd ɪnˈspaɪər jʌŋ ˈpiː.pəl tuː riːtʃ ðeər pəˈten.ʃəl./", "Tôi muốn trở thành giáo viên và truyền cảm hứng cho các bạn trẻ phát huy tiềm năng.", 8));

        // Conv 5: Agreeing and Disagreeing
        c = conversationIdOffset + 5;
        lines.add(line(c, "A", "In my opinion, social media does more harm than good.", "/ɪn maɪ əˈpɪn.jən, ˈsoʊ.ʃəl ˈmiː.di.ə dʌz mɔːr hɑːrm ðæn ɡʊd./", "Theo ý kiến tôi, mạng xã hội gây hại nhiều hơn là có ích.", 1));
        lines.add(line(c, "B", "I see your point, but I do not completely agree.", "/aɪ siː jɔːr pɔɪnt, bʌt aɪ duː nɒt kəmˈpliːt.li əˈɡriː./", "Tôi hiểu ý bạn nhưng tôi không hoàn toàn đồng ý.", 2));
        lines.add(line(c, "A", "Really? How so? Can you explain?", "/ˈriː.li? haʊ soʊ? kæn juː ɪkˈspleɪn?/", "Thật sao? Tại sao? Bạn có thể giải thích không?", 3));
        lines.add(line(c, "B", "Well, it helps people stay connected and share information quickly.", "/wel, ɪt helpz ˈpiː.pəl steɪ kəˈnek.tɪd ænd ʃeər ˌɪn.fəˈmeɪ.ʃən ˈkwɪk.li./", "Ừ, nó giúp mọi người kết nối và chia sẻ thông tin nhanh chóng.", 4));
        lines.add(line(c, "A", "That is a fair point. However, it also spreads misinformation.", "/ðæt ɪz ə feər pɔɪnt. haʊˈev.ər, ɪt ˈɔːl.soʊ spredz ˌmɪs.ɪn.fəˈmeɪ.ʃən./", "Đó là điểm hợp lý. Tuy nhiên, nó cũng lan truyền thông tin sai lệch.", 5));
        lines.add(line(c, "B", "You are right about that. I think it depends on how people use it.", "/juː ɑːr raɪt əˈbaʊt ðæt. aɪ θɪŋk ɪt dɪˈpendz ɒn haʊ ˈpiː.pəl juːz ɪt./", "Bạn đúng về điều đó. Tôi nghĩ phụ thuộc vào cách mọi người sử dụng nó.", 6));
        lines.add(line(c, "A", "I can agree with that. Responsible use is key.", "/aɪ kæn əˈɡriː wɪð ðæt. rɪˈspɒn.sɪ.bəl juːs ɪz kiː./", "Tôi có thể đồng ý với điều đó. Sử dụng có trách nhiệm là điều quan trọng.", 7));
        lines.add(line(c, "B", "Exactly. Education about digital literacy would make a big difference.", "/ɪɡˈzækt.li. ˌedʒ.uˈkeɪ.ʃən əˈbaʊt ˈdɪdʒ.ɪ.təl ˈlɪt.ər.ə.si wʊd meɪk ə bɪɡ ˈdɪf.ər.əns./", "Chính xác. Giáo dục về hiểu biết kỹ thuật số sẽ tạo ra sự khác biệt lớn.", 8));

        // Conv 6: Polite Complaint
        c = conversationIdOffset + 6;
        lines.add(line(c, "A", "Excuse me, I would like to make a complaint about the service today.", "/ɪkˈskjuːz miː, aɪ wʊd laɪk tuː meɪk ə kəmˈpleɪnt əˈbaʊt ðə ˈsɜːr.vɪs tuˈdeɪ./", "Xin lỗi, tôi muốn khiếu nại về dịch vụ hôm nay.", 1));
        lines.add(line(c, "B", "Of course. I am sorry to hear that. What happened?", "/əv kɔːrs. aɪ æm ˈsɒr.i tuː hɪər ðæt. wʌt ˈhæp.ənd?/", "Được ạ. Tôi xin lỗi khi nghe vậy. Chuyện gì đã xảy ra?", 2));
        lines.add(line(c, "A", "I have been waiting for my order for forty minutes now.", "/aɪ hæv biːn ˈweɪ.tɪŋ fɔːr maɪ ˈɔːr.dər fɔːr ˈfɔːr.ti ˈmɪn.ɪts naʊ./", "Tôi đã chờ đơn hàng của mình bốn mươi phút rồi.", 3));
        lines.add(line(c, "B", "I sincerely apologise for the delay. Let me check on that immediately.", "/aɪ sɪnˈsɪər.li əˈpɒl.ə.dʒaɪz fɔːr ðə dɪˈleɪ. let miː tʃek ɒn ðæt ɪˈmiː.di.ət.li./", "Tôi thành thật xin lỗi vì sự chậm trễ. Để tôi kiểm tra ngay.", 4));
        lines.add(line(c, "A", "Also, the waiter was quite rude when I asked about my order.", "/ˈɔːl.soʊ, ðə ˈweɪ.tər wɒz kwaɪt ruːd wen aɪ ɑːskt əˈbaʊt maɪ ˈɔːr.dər./", "Ngoài ra, người phục vụ hơi thô lỗ khi tôi hỏi về đơn hàng.", 5));
        lines.add(line(c, "B", "I am very sorry about that. That is not acceptable behaviour from our staff.", "/aɪ æm ˈver.i ˈsɒr.i əˈbaʊt ðæt. ðæt ɪz nɒt əkˈsep.tə.bəl bɪˈheɪ.vjər frɒm ˈaʊər stɑːf./", "Tôi rất xin lỗi về điều đó. Đó không phải là hành vi chấp nhận được từ nhân viên của chúng tôi.", 6));
        lines.add(line(c, "A", "I appreciate your understanding. I hope this gets resolved.", "/aɪ əˈpriː.ʃi.eɪt jɔːr ˌʌn.dɚˈstæn.dɪŋ. aɪ hoʊp ðɪs ɡets rɪˈzɒlvd./", "Tôi trân trọng sự thông cảm của bạn. Tôi mong vấn đề được giải quyết.", 7));
        lines.add(line(c, "B", "Absolutely. As an apology, your meal today will be complimentary.", "/ˌæb.səˈluːt.li. æz ən ˈæp.ə.lɒdʒ.i, jɔːr miːl tuˈdeɪ wɪl biː ˌkɒm.plɪˈmen.tər.i./", "Chắc chắn rồi. Như một lời xin lỗi, bữa ăn hôm nay của bạn sẽ miễn phí.", 8));

        // Conv 7: Travel Problem
        c = conversationIdOffset + 7;
        lines.add(line(c, "A", "I need some help. My hotel has no record of my booking.", "/aɪ niːd sʌm help. maɪ hoʊˈtel hæz noʊ ˈrek.ərd əv maɪ ˈbʊk.ɪŋ./", "Tôi cần giúp đỡ. Khách sạn không có thông tin đặt phòng của tôi.", 1));
        lines.add(line(c, "B", "Oh no. When did you make the reservation?", "/oʊ noʊ. wen dɪd juː meɪk ðə ˌrez.əˈveɪ.ʃən?/", "Ôi không. Bạn đặt phòng khi nào?", 2));
        lines.add(line(c, "A", "Two weeks ago through a travel website. I have the confirmation email.", "/tuː wiːks əˈɡoʊ θruː ə ˈtræv.əl ˈweb.saɪt. aɪ hæv ðə ˌkɒn.fɜːˈmeɪ.ʃən ˈiː.meɪl./", "Hai tuần trước qua một trang web du lịch. Tôi có email xác nhận.", 3));
        lines.add(line(c, "B", "That should help. Did you contact the website directly?", "/ðæt ʃʊd help. dɪd juː ˈkɒn.tækt ðə ˈweb.saɪt dɪˈrekt.li?/", "Điều đó sẽ giúp được. Bạn đã liên hệ trực tiếp với trang web chưa?", 4));
        lines.add(line(c, "A", "Not yet. I was hoping the hotel would sort it out.", "/nɒt jet. aɪ wɒz ˈhoʊ.pɪŋ ðə hoʊˈtel wʊd sɔːrt ɪt aʊt./", "Chưa. Tôi hi vọng khách sạn sẽ giải quyết được.", 5));
        lines.add(line(c, "B", "I would suggest calling them now. They should be able to resolve it.", "/aɪ wʊd səˈdʒest ˈkɔː.lɪŋ ðem naʊ. ðeɪ ʃʊd biː ˈeɪ.bəl tuː rɪˈzɒlv ɪt./", "Tôi gợi ý bạn gọi cho họ ngay. Họ nên giải quyết được.", 6));
        lines.add(line(c, "A", "You are right. What a stressful start to the holiday.", "/juː ɑːr raɪt. wʌt ə ˈstres.fəl stɑːrt tuː ðə ˈhɒl.ɪ.deɪ./", "Bạn đúng. Thật là một khởi đầu căng thẳng cho kỳ nghỉ.", 7));
        lines.add(line(c, "B", "I know, but I am sure it will all work out in the end.", "/aɪ noʊ, bʌt aɪ æm ʃʊər ɪt wɪl ɔːl wɜːrk aʊt ɪn ðə end./", "Tôi biết, nhưng tôi chắc mọi thứ sẽ ổn thôi.", 8));

        // Conv 8: Health Habits
        c = conversationIdOffset + 8;
        lines.add(line(c, "A", "Have you always had such a healthy lifestyle?", "/hæv juː ˈɔːl.weɪz hæd sʌtʃ ə ˈhel.θi ˈlaɪf.staɪl?/", "Bạn luôn có lối sống lành mạnh như vậy không?", 1));
        lines.add(line(c, "B", "Not at all! I used to eat junk food every day and never exercise.", "/nɒt æt ɔːl! aɪ juːst tuː iːt dʒʌŋk fuːd ˈev.ri deɪ ænd ˈnev.ər ˈek.sɚ.saɪz./", "Không phải! Trước đây tôi hay ăn đồ ăn nhanh mỗi ngày và không bao giờ tập thể dục.", 2));
        lines.add(line(c, "A", "Wow, that is a big change. What motivated you?", "/waʊ, ðæt ɪz ə bɪɡ tʃeɪndʒ. wʌt ˈmoʊ.tɪ.veɪ.tɪd juː?/", "Ồ, thay đổi lớn đó. Điều gì đã thúc đẩy bạn?", 3));
        lines.add(line(c, "B", "I got sick and the doctor told me I had to change my diet.", "/aɪ ɡɒt sɪk ænd ðə ˈdɒk.tər toʊld miː aɪ hæd tuː tʃeɪndʒ maɪ ˈdaɪ.ɪt./", "Tôi bị bệnh và bác sĩ nói tôi phải thay đổi chế độ ăn.", 4));
        lines.add(line(c, "A", "That must have been a wake-up call.", "/ðæt mʌst hæv biːn ə ˈweɪk.ʌp kɔːl./", "Hẳn là một hồi chuông cảnh tỉnh.", 5));
        lines.add(line(c, "B", "It really was. Now I eat more vegetables and go for a walk every morning.", "/ɪt ˈriː.li wɒz. naʊ aɪ iːt mɔːr ˈvedʒ.tə.bəlz ænd ɡoʊ fɔːr ə wɔːk ˈev.ri ˈmɔːr.nɪŋ./", "Đúng vậy. Bây giờ tôi ăn nhiều rau hơn và đi bộ mỗi buổi sáng.", 6));
        lines.add(line(c, "A", "Do you feel better now?", "/duː juː fiːl ˈbet.ər naʊ?/", "Bây giờ bạn cảm thấy tốt hơn không?", 7));
        lines.add(line(c, "B", "Absolutely. I have more energy and I sleep much better than before.", "/ˌæb.səˈluːt.li. aɪ hæv mɔːr ˈen.ɚ.dʒi ænd aɪ sliːp mʌtʃ ˈbet.ər ðæn bɪˈfɔːr./", "Chắc chắn rồi. Tôi có nhiều năng lượng hơn và ngủ ngon hơn nhiều so với trước.", 8));

        // Conv 9: Social Media Opinion
        c = conversationIdOffset + 9;
        lines.add(line(c, "A", "Do you think young people spend too much time on social media?", "/duː juː θɪŋk jʌŋ ˈpiː.pəl spend tuː mʌtʃ taɪm ɒn ˈsoʊ.ʃəl ˈmiː.di.ə?/", "Bạn có nghĩ giới trẻ dành quá nhiều thời gian trên mạng xã hội không?", 1));
        lines.add(line(c, "B", "I think so, yes. Studies show it can affect mental health negatively.", "/aɪ θɪŋk soʊ, jes. ˈstʌd.iz ʃoʊ ɪt kæn əˈfekt ˈmen.tl helθ ˈneɡ.ə.tɪv.li./", "Tôi nghĩ vậy. Các nghiên cứu cho thấy nó có thể ảnh hưởng tiêu cực đến sức khỏe tâm thần.", 2));
        lines.add(line(c, "A", "However, it also helps people stay connected across the world.", "/haʊˈev.ər, ɪt ˈɔːl.soʊ helpz ˈpiː.pəl steɪ kəˈnek.tɪd əˈkrɒs ðə wɜːrld./", "Tuy nhiên, nó cũng giúp mọi người kết nối trên toàn thế giới.", 3));
        lines.add(line(c, "B", "That is true. I suppose balance is the key.", "/ðæt ɪz truː. aɪ səˈpoʊz ˈbæl.əns ɪz ðə kiː./", "Đúng vậy. Tôi cho rằng cân bằng là điều quan trọng.", 4));
        lines.add(line(c, "A", "Do you personally use social media a lot?", "/duː juː ˈpɜːr.sə.nəl.i juːz ˈsoʊ.ʃəl ˈmiː.di.ə ə lɒt?/", "Cá nhân bạn có dùng mạng xã hội nhiều không?", 5));
        lines.add(line(c, "B", "I try to limit myself to an hour a day. It is not always easy though.", "/aɪ traɪ tuː ˈlɪm.ɪt maɪˈself tuː ən ˈaʊər ə deɪ. ɪt ɪz nɒt ˈɔːl.weɪz ˈiː.zi ðoʊ./", "Tôi cố hạn chế bản thân một tiếng mỗi ngày. Nhưng không phải lúc nào cũng dễ.", 6));
        lines.add(line(c, "A", "I agree. The algorithms are designed to keep you scrolling.", "/aɪ əˈɡriː. ðə ˈæl.ɡə.rɪ.ðəmz ɑːr dɪˈzaɪnd tuː kiːp juː ˈskroʊ.lɪŋ./", "Tôi đồng ý. Thuật toán được thiết kế để giữ bạn cuộn mãi.", 7));
        lines.add(line(c, "B", "Exactly. Being aware of that makes it easier to manage your usage.", "/ɪɡˈzækt.li. ˈbiː.ɪŋ əˈweər əv ðæt meɪks ɪt ˈiː.zi.ər tuː ˈmæn.ɪdʒ jɔːr ˈjuː.sɪdʒ./", "Đúng vậy. Nhận biết điều đó giúp bạn dễ quản lý việc sử dụng hơn.", 8));

        // Conv 10: Group Project
        c = conversationIdOffset + 10;
        lines.add(line(c, "A", "We need to start planning our group project. Any ideas?", "/wiː niːd tuː stɑːrt ˈplæn.ɪŋ ˈaʊər ɡruːp ˈprɒdʒ.ekt. ˈeni aɪˈdɪəz?/", "Chúng ta cần bắt đầu lên kế hoạch cho dự án nhóm. Ai có ý tưởng không?", 1));
        lines.add(line(c, "B", "I suggest we split into sub-groups. One does research, one does design.", "/aɪ səˈdʒest wiː splɪt ˈɪn.tuː ˈsʌb.ɡruːps. wʌn dʌz rɪˈsɜːrtʃ, wʌn dʌz dɪˈzaɪn./", "Tôi gợi ý chia thành nhóm nhỏ. Một nhóm nghiên cứu, một nhóm thiết kế.", 2));
        lines.add(line(c, "A", "That sounds efficient. When should we set the first deadline?", "/ðæt saʊndz ɪˈfɪʃ.ənt. wen ʃʊd wiː set ðə fɜːrst ˈded.laɪn?/", "Nghe hiệu quả đó. Khi nào chúng ta đặt hạn chót đầu tiên?", 3));
        lines.add(line(c, "B", "How about next Wednesday for the initial research?", "/haʊ əˈbaʊt nekst ˈwenz.deɪ fɔːr ðə ɪˈnɪʃ.əl rɪˈsɜːrtʃ?/", "Thứ Tư tới để hoàn thành nghiên cứu ban đầu thì sao?", 4));
        lines.add(line(c, "A", "That works. I will take notes so we all know our responsibilities.", "/ðæt wɜːrks. aɪ wɪl teɪk noʊts soʊ wiː ɔːl noʊ ˈaʊər rɪˌspɒn.sɪˈbɪl.ɪ.tiz./", "Được. Tôi sẽ ghi chú để mọi người biết trách nhiệm của mình.", 5));
        lines.add(line(c, "B", "Great. We should also schedule weekly check-ins to track progress.", "/ɡreɪt. wiː ʃʊd ˈɔːl.soʊ ˈsked.juːl ˈwiːk.li ˈtʃek.ɪnz tuː træk ˈproʊ.ɡres./", "Tuyệt. Chúng ta cũng nên lên lịch kiểm tra hàng tuần để theo dõi tiến độ.", 6));
        lines.add(line(c, "A", "Agreed. Shall we use a group chat to communicate?", "/əˈɡriːd. ʃæl wiː juːz ə ɡruːp tʃæt tuː kəˈmjuː.nɪ.keɪt?/", "Đồng ý. Chúng ta dùng chat nhóm để liên lạc nhé?", 7));
        lines.add(line(c, "B", "Definitely. Let us set one up now and share all our materials there.", "/ˈdef.ɪ.nɪt.li. let ʌs set wʌn ʌp naʊ ænd ʃeər ɔːl ˈaʊər məˈtɪər.i.əlz ðeər./", "Nhất định rồi. Hãy tạo ngay bây giờ và chia sẻ tất cả tài liệu ở đó.", 8));

        // Conv 11: Money Problems
        c = conversationIdOffset + 11;
        lines.add(line(c, "A", "I am really struggling financially this month.", "/aɪ æm ˈriː.li ˈstrʌɡ.lɪŋ faɪˈnæn.ʃəl.i ðɪs mʌnθ./", "Tháng này tôi đang thực sự khó khăn về tài chính.", 1));
        lines.add(line(c, "B", "What happened? Did something unexpected come up?", "/wʌt ˈhæp.ənd? dɪd ˈsʌm.θɪŋ ˌʌn.ɪkˈspek.tɪd kʌm ʌp?/", "Chuyện gì xảy ra vậy? Có điều gì không mong đợi không?", 2));
        lines.add(line(c, "A", "My motorbike broke down and the repair cost a lot.", "/maɪ ˈmoʊ.tɚ.baɪk broʊk daʊn ænd ðə rɪˈpeər kɒst ə lɒt./", "Xe máy của tôi bị hỏng và chi phí sửa chữa khá tốn kém.", 3));
        lines.add(line(c, "B", "That is tough. Have you thought about setting a monthly budget?", "/ðæt ɪz tʌf. hæv juː θɔːt əˈbaʊt ˈset.ɪŋ ə ˈmʌnθ.li ˈbʌdʒ.ɪt?/", "Thật khó khăn. Bạn có nghĩ đến việc lập ngân sách hàng tháng chưa?", 4));
        lines.add(line(c, "A", "I have tried, but I always end up spending more than I planned.", "/aɪ hæv traɪd, bʌt aɪ ˈɔːl.weɪz end ʌp ˈspend.ɪŋ mɔːr ðæn aɪ plænd./", "Tôi đã thử nhưng luôn chi tiêu nhiều hơn kế hoạch.", 5));
        lines.add(line(c, "B", "Maybe tracking every expense would help you see where the money goes.", "/ˈmeɪ.bi ˈtræk.ɪŋ ˈev.ri ɪkˈspens wʊd help juː siː weər ðə ˈmʌn.i ɡoʊz./", "Có lẽ ghi lại từng khoản chi tiêu sẽ giúp bạn thấy tiền đi đâu.", 6));
        lines.add(line(c, "A", "That is a good idea. I should also try to save a little each month.", "/ðæt ɪz ə ɡʊd aɪˈdɪə. aɪ ʃʊd ˈɔːl.soʊ traɪ tuː seɪv ə ˈlɪt.əl iːtʃ mʌnθ./", "Ý hay đó. Tôi cũng nên cố tiết kiệm một chút mỗi tháng.", 7));
        lines.add(line(c, "B", "Even a small amount adds up over time. Start with what you can afford.", "/ˈiː.vən ə smɔːl əˈmaʊnt ædz ʌp ˈoʊ.vər taɪm. stɑːrt wɪð wʌt juː kæn əˈfɔːrd./", "Dù ít cũng tích lũy theo thời gian. Bắt đầu với số tiền bạn có thể tiết kiệm.", 8));

        // Conv 12: Asking for Clarification
        c = conversationIdOffset + 12;
        lines.add(line(c, "A", "Excuse me, I am not sure I understood the last part of your presentation.", "/ɪkˈskjuːz miː, aɪ æm nɒt ʃʊər aɪ ˌʌn.dɚˈstʊd ðə lɑːst pɑːrt əv jɔːr ˌprez.ənˈteɪ.ʃən./", "Xin lỗi, tôi không chắc mình đã hiểu phần cuối bài thuyết trình của bạn.", 1));
        lines.add(line(c, "B", "Of course. Which part would you like me to explain again?", "/əv kɔːrs. wɪtʃ pɑːrt wʊd juː laɪk miː tuː ɪkˈspleɪn əˈɡen?/", "Được ạ. Bạn muốn tôi giải thích lại phần nào?", 2));
        lines.add(line(c, "A", "The part about the first conditional. Could you give another example?", "/ðə pɑːrt əˈbaʊt ðə fɜːrst kənˈdɪʃ.ən.əl. kʊd juː ɡɪv əˈnʌð.ər ɪɡˈzɑːm.pəl?/", "Phần về câu điều kiện loại một. Bạn có thể cho ví dụ khác không?", 3));
        lines.add(line(c, "B", "Sure! If it snows tomorrow, I will stay at home and drink hot tea.", "/ʃʊər! ɪf ɪt snoʊz təˈmɒr.oʊ, aɪ wɪl steɪ æt hoʊm ænd drɪŋk hɒt tiː./", "Được! Nếu ngày mai tuyết rơi, tôi sẽ ở nhà và uống trà nóng.", 4));
        lines.add(line(c, "A", "I see. So the 'if' clause always uses present simple?", "/aɪ siː. soʊ ðə ɪf klɔːz ˈɔːl.weɪz ˈjuːz.ɪz ˈprez.ənt ˈsɪm.pəl?/", "Tôi hiểu rồi. Vậy mệnh đề 'if' luôn dùng hiện tại đơn à?", 5));
        lines.add(line(c, "B", "Exactly right. And the main clause uses will plus the base verb.", "/ɪɡˈzækt.li raɪt. ænd ðə meɪn klɔːz ˈjuːz.ɪz wɪl plʌs ðə beɪs vɜːrb./", "Đúng vậy. Và mệnh đề chính dùng will cộng với động từ nguyên mẫu.", 6));
        lines.add(line(c, "A", "That is much clearer now. Thank you for your patience.", "/ðæt ɪz mʌtʃ ˈklɪər.ər naʊ. θæŋk juː fɔːr jɔːr ˈpeɪ.ʃəns./", "Bây giờ rõ ràng hơn nhiều. Cảm ơn bạn đã kiên nhẫn.", 7));
        lines.add(line(c, "B", "No problem at all. Always feel free to ask if anything is unclear.", "/noʊ ˈprɒb.ləm æt ɔːl. ˈɔːl.weɪz fiːl friː tuː ɑːsk ɪf ˈeni.θɪŋ ɪz ʌnˈklɪər./", "Không vấn đề gì cả. Cứ thoải mái hỏi nếu có gì chưa rõ.", 8));

        // Conv 13: Short Presentation
        c = conversationIdOffset + 13;
        lines.add(line(c, "A", "Good morning everyone. Today I am going to talk about the importance of recycling.", "/ɡʊd ˈmɔːr.nɪŋ ˈev.ri.wʌn. tuˈdeɪ aɪ æm ˈɡoʊ.ɪŋ tuː tɔːk əˈbaʊt ðə ɪmˈpɔːr.təns əv ˈriː.saɪ.klɪŋ./", "Chào buổi sáng mọi người. Hôm nay tôi sẽ nói về tầm quan trọng của việc tái chế.", 1));
        lines.add(line(c, "A", "Firstly, recycling reduces the amount of waste that goes to landfills.", "/ˈfɜːrst.li, ˈriː.saɪ.klɪŋ rɪˈdjuːsɪz ðə əˈmaʊnt əv weɪst ðæt ɡoʊz tuː ˈlænd.fɪlz./", "Đầu tiên, tái chế giảm lượng rác thải đến bãi chôn lấp.", 2));
        lines.add(line(c, "B", "Could you explain what you mean by landfills?", "/kʊd juː ɪkˈspleɪn wʌt juː miːn baɪ ˈlænd.fɪlz?/", "Bạn có thể giải thích 'landfills' là gì không?", 3));
        lines.add(line(c, "A", "Of course. Landfills are large areas where rubbish is buried in the ground.", "/əv kɔːrs. ˈlænd.fɪlz ɑːr lɑːrdʒ ˈeər.i.əz weər ˈrʌb.ɪʃ ɪz ˈber.id ɪn ðə ɡraʊnd./", "Được ạ. Bãi chôn lấp là các khu vực lớn nơi rác thải được chôn dưới đất.", 4));
        lines.add(line(c, "A", "Furthermore, recycling saves energy and natural resources.", "/ˌfɜːr.ðɚˈmɔːr, ˈriː.saɪ.klɪŋ seɪvz ˈen.ɚ.dʒi ænd ˈnætʃ.ər.əl rɪˈzɔːr.sɪz./", "Hơn nữa, tái chế tiết kiệm năng lượng và tài nguyên thiên nhiên.", 5));
        lines.add(line(c, "A", "In addition, it creates jobs in the green economy.", "/ɪn əˈdɪʃ.ən, ɪt kriˈeɪts dʒɒbz ɪn ðə ɡriːn ɪˈkɒn.ə.mi./", "Ngoài ra, nó tạo ra việc làm trong nền kinh tế xanh.", 6));
        lines.add(line(c, "A", "To sum up, recycling benefits the environment, the economy, and future generations.", "/tuː sʌm ʌp, ˈriː.saɪ.klɪŋ ˈben.ɪ.fɪts ðə ɪnˈvaɪ.rən.mənt, ðə ɪˈkɒn.ə.mi, ænd ˈfjuː.tʃər ˌdʒen.əˈreɪ.ʃənz./", "Tóm lại, tái chế có lợi cho môi trường, kinh tế và các thế hệ tương lai.", 7));
        lines.add(line(c, "B", "Thank you. That was very well structured and informative.", "/θæŋk juː. ðæt wɒz ˈver.i wel ˈstrʌk.tʃərd ænd ɪnˈfɔːr.mə.tɪv./", "Cảm ơn. Bài thuyết trình rất có cấu trúc và thông tin.", 8));

        // Conv 14: Environment
        c = conversationIdOffset + 14;
        lines.add(line(c, "A", "I am increasingly worried about climate change.", "/aɪ æm ɪnˈkriː.sɪŋ.li ˈwʌr.id əˈbaʊt ˈklaɪ.mɪt tʃeɪndʒ./", "Tôi ngày càng lo lắng về biến đổi khí hậu.", 1));
        lines.add(line(c, "B", "Me too. I think it is the biggest challenge of our generation.", "/miː tuː. aɪ θɪŋk ɪt ɪz ðə ˈbɪɡ.ɪst ˈtʃæl.ɪndʒ əv ˈaʊər ˌdʒen.əˈreɪ.ʃən./", "Tôi cũng vậy. Tôi nghĩ đó là thách thức lớn nhất của thế hệ chúng ta.", 2));
        lines.add(line(c, "A", "What do you personally do to help the environment?", "/wʌt duː juː ˈpɜːr.sə.nəl.i duː tuː help ðə ɪnˈvaɪ.rən.mənt?/", "Cá nhân bạn làm gì để giúp đỡ môi trường?", 3));
        lines.add(line(c, "B", "I recycle, use public transport, and try to reduce plastic use.", "/aɪ rɪˈsaɪ.kəl, juːz ˈpʌb.lɪk ˈtræns.pɔːrt, ænd traɪ tuː rɪˈdjuːs ˈplæs.tɪk juːs./", "Tôi tái chế, đi phương tiện công cộng và cố giảm sử dụng nhựa.", 4));
        lines.add(line(c, "A", "Those are great habits. Do you think individual actions are enough?", "/ðoʊz ɑːr ɡreɪt ˈhæb.ɪts. duː juː θɪŋk ˌɪn.dɪˈvɪdʒ.u.əl ˈæk.ʃənz ɑːr ɪˈnʌf?/", "Những thói quen tốt đó. Bạn có nghĩ hành động cá nhân là đủ không?", 5));
        lines.add(line(c, "B", "Honestly, no. Governments and companies must take action too.", "/ˈɒn.ɪst.li, noʊ. ˈɡʌv.ɚn.mənts ænd ˈkʌm.pə.niz mʌst teɪk ˈæk.ʃən tuː./", "Thành thật mà nói, không. Chính phủ và các công ty cũng phải hành động.", 6));
        lines.add(line(c, "A", "I agree. Without systematic changes, individual efforts are not enough.", "/aɪ əˈɡriː. wɪˈðaʊt ˌsɪs.tɪˈmæt.ɪk ˈtʃeɪn.dʒɪz, ˌɪn.dɪˈvɪdʒ.u.əl ˈef.ərts ɑːr nɒt ɪˈnʌf./", "Tôi đồng ý. Không có những thay đổi có hệ thống, nỗ lực cá nhân là không đủ.", 7));
        lines.add(line(c, "B", "Exactly. We need both individual and collective action to make a real difference.", "/ɪɡˈzækt.li. wiː niːd boʊθ ˌɪn.dɪˈvɪdʒ.u.əl ænd kəˈlek.tɪv ˈæk.ʃən tuː meɪk ə rɪəl ˈdɪf.ər.əns./", "Đúng vậy. Chúng ta cần cả hành động cá nhân và tập thể để tạo ra sự khác biệt thực sự.", 8));

        // Conv 15: Job Interview
        c = conversationIdOffset + 15;
        lines.add(line(c, "A", "How did your job interview go yesterday?", "/haʊ dɪd jɔːr dʒɒb ˈɪn.tə.vjuː ɡoʊ ˈjes.tər.deɪ?/", "Buổi phỏng vấn xin việc hôm qua của bạn thế nào?", 1));
        lines.add(line(c, "B", "It went quite well, I think. Better than I expected.", "/ɪt went kwaɪt wel, aɪ θɪŋk. ˈbet.ər ðæn aɪ ɪkˈspek.tɪd./", "Khá tốt, tôi nghĩ vậy. Tốt hơn tôi mong đợi.", 2));
        lines.add(line(c, "A", "What kind of questions did they ask?", "/wʌt kaɪnd əv ˈkwes.tʃənz dɪd ðeɪ ɑːsk?/", "Họ hỏi những loại câu hỏi gì?", 3));
        lines.add(line(c, "B", "They asked about my experience and why I wanted the position.", "/ðeɪ ɑːskt əˈbaʊt maɪ ɪkˈspɪər.i.əns ænd waɪ aɪ ˈwɒn.tɪd ðə pəˈzɪʃ.ən./", "Họ hỏi về kinh nghiệm của tôi và tại sao tôi muốn vị trí đó.", 4));
        lines.add(line(c, "A", "Were there any difficult questions?", "/wɜːr ðeər ˈeni ˈdɪf.ɪ.kəlt ˈkwes.tʃənz?/", "Có câu hỏi khó nào không?", 5));
        lines.add(line(c, "B", "Yes. They asked me to describe my biggest weakness. I found that challenging.", "/jes. ðeɪ ɑːskt miː tuː dɪˈskraɪb maɪ ˈbɪɡ.ɪst ˈwiːk.nɪs. aɪ faʊnd ðæt ˈtʃæl.ɪn.dʒɪŋ./", "Có. Họ yêu cầu tôi mô tả điểm yếu lớn nhất của mình. Tôi thấy điều đó thách thức.", 6));
        lines.add(line(c, "A", "What did you say?", "/wʌt dɪd juː seɪ?/", "Bạn đã nói gì?", 7));
        lines.add(line(c, "B", "I said I sometimes take on too much work, but I am learning to delegate better.", "/aɪ sed aɪ ˈsʌm.taɪmz teɪk ɒn tuː mʌtʃ wɜːrk, bʌt aɪ æm ˈlɜːr.nɪŋ tuː ˈdel.ɪ.ɡeɪt ˈbet.ər./", "Tôi nói tôi đôi khi nhận quá nhiều việc, nhưng tôi đang học cách ủy quyền tốt hơn.", 8));

        // Conv 16: Difficult Situation
        c = conversationIdOffset + 16;
        lines.add(line(c, "A", "Have you ever been in a situation where you had to make a really difficult decision?", "/hæv juː ˈev.ər biːn ɪn ə ˌsɪtʃ.uˈeɪ.ʃən weər juː hæd tuː meɪk ə ˈriː.li ˈdɪf.ɪ.kəlt dɪˈsɪʒ.ən?/", "Bạn đã từng ở trong tình huống phải đưa ra quyết định thực sự khó khăn chưa?", 1));
        lines.add(line(c, "B", "Yes, actually. A few years ago I had to choose between two job offers.", "/jes, ˈæk.tʃu.ə.li. ə fjuː jɪərz əˈɡoʊ aɪ hæd tuː tʃuːz bɪˈtwiːn tuː dʒɒb ˈɒf.ərz./", "Có, thực ra là có. Vài năm trước tôi phải chọn giữa hai lời mời làm việc.", 2));
        lines.add(line(c, "A", "That must have been stressful. What were your options?", "/ðæt mʌst hæv biːn ˈstres.fəl. wʌt wɜːr jɔːr ˈɒp.ʃənz?/", "Hẳn là căng thẳng. Các lựa chọn của bạn là gì?", 3));
        lines.add(line(c, "B", "One was a high-paying job far from home. The other paid less but was closer to my family.", "/wʌn wɒz ə haɪ ˈpeɪ.ɪŋ dʒɒb fɑːr frɒm hoʊm. ðə ˈʌð.ər peɪd les bʌt wɒz ˈkloʊ.sər tuː maɪ ˈfæm.ə.li./", "Một là việc lương cao nhưng xa nhà. Cái kia lương thấp hơn nhưng gần gia đình.", 4));
        lines.add(line(c, "A", "Which did you choose in the end?", "/wɪtʃ dɪd juː tʃuːz ɪn ðə end?/", "Cuối cùng bạn đã chọn cái nào?", 5));
        lines.add(line(c, "B", "I chose the one close to home. At the time it was hard, but I have no regrets.", "/aɪ tʃoʊz ðə wʌn kloʊs tuː hoʊm. æt ðə taɪm ɪt wɒz hɑːrd, bʌt aɪ hæv noʊ rɪˈɡrets./", "Tôi chọn cái gần nhà. Lúc đó khó khăn nhưng tôi không hối tiếc.", 6));
        lines.add(line(c, "A", "That took real courage. How did you cope with the uncertainty?", "/ðæt tʊk rɪəl ˈkɜːr.ɪdʒ. haʊ dɪd juː koʊp wɪð ðə ʌnˈsɜːr.tən.ti?/", "Điều đó cần dũng khí thực sự. Bạn đối phó với sự không chắc chắn như thế nào?", 7));
        lines.add(line(c, "B", "I talked to people I trusted and took time to reflect before deciding.", "/aɪ tɔːkt tuː ˈpiː.pəl aɪ ˈtrʌs.tɪd ænd tʊk taɪm tuː rɪˈflekt bɪˈfɔːr dɪˈsaɪ.dɪŋ./", "Tôi nói chuyện với những người tôi tin tưởng và dành thời gian suy ngẫm trước khi quyết định.", 8));

        return lines;
    }

    private static ConversationLine line(int convId, String role, String en, String ipa, String vi, int order) {
        return new ConversationLine(convId, role, en, ipa, vi, order);
    }
}
