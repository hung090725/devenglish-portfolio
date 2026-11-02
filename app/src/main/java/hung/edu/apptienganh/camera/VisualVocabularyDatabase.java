package hung.edu.apptienganh.camera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 100% Offline Visual Vocabulary Dictionary mapping everyday objects,
 * classroom/office supplies, furniture, electronics, clothing, food, and street items
 * to CEFR levels (A1-C1), IPA phonetics, syllable stress, idioms, dialogues,
 * word families, and AR Pop-Quizzes.
 */
public final class VisualVocabularyDatabase {

    private static final Map<String, VisualWordInfo> DICTIONARY = new HashMap<>();

    static {
        // ==========================================
        // 1. ELECTRONICS & GADGETS
        // ==========================================
        put("laptop", "Laptop", "/ˈlæp.tɒp/", "Máy tính xách tay", "danh từ (noun)", "A1",
                "work on a laptop, close the laptop", "làm việc trên laptop, gập máy tính",
                "I use my laptop every day for studying English and working.",
                "Tôi dùng máy tính xách tay mỗi ngày để học tiếng Anh và làm việc.",
                "On the blink (máy tính chập chờn, trục trặc)", "Đang bị lỗi hoặc hoạt động không ổn định",
                "A: Do you have your laptop with you?\nB: Yes, it's inside my backpack.",
                "A: Bạn có mang laptop theo không?\nB: Có chứ, nó ở trong ba lô của mình.",
                "compute (v) • computerize (v) • computational (adj)", "tính toán • điện toán hóa • thuộc về máy tính",
                "Portable workstation / Computational device", "Trạm làm việc di động / Thiết bị điện toán xách tay",
                "I need to connect my _____ to the charger because the battery is low.",
                Arrays.asList("laptop", "blanket", "curtain"), 0, "LAP-top");

        put("computer", "Computer", "/kəmˈpjuː.tər/", "Máy vi tính", "danh từ (noun)", "A1",
                "personal computer, turn on the computer", "máy tính cá nhân, bật máy tính",
                "He spends hours in front of the computer doing research.",
                "Anh ấy dành hàng giờ trước máy tính để nghiên cứu.",
                "Garbage in, garbage out (dữ liệu vào sai thì kết quả ra sai)", "Chất lượng đầu ra phụ thuộc hoàn toàn vào đầu vào",
                "A: Is this computer connected to the internet?\nB: Yes, high-speed Wi-Fi is ready.",
                "A: Máy tính này có kết nối internet không?\nB: Có, Wi-Fi tốc độ cao đã sẵn sàng.",
                "compute (v) • computation (n) • computerized (adj)", "tính toán • sự tính toán • được vi tính hóa",
                "Digital computing system", "Hệ thống điện toán kỹ thuật số",
                "Click the power button to boot up the _____.",
                Arrays.asList("computer", "pillow", "spoon"), 0, "com-PU-ter");

        put("keyboard", "Keyboard", "/ˈkiː.bɔːd/", "Bàn phím máy tính", "danh từ (noun)", "A2",
                "type on the keyboard, mechanical keyboard", "gõ trên bàn phím, bàn phím cơ",
                "She types very quickly on her wireless keyboard.",
                "Cô ấy gõ rất nhanh trên bàn phím không dây của mình.",
                "Key in (nhập dữ liệu bằng bàn phím)", "Gõ hoặc nhập thông tin vào hệ thống",
                "A: Which keyboard shortcut copies text?\nB: Press Ctrl and C together.",
                "A: Phím tắt nào sao chép văn bản?\nB: Nhấn đồng thời Ctrl và C nhé.",
                "key (n) • keypad (n) • keystroke (n)", "phím bấm • bàn phím số nhỏ • thao tác gõ phím",
                "Alphanumeric input peripheral", "Thiết bị ngoại vi nhập liệu ký tự số",
                "She entered her secure password using the _____.",
                Arrays.asList("keyboard", "fork", "mirror"), 0, "KEY-board");

        putAlias("computer keyboard", "keyboard");

        put("mouse", "Computer Mouse", "/ˈmaʊs/", "Chuột máy tính", "danh từ (noun)", "A1",
                "click the mouse, wireless mouse", "nhấp chuột, chuột không dây",
                "Double-click the mouse button to open the application.",
                "Nhấp đúp chuột để mở ứng dụng.",
                "Quiet as a mouse (im thin thít, cực kỳ yên lặng)", "Giữ im lặng hoàn toàn, không gây tiếng động",
                "A: Why isn't the cursor moving?\nB: The wireless mouse needs a new battery.",
                "A: Sao con trỏ chuột không di chuyển?\nB: Chuột không dây hết pin rồi đó.",
                "mousepad (n) • trackpad (n) • click (v)", "lót chuột • bàn di chuột cảm ứng • nhấp chuột",
                "Optical pointing device", "Thiết bị định vị quang học cầm tay",
                "Use the _____ to scroll down the webpage.",
                Arrays.asList("mouse", "blanket", "knife"), 0, "MOUSE");

        put("phone", "Smartphone", "/ˈsmɑːt.fəʊn/", "Điện thoại thông minh", "danh từ (noun)", "A1",
                "check the smartphone, answer the phone", "kiểm tra điện thoại, nghe điện thoại",
                "Don't spend too much time staring at your smartphone screen.",
                "Đừng dành quá nhiều thời gian nhìn chằm chằm vào màn hình điện thoại.",
                "Hold the phone (khoan đã, chờ một chút)", "Khoan đã, hãy dừng lại để kiểm tra kỹ lại",
                "A: Did you see my new smartphone?\nB: Wow, the camera quality is amazing!",
                "A: Bạn thấy chiếc điện thoại thông minh mới của mình chưa?\nB: Chà, chất lượng camera đỉnh thật!",
                "phonetic (adj) • telephone (n) • phonics (n)", "ngữ âm • máy điện thoại • phương pháp ngữ âm",
                "Mobile telecommunication device", "Thiết bị viễn thông di động đa năng",
                "I received an important call on my _____.",
                Arrays.asList("smartphone", "table", "chair"), 0, "SMART-phone");

        putAlias("mobile phone", "phone");

        put("headphones", "Headphones", "/ˈhed.fəʊnz/", "Tai nghe chụp tai", "danh từ (noun)", "A2",
                "put on headphones, noise-cancelling headphones", "đeo tai nghe, tai nghe chống ồn",
                "Listening to English podcasts with headphones helps improve your pronunciation.",
                "Nghe podcast tiếng Anh bằng tai nghe giúp cải thiện phát âm của bạn.",
                "Turn a deaf ear (làm ngơ, phớt lờ không muốn nghe)", "Cố tình không nghe hoặc không để tâm",
                "A: Can you hear me clearly?\nB: Wait, let me take off my headphones first.",
                "A: Bạn nghe mình rõ không?\nB: Đợi tí, để mình tháo tai nghe ra đã.",
                "headset (n) • acoustic (adj) • headphone jack (n)", "bộ tai nghe kèm mic • thuộc âm thanh • jack cắm tai nghe",
                "Acoustic listening apparatus", "Thiết bị truyền âm thanh chụp tai chuyên dụng",
                "He plugged in his _____ to concentrate on studying in the library.",
                Arrays.asList("headphones", "plate", "ruler"), 0, "HEAD-phones");

        putAlias("earphones", "headphones");

        // ==========================================
        // 2. OFFICE, STUDY & CLASSROOM
        // ==========================================
        put("book", "Book", "/bʊk/", "Quyển sách", "danh từ (noun)", "A0",
                "read a book, open the book", "đọc sách, mở sách",
                "Reading English books daily expands your vocabulary significantly.",
                "Đọc sách tiếng Anh mỗi ngày giúp mở rộng vốn từ vựng đáng kể.",
                "Hit the books (lao vào học tập chăm chỉ)", "Bắt đầu học bài nghiêm túc cho kỳ thi",
                "A: Have you read this English novel?\nB: Yes, it's one of the best books I've ever read.",
                "A: Bạn đọc cuốn tiểu thuyết tiếng Anh này chưa?\nB: Rồi, đó là một trong những cuốn sách hay nhất mình từng đọc.",
                "bookish (adj) • bookmark (n) • bookstore (n)", "ham đọc sách • thẻ kẹp sách • hiệu sách",
                "Academic publication / Literary tome", "Tác phẩm văn học / Ấn phẩm học thuật",
                "Open your English _____ to page forty-five.",
                Arrays.asList("book", "lamp", "shoes"), 0, "BOOK");

        put("notebook", "Notebook", "/ˈnəʊt.bʊk/", "Vở ghi chép / Sổ tay", "danh từ (noun)", "A1",
                "write in a notebook, spiral notebook", "viết vào sổ, sổ tay gáy lò xo",
                "Keep a small notebook handy to jot down new English expressions.",
                "Hãy giữ một cuốn sổ nhỏ bên mình để ghi chép các cách diễn đạt tiếng Anh mới.",
                "Make a note of (ghi chép lại điều gì cẩn thận)", "Ghi chú lại để không bị quên",
                "A: Did you write down the teacher's instructions?\nB: Yes, I wrote everything in my notebook.",
                "A: Bạn đã ghi lại hướng dẫn của giáo viên chưa?\nB: Rồi, mình ghi hết vào sổ tay rồi.",
                "note (n/v) • noteworthy (adj) • notepad (n)", "ghi chú • đáng chú ý • tập giấy ghi chú",
                "Personal compendium / Journal", "Tập san ghi chép cá nhân / Sổ lưu bút",
                "She recorded all new grammar rules in her study _____.",
                Arrays.asList("notebook", "bottle", "window"), 0, "NOTE-book");

        put("pen", "Ballpoint Pen", "/pen/", "Cây bút bi / Bút viết", "danh từ (noun)", "A1",
                "blue pen, click the pen", "bút bi xanh, bấm bút",
                "Could I borrow your pen for a moment to sign this form?",
                "Tôi có thể mượn cây bút của bạn một lát để ký biểu mẫu này không?",
                "The pen is mightier than the sword (ngòi bút sắc hơn gươm giáo)", "Sức mạnh của tri thức và ngôn từ to lớn hơn vũ lực",
                "A: Can I borrow a pen?\nB: Sure, here's a blue ballpoint pen for you.",
                "A: Cho mình mượn cây bút được không?\nB: Được chứ, đây là cây bút bi xanh cho bạn.",
                "penmanship (n) • pen pal (n) • fountain pen (n)", "nghệ thuật viết chữ đẹp • bạn qua thư • bút máy",
                "Precision writing instrument", "Dụng cụ viết chính xác",
                "Please sign your signature using black ink with a _____.",
                Arrays.asList("pen", "bed", "fan"), 0, "PEN");

        put("pencil", "Pencil", "/ˈpen.səl/", "Bút chì", "danh từ (noun)", "A1",
                "sharpen a pencil, wooden pencil", "gọt bút chì, bút chì gỗ",
                "Architects and artists often sketch initial ideas with a soft pencil.",
                "Các kiến trúc sư và họa sĩ thường phác thảo ý tưởng ban đầu bằng bút chì mềm.",
                "Pencil in (ghi tạm thời vào lịch trình)", "Lên lịch sơ bộ nhưng có thể thay đổi",
                "A: Do you have a pencil sharpener?\nB: Yes, here it is in my pencil case.",
                "A: Bạn có cái gọt bút chì không?\nB: Có, ở trong hộp bút của mình đây.",
                "penciled (adj) • pencil case (n) • lead (n)", "viết bằng chì • hộp bút • ruột chì",
                "Graphite drafting instrument", "Dụng cụ vẽ phác thảo bằng than chì",
                "Sketch the basic outline lightly with a 2B _____.",
                Arrays.asList("pencil", "wardrobe", "curtain"), 0, "PEN-cil");

        put("backpack", "Backpack", "/ˈbæk.pæk/", "Ba lô đi học / đi làm", "danh từ (noun)", "A2",
                "carry a backpack, pack a backpack", "đeo ba lô, xếp đồ vào ba lô",
                "He packed his laptop, notebooks, and water bottle into his backpack.",
                "Anh ấy xếp laptop, sổ tay và bình nước vào chiếc ba lô của mình.",
                "Pack light (mang hành lý gọn nhẹ)", "Chỉ mang theo những vật dụng thiết yếu nhất",
                "A: Is your backpack heavy?\nB: A bit, because I have three textbooks inside.",
                "A: Ba lô của bạn có nặng không?\nB: Hơi nặng chút, vì mình để tận ba cuốn sách giáo khoa.",
                "pack (v) • package (n) • backpacker (n)", "đóng gói • bưu kiện • dân phượt",
                "Ergonomic knapsack / Rucksack", "Ba lô chịu lực công thái học",
                "Zip up your _____ before riding your bicycle.",
                Arrays.asList("backpack", "chair", "sofa"), 0, "BACK-pack");

        put("glasses", "Eyeglasses", "/ˈaɪˌɡlɑː.sɪz/", "Kính mắt / mắt kính", "danh từ (noun)", "A1",
                "wear glasses, put on reading glasses", "đeo kính, đeo kính đọc sách",
                "She needs to wear glasses when working on the computer.",
                "Cô ấy cần đeo kính khi làm việc trên máy tính.",
                "See through rose-colored glasses (nhìn đời qua lăng kính màu hồng)", "Nhìn nhận mọi thứ quá lạc quan, tích cực",
                "A: Have you seen my reading glasses?\nB: They are sitting right on your head!",
                "A: Bạn có thấy kính đọc sách của mình đâu không?\nB: Nó đang cài ngay trên đầu bạn kìa!",
                "glass (n) • glassware (n) • eyeglasses (n)", "thủy tinh • đồ thủy tinh • kính mắt",
                "Corrective optical lenses / Eyewear", "Tròng kính quang học điều chỉnh thị lực",
                "He wiped the lenses of his _____ with a microfiber cloth.",
                Arrays.asList("glasses", "cup", "pillow"), 0, "EYE-glas-ses");

        // ==========================================
        // 3. KITCHEN, FOOD & DRINKS
        // ==========================================
        put("cup", "Cup / Teacup", "/kʌp/", "Cái tách / Tách trà", "danh từ (noun)", "A1",
                "a cup of tea, porcelain cup", "một tách trà, tách bằng sứ",
                "She enjoyed a fragrant cup of green tea during her study break.",
                "Cô ấy thưởng thức một tách trà xanh thơm ngát trong giờ giải lao học tập.",
                "Not my cup of tea (đó không phải là sở thích/gu của tôi)", "Thứ gì đó không hợp phong cách của bản thân",
                "A: Would you like a cup of hot chocolate?\nB: That sounds wonderful on this cold day!",
                "A: Bạn có muốn một tách sô-cô-la nóng không?\nB: Nghe tuyệt quá trong ngày lạnh thế này!",
                "cupcake (n) • teacup (n) • saucer (n)", "bánh cupcake • tách trà • đĩa lót tách",
                "Ceramic beverage vessel", "Vật dụng chứa đồ uống bằng gốm sứ",
                "Carefully pour the herbal tea into the porcelain _____.",
                Arrays.asList("cup", "door", "ruler"), 0, "CUP");

        put("coffee cup", "Coffee Cup", "/ˈkɒf.i kʌp/", "Tách cà phê", "danh từ (noun)", "A1",
                "drink a coffee cup, hot coffee cup", "uống tách cà phê, tách cà phê nóng",
                "A hot cup of coffee helps keep me alert and focused in the morning.",
                "Một tách cà phê nóng giúp tôi tỉnh táo và tập trung vào buổi sáng.",
                "Wake up and smell the coffee (hãy tỉnh táo nhìn nhận thực tế đi)", "Nhận ra sự thật trước mắt thay vì mơ mộng",
                "A: How do you take your coffee?\nB: Just a drop of milk and no sugar, please.",
                "A: Bạn uống cà phê thế nào?\nB: Cho mình chút sữa và không đường nhé.",
                "caffeinated (adj) • coffeemaker (n) • barista (n)", "có chứa caffeine • máy pha cà phê • thợ pha chế",
                "Thermal caffeinated vessel", "Ly giữ nhiệt đựng đồ uống caffeine",
                "She started her productive morning with a hot _____.",
                Arrays.asList("coffee cup", "blanket", "t-shirt"), 0, "COF-fee cup");

        put("bottle", "Water Bottle", "/ˈwɔː.tər ˌbɒt.əl/", "Bình nước cá nhân", "danh từ (noun)", "A1",
                "reusable water bottle, drink from a water bottle", "bình nước tái sử dụng, uống từ bình nước",
                "Carrying a water bottle reminds you to stay hydrated all day.",
                "Mang theo một bình nước giúp nhắc nhở bạn uống đủ nước suốt cả ngày.",
                "Bottle up your feelings (kìm nén cảm xúc trong lòng)", "Không chịu bộc lộ cảm xúc ra ngoài",
                "A: Can I refill my water bottle here?\nB: Yes, the water dispenser is in the corner.",
                "A: Mình có thể rót thêm nước vào bình ở đây không?\nB: Được chứ, cây nước nóng lạnh ở góc phòng kìa.",
                "bottled (adj) • bottleneck (n) • bottling (n)", "đóng chai • nút thắt cổ chai • quá trình đóng chai",
                "Hydration reservoir / Reusable flask", "Bình chứa nước uống tái sử dụng cao cấp",
                "Stay healthy by drinking two liters of water from your _____.",
                Arrays.asList("water bottle", "laptop", "scissors"), 0, "WA-ter bot-tle");

        putAlias("water bottle", "bottle");

        put("bread", "Bread", "/bred/", "Bánh mì", "danh từ (noun)", "A1",
                "slice of bread, fresh crispy bread", "lát bánh mì, bánh mì giòn tươi",
                "Vietnamese banh mi is world-famous for its crunchy crust and flavorful filling.",
                "Bánh mì Việt Nam nổi tiếng thế giới nhờ lớp vỏ giòn rụm và nhân đậm đà.",
                "Bread and butter (nguồn sống chính, nghề mưu sinh)", "Công việc mang lại thu nhập chủ yếu",
                "A: Do you want toasted bread for breakfast?\nB: Yes, with a little strawberry jam please.",
                "A: Bạn có muốn ăn bánh mì nướng cho bữa sáng không?\nB: Có, kèm chút mứt dâu tây nhé.",
                "bakery (n) • baker (n) • breadcrumb (n)", "tiệm bánh • thợ làm bánh • vụn bánh mì",
                "Artisan baked staple", "Lương thực bột mì nướng thủ công",
                "He toasted two slices of whole wheat _____.",
                Arrays.asList("bread", "watch", "camera"), 0, "BREAD");

        // ==========================================
        // 4. CLOTHING & ACCESSORIES
        // ==========================================
        put("shoes", "Shoes", "/ʃuːz/", "Đôi giày", "danh từ (noun)", "A1",
                "leather shoes, tie your shoes", "giày da, buộc dây giày",
                "Choose comfortable, supportive shoes when walking for long distances.",
                "Hãy chọn những đôi giày êm ái và nâng đỡ tốt khi đi bộ đường dài.",
                "Put yourself in someone else's shoes (đặt mình vào vị trí của người khác)", "Biết thấu hiểu và đồng cảm với hoàn cảnh của người khác",
                "A: Are those new shoes comfortable?\nB: Yes, they feel like walking on clouds!",
                "A: Đôi giày mới đó êm không?\nB: Êm lắm, đi mà như bước trên mây vậy!",
                "shoelace (n) • shoemaker (n) • footwear (n)", "dây giày • thợ đóng giày • giày dép nói chung",
                "Protective footwear / Foot attire", "Trang phục bảo vệ đôi chân",
                "Remember to take off your outdoor _____ before entering the living room.",
                Arrays.asList("shoes", "pen", "notebook"), 0, "SHOES");

        putAlias("sneakers", "shoes");

        put("shirt", "Dress Shirt", "/ʃɜːt/", "Áo sơ mi", "danh từ (noun)", "A1",
                "wear a white shirt, iron the shirt", "mặc áo sơ mi trắng, ủi áo sơ mi",
                "He wore a freshly ironed white shirt for his job interview.",
                "Anh ấy mặc một chiếc áo sơ mi trắng vừa được ủi phẳng phiu cho buổi phỏng vấn.",
                "Keep your shirt on (hãy bình tĩnh, đừng nóng vội)", "Khuyên ai đó đừng mất bình tĩnh",
                "A: Does this tie match my dress shirt?\nB: Perfectly, the dark blue color matches great.",
                "A: Cà vạt này có hợp với áo sơ mi của mình không?\nB: Rất hợp, màu xanh đậm trông rất trang nhã.",
                "shirting (n) • shirtless (adj) • collar (n)", "vải may áo sơ mi • cởi trần • cổ áo sơ mi",
                "Formal tailored garment", "Trang phục áo may đo trang trọng",
                "Button up the collar of your formal _____.",
                Arrays.asList("shirt", "clock", "calculator"), 0, "SHIRT");

        // ==========================================
        // 5. VEHICLES & HOME
        // ==========================================
        put("car", "Car / Automobile", "/kɑːr/", "Xe ô tô", "danh từ (noun)", "A1",
                "drive a car, park the car", "lái xe ô tô, đỗ xe",
                "Electric cars are becoming increasingly popular for cleaner air.",
                "Xe ô tô điện đang ngày càng trở nên phổ biến để bảo vệ bầu không khí sạch hơn.",
                "In the driver's seat (nắm quyền kiểm soát tình hình)", "Đang nắm quyền chỉ đạo hoặc kiểm soát sự việc",
                "A: Where did you park the car?\nB: In the basement garage on level B2.",
                "A: Bạn đỗ xe ô tô ở đâu vậy?\nB: Ở tầng hầm B2 của bãi đỗ xe nhé.",
                "carpool (v) • auto (n) • automotive (adj)", "đi chung xe • ô tô • thuộc ngành công nghiệp ô tô",
                "Electric passenger vehicle", "Phương tiện chở khách chạy bằng điện",
                "Fasten your seatbelt before driving the _____.",
                Arrays.asList("car", "apple", "backpack"), 0, "CAR");

        put("desk", "Work Desk", "/desk/", "Bàn làm việc / Bàn học", "danh từ (noun)", "A1",
                "tidy up the desk, sit at the desk", "dọn dẹp bàn làm việc, ngồi tại bàn",
                "Keeping your study desk clean boosts your learning concentration.",
                "Giữ bàn học gọn gàng sạch sẽ giúp tăng cường sự tập trung khi học.",
                "Desk jockey (nhân viên văn phòng ngồi bàn giấy cả ngày)", "Người làm công việc bàn giấy văn phòng",
                "A: Can we put another monitor on this desk?\nB: Sure, there is plenty of space.",
                "A: Chúng ta có thể đặt thêm một màn hình lên bàn này không?\nB: Được chứ, bàn còn rất nhiều chỗ.",
                "desktop (n) • desk-bound (adj)", "mặt bàn / máy tính để bàn • bị gò bó nơi bàn giấy",
                "Ergonomic workstation", "Không gian làm việc công thái học",
                "Clear all unnecessary papers off your _____.",
                Arrays.asList("desk", "shoes", "bread"), 0, "DESK");

        initDeepKnowledge();
    }

