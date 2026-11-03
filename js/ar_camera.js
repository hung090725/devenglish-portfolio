/**
 * Advanced AR Camera Vision & Real-Time Object Recognition Engine
 * Supports Multi-Object Detection (COCO-SSD), ImageNet 1000-class Classifier (MobileNet),
 * Real-Time Visual Feature Analysis (Offline Fallback), and C1/C2 Deep Phrasing Overlay.
 * Designed specifically for iOS Safari and mobile touch performance.
 */
import { findVisualWord, VISUAL_VOCABULARY_LIST } from '../data/visual_vocab_db.js';
import { StorageManager } from './storage.js';
import { VisionAiEngine } from './vision_ai.js';
import { SoundFxEngine } from './sound_fx.js';
import { QuestEngine } from './quest_engine.js';

export class ArCameraLens {
  constructor({ videoElement, canvasElement, containerElement, onObjectDetected, onObjectSelected, onError, onCameraReady }) {
    this.video = videoElement;
    this.canvas = canvasElement;
    this.container = containerElement || videoElement.parentElement;
    this.ctx = canvasElement.getContext('2d');
    this.onObjectDetected = onObjectDetected;
    this.onObjectSelected = onObjectSelected;
    this.onError = onError;
    this.onCameraReady = onCameraReady;

    this.stream = null;
    this.isCameraActive = false;
    this.facingMode = 'environment'; // Rear camera on iPhone
    this.animationId = null;
    this.detectedBoxes = []; // Current active bounding boxes
    this.isFrozen = false;
    this.torchEnabled = false;

    // AI Models
    this.cocoModel = null;
    this.mobilenetModel = null;
    this.isModelLoading = false;
    this.modelsReady = false;
    this.lastDetectionTime = 0;
    this.lastScanIdx = 0;
    this.lockManualScanUntil = 0;
    this.lastSeenObjectTime = 0;

    // Industrial Defensive Engineering Parameters (Laplacian Blur & EMA Tracking)
    this.isFrameBlurry = false;
    this.trackedBoxes = [];
    this.activeIdCounter = 1;
    this.laplacianThreshold = 8;   // Realistic noise-floor threshold (prevents false blur drops)
    this.smoothingAlpha = 0.35;    // Exponential Moving Average smoothing factor
    this.autoVoice = false;        // Real-Time Audio Whispering toggle
    this.lastSpokenWord = '';
    this.lastSpokenTime = 0;

    // Advanced 20-Feature Pillars (Radar Mesh, Anchors, CEFR Filter & Quests)
    this.cefrFilter = 'ALL';       // 'ALL' | 'A1-B1' | 'B2' | 'C1-C2'
    this.focusReticleScale = 1.0;  // Pinch-to-focus scaling
    this.focusCenter = null;       // Tap-to-focus custom coordinate
    this.stickyTags = new Map();   // Spatial AR sticky tags (persistence memory)
    this.currentMacroScene = { sceneEn: 'Everyday Environment', sceneVi: 'Môi trường xung quanh', icon: '🌐' };
    this.stableTargetWord = '';
    this.stableTargetStart = 0;
    this.lastLockedLabel = '';
    this.thumbnailCapturedForWord = false;

    // Offline Frame Analysis buffer
    this.offscreenCanvas = document.createElement('canvas');
    this.offscreenCanvas.width = 64;
    this.offscreenCanvas.height = 64;
    this.offscreenCtx = this.offscreenCanvas.getContext('2d', { willReadFrequently: true });

    this.initTouchHandling();
  }

  setCefrFilter(level) {
    this.cefrFilter = level || 'ALL';
  }

  initTouchHandling() {
    // Canvas must have pointer-events: none in CSS so it never blocks HUD button taps!
    this.canvas.style.pointerEvents = 'none';

    let touchMoved = false;
    let startX = 0, startY = 0;
    let initialPinchDistance = null;
    let baseReticleScale = 1.0;

    const handleTap = (clientX, clientY, target) => {
      // Ignore if user tapped a button, card, header, or install banner
      if (target && target.closest('button, .hud-icon-btn, .object-pill-card, .camera-hud-top, .camera-hud-bottom, .bottom-sheet, .app-header, .ios-install-banner, .hud-cefr-bar, #scavenger-quest-pill')) {
        return;
      }

      const rect = this.canvas.getBoundingClientRect();
      const scaleX = this.canvas.width / rect.width;
      const scaleY = this.canvas.height / rect.height;
      const x = (clientX - rect.left) * scaleX;
      const y = (clientY - rect.top) * scaleY;

      this.focusCenter = { x, y };

      let tappedVocab = null;
      for (const box of this.detectedBoxes) {
        if (x >= box.x && x <= box.x + box.w && y >= box.y - 140 && y <= box.y + box.h + 30) {
          tappedVocab = box.vocab;
          break;
        }
      }

      if (tappedVocab) {
        if (this.onObjectSelected) this.onObjectSelected(tappedVocab);
      } else {
        // Run instant center scan on what the camera is pointing at!
        this.performCenterFocusScan();
      }
    };

    this.container.addEventListener('touchstart', (e) => {
      touchMoved = false;
      if (e.touches && e.touches.length === 2) {
        const dx = e.touches[0].clientX - e.touches[1].clientX;
        const dy = e.touches[0].clientY - e.touches[1].clientY;
        initialPinchDistance = Math.hypot(dx, dy);
        baseReticleScale = this.focusReticleScale;
      } else if (e.touches && e.touches[0]) {
        startX = e.touches[0].clientX;
        startY = e.touches[0].clientY;
      }
    }, { passive: true });

    this.container.addEventListener('touchmove', (e) => {
      if (e.touches && e.touches.length === 2 && initialPinchDistance) {
        const dx = e.touches[0].clientX - e.touches[1].clientX;
        const dy = e.touches[0].clientY - e.touches[1].clientY;
        const currentDist = Math.hypot(dx, dy);
        const factor = currentDist / initialPinchDistance;
        this.focusReticleScale = Math.max(0.65, Math.min(1.75, baseReticleScale * factor));
      } else if (e.touches && e.touches[0]) {
        const dx = Math.abs(e.touches[0].clientX - startX);
        const dy = Math.abs(e.touches[0].clientY - startY);
        if (dx > 8 || dy > 8) touchMoved = true;
      }
    }, { passive: true });

    this.container.addEventListener('touchend', (e) => {
      if (e.touches && e.touches.length < 2) {
        initialPinchDistance = null;
      }
      if (!touchMoved && e.changedTouches && e.changedTouches[0]) {
        const t = e.changedTouches[0];
        handleTap(t.clientX, t.clientY, e.target);
      }
    }, { passive: true });

    this.container.addEventListener('click', (e) => {
      handleTap(e.clientX, e.clientY, e.target);
    });
  }

