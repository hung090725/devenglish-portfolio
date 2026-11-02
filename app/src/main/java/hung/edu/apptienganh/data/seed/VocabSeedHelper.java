package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.Vocabulary;

final class VocabSeedHelper {

    private VocabSeedHelper() {}

    static void add(List<Vocabulary> list, String word, String ipaUk, String ipaUs, String type,
                    String meaningVi, String context, String exEn, String exVi,
                    String collocations, String mistake, String topic) {
        list.add(new Vocabulary(word, ipaUk, ipaUs, type, meaningVi, context, exEn, exVi,
                collocations, mistake, "A0", topic, false, 0));
    }

    static List<Vocabulary> buildAll() {
        List<Vocabulary> list = new ArrayList<>();
        addGreetings(list);
        addFamily(list);
        addSchool(list);
        addFood(list);
        addDailyRoutine(list);
        addNumbers(list);
        addTime(list);
        addVerbs(list);
        addAdjectives(list);
        addClassroom(list);
        return list;
    }

    private static void addGreetings(List<Vocabulary> list) {
        add(list, "hello", "/həˈləʊ/", "/həˈloʊ/", "interjection", "xin chào",
                "dùng khi gặp ai đó", "Hello, nice to meet you.", "Xin chào, rất vui được gặp bạn.",
                "say hello, hello everyone", "Không đọc là 'he-lô' 3 âm tiết rời.", "Greetings");
        add(list, "hi", "/haɪ/", "/haɪ/", "interjection", "chào (thân mật)",
                "dùng với bạn bè", "Hi, how are you?", "Chào, bạn khỏe không?",
                "hi there, hi everyone", "Âm /aɪ/ kéo dài, không đọc là 'hi' ngắn kiểu Việt.", "Greetings");
        add(list, "goodbye", "/ˌɡʊdˈbaɪ/", "/ˌɡʊdˈbaɪ/", "interjection", "tạm biệt",
                "khi chia tay", "Goodbye, see you tomorrow.", "Tạm biệt, hẹn ngày mai.",
                "say goodbye, wave goodbye", "Nhấn âm 'good-bye', không nuốt âm /d/.", "Greetings");
        add(list, "bye", "/baɪ/", "/baɪ/", "interjection", "tạm biệt (ngắn)",
                "thân mật hơn goodbye", "Bye! Have a nice day.", "Tạm biệt! Chúc một ngày tốt lành.",
                "bye bye, see you", "Âm /aɪ/ giống từ 'hi'.", "Greetings");
        add(list, "please", "/pliːz/", "/pliːz/", "adverb", "làm ơn, xin vui lòng",
                "khi nhờ vả lịch sự", "Please sit down.", "Xin mời ngồi.",
                "please help, yes please", "Không đọc là 'pli-zơ' mà là /pliːz/.", "Greetings");
        add(list, "thank you", "/ˈθæŋk juː/", "/ˈθæŋk juː/", "phrase", "cảm ơn",
                "bày tỏ lòng biết ơn", "Thank you very much.", "Cảm ơn bạn rất nhiều.",
                "thank you so much, thanks a lot", "Âm /θ/ đầu từ 'thank' cần lưỡi giữa răng.", "Greetings");
        add(list, "thanks", "/θæŋks/", "/θæŋks/", "interjection", "cảm ơn (ngắn)",
                "thân mật", "Thanks for your help.", "Cảm ơn vì sự giúp đỡ của bạn.",
                "many thanks, thanks a lot", "Nhớ bật âm /s/ cuối từ.", "Greetings");
        add(list, "sorry", "/ˈsɒr.i/", "/ˈsɑːr.i/", "adjective", "xin lỗi",
                "khi xin lỗi ai đó", "I am sorry for being late.", "Tôi xin lỗi vì đến muộn.",
                "sorry about that, I am sorry", "Không đọc là 'sô-ri' mà /ˈsɒr.i/.", "Greetings");
        add(list, "welcome", "/ˈwel.kəm/", "/ˈwel.kəm/", "adjective", "chào mừng",
                "khi đón khách", "Welcome to our school.", "Chào mừng đến trường chúng tôi.",
                "you are welcome, warm welcome", "Nhấn âm tiết đầu WEL-come.", "Greetings");
        add(list, "excuse me", "/ɪkˈskjuːz miː/", "/ɪkˈskjuːz miː/", "phrase", "xin lỗi, làm ơn",
                "khi chen ngang hoặc hỏi đường", "Excuse me, where is the bathroom?", "Xin lỗi, nhà vệ sinh ở đâu?",
                "excuse me sir, excuse me please", "Trọng âm rơi vào 'cuse'.", "Greetings");
        add(list, "good morning", "/ˌɡʊd ˈmɔː.nɪŋ/", "/ˌɡʊd ˈmɔːr.nɪŋ/", "phrase", "chào buổi sáng",
                "buổi sáng đến trưa", "Good morning, teacher.", "Chào buổi sáng, thầy.",
                "good morning everyone", "Âm /ŋ/ cuối 'morning' cần ngân.", "Greetings");
        add(list, "good night", "/ˌɡʊd ˈnaɪt/", "/ˌɡʊd ˈnaɪt/", "phrase", "chúc ngủ ngon",
                "trước khi đi ngủ", "Good night, mom.", "Chúc mẹ ngủ ngon.",
                "say good night, good night sleep well", "Không dùng 'good night' để chào buổi tối khi mới gặp.", "Greetings");
    }

