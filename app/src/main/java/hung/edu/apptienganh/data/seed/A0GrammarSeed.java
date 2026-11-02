package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.GrammarPoint;

public final class A0GrammarSeed {

    private A0GrammarSeed() {}

    public static List<GrammarPoint> buildGrammarPoints() {
        List<GrammarPoint> list = new ArrayList<>();

        list.add(new GrammarPoint("A0", "Personal pronouns",
                "I / You / He / She / It / We / They + Verb",
                "Dùng làm chủ ngữ thay cho danh từ, đứng đầu câu trước động từ.",
                "Đứng đầu câu, trước động từ chính.",
                "I am a student.\nShe likes English.\nThey are from Vietnam.",
                "Me am a student.\nHer likes English.\nHim is tall.",
                "'Me', 'him', 'her' là đại từ tân ngữ, không đứng làm chủ ngữ. Phải dùng I, he, she.",
                "Who is the subject in 'They are happy'?|||They"));

        list.add(new GrammarPoint("A0", "Verb to be",
                "S + am/is/are + Noun/Adj\nS + am/is/are + not + Noun/Adj\nAm/Is/Are + S + ...?",
                "Diễn tả tên, tuổi, nghề nghiệp, quốc tịch, trạng thái hoặc tính chất.",
                "today, now, here, always (với trạng thái).",
                "I am Hung.\nShe is a teacher.\nThey are students.",
                "I is Hung.\nShe are a teacher.\nThey is students.",
                "I → am; he/she/it/danh từ số ít → is; you/we/they/danh từ số nhiều → are.",
                "Fill in: I ___ a student.|||am"));

        list.add(new GrammarPoint("A0", "Possessive adjectives",
                "my / your / his / her / its / our / their + Noun",
                "Đứng trước danh từ để chỉ sự sở hữu.",
                "Luôn có danh từ đi sau.",
                "My name is Linh.\nHer book is new.\nTheir house is big.",
                "Me name is Linh.\nShe book is new.\nThey house is big.",
                "Không dùng đại từ chủ ngữ/tân ngữ thay cho tính từ sở hữu.",
                "Choose: ___ name is John. (He)|||His"));

        list.add(new GrammarPoint("A0", "Articles: a/an/the",
                "a + noun (consonant sound)\nan + noun (vowel sound)\nthe + specific noun",
                "a/an dùng với danh từ số ít không xác định lần đầu. the dùng khi đã biết rõ.",
                "a/an: lần đầu nhắc; the: đã nhắc trước đó, duy nhất.",
                "I have a cat. The cat is white.\nShe is an engineer.\nThe sun is hot.",
                "I have cat.\nShe is a engineer.\nI like a sun.",
                "Chọn a/an theo âm đầu (a university / an hour). the dùng cho sự vật xác định.",
                "I eat ___ apple every day.|||an"));

        list.add(new GrammarPoint("A0", "Singular and plural nouns",
                "Singular: one noun\nPlural: noun + -s / -es / irregular",
                "Số ít chỉ một; số nhiều chỉ từ hai trở lên.",
                "one, two, many, some, a lot of.",
                "one book → two books\none child → two children\none box → two boxes",
                "two book\nthree childs\nmany boxs",
                "Hầu hết danh từ thêm -s; tận cùng ch/sh/s/x + -es; một số bất quy tắc (child→children).",
                "Plural of 'child' is?|||children"));

        list.add(new GrammarPoint("A0", "This/That/These/Those",
                "This/That + singular noun\nThese/Those + plural noun",
                "this/these: gần người nói; that/those: xa người nói.",
                "here (gần) / there (xa).",
                "This is my pen.\nThose are my friends.\nThat book is interesting.",
                "This are my pens.\nThose is my friend.\nThis books are mine.",
                "This/that đi với số ít; these/those đi với số nhiều.",
                "___ students are in my class. (far, plural)|||Those"));

        list.add(new GrammarPoint("A0", "Simple present: I/You/We/They",
                "I/You/We/They + V(infinitive)",
                "Diễn tả thói quen, sự thật hiển nhiên, lịch trình cố định.",
                "every day, always, usually, often, sometimes, never.",
                "I live in Hanoi.\nWe study English.\nThey play soccer on Sunday.",
                "I lives in Hanoi.\nWe studys English.\nThey plays soccer.",
                "Chủ ngữ I/you/we/they: động từ giữ nguyên dạng gốc, không thêm -s.",
                "They ___ English every day. (study)|||study"));

        list.add(new GrammarPoint("A0", "Simple present: He/She/It",
                "He/She/It + V-s/es",
                "Giống hiện tại đơn nhưng động từ chia theo ngôi thứ 3 số ít.",
                "every day, always, usually, on Mondays.",
                "He works in a hospital.\nShe watches TV.\nIt rains a lot.",
                "He work in a hospital.\nShe watchs TV.\nIt rain a lot.",
                "Thêm -s hoặc -es (watch→watches, go→goes) với he/she/it.",
                "She ___ to school by bus. (go)|||goes"));

        list.add(new GrammarPoint("A0", "Negative sentences with do/does not",
                "I/You/We/They + do not (don't) + V\nHe/She/It + does not (doesn't) + V",
                "Phủ định thói quen hoặc sự thật ở hiện tại đơn.",
                "not, never, don't, doesn't.",
                "I don't like coffee.\nShe doesn't eat meat.\nThey don't live here.",
                "I not like coffee.\nShe don't eat meat.\nHe doesn't eats meat.",
                "Cần trợ động từ do/does + động từ gốc (không thêm -s sau doesn't).",
                "He ___ like fish.|||doesn't"));

        list.add(new GrammarPoint("A0", "Yes/No questions",
                "Do + I/you/we/they + V?\nDoes + he/she/it + V?",
                "Hỏi có/không với câu trả lời Yes/No.",
                "Do you...? Does she...? Is/Are...? (với to be).",
                "Do you speak English?\nDoes he work here?\nAre they students?",
                "Do you speaks English?\nDoes he works here?\nIs they students?",
                "Sau do/does dùng động từ gốc. Với to be đảo am/is/are lên đầu.",
                "___ you like music?|||Do"));

        list.add(new GrammarPoint("A0", "Wh-questions",
                "Wh-word + do/does + S + V?\nWh-word + am/is/are + S + ...?",
                "Hỏi thông tin cụ thể: what, where, when, who, why, how.",
                "What, Where, When, Who, Why, How.",
                "What is your name?\nWhere do you live?\nHow old are you?",
                "What your name is?\nWhere you live?\nHow old you are?",
                "Với to be: Wh + am/is/are + S. Với động từ thường: Wh + do/does + S + V.",
                "___ is your hometown?|||Where"));

        list.add(new GrammarPoint("A0", "Basic prepositions: in/on/at",
                "in + month/year/room/city\non + day/date/surface\nat + time/place (point)",
                "Chỉ thời gian, địa điểm, vị trí.",
                "in the morning, on Monday, at 7 o'clock, at school.",
                "I was born in 2005.\nClass starts at 8.\nThe book is on the desk.\nI live in Hanoi.",
                "I was born on 2005.\nClass starts in 8 o'clock.\nThe book is in the desk.",
                "in: khoảng thời gian dài/không gian bao bọc; on: ngày/mặt phẳng; at: giờ/điểm cụ thể.",
                "We have English ___ Monday.|||on"));

        return list;
    }
}
