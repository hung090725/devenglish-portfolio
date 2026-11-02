package hung.edu.apptienganh.engine.audit.content.review;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentReviewExporter {

    public ContentReviewExportResult exportToMarkdown(List<ContentReviewItem> items, String directoryPath) {
        if (items == null || items.isEmpty()) {
            return new ContentReviewExportResult(true, null, 0, items, "No items to export.");
        }
        
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        File file = new File(dir, "CONTENT_REVIEW_EXPORT.md");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("# CONTENT REVIEW EXPORT\n\n");
            writer.write("| Review ID | Type | Level | Field | Issue | Suggested Action | Confidence | Status | Current Text |\n");
            writer.write("|---|---|---|---|---|---|---|---|---|\n");
            
            for (ContentReviewItem item : items) {
                writer.write(String.format("| %s | %s | %s | %s | %s | %s | %s | %s | %s |\n",
                        item.getReviewId(), item.getType(), item.getLevel(), item.getFieldName(),
                        item.getIssue(), item.getSuggestedAction(), item.getConfidence(), item.getStatus(),
                        escapeMd(item.getCurrentText())));
            }
            return new ContentReviewExportResult(true, file.getAbsolutePath(), items.size(), items, null);
        } catch (IOException e) {
            return new ContentReviewExportResult(false, null, 0, items, e.getMessage());
        }
    }

    public ContentReviewExportResult exportToJson(List<ContentReviewItem> items, String directoryPath) {
        if (items == null || items.isEmpty()) {
            return new ContentReviewExportResult(true, null, 0, items, "No items to export.");
        }
        
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        File file = new File(dir, "content_review_items.json");
        try (FileWriter writer = new FileWriter(file)) {
            JSONArray array = new JSONArray();
            for (ContentReviewItem item : items) {
                JSONObject obj = new JSONObject();
                obj.put("reviewId", item.getReviewId());
                obj.put("type", item.getType());
                obj.put("level", item.getLevel());
                obj.put("sourceId", item.getSourceId());
                obj.put("fieldName", item.getFieldName());
                obj.put("currentText", item.getCurrentText());
                obj.put("issue", item.getIssue());
                obj.put("suggestedAction", item.getSuggestedAction());
                obj.put("confidence", item.getConfidence());
                obj.put("status", item.getStatus());
                array.put(obj);
            }
            writer.write(array.toString(4));
            return new ContentReviewExportResult(true, file.getAbsolutePath(), items.size(), items, null);
        } catch (IOException | JSONException e) {
            return new ContentReviewExportResult(false, null, 0, items, e.getMessage());
        }
    }
    
    private String escapeMd(String text) {
        if (text == null) return "";
        return text.replace("\n", " ").replace("|", "\\|");
    }
}