    private static void addFamily(List<Vocabulary> list) {
        add(list, "father", "/ˈfɑː.ðər/", "/ˈfɑː.ðɚ/", "noun", "bố, cha",
                "người cha trong gia đình", "My father is kind.", "Bố tôi rất tốt bụng.",
                "dear father, my father", "Âm /ð/ giữa từ cần rung lưỡi.", "Family");
        add(list, "mother", "/ˈmʌð.ər/", "/ˈmʌð.ɚ/", "noun", "mẹ",
                "người mẹ trong gia đình", "My mother cooks well.", "Mẹ tôi nấu ăn rất ngon.",
                "loving mother, my mother", "Âm đầu /mʌ/ không đọc là 'mo'.", "Family");
        add(list, "brother", "/ˈbrʌð.ər/", "/ˈbrʌð.ɚ/", "noun", "anh/em trai",
                "con trai cùng cha mẹ hoặc một trong hai", "I have one brother.", "Tôi có một người anh trai.",
                "older brother, little brother", "Phát âm /br/ liền mạch.", "Family");
        add(list, "sister", "/ˈsɪs.tər/", "/ˈsɪs.tɚ/", "noun", "chị/em gái",
                "con gái cùng cha mẹ", "She is my sister.", "Cô ấy là chị gái tôi.",
                "older sister, twin sister", "Âm /s/ ở giữa phải rõ.", "Family");
        add(list, "family", "/ˈfæm.ə.li/", "/ˈfæm.ə.li/", "noun", "gia đình",
                "những người thân ruột thịt", "I love my family.", "Tôi yêu gia đình mình.",
                "big family, happy family", "Đọc 2 âm tiết chính: FAM-ily.", "Family");
        add(list, "parents", "/ˈpeə.rənts/", "/ˈper.ənts/", "noun", "bố mẹ",
                "cha và mẹ", "My parents live in Hanoi.", "Bố mẹ tôi sống ở Hà Nội.",
                "loving parents, both parents", "Âm /ts/ cuối từ cần bật hơi.", "Family");
        add(list, "son", "/sʌn/", "/sʌn/", "noun", "con trai",
                "con trai của ai đó", "He is their son.", "Anh ấy là con trai của họ.",
                "only son, young son", "Không nhầm với 'sun' (mặt trời) khi viết.", "Family");
        add(list, "daughter", "/ˈdɔː.tər/", "/ˈdɑː.tɚ/", "noun", "con gái",
                "con gái của ai đó", "She is their daughter.", "Cô ấy là con gái của họ.",
                "young daughter, only daughter", "Âm /ɔː/ UK khác /ɑː/ US.", "Family");
        add(list, "grandfather", "/ˈɡræn.fɑː.ðər/", "/ˈɡræn.fɑː.ðɚ/", "noun", "ông",
                "cha của bố hoặc mẹ", "My grandfather is seventy.", "Ông tôi 70 tuổi.",
                "dear grandfather, visit grandfather", "Nhấn GRAND-father.", "Family");
        add(list, "grandmother", "/ˈɡræn.mʌð.ər/", "/ˈɡræn.mʌð.ɚ/", "noun", "bà",
                "mẹ của bố hoặc mẹ", "My grandmother tells stories.", "Bà tôi kể chuyện.",
                "dear grandmother, visit grandmother", "Âm /ð/ ở giữa cần rung.", "Family");
        add(list, "husband", "/ˈhʌz.bənd/", "/ˈhʌz.bənd/", "noun", "chồng",
                "người đàn ông đã kết hôn", "Her husband is a doctor.", "Chồng cô ấy là bác sĩ.",
                "loving husband, her husband", "Âm /z/ ở giữa, không đọc /s/.", "Family");
        add(list, "wife", "/waɪf/", "/waɪf/", "noun", "vợ",
                "người phụ nữ đã kết hôn", "His wife is a teacher.", "Vợ anh ấy là giáo viên.",
                "loving wife, his wife", "Số nhiều là wives /waɪvz/.", "Family");
    }

