package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.QuizQuestion;

public final class A0QuizSeed {

    private A0QuizSeed() {}

    public static List<QuizQuestion> buildDayQuizzes() {
        List<QuizQuestion> q = new ArrayList<>();
        seedDay1(q); seedDay2(q); seedDay3(q); seedDay4(q); seedDay5(q); seedDay6(q); seedDay7(q);
        seedDay8(q); seedDay9(q); seedDay10(q); seedDay11(q); seedDay12(q); seedDay13(q); seedDay14(q);
        return q;
    }

    private static void add(List<QuizQuestion> q, int day, String type, String question,
                            String a, String b, String c, String d, String ans, String explain) {
        q.add(new QuizQuestion(0, day, type, question, a, b, c, d, ans, explain));
    }

    private static void seedDay1(List<QuizQuestion> q) {
        add(q, 1, "VOCABULARY", "Từ 'hello' nghĩa là gì?", "Tạm biệt", "Xin chào", "Cảm ơn", "Xin lỗi", "B", "'Hello' dùng để chào khi gặp ai đó.");
        add(q, 1, "VOCABULARY", "'Good morning' dùng khi nào?", "Buổi tối", "Buổi sáng", "Buổi đêm", "Khi chia tay", "B", "'Good morning' = chào buổi sáng.");
        add(q, 1, "GRAMMAR", "Chọn câu đúng:", "I is a student.", "I am a student.", "I are a student.", "I be a student.", "B", "Chủ ngữ 'I' đi với 'am'.");
        add(q, 1, "GRAMMAR", "Điền: My name ___ Hung.", "am", "is", "are", "be", "B", "'My name' là ngôi thứ ba số ít → is.");
        add(q, 1, "CONVERSATION", "Khi gặp lần đầu, nói gì?", "Good night", "Nice to meet you", "See you tomorrow", "I am fine", "B", "'Nice to meet you' = rất vui được gặp bạn.");
    }

    private static void seedDay2(List<QuizQuestion> q) {
        add(q, 2, "VOCABULARY", "'Name' nghĩa là gì?", "Tuổi", "Tên", "Quê quán", "Nghề nghiệp", "B", "'Name' = tên.");
        add(q, 2, "VOCABULARY", "Hỏi tên bằng câu nào?", "How old are you?", "What is your name?", "Where are you from?", "How are you?", "B", "What is your name? = Tên bạn là gì?");
        add(q, 2, "GRAMMAR", "Chọn đại từ đúng: ___ is my friend. (Linh - nữ)", "He", "She", "It", "They", "B", "Linh (nữ) → she.");
        add(q, 2, "GRAMMAR", "They ___ students.", "am", "is", "are", "be", "C", "They → are.");
        add(q, 2, "CONVERSATION", "Trả lời 'How old are you?'", "I am from Vietnam.", "I am twelve years old.", "My name is Linh.", "I am fine.", "B", "How old = hỏi tuổi.");
    }

    private static void seedDay3(List<QuizQuestion> q) {
        add(q, 3, "VOCABULARY", "'Fine' trong 'I am fine' nghĩa là?", "Buồn", "Khỏe, ổn", "Đói", "Mệt", "B", "Fine = khỏe, ổn.");
        add(q, 3, "VOCABULARY", "'Teacher' là gì?", "Học sinh", "Giáo viên", "Bác sĩ", "Kỹ sư", "B", "Teacher = giáo viên.");
        add(q, 3, "GRAMMAR", "She ___ a doctor.", "am", "is", "are", "be", "B", "She → is.");
        add(q, 3, "GRAMMAR", "We ___ happy.", "am", "is", "are", "be", "C", "We → are.");
        add(q, 3, "GRAMMAR", "Câu hỏi đúng:", "You is a student?", "Are you a student?", "Is you a student?", "Do you be a student?", "B", "Are you...? với to be.");
    }

    private static void seedDay4(List<QuizQuestion> q) {
        add(q, 4, "VOCABULARY", "'Mother' nghĩa là?", "Bố", "Mẹ", "Anh trai", "Chị gái", "B", "Mother = mẹ.");
        add(q, 4, "VOCABULARY", "'Family' là gì?", "Trường học", "Gia đình", "Bạn bè", "Công viên", "B", "Family = gia đình.");
        add(q, 4, "GRAMMAR", "___ name is Anna. (cô ấy)", "She", "Her", "Hers", "He's", "B", "Her + noun = tính từ sở hữu.");
        add(q, 4, "GRAMMAR", "This is ___ book. (tôi)", "my", "me", "I", "mine", "A", "My book = sách của tôi.");
        add(q, 4, "CONVERSATION", "Giới thiệu mẹ:", "This is my mother.", "This is me mother.", "This mother is my.", "This is I mother.", "A", "This is my mother. đúng ngữ pháp.");
    }

