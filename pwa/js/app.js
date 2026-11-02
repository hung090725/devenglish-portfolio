/**
 * DevEnglish AI PWA Main Application Controller
 * Optimized for iPhone (iOS Safari) & Modern Mobile Browsers
 */
import { ArCameraLens } from './ar_camera.js';
import { SpeechTutor } from './speech_tutor.js';
import { ItEnglishEngine } from './it_english_engine.js';
import { StorageManager } from './storage.js';
import { VISUAL_VOCABULARY_LIST, findVisualWord } from '../data/visual_vocab_db.js';
import { CODE_SNIPPETS, TECH_ARTICLES, INTERVIEW_QUESTIONS } from '../data/it_english_db.js';

/**
 * High-performance mobile touch helper for iOS Safari & Android
 * Eliminates 300ms click delay, avoids ghost clicks, and ensures instant response.
 */
function attachFastClick(element, handler) {
  if (!element) return;
  let touchMoved = false;

  element.addEventListener('touchstart', () => {
    touchMoved = false;
  }, { passive: true });

  element.addEventListener('touchmove', () => {
    touchMoved = true;
  }, { passive: true });

  element.addEventListener('touchend', (e) => {
    if (!touchMoved) {
      if (e.cancelable) e.preventDefault();
      handler(e);
    }
  }, { passive: false });

  element.addEventListener('click', (e) => {
    handler(e);
  });
}

class AppController {
  constructor() {
    this.speech = new SpeechTutor();
    this.camera = null;
    this.currentSnippetIndex = 0;
    this.currentArticleIndex = 0;
    this.currentQuestionIndex = 0;
    this.activeTab = 'tab-camera';
    this.activeItSubtab = 'subtab-code';

    this.initElements();
    this.initNavigation();
    this.initCamera();
    this.initItEnglishLab();
    this.initKnowledgeHub();
    this.initSettings();
    this.initBottomSheet();
    this.initInstallBanner();
    this.updateStatsUI();
  }

  initElements() {
    this.videoEl = document.getElementById('camera-video');
    this.canvasEl = document.getElementById('camera-canvas');
    this.cardsScroll = document.getElementById('detected-cards-scroll');
    this.sheetOverlay = document.getElementById('sheet-overlay');
    this.bottomSheet = document.getElementById('bottom-sheet');
  }

  /* ===================================================
   * 1. NAVIGATION & TABS
   * =================================================== */
  initNavigation() {
    const navItems = document.querySelectorAll('.tab-nav-item');
    navItems.forEach(item => {
      attachFastClick(item, (e) => {
        if (e.preventDefault) e.preventDefault();
        const tabId = item.dataset.tab;
        this.switchTab(tabId);
      });
    });
  }

  switchTab(tabId) {
    if (this.activeTab === tabId) return;

    document.querySelectorAll('.tab-nav-item').forEach(el => el.classList.remove('active'));
    document.querySelectorAll('.tab-pane').forEach(el => el.classList.remove('active'));

    const targetNav = document.querySelector(`.tab-nav-item[data-tab="${tabId}"]`);
    const targetPane = document.getElementById(tabId);

    if (targetNav) targetNav.classList.add('active');
    if (targetPane) targetPane.classList.add('active');

    this.activeTab = tabId;

    // Camera power saving on iPhone
    if (tabId === 'tab-camera') {
      if (this.camera) this.camera.startCamera();
    } else {
      if (this.camera) this.camera.stopCamera();
    }

    if (tabId === 'tab-settings') {
      this.updateStatsUI();
    }
  }