    private static void addSchool(List<Vocabulary> list) {
        add(list, "student", "/ˈstjuː.dənt/", "/ˈstuː.dənt/", "noun", "học sinh, sinh viên",
                "người đang đi học", "I am a student.", "Tôi là sinh viên.",
                "good student, university student", "Chú ý âm cuối /t/.", "School");
        add(list, "teacher", "/ˈtiː.tʃər/", "/ˈtiː.tʃɚ/", "noun", "giáo viên",
                "người dạy học", "Our teacher is friendly.", "Giáo viên của chúng tôi thân thiện.",
                "English teacher, math teacher", "Âm /tʃ/ mạnh ở giữa.", "School");
        add(list, "school", "/skuːl/", "/skuːl/", "noun", "trường học",
                "nơi học tập", "I go to school every day.", "Tôi đi học mỗi ngày.",
                "primary school, high school", "Có âm /l/ cuối từ.", "School");
        add(list, "class", "/klɑːs/", "/klæs/", "noun", "lớp học",
                "nhóm học sinh hoặc phòng học", "Our class has thirty students.", "Lớp chúng tôi có 30 học sinh.",
                "English class, in class", "Âm /s/ cuối không được bỏ.", "School");
        add(list, "classroom", "/ˈklɑːs.ruːm/", "/ˈklæs.ruːm/", "noun", "phòng học",
                "phòng dùng để học", "The classroom is big.", "Phòng học rất rộng.",
                "clean classroom, enter classroom", "Ghép class + room.", "School");
        add(list, "book", "/bʊk/", "/bʊk/", "noun", "sách",
                "để đọc hoặc học", "Open your book, please.", "Mở sách ra, làm ơn.",
                "English book, read a book", "Âm /k/ cuối bật hơi.", "School");
        add(list, "pen", "/pen/", "/pen/", "noun", "bút mực",
                "dụng cụ viết", "I need a pen.", "Tôi cần một cây bút.",
                "blue pen, ballpoint pen", "Âm /p/ bật hơi đầu từ.", "School");
        add(list, "pencil", "/ˈpen.səl/", "/ˈpen.səl/", "noun", "bút chì",
                "bút để viết và tẩy", "Write with a pencil.", "Viết bằng bút chì.",
                "sharp pencil, pencil case", "Âm /s/ nhẹ ở giữa.", "School");
        add(list, "desk", "/desk/", "/desk/", "noun", "bàn học",
                "bàn ngồi học", "Sit at your desk.", "Ngồi vào bàn của bạn.",
                "clean desk, school desk", "Âm /sk/ cuối rõ.", "School");
        add(list, "homework", "/ˈhəʊm.wɜːk/", "/ˈhoʊm.wɝːk/", "noun", "bài tập về nhà",
                "bài làm ở nhà", "I do my homework after school.", "Tôi làm bài tập sau giờ học.",
                "do homework, finish homework", "Không tách thành 'home work' khi nói.", "School");
        add(list, "lesson", "/ˈles.ən/", "/ˈles.ən/", "noun", "bài học",
                "một đơn vị học", "Today we have an English lesson.", "Hôm nay chúng ta có bài tiếng Anh.",
                "English lesson, first lesson", "Âm /ʃ/ không có, đọc LES-son.", "School");
        add(list, "university", "/ˌjuː.nɪˈvɜː.sə.ti/", "/ˌjuː.nəˈvɝː.sə.ti/", "noun", "đại học",
                "trường sau trung học", "She studies at university.", "Cô ấy học đại học.",
                "go to university, university student", "Trọng âm: uniVERsity.", "School");
    }

