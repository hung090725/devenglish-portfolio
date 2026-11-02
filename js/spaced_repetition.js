/**
 * SuperMemo-2 (SM-2) Spaced Repetition Engine
 * Scientific Implementation of the Ebbinghaus Forgetting Curve Mitigation Algorithm
 *
 * Mathematical Formulation:
 * EF' = max(1.3, EF + (0.1 - (5 - q) * (0.08 + (5 - q) * 0.02)))
 *
 * Interval Schedule:
 * - If q < 3 (Recall Failure): Repetitions = 0, Interval I(1) = 1 day
 * - If q >= 3 (Recall Success):
 *     n = 1 -> I(1) = 1 day
 *     n = 2 -> I(2) = 6 days
 *     n > 2 -> I(n) = round(I(n-1) * EF')
 */

export class SM2Engine {
  static STORAGE_KEY = 'devenglish_sm2_cards';

  /**
   * Initializes or retrieves the SM-2 record for a given vocabulary word
   * @param {string} wordKey 
   * @returns {Object} SM-2 card state
   */
  static getCard(wordKey) {
    const allCards = this.getAllCards();
    if (allCards[wordKey]) {
      return allCards[wordKey];
    }
    // Default initial SM-2 card state
    const newCard = {
      wordKey: wordKey,
      repetitions: 0,
      easeFactor: 2.5,
      intervalDays: 0,
      lastReviewed: null,
      nextReviewDate: Date.now(), // Due immediately
      history: []
    };
    allCards[wordKey] = newCard;
    this.saveAllCards(allCards);
    return newCard;
  }

  /**
   * Process a review response quality q in range [0, 5]
   * @param {string} wordKey 
   * @param {number} q - Quality score (0: Blackout, 1: Wrong, 2: Difficult, 3: Pass, 4: Good, 5: Perfect)
   * @returns {Object} Updated card state and calculation details
   */
  static processReview(wordKey, q) {
    // Clamp q between 0 and 5
    q = Math.max(0, Math.min(5, Math.round(q)));
    const card = this.getCard(wordKey);

    let repetitions = card.repetitions;
    let easeFactor = card.easeFactor;
    let intervalDays = card.intervalDays;

    if (q >= 3) {
      // Successful recall
      if (repetitions === 0) {
        intervalDays = 1;
      } else if (repetitions === 1) {
        intervalDays = 6;
      } else {
        intervalDays = Math.round(intervalDays * easeFactor);
      }
      repetitions += 1;
    } else {
      // Recall failure -> Reset to initial interval
      repetitions = 0;
      intervalDays = 1;
    }

    // Ease Factor calculation: EF' = EF + (0.1 - (5 - q) * (0.08 + (5 - q) * 0.02))
    const deltaEF = 0.1 - (5 - q) * (0.08 + (5 - q) * 0.02);
    easeFactor = easeFactor + deltaEF;

    // Ease Hell protection: clamp lower bound to 1.3
    if (easeFactor < 1.3) {
      easeFactor = 1.3;
    }

    const now = Date.now();
    const nextReviewTimestamp = now + intervalDays * 24 * 60 * 60 * 1000;

    card.repetitions = repetitions;
    card.easeFactor = Number(easeFactor.toFixed(3));
    card.intervalDays = intervalDays;
    card.lastReviewed = now;
    card.nextReviewDate = nextReviewTimestamp;

    card.history.push({
      date: now,
      score: q,
      interval: intervalDays,
      ef: card.easeFactor
    });

    const allCards = this.getAllCards();
    allCards[wordKey] = card;
    this.saveAllCards(allCards);

    return {
      wordKey,
      qualityScore: q,
      repetitions,
      easeFactor: card.easeFactor,
      intervalDays,
      nextReviewDate: new Date(nextReviewTimestamp).toLocaleDateString('vi-VN')
    };
  }

  /**
   * Get all cards due for review today
   */
  static getDueCards() {
    const allCards = this.getAllCards();
    const now = Date.now();
    const dueKeys = [];

    for (const [key, card] of Object.entries(allCards)) {
      if (card.nextReviewDate <= now) {
        dueKeys.push(key);
      }
    }
    return dueKeys;
  }

  /**
   * Internal storage helpers
   */
  static getAllCards() {
    try {
      const raw = localStorage.getItem(this.STORAGE_KEY);
      return raw ? JSON.parse(raw) : {};
    } catch (e) {
      console.warn("SM2Engine load error:", e);
      return {};
    }
  }

  static saveAllCards(cards) {
    try {
      localStorage.setItem(this.STORAGE_KEY, JSON.stringify(cards));
    } catch (e) {
      console.warn("SM2Engine save error:", e);
    }
  }
}
