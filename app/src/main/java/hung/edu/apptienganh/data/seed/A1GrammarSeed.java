package hung.edu.apptienganh.data.seed;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.GrammarPoint;

public final class A1GrammarSeed {

    private A1GrammarSeed() {}

    public static List<GrammarPoint> buildGrammarPoints() {
        List<GrammarPoint> list = new ArrayList<>();

        list.add(gp("Present simple review",
                "S + V(s/es) / S + do/does + not + V / Do/Does + S + V?",
                "Diễn tả thói quen, sự thật hiện tại và lịch trình cố định.",
                "every day, usually, always, often, sometimes, never",
                "I live in Hanoi.\nShe works in a hospital.\nThey play football on Sunday.",
                "I lives in Hanoi.\nShe work in a hospital.\nThey plays football.",
                "He/She/It thêm -s/-es; I/You/We/They dùng động từ nguyên mẫu.",
                "He ___ to school every day. (go)|||goes"));

        list.add(gp("Like / want / need + noun / to V",
                "S + like/want/need + noun\nS + like/want/need + to + V",
                "Diễn tả sở thích, mong muốn hoặc nhu cầu.",
                "like, want, need, love, prefer",
                "I like coffee.\nShe wants to travel.\nWe need a new bag.",
                "I like to coffee.\nShe want travel.\nWe need buy a bag.",
                "Sau like/want/need có thể là danh từ hoặc to + động từ nguyên mẫu.",
                "I want ___ English. (learn)|||to learn"));

        list.add(gp("There is / There are",
                "There is + singular noun\nThere are + plural noun",
                "Nói sự vật tồn tại ở đâu đó.",
                "there is, there are, in, on, near",
                "There is a book on the table.\nThere are two chairs in the room.",
                "There are a book on the table.\nThere is two chairs in the room.",
                "Danh từ số ít → there is; số nhiều → there are.",
                "There ___ some milk in the fridge.|||is"));

        list.add(gp("Countable nouns",
                "a/an + countable noun\nmany + countable noun\nHow many + ...?",
                "Danh từ đếm được có dạng số ít và số nhiều.",
                "a, an, many, few, several, two, three",
                "I have an apple.\nThere are many students in the class.",
                "I have many apple.\nThere is many students.",
                "Danh từ đếm được cần a/an ở số ít và thêm -s ở số nhiều.",
                "How ___ books do you have?|||many"));

        list.add(gp("Uncountable nouns",
                "some + uncountable noun\nmuch + uncountable noun\nHow much + ...?",
                "Danh từ không đếm được không có dạng số nhiều thông thường.",
                "some, much, a little, water, rice, money",
                "I need some water.\nThere is not much time.",
                "I need a water.\nThere are much time.",
                "Không dùng a/an với danh từ không đếm được như water, rice, money.",
                "How ___ sugar do you want?|||much"));

        list.add(gp("Some / any",
                "some + noun (affirmative)\nany + noun (questions/negatives)",
                "some dùng trong câu khẳng định; any trong câu hỏi và phủ định.",
                "some, any, something, anything",
                "I have some friends.\nDo you have any questions?\nThere isn't any milk.",
                "I have any friends.\nDo you have some questions in negative?",
                "Câu khẳng định thường dùng some; câu phủ định/hỏi thường dùng any.",
                "Are there ___ apples in the fridge?|||any"));

        list.add(gp("Can / cannot",
                "S + can/can't + V (base form)",
                "Diễn tả khả năng, xin phép hoặc đề nghị.",
                "can, can't, cannot, could (basic)",
                "I can swim.\nShe can't drive.\nCan you help me?",
                "I can to swim.\nShe can drives.\nCan you helps me?",
                "Sau can/can't luôn là động từ nguyên mẫn, không thêm to hoặc -s.",
                "___ you speak English?|||Can"));

        list.add(gp("Prepositions of place",
                "in + enclosed place\non + surface\nat + specific point/near",
                "Chỉ vị trí của người/vật.",
                "in, on, at, under, behind, next to, between",
                "The book is on the table.\nI am at the bus stop.\nShe is in the kitchen.",
                "The book is in the table (surface).\nI am in the bus stop.",
                "on = trên bề mặt; in = bên trong; at = tại điểm cụ thể.",
                "The cat is ___ the chair. (under)|||under"));

        list.add(gp("Prepositions of time",
                "in + month/year/season\non + day/date\nat + clock time",
                "Chỉ thời gian.",
                "in, on, at, morning, afternoon, evening",
                "I wake up at seven.\nWe have English on Monday.\nMy birthday is in May.",
                "I wake up in seven o'clock.\nWe have English at Monday.",
                "at + giờ; on + ngày; in + tháng/năm/mùa.",
                "The meeting is ___ 3 pm.|||at"));

        list.add(gp("Adverbs of frequency",
                "S + always/usually/often/sometimes/never + V",
                "Diễn tả tần suất thói quen.",
                "always, usually, often, sometimes, never, every day",
                "I always brush my teeth.\nShe sometimes eats out.\nThey never watch TV late.",
                "I always am happy.\nShe eats sometimes out.",
                "Trạng từ tần suất đứng trước động từ thường, sau động từ to be.",
                "He ___ goes to the gym. (often)|||often"));

        list.add(gp("Present continuous",
                "S + am/is/are + V-ing",
                "Diễn tả hành động đang xảy ra ngay lúc nói.",
                "now, at the moment, right now, today",
                "I am studying now.\nShe is cooking dinner.\nThey are waiting for the bus.",
                "I am study now.\nShe cooking dinner.\nThey is waiting.",
                "Cấu trúc: am/is/are + V-ing; không dùng với stative verbs như know, like (nghĩa sở thích).",
                "Look! It ___ raining.|||is"));

        list.add(gp("Past simple: was/were",
                "S + was/were + noun/adj/place",
                "Diễn tả trạng thái hoặc vị trí trong quá khứ với to be.",
                "yesterday, last week, ago, was, were",
                "I was at home yesterday.\nThey were happy last night.\nShe was not tired.",
                "I were at home.\nThey was happy.\nShe were not tired.",
                "I/he/she/it → was; you/we/they → were.",
                "We ___ at the airport last Sunday.|||were"));

        list.add(gp("Basic imperatives",
                "V (base) / Don't + V",
                "Ra lệnh, hướng dẫn hoặc yêu cầu lịch sự.",
                "please, don't, open, close, sit, stand",
                "Open the door, please.\nDon't run in the classroom.\nSit down.",
                "You open the door please.\nNot run in the classroom.",
                "Mệnh lệnh bỏ chủ ngữ you; phủ định: Don't + V.",
                "___ quiet, please.|||Be"));

        list.add(gp("Basic conjunctions: and/but/because",
                "Clause + and/but/because + clause",
                "Nối ý bổ sung, đối lập hoặc giải thích.",
                "and, but, because, so",
                "I like tea and coffee.\nShe is tired but happy.\nI stay home because it is raining.",
                "I like tea but coffee (when adding).\nBecause it is raining so I stay home.",
                "and = và; but = nhưng; because = vì; không dùng because và so cùng lúc.",
                "I am late ___ the bus was slow.|||because"));

        return list;
    }

    private static GrammarPoint gp(String title, String formula, String usageVi, String signalWords,
                                   String correctEx, String wrongEx, String explanationVi, String practice) {
        return new GrammarPoint("A1", title, formula, usageVi, signalWords,
                correctEx, wrongEx, explanationVi, practice);
    }
}