    private static void addFood(List<Vocabulary> list) {
        add(list, "apple", "/ˈæp.əl/", "/ˈæp.əl/", "noun", "quả táo", "trái cây phổ biến",
                "I eat an apple every day.", "Tôi ăn một quả táo mỗi ngày.",
                "red apple, fresh apple", "Âm /æ/ mở miệng rộng.", "Food");
        add(list, "bread", "/bred/", "/bred/", "noun", "bánh mì", "thức ăn cơ bản",
                "I like bread and butter.", "Tôi thích bánh mì và bơ.",
                "fresh bread, slice of bread", "Âm /e/ ngắn, không đọc 'bơ-rét'.", "Food");
        add(list, "water", "/ˈwɔː.tər/", "/ˈwɑː.t̬ɚ/", "noun", "nước", "đồ uống",
                "Can I have some water?", "Cho tôi xin chút nước được không?",
                "drinking water, cold water", "US thường nghe như 'wa-der'.", "Food");
        add(list, "milk", "/mɪlk/", "/mɪlk/", "noun", "sữa", "đồ uống bổ dưỡng",
                "I drink milk every morning.", "Tôi uống sữa mỗi sáng.",
                "fresh milk, hot milk", "Âm /l/ và /k/ cuối từ.", "Food");
        add(list, "rice", "/raɪs/", "/raɪs/", "noun", "cơm, gạo", "thức ăn chính của người Việt",
                "We eat rice for lunch.", "Chúng tôi ăn cơm vào bữa trưa.",
                "fried rice, white rice", "Không bỏ âm /s/ cuối.", "Food");
        add(list, "chicken", "/ˈtʃɪk.ɪn/", "/ˈtʃɪk.ən/", "noun", "thịt gà, con gà", "món ăn phổ biến",
                "I like fried chicken.", "Tôi thích gà rán.",
                "fried chicken, roast chicken", "Âm đầu /tʃ/ như 'ch'.", "Food");
        add(list, "egg", "/eɡ/", "/eɡ/", "noun", "trứng", "thực phẩm",
                "I eat an egg for breakfast.", "Tôi ăn một quả trứng cho bữa sáng.",
                "boiled egg, fried egg", "Âm /ɡ/ cuối bật hơi.", "Food");
        add(list, "coffee", "/ˈkɒf.i/", "/ˈkɑː.fi/", "noun", "cà phê", "đồ uống",
                "I drink coffee in the morning.", "Tôi uống cà phê buổi sáng.",
                "hot coffee, black coffee", "Âm /f/ nhẹ giữa từ.", "Food");
        add(list, "tea", "/tiː/", "/tiː/", "noun", "trà", "đồ uống",
                "Would you like some tea?", "Bạn có muốn uống trà không?",
                "green tea, hot tea", "Âm /iː/ kéo dài.", "Food");
        add(list, "juice", "/dʒuːs/", "/dʒuːs/", "noun", "nước ép", "đồ uống từ trái cây",
                "Orange juice is healthy.", "Nước cam rất tốt cho sức khỏe.",
                "orange juice, apple juice", "Âm /s/ cuối không bỏ.", "Food");
        add(list, "meat", "/miːt/", "/miːt/", "noun", "thịt", "thực phẩm từ động vật",
                "I do not eat much meat.", "Tôi không ăn nhiều thịt.",
                "fresh meat, eat meat", "Âm /t/ cuối rõ.", "Food");
        add(list, "fish", "/fɪʃ/", "/fɪʃ/", "noun", "cá", "thủy sản",
                "Fish is good for health.", "Cá tốt cho sức khỏe.",
                "fresh fish, fried fish", "Âm /ʃ/ cuối như 'sh'.", "Food");
    }

    private static void addDailyRoutine(List<Vocabulary> list) {
        add(list, "wake up", "/weɪk ʌp/", "/weɪk ʌp/", "verb", "thức dậy", "buổi sáng",
                "I wake up at six.", "Tôi thức dậy lúc sáu giờ.",
                "wake up early, wake up late", "Nối âm wake-up tự nhiên.", "Daily routine");
        add(list, "sleep", "/sliːp/", "/sliːp/", "verb", "ngủ", "nghỉ đêm",
                "I sleep eight hours.", "Tôi ngủ tám tiếng.",
                "go to sleep, sleep well", "Âm /p/ cuối khép môi.", "Daily routine");
        add(list, "eat", "/iːt/", "/iːt/", "verb", "ăn", "hành động ăn uống",
                "We eat breakfast at seven.", "Chúng tôi ăn sáng lúc bảy giờ.",
                "eat breakfast, eat healthy", "Âm /iː/ dài trước /t/.", "Daily routine");
        add(list, "drink", "/drɪŋk/", "/drɪŋk/", "verb", "uống", "hành động uống",
                "I drink water every day.", "Tôi uống nước mỗi ngày.",
                "drink water, drink coffee", "Âm /ŋ/ trước /k/.", "Daily routine");
        add(list, "study", "/ˈstʌd.i/", "/ˈstʌd.i/", "verb", "học", "tiếp thu kiến thức",
                "I study English at home.", "Tôi học tiếng Anh ở nhà.",
                "study hard, study English", "Âm /st/ đầu từ rõ.", "Daily routine");
        add(list, "work", "/wɜːk/", "/wɝːk/", "verb", "làm việc", "công việc hàng ngày",
                "My father works in an office.", "Bố tôi làm việc ở văn phòng.",
                "go to work, hard work", "Âm /ɜː/ uốn lưỡi.", "Daily routine");
        add(list, "play", "/pleɪ/", "/pleɪ/", "verb", "chơi", "giải trí",
                "Children play in the park.", "Trẻ em chơi ở công viên.",
                "play games, play soccer", "Âm /pl/ bật môi.", "Daily routine");
        add(list, "read", "/riːd/", "/riːd/", "verb", "đọc", "đọc sách báo",
                "I read books every night.", "Tôi đọc sách mỗi tối.",
                "read books, read aloud", "Âm /iː/ dài.", "Daily routine");
        add(list, "write", "/raɪt/", "/raɪt/", "verb", "viết", "viết chữ",
                "Please write your name.", "Hãy viết tên của bạn.",
                "write a letter, write down", "Âm /t/ cuối bật hơi.", "Daily routine");
        add(list, "listen", "/ˈlɪs.ən/", "/ˈlɪs.ən/", "verb", "nghe", "lắng nghe",
                "Listen to the teacher.", "Hãy nghe giáo viên.",
                "listen carefully, listen to music", "Âm /sn/ cuối nhẹ.", "Daily routine");
        add(list, "speak", "/spiːk/", "/spiːk/", "verb", "nói", "giao tiếp bằng lời",
                "Speak English every day.", "Nói tiếng Anh mỗi ngày.",
                "speak English, speak slowly", "Âm /k/ cuối bật hơi.", "Daily routine");
        add(list, "watch", "/wɒtʃ/", "/wɑːtʃ/", "verb", "xem", "xem TV hoặc phim",
                "I watch TV in the evening.", "Tôi xem TV buổi tối.",
                "watch TV, watch a movie", "Âm /tʃ/ cuối như 'ch'.", "Daily routine");
    }

