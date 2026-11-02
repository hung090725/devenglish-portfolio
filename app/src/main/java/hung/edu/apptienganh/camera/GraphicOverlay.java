package hung.edu.apptienganh.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom AR Canvas Overlay rendering real-time bounding boxes,
 * Cyberpunk HUD reticles, laser scanning beam, tap-to-focus ripples,
 * leader pointer lines, active Quest HUD banner, and floating AR vocabulary tags
 * with Syllable Stress, CEFR C1 Academic mode, and Blind Memory Challenge mode.
 */
public class GraphicOverlay extends View {

    public static class ArTag {
        public String titleEn;
        public String ipa;
        public String meaningVi;
        public String sentenceEn;
        public String sentenceVi;
        public String academicC1TitleEn;
        public String academicC1MeaningVi;
        public RectF boundingBox;
        public float confidence;
        public boolean isBlindRevealed = false;

        public ArTag(String titleEn, String ipa, String meaningVi, String sentenceEn, String sentenceVi, RectF boundingBox, float confidence) {
            this.titleEn = titleEn;
            this.ipa = ipa;
            this.meaningVi = meaningVi;
            this.sentenceEn = sentenceEn;
            this.sentenceVi = sentenceVi;
            this.boundingBox = boundingBox;
            this.confidence = confidence;
        }

        public ArTag(String titleEn, String ipa, String meaningVi, String sentenceEn, RectF boundingBox, float confidence) {
            this(titleEn, ipa, meaningVi, sentenceEn, null, boundingBox, confidence);
        }

        public ArTag(String titleEn, String ipa, String meaningVi, RectF boundingBox, float confidence) {
            this(titleEn, ipa, meaningVi, null, null, boundingBox, confidence);
        }
    }

    private final List<ArTag> tags = new ArrayList<>();
    private final Object lock = new Object();

    // Source image dimensions for coordinate mapping
    private int imageWidth;
    private int imageHeight;
    private boolean isFlipped = false;

    // Paints
    private Paint boxPaint;
    private Paint boxCornerPaint;
    private Paint leaderLinePaint;
    private Paint bgPaint;
    private Paint textEnPaint;
    private Paint textIpaPaint;
    private Paint textViPaint;
    private Paint textSentenceEnPaint;
    private Paint textSentenceViPaint;
    private Paint scanLinePaint;
    private Paint focusRipplePaint;
    private Paint frozenBannerPaint;
    private Paint frozenTextPaint;
    private Paint questBannerBgPaint;
    private Paint questTextPaint;

    // Modes & Animation States
    private float scanLineY = 0f;
    private boolean scanDown = true;
    private boolean isScanningAnimationEnabled = true;
    private boolean isFrozen = false;
    private boolean isBlindModeEnabled = false;
    private boolean isAcademicC1ModeEnabled = false;
    private String activeQuestBannerText = null;

    // Tap-to-focus animation
    private float focusX = -1f;
    private float focusY = -1f;
    private float focusRadius = 0f;
    private boolean isFocusing = false;