    private static void seedDay5(List<QuizQuestion> q) {
        add(q, 5, "VOCABULARY", "'School' nghĩa là?", "Bệnh viện", "Trường học", "Nhà hàng", "Cửa hàng", "B", "School = trường học.");
        add(q, 5, "VOCABULARY", "'Homework' là gì?", "Bài kiểm tra", "Bài tập về nhà", "Giờ ra chơi", "Thư viện", "B", "Homework = bài tập về nhà.");
        add(q, 5, "GRAMMAR", "Số nhiều của 'book':", "book", "books", "bookes", "bookies", "B", "Thêm -s: books.");
        add(q, 5, "GRAMMAR", "Số nhiều của 'child':", "childs", "childes", "children", "childrens", "C", "Bất quy tắc: children.");
        add(q, 5, "CONVERSATION", "Mở sách trong lớp:", "Open your book, please.", "Open you book, please.", "Open book your, please.", "Please book open.", "A", "Open your book, please. lịch sự và đúng.");
    }

    private static void seedDay6(List<QuizQuestion> q) {
        add(q, 6, "VOCABULARY", "Số 'fifteen' là?", "5", "10", "15", "50", "C", "Fifteen = 15.");
        add(q, 6, "VOCABULARY", "'Age' nghĩa là?", "Tên", "Tuổi", "Địa chỉ", "Số điện thoại", "B", "Age = tuổi.");
        add(q, 6, "GRAMMAR", "I am ___ years old. (10)", "ten", "tenth", "tenning", "tens", "A", "Ten years old = mười tuổi.");
        add(q, 6, "GRAMMAR", "There ___ four books.", "am", "is", "are", "be", "C", "Four books (số nhiều) → are.");
        add(q, 6, "CONVERSATION", "Hỏi tuổi:", "What is your age name?", "How old are you?", "Where is your age?", "What age you have?", "B", "How old are you? = Bạn bao nhiêu tuổi?");
    }

    private static void seedDay7(List<QuizQuestion> q) {
        add(q, 7, "VOCABULARY", "'Thank you' nghĩa là?", "Xin lỗi", "Cảm ơn", "Không có gì", "Tạm biệt", "B", "Thank you = cảm ơn.");
        add(q, 7, "VOCABULARY", "'Student' là?", "Giáo viên", "Học sinh", "Bác sĩ", "Đầu bếp", "B", "Student = học sinh.");
        add(q, 7, "GRAMMAR", "They ___ from Vietnam.", "am", "is", "are", "be", "C", "They → are.");
        add(q, 7, "GRAMMAR", "My sister ___ kind.", "am", "is", "are", "be", "B", "My sister (số ít) → is.");
        add(q, 7, "LISTENING", "Nghe hiểu: 'I am fine, thank you' – người nói thế nào?", "Buồn", "Khỏe", "Đói", "Giận", "B", "Fine = khỏe.");
    }

    private static void seedDay8(List<QuizQuestion> q) {
        add(q, 8, "VOCABULARY", "'Live' nghĩa là?", "Làm việc", "Sống", "Chơi", "Ngủ", "B", "Live = sống.");
        add(q, 8, "VOCABULARY", "'Study' là?", "Học", "Ăn", "Uống", "Chạy", "A", "Study = học.");
        add(q, 8, "GRAMMAR", "I ___ English every day.", "study", "studies", "studying", "studys", "A", "I → động từ gốc study.");
        add(q, 8, "GRAMMAR", "We ___ in Hanoi.", "live", "lives", "living", "liveing", "A", "We → live.");
        add(q, 8, "CONVERSATION", "Nói nơi ở:", "I live in Hanoi.", "I lives in Hanoi.", "I living in Hanoi.", "I am live in Hanoi.", "A", "I live... đúng hiện tại đơn.");
    }

    private static void seedDay9(List<QuizQuestion> q) {
        add(q, 9, "VOCABULARY", "'Watch' nghĩa là?", "Đọc", "Xem", "Viết", "Nghe", "B", "Watch TV = xem TV.");
        add(q, 9, "VOCABULARY", "'Like' là?", "Ghét", "Thích", "Cần", "Muốn", "B", "Like = thích.");
        add(q, 9, "GRAMMAR", "She ___ TV every evening.", "watch", "watches", "watchs", "watching", "B", "She → watches.");
        add(q, 9, "GRAMMAR", "He ___ to school by bus.", "go", "goes", "going", "gos", "B", "He → goes.");
        add(q, 9, "GRAMMAR", "My father ___ in an office.", "work", "works", "working", "workes", "B", "He → works.");
    }

