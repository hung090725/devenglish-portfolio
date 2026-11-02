package hung.edu.apptienganh.engine.audit.content.review;

import java.util.List;

public class ContentReviewExportResult {
    private boolean isSuccess;
    private String exportPath;
    private int totalItemsExported;
    private List<ContentReviewItem> items;
    private String errorMessage;

    public ContentReviewExportResult(boolean isSuccess, String exportPath, int totalItemsExported, List<ContentReviewItem> items, String errorMessage) {
        this.isSuccess = isSuccess;
        this.exportPath = exportPath;
        this.totalItemsExported = totalItemsExported;
        this.items = items;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() { return isSuccess; }
    public String getExportPath() { return exportPath; }
    public int getTotalItemsExported() { return totalItemsExported; }
    public List<ContentReviewItem> getItems() { return items; }
    public String getErrorMessage() { return errorMessage; }
}