    private static void addNumbers(List<Vocabulary> list) {
        add(list, "one", "/wʌn/", "/wʌn/", "number", "một", "số 1",
                "I have one brother.", "Tôi có một người anh trai.",
                "one person, one more", "Bắt đầu bằng /w/.", "Numbers");
        add(list, "two", "/tuː/", "/tuː/", "number", "hai", "số 2",
                "Two students are here.", "Có hai học sinh ở đây.",
                "two books, two hours", "Âm /uː/ dài.", "Numbers");
        add(list, "three", "/θriː/", "/θriː/", "number", "ba", "số 3",
                "I have three cats.", "Tôi có ba con mèo.",
                "three days, three times", "Âm /θ/ đầu từ.", "Numbers");
        add(list, "four", "/fɔːr/", "/fɔːr/", "number", "bốn", "số 4",
                "Four chairs are in the room.", "Có bốn cái ghế trong phòng.",
                "four people, four years", "Âm /f/ đầu từ.", "Numbers");
        add(list, "five", "/faɪv/", "/faɪv/", "number", "năm", "số 5",
                "She is five years old.", "Cô bé năm tuổi.",
                "five minutes, five books", "Âm /v/ cuối rung môi.", "Numbers");
        add(list, "six", "/sɪks/", "/sɪks/", "number", "sáu", "số 6",
                "Six apples are on the table.", "Sáu quả táo trên bàn.",
                "six days, six o'clock", "Âm /ks/ cuối.", "Numbers");
        add(list, "seven", "/ˈsev.ən/", "/ˈsev.ən/", "number", "bảy", "số 7",
                "We start at seven.", "Chúng tôi bắt đầu lúc bảy giờ.",
                "seven days, seven years", "Âm /v/ giữa từ.", "Numbers");
        add(list, "eight", "/eɪt/", "/eɪt/", "number", "tám", "số 8",
                "Eight students are absent.", "Tám học sinh vắng mặt.",
                "eight hours, eight people", "Chỉ một âm tiết /eɪt/.", "Numbers");
        add(list, "nine", "/naɪn/", "/naɪn/", "number", "chín", "số 9",
                "Nine books are on the shelf.", "Chín cuốn sách trên kệ.",
                "nine years old, nine o'clock", "Âm /aɪ/ dài.", "Numbers");
        add(list, "ten", "/ten/", "/ten/", "number", "mười", "số 10",
                "Ten minutes later.", "Mười phút sau.",
                "ten years, ten dollars", "Âm /t/ đầu bật hơi.", "Numbers");
        add(list, "eleven", "/ɪˈlev.ən/", "/ɪˈlev.ən/", "number", "mười một", "số 11",
                "I am eleven years old.", "Tôi mười một tuổi.",
                "eleven students, at eleven", "Trọng âm eLEVen.", "Numbers");
        add(list, "twenty", "/ˈtwen.ti/", "/ˈtwen.ti/", "number", "hai mươi", "số 20",
                "She is twenty years old.", "Cô ấy hai mươi tuổi.",
                "twenty minutes, twenty people", "Âm /tw/ đầu từ.", "Numbers");
    }