    private static void put(String key, String wordEn, String ipa, String meaningVi, String wordType,
                            String cefrLevel, String collocationEn, String collocationVi,
                            String exampleSentenceEn, String exampleSentenceVi,
                            String idiomEn, String idiomVi,
                            String dialogueEn, String dialogueVi,
                            String wordFamilyEn, String wordFamilyVi,
                            String academicC1WordEn, String academicC1MeaningVi,
                            String quizQuestionEn, List<String> quizOptions, int quizAnswerIndex,
                            String syllableStress) {
        VisualWordInfo info = new VisualWordInfo(wordEn, ipa, meaningVi, wordType, cefrLevel,
                collocationEn, collocationVi, exampleSentenceEn, exampleSentenceVi);
        info.setIdiomEn(idiomEn);
        info.setIdiomVi(idiomVi);
        info.setDialogueEn(dialogueEn);
        info.setDialogueVi(dialogueVi);
        info.setWordFamilyEn(wordFamilyEn);
        info.setWordFamilyVi(wordFamilyVi);
        info.setAcademicC1WordEn(academicC1WordEn);
        info.setAcademicC1MeaningVi(academicC1MeaningVi);
        info.setQuizQuestionEn(quizQuestionEn);
        info.setQuizOptions(quizOptions);
        info.setQuizAnswerIndex(quizAnswerIndex);
        info.setSyllableStress(syllableStress);

        DICTIONARY.put(key.toLowerCase(Locale.ROOT), info);
    }

