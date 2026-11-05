/**
 * Speech Recognition and Text-to-Speech Engine for Web & iPhone (iOS Safari)
 */
import { StorageManager } from './storage.js';

export class SpeechTutor {
  constructor() {
    this.synth = window.speechSynthesis;
    this.recognition = null;
    this.isListening = false;
    this.availableVoices = [];
    this.initVoices();
  }

  initVoices() {
    if (!this.synth) return;
    const load = () => {
      this.availableVoices = this.synth.getVoices();
    };
    load();
    if (this.synth.onvoiceschanged !== undefined) {
      this.synth.onvoiceschanged = load;
    }
  }

  /**
   * Speak English text with US or UK accent
   */
  speak(text, options = {}) {
    if (!this.synth) {
      console.warn("Speech synthesis not supported on this device.");
      return;
    }
    // Cancel previous utterance
    this.synth.cancel();

    const utterance = new SpeechSynthesisUtterance(text);
    const accent = options.accent || StorageManager.getVoiceAccent(); // 'en-US' or 'en-GB'
    utterance.lang = accent;
    utterance.rate = options.rate || 0.95; // Slightly slower for better educational clarity
    utterance.pitch = options.pitch || 1.0;

    // Pick best matching voice if available
    if (this.availableVoices.length > 0) {
      const match = this.availableVoices.find(v => v.lang.startsWith(accent) || (accent.includes('US') ? v.name.includes('Samantha') || v.name.includes('Google US') : v.name.includes('Daniel') || v.name.includes('UK')));
      if (match) utterance.voice = match;
    }

    if (options.onStart) utterance.onstart = options.onStart;
    if (options.onEnd) utterance.onend = options.onEnd;
    if (options.onError) utterance.onerror = options.onError;

    this.synth.speak(utterance);
  }

  stopSpeaking() {
    if (this.synth) this.synth.cancel();
  }

  /**
   * Check if Speech Recognition is supported
   */
  isRecognitionSupported() {
    return 'webkitSpeechRecognition' in window || 'SpeechRecognition' in window;
  }

  /**
   * Start Speech-to-Text listening
   */
  startListening({ onStart, onResult, onError, onEnd }) {
    if (!this.isRecognitionSupported()) {
      if (onError) onError("Trình duyệt chưa hỗ trợ Web Speech API. Bạn có thể nhập câu trả lời bằng bàn phím.");
      return;
    }

    if (this.isListening) {
      this.stopListening();
    }

    const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
    this.recognition = new SpeechRecognition();
    this.recognition.lang = 'en-US';
    this.recognition.interimResults = true;
    this.recognition.continuous = false;

    this.recognition.onstart = () => {
      this.isListening = true;
      if (onStart) onStart();
    };

    this.recognition.onresult = (event) => {
      let interimTranscript = '';
      let finalTranscript = '';

      for (let i = event.resultIndex; i < event.results.length; ++i) {
        if (event.results[i].isFinal) {
          finalTranscript += event.results[i][0].transcript;
        } else {
          interimTranscript += event.results[i][0].transcript;
        }
      }

      if (onResult) {
        onResult(finalTranscript || interimTranscript, Boolean(finalTranscript));
      }
    };

    this.recognition.onerror = (event) => {
      this.isListening = false;
      let msg = "Lỗi micro: " + event.error;
      if (event.error === 'not-allowed') {
        msg = "Quyền truy cập micro đã bị từ chối trên iPhone. Vui lòng vào Cài đặt Safari để cấp quyền.";
      } else if (event.error === 'no-speech') {
        msg = "Chưa nhận được âm thanh. Hãy thử lại gần micro hơn.";
      }
      if (onError) onError(msg);
    };

    this.recognition.onend = () => {
      this.isListening = false;
      if (onEnd) onEnd();
    };

    try {
      this.recognition.start();
    } catch (e) {
      this.isListening = false;
      if (onError) onError(e.message);
    }
  }

  stopListening() {
    if (this.recognition && this.isListening) {
      this.recognition.stop();
      this.isListening = false;
    }
  }