    private static void seedDay10(List<QuizQuestion> q) {
        add(q, 10, "VOCABULARY", "'Wake up' nghĩa là?", "Đi ngủ", "Thức dậy", "Ăn sáng", "Đi học", "B", "Wake up = thức dậy.");
        add(q, 10, "VOCABULARY", "'Evening' là?", "Buổi sáng", "Buổi trưa", "Buổi tối", "Buổi đêm", "C", "Evening = buổi tối.");
        add(q, 10, "GRAMMAR", "I ___ up at six.", "wake", "wakes", "waking", "wkake", "A", "I → wake.");
        add(q, 10, "GRAMMAR", "They ___ homework in the evening.", "do", "does", "doing", "dos", "A", "They → do.");
        add(q, 10, "CONVERSATION", "Hỏi giờ dậy:", "What time do you wake up?", "What time you wake up?", "When you wake up time?", "What wake up time?", "A", "What time do you wake up? chuẩn.");
    }

    private static void seedDay11(List<QuizQuestion> q) {
        add(q, 11, "VOCABULARY", "'Apple' là?", "Quả cam", "Quả táo", "Quả chuối", "Quả nho", "B", "Apple = táo.");
        add(q, 11, "VOCABULARY", "'Water' nghĩa là?", "Sữa", "Nước", "Trà", "Cà phê", "B", "Water = nước.");
        add(q, 11, "GRAMMAR", "I eat ___ apple.", "a", "an", "the", "some", "B", "Apple bắt đầu bằng nguyên âm → an.");
        add(q, 11, "GRAMMAR", "Can I have ___ cup of tea?", "a", "an", "the", "many", "A", "A cup of tea = một tách trà.");
        add(q, 11, "CONVERSATION", "Gọi trà:", "Can I have a cup of tea, please?", "I want tea cup one.", "Give tea me.", "Tea cup I want.", "A", "Can I have... please? lịch sự.");
    }

    private static void seedDay12(List<QuizQuestion> q) {
        add(q, 12, "VOCABULARY", "'Clock' là gì?", "Lịch", "Đồng hồ", "Bản đồ", "Cặp sách", "B", "Clock = đồng hồ.");
        add(q, 12, "VOCABULARY", "'Tomorrow' nghĩa là?", "Hôm qua", "Hôm nay", "Ngày mai", "Tuần sau", "C", "Tomorrow = ngày mai.");
        add(q, 12, "GRAMMAR", "Class starts ___ eight.", "in", "on", "at", "to", "C", "At + giờ cụ thể.");
        add(q, 12, "GRAMMAR", "We have English ___ Monday.", "in", "on", "at", "by", "B", "On + ngày trong tuần.");
        add(q, 12, "CONVERSATION", "Hỏi giờ:", "What time is it?", "What is time it?", "What hour are it?", "How time is?", "A", "What time is it? = Mấy giờ?");
    }

    private static void seedDay13(List<QuizQuestion> q) {
        add(q, 13, "VOCABULARY", "'From' trong 'I am from Vietnam' nghĩa là?", "Đến từ", "Đi đến", "Sống tại", "Yêu", "A", "Be from = đến từ.");
        add(q, 13, "VOCABULARY", "'Meet' trong 'Nice to meet you':", "Gặp", "Nói", "Nghe", "Viết", "A", "Meet = gặp.");
        add(q, 13, "GRAMMAR", "I ___ Hung. (tên)", "am", "is", "are", "be", "A", "I am Hung.");
        add(q, 13, "GRAMMAR", "She ___ from Japan.", "am", "is", "are", "be", "B", "She is from...");
        add(q, 13, "CONVERSATION", "Tự giới thiệu đầy đủ:", "Hello, my name is Linh. I am from Vietnam.", "Hello, name my Linh.", "I name Linh am.", "Linh is name my.", "A", "Cấu trúc chuẩn: Hello, my name is...");
    }

    private static void seedDay14(List<QuizQuestion> q) {
        add(q, 14, "VOCABULARY", "'Beautiful' nghĩa là?", "Xấu", "Đẹp", "To", "Nhỏ", "B", "Beautiful = đẹp.");
        add(q, 14, "VOCABULARY", "'Practice' là?", "Kiểm tra", "Luyện tập", "Nghỉ ngơi", "Ngủ", "B", "Practice = luyện tập.");
        add(q, 14, "GRAMMAR", "I ___ a student and I ___ English.", "am / like", "is / likes", "are / like", "am / likes", "A", "I am... I like...");
        add(q, 14, "GRAMMAR", "They ___ in Vietnam.", "live", "lives", "living", "lived", "A", "They → live.");
        add(q, 14, "CONVERSATION", "Kết thúc khóa A0 – câu tốt nhất:", "Hello, my name is Hung. I am a student from Vietnam.", "I student Hung Vietnam.", "Name Hung am student.", "Hung is I student.", "A", "Câu đầy đủ, tự nhiên, đúng ngữ pháp A0.");
    }
}