    private static void setDeepInfo(String key,
                                    String etymologyRoot,
                                    String componentAnatomy,
                                    String confusingWordAlert,
                                    String polysemyNotes,
                                    String britishVsAmerican,
                                    String sensoryAdjectives,
                                    String mindmapTree,
                                    String mnemonicHook,
                                    String atmosphericMood,
                                    String grammarRuleNotes,
                                    String ieltsSpeakingSample,
                                    String phrasalVerbs,
                                    String espSpecializedVocab,
                                    String shoppingInquiries,
                                    String aiTutorFact) {
        VisualWordInfo info = DICTIONARY.get(key.toLowerCase(Locale.ROOT));
        if (info != null) {
            info.setEtymologyRoot(etymologyRoot);
            info.setComponentAnatomy(componentAnatomy);
            info.setConfusingWordAlert(confusingWordAlert);
            info.setPolysemyNotes(polysemyNotes);
            info.setBritishVsAmerican(britishVsAmerican);
            info.setSensoryAdjectives(sensoryAdjectives);
            info.setMindmapTree(mindmapTree);
            info.setMnemonicHook(mnemonicHook);
            info.setAtmosphericMood(atmosphericMood);
            info.setGrammarRuleNotes(grammarRuleNotes);
            info.setIeltsSpeakingSample(ieltsSpeakingSample);
            info.setPhrasalVerbs(phrasalVerbs);
            info.setEspSpecializedVocab(espSpecializedVocab);
            info.setShoppingInquiries(shoppingInquiries);
            info.setAiTutorFact(aiTutorFact);
        }
    }

