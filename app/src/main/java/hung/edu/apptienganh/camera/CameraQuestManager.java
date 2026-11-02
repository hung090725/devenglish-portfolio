package hung.edu.apptienganh.camera;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Manages Scavenger Hunt Quests (Ý tưởng 1),
 * Visual Sticker Album / Pokédex tracking (Ý tưởng 4 & 15),
 * 1-Click Anki / Flashcard export (Ý tưởng 14),
 * and 30-Day Bilingual Home Journey (Ý tưởng 34).
 */
public class CameraQuestManager {

    private static final String PREF_NAME = "camera_quest_prefs";
    private static final String KEY_DISCOVERED_WORDS = "discovered_words";
    private static final String KEY_CURRENT_QUEST_INDEX = "current_quest_index";
    private static final String KEY_QUEST_FOUND_WORDS = "quest_found_words";
    private static final String KEY_30_DAY_INDEX = "journey_30_day_index";

    public static class Quest {
        public String id;
        public String titleVi;
        public String descriptionVi;
        public List<String> targetKeywords;
        public int requiredCount;

        public Quest(String id, String titleVi, String descriptionVi, List<String> targetKeywords, int requiredCount) {
            this.id = id;
            this.titleVi = titleVi;
            this.descriptionVi = descriptionVi;
            this.targetKeywords = targetKeywords;
            this.requiredCount = requiredCount;
        }
    }

    private final SharedPreferences prefs;
    private final List<Quest> allQuests = new ArrayList<>();
    private int currentQuestIndex = 0;
    private int journey30DayIndex = 1;
    private final Set<String> currentQuestFoundWords = new HashSet<>();
    private final Set<String> totalDiscoveredWords = new LinkedHashSet<>();

    public CameraQuestManager(Context context) {
        this.prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        initQuests();
        loadState();
    }

    private void initQuests() {
        allQuests.add(new Quest(
                "QUEST_STUDY",
                "🎯 Săn Đồ Dùng Học Tập & Bàn Làm Việc",
                "Tìm và quét 3 đồ vật: Laptop/Máy tính, Sách/Vở, hoặc Bút viết.",
                Arrays.asList("laptop", "computer", "keyboard", "mouse", "book", "notebook", "pen", "pencil", "backpack"),
                3
        ));

        allQuests.add(new Quest(
                "QUEST_KITCHEN",
                "☕ Săn Góc Bếp & Thức Uống",
                "Tìm và quét 3 vật dụng ăn uống: Tách cà phê, Bình nước, hoặc Bánh mì.",
                Arrays.asList("cup", "coffee cup", "mug", "bottle", "water bottle", "plate", "bowl", "spoon", "fork", "bread", "apple", "milk"),
                3
        ));

        allQuests.add(new Quest(
                "QUEST_FASHION",
                "👟 Săn Trang Phục & Phụ Kiện",
                "Tìm và quét 3 phụ kiện cá nhân: Giày, Áo sơ mi/thun, Ba lô, hoặc Kính mắt.",
                Arrays.asList("shoes", "sneakers", "shirt", "t-shirt", "jacket", "glasses", "backpack", "watch", "hat", "cap"),
                3
        ));

        allQuests.add(new Quest(
                "QUEST_LIVING",
                "🛋️ Săn Không Gian Phòng Khách & Phòng Ngủ",
                "Tìm và quét 3 đồ gia dụng: Bàn, Ghế sofa, Giường, Đèn, hoặc Đồng hồ.",
                Arrays.asList("table", "sofa", "bed", "pillow", "blanket", "lamp", "clock", "chair", "fan", "window", "door"),
                3
        ));
    }

    private void loadState() {
        currentQuestIndex = prefs.getInt(KEY_CURRENT_QUEST_INDEX, 0);
        journey30DayIndex = prefs.getInt(KEY_30_DAY_INDEX, 1);
        if (currentQuestIndex >= allQuests.size()) {
            currentQuestIndex = 0;
        }

        Set<String> savedTotal = prefs.getStringSet(KEY_DISCOVERED_WORDS, null);
        if (savedTotal != null) {
            totalDiscoveredWords.addAll(savedTotal);
        }

        Set<String> savedQuest = prefs.getStringSet(KEY_QUEST_FOUND_WORDS, null);
        if (savedQuest != null) {
            currentQuestFoundWords.addAll(savedQuest);
        }
    }

    private void saveState() {
        prefs.edit()
                .putInt(KEY_CURRENT_QUEST_INDEX, currentQuestIndex)
                .putInt(KEY_30_DAY_INDEX, journey30DayIndex)
                .putStringSet(KEY_DISCOVERED_WORDS, totalDiscoveredWords)
                .putStringSet(KEY_QUEST_FOUND_WORDS, currentQuestFoundWords)
                .apply();
    }