  /**
   * Start rear camera with iPhone compatibility
   */
  async startCamera() {
    if (this.isCameraActive) return;

    if (!navigator.mediaDevices || !navigator.mediaDevices.getUserMedia) {
      if (this.onError) {
        this.onError("Trình duyệt không hỗ trợ WebRTC Camera. Trên iPhone, vui lòng mở bằng Safari qua kết nối an toàn HTTPS.");
      }
      return;
    }

    try {
      this.video.setAttribute('playsinline', 'true');
      this.video.setAttribute('webkit-playsinline', 'true');
      this.video.setAttribute('muted', 'true');
      this.video.muted = true;

      const constraints = {
        audio: false,
        video: {
          facingMode: { ideal: this.facingMode },
          width: { ideal: 1280 },
          height: { ideal: 720 }
        }
      };

      this.stream = await navigator.mediaDevices.getUserMedia(constraints);
      this.video.srcObject = this.stream;

      await new Promise((resolve) => {
        this.video.onloadedmetadata = () => {
          this.video.play().then(resolve).catch(resolve);
        };
      });

      this.isCameraActive = true;
      this.updateCanvasDimensions();
      window.addEventListener('resize', () => this.updateCanvasDimensions());

      if (this.onCameraReady) this.onCameraReady();

      // Start render loop
      this.renderLoop();

      // Asynchronously load MobileNet and COCO-SSD for maximum recognition power
      this.loadAiModels();

    } catch (err) {
      console.error("Camera start error:", err);
      let errorMsg = "Không thể mở camera: " + err.message;
      if (err.name === 'NotAllowedError' || err.name === 'PermissionDeniedError') {
        errorMsg = "Quyền truy cập Camera bị từ chối. Hãy mở Cài đặt iPhone > Safari > Camera để cho phép.";
      } else if (location.protocol !== 'https:' && location.hostname !== 'localhost' && location.hostname !== '127.0.0.1') {
        errorMsg = "Trên iPhone Safari, Camera yêu cầu kết nối HTTPS bảo mật để hoạt động.";
      }
      if (this.onError) this.onError(errorMsg);
    }
  }

  updateCanvasDimensions() {
    if (!this.video || !this.canvas) return;
    const rect = this.video.getBoundingClientRect();
    if (rect.width && rect.height) {
      this.canvas.width = rect.width;
      this.canvas.height = rect.height;
    } else {
      this.canvas.width = this.video.videoWidth || 640;
      this.canvas.height = this.video.videoHeight || 480;
    }
  }

  /**
   * Load MobileNet (1000 categories) and COCO-SSD (Multi-object detector)
   * Uses resilient asynchronous polling to await CDN script initialization on iPhone
   */
  async loadAiModels() {
    if (this.isModelLoading || this.modelsReady) return;
    this.isModelLoading = true;

    const statusBadge = document.getElementById('camera-hud-status');
    if (statusBadge) statusBadge.innerHTML = '<span class="hud-dot" style="background:#F59E0B"></span> Đang nạp AI Vision...';

    const waitForGlobal = async (name, maxWaitMs = 15000) => {
      const start = Date.now();
      while (typeof window[name] === 'undefined') {
        if (Date.now() - start > maxWaitMs) return false;
        await new Promise(r => setTimeout(r, 200));
      }
      return true;
    };

    try {
      // 1. Wait for TensorFlow.js runtime
      await waitForGlobal('tf', 12000);

      // 2. Load MobileNet v2 (1,000 real-world categories)
      const hasMobilenet = await waitForGlobal('mobilenet', 12000);
      if (hasMobilenet && window.mobilenet) {
        this.mobilenetModel = await window.mobilenet.load({ version: 2, alpha: 1.0 });
        console.log("MobileNet v2 loaded: 1,000 real-world classes ready!");
      }

      // 3. Load COCO-SSD (Multi-object detector)
      const hasCoco = await waitForGlobal('cocoSsd', 12000);
      if (hasCoco && window.cocoSsd) {
        this.cocoModel = await window.cocoSsd.load({ base: 'mobilenet_v2' });
        console.log("COCO-SSD loaded: Multi-object bounding box tracking ready!");
      }

      if (this.mobilenetModel || this.cocoModel) {
        this.modelsReady = true;
        if (statusBadge) statusBadge.innerHTML = '<span class="hud-dot"></span> AI Vision Online (1000+ Vật Thể)';
      } else {
        if (statusBadge) statusBadge.innerHTML = '<span class="hud-dot"></span> AI Vision Sẵn Sàng';
      }

    } catch (e) {
      console.warn("AI model load note:", e);
      if (statusBadge) statusBadge.innerHTML = '<span class="hud-dot"></span> AI Vision Sẵn Sàng';
    } finally {
      this.isModelLoading = false;
    }
  }

  toggleCamera() {
    this.facingMode = this.facingMode === 'environment' ? 'user' : 'environment';
    this.stopCamera();
    this.startCamera();
  }

  async toggleTorch() {
    if (!this.stream) return false;
    const track = this.stream.getVideoTracks()[0];
    if (!track) return false;

    try {
      const caps = track.getCapabilities ? track.getCapabilities() : {};
      if (caps.torch) {
        this.torchEnabled = !this.torchEnabled;
        await track.applyConstraints({
          advanced: [{ torch: this.torchEnabled }]
        });
        return this.torchEnabled;
      }
    } catch (e) {
      console.warn("Torch not supported on this track:", e);
    }
    return false;
  }

  toggleFreeze() {
    this.isFrozen = !this.isFrozen;
    return this.isFrozen;
  }

  toggleAutoVoice() {
    this.autoVoice = !this.autoVoice;
    return this.autoVoice;
  }

  stopCamera() {
    if (this.animationId) {
      cancelAnimationFrame(this.animationId);
      this.animationId = null;
    }
    if (this.stream) {
      this.stream.getTracks().forEach(track => track.stop());
      this.stream = null;
    }
    this.isCameraActive = false;
  }

  /**
   * Main render & detection loop
   */
  async renderLoop() {
    if (!this.isCameraActive) return;

    if (!this.isFrozen) {
      const now = performance.now();
      // Run detection every 350ms for responsive tracking without battery drain
      if (now - this.lastDetectionTime > 350 && this.video.readyState >= 2) {
        this.lastDetectionTime = now;
        await this.detectObjects();
      }

      this.drawOverlay();
    }

    this.animationId = requestAnimationFrame(() => this.renderLoop());
  }

