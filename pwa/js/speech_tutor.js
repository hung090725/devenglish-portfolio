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
}