  /* ===================================================
   * 2. AR CAMERA REAL-TIME LENS
   * =================================================== */
  initCamera() {
    this.camera = new ArCameraLens({
      videoElement: this.videoEl,
      canvasElement: this.canvasEl,
      onObjectDetected: (vocabList) => this.renderDetectedPills(vocabList),
      onObjectSelected: (vocab) => this.openDeepKnowledgeSheet(vocab),
      onError: (msg) => {
        const hudBadge = document.getElementById('camera-hud-status');
        if (hudBadge) hudBadge.textContent = '⚠️ ' + msg;
      },
      onCameraReady: () => {
        const hudBadge = document.getElementById('camera-hud-status');
        if (hudBadge) hudBadge.innerHTML = '<span class="hud-dot"></span> Live AR Lens';
      }
    });

    // Camera action buttons with instant zero-latency touch
    const btnFlip = document.getElementById('btn-flip-cam');
    attachFastClick(btnFlip, () => {
      this.camera.toggleCamera();
    });

    const btnTorch = document.getElementById('btn-torch');
    attachFastClick(btnTorch, async () => {
      const state = await this.camera.toggleTorch();
      if (btnTorch) btnTorch.style.color = state ? '#F59E0B' : '#FFFFFF';
    });

    const btnFreeze = document.getElementById('btn-freeze');
    attachFastClick(btnFreeze, () => {
      const isFrozen = this.camera.toggleFreeze();
      if (btnFreeze) btnFreeze.style.color = isFrozen ? '#38BDF8' : '#FFFFFF';
    });

    const btnScan = document.getElementById('btn-capture-scan');
    attachFastClick(btnScan, () => {
      this.camera.performCenterFocusScan();
    });

    // Start camera if on camera tab
    if (this.activeTab === 'tab-camera') {
      this.camera.startCamera();
    }
  }

  renderDetectedPills(vocabList) {
    if (!this.cardsScroll) return;
    this.cardsScroll.innerHTML = '';
    vocabList.forEach(vocab => {
      const pill = document.createElement('div');
      pill.className = 'object-pill-card';
      const level = vocab.cefrLevel || 'B2-C1';
      const sentence = vocab.exampleSentenceEn || vocab.c1c2ExpressionEn || '';
      pill.innerHTML = `
        <div class="object-pill-info">
          <h4>${vocab.wordEn} <span class="object-pill-level">${level}</span></h4>
          <p>${vocab.ipa} • ${vocab.meaningVi}</p>
          <div class="object-pill-sentence">"${sentence}"</div>
        </div>
        <button class="object-pill-audio" title="Nghe phát âm">🔊</button>
      `;

      const audioBtn = pill.querySelector('.object-pill-audio');
      attachFastClick(audioBtn, (e) => {
        if (e.stopPropagation) e.stopPropagation();
        this.speech.speak(vocab.wordEn);
      });

      attachFastClick(pill, () => {
        this.openDeepKnowledgeSheet(vocab);
      });

      this.cardsScroll.appendChild(pill);
    });
  }

  /* ===================================================
   * 3. DEVENGLISH AI CORE SUITE
   * =================================================== */
  initItEnglishLab() {
    // Subtab switching
    const subtabBtns = document.querySelectorAll('.segment-btn');
    subtabBtns.forEach(btn => {
      btn.addEventListener('click', () => {
        subtabBtns.forEach(b => b.classList.remove('active'));
        document.querySelectorAll('.it-subtab-pane').forEach(p => p.classList.remove('active'));

        btn.classList.add('active');
        const targetId = btn.dataset.subtab;
        const targetPane = document.getElementById(targetId);
        if (targetPane) targetPane.classList.add('active');
        this.activeItSubtab = targetId;
      });
    });

    this.renderCodeSnippet();
    this.initCodeExplanationActions();

    this.renderTechArticle();
    this.initTechDocActions();

    this.renderInterviewQuestion();
    this.initInterviewActions();

    this.initPracticeTools();
  }

  /* --- Code-to-English Lab --- */
  renderCodeSnippet() {
    const snippet = CODE_SNIPPETS[this.currentSnippetIndex];
    if (!snippet) return;

    document.getElementById('code-title').textContent = snippet.title;
    document.getElementById('code-lang').textContent = snippet.language;
    document.getElementById('code-level').textContent = snippet.level;
    document.getElementById('code-block').textContent = snippet.code;
    document.getElementById('code-fn-vi').textContent = snippet.technicalFunctionVi;
    document.getElementById('code-req-terms').textContent = snippet.requiredKeyTerms.join(', ');

    // Reset input & result
    document.getElementById('code-explanation-input').value = '';
    document.getElementById('code-result-card').classList.remove('active');
  }

