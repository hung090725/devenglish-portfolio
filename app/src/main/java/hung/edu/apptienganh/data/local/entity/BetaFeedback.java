package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "beta_feedback")
public class BetaFeedback {
    @PrimaryKey(autoGenerate = true)
    private long id;
    
    private String category;
    private String screenName;
    private String description;
    private String stepsToReproduce;
    private String expectedResult;
    private String actualResult;
    private String severity;
    private String deviceInfo;
    private String appVersionName;
    private long appVersionCode;
    private long createdAt;
    private String status;

    public BetaFeedback(String category, String screenName, String description, 
                        String stepsToReproduce, String expectedResult, String actualResult, 
                        String severity, String deviceInfo, String appVersionName, 
                        long appVersionCode, long createdAt, String status) {
        this.category = category;
        this.screenName = screenName;
        this.description = description;
        this.stepsToReproduce = stepsToReproduce;
        this.expectedResult = expectedResult;
        this.actualResult = actualResult;
        this.severity = severity;
        this.deviceInfo = deviceInfo;
        this.appVersionName = appVersionName;
        this.appVersionCode = appVersionCode;
        this.createdAt = createdAt;
        this.status = status;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getScreenName() { return screenName; }
    public void setScreenName(String screenName) { this.screenName = screenName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getStepsToReproduce() { return stepsToReproduce; }
    public void setStepsToReproduce(String stepsToReproduce) { this.stepsToReproduce = stepsToReproduce; }
    
    public String getExpectedResult() { return expectedResult; }
    public void setExpectedResult(String expectedResult) { this.expectedResult = expectedResult; }
    
    public String getActualResult() { return actualResult; }
    public void setActualResult(String actualResult) { this.actualResult = actualResult; }
    
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    
    public String getDeviceInfo() { return deviceInfo; }
    public void setDeviceInfo(String deviceInfo) { this.deviceInfo = deviceInfo; }
    
    public String getAppVersionName() { return appVersionName; }
    public void setAppVersionName(String appVersionName) { this.appVersionName = appVersionName; }
    
    public long getAppVersionCode() { return appVersionCode; }
    public void setAppVersionCode(long appVersionCode) { this.appVersionCode = appVersionCode; }
    
    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
