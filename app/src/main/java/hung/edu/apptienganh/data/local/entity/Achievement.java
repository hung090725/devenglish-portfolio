package hung.edu.apptienganh.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "achievements")
public class Achievement {
    @PrimaryKey
    @NonNull
    private String code; // e.g. "A0_COMPLETED", "7_DAY_STREAK"
    private String title;
    private String description;
    private boolean isUnlocked;
    private long unlockedAt;

    public Achievement(@NonNull String code, String title, String description, boolean isUnlocked, long unlockedAt) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.isUnlocked = isUnlocked;
        this.unlockedAt = unlockedAt;
    }

    @NonNull
    public String getCode() { return code; }
    public void setCode(@NonNull String code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isUnlocked() { return isUnlocked; }
    public void setUnlocked(boolean unlocked) { isUnlocked = unlocked; }

    public long getUnlockedAt() { return unlockedAt; }
    public void setUnlockedAt(long unlockedAt) { this.unlockedAt = unlockedAt; }
}