  initCodeExplanationActions() {
    document.getElementById('btn-prev-code')?.addEventListener('click', () => {
      this.currentSnippetIndex = (this.currentSnippetIndex - 1 + CODE_SNIPPETS.length) % CODE_SNIPPETS.length;
      this.renderCodeSnippet();
    });

    document.getElementById('btn-next-code')?.addEventListener('click', () => {
      this.currentSnippetIndex = (this.currentSnippetIndex + 1) % CODE_SNIPPETS.length;
      this.renderCodeSnippet();
    });

    // Speech-to-text mic
    const micBtn = document.getElementById('btn-code-mic');
    const inputEl = document.getElementById('code-explanation-input');
    micBtn?.addEventListener('click', () => {
      if (this.speech.isListening) {
        this.speech.stopListening();
        micBtn.classList.remove('recording');
      } else {
        this.speech.startListening({
          onStart: () => micBtn.classList.add('recording'),
          onResult: (text) => { inputEl.value = text; },
          onError: (err) => {
            alert(err);
            micBtn.classList.remove('recording');
          },
          onEnd: () => micBtn.classList.remove('recording')
        });
      }
    });

    // Evaluate
    document.getElementById('btn-eval-code')?.addEventListener('click', () => {
      const snippet = CODE_SNIPPETS[this.currentSnippetIndex];
      const text = inputEl.value;
      const res = ItEnglishEngine.evaluateCodeExplanation(snippet, text);

      document.getElementById('code-tech-score').textContent = res.technicalScore + '/100';
      document.getElementById('code-eng-score').textContent = res.englishScore + '/100';
      document.getElementById('code-tech-feedback').textContent = res.technicalFeedback;
      document.getElementById('code-eng-feedback').textContent = res.englishFeedback;
      document.getElementById('code-native-sample').textContent = res.nativePhrasingSample;

      document.getElementById('code-result-card').classList.add('active');
    });

    // Audio sample play
    document.getElementById('btn-listen-native')?.addEventListener('click', () => {
      const sample = document.getElementById('code-native-sample').textContent;
      if (sample) this.speech.speak(sample);
    });
  }

  /* --- Technical Documentation Reader --- */
  renderTechArticle() {
    const article = TECH_ARTICLES[this.currentArticleIndex];
    if (!article) return;

    document.getElementById('doc-title').textContent = article.title;
    document.getElementById('doc-domain').textContent = article.domain;
    document.getElementById('doc-level').textContent = article.cefrLevel;
    document.getElementById('doc-original-text').textContent = article.originalText;
    document.getElementById('doc-plain-text').textContent = article.simplifiedPlainEnglish;

    // Vocabularies
    const vocabContainer = document.getElementById('doc-vocab-list');
    vocabContainer.innerHTML = '';
    article.vocabularies.forEach(v => {
      const item = document.createElement('div');
      item.className = 'sheet-section';
      item.innerHTML = `
        <div style="display:flex; justify-content:space-between; align-items:center;">
          <div>
            <strong style="color:#FFF; font-size:14px;">${v.term}</strong>
            <span style="color:#38BDF8; font-family:monospace; margin-left:6px;">${v.ipa}</span>
          </div>
          <button class="header-btn" style="padding:4px 8px;">🔊</button>
        </div>
        <p style="font-size:12px; color:#E2E8F0; margin-top:4px;">🇻🇳 ${v.definitionVi}</p>
        <p style="font-size:11px; color:#94A3B8; margin-top:2px;">📖 "${v.example}"</p>
      `;
      item.querySelector('button').addEventListener('click', () => this.speech.speak(v.term));
      vocabContainer.appendChild(item);
    });

    // Quizzes
    const quizContainer = document.getElementById('doc-quiz-list');
    quizContainer.innerHTML = '';
    article.quizzes.forEach((q, idx) => {
      const qCard = document.createElement('div');
      qCard.className = 'sheet-section';
      let optHtml = '';
      q.options.forEach((opt, optIdx) => {
        optHtml += `<button class="action-btn-primary opt-btn" data-q="${idx}" data-opt="${optIdx}" style="background:rgba(255,255,255,0.06); text-align:left; font-size:13px; font-weight:500; margin-top:6px;">${opt}</button>`;
      });
      qCard.innerHTML = `
        <strong style="color:#FFF; font-size:13px;">Câu ${idx + 1}: ${q.question}</strong>
        <div style="margin-top:6px;">${optHtml}</div>
        <div class="quiz-feedback" id="quiz-feedback-${idx}" style="display:none; font-size:12px; margin-top:8px; padding:6px; border-radius:6px;"></div>
      `;

      qCard.querySelectorAll('.opt-btn').forEach(btn => {
        btn.addEventListener('click', () => {
          const optIndex = parseInt(btn.dataset.opt);
          const feedback = qCard.querySelector(`#quiz-feedback-${idx}`);
          feedback.style.display = 'block';
          if (optIndex === q.correctIndex) {
            feedback.style.background = 'rgba(16, 185, 129, 0.2)';
            feedback.style.color = '#10B981';
            feedback.textContent = '✅ Chính xác! ' + q.explanationVi;
          } else {
            feedback.style.background = 'rgba(244, 63, 94, 0.2)';
            feedback.style.color = '#F43F5E';
            feedback.textContent = '❌ Chưa chính xác. ' + q.explanationVi;
          }
        });
      });

      quizContainer.appendChild(qCard);
    });
  }