    public Quest getCurrentQuest() {
        if (allQuests.isEmpty()) return null;
        return allQuests.get(currentQuestIndex % allQuests.size());
    }

    public int getQuestProgressCount() {
        return currentQuestFoundWords.size();
    }

    public boolean isQuestCompleted() {
        Quest q = getCurrentQuest();
        return q != null && currentQuestFoundWords.size() >= q.requiredCount;
    }

    /**
     * Called when a word is detected by the camera.
     * Returns true if this detection just completed the quest!
     */
    public boolean onWordScanned(String wordEn) {
        if (wordEn == null || wordEn.trim().isEmpty()) return false;
        String normalized = wordEn.toLowerCase(Locale.ROOT).trim();

        // 1. Add to permanent Pokédex
        totalDiscoveredWords.add(normalized);

        // 2. Check match with current quest
        Quest q = getCurrentQuest();
        if (q != null && !isQuestCompleted()) {
            for (String target : q.targetKeywords) {
                if (normalized.contains(target) || target.contains(normalized)) {
                    currentQuestFoundWords.add(target);
                    saveState();
                    if (currentQuestFoundWords.size() >= q.requiredCount) {
                        return true; // Quest just completed!
                    }
                    break;
                }
            }
        }

        saveState();
        return false;
    }

    public void advanceToNextQuest() {
        currentQuestFoundWords.clear();
        currentQuestIndex = (currentQuestIndex + 1) % allQuests.size();
        journey30DayIndex = Math.min(30, journey30DayIndex + 1);
        saveState();
    }

    public int getJourneyDay() {
        return journey30DayIndex;
    }

    public String get30DayJourneySummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("🗓️ THỬ THÁCH 30 NGÀY NGÔI NHÀ SONG NGỮ (Ý TƯỞNG 34)\n\n");
        sb.append("Hôm nay: Ngày ").append(journey30DayIndex).append(" / 30\n");
        sb.append("Mục tiêu: Quét và làm chủ các đồ vật theo lộ trình hàng ngày để biến căn nhà thành không gian tắm tiếng Anh tự nhiên.\n\n");
        sb.append("• Ngày 1-5: Bàn làm việc & Thiết bị công nghệ (Laptop, Pen, Book...)\n");
        sb.append("• Ngày 6-10: Nhà bếp & Thức uống dinh dưỡng (Cup, Bottle, Bread...)\n");
        sb.append("• Ngày 11-15: Thời trang & Đồ dùng cá nhân (Shoes, Shirt, Glasses...)\n");
        sb.append("• Ngày 16-20: Phòng khách & Thư giãn (Sofa, Lamp, Clock...)\n");
        sb.append("• Ngày 21-25: Phòng ngủ & Chăm sóc giấc ngủ (Bed, Pillow, Blanket...)\n");
        sb.append("• Ngày 26-30: Thiên nhiên & Thế giới xung quanh (Tree, Flower, Car...)\n\n");
        sb.append("✅ Tổng từ vựng thực tế bạn đã tích lũy: ").append(totalDiscoveredWords.size()).append(" từ.");
        return sb.toString();
    }

    public int getTotalDiscoveredCount() {
        return totalDiscoveredWords.size();
    }

    public Set<String> getTotalDiscoveredWords() {
        return new LinkedHashSet<>(totalDiscoveredWords);
    }

    /**
     * Generates a standard Anki / Quizlet tab-separated Flashcard text file (Ý tưởng 14).
     */
    public String exportAnkiFlashcardsTsv() {
        StringBuilder sb = new StringBuilder();
        sb.append("# Anki Flashcard Export - EnglishPath Visual AR Lens\n");
        sb.append("# Generated on: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date())).append("\n");
        sb.append("# Format: English Word\tIPA\tVietnamese Meaning\tExample Sentence\tCollocation\n\n");

        for (String word : totalDiscoveredWords) {
            VisualWordInfo info = VisualVocabularyDatabase.lookup(word, 1.0f);
            if (info != null) {
                sb.append(info.getWordEn()).append("\t")
                        .append(info.getIpa() != null ? info.getIpa() : "").append("\t")
                        .append(info.getMeaningVi() != null ? info.getMeaningVi() : "").append("\t")
                        .append(info.getExampleSentenceEn() != null ? info.getExampleSentenceEn() : "").append(" (").append(info.getExampleSentenceVi() != null ? info.getExampleSentenceVi() : "").append(")\t")
                        .append(info.getCollocationEn() != null ? info.getCollocationEn() : "").append("\n");
            }
        }

        return sb.toString();
    }
}