    private static void initDeepKnowledge() {
        setDeepInfo("laptop",
                "🧬 Gốc từ: Ghép từ 'Lap' (vạt áo / đùi của người ngồi) + 'Top' (phía trên). Xuất phát từ tiếng Anh cổ 'læppa'. Ý nghĩa: Máy vi tính nhỏ gọn có thể đặt và làm việc thoải mái ngay trên đùi.",
                "🔍 Giải phẫu bộ phận: Display / Screen (Màn hình) • Hinge (Bản lề gập) • Touchpad / Trackpad (Bàn rê cảm ứng) • Chassis (Bộ khung vỏ máy) • Cooling Vents (Khe tản nhiệt) • USB-C Thunderbolt Port (Cổng kết nối đa năng).",
                "⚠️ Cảnh báo nhầm lẫn: Phân biệt 'Laptop' (máy tính xách tay gấp mở) vs 'Desktop' (máy bàn cố định) vs 'Tablet' (máy tính bảng màn hình phẳng cảm ứng).",
                "✨ Đa nghĩa: Dùng làm danh từ chỉ thiết bị xách tay; danh từ ghép: laptop bag (túi chống sốc), laptop stand (giá đỡ tản nhiệt công thái học).",
                "🇬🇧 UK: Laptop / Notebook • Mains charger (củ sạc cắm tường)\n🇺🇸 US: Laptop computer • Power adapter / Wall plug",
                "🎨 5 Giác quan: Sleek (bóng bẩy, tinh tế) • Featherlight (nhẹ như lông hồng) • Ultra-responsive (phản hồi cực nhạy) • Whisper-quiet (quạt chạy êm ru) • Brushed metallic (kim loại phay xước mát lạnh).",
                "🌳 Sơ đồ tư duy: Laptop ➔ Core Specs (CPU, GPU, RAM, NVMe SSD) ➔ Workflow (Cloud computing, Coding, Video editing) ➔ Ergonomics (Posture, Eye strain).",
                "💡 Mẹo siêu trí nhớ: 'LAP' là lòng/đùi, 'TOP' là bên trên. Cứ ngồi xuống ghế đặt máy lên đùi làm việc khắp muôn nơi là nhớ ngay chữ 'Laptop'!",
                "🌌 Không gian & Cảm xúc: Minimalist workspace (không gian làm việc tối giản) • Digital nomad vibe (phong cách du mục số tự do).",
                "⚖️ La bàn ngữ pháp: Danh từ đếm được (a laptop, two laptops). Đi với giới từ 'ON': 'I am working ON my laptop' (TUYỆT ĐỐI KHÔNG dùng 'in my laptop').",
                "🏆 IELTS Speaking (Band 7.5+):\nQ: How significant is your laptop in your daily routine?\nA: 'To be completely candid, my laptop is utterly indispensable. It serves as my primary intellectual hub where I conduct extensive research, engage in collaborative coding, and streamline my academic output with utmost efficiency.'",
                "🔗 Phrasal Verbs: Boot up (khởi động hệ điều hành) • Shut down (tắt nguồn hoàn toàn) • Plug in (cắm sạc nguồn) • Back up (sao lưu dữ liệu lên đám mây) • Freeze up (máy tính bị treo đơ).",
                "💼 Tiếng Anh Chuyên Ngành (IT / Tech):\nThermal throttling (sụt giảm hiệu năng do quá nhiệt) • Refresh rate (tần số quét màn hình 120Hz) • Firmware update (nâng cấp phần mềm hệ thống).",
                "🛒 Giao tiếp mua sắm: 'Excuse me, does this ultrabook feature upgradable RAM, and is an international manufacturer warranty included?'",
                "🤖 Gia sư AI lưu ý: Trong bài thi Writing Task 2, tránh lặp lại từ 'laptop' quá nhiều. Hãy linh hoạt thay thế bằng 'portable personal computer' hoặc 'mobile workstation' để ghi trọn điểm tiêu chí Lexical Resource!"
        );

        setDeepInfo("computer",
                "🧬 Gốc từ: Từ gốc Latin 'computare' (com- = cùng nhau, putare = tính toán, suy xét). Ban đầu vào thế kỷ 17 chỉ người làm nghề tính toán số liệu, sau chuyển thành cỗ máy điện toán.",
                "🔍 Giải phẫu bộ phận: Monitor (Màn hình hiển thị) • Motherboard (Bo mạch chủ) • Processor / CPU (Bộ vi xử lý trung tâm) • Power Supply Unit (Bộ nguồn PSU) • Graphics Card / GPU (Card đồ họa rời).",
                "⚠️ Cảnh báo nhầm lẫn: 'Computer' là thuật ngữ bao hàm rộng lớn (bao gồm cả Supercomputer, Server, Desktop, Laptop); không nên đánh đồng chỉ có nghĩa là màn hình hiển thị (Monitor).",
                "✨ Đa nghĩa: Động từ liên quan: Computerize (tin học hóa quy trình), Compute (tính toán toán học hoặc dữ liệu lớn).",
                "🇬🇧 UK: Personal computer / Desktop PC\n🇺🇸 US: Desktop computer / Rig",
                "🎨 5 Giác quan: High-performance (hiệu năng cao) • Robust (chắc chắn, bền bỉ) • Humming (tiếng quạt quay rì rầm êm dịu) • Luminous (ánh sáng đèn LED RGB rực rỡ).",
                "🌳 Sơ đồ tư duy: Computer ➔ Architecture (von Neumann, RAM, Storage) ➔ Software ecosystem (OS, Kernel, Compilers) ➔ Cyber security.",
                "💡 Mẹo siêu trí nhớ: 'Com' (cùng nhau) + 'pute' (tính nhẩm) ➔ Máy móc giúp con người cùng nhau tính toán hàng tỷ phép tính mỗi giây = Computer!",
                "🌌 Không gian & Cảm xúc: High-tech immersion (không gian đắm chìm công nghệ cao) • Cutting-edge innovation.",
                "⚖️ La bàn ngữ pháp: Đếm được: a computer. Giới từ: 'BY computer' (bằng phương thức máy tính), 'ON the computer' (đang dùng máy tính làm việc).",
                "🏆 IELTS Speaking (Band 7.5+):\nQ: How has computer technology impacted modern education?\nA: 'Unquestionably, the advent of high-performance computing has democratized knowledge acquisition, empowering students to access vast academic repositories at the mere click of a button.'",
                "🔗 Phrasal Verbs: Log into (đăng nhập vào tài khoản) • Hack into (xâm nhập trái phép) • Print out (in tài liệu ra giấy) • Wipe out (xóa sạch dữ liệu ổ cứng).",
                "💼 Tiếng Anh Chuyên Ngành (Computer Science):\nDistributed system (hệ thống phân tán) • Algorithmic complexity (độ phức tạp thuật toán) • Multi-threading (xử lý đa luồng).",
                "🛒 Giao tiếp mua sắm: 'Could you configure this workstation with 32 gigabytes of dual-channel RAM and a dedicated graphics card?'",
                "🤖 Gia sư AI lưu ý: Hãy chú ý trọng âm rơi vào âm tiết thứ hai: /kəmˈpjuː.tər/ (com-PU-ter), không đọc thành 'côm-pu-tơ' nhé!"
        );

        setDeepInfo("book",
                "🧬 Gốc từ: Bắt nguồn từ tiếng German cổ 'bōk-', có nguồn gốc họ hàng với cây cử gai (beech tree), bởi vì các dân tộc German cổ xưa từng khắc chữ rune đầu tiên lên vỏ gỗ cây sồi cử.",
                "🔍 Giải phẫu bộ phận: Hardcover / Paperback (Bìa cứng / bìa mềm) • Spine (Gáy sách) • Bookmark Ribbon (Dây ruy băng đánh dấu trang) • Margin (Lề trang sách) • Table of Contents (Mục lục) • Footnotes (Chú thích chân trang).",
                "⚠️ Cảnh báo nhầm lẫn: Phân biệt 'Book' (sách hoàn chỉnh) vs 'Booklet' (sách bướm mỏng) vs 'Novel' (tiểu thuyết hư cấu) vs 'Textbook' (sách giáo khoa chuyên biệt).",
                "✨ Đa nghĩa: Động từ 'to book': Đặt chỗ trước ('book a hotel room', 'book a flight ticket'). Thành ngữ: 'Bring someone to book' (bắt ai đó chịu trách nhiệm trước pháp luật).",
                "🇬🇧 UK: Reading book / Tome / Volume\n🇺🇸 US: Book / Hardback copy",
                "🎨 5 Giác quan: Crisp paper scent (mùi thơm thanh khiết của giấy mới) • Velvety tactile finish (bìa sờ mịn màng như nhung) • Rustling pages (tiếng lật trang sột soạt nhẹ êm) • Creamy tinted paper (màu giấy ngà dịu mắt).",
                "🌳 Sơ đồ tư duy: Book ➔ Genre (Non-fiction, Sci-Fi, Biography) ➔ Literary elements (Plot, Character arc, Motif) ➔ Publication (Editorial, ISBN).",
                "💡 Mẹo siêu trí nhớ: Chữ 'B-O-O-K' nhìn hai chữ 'OO' tròn xoe giống như một cặp kính cận của người ham mê đọc sách tri thức!",
                "🌌 Không gian & Cảm xúc: Serene bookish sanctuary (chốn tĩnh lặng ngập tràn tri thức) • Cozy reading nook (góc đọc sách ấm cúng bên khung cửa).",
                "⚖️ La bàn ngữ pháp: Đếm được (a book, ten books). Giới từ: 'IN a book' (tìm thấy thông tin trong sách), 'BY the book' (làm theo đúng quy tắc sách vở).",
                "🏆 IELTS Speaking (Band 7.5+):\nQ: Do you prefer physical books or e-books?\nA: 'While digital tablets undoubtedly offer unmatched portability, I still have a profound affinity for tangible hardcovers. The tactile sensation of turning leaves and the distinctive fragrance of print evoke a sense of mindfulness that screens simply cannot replicate.'",
                "🔗 Phrasal Verbs: Flip through (lật nhanh các trang xem lướt) • Pore over (đọc kỹ nghiền ngẫm từng chữ) • Dip into (đọc qua vài phần ngắn) • Read up on (đọc tài liệu nghiên cứu sâu về một đề tài).",
                "💼 Tiếng Anh Chuyên Ngành (Publishing & Literature):\nManuscript (bản thảo gốc tác giả) • Copyright attribution (bản quyền xuất bản) • Peer-reviewed bibliography (thư mục tài liệu thẩm định khoa học).",
                "🛒 Giao tiếp mua sắm: 'Do you happen to have the collector's edition of this historical volume in stock, or would I need to place a backorder?'",
                "🤖 Gia sư AI lưu ý: Hãy dùng cụm collocations điểm cao: 'an avid reader' (người đọc say mê) hoặc 'a voracious bookworm' (mọt sách ham hiểu biết) khi thi Speaking!"
        );

        setDeepInfo("bottle",
                "🧬 Gốc từ: Xuất phát từ tiếng Latinh Trung cổ 'butticula', giảm thể từ 'buttis' (thùng, bình rượu lớn). Tiến hóa qua tiếng Pháp cổ 'bouteille' trước khi du nhập vào tiếng Anh.",
                "🔍 Giải phẫu bộ phận: Cap / Screw-on lid (Nắp vặn kín khí) • Neck (Cổ chai thon gọn) • Spout (Miệng vòi uống) • Vacuum insulated double-wall (Thành kép cách nhiệt chân không) • Silicone gasket (Vòng đệm cao su chống rò rỉ).",
                "⚠️ Cảnh báo nhầm lẫn: Phân biệt 'Bottle' (bình/chai có cổ hẹp) vs 'Flask' (bình giữ nhiệt hoặc bình bỏ túi) vs 'Jar' (lọ miệng rộng đựng mứt/thực phẩm).",
                "✨ Đa nghĩa: Động từ 'Bottle up': kìm nén cảm xúc. Thành ngữ: 'Hit the bottle' (chìm đắm trong men rượu).",
                "🇬🇧 UK: Water bottle / Reusable flask\n🇺🇸 US: Thermos / Hydro flask / Water canteen",
                "🎨 5 Giác quan: Icy cold to the touch (mát lạnh chạm vào tay) • Leak-proof (hoàn toàn kín nước) • Condensation-free (không đọng hơi sương mặt ngoài) • Metallic clink (tiếng va chạm kim loại trong trẻo).",
                "🌳 Sơ đồ tư duy: Bottle ➔ Materials (Stainless steel, BPA-free plastic, Glass) ➔ Eco impact (Zero single-use plastic, Ocean preservation) ➔ Daily hydration.",
                "💡 Mẹo siêu trí nhớ: Hãy tưởng tượng cái nút chai bật mở 'BỐP' một tiếng vang (Bot) rồi nước mát rót vào ly (tle) ➔ Bottle!",
                "🌌 Không gian & Cảm xúc: Active eco-conscious lifestyle (lối sống năng động bảo vệ môi trường) • Hydrated wellness.",
                "⚖️ La bàn ngữ pháp: Đếm được (a bottle of water, three glass bottles). Khi nói về chất lỏng bên trong là danh từ không đếm được: 'a bottle of water' (KHÔNG nói 'a water').",
                "🏆 IELTS Speaking (Band 7.5+):\nQ: Why do many people choose reusable water bottles nowadays?\nA: 'Essentially, carrying a personal vacuum flask has evolved from a mere trend into an imperative eco-friendly habit. It not only keeps beverages refreshingly chilled throughout scorching days but also curbs the disastrous accumulation of single-use plastic waste.'",
                "🔗 Phrasal Verbs: Fill up (đổ đầy nước vào bình) • Drink up (uống cạn sạch) • Top off (rót thêm cho đầy đến miệng) • Wash out (súc rửa sạch lòng chai).",
                "💼 Tiếng Anh Chuyên Ngành (Material Science & Sustainability):\nThermal conductivity (độ dẫn nhiệt) • BPA-free polymer (nhựa không chứa bisphenol A) • Carbon footprint mitigation (cắt giảm lượng phát thải carbon).",
                "🛒 Giao tiếp mua sắm: 'Is this stainless-steel bottle dishwasher-safe, and does the insulation retain heat for up to twenty-four hours?'",
                "🤖 Gia sư AI lưu ý: Hãy phân biệt phát âm: US thường đọc âm /t/ thành vỗ nhẹ [d] 'bah-dl', còn UK giữ chuẩn âm /t/ bật rõ 'bot-əl'."
        );

        setDeepInfo("cup",
                "🧬 Gốc từ: Nguồn gốc từ tiếng Latin 'cuppa' (chén, bát uống rượu), liên quan đến từ 'cupa' (thùng chứa). Đã hiện diện trong tiếng Anh cổ dạng 'cuppe' hơn 1.000 năm.",
                "🔍 Giải phẫu bộ phận: Rim (Vành miệng tách) • Ergonomic handle (Quai cầm tiện dụng) • Inner glaze (Lớp men tráng bên trong lòng) • Base / Foot (Đế tách chịu lực) • Saucer (Đĩa lót tách thanh nhã).",
                "⚠️ Cảnh báo nhầm lẫn: Phân biệt 'Cup' (tách nhỏ có đĩa lót, thường uống trà) vs 'Mug' (ly sứ dày, to hình trụ uống cà phê lớn) vs 'Glass' (ly thủy tinh).",
                "✨ Đa nghĩa: Thành ngữ kinh điển: 'Not my cup of tea' (không thuộc sở thích của tôi). 'In one's cups' (đang lúc say mèm).",
                "🇬🇧 UK: Teacup and saucer / China cup\n🇺🇸 US: Coffee cup / Ceramic mug",
                "🎨 5 Giác quan: Steaming hot aroma (hương thơm nghi ngút khói) • Smooth porcelain glaze (men sứ mịn màng như ngọc) • Delicate clatter on the saucer (tiếng chạm lách cách thanh tao trên đĩa lót) • Fragrant warmth (hơi ấm thơm ngào ngạt).",
                "🌳 Sơ đồ tư duy: Cup ➔ Beverage culture (British afternoon tea, Espresso brewing) ➔ Ceramics craft (Bone china, Terracotta) ➔ Mindfulness ritual.",
                "💡 Mẹo siêu trí nhớ: Hai bàn tay bạn khum tròn lại như chữ 'C' rồi ôm lấy cốc trà thơm ➔ Chữ 'C' là Cup!",
                "🌌 Không gian & Cảm xúc: Cozy hygge moment (khoảnh khắc bình yên ấm cúng) • Artisanal café atmosphere.",
                "⚖️ La bàn ngữ pháp: Đếm được. Định lượng: 'a cup of coffee / a cup of tea'. Cẩn thận: 'a tea cup' là cái tách trống để đựng trà, còn 'a cup of tea' là tách đã rót đầy trà!",
                "🏆 IELTS Speaking (Band 7.5+):\nQ: What kind of drink do you usually prefer in the morning?\nA: 'Without fail, I inaugurate every morning with a freshly brewed cup of robust coffee. The intoxicating aroma alone never fails to invigorate my senses and instill a calm yet focused mental posture for the rigorous day ahead.'",
                "🔗 Phrasal Verbs: Sip on (nhấp từng ngụm chậm rãi) • Cool down (để nguội bớt kẻo bỏng) • Spill over (nước trà trào ra ngoài vành) • Wash up (rửa sạch ly tách sau khi uống).",
                "💼 Tiếng Anh Chuyên Ngành (Hospitality & Gastronomy):\nBone china composition (thành phần gốm sứ xương cao cấp) • Thermal shock resistance (khả năng chịu sốc nhiệt) • Aromatics extraction (sự chiết xuất các hợp chất hương thơm).",
                "🛒 Giao tiếp mua sắm: 'Are these handcrafted ceramic cups microwave-safe, and do they come as a matched set with complimentary saucers?'",
                "🤖 Gia sư AI lưu ý: Hãy nhớ phân biệt 'tea cup' (đồ vật) và 'cup of tea' (thức uống bên trong) - người bản xứ đánh giá cao sự tinh tế này!"
        );

        setDeepInfo("shoes",
                "🧬 Gốc từ: Nguồn gốc từ tiếng Anh cổ 'scōh', thuộc nhóm gốc German nguyên thủy '*skōhaz' (vật che phủ, bảo vệ bàn chân). Là một trong những từ cổ xưa nhất lịch sử nhân loại.",
                "🔍 Giải phẫu bộ phận: Outsole (Đế ngoài tiếp đất) • Midsole cushion (Đệm lót êm giữa) • Upper (Thân giày bao quanh) • Shoelaces (Dây buộc giày) • Insole (Lót giày trong có vòm đỡ) • Heel counter (Phần bọc gót giữ phom).",
                "⚠️ Cảnh báo nhầm lẫn: Phân biệt 'Shoes' (giày nói chung) vs 'Sneakers / Trainers' (giày thể thao êm) vs 'Boots' (giày ống cao cổ) vs 'Loafers' (giày lười xỏ chân).",
                "✨ Đa nghĩa: Thành ngữ sâu sắc: 'Put yourself in someone else's shoes' (hãy đặt mình vào vị trí thấu cảm nỗi niềm của người khác); 'Step into someone's shoes' (kế nhiệm trọng trách lớn).",
                "🇬🇧 UK: Trainers (giày thể thao) • Smart shoes (giày tây lịch sự)\n🇺🇸 US: Sneakers / Athletic shoes • Dress shoes",
                "🎨 5 Giác quan: Cushioned bounciness (độ đàn hồi êm ái đàn hồi) • Supple genuine leather (da thật mềm mại ôm chân) • Crisp rubber squeak (tiếng rít cao su bám sàn thể thao) • Breathable mesh weave (vải lưới dệt thông thoáng khí).",
                "🌳 Sơ đồ tư duy: Shoes ➔ Categories (Athletic, Formal, Casual, Trail running) ➔ Biomechanics (Arch support, Pronation, Shock absorption) ➔ Sustainable footwear.",
                "💡 Mẹo siêu trí nhớ: Khi bước đi chân bạn phát ra âm thanh 'Shoo... Shoo...' trên từng bước đường tự tin ➔ Shoes!",
                "🌌 Không gian & Cảm xúc: Dynamic active motion (chuyển động năng động bứt phá) • Sartorial elegance (phong thái thời trang lịch lãm).",
                "⚖️ La bàn ngữ pháp: LUÔN DÙNG DẠNG SỐ NHIỀU có 's' khi nói về đôi giày: 'These shoes ARE comfortable' (động từ số nhiều). Khi muốn đếm số lượng: dùng đơn vị 'a pair of shoes', 'two pairs of shoes'.",
                "🏆 IELTS Speaking (Band 7.5+):\nQ: What criteria do you prioritize when purchasing footwear?\nA: 'First and foremost, ergonomic support and supreme comfort take precedence over ephemeral fashion fads. Since I spend countless hours on my feet commuting, investing in high-grade shoes with shock-absorbing soles is non-negotiable for orthopedic well-being.'",
                "🔗 Phrasal Verbs: Lace up (thắt chặt dây giày) • Slip on (xỏ nhanh giày lười vào chân) • Kick off (đá văng giày ra nghỉ ngơi) • Break in (đi vài hôm cho giày mới mềm ra bớt chật).",
                "💼 Tiếng Anh Chuyên Ngành (Orthopedics & Fashion Retail):\nPlantar fasciitis relief (hỗ trợ giảm đau viêm cân gan chân) • Vulcanized rubber compound (hợp chất cao su lưu hóa siêu bền) • Ergonomic gait optimization (tối ưu hóa dáng đi tự nhiên).",
                "🛒 Giao tiếp mua sắm: 'Could you measure my foot width and check whether you have this model in a European size forty-two with wide toe-box fit?'",
                "🤖 Gia sư AI lưu ý: Hãy nhớ từ 'shoes' luôn đi với động từ số nhiều 'are', đừng bao giờ nói 'my shoes is' nhé!"
        );

        setDeepInfo("backpack",
                "🧬 Gốc từ: Từ ghép giữa 'Back' (lưng) + 'Pack' (gói ghém, bọc hành lý). Xuất hiện đầu tiên tại Mỹ vào đầu thế kỷ 20 thay cho từ cổ 'knapsack' của lính bộ binh.",
                "🔍 Giải phẫu bộ phận: Padded shoulder straps (Dây quai đeo vai có đệm êm) • Laptop compartment with fleece lining (Ngăn chống sốc lót nhung cho laptop) • Water-resistant zipper (Khóa kéo chống thấm nước) • Sternum chest buckle (Khóa gài ngực trợ lực) • Breathable back panel (Đệm lưng lưới thoáng khí).",
                "⚠️ Cảnh báo nhầm lẫn: Phân biệt 'Backpack' (ba lô hai quai đeo sau lưng) vs 'Messenger bag' (túi đeo chéo một bên) vs 'Briefcase' (cặp táp công sở xách tay) vs 'Luggage / Suitcase' (vali kéo du lịch).",
                "✨ Đa nghĩa: Động từ 'to backpack': đi du lịch bụi khắp nơi. Danh từ 'backpacker': dân phượt đam mê trải nghiệm tự do.",
                "🇬🇧 UK: Rucksack / Knapsack / School bag\n🇺🇸 US: Backpack / Bookbag / Daypack",
                "🎨 5 Giác quan: Weightless ergonomic distribution (trợ lực tản đều trọng lượng nhẹ tênh) • Durable ballistic nylon (vải dù chống rách sờ ráp tay) • Smooth gliding zipper click (tiếng kéo khóa mượt mà tanh tách) • Sturdy reinforced stitching (đường may gia cố chắc nịch).",
                "🌳 Sơ đồ tư duy: Backpack ➔ Everyday Carry (Laptop, Charger, Umbrella, Notebook) ➔ Adventure Gear (Hiking poles, Hydration bladder) ➔ Urban commute.",
                "💡 Mẹo siêu trí nhớ: 'BACK' là cái lưng, 'PACK' là đóng gói đồ đạc ➔ Đóng gói toàn bộ thế giới học tập rồi đeo lên lưng chính là Backpack!",
                "🌌 Không gian & Cảm xúc: Nomadic readiness (luôn sẵn sàng xách ba lô lên và đi) • Prepared efficiency.",
                "⚖️ La bàn ngữ pháp: Đếm được (a backpack). Giới từ: 'IN the backpack' (đồ đạc nằm bên trong ba lô), 'ON one's back' (đeo trên lưng).",
                "🏆 IELTS Speaking (Band 7.5+):\nQ: Do you often carry a backpack with you?\nA: 'Without a shadow of a doubt. As an urban commuter juggling work and continuous learning, my weather-resistant backpack is essentially my mobile workstation, accommodating my laptop, study notebooks, and daily essentials with remarkable ease.'",
                "🔗 Phrasal Verbs: Pack up (thu dọn đồ đạc vào ba lô) • Unzip (kéo khóa mở ra) • Strap on (cài dây đai an toàn ôm sát người) • Sling over (quẩy một bên quai lên vai nhanh chóng).",
                "💼 Tiếng Anh Chuyên Ngành (Product Design & Textile):\nCordura ballistic fabric (vải sợi Cordura chống đạn chống mài mòn) • Ergonomic load distribution (phân bổ tải trọng chuẩn công thái học) • Hydrophobic coating (lớp phủ kỵ nước ngăn mưa).",
                "🛒 Giao tiếp mua sắm: 'Does this commuter backpack include an anti-theft hidden pocket, and will the padded sleeve safely fit a sixteen-inch gaming laptop?'",
                "🤖 Gia sư AI lưu ý: Hãy dùng phrasal verb tự nhiên: 'Sling the backpack over my shoulder' để câu văn tràn đầy chất bản ngữ đời sống!"
        );

        setDeepInfo("desk",
                "🧬 Gốc từ: Bắt nguồn từ tiếng Latin Trung cổ 'desca' (bàn để đọc hoặc viết), tiến hóa từ tiếng Latinh cổ điển 'discus' (chiếc đĩa phẳng, mặt bàn phẳng hình tròn).",
                "🔍 Giải phẫu bộ phận: Desktop surface (Mặt phẳng bàn làm việc) • Cable management grommet (Lỗ luồn dây cáp gọn gàng) • Drawer organizers (Ngăn kéo chia ô) • Adjustable electric lifting legs (Chân bàn nâng hạ điện thông minh) • Anti-collision sensor (Cảm biến chống va chạm khi nâng hạ).",
                "⚠️ Cảnh báo nhầm lẫn: Phân biệt 'Desk' (bàn học/làm việc có ngăn kéo chuyên để làm việc trí óc) vs 'Table' (bàn ăn, bàn tiếp khách, bàn trà sinh hoạt chung).",
                "✨ Đa nghĩa: Thành ngữ văn phòng: 'Clear the desk' (giải quyết dứt điểm toàn bộ hồ sơ tồn đọng). 'Desk job' (công việc văn phòng ít di chuyển).",
                "🇬🇧 UK: Writing desk / Study bureau\n🇺🇸 US: Work desk / Standing desk / Workstation",
                "🎨 5 Giác quan: Spacious clutter-free surface (mặt bàn thênh thang không vướng bận) • Warm natural oak woodgrain (vân gỗ sồi tự nhiên ấm áp) • Solid wobble-free stability (vững chãi không chút rung lắc) • Matte anti-glare finish (lớp phủ mờ chống chói mắt).",
                "🌳 Sơ đồ tư duy: Desk ➔ Ergonomics (Standing desk converter, Monitor arm) ➔ Productivity zone (Pomodoro timer, Focus lighting) ➔ Aesthetics (Scandinavian, Industrial).",
                "💡 Mẹo siêu trí nhớ: Chữ 'D-E-S-K' bắt đầu bằng chữ D giống như chiếc ghế tựa đặt ngay ngắn trước mặt bàn làm việc trí thức!",
                "🌌 Không gian & Cảm xúc: Zen deep focus environment (môi trường tập trung tĩnh tại kiểu Thiền) • Professional diligence.",
                "⚖️ La bàn ngữ pháp: Đếm được (a desk). CỰC KỲ QUAN TRỌNG VỀ GIỚI TỪ: Ngồi làm việc trước bàn dùng giới từ 'AT': 'He is sitting AT his desk' (KHÔNG nói 'sitting on the desk' - ngồi lên mặt bàn là sai nghĩa).",
                "🏆 IELTS Speaking (Band 7.5+):\nQ: How do you organize your study desk?\nA: 'I am a firm proponent of minimalism, so I endeavor to keep my study desk completely devoid of clutter. Having an organized, pristine surface facilitates deep mental clarity and elevates my cognitive focus immensely during intense study sessions.'",
                "🔗 Phrasal Verbs: Clear off (dọn sạch đồ bừa bộn trên mặt bàn) • Set up (bài trí sắp xếp màn hình và đồ dùng) • Sit down at (ngồi vào bàn bắt đầu làm việc) • Lean over (cúi người qua mặt bàn nhìn bản vẽ).",
                "💼 Tiếng Anh Chuyên Ngành (Interior Architecture & Workplace Ergonomics):\nHeight-adjustable motorized mechanism (cơ cấu mô-tơ điện nâng hạ chiều cao) • Load-bearing capacity (khả năng chịu tải trọng mặt bàn) • Sit-stand alternating habit (thói quen luân phiên giữa ngồi và đứng làm việc).",
                "🛒 Giao tiếp mua sắm: 'Does this electric standing desk feature memory presets for different height levels, and what is the dual-motor warranty period?'",
                "🤖 Gia sư AI lưu ý: Hãy nhớ phân biệt kỹ: 'Desk' là bàn làm việc/bàn học, còn 'Table' là bàn ăn hoặc bàn uống trà. Nhầm lẫn hai từ này là lỗi phổ biến nhất của người học tiếng Anh!"
        );
    }

