package hung.edu.apptienganh.ai;

public class AiMessage {

    public static final String SENDER_USER = "user";
    public static final String SENDER_AI = "ai";

    private String sender;
    private String text;
    private String feedbackVi;
    private long timestamp;

    public AiMessage() {
        this.timestamp = System.currentTimeMillis();
    }

    public AiMessage(String sender, String text) {
        this.sender = sender;
        this.text = text;
        this.timestamp = System.currentTimeMillis();
    }

    public AiMessage(String sender, String text, String feedbackVi) {
        this.sender = sender;
        this.text = text;
        this.feedbackVi = feedbackVi;
        this.timestamp = System.currentTimeMillis();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFeedbackVi() {
        return feedbackVi;
    }

    public void setFeedbackVi(String feedbackVi) {
        this.feedbackVi = feedbackVi;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isUser() {
        return SENDER_USER.equalsIgnoreCase(sender);
    }
}