    public GraphicOverlay(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        boxPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        boxPaint.setColor(Color.parseColor("#38BDF8")); // Cyan border
        boxPaint.setStyle(Paint.Style.STROKE);
        boxPaint.setStrokeWidth(3.5f);

        boxCornerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        boxCornerPaint.setColor(Color.parseColor("#00F0FF")); // Bright neon cyan
        boxCornerPaint.setStyle(Paint.Style.STROKE);
        boxCornerPaint.setStrokeWidth(6.5f);

        leaderLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        leaderLinePaint.setColor(Color.parseColor("#8038BDF8"));
        leaderLinePaint.setStyle(Paint.Style.STROKE);
        leaderLinePaint.setStrokeWidth(2.5f);
        leaderLinePaint.setPathEffect(new DashPathEffect(new float[]{10f, 10f}, 0));

        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(Color.parseColor("#EE0B1329")); // Deep modern dark navy slate (93% opacity)
        bgPaint.setStyle(Paint.Style.FILL);

        textEnPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textEnPaint.setColor(Color.WHITE);
        textEnPaint.setTextSize(32f);
        textEnPaint.setFakeBoldText(true);

        textIpaPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textIpaPaint.setColor(Color.parseColor("#38BDF8")); // Cyan IPA
        textIpaPaint.setTextSize(23f);

        textViPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textViPaint.setColor(Color.parseColor("#34D399")); // Emerald green Vietnamese
        textViPaint.setTextSize(25f);
        textViPaint.setFakeBoldText(true);

        textSentenceEnPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textSentenceEnPaint.setColor(Color.parseColor("#F1F5F9")); // Crisp light slate
        textSentenceEnPaint.setTextSize(21f);

        textSentenceViPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textSentenceViPaint.setColor(Color.parseColor("#94A3B8")); // Muted slate translation
        textSentenceViPaint.setTextSize(19f);

        scanLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        scanLinePaint.setColor(Color.parseColor("#8038BDF8")); // Neon Laser glow
        scanLinePaint.setStrokeWidth(5f);

        focusRipplePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        focusRipplePaint.setColor(Color.parseColor("#38BDF8"));
        focusRipplePaint.setStyle(Paint.Style.STROKE);
        focusRipplePaint.setStrokeWidth(4f);

        frozenBannerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        frozenBannerPaint.setColor(Color.parseColor("#CCEA580C")); // Amber/Orange badge
        frozenBannerPaint.setStyle(Paint.Style.FILL);

        frozenTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        frozenTextPaint.setColor(Color.WHITE);
        frozenTextPaint.setTextSize(22f);
        frozenTextPaint.setFakeBoldText(true);

        questBannerBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        questBannerBgPaint.setColor(Color.parseColor("#D92563EB")); // Deep Royal Blue badge
        questBannerBgPaint.setStyle(Paint.Style.FILL);

        questTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        questTextPaint.setColor(Color.WHITE);
        questTextPaint.setTextSize(22f);
        questTextPaint.setFakeBoldText(true);
    }