    private static void addTime(List<Vocabulary> list) {
        add(list, "morning", "/ˈmɔː.nɪŋ/", "/ˈmɔːr.nɪŋ/", "noun", "buổi sáng", "từ sáng đến trưa",
                "Good morning!", "Chào buổi sáng!",
                "early morning, this morning", "Âm /ŋ/ cuối.", "Time");
        add(list, "afternoon", "/ˌɑːf.təˈnuːn/", "/ˌæf.tɚˈnuːn/", "noun", "buổi chiều", "sau trưa",
                "Good afternoon!", "Chào buổi chiều!",
                "this afternoon, late afternoon", "Trọng âm afterNOON.", "Time");
        add(list, "evening", "/ˈiːv.nɪŋ/", "/ˈiːv.nɪŋ/", "noun", "buổi tối", "sau chiều",
                "Good evening!", "Chào buổi tối!",
                "this evening, Friday evening", "2 âm tiết: EVE-ning.", "Time");
        add(list, "night", "/naɪt/", "/naɪt/", "noun", "đêm", "khi trời tối",
                "Good night!", "Chúc ngủ ngon!",
                "at night, last night", "Âm /t/ cuối.", "Time");
        add(list, "today", "/təˈdeɪ/", "/təˈdeɪ/", "adverb", "hôm nay", "ngày hiện tại",
                "Today is Monday.", "Hôm nay là thứ Hai.",
                "today morning, see you today", "Trọng âm toDAY.", "Time");
        add(list, "tomorrow", "/təˈmɒr.əʊ/", "/təˈmɑːr.oʊ/", "adverb", "ngày mai", "ngày kế tiếp",
                "See you tomorrow.", "Hẹn gặp ngày mai.",
                "tomorrow morning, until tomorrow", "Trọng âm toMORrow.", "Time");
        add(list, "yesterday", "/ˈjes.tə.deɪ/", "/ˈjes.tɚ.deɪ/", "adverb", "hôm qua", "ngày trước",
                "I met him yesterday.", "Tôi gặp anh ấy hôm qua.",
                "yesterday evening, since yesterday", "Trọng âm YESterday.", "Time");
        add(list, "hour", "/ˈaʊər/", "/ˈaʊɚ/", "noun", "giờ", "đơn vị thời gian",
                "One hour has sixty minutes.", "Một giờ có sáu mươi phút.",
                "half an hour, per hour", "Âm /aʊ/ như 'ao'.", "Time");
        add(list, "minute", "/ˈmɪn.ɪt/", "/ˈmɪn.ət/", "noun", "phút", "đơn vị thời gian",
                "Wait five minutes, please.", "Đợi năm phút, làm ơn.",
                "five minutes, just a minute", "Không nhầm với 'minute' nhỏ (mai-nút).", "Time");
        add(list, "clock", "/klɒk/", "/klɑːk/", "noun", "đồng hồ", "xem giờ",
                "Look at the clock.", "Nhìn đồng hồ đi.",
                "wall clock, alarm clock", "Âm /k/ cuối.", "Time");
        add(list, "time", "/taɪm/", "/taɪm/", "noun", "thời gian, giờ giấc", "khái niệm thời gian",
                "What time is it?", "Mấy giờ rồi?",
                "free time, on time", "Âm /m/ cuối.", "Time");
        add(list, "week", "/wiːk/", "/wiːk/", "noun", "tuần", "bảy ngày",
                "I study English three times a week.", "Tôi học tiếng Anh ba lần một tuần.",
                "this week, next week", "Âm /k/ cuối.", "Time");
    }

