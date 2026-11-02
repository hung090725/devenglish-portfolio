/**
 * Storage & Offline State Manager for DevEnglish PWA
 * Supports localStorage and IndexedDB fallback.
 */

const STORAGE_KEYS = {
  VOICE_ACCENT: 'devenglish_voice_accent', // 'en-US' or 'en-GB'
  SCANNED_HISTORY: 'devenglish_scanned_history',
  LEARNED_WORDS: 'devenglish_learned_words',
  QUIZ_SCORES: 'devenglish_quiz_scores',
  SAVED_BOOKMARKS: 'devenglish_saved_bookmarks',
  INTERVIEW_SCORES: 'devenglish_interview_scores'
};

export class StorageManager {
  static getVoiceAccent() {
    return localStorage.getItem(STORAGE_KEYS.VOICE_ACCENT) || 'en-US';
  }

  static setVoiceAccent(accent) {
    localStorage.setItem(STORAGE_KEYS.VOICE_ACCENT, accent);
  }

  static getScannedHistory() {
    try {
      const data = localStorage.getItem(STORAGE_KEYS.SCANNED_HISTORY);
      return data ? JSON.parse(data) : [];
    } catch (e) {
      return [];
    }
  }

  static addScannedWord(wordKey) {
    const list = this.getScannedHistory();
    const now = new Date().toISOString();
    const existingIndex = list.findIndex(item => item.key === wordKey);
    if (existingIndex >= 0) {
      list[existingIndex].count += 1;
      list[existingIndex].lastSeen = now;
    } else {
      list.unshift({ key: wordKey, count: 1, firstSeen: now, lastSeen: now });
    }
    // keep top 50
    if (list.length > 50) list.pop();
    localStorage.setItem(STORAGE_KEYS.SCANNED_HISTORY, JSON.stringify(list));
  }

  static getLearnedWords() {
    try {
      const data = localStorage.getItem(STORAGE_KEYS.LEARNED_WORDS);
      return data ? JSON.parse(data) : [];
    } catch (e) {
      return [];
    }
  }

  static markWordLearned(wordKey) {
    const list = this.getLearnedWords();
    if (!list.includes(wordKey)) {
      list.push(wordKey);
      localStorage.setItem(STORAGE_KEYS.LEARNED_WORDS, JSON.stringify(list));
    }
  }

  static getBookmarks() {
    try {
      const data = localStorage.getItem(STORAGE_KEYS.SAVED_BOOKMARKS);
      return data ? JSON.parse(data) : [];
    } catch (e) {
      return [];
    }
  }

  static toggleBookmark(wordKey) {
    const list = this.getBookmarks();
    const idx = list.indexOf(wordKey);
    if (idx >= 0) {
      list.splice(idx, 1);
    } else {
      list.push(wordKey);
    }
    localStorage.setItem(STORAGE_KEYS.SAVED_BOOKMARKS, JSON.stringify(list));
    return idx < 0; // returns true if now bookmarked
  }

  static isBookmarked(wordKey) {
    return this.getBookmarks().includes(wordKey);
  }

  static saveQuizScore(quizId, score) {
    try {
      const scores = JSON.parse(localStorage.getItem(STORAGE_KEYS.QUIZ_SCORES) || '{}');
      scores[quizId] = { score, date: new Date().toISOString() };
      localStorage.setItem(STORAGE_KEYS.QUIZ_SCORES, JSON.stringify(scores));
    } catch (e) {
      console.error(e);
    }
  }

  static getStats() {
    const scanned = this.getScannedHistory().length;
    const learned = this.getLearnedWords().length;
    const bookmarks = this.getBookmarks().length;
    return { scanned, learned, bookmarks };
  }
}