    public void setImageSourceInfo(int imageWidth, int imageHeight, boolean isFlipped) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.isFlipped = isFlipped;
    }

    public void clear() {
        synchronized (lock) {
            tags.clear();
        }
        postInvalidate();
    }

    public void setTags(List<ArTag> newTags) {
        synchronized (lock) {
            tags.clear();
            if (newTags != null) {
                tags.addAll(newTags);
            }
        }
        postInvalidate();
    }

    public void setScanningAnimationEnabled(boolean enabled) {
        this.isScanningAnimationEnabled = enabled;
        postInvalidate();
    }

    public void setFrozen(boolean frozen) {
        this.isFrozen = frozen;
        postInvalidate();
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setBlindModeEnabled(boolean blindModeEnabled) {
        this.isBlindModeEnabled = blindModeEnabled;
        postInvalidate();
    }

    public boolean isBlindModeEnabled() {
        return isBlindModeEnabled;
    }

    public void setAcademicC1ModeEnabled(boolean academicC1ModeEnabled) {
        this.isAcademicC1ModeEnabled = academicC1ModeEnabled;
        postInvalidate();
    }

    public void setActiveQuestBannerText(String text) {
        this.activeQuestBannerText = text;
        postInvalidate();
    }

    public void revealBlindTag() {
        synchronized (lock) {
            for (ArTag tag : tags) {
                tag.isBlindRevealed = true;
            }
        }
        postInvalidate();
    }

    public void triggerFocusAnimation(float x, float y) {
        this.focusX = x;
        this.focusY = y;
        this.focusRadius = 70f;
        this.isFocusing = true;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        if (width == 0 || height == 0) return;

        // 1. Draw animated laser scanning line (if not frozen)
        if (isScanningAnimationEnabled && !isFrozen) {
            float startY = height * 0.20f;
            float endY = height * 0.60f;

            if (scanLineY < startY) scanLineY = startY;

            canvas.drawLine(width * 0.10f, scanLineY, width * 0.90f, scanLineY, scanLinePaint);

            if (scanDown) {
                scanLineY += 7f;
                if (scanLineY > endY) scanDown = false;
            } else {
                scanLineY -= 7f;
                if (scanLineY < startY) scanDown = true;
            }
            postInvalidateDelayed(16); // ~60 FPS
        }

        // 2. Draw Active Quest Banner if active
        if (activeQuestBannerText != null && !activeQuestBannerText.isEmpty()) {
            float labelW = questTextPaint.measureText(activeQuestBannerText);
            float bannerW = Math.min(labelW + 44f, width - 40f);
            float bannerLeft = (width - bannerW) / 2f;
            RectF questRect = new RectF(bannerLeft, 115f, bannerLeft + bannerW, 165f);
            canvas.drawRoundRect(questRect, 25f, 25f, questBannerBgPaint);
            canvas.drawText(activeQuestBannerText, bannerLeft + 22f, 148f, questTextPaint);
        }

        // 3. Draw Frozen indicator badge if frame is paused
        if (isFrozen) {
            String frozenLabel = "⏸️ ĐÃ GIỮ HÌNH ĐỂ ĐỌC (CHẠM ĐỂ TIẾP TỤC)";
            float labelWidth = frozenTextPaint.measureText(frozenLabel);
            float bannerW = labelWidth + 40f;
            float bannerLeft = (width - bannerW) / 2f;
            RectF bannerRect = new RectF(bannerLeft, 175f, bannerLeft + bannerW, 225f);
            canvas.drawRoundRect(bannerRect, 25f, 25f, frozenBannerPaint);
            canvas.drawText(frozenLabel, bannerLeft + 20f, 207f, frozenTextPaint);
        }

        // 4. Draw focus ring animation if triggered
        if (isFocusing) {
            canvas.drawCircle(focusX, focusY, focusRadius, focusRipplePaint);
            focusRadius -= 4f;
            if (focusRadius <= 25f) {
                isFocusing = false;
            } else {
                postInvalidateDelayed(16);
            }
        }

        // 5. Draw AR tags
        synchronized (lock) {
            int tagIndex = 0;
            for (ArTag tag : tags) {
                drawArTag(canvas, tag, width, height, tagIndex++);
                if (tagIndex >= 3) break; // Limit to top 3 objects to prevent clutter on mobile screen
            }
        }
    }

    private RectF translateRect(RectF rect, int viewWidth, int viewHeight) {
        if (rect == null) return null;
        if (imageWidth <= 0 || imageHeight <= 0) return rect;

        float scaleX = (float) viewWidth / (float) imageWidth;
        float scaleY = (float) viewHeight / (float) imageHeight;

        float left = rect.left * scaleX;
        float top = rect.top * scaleY;
        float right = rect.right * scaleX;
        float bottom = rect.bottom * scaleY;

        if (isFlipped) {
            float flippedLeft = viewWidth - right;
            float flippedRight = viewWidth - left;
            left = flippedLeft;
            right = flippedRight;
        }

        return new RectF(left, top, right, bottom);
    }

    private void drawArTag(Canvas canvas, ArTag tag, int viewWidth, int viewHeight, int tagIndex) {
        RectF rawRect = tag.boundingBox;
        RectF rect = (rawRect != null) ? translateRect(rawRect, viewWidth, viewHeight) : null;

        if (rect == null) {
            // Default center reticle
            float cx = viewWidth / 2f;
            float cy = viewHeight * 0.40f;
            float half = Math.min(viewWidth, viewHeight) * 0.32f;
            rect = new RectF(cx - half, cy - half, cx + half, cy + half);
        }

        // Draw bounding box & corner markers
        canvas.drawRoundRect(rect, 20f, 20f, boxPaint);
        drawReticleCorners(canvas, rect);

        // Academic C1 title or Standard title
        String displayTitle = tag.titleEn;
        String displayMeaning = tag.meaningVi;
        if (isAcademicC1ModeEnabled && tag.academicC1TitleEn != null) {
            displayTitle = "🎓 " + tag.academicC1TitleEn;
            if (tag.academicC1MeaningVi != null) {
                displayMeaning = tag.academicC1MeaningVi;
            }
        }

        // Blind Memory Challenge mode (Ý tưởng 3)
        if (isBlindModeEnabled && !tag.isBlindRevealed) {
            displayTitle = "🔒 [???] Chạm mở từ vựng";
        }

        String line1 = "🎯 " + displayTitle + (tag.ipa != null ? "  " + tag.ipa : "");
        String line2 = "🇻🇳 " + displayMeaning;
        String line3 = (tag.sentenceEn != null && !tag.sentenceEn.isEmpty() && (!isBlindModeEnabled || tag.isBlindRevealed))
                ? "💬 " + truncateText(tag.sentenceEn, 38)
                : null;
        String line4 = (tag.sentenceVi != null && !tag.sentenceVi.isEmpty())
                ? "📝 " + truncateText(tag.sentenceVi, 38)
                : null;

        float paddingH = 20f;

        float w1 = textEnPaint.measureText(line1);
        float w2 = textViPaint.measureText(line2);
        float w3 = (line3 != null) ? textSentenceEnPaint.measureText(line3) : 0;
        float w4 = (line4 != null) ? textSentenceViPaint.measureText(line4) : 0;
        float maxContentWidth = Math.max(Math.max(w1, w2), Math.max(w3, w4));

        float cardWidth = Math.min(maxContentWidth + paddingH * 2, viewWidth - 40f);

        float cardHeight = 84f;
        if (line3 != null && line4 != null) {
            cardHeight = 146f;
        } else if (line3 != null || line4 != null) {
            cardHeight = 118f;
        }

        // Multi-tag offset staggering for multiple items
        float verticalOffset = tagIndex * 15f;

        // Position above the box, or below if too close to top
        float cardLeft = rect.centerX() - cardWidth / 2f;
        float cardTop = rect.top - cardHeight - 16f - verticalOffset;

        if (cardTop < 120f) {
            cardTop = rect.bottom + 16f + verticalOffset;
        }
        if (cardLeft < 20f) cardLeft = 20f;
        if (cardLeft + cardWidth > viewWidth - 20f) {
            cardLeft = viewWidth - cardWidth - 20f;
        }

        RectF cardRect = new RectF(cardLeft, cardTop, cardLeft + cardWidth, cardTop + cardHeight);

        // Draw leader pointer line from card to box center
        canvas.drawLine(cardRect.centerX(), cardRect.bottom, rect.centerX(), rect.top, leaderLinePaint);

        // Background chip with dark translucent styling
        canvas.drawRoundRect(cardRect, 18f, 18f, bgPaint);

        // Accent indicator bar on left
        Paint accentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        accentPaint.setColor(isBlindModeEnabled ? Color.parseColor("#F59E0B") : Color.parseColor("#38BDF8"));
        accentPaint.setStyle(Paint.Style.FILL);
        RectF accentRect = new RectF(cardLeft, cardTop + 12f, cardLeft + 6f, cardTop + cardHeight - 12f);
        canvas.drawRoundRect(accentRect, 3f, 3f, accentPaint);

        // Draw text lines
        float textX = cardLeft + paddingH;
        canvas.drawText(line1, textX, cardTop + 34f, textEnPaint);
        canvas.drawText(line2, textX, cardTop + 66f, textViPaint);

        if (line3 != null) {
            canvas.drawText(line3, textX, cardTop + 98f, textSentenceEnPaint);
        }
        if (line4 != null) {
            canvas.drawText(line4, textX, cardTop + 128f, textSentenceViPaint);
        }
    }

    private void drawReticleCorners(Canvas canvas, RectF r) {
        float len = 28f;
        // Top-left
        canvas.drawLine(r.left, r.top, r.left + len, r.top, boxCornerPaint);
        canvas.drawLine(r.left, r.top, r.left, r.top + len, boxCornerPaint);
        // Top-right
        canvas.drawLine(r.right, r.top, r.right - len, r.top, boxCornerPaint);
        canvas.drawLine(r.right, r.top, r.right, r.top + len, boxCornerPaint);
        // Bottom-left
        canvas.drawLine(r.left, r.bottom, r.left + len, r.bottom, boxCornerPaint);
        canvas.drawLine(r.left, r.bottom, r.left, r.bottom - len, boxCornerPaint);
        // Bottom-right
        canvas.drawLine(r.right, r.bottom, r.right - len, r.bottom, boxCornerPaint);
        canvas.drawLine(r.right, r.bottom, r.right, r.bottom - len, boxCornerPaint);
    }

    private String truncateText(String text, int maxChars) {
        if (text == null || text.length() <= maxChars) return text;
        return text.substring(0, maxChars - 3) + "...";
    }
}