    private static void putAlias(String alias, String targetKey) {
        if (DICTIONARY.containsKey(targetKey.toLowerCase(Locale.ROOT))) {
            DICTIONARY.put(alias.toLowerCase(Locale.ROOT), copy(DICTIONARY.get(targetKey.toLowerCase(Locale.ROOT))));
        }
    }

    public static VisualWordInfo lookup(String label, float confidence) {
        if (label == null || label.trim().isEmpty()) {
            return null;
        }

        String cleaned = label.toLowerCase(Locale.ROOT).trim();

        // 1. Exact match
        if (DICTIONARY.containsKey(cleaned)) {
            VisualWordInfo info = copy(DICTIONARY.get(cleaned));
            info.setConfidence(confidence);
            info.setRawLabel(label);
            return info;
        }

        // 2. Contains match with longest key preference
        String bestKey = null;
        for (String key : DICTIONARY.keySet()) {
            if (cleaned.contains(key) || key.contains(cleaned)) {
                if (bestKey == null || key.length() > bestKey.length()) {
                    bestKey = key;
                }
            }
        }
        if (bestKey != null) {
            VisualWordInfo info = copy(DICTIONARY.get(bestKey));
            info.setConfidence(confidence);
            info.setRawLabel(label);
            return info;
        }

        // 3. Fallback generator for unmapped labels
        String capitalWord = capitalize(label);
        VisualWordInfo dynamicInfo = new VisualWordInfo();
        dynamicInfo.setWordEn(capitalWord);
        dynamicInfo.setIpa("/" + label.toLowerCase(Locale.ROOT).replace(" ", "·") + "/");
        dynamicInfo.setMeaningVi("Vật thể: " + capitalWord);
        dynamicInfo.setWordType("danh từ (noun)");
        dynamicInfo.setCefrLevel("B1");
        dynamicInfo.setCollocationEn("look at the " + label.toLowerCase(Locale.ROOT) + ", use a " + label.toLowerCase(Locale.ROOT));
        dynamicInfo.setCollocationVi("quan sát " + label + ", sử dụng " + label);
        dynamicInfo.setExampleSentenceEn("I noticed a " + label.toLowerCase(Locale.ROOT) + " right in front of the camera lens.");
        dynamicInfo.setExampleSentenceVi("Tôi nhìn thấy một " + label + " ngay trước ống kính máy ảnh.");
        dynamicInfo.setIdiomEn("Actions speak louder than words");
        dynamicInfo.setIdiomVi("Hành động có giá trị hơn lời nói");
        dynamicInfo.setDialogueEn("A: Have you noticed the " + label.toLowerCase(Locale.ROOT) + "?\nB: Yes, it's right there!");
        dynamicInfo.setDialogueVi("A: Bạn có thấy " + capitalWord + " không?\nB: Có chứ, nó ở ngay đằng kia!");
        dynamicInfo.setWordFamilyEn("use (v) • useful (adj) • usefulness (n)");
        dynamicInfo.setWordFamilyVi("sử dụng • hữu ích • tính hữu dụng");
        dynamicInfo.setAcademicC1WordEn("Specialized " + capitalWord);
        dynamicInfo.setAcademicC1MeaningVi("Thiết bị / vật phẩm chuyên dụng: " + capitalWord);
        dynamicInfo.setQuizQuestionEn("Identify the object: 'I noticed a _____ right in front of me.'");
        dynamicInfo.setQuizOptions(Arrays.asList(capitalWord, "Airplane", "Mountain"));
        dynamicInfo.setQuizAnswerIndex(0);
        dynamicInfo.setSyllableStress(capitalWord.toUpperCase());
        dynamicInfo.setConfidence(confidence);
        dynamicInfo.setRawLabel(label);

        // Dynamic encyclopedic deep learning defaults
        dynamicInfo.setEtymologyRoot("🧬 Gốc từ học: Danh từ thực thể '" + capitalWord + "' được định hình qua quá trình giao thoa ngôn ngữ hiện đại và đời sống thường nhật.");
        dynamicInfo.setComponentAnatomy("🔍 Bóc tách cấu tạo: Bề mặt kết cấu bên ngoài (Outer surface) • Bộ khung chịu lực (Support frame) • Chi tiết kết nối chức năng (Functional interface).");
        dynamicInfo.setConfusingWordAlert("⚠️ Lưu ý ngữ cảnh: Sử dụng đúng danh từ '" + capitalWord + "' theo đúng hoàn cảnh chuyên biệt, tránh nhầm lẫn với các vật dụng gia đình tương đồng.");
        dynamicInfo.setBritishVsAmerican("🇬🇧 UK: " + capitalWord + " (phát âm chuẩn Received Pronunciation)\n🇺🇸 US: " + capitalWord + " (phát âm giọng General American tự nhiên)");
        dynamicInfo.setGrammarRuleNotes("⚖️ La bàn ngữ pháp: Thường được dùng như danh từ đếm được (Countable Noun: a " + label.toLowerCase(Locale.ROOT) + "). Luôn chú ý mạo từ 'a/the' khi đặt câu.");
        dynamicInfo.setIeltsSpeakingSample("🏆 IELTS Speaking Band 7.5+:\nQ: Why is this object significant in modern life?\nA: 'Indisputably, having access to a reliable " + label.toLowerCase(Locale.ROOT) + " enhances our daily efficiency and elevates the overall quality of our living space.'");
        dynamicInfo.setPhrasalVerbs("🔗 Cụm động từ: Set up (bài trí, chuẩn bị) • Look at (quan sát kỹ) • Pick up (cầm nhấc lên) • Put away (cất gọn gàng sau khi dùng).");
        dynamicInfo.setSensoryAdjectives("🎨 5 Giác quan: Practical (tiện dụng) • Well-designed (thiết kế tinh tế) • Sturdy (bền vững) • Smooth finish (bề mặt nhẵn bóng).");
        dynamicInfo.setMindmapTree("🌳 Sơ đồ tư duy: " + capitalWord + " ➔ Material & Form ➔ Everyday Function ➔ Lifestyle Environment.");
        dynamicInfo.setMnemonicHook("💡 Mẹo siêu trí nhớ: Hãy nhắm mắt lại và liên tưởng hình ảnh của " + capitalWord + " ngay trong không gian sống của bạn để khắc sâu phản xạ từ!");
        dynamicInfo.setEspSpecializedVocab("💼 Chuyên ngành: Ergonomic specification • Material composition • Operational lifecycle.");
        dynamicInfo.setShoppingInquiries("🛒 Giao tiếp mua sắm: 'Could you tell me the retail price and warranty conditions for this " + label.toLowerCase(Locale.ROOT) + "?'");
        dynamicInfo.setAiTutorFact("🤖 Gia sư AI lưu ý: Hãy thường xuyên thực hành đặt câu hoàn chỉnh với '" + capitalWord + "' kết hợp cùng các tính từ miêu tả để não bộ tạo liên kết dài hạn!");
        return dynamicInfo;
    }

