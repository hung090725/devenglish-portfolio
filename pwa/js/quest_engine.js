/**
 * Scavenger Hunt Quests & Macro Scene Recognition Engine
 * Real-world interactive challenges that gamify vocabulary discovery
 */
import { SoundFxEngine } from './sound_fx.js';

export const SCAVENGER_QUESTS = [
  {
    id: 'quest_tech',
    title: 'Thợ Săn Công Nghệ (Tech Hunter)',
    desc: 'Lia camera tìm 1 thiết bị điện tử hoặc phụ kiện công nghệ',
    targetCategory: ['laptop', 'computer', 'cell phone', 'keyboard', 'mouse', 'tv', 'headphones'],
    points: 100,
    badge: '💻 Tech Savvy'
  },
  {
    id: 'quest_stationery',
    title: 'Góc Học Tập (Study & Stationery)',
    desc: 'Lia camera tìm 1 cuốn sách, cây bút hoặc tài liệu học tập',
    targetCategory: ['book', 'pen', 'document', 'backpack', 'notebook'],
    points: 100,
    badge: '📚 Scholar'
  },
  {
    id: 'quest_hydration',
    title: 'Tiếp Năng Lượng (Hydration Station)',
    desc: 'Lia camera tìm 1 cốc nước, tách cà phê hoặc bình giữ nhiệt',
    targetCategory: ['cup', 'bottle'],
    points: 80,
    badge: '☕ Well Hydrated'
  },
  {
    id: 'quest_interior',
    title: 'Khám Phá Không Gian (Interior Explorer)',
    desc: 'Lia camera tìm 1 chiếc ghế, bàn làm việc hoặc cây xanh',
    targetCategory: ['chair', 'dining table', 'plant', 'couch', 'lamp'],
    points: 90,
    badge: '🪴 Biophilic'
  }
];

export class QuestEngine {
  static STORAGE_KEY = 'devenglish_scavenger_quest';

  static getCurrentQuest() {
    try {
      const raw = localStorage.getItem(this.STORAGE_KEY);
      if (raw) {
        return JSON.parse(raw);
      }
    } catch (e) {}

    const initial = {
      questIndex: 0,
      completedToday: 0,
      streak: 1,
      exp: 150,
      lastCompletedTime: null
    };
    this.saveQuestState(initial);
    return initial;
  }

  static saveQuestState(state) {
    try {
      localStorage.setItem(this.STORAGE_KEY, JSON.stringify(state));
    } catch (e) {}
  }

  static getActiveQuestDetails() {
    const state = this.getCurrentQuest();
    const quest = SCAVENGER_QUESTS[state.questIndex % SCAVENGER_QUESTS.length];
    return {
      ...quest,
      streak: state.streak,
      exp: state.exp,
      completedToday: state.completedToday
    };
  }

  /**
   * Evaluates if a scanned word key fulfills the active quest
   */
  static checkQuestMatch(wordKey) {
    if (!wordKey) return false;
    const cleanKey = String(wordKey).toLowerCase().trim();
    const state = this.getCurrentQuest();
    const quest = SCAVENGER_QUESTS[state.questIndex % SCAVENGER_QUESTS.length];

    const isMatch = quest.targetCategory.some(target => 
      cleanKey.includes(target) || target.includes(cleanKey)
    );

    if (isMatch) {
      state.questIndex = (state.questIndex + 1) % SCAVENGER_QUESTS.length;
      state.completedToday += 1;
      state.exp += quest.points;
      state.lastCompletedTime = Date.now();
      this.saveQuestState(state);

      SoundFxEngine.playQuestVictory();

      return {
        success: true,
        questTitle: quest.title,
        pointsAwarded: quest.points,
        newExp: state.exp,
        nextQuest: SCAVENGER_QUESTS[state.questIndex]
      };
    }
    return { success: false };
  }

  /**
   * Hierarchical Macro Scene Detection based on active detected objects
   */
  static detectMacroScene(detectedLabels = []) {
    if (!detectedLabels || detectedLabels.length === 0) {
      return { sceneEn: 'Everyday Environment', sceneVi: 'Môi trường xung quanh', icon: '🌐' };
    }

    const lower = detectedLabels.map(l => String(l).toLowerCase()).join(' ');

    if (/laptop|keyboard|mouse|monitor|tv|computer|phone/.test(lower)) {
      return { sceneEn: 'Workstation / Office', sceneVi: 'Góc làm việc / Bàn máy tính', icon: '💻' };
    }
    if (/book|pen|document|backpack/.test(lower)) {
      return { sceneEn: 'Study / Library', sceneVi: 'Góc học tập / Đọc sách', icon: '📚' };
    }
    if (/cup|bottle|dining table|microwave|refrigerator/.test(lower)) {
      return { sceneEn: 'Kitchen / Dining Space', sceneVi: 'Khu vực ăn uống / Nhà bếp', icon: '☕' };
    }
    if (/plant|flower|tree/.test(lower)) {
      return { sceneEn: 'Green Oasis / Biophilic Space', sceneVi: 'Không gian xanh / Cây cảnh', icon: '🪴' };
    }
    if (/chair|couch|lamp/.test(lower)) {
      return { sceneEn: 'Living Space / Lounge', sceneVi: 'Phòng sinh hoạt chung', icon: '🛋️' };
    }

    return { sceneEn: 'Indoor Workspace', sceneVi: 'Không gian sinh hoạt', icon: '🏢' };
  }
}
