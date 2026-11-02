package hung.edu.apptienganh.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "real_device_test_cases")
public class RealDeviceTestCase {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String category;
    private String status; // NOT_TESTED, PASS, FAIL, BLOCKED
    private String note;
    private long updatedAt;
    private int orderIndex;

    public RealDeviceTestCase() {
        this.status = "NOT_TESTED";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public long getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(long updatedAt) { this.updatedAt = updatedAt; }

    public int getOrderIndex() { return orderIndex; }
    public void setOrderIndex(int orderIndex) { this.orderIndex = orderIndex; }
}
