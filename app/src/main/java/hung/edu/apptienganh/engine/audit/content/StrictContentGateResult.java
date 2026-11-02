package hung.edu.apptienganh.engine.audit.content;

import java.util.ArrayList;
import java.util.List;

public class StrictContentGateResult {
    public enum Status {
        PASS,
        PASS_WITH_WARNINGS,
        FAIL
    }
    
    private Status status;
    private int criticalIssueCount;
    private int warningIssueCount;
    private List<String> errorMessages = new ArrayList<>();
    
    public StrictContentGateResult() {
        this.status = Status.PASS;
    }
    
    public void addIssue(boolean isCritical, String message) {
        if (isCritical) {
            this.criticalIssueCount++;
            this.status = Status.FAIL;
        } else {
            this.warningIssueCount++;
            if (this.status == Status.PASS) {
                this.status = Status.PASS_WITH_WARNINGS;
            }
        }
        this.errorMessages.add((isCritical ? "[CRITICAL] " : "[WARNING] ") + message);
    }
    
    public Status getStatus() { return status; }
    public int getCriticalIssueCount() { return criticalIssueCount; }
    public int getWarningIssueCount() { return warningIssueCount; }
    public List<String> getErrorMessages() { return errorMessages; }
}