  /**
   * AI Pronunciation Evaluator (Levenshtein + Phonetic Token Alignment + IELTS Band Scoring)
   */
  evaluatePronunciation(targetText, spokenText, options = {}) {
    if (!targetText) return null;
    const cleanT = (targetText || '').toLowerCase().replace(/[^\w\s]/g, '').trim();
    const cleanS = (spokenText || '').toLowerCase().replace(/[^\w\s]/g, '').trim();

    if (!cleanS) {
      return {
        accuracy: 0,
        band: 'Band 4.0',
        bandScore: 4.0,
        statusText: 'Chưa phát hiện giọng nói',
        color: '#EF4444',
        feedbackVi: 'Hệ thống chưa ghi nhận được âm thanh. Hãy bấm nút micro và nói to, rõ ràng vào mic nhé!',
        wordBreakdown: []
      };
    }

    const tWords = cleanT.split(/\s+/).filter(Boolean);
    const sWords = cleanS.split(/\s+/).filter(Boolean);

    // Compute Levenshtein distance
    const dist = this._computeLevenshtein(cleanT, cleanS);
    const maxLen = Math.max(cleanT.length, cleanS.length, 1);
    let charScore = Math.max(0, Math.round((1 - dist / maxLen) * 100));

    // Word-level matching
    let matchedWordCount = 0;
    const wordBreakdown = tWords.map((tw) => {
      let bestSim = 0;
      for (const sw of sWords) {
        const d = this._computeLevenshtein(tw, sw);
        const m = Math.max(tw.length, sw.length, 1);
        const sim = Math.max(0, 1 - d / m);
        if (sim > bestSim) bestSim = sim;
      }
      if (bestSim >= 0.75) matchedWordCount++;
      return {
        word: tw,
        matchRate: Math.round(bestSim * 100),
        status: bestSim >= 0.85 ? 'perfect' : (bestSim >= 0.6 ? 'close' : 'missed')
      };
    });

    const wordScore = tWords.length > 0 ? Math.round((matchedWordCount / tWords.length) * 100) : 0;
    const finalAccuracy = Math.round(charScore * 0.4 + wordScore * 0.6);

    // Map to IELTS Speaking Band Descriptor (Pronunciation Criterion)
    let band = 'Band 5.0';
    let bandScore = 5.0;
    let color = '#EAB308';
    let statusText = 'Khá - Cần hoàn thiện';
    let feedbackVi = 'Cần luyện thêm ngữ điệu và phát âm rõ âm đuôi (ending sounds).';

    if (finalAccuracy >= 93) {
      band = 'Band 9.0';
      bandScore = 9.0;
      color = '#10B981';
      statusText = 'Hoàn hảo - Native Level';
      feedbackVi = 'Phát âm tuyệt đối chuẩn xác! Ngữ điệu tự nhiên, trọng âm và liên kết âm đạt chuẩn người bản xứ.';
    } else if (finalAccuracy >= 85) {
      band = 'Band 8.5';
      bandScore = 8.5;
      color = '#10B981';
      statusText = 'Xuất sắc - Chuẩn quốc tế';
      feedbackVi = 'Kiểm soát ngữ âm rất tốt! Trọng âm từ rõ ràng, nối âm mượt mà và nhịp điệu tự nhiên.';
    } else if (finalAccuracy >= 78) {
      band = 'Band 8.0';
      bandScore = 8.0;
      color = '#38BDF8';
      statusText = 'Rất Tốt - Trôi chảy';
      feedbackVi = 'Phát âm chính xác, đầy đủ các phụ âm đuôi (ending sounds). Giữ vững phong độ này!';
    } else if (finalAccuracy >= 70) {
      band = 'Band 7.5';
      bandScore = 7.5;
      color = '#38BDF8';
      statusText = 'Tốt - Giao tiếp hiệu quả';
      feedbackVi = 'Người nghe hiểu bạn dễ dàng. Hãy chú ý hơn vào việc ngắt nghỉ và lướt âm để tự nhiên hơn.';
    } else if (finalAccuracy >= 60) {
      band = 'Band 6.5';
      bandScore = 6.5;
      color = '#F59E0B';
      statusText = 'Khá - Cần luyện thêm';
      feedbackVi = 'Bạn đã phát âm đúng cấu trúc từ cơ bản. Cần chú ý các âm khó như /θ/, /ð/, /ʃ/, /tʃ/ và trọng âm.';
    } else if (finalAccuracy >= 45) {
      band = 'Band 5.5';
      bandScore = 5.5;
      color = '#F97316';
      statusText = 'Trung bình - Cần cải thiện';
      feedbackVi = 'Hãy nghe mẫu kỹ trước khi nói, chú ý phát âm chậm và rõ từng âm tiết theo bảng phiên âm IPA.';
    } else {
      band = 'Band 4.5';
      bandScore = 4.5;
      color = '#EF4444';
      statusText = 'Mới bắt đầu - Luyện tập lại';
      feedbackVi = 'Đừng nản lòng! Hãy bấm nút loa 🔊 để nghe phát âm mẫu 2-3 lần rồi bấm micro lặp lại nhé.';
    }

    return {
      accuracy: finalAccuracy,
      band,
      bandScore,
      statusText,
      color,
      feedbackVi,
      targetText,
      spokenText,
      wordBreakdown,
      syllableTip: options.syllableStress ? `🎯 Nhấn trọng âm: ${options.syllableStress}` : null
    };
  }

  _computeLevenshtein(a, b) {
    const matrix = [];
    for (let i = 0; i <= b.length; i++) matrix[i] = [i];
    for (let j = 0; j <= a.length; j++) matrix[0][j] = j;

    for (let i = 1; i <= b.length; i++) {
      for (let j = 1; j <= a.length; j++) {
        if (b.charAt(i - 1) === a.charAt(j - 1)) {
          matrix[i][j] = matrix[i - 1][j - 1];
        } else {
          matrix[i][j] = Math.min(
            matrix[i - 1][j - 1] + 1,
            matrix[i][j - 1] + 1,
            matrix[i - 1][j] + 1
          );
        }
      }
    }
    return matrix[b.length][a.length];
  }
}
