package hung.edu.apptienganh.camera;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CameraVisionAnalyzer implements ImageAnalysis.Analyzer {

    public static final int MODE_OBJECT = 0;
    public static final int MODE_TEXT = 1;

    public interface OnVisionResultListener {
        void onObjectDetected(VisualWordInfo info, List<GraphicOverlay.ArTag> tags);
        void onTextDetected(String fullText, VisualWordInfo info, List<GraphicOverlay.ArTag> tags);
        void onNoResult();
    }

    private final ImageLabeler labeler;
    private final TextRecognizer textRecognizer;
    private final OnVisionResultListener listener;
    private GraphicOverlay graphicOverlay;

    private int currentMode = MODE_OBJECT;
    private boolean isFrozen = false;
    private long lastAnalyzedTimestamp = 0;
    private static final long THROTTLE_INTERVAL_MS = 220; // 220ms for super responsive live tracking

    public CameraVisionAnalyzer(OnVisionResultListener listener) {
        this.listener = listener;

        ImageLabelerOptions labelerOptions = new ImageLabelerOptions.Builder()
                .setConfidenceThreshold(0.38f)
                .build();
        this.labeler = ImageLabeling.getClient(labelerOptions);
        this.textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
    }

    public void setGraphicOverlay(GraphicOverlay graphicOverlay) {
        this.graphicOverlay = graphicOverlay;
    }

    public void setMode(int mode) {
        this.currentMode = mode;
    }

    public int getMode() {
        return currentMode;
    }

    public void setFrozen(boolean frozen) {
        this.isFrozen = frozen;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    @Override
    @SuppressLint("UnsafeOptInUsageError")
    public void analyze(@NonNull ImageProxy imageProxy) {
        if (isFrozen) {
            imageProxy.close();
            return;
        }

        long currentTimestamp = System.currentTimeMillis();

        Image mediaImage = imageProxy.getImage();
        if (mediaImage == null) {
            imageProxy.close();
            return;
        }

        if (currentTimestamp - lastAnalyzedTimestamp < THROTTLE_INTERVAL_MS) {
            imageProxy.close();
            return;
        }

        int rotationDegrees = imageProxy.getImageInfo().getRotationDegrees();
        InputImage image = InputImage.fromMediaImage(mediaImage, rotationDegrees);

        if (graphicOverlay != null) {
            int width = (rotationDegrees == 90 || rotationDegrees == 270) ? image.getHeight() : image.getWidth();
            int height = (rotationDegrees == 90 || rotationDegrees == 270) ? image.getWidth() : image.getHeight();
            graphicOverlay.setImageSourceInfo(width, height, false);
        }

        if (currentMode == MODE_OBJECT) {
            analyzeObject(image, imageProxy, currentTimestamp);
        } else {
            analyzeText(image, imageProxy, currentTimestamp);
        }
    }

    private void analyzeObject(InputImage image, ImageProxy imageProxy, long timestamp) {
        labeler.process(image)
                .addOnSuccessListener(labels -> {
                    lastAnalyzedTimestamp = timestamp;
                    handleObjectLabels(labels, image.getWidth(), image.getHeight());
                })
                .addOnFailureListener(e -> {})
                .addOnCompleteListener(task -> imageProxy.close());
    }

    private void handleObjectLabels(List<ImageLabel> labels, int imgWidth, int imgHeight) {
        if (labels == null || labels.isEmpty()) {
            if (listener != null) listener.onNoResult();
            return;
        }

        // Sort descending by confidence
        List<ImageLabel> sortedLabels = new ArrayList<>(labels);
        Collections.sort(sortedLabels, (a, b) -> Float.compare(b.getConfidence(), a.getConfidence()));

        List<GraphicOverlay.ArTag> tags = new ArrayList<>();
        Set<String> seenWords = new HashSet<>();
        VisualWordInfo primaryWord = null;

        float cx = imgWidth > 0 ? imgWidth / 2f : 540f;
        float cy = imgHeight > 0 ? imgHeight * 0.42f : 800f;
        float halfSize = Math.min(imgWidth, imgHeight) > 0 ? Math.min(imgWidth, imgHeight) * 0.28f : 240f;

        for (ImageLabel label : sortedLabels) {
            if (label.getConfidence() < 0.38f) continue;

            VisualWordInfo wordInfo = VisualVocabularyDatabase.lookup(label.getText(), label.getConfidence());
            if (wordInfo != null) {
                String normalized = wordInfo.getWordEn().toLowerCase();
                if (seenWords.contains(normalized)) continue;
                seenWords.add(normalized);

                if (primaryWord == null) {
                    primaryWord = wordInfo;
                }

                // Center reticle box for primary object; offset slightly for secondary
                float offset = (tags.size()) * 20f;
                RectF box = new RectF(cx - halfSize + offset, cy - halfSize + offset, cx + halfSize + offset, cy + halfSize + offset);

                GraphicOverlay.ArTag arTag = new GraphicOverlay.ArTag(
                        wordInfo.getWordEn(),
                        wordInfo.getIpa(),
                        wordInfo.getMeaningVi(),
                        wordInfo.getExampleSentenceEn(),
                        wordInfo.getExampleSentenceVi(),
                        box,
                        label.getConfidence()
                );
                arTag.academicC1TitleEn = wordInfo.getAcademicC1WordEn();
                arTag.academicC1MeaningVi = wordInfo.getAcademicC1MeaningVi();

                tags.add(arTag);

                if (tags.size() >= 2) break; // Multi-object limit to avoid screen overlap
            }
        }

        if (primaryWord != null && listener != null) {
            listener.onObjectDetected(primaryWord, tags);
        } else if (listener != null) {
            listener.onNoResult();
        }
    }

    private void analyzeText(InputImage image, ImageProxy imageProxy, long timestamp) {
        textRecognizer.process(image)
                .addOnSuccessListener(visionText -> {
                    lastAnalyzedTimestamp = timestamp;
                    handleRecognizedText(visionText);
                })
                .addOnFailureListener(e -> {})
                .addOnCompleteListener(task -> imageProxy.close());
    }

    private void handleRecognizedText(Text visionText) {
        if (visionText == null || visionText.getTextBlocks().isEmpty()) {
            if (listener != null) listener.onNoResult();
            return;
        }

        String fullText = visionText.getText().trim();
        List<GraphicOverlay.ArTag> tags = new ArrayList<>();
        Set<String> seenWords = new HashSet<>();
        VisualWordInfo primaryWord = null;

        for (Text.TextBlock block : visionText.getTextBlocks()) {
            Rect boundingBox = block.getBoundingBox();
            String blockText = block.getText().trim();

            if (!blockText.isEmpty()) {
                String[] words = blockText.split("[^a-zA-Z]+");
                for (String w : words) {
                    if (w.length() >= 3) {
                        String lowerW = w.toLowerCase();
                        if (seenWords.contains(lowerW)) continue;

                        VisualWordInfo info = VisualVocabularyDatabase.lookup(w, 0.90f);
                        seenWords.add(lowerW);

                        if (primaryWord == null) primaryWord = info;

                        RectF rectF = (boundingBox != null)
                                ? new RectF(boundingBox)
                                : new RectF(200f, 400f, 700f, 600f);

                        GraphicOverlay.ArTag arTag = new GraphicOverlay.ArTag(
                                info.getWordEn(),
                                info.getIpa(),
                                info.getMeaningVi(),
                                info.getExampleSentenceEn(),
                                info.getExampleSentenceVi(),
                                rectF,
                                0.92f
                        );
                        arTag.academicC1TitleEn = info.getAcademicC1WordEn();
                        arTag.academicC1MeaningVi = info.getAcademicC1MeaningVi();

                        tags.add(arTag);
                        break;
                    }
                }
            }
            if (tags.size() >= 3) break; // Top 3 text blocks
        }

        if (primaryWord != null && listener != null) {
            listener.onTextDetected(fullText, primaryWord, tags);
        } else if (listener != null) {
            listener.onNoResult();
        }
    }
}