  initTechDocActions() {
    document.getElementById('btn-prev-doc')?.addEventListener('click', () => {
      this.currentArticleIndex = (this.currentArticleIndex - 1 + TECH_ARTICLES.length) % TECH_ARTICLES.length;
      this.renderTechArticle();
    });

    document.getElementById('btn-next-doc')?.addEventListener('click', () => {
      this.currentArticleIndex = (this.currentArticleIndex + 1) % TECH_ARTICLES.length;
      this.renderTechArticle();
    });

    // Listen audio
    document.getElementById('btn-listen-doc')?.addEventListener('click', () => {
      const art = TECH_ARTICLES[this.currentArticleIndex];
      if (art) this.speech.speak(art.originalText);
    });
  }

  /* --- IT English Interview Simulator --- */
  renderInterviewQuestion() {
    const q = INTERVIEW_QUESTIONS[this.currentQuestionIndex];
    if (!q) return;

    document.getElementById('interview-round').textContent = q.round;
    document.getElementById('interview-role').textContent = q.role;
    document.getElementById('interview-question-en').textContent = q.questionEn;
    document.getElementById('interview-question-vi').textContent = q.questionVi;
    document.getElementById('interview-tips').textContent = q.tipsVi;

    document.getElementById('interview-answer-input').value = '';
    document.getElementById('interview-result-card').classList.remove('active');
  }

  initInterviewActions() {
    document.getElementById('btn-prev-question')?.addEventListener('click', () => {
      this.currentQuestionIndex = (this.currentQuestionIndex - 1 + INTERVIEW_QUESTIONS.length) % INTERVIEW_QUESTIONS.length;
      this.renderInterviewQuestion();
    });

    document.getElementById('btn-next-question')?.addEventListener('click', () => {
      this.currentQuestionIndex = (this.currentQuestionIndex + 1) % INTERVIEW_QUESTIONS.length;
      this.renderInterviewQuestion();
    });

    // AI reads question
    document.getElementById('btn-read-question')?.addEventListener('click', () => {
      const q = INTERVIEW_QUESTIONS[this.currentQuestionIndex];
      if (q) this.speech.speak(q.questionEn);
    });

    // Mic recording
    const micBtn = document.getElementById('btn-interview-mic');
    const inputEl = document.getElementById('interview-answer-input');
    micBtn?.addEventListener('click', () => {
      if (this.speech.isListening) {
        this.speech.stopListening();
        micBtn.classList.remove('recording');
      } else {
        this.speech.startListening({
          onStart: () => micBtn.classList.add('recording'),
          onResult: (text) => { inputEl.value = text; },
          onError: (err) => {
            alert(err);
            micBtn.classList.remove('recording');
          },
          onEnd: () => micBtn.classList.remove('recording')
        });
      }
    });

    // Evaluate
    document.getElementById('btn-eval-interview')?.addEventListener('click', () => {
      const q = INTERVIEW_QUESTIONS[this.currentQuestionIndex];
      const answer = inputEl.value;
      const res = ItEnglishEngine.evaluateInterviewResponse(q, answer);

      document.getElementById('interview-overall-score').textContent = res.overallScore + '/100';
      document.getElementById('interview-tech-score').textContent = res.technicalScore;
      document.getElementById('interview-vocab-score').textContent = res.vocabScore;
      document.getElementById('interview-fluency-score').textContent = res.fluencyScore;
      document.getElementById('interview-feedback').textContent = res.feedbackVi;
      document.getElementById('interview-sample-answer').textContent = res.sampleAnswerEn;

      document.getElementById('interview-result-card').classList.add('active');
    });

    // Audio sample play
    document.getElementById('btn-listen-sample-answer')?.addEventListener('click', () => {
      const sample = document.getElementById('interview-sample-answer').textContent;
      if (sample) this.speech.speak(sample);
    });
  }

