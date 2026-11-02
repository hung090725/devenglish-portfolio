package hung.edu.apptienganh.qa;

public class SelfTestResult {
    private String testName;
    private SelfTestStatus status;
    private String message;

    public SelfTestResult(String testName, SelfTestStatus status, String message) {
        this.testName = testName;
        this.status = status;
        this.message = message;
    }

    public String getTestName() {
        return testName;
    }

    public SelfTestStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message != null ? message : "";
    }
}