    private static VisualWordInfo copy(VisualWordInfo src) {
        VisualWordInfo dest = new VisualWordInfo(
                src.getWordEn(),
                src.getIpa(),
                src.getMeaningVi(),
                src.getWordType(),
                src.getCefrLevel(),
                src.getCollocationEn(),
                src.getCollocationVi(),
                src.getExampleSentenceEn(),
                src.getExampleSentenceVi()
        );
        dest.setDetailedAiNotesVi(src.getDetailedAiNotesVi());
        dest.setIdiomEn(src.getIdiomEn());
        dest.setIdiomVi(src.getIdiomVi());
        dest.setDialogueEn(src.getDialogueEn());
        dest.setDialogueVi(src.getDialogueVi());
        dest.setWordFamilyEn(src.getWordFamilyEn());
        dest.setWordFamilyVi(src.getWordFamilyVi());
        dest.setAcademicC1WordEn(src.getAcademicC1WordEn());
        dest.setAcademicC1MeaningVi(src.getAcademicC1MeaningVi());
        dest.setQuizQuestionEn(src.getQuizQuestionEn());
        if (src.getQuizOptions() != null) {
            dest.setQuizOptions(new ArrayList<>(src.getQuizOptions()));
        }
        dest.setQuizAnswerIndex(src.getQuizAnswerIndex());
        dest.setSyllableStress(src.getSyllableStress());

        // Encyclopedic Deep Learning fields (Ideas 16 to 35)
        dest.setEtymologyRoot(src.getEtymologyRoot());
        dest.setComponentAnatomy(src.getComponentAnatomy());
        dest.setConfusingWordAlert(src.getConfusingWordAlert());
        dest.setPolysemyNotes(src.getPolysemyNotes());
        dest.setBritishVsAmerican(src.getBritishVsAmerican());
        dest.setSensoryAdjectives(src.getSensoryAdjectives());
        dest.setMindmapTree(src.getMindmapTree());
        dest.setMnemonicHook(src.getMnemonicHook());
        dest.setAtmosphericMood(src.getAtmosphericMood());
        dest.setGrammarRuleNotes(src.getGrammarRuleNotes());
        dest.setIeltsSpeakingSample(src.getIeltsSpeakingSample());
        dest.setPhrasalVerbs(src.getPhrasalVerbs());
        dest.setEspSpecializedVocab(src.getEspSpecializedVocab());
        dest.setShoppingInquiries(src.getShoppingInquiries());
        dest.setActionVerbsInMotion(src.getActionVerbsInMotion());
        dest.setAiTutorFact(src.getAiTutorFact());
        return dest;
    }

    private static String capitalize(String text) {
        if (text == null || text.isEmpty()) return "";
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (!w.isEmpty()) {
                sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1).toLowerCase(Locale.ROOT)).append(" ");
            }
        }
        return sb.toString().trim();
    }
}