  /* --- Workplace Practice Tools --- */
  initPracticeTools() {
    // Bug Report
    document.getElementById('btn-eval-bug')?.addEventListener('click', () => {
      const title = document.getElementById('bug-title-input').value;
      const steps = document.getElementById('bug-steps-input').value;
      const expected = document.getElementById('bug-expected-input').value;
      const actual = document.getElementById('bug-actual-input').value;
      const env = document.getElementById('bug-env-input').value;

      const res = ItEnglishEngine.evaluateBugReport(title, steps, expected, actual, env);
      const out = document.getElementById('bug-result');
      out.style.display = 'block';
      out.innerHTML = `
        <div style="font-size:16px; font-weight:700; color:${res.isPass ? '#10B981' : '#F59E0B'}">
          ${res.isPass ? '✅ Bug Report Đạt Tiêu Chuẩn' : '⚠️ Cần Bổ Sung Chi Tiết'} (Điểm: ${res.score}/100)
        </div>
        ${res.suggestions.length ? '<ul style="margin-top:6px; padding-left:18px; font-size:12px; color:#E2E8F0;">' + res.suggestions.map(s => `<li>${s}</li>`).join('') + '</ul>' : ''}
      `;
    });

    // Conventional Commit
    document.getElementById('btn-eval-commit')?.addEventListener('click', () => {
      const msg = document.getElementById('commit-input').value;
      const res = ItEnglishEngine.evaluateGitCommit(msg);
      const out = document.getElementById('commit-result');
      out.style.display = 'block';
      out.innerHTML = `
        <div style="font-size:15px; font-weight:700; color:${res.isStandard ? '#10B981' : '#F59E0B'}">
          ${res.isStandard ? '✅ Commit Message Đạt Chuẩn' : '⚠️ Gợi Ý Tối Ưu'} (Điểm: ${res.score}/100)
        </div>
        ${res.tips.length ? '<ul style="margin-top:6px; padding-left:18px; font-size:12px; color:#E2E8F0;">' + res.tips.map(t => `<li>${t}</li>`).join('') + '</ul>' : ''}
      `;
    });

    // Daily Standup
    document.getElementById('btn-eval-standup')?.addEventListener('click', () => {
      const y = document.getElementById('standup-yesterday').value;
      const t = document.getElementById('standup-today').value;
      const b = document.getElementById('standup-blockers').value;

      const res = ItEnglishEngine.evaluateDailyStandup(y, t, b);
      const out = document.getElementById('standup-result');
      out.style.display = 'block';
      out.innerHTML = `
        <div style="font-size:15px; font-weight:700; color:${res.isPass ? '#10B981' : '#F59E0B'}">
          ${res.isPass ? '✅ Standup Tốt & Đầy Đủ' : '💡 Cần Mở Rộng Thêm'} (Điểm: ${res.score}/100)
        </div>
        ${res.remarks.length ? '<ul style="margin-top:6px; padding-left:18px; font-size:12px; color:#E2E8F0;">' + res.remarks.map(r => `<li>${r}</li>`).join('') + '</ul>' : ''}
      `;
    });
  }