    private static void addVerbs(List<Vocabulary> list) {
        add(list, "go", "/ɡəʊ/", "/ɡoʊ/", "verb", "đi", "di chuyển",
                "I go to school by bus.", "Tôi đi học bằng xe buýt.",
                "go home, go out", "Âm /ɡ/ không đọc /k/.", "Common verbs");
        add(list, "come", "/kʌm/", "/kʌm/", "verb", "đến", "di chuyển về phía người nói",
                "Come here, please.", "Đến đây, làm ơn.",
                "come in, come back", "Âm /ʌ/ ngắn.", "Common verbs");
        add(list, "like", "/laɪk/", "/laɪk/", "verb", "thích", "sở thích",
                "I like English.", "Tôi thích tiếng Anh.",
                "like music, really like", "Âm /k/ cuối.", "Common verbs");
        add(list, "love", "/lʌv/", "/lʌv/", "verb", "yêu, rất thích", "tình cảm mạnh",
                "I love my family.", "Tôi yêu gia đình mình.",
                "love music, love cooking", "Âm /v/ cuối rung môi.", "Common verbs");
        add(list, "want", "/wɒnt/", "/wɑːnt/", "verb", "muốn", "mong muốn",
                "I want a cup of tea.", "Tôi muốn một tách trà.",
                "want to go, really want", "Âm /nt/ cuối.", "Common verbs");
        add(list, "need", "/niːd/", "/niːd/", "verb", "cần", "cần thiết",
                "I need help.", "Tôi cần giúp đỡ.",
                "need help, need time", "Âm /d/ cuối.", "Common verbs");
        add(list, "have", "/hæv/", "/hæv/", "verb", "có", "sở hữu",
                "I have a book.", "Tôi có một cuốn sách.",
                "have breakfast, have fun", "Âm /v/ cuối.", "Common verbs");
        add(list, "live", "/lɪv/", "/lɪv/", "verb", "sống", "nơi cư trú",
                "I live in Hanoi.", "Tôi sống ở Hà Nội.",
                "live here, live alone", "Động từ đọc /lɪv/, danh từ 'live' /laɪv/.", "Common verbs");
        add(list, "know", "/nəʊ/", "/noʊ/", "verb", "biết", "có kiến thức",
                "I know her name.", "Tôi biết tên cô ấy.",
                "know well, don't know", "Âm /k/ không phát âm.", "Common verbs");
        add(list, "think", "/θɪŋk/", "/θɪŋk/", "verb", "nghĩ", "suy nghĩ",
                "I think you are right.", "Tôi nghĩ bạn đúng.",
                "think about, think so", "Âm /θ/ đầu từ.", "Common verbs");
        add(list, "help", "/help/", "/help/", "verb", "giúp", "hỗ trợ",
                "Can you help me?", "Bạn có thể giúp tôi không?",
                "help me, help with", "Âm /p/ cuối.", "Common verbs");
        add(list, "learn", "/lɜːn/", "/lɝːn/", "verb", "học", "tiếp thu kiến thức mới",
                "I learn English every day.", "Tôi học tiếng Anh mỗi ngày.",
                "learn English, learn fast", "Âm /ɜː/ uốn lưỡi.", "Common verbs");
    }

    private static void addAdjectives(List<Vocabulary> list) {
        add(list, "good", "/ɡʊd/", "/ɡʊd/", "adjective", "tốt", "chất lượng cao",
                "She is a good student.", "Cô ấy là học sinh giỏi.",
                "good job, good morning", "Âm /ʊ/ ngắn.", "Common adjectives");
        add(list, "bad", "/bæd/", "/bæd/", "adjective", "xấu, tệ", "không tốt",
                "That is a bad idea.", "Đó là ý tưởng tồi.",
                "bad weather, too bad", "Âm /æ/ mở miệng.", "Common adjectives");
        add(list, "big", "/bɪɡ/", "/bɪɡ/", "adjective", "to, lớn", "kích thước",
                "This is a big house.", "Đây là ngôi nhà lớn.",
                "big city, big family", "Âm /ɡ/ cuối.", "Common adjectives");
        add(list, "small", "/smɔːl/", "/smɔːl/", "adjective", "nhỏ", "kích thước",
                "I have a small bag.", "Tôi có một cái túi nhỏ.",
                "small town, small class", "Âm /ɔː/ UK.", "Common adjectives");
        add(list, "happy", "/ˈhæp.i/", "/ˈhæp.i/", "adjective", "vui, hạnh phúc", "cảm xúc tích cực",
                "I am happy today.", "Hôm nay tôi vui.",
                "happy birthday, feel happy", "Âm /æ/ đầu từ.", "Common adjectives");
        add(list, "sad", "/sæd/", "/sæd/", "adjective", "buồn", "cảm xúc tiêu cực",
                "She looks sad.", "Cô ấy trông buồn.",
                "feel sad, very sad", "Âm /æ/ ngắn.", "Common adjectives");
        add(list, "new", "/njuː/", "/nuː/", "adjective", "mới", "chưa cũ",
                "This is my new book.", "Đây là sách mới của tôi.",
                "new student, brand new", "Âm /njuː/ UK.", "Common adjectives");
        add(list, "old", "/əʊld/", "/oʊld/", "adjective", "cũ, già", "tuổi hoặc đồ cũ",
                "My grandfather is old.", "Ông tôi già rồi.",
                "old friend, how old", "Âm /ld/ cuối.", "Common adjectives");
        add(list, "young", "/jʌŋ/", "/jʌŋ/", "adjective", "trẻ", "tuổi trẻ",
                "She is young and active.", "Cô ấy trẻ và năng động.",
                "young people, young student", "Âm /j/ đầu như 'd'.", "Common adjectives");
        add(list, "beautiful", "/ˈbjuː.tɪ.fəl/", "/ˈbjuː.t̬ə.fəl/", "adjective", "đẹp", "vẻ ngoài",
                "Vietnam is beautiful.", "Việt Nam rất đẹp.",
                "beautiful day, beautiful place", "3 âm tiết: BEAU-ti-ful.", "Common adjectives");
        add(list, "easy", "/ˈiː.zi/", "/ˈiː.zi/", "adjective", "dễ", "không khó",
                "English is easy for me.", "Tiếng Anh dễ với tôi.",
                "easy question, very easy", "Âm /z/ giữa từ.", "Common adjectives");
        add(list, "difficult", "/ˈdɪf.ɪ.kəlt/", "/ˈdɪf.ə.kəlt/", "adjective", "khó", "không dễ",
                "This lesson is difficult.", "Bài học này khó.",
                "difficult question, too difficult", "Trọng âm DIFficult.", "Common adjectives");
    }