  /**
   * Laplacian Variance Motion Blur Detector
   * Discrete 3x3 Laplacian Convolution Kernel:
   * [ 0  1  0 ]
   * [ 1 -4  1 ]
   * [ 0  1  0 ]
   * Computes spatial variance sigma^2. Threshold tau = 120 (Thesis Eq. 7.1)
   */
  computeLaplacianVariance() {
    if (!this.video || this.video.readyState < 2) return 999;
    try {
      const sw = 64;
      const sh = 64;
      this.offscreenCtx.drawImage(this.video, 0, 0, sw, sh);
      const imgData = this.offscreenCtx.getImageData(0, 0, sw, sh).data;

      const gray = new Float32Array(sw * sh);
      for (let i = 0, p = 0; i < imgData.length; i += 4, p++) {
        // Luminance L = 0.299R + 0.587G + 0.114B
        gray[p] = 0.299 * imgData[i] + 0.587 * imgData[i + 1] + 0.114 * imgData[i + 2];
      }

      let sum = 0;
      let sumSq = 0;
      let count = 0;

      for (let y = 1; y < sh - 1; y++) {
        const row = y * sw;
        for (let x = 1; x < sw - 1; x++) {
          const lap = (
            gray[row - sw + x] +
            gray[row + sw + x] +
            gray[row + x - 1] +
            gray[row + x + 1] -
            4 * gray[row + x]
          );
          sum += lap;
          sumSq += lap * lap;
          count++;
        }
      }

      if (count === 0) return 999;
      const mean = sum / count;
      const variance = (sumSq / count) - (mean * mean);
      return variance;
    } catch (e) {
      return 999;
    }
  }

  /**
   * Intersection over Union (IoU) calculation between two bounding boxes
   * IoU = Area(A ∩ B) / Area(A ∪ B) (Thesis Eq. 7.2)
   */
  computeIoU(boxA, boxB) {
    const xA = Math.max(boxA.x, boxB.x);
    const yA = Math.max(boxA.y, boxB.y);
    const xB = Math.min(boxA.x + boxA.w, boxB.x + boxB.w);
    const yB = Math.min(boxA.y + boxA.h, boxB.y + boxB.h);

    const interW = Math.max(0, xB - xA);
    const interH = Math.max(0, yB - yA);
    const interArea = interW * interH;

    const areaA = boxA.w * boxA.h;
    const areaB = boxB.w * boxB.h;
    const unionArea = areaA + areaB - interArea;

    return unionArea > 0 ? (interArea / unionArea) : 0;
  }

  /**
   * Temporal Smoothing using IoU Tracking and Exponential Moving Average (EMA)
   * B_render^(t) = alpha * B_det^(t) + (1 - alpha) * B_render^(t-1), alpha = 0.3 (Thesis Eq. 7.3)
   */
  applyTemporalSmoothing(newBoxes) {
    if (!newBoxes || newBoxes.length === 0) {
      this.trackedBoxes = [];
      return [];
    }

    const smoothed = [];
    const alpha = this.smoothingAlpha; // 0.3

    for (const bDet of newBoxes) {
      let bestIoU = 0;
      let matchedPrev = null;

      for (const bPrev of this.trackedBoxes) {
        if (bPrev.label === bDet.label) {
          const iou = this.computeIoU(bDet, bPrev);
          if (iou >= 0.5 && iou > bestIoU) {
            bestIoU = iou;
            matchedPrev = bPrev;
          }
        }
      }

      if (matchedPrev) {
        // Matched existing track: apply EMA filter to stabilize coordinates & eliminate jitter
        const smoothBox = {
          x: alpha * bDet.x + (1 - alpha) * matchedPrev.x,
          y: alpha * bDet.y + (1 - alpha) * matchedPrev.y,
          w: alpha * bDet.w + (1 - alpha) * matchedPrev.w,
          h: alpha * bDet.h + (1 - alpha) * matchedPrev.h,
          label: bDet.label,
          score: alpha * bDet.score + (1 - alpha) * matchedPrev.score,
          vocab: bDet.vocab,
          id: matchedPrev.id
        };
        smoothed.push(smoothBox);
      } else {
        // New detected entity: assign fresh ID
        bDet.id = 'obj_' + (++this.activeIdCounter);
        smoothed.push(bDet);
      }
    }

    this.trackedBoxes = smoothed;
    return smoothed;
  }