  /* ===================================================
   * 4. DEEP KNOWLEDGE HUB TAB
   * =================================================== */
  initKnowledgeHub() {
    const grid = document.getElementById('vocab-grid');
    const searchInput = document.getElementById('vocab-search-input');

    const renderGrid = (items) => {
      grid.innerHTML = '';
      items.forEach(v => {
        const card = document.createElement('div');
        card.className = 'vocab-grid-item';
        card.innerHTML = `
          <div class="vocab-item-top">
            <span class="vocab-word">${v.wordEn}</span>
            <span class="code-lang-tag">${v.cefrLevel || 'A1'}</span>
          </div>
          <div class="vocab-meaning">${v.meaningVi}</div>
          <div style="font-family:monospace; font-size:11px; color:#38BDF8;">${v.ipa}</div>
        `;
        card.addEventListener('click', () => this.openDeepKnowledgeSheet(v));
        grid.appendChild(card);
      });
    };

    renderGrid(VISUAL_VOCABULARY_LIST);

    searchInput?.addEventListener('input', (e) => {
      const query = e.target.value.toLowerCase().trim();
      if (!query) {
        renderGrid(VISUAL_VOCABULARY_LIST);
        return;
      }
      const filtered = VISUAL_VOCABULARY_LIST.filter(v =>
        v.wordEn.toLowerCase().includes(query) ||
        v.meaningVi.toLowerCase().includes(query) ||
        v.key.toLowerCase().includes(query)
      );
      renderGrid(filtered);
    });
  }

  /* ===================================================
   * 5. BOTTOM SHEET MODAL (Encyclopedic Deep Knowledge)
   * =================================================== */
  initBottomSheet() {
    const closeBtn = document.getElementById('btn-close-sheet');
    attachFastClick(closeBtn, () => {
      this.closeDeepKnowledgeSheet();
    });

    attachFastClick(this.sheetOverlay, () => {
      this.closeDeepKnowledgeSheet();
    });

    const speakBtn = document.getElementById('sheet-speak-btn');
    attachFastClick(speakBtn, () => {
      const word = document.getElementById('sheet-word').textContent;
      if (word) this.speech.speak(word);
    });
  }

  openDeepKnowledgeSheet(vocab) {
    if (!vocab) return;

    document.getElementById('sheet-word').textContent = vocab.wordEn;
    document.getElementById('sheet-ipa').textContent = `${vocab.ipa} • [Trọng âm: ${vocab.syllableStress}]`;
    document.getElementById('sheet-meaning').textContent = `🇻🇳 ${vocab.meaningVi} (${vocab.partOfSpeech})`;
    document.getElementById('sheet-collocations-en').textContent = vocab.collocationsEn;
    document.getElementById('sheet-collocations-vi').textContent = vocab.collocationsVi;
    document.getElementById('sheet-sentence-en').textContent = vocab.exampleSentenceEn;
    document.getElementById('sheet-sentence-vi').textContent = vocab.exampleSentenceVi;
    document.getElementById('sheet-idiom-en').textContent = vocab.idiomEn;
    document.getElementById('sheet-idiom-vi').textContent = vocab.idiomVi;
    document.getElementById('sheet-dialogue-en').textContent = vocab.miniDialogueEn;
    document.getElementById('sheet-dialogue-vi').textContent = vocab.miniDialogueVi;
    document.getElementById('sheet-tech-en').textContent = vocab.technicalNoteEn;
    document.getElementById('sheet-tech-vi').textContent = vocab.technicalNoteVi;

    // Pop Quiz
    const quizBox = document.getElementById('sheet-quiz-box');
    quizBox.innerHTML = `
      <div style="font-weight:600; color:#FFF; font-size:13px; margin-bottom:8px;">${vocab.quizQuestion}</div>
      <div style="display:flex; flex-direction:column; gap:6px;">
        ${vocab.quizOptions.map((opt, i) => `
          <button class="sheet-quiz-opt action-btn-primary" data-idx="${i}" style="background:rgba(255,255,255,0.06); text-align:left; font-size:13px; padding:8px 12px;">${opt}</button>
        `).join('')}
      </div>
      <div id="sheet-quiz-feedback" style="display:none; margin-top:8px; font-size:12px; padding:6px; border-radius:6px;"></div>
    `;

    quizBox.querySelectorAll('.sheet-quiz-opt').forEach(btn => {
      btn.addEventListener('click', () => {
        const chosen = parseInt(btn.dataset.idx);
        const fb = document.getElementById('sheet-quiz-feedback');
        fb.style.display = 'block';
        if (chosen === vocab.quizCorrectIndex) {
          fb.style.background = 'rgba(16, 185, 129, 0.2)';
          fb.style.color = '#10B981';
          fb.textContent = '🎉 Chính xác! Bạn đã ghi nhớ từ vựng này rất tốt.';
          StorageManager.markWordLearned(vocab.key);
        } else {
          fb.style.background = 'rgba(244, 63, 94, 0.2)';
          fb.style.color = '#F43F5E';
          fb.textContent = `❌ Chưa đúng. Đáp án đúng là: ${vocab.quizOptions[vocab.quizCorrectIndex]}`;
        }
      });
    });

    this.sheetOverlay.classList.add('open');
    this.bottomSheet.classList.add('open');
  }

