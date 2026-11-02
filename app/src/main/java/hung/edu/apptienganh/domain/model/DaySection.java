package hung.edu.apptienganh.domain.model;

public final class DaySection {

    public static final int OBJECTIVE = 0;
    public static final int PRONUNCIATION = 1;
    public static final int VOCABULARY = 2;
    public static final int GRAMMAR = 3;
    public static final int CONVERSATION = 4;
    public static final int LISTENING = 5;
    public static final int SPEAKING = 6;
    public static final int QUIZ = 7;
    public static final int COMPLETED = 8;

    public static final String STATUS_LOCKED = "LOCKED";
    public static final String STATUS_AVAILABLE = "AVAILABLE";
    public static final String STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String STATUS_COMPLETED = "COMPLETED";

    public static final String RATING_AGAIN = "AGAIN";
    public static final String RATING_HARD = "HARD";
    public static final String RATING_GOOD = "GOOD";
    public static final String RATING_EASY = "EASY";

    private DaySection() {}
}