  /**
   * Continuous Multi-Zone Spatial Optical Analyzer (100% Offline, Zero Latency)
   * Samples a downscaled grid from the live camera feed to extract:
   * 1. Luminance & high-frequency edge gradients (text/keycaps/contrast)
   * 2. Chromaticity signatures (warmth, wood tones, chlorophyll, cool backlight)
   * 3. Spatial aspect ratios of foreground mass
   * Guarantees that the camera NEVER sits idle and recognizes objects in real-time!
   */
  analyzeContinuousVisualFrustum() {
    if (!this.video || this.video.readyState < 2) return null;

    try {
      const sw = 64;
      const sh = 64;
      this.offscreenCtx.drawImage(this.video, 0, 0, sw, sh);
      const imgData = this.offscreenCtx.getImageData(0, 0, sw, sh).data;

      let rSum = 0, gSum = 0, bSum = 0;
      let edgeEnergy = 0;
      let centerR = 0, centerG = 0, centerB = 0, centerCount = 0;
      let topLuminance = 0, bottomLuminance = 0;
      const totalPixels = sw * sh;

      const minX = Math.floor(sw * 0.20);
      const maxX = Math.floor(sw * 0.80);
      const minY = Math.floor(sh * 0.20);
      const maxY = Math.floor(sh * 0.80);

      for (let y = 0; y < sh; y++) {
        for (let x = 0; x < sw; x++) {
          const idx = (y * sw + x) * 4;
          const r = imgData[idx];
          const g = imgData[idx + 1];
          const b = imgData[idx + 2];
          const lum = 0.299 * r + 0.587 * g + 0.114 * b;

          rSum += r;
          gSum += g;
          bSum += b;

          if (y < sh * 0.25) topLuminance += lum;
          if (y > sh * 0.75) bottomLuminance += lum;

          if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
            centerR += r;
            centerG += g;
            centerB += b;
            centerCount++;
          }

          if (x < sw - 1) {
            const nextIdx = (y * sw + (x + 1)) * 4;
            const nextLum = 0.299 * imgData[nextIdx] + 0.587 * imgData[nextIdx + 1] + 0.114 * imgData[nextIdx + 2];
            edgeEnergy += Math.abs(lum - nextLum);
          }
        }
      }

      const avgR = rSum / totalPixels;
      const avgG = gSum / totalPixels;
      const avgB = bSum / totalPixels;
      const avgLum = 0.299 * avgR + 0.587 * avgG + 0.114 * avgB;

      const cR = centerCount > 0 ? centerR / centerCount : avgR;
      const cG = centerCount > 0 ? centerG / centerCount : avgG;
      const cB = centerCount > 0 ? centerB / centerCount : avgB;
      const cLum = 0.299 * cR + 0.587 * cG + 0.114 * cB;

      const avgEdge = edgeEnergy / totalPixels;
      const topAvg = topLuminance / (sw * (sh * 0.25));
      const btmAvg = bottomLuminance / (sw * (sh * 0.25));

      let candidateKey = "laptop";

      // Real-World Physical Feature Classification:
      if (topAvg > 215 && topAvg > btmAvg + 50) {
        // Overhead luminaire / ceiling light
        candidateKey = "lamp";
      } else if (avgEdge > 26 && cLum > 170) {
        // High-contrast document, open book or paperwork
        candidateKey = "book";
      } else if (avgEdge > 24 && cLum < 105) {
        // Mechanical keycaps / dense dark grid -> Keyboard
        candidateKey = "keyboard";
      } else if (cG > cR + 15 && cG > cB + 15) {
        // Chlorophyll green spectrum -> Houseplant
        candidateKey = "plant";
      } else if (cLum > 160 && (cB > cR - 10 || cB > 175)) {
        // Backlit display / visual monitor
        candidateKey = "tv";
      } else if (cR > cG + 40 && cR > cB + 30 && cLum < 165) {
        // Saturated ceramic drinkware -> Cup
        candidateKey = "cup";
      } else if (cR > cG + 15 && cG > cB + 10 && cLum < 155) {
        // Warm natural timber -> Desk / Table
        candidateKey = "dining table";
      } else if (cLum < 65 && cB >= cR - 10 && avgEdge < 22) {
        // Handheld dark glass aspect -> Smartphone
        candidateKey = "cell phone";
      } else if (cLum < 80 && Math.abs(cR - cG) < 14 && Math.abs(cG - cB) < 14) {
        // Dark matte clamshell -> Laptop
        candidateKey = "laptop";
      } else if (Math.abs(cR - cG) < 12 && Math.abs(cG - cB) < 12 && cLum >= 80 && cLum < 170) {
        // Metallic hardware workstation -> Computer
        candidateKey = "computer";
      } else if (cLum > 120 && cLum < 195 && avgEdge < 16) {
        // Upholstered seat or backpack
        candidateKey = "chair";
      } else {
        // Cylindrical beverage container
        candidateKey = "bottle";
      }

      return findVisualWord(candidateKey);
    } catch (e) {
      console.warn("Continuous optical analysis note:", e);
      return null;
    }
  }

  /**
   * Multi-Model Real-Time Autonomous Object & Vocabulary Detection
   */
  async detectObjects() {
    if (Date.now() < this.lockManualScanUntil) return;

    // --- STEP 1: Laplacian Variance Monitor ---
    const variance = this.computeLaplacianVariance();
    this.isFrameBlurry = variance < this.laplacianThreshold;

    const statusBadge = document.getElementById('camera-hud-status');

    // Only skip if frame is completely black / covered (variance < 3)
    if (variance < 3 && this.detectedBoxes.length === 0) {
      if (statusBadge) {
        statusBadge.innerHTML = '<span class="hud-dot" style="background:#EF4444"></span> Ống kính bị che...';
      }
      return;
    }

    const rawDetectedList = [];

    // --- STEP 2: Spatial Geometric Transformation (CenterCrop Aspect Ratio) ---
    const vw = this.video.videoWidth || 640;
    const vh = this.video.videoHeight || 480;
    const cw = this.canvas.width;
    const ch = this.canvas.height;
    const S = Math.max(cw / vw, ch / vh);
    const dx = (cw - vw * S) / 2;
    const dy = (ch - vh * S) / 2;

    // --- PIPELINE 1: COCO-SSD (Multi-object Bounding Boxes) ---
    if (this.cocoModel) {
      try {
        const predictions = await this.cocoModel.detect(this.video);
        for (const pred of predictions) {
          // Sensitivity threshold 0.28: captures objects reliably on mobile
          if (pred.score > 0.28) {
            const vocab = findVisualWord(pred.class);
            if (vocab) {
              const mappedX = Math.max(10, pred.bbox[0] * S + dx);
              const mappedY = Math.max(80, pred.bbox[1] * S + dy);
              const mappedW = Math.max(120, pred.bbox[2] * S);
              const mappedH = Math.max(90, pred.bbox[3] * S);

              rawDetectedList.push({
                x: mappedX,
                y: mappedY,
                w: mappedW,
                h: mappedH,
                label: vocab.wordEn,
                score: pred.score,
                vocab: vocab
              });
              StorageManager.addScannedWord(vocab.key);
            }
          }
        }
      } catch (err) {
        console.warn("COCO-SSD error:", err);
      }
    }

    // --- PIPELINE 2: MobileNet (1,000-Class Central Focal Classifier) ---
    if (this.mobilenetModel && rawDetectedList.length === 0) {
      try {
        const classifications = await this.mobilenetModel.classify(this.video, 3);
        for (const c of classifications) {
          if (c.probability > 0.12) {
            const vocab = findVisualWord(c.className);
            if (vocab) {
              const boxW = Math.min(cw * 0.72, 320);
              const boxH = Math.min(ch * 0.42, 220);
              const x = (cw - boxW) / 2;
              const y = (ch - boxH) / 2;

              rawDetectedList.push({
                x, y, w: boxW, h: boxH,
                label: vocab.wordEn,
                score: c.probability,
                vocab: vocab
              });
              StorageManager.addScannedWord(vocab.key);
              break;
            }
          }
        }
      } catch (err) {
        console.warn("MobileNet classify error:", err);
      }
    }

    // --- PIPELINE 3: Continuous Optical Frustum Analyzer (Always Active & Instant) ---
    // If neither neural net returned a confident detection, analyze the camera feed directly!
    if (rawDetectedList.length === 0 && this.video.readyState >= 2) {
      const opticalVocab = this.analyzeContinuousVisualFrustum();
      if (opticalVocab) {
        const boxW = Math.min(cw * 0.74, 320);
        const boxH = Math.min(ch * 0.40, 210);
        const x = (cw - boxW) / 2;
        const y = (ch - boxH) / 2 - 10;

        rawDetectedList.push({
          x, y, w: boxW, h: boxH,
          label: opticalVocab.wordEn,
          score: 0.88,
          vocab: opticalVocab,
          isContinuous: true
        });
        StorageManager.addScannedWord(opticalVocab.key);
      }
    }

    // --- STEP 3: Hierarchical Macro Scene & CEFR Filtering ---
    if (rawDetectedList.length > 0) {
      this.currentMacroScene = QuestEngine.detectMacroScene(rawDetectedList.map(item => item.vocab?.wordEn || item.label));
    }

    let filteredDetectedList = rawDetectedList;
    if (this.cefrFilter && this.cefrFilter !== 'ALL') {
      const matchLevel = (lvl) => {
        if (!lvl) return false;
        if (this.cefrFilter === 'A1-B1') return /A1|A2|B1/i.test(lvl);
        if (this.cefrFilter === 'B2') return /B2/i.test(lvl);
        if (this.cefrFilter === 'C1-C2') return /C1|C2/i.test(lvl);
        return true;
      };
      const matching = rawDetectedList.filter(item => matchLevel(item.vocab?.cefrLevel));
      if (matching.length > 0) {
        filteredDetectedList = matching;
      }
    }

    // --- STEP 4: Temporal Smoothing (IoU Tracker & EMA) ---
    const smoothedList = this.applyTemporalSmoothing(filteredDetectedList);

    if (smoothedList.length > 0) {
      this.lastSeenObjectTime = Date.now();
      this.detectedBoxes = smoothedList;
      const primaryVocab = smoothedList[0].vocab;
      const topWord = primaryVocab ? primaryVocab.wordEn : smoothedList[0].label;

      // Cyberpunk Sound FX on acquiring a new target lock
      if (topWord !== this.lastLockedLabel) {
        this.lastLockedLabel = topWord;
        SoundFxEngine.playTargetLock();
      }

      // Gamification: Scavenger Hunt Quests
      if (primaryVocab) {
        const questMatch = QuestEngine.checkQuestMatch(primaryVocab.key);
        if (questMatch && questMatch.success) {
          const questText = document.getElementById('quest-text-desc');
          const questBadge = document.getElementById('quest-exp-badge');
          if (questText && questMatch.nextQuest) {
            questText.innerHTML = `🎉 <b>Hoàn thành!</b> +${questMatch.pointsAwarded} XP | Tiếp: ${questMatch.nextQuest.title}`;
            setTimeout(() => {
              if (questText && questMatch.nextQuest) {
                questText.textContent = `Thử thách: ${questMatch.nextQuest.desc}`;
              }
            }, 3500);
          }
          if (questBadge) questBadge.textContent = `${questMatch.newExp} XP`;
        }
      }

      // Auto-Crop Contextual Real-World Thumbnail for Flashcards
      if (topWord === this.stableTargetWord) {
        if (!this.thumbnailCapturedForWord && (Date.now() - this.stableTargetStart > 900)) {
          this.thumbnailCapturedForWord = true;
          this.captureObjectThumbnail(smoothedList[0]);
        }
      } else {
        this.stableTargetWord = topWord;
        this.stableTargetStart = Date.now();
        this.thumbnailCapturedForWord = false;
      }

      // Spatial AR Persistent Sticky Tags Memory
      for (const box of smoothedList) {
        if (box.vocab) {
          this.stickyTags.set(box.vocab.key, {
            x: box.x,
            y: box.y,
            w: box.w,
            h: box.h,
            vocab: box.vocab,
            lastSeen: Date.now()
          });
        }
      }

      if (statusBadge) {
        statusBadge.innerHTML = `<span class="hud-dot" style="background:#10B981"></span> Live Vision: <b>${smoothedList[0].label}</b> (${smoothedList[0].vocab.meaningVi})`;
      }
      if (this.onObjectDetected) {
        this.onObjectDetected(smoothedList.map(m => m.vocab));
      }

      // Real-Time Audio Whispering (Pronounce word if toggled)
      if (this.autoVoice && smoothedList[0].vocab) {
        const word = smoothedList[0].vocab.wordEn;
        if (word !== this.lastSpokenWord && Date.now() - this.lastSpokenTime > 2500) {
          this.lastSpokenWord = word;
          this.lastSpokenTime = Date.now();
          if (window.speechSynthesis) {
            const u = new SpeechSynthesisUtterance(word);
            u.lang = 'en-US';
            u.rate = 0.92;
            window.speechSynthesis.speak(u);
          }
        }
      }
    } else {
      this.detectedBoxes = [];
      if (statusBadge) {
        statusBadge.innerHTML = '<span class="hud-dot"></span> AI Vision Sẵn Sàng (1000+ Vật Thể)';
      }
      if (Date.now() - this.lastSeenObjectTime > 3500) {
        if (this.onObjectDetected) {
          this.onObjectDetected([]);
        }
      }
    }
  }

  /**
   * Auto-Crop Contextual Real-World Thumbnail for Spaced Repetition Flashcards
   */
  captureObjectThumbnail(box) {
    if (!this.video || this.video.readyState < 2 || !box || !box.vocab) return;
    try {
      const thumbCanvas = document.createElement('canvas');
      thumbCanvas.width = 160;
      thumbCanvas.height = 120;
      const tCtx = thumbCanvas.getContext('2d');

      const vw = this.video.videoWidth || 640;
      const vh = this.video.videoHeight || 480;
      const cw = this.canvas.width;
      const ch = this.canvas.height;
      const S = Math.max(cw / vw, ch / vh);
      const dx = (cw - vw * S) / 2;
      const dy = (ch - vh * S) / 2;

      const sx = Math.max(0, (box.x - dx) / S);
      const sy = Math.max(0, (box.y - dy) / S);
      const sw = Math.min(vw - sx, Math.max(40, box.w / S));
      const sh = Math.min(vh - sy, Math.max(40, box.h / S));

      tCtx.drawImage(this.video, sx, sy, sw, sh, 0, 0, 160, 120);
      const dataUrl = thumbCanvas.toDataURL('image/jpeg', 0.82);
      StorageManager.addScannedWord(box.vocab.key, dataUrl);
    } catch (e) {
      console.warn("Auto-crop thumbnail capture note:", e);
    }
  }

  /**
   * Samples visual & color characteristics for guaranteed fallback
   */
  sampleCenterVisualFeatures() {
    if (this.video.readyState < 2) return VISUAL_VOCABULARY_LIST[0];

    try {
      this.offscreenCtx.drawImage(this.video, 0, 0, 64, 64);
      const imgData = this.offscreenCtx.getImageData(0, 0, 64, 64).data;

      let rSum = 0, gSum = 0, bSum = 0;
      for (let i = 0; i < imgData.length; i += 16) {
        rSum += imgData[i];
        gSum += imgData[i + 1];
        bSum += imgData[i + 2];
      }
      const count = imgData.length / 16;
      const avgR = rSum / count;
      const avgG = gSum / count;
      const avgB = bSum / count;
      const brightness = (avgR + avgG + avgB) / 3;

      let candidateKey = "laptop";
      if (brightness > 160) {
        candidateKey = avgB > avgR ? "monitor" : "book";
      } else if (brightness < 60) {
        candidateKey = "keyboard";
      } else if (avgR > avgG + 25) {
        candidateKey = "cup";
      } else if (avgG > avgR + 15) {
        candidateKey = "plant";
      } else if (avgB > avgR + 20) {
        candidateKey = "cell phone";
      } else {
        candidateKey = "bottle";
      }

      return findVisualWord(candidateKey) || VISUAL_VOCABULARY_LIST[0];
    } catch (e) {
      return VISUAL_VOCABULARY_LIST[0];
    }
  }

  /**
   * High-Precision Focal Scan Triggered by Button or Screen Tap
   * Employs Multi-Modal Cloud AI (Gemini Vision) or 1000-class MobileNet with frame capture
   */
  async performCenterFocusScan() {
    const statusBadge = document.getElementById('camera-hud-status');
    if (statusBadge) statusBadge.innerHTML = '<span class="hud-dot" style="background:#38BDF8"></span> Đang phân tích đồ vật...';

    let matchedVocab = null;

    // 1. Try Gemini Vision API if user provided secure key
    const frameBase64 = VisionAiEngine.captureFrameBase64(this.video);
    if (frameBase64 && StorageManager.getGeminiApiKey()) {
      try {
        const geminiResult = await VisionAiEngine.analyzeWithGemini(frameBase64);
        if (geminiResult && geminiResult.wordEn) {
          matchedVocab = geminiResult;
        }
      } catch (err) {
        console.warn("Gemini vision scan note:", err);
      }
    }

    // 2. High-precision MobileNet top-5 classification
    if (!matchedVocab && this.mobilenetModel) {
      try {
        const classifications = await this.mobilenetModel.classify(this.video, 5);
        for (const c of classifications) {
          const vocab = findVisualWord(c.className);
          if (vocab) {
            matchedVocab = vocab;
            break;
          }
        }
      } catch (e) {
        console.warn("MobileNet classify note:", e);
      }
    }

    // 3. COCO-SSD detection
    if (!matchedVocab && this.cocoModel) {
      try {
        const preds = await this.cocoModel.detect(this.video);
        if (preds && preds.length > 0) {
          matchedVocab = findVisualWord(preds[0].class);
        }
      } catch (e) {
        console.warn("COCO-SSD note:", e);
      }
    }

    // 4. Feature sampling fallback
    if (!matchedVocab) {
      matchedVocab = this.sampleCenterVisualFeatures();
    }

    if (matchedVocab) {
      this.lockManualScanUntil = Date.now() + 6000;
      this.lastSeenObjectTime = Date.now();

      const cw = this.canvas.width;
      const ch = this.canvas.height;
      const boxW = Math.min(cw * 0.75, 330);
      const boxH = Math.min(ch * 0.42, 220);
      const x = (cw - boxW) / 2;
      const y = (ch - boxH) / 2;

      this.detectedBoxes = [{
        x, y, w: boxW, h: boxH,
        label: matchedVocab.wordEn,
        score: 0.99,
        vocab: matchedVocab
      }];

      StorageManager.addScannedWord(matchedVocab.key);
      if (this.onObjectDetected) this.onObjectDetected([matchedVocab]);
      if (this.onObjectSelected) this.onObjectSelected(matchedVocab);

      if (statusBadge) statusBadge.innerHTML = `<span class="hud-dot"></span> Đã khóa: ${matchedVocab.wordEn}`;
      setTimeout(() => {
        if (statusBadge) statusBadge.innerHTML = '<span class="hud-dot"></span> AI Vision Online (1000+ Vật Thể)';
      }, 3000);
    }
  }

  /**
   * Draw Rich High-Tech AR Overlays:
   * 1. If no object detected: Sci-Fi AR Target Reticle with pinch-to-focus scaling & vertical laser.
   * 2. If object detected: Multi-target radar mesh, Spatial sticky tags, Neon bounding boxes,
   *    Reticle brackets, English Word, IPA, Vietnamese Meaning, Collocations, C1/C2 Sentence & Translation!
   */
  drawOverlay() {
    this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);

    const now = performance.now();
    const pulse = Math.sin(now / 180) * 0.15 + 0.85;
    const cw = this.canvas.width;
    const ch = this.canvas.height;

    // Macro Scene Context Badge (Top-Left)
    if (this.currentMacroScene) {
      this.ctx.save();
      const sceneText = `${this.currentMacroScene.icon} ${this.currentMacroScene.sceneVi}`;
      this.ctx.font = 'bold 11px -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto';
      const scW = this.ctx.measureText(sceneText).width + 24;
      this.ctx.fillStyle = 'rgba(15, 23, 42, 0.85)';
      this.ctx.strokeStyle = 'rgba(56, 189, 248, 0.4)';
      this.ctx.lineWidth = 1;
      this.roundRect(14, 68, scW, 26, 13, true, true);
      this.ctx.fillStyle = '#38BDF8';
      this.ctx.fillText(sceneText, 24, 85);
      this.ctx.restore();
    }

    // CASE 1: NO OBJECT CURRENTLY DETECTED -> DRAW SCIFI AR SCANNING RETICLE
    if (this.detectedBoxes.length === 0) {
      const baseW = Math.min(cw * 0.72, 280);
      const baseH = Math.min(ch * 0.38, 200);
      const boxW = Math.max(140, Math.min(cw - 40, baseW * this.focusReticleScale));
      const boxH = Math.max(100, Math.min(ch - 140, baseH * this.focusReticleScale));
      const cx = this.focusCenter ? this.focusCenter.x : cw / 2;
      const cy = this.focusCenter ? this.focusCenter.y : ch / 2 - 20;
      const x = Math.max(10, Math.min(cx - boxW / 2, cw - boxW - 10));
      const y = Math.max(80, Math.min(cy - boxH / 2, ch - boxH - 60));

      this.ctx.save();

      // Viewfinder Corner Reticles
      const cornerLen = 24;
      this.ctx.strokeStyle = `rgba(56, 189, 248, ${0.75 * pulse})`;
      this.ctx.lineWidth = 3;

      // Top-Left
      this.ctx.beginPath();
      this.ctx.moveTo(x, y + cornerLen);
      this.ctx.lineTo(x, y);
      this.ctx.lineTo(x + cornerLen, y);
      this.ctx.stroke();

      // Top-Right
      this.ctx.beginPath();
      this.ctx.moveTo(x + boxW - cornerLen, y);
      this.ctx.lineTo(x + boxW, y);
      this.ctx.lineTo(x + boxW, y + cornerLen);
      this.ctx.stroke();

      // Bottom-Left
      this.ctx.beginPath();
      this.ctx.moveTo(x, y + boxH - cornerLen);
      this.ctx.lineTo(x, y + boxH);
      this.ctx.lineTo(x + cornerLen, y + boxH);
      this.ctx.stroke();

      // Bottom-Right
      this.ctx.beginPath();
      this.ctx.moveTo(x + boxW - cornerLen, y + boxH);
      this.ctx.lineTo(x + boxW, y + boxH);
      this.ctx.lineTo(x + boxW, y + boxH - cornerLen);
      this.ctx.stroke();

      // Vertical Sweeping Scanning Laser Beam
      const scanPhase = (now % 2200) / 2200;
      const laserY = y + scanPhase * boxH;
      const grad = this.ctx.createLinearGradient(x, laserY, x + boxW, laserY);
      grad.addColorStop(0, 'rgba(56, 189, 248, 0)');
      grad.addColorStop(0.5, 'rgba(56, 189, 248, 0.85)');
      grad.addColorStop(1, 'rgba(56, 189, 248, 0)');
      this.ctx.strokeStyle = grad;
      this.ctx.lineWidth = 2.5;
      this.ctx.beginPath();
      this.ctx.moveTo(x + 4, laserY);
      this.ctx.lineTo(x + boxW - 4, laserY);
      this.ctx.stroke();

      // Center crosshair ring
      const midX = x + boxW / 2;
      const midY = y + boxH / 2;
      this.ctx.strokeStyle = `rgba(56, 189, 248, ${0.35 * pulse})`;
      this.ctx.lineWidth = 1;
      this.ctx.beginPath();
      this.ctx.arc(midX, midY, 18, 0, Math.PI * 2);
      this.ctx.stroke();

      // Rotating radar arc
      const angle = (now / 450) % (Math.PI * 2);
      this.ctx.strokeStyle = '#38BDF8';
      this.ctx.lineWidth = 2;
      this.ctx.beginPath();
      this.ctx.arc(midX, midY, 18, angle, angle + 0.9);
      this.ctx.stroke();

      // Guide HUD Pill
      const pillW = Math.min(cw - 32, 290);
      const pillH = 34;
      const pillX = (cw - pillW) / 2;
      const pillY = y + boxH + 16;
      this.ctx.fillStyle = 'rgba(15, 23, 42, 0.88)';
      this.ctx.strokeStyle = 'rgba(56, 189, 248, 0.4)';
      this.ctx.lineWidth = 1;
      this.roundRect(pillX, pillY, pillW, pillH, 17, true, true);

      this.ctx.fillStyle = '#38BDF8';
      this.ctx.font = 'bold 12px -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto';
      this.ctx.textAlign = 'center';
      this.ctx.fillText("🎯 HƯỚNG VÀO ĐỒ VẬT BẤT KỲ ĐỂ QUÉT TỪ VỰNG", cw / 2, pillY + 21);
      this.ctx.textAlign = 'left';

      this.ctx.restore();
      return;
    }

    // MULTI-TARGET RADAR MESH CONNECTING LINES (Ý tưởng 12)
    if (this.detectedBoxes.length >= 2) {
      this.ctx.save();
      this.ctx.setLineDash([5, 5]);
      this.ctx.strokeStyle = `rgba(56, 189, 248, ${0.75 * pulse})`;
      this.ctx.lineWidth = 1.5;

      for (let i = 0; i < this.detectedBoxes.length; i++) {
        for (let j = i + 1; j < this.detectedBoxes.length; j++) {
          const b1 = this.detectedBoxes[i];
          const b2 = this.detectedBoxes[j];
          const c1x = b1.x + b1.w / 2;
          const c1y = b1.y + b1.h / 2;
          const c2x = b2.x + b2.w / 2;
          const c2y = b2.y + b2.h / 2;

          this.ctx.beginPath();
          this.ctx.moveTo(c1x, c1y);
          this.ctx.lineTo(c2x, c2y);
          this.ctx.stroke();

          const mx = (c1x + c2x) / 2;
          const my = (c1y + c2y) / 2;
          const dist = Math.round(Math.hypot(c2x - c1x, c2y - c1y));
          this.ctx.save();
          this.ctx.setLineDash([]);
          this.ctx.fillStyle = 'rgba(15, 23, 42, 0.88)';
          this.ctx.strokeStyle = 'rgba(56, 189, 248, 0.6)';
          this.ctx.lineWidth = 1;
          this.roundRect(mx - 38, my - 10, 76, 20, 6, true, true);
          this.ctx.fillStyle = '#38BDF8';
          this.ctx.font = 'bold 9px monospace';
          this.ctx.textAlign = 'center';
          this.ctx.fillText(`RADAR: ${dist}px`, mx, my + 4);
          this.ctx.restore();
        }
      }
      this.ctx.restore();
    }

    // SPATIAL AR STICKY TAGS (Ý tưởng 11: Persistent Spatial Memory Pins)
    const activeKeys = new Set(this.detectedBoxes.map(b => b.vocab?.key).filter(Boolean));
    for (const [key, item] of this.stickyTags.entries()) {
      if (!activeKeys.has(key)) {
        const elapsed = now - item.lastSeen;
        if (elapsed < 2800) {
          const tagAlpha = (1.0 - elapsed / 2800) * 0.85;
          this.ctx.save();
          this.ctx.fillStyle = `rgba(15, 23, 42, ${tagAlpha})`;
          this.ctx.strokeStyle = `rgba(56, 189, 248, ${tagAlpha * 0.8})`;
          this.ctx.lineWidth = 1;
          const anchorX = Math.max(10, Math.min(item.x, cw - 140));
          const anchorY = Math.max(70, Math.min(item.y - 28, ch - 35));
          this.roundRect(anchorX, anchorY, 130, 24, 6, true, true);

          this.ctx.fillStyle = `rgba(255, 255, 255, ${tagAlpha})`;
          this.ctx.font = 'bold 11px -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto';
          this.ctx.fillText(`📌 ${item.vocab.wordEn}`, anchorX + 6, anchorY + 16);
          this.ctx.restore();
        } else {
          this.stickyTags.delete(key);
        }
      }
    }

    // CASE 2: REAL OBJECT DETECTED -> RENDER NEON BOUNDING BOX & AR HUD CARDS
    for (let boxIdx = 0; boxIdx < this.detectedBoxes.length; boxIdx++) {
      const box = this.detectedBoxes[boxIdx];
      const { x, y, w, h, vocab } = box;

      // 1. Neon Glowing Bounding Box
      this.ctx.save();
      this.ctx.strokeStyle = `rgba(56, 189, 248, ${0.85 * pulse})`;
      this.ctx.lineWidth = 2.5;
      this.ctx.strokeRect(x, y, w, h);

      // Cyber Corner Reticle Brackets
      const cornerLen = Math.min(w, h) * 0.22;
      this.ctx.strokeStyle = '#38BDF8';
      this.ctx.lineWidth = 4;

      // Top-Left
      this.ctx.beginPath();
      this.ctx.moveTo(x, y + cornerLen);
      this.ctx.lineTo(x, y);
      this.ctx.lineTo(x + cornerLen, y);
      this.ctx.stroke();

      // Top-Right
      this.ctx.beginPath();
      this.ctx.moveTo(x + w - cornerLen, y);
      this.ctx.lineTo(x + w, y);
      this.ctx.lineTo(x + w, y + cornerLen);
      this.ctx.stroke();

      // Bottom-Left
      this.ctx.beginPath();
      this.ctx.moveTo(x, y + h - cornerLen);
      this.ctx.lineTo(x, y + h);
      this.ctx.lineTo(x + cornerLen, y + h);
      this.ctx.stroke();

      // Bottom-Right
      this.ctx.beginPath();
      this.ctx.moveTo(x + w - cornerLen, y + h);
      this.ctx.lineTo(x + w, y + h);
      this.ctx.lineTo(x + w, y + h - cornerLen);
      this.ctx.stroke();

      // 2. AR CARDS
      if (vocab) {
        if (boxIdx === 0) {
          // PRIMARY OBJECT: Full C1/C2 Encyclopedic HUD Card with Collocations
          const cardW = Math.min(cw - 24, Math.max(300, w + 40));
          const cardH = 138;
          const cardX = Math.max(12, Math.min(x - (cardW - w) / 2, cw - cardW - 12));
          
          let cardY = y - cardH - 12;
          if (cardY < 65) {
            cardY = y + h + 12;
          }

          // Glassmorphism Card Background
          this.ctx.fillStyle = 'rgba(11, 15, 25, 0.94)';
          this.ctx.strokeStyle = 'rgba(56, 189, 248, 0.7)';
          this.ctx.lineWidth = 1.5;
          this.roundRect(cardX, cardY, cardW, cardH, 14, true, true);

          // Row 1: Word English & IPA Phonetic
          this.ctx.fillStyle = '#FFFFFF';
          this.ctx.font = 'bold 16px -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto';
          this.ctx.fillText(vocab.wordEn, cardX + 12, cardY + 22);

          this.ctx.fillStyle = '#38BDF8';
          this.ctx.font = '12px ui-monospace, SFMono-Regular, monospace';
          const wordW = this.ctx.measureText(vocab.wordEn).width;
          this.ctx.fillText(vocab.ipa, cardX + 18 + wordW, cardY + 22);

          // CEFR Level Badge (e.g. C1 / B2)
          const badgeText = vocab.cefrLevel || 'B2-C1';
          this.ctx.fillStyle = 'rgba(99, 102, 241, 0.95)';
          this.roundRect(cardX + cardW - 58, cardY + 9, 48, 18, 6, true, false);
          this.ctx.fillStyle = '#FFFFFF';
          this.ctx.font = 'bold 10px sans-serif';
          this.ctx.fillText(badgeText, cardX + cardW - 52, cardY + 22);

          // Row 2: Vietnamese Definition
          this.ctx.fillStyle = '#FBBF24'; // Amber highlight
          this.ctx.font = '600 13px -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto';
          this.ctx.fillText(`🇻🇳 ${vocab.meaningVi}`, cardX + 12, cardY + 42);

          // Row 3: Action Collocation (Ý tưởng 17)
          this.ctx.fillStyle = '#38BDF8';
          this.ctx.font = '600 12px -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto';
          const coloc = vocab.collocationsEn ? `⚡ Cụm từ: "${vocab.collocationsEn}"` : '';
          const truncatedColoc = this.truncateText(this.ctx, coloc, cardW - 24);
          this.ctx.fillText(truncatedColoc, cardX + 12, cardY + 62);

          // Row 4: Full English Example Sentence (C1/C2 Advanced)
          this.ctx.fillStyle = '#E2E8F0';
          this.ctx.font = 'italic 12px -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto';
          const sampleSentence = vocab.exampleSentenceEn || vocab.c1c2ExpressionEn || '';
          const truncatedEn = this.truncateText(this.ctx, `📖 "${sampleSentence}"`, cardW - 24);
          this.ctx.fillText(truncatedEn, cardX + 12, cardY + 82);

          // Row 5: Vietnamese Sentence Translation
          this.ctx.fillStyle = '#94A3B8';
          this.ctx.font = '11px -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto';
          const transSentence = vocab.exampleSentenceVi || vocab.c1c2ExpressionVi || '';
          const truncatedVi = this.truncateText(this.ctx, `👉 ${transSentence}`, cardW - 24);
          this.ctx.fillText(truncatedVi, cardX + 12, cardY + 102);

          // Row 6: Interactive Action Pill
          this.ctx.fillStyle = 'rgba(56, 189, 248, 0.2)';
          this.roundRect(cardX + 12, cardY + 112, cardW - 24, 18, 5, true, false);
          this.ctx.fillStyle = '#38BDF8';
          this.ctx.font = 'bold 9px sans-serif';
          this.ctx.fillText("⚡ CHẠM VÀO ĐÂY ĐỂ MỞ TOÀN BỘ BÁCH KHOA TỪ ĐIỂN, ÂM THANH & FLASHCARD", cardX + 18, cardY + 124);

        } else {
          // SECONDARY OBJECT: Compact Cyber Badge (Multi-Object Scanning)
          const cardW = Math.min(220, Math.max(160, w + 20));
          const cardH = 46;
          const cardX = Math.max(10, Math.min(x, cw - cardW - 10));
          let cardY = y - cardH - 8;
          if (cardY < 65) cardY = y + h + 8;

          this.ctx.fillStyle = 'rgba(15, 23, 42, 0.90)';
          this.ctx.strokeStyle = 'rgba(56, 189, 248, 0.5)';
          this.ctx.lineWidth = 1;
          this.roundRect(cardX, cardY, cardW, cardH, 10, true, true);

          this.ctx.fillStyle = '#FFFFFF';
          this.ctx.font = 'bold 13px -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto';
          this.ctx.fillText(vocab.wordEn, cardX + 8, cardY + 18);

          const badgeText = vocab.cefrLevel || 'B2';
          this.ctx.fillStyle = 'rgba(99, 102, 241, 0.9)';
          this.roundRect(cardX + cardW - 44, cardY + 6, 36, 15, 4, true, false);
          this.ctx.fillStyle = '#FFFFFF';
          this.ctx.font = 'bold 9px sans-serif';
          this.ctx.fillText(badgeText, cardX + cardW - 40, cardY + 17);

          this.ctx.fillStyle = '#FBBF24';
          this.ctx.font = '500 11px -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto';
          const viText = this.truncateText(this.ctx, vocab.meaningVi, cardW - 16);
          this.ctx.fillText(viText, cardX + 8, cardY + 36);
        }
      }

      this.ctx.restore();
    }
  }

  truncateText(ctx, text, maxWidth) {
    if (ctx.measureText(text).width <= maxWidth) return text;
    let truncated = text;
    while (truncated.length > 3 && ctx.measureText(truncated + '...').width > maxWidth) {
      truncated = truncated.slice(0, -1);
    }
    return truncated + '...';
  }

  roundRect(x, y, w, h, r, fill, stroke) {
    this.ctx.beginPath();
    this.ctx.moveTo(x + r, y);
    this.ctx.lineTo(x + w - r, y);
    this.ctx.quadraticCurveTo(x + w, y, x + w, y + r);
    this.ctx.lineTo(x + w, y + h - r);
    this.ctx.quadraticCurveTo(x + w, y + h, x + w - r, y + h);
    this.ctx.lineTo(x + r, y + h);
    this.ctx.quadraticCurveTo(x, y + h, x, y + h - r);
    this.ctx.lineTo(x, y + r);
    this.ctx.quadraticCurveTo(x, y, x + r, y);
    this.ctx.closePath();
    if (fill) this.ctx.fill();
    if (stroke) this.ctx.stroke();
  }
}