    private static void addClassroom(List<Vocabulary> list) {
        add(list, "answer", "/ˈɑːn.sər/", "/ˈæn.sɚ/", "noun", "câu trả lời", "trong lớp học",
                "Please give your answer.", "Hãy đưa ra câu trả lời của bạn.",
                "correct answer, short answer", "Động từ cũng là answer.", "Classroom English");
        add(list, "question", "/ˈkwes.tʃən/", "/ˈkwes.tʃən/", "noun", "câu hỏi", "khi hỏi giáo viên",
                "I have a question.", "Tôi có một câu hỏi.",
                "ask a question, good question", "Âm /kw/ đầu từ.", "Classroom English");
        add(list, "repeat", "/rɪˈpiːt/", "/rɪˈpiːt/", "verb", "nhắc lại", "yêu cầu nói lại",
                "Can you repeat that, please?", "Bạn có thể nhắc lại được không?",
                "repeat after me, repeat please", "Trọng âm rePEAT.", "Classroom English");
        add(list, "understand", "/ˌʌn.dəˈstænd/", "/ˌʌn.dɚˈstænd/", "verb", "hiểu", "nắm bắt nội dung",
                "I understand the lesson.", "Tôi hiểu bài học.",
                "understand well, don't understand", "Âm /nd/ cuối.", "Classroom English");
        add(list, "explain", "/ɪkˈspleɪn/", "/ɪkˈspleɪn/", "verb", "giải thích", "làm rõ",
                "Can you explain this word?", "Bạn có thể giải thích từ này không?",
                "explain clearly, explain again", "Trọng âm exPLAIN.", "Classroom English");
        add(list, "practice", "/ˈpræk.tɪs/", "/ˈpræk.tɪs/", "noun", "luyện tập", "ôn luyện",
                "Practice makes perfect.", "Có công mài sắt có ngày nên kim.",
                "speaking practice, more practice", "Danh từ PRACtice, động từ pracTIse (UK).", "Classroom English");
        add(list, "test", "/test/", "/test/", "noun", "bài kiểm tra", "đánh giá",
                "We have a test tomorrow.", "Ngày mai chúng ta có bài kiểm tra.",
                "English test, take a test", "Âm /t/ cuối.", "Classroom English");
        add(list, "homework", "/ˈhəʊm.wɜːk/", "/ˈhoʊm.wɝːk/", "noun", "bài tập về nhà", "làm ở nhà",
                "Do your homework tonight.", "Làm bài tập về nhà tối nay.",
                "finish homework, check homework", "Một từ ghép.", "Classroom English");
        add(list, "board", "/bɔːd/", "/bɔːrd/", "noun", "bảng", "bảng viết trong lớp",
                "Look at the board.", "Nhìn lên bảng.",
                "whiteboard, on the board", "Âm /ɔː/ dài.", "Classroom English");
        add(list, "chair", "/tʃeər/", "/tʃer/", "noun", "ghế", "ngồi học",
                "Sit on your chair.", "Ngồi trên ghế của bạn.",
                "empty chair, pull a chair", "Âm /tʃ/ đầu.", "Classroom English");
        add(list, "name", "/neɪm/", "/neɪm/", "noun", "tên", "tên gọi",
                "What is your name?", "Tên bạn là gì?",
                "my name, full name", "Âm /m/ cuối.", "Classroom English");
        add(list, "age", "/eɪdʒ/", "/eɪdʒ/", "noun", "tuổi", "số năm sống",
                "How old are you? I am ten.", "Bạn bao nhiêu tuổi? Tôi mười tuổi.",
                "at my age, school age", "Âm /dʒ/ cuối.", "Classroom English");
    }
}