  closeDeepKnowledgeSheet() {
    this.sheetOverlay.classList.remove('open');
    this.bottomSheet.classList.remove('open');
  }

  /* ===================================================
   * 6. SETTINGS & PROFILE TAB
   * =================================================== */
  initSettings() {
    const accentSelect = document.getElementById('setting-voice-accent');
    if (accentSelect) {
      accentSelect.value = StorageManager.getVoiceAccent();
      accentSelect.addEventListener('change', (e) => {
        StorageManager.setVoiceAccent(e.target.value);
        this.speech.speak("Accent set to " + (e.target.value === 'en-US' ? 'American English' : 'British English'));
      });
    }

    document.getElementById('btn-clear-cache')?.addEventListener('click', () => {
      if (confirm("Bạn có chắc muốn xóa lịch sử học và bộ nhớ đệm?")) {
        localStorage.clear();
        this.updateStatsUI();
        alert("Đã xóa bộ nhớ đệm thành công.");
      }
    });
  }

  updateStatsUI() {
    const stats = StorageManager.getStats();
    const scannedEl = document.getElementById('stat-scanned');
    const learnedEl = document.getElementById('stat-learned');
    const totalEl = document.getElementById('stat-total');

    if (scannedEl) scannedEl.textContent = stats.scanned;
    if (learnedEl) learnedEl.textContent = stats.learned;
    if (totalEl) totalEl.textContent = VISUAL_VOCABULARY_LIST.length;
  }

  /* ===================================================
   * 7. iOS SAFARI "ADD TO HOME SCREEN" INSTALL BANNER
   * =================================================== */
  initInstallBanner() {
    const banner = document.getElementById('ios-install-banner');
    if (!banner) return;

    // Check if on iOS Safari and not standalone
    const isIos = /iphone|ipad|ipod/.test(window.navigator.userAgent.toLowerCase());
    const isStandalone = window.navigator.standalone === true || window.matchMedia('(display-mode: standalone)').matches;
    const isDismissed = localStorage.getItem('pwa_install_dismissed') === 'true';

    if (isIos && !isStandalone && !isDismissed) {
      banner.classList.remove('hidden');
      // Auto-hide after 6 seconds so it never hinders camera interaction
      setTimeout(() => {
        banner.classList.add('hidden');
      }, 6000);
    }

    const closeBtn = document.getElementById('btn-close-banner');
    attachFastClick(closeBtn, (e) => {
      if (e.stopPropagation) e.stopPropagation();
      banner.classList.add('hidden');
      localStorage.setItem('pwa_install_dismissed', 'true');
    });

    const triggerBtn = document.getElementById('btn-show-install');
    attachFastClick(triggerBtn, () => {
      banner.classList.remove('hidden');
    });
  }
}

// Bootstrap on DOM ready
document.addEventListener('DOMContentLoaded', () => {
  window.app = new AppController();

  // Register Service Worker for 100% Offline Capability
  if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('./sw.js')
      .then(reg => console.log('DevEnglish PWA Service Worker Registered', reg))
      .catch(err => console.warn('Service Worker registration failed', err));
  }
});
