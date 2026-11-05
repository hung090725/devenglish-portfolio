/**
 * Sci-Fi Cyberpunk Sound & Haptic FX Engine
 * Uses 100% native Web Audio API oscillators to generate futuristic sound effects
 * Zero external audio files or network bandwidth required!
 */

export class SoundFxEngine {
  static audioCtx = null;
  static soundEnabled = true;

  static getAudioContext() {
    if (!this.audioCtx) {
      const AudioCtxClass = window.AudioContext || window.webkitAudioContext;
      if (AudioCtxClass) {
        this.audioCtx = new AudioCtxClass();
      }
    }
    if (this.audioCtx && this.audioCtx.state === 'suspended') {
      this.audioCtx.resume();
    }
    return this.audioCtx;
  }

  /**
   * Futuristic Cyberpunk Target Acquisition Lock Sound
   */
  static playTargetLock() {
    if (!this.soundEnabled) return;
    try {
      const ctx = this.getAudioContext();
      if (!ctx) return;

      const osc = ctx.createOscillator();
      const gain = ctx.createGain();

      osc.type = 'sine';
      const now = ctx.currentTime;
      osc.frequency.setValueAtTime(880, now); // A5
      osc.frequency.exponentialRampToValueAtTime(1760, now + 0.08); // A6
      osc.frequency.setValueAtTime(1320, now + 0.09); // E6

      gain.gain.setValueAtTime(0.08, now);
      gain.gain.exponentialRampToValueAtTime(0.001, now + 0.22);

      osc.connect(gain);
      gain.connect(ctx.destination);

      osc.start(now);
      osc.stop(now + 0.22);

      this.triggerHaptic([12, 25, 15]);
    } catch (e) {
      // Audio context might be restricted before user gesture
    }
  }

  /**
   * Quest Complete Victory Chime
   */
  static playQuestVictory() {
    if (!this.soundEnabled) return;
    try {
      const ctx = this.getAudioContext();
      if (!ctx) return;

      const now = ctx.currentTime;
      const notes = [523.25, 659.25, 783.99, 1046.50]; // C5, E5, G5, C6 (Major Arpeggio)

      notes.forEach((freq, i) => {
        const osc = ctx.createOscillator();
        const gain = ctx.createGain();

        osc.type = 'triangle';
        osc.frequency.setValueAtTime(freq, now + i * 0.08);

        gain.gain.setValueAtTime(0.12, now + i * 0.08);
        gain.gain.exponentialRampToValueAtTime(0.001, now + i * 0.08 + 0.35);

        osc.connect(gain);
        gain.connect(ctx.destination);

        osc.start(now + i * 0.08);
        osc.stop(now + i * 0.08 + 0.35);
      });

      this.triggerHaptic([30, 40, 30, 40, 60]);
    } catch (e) {}
  }

  /**
   * Haptic Vibration Feedback (Supported on Android and iOS Safari PWA)
   */
  static triggerHaptic(pattern = 15) {
    try {
      if (typeof navigator !== 'undefined' && typeof navigator.vibrate === 'function') {
        // Only trigger if user has interacted with the document (avoids browser console restriction warnings)
        if (!navigator.userActivation || navigator.userActivation.hasBeenActive) {
          navigator.vibrate(pattern);
        }
      }
    } catch (e) {}
  }
}
