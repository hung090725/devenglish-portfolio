package hung.edu.apptienganh.domain.model;

public final class LevelConstants {

    public static final String A0 = "A0";
    public static final String A1 = "A1";
    public static final String A2 = "A2";
    public static final String B1 = "B1";
    public static final String B2 = "B2";
    public static final String C1 = "C1";

    public static final int A0_DAY_COUNT = 14;
    public static final int A1_DAY_COUNT = 14;
    public static final int A2_DAY_COUNT = 14;
    public static final int B1_DAY_COUNT = 14;
    public static final int B2_DAY_COUNT = 14;
    public static final int C1_DAY_COUNT = 14;

    /** Global day number shown in UI (A0: 1-14, A1: 15-28, A2: 29-42, B1: 43-56). */
    public static int globalDayNumber(String level, int dayIndex) {
        if (A1.equals(level)) {
            return dayIndex + A0_DAY_COUNT;
        }
        if (A2.equals(level)) {
            return dayIndex + A0_DAY_COUNT + A1_DAY_COUNT;
        }
        if (B1.equals(level)) {
            return dayIndex + A0_DAY_COUNT + A1_DAY_COUNT + A2_DAY_COUNT;
        }
        if (B2.equals(level)) {
            return dayIndex + A0_DAY_COUNT + A1_DAY_COUNT + A2_DAY_COUNT + B1_DAY_COUNT;
        }
        if (C1.equals(level)) {
            return dayIndex + A0_DAY_COUNT + A1_DAY_COUNT + A2_DAY_COUNT + B1_DAY_COUNT + B2_DAY_COUNT;
        }
        return dayIndex;
    }

    public static int totalDays() {
        return A0_DAY_COUNT + A1_DAY_COUNT + A2_DAY_COUNT + B1_DAY_COUNT + B2_DAY_COUNT + C1_DAY_COUNT;
    }

    public static String displayDayLabel(String level, int dayIndex) {
        return "Ngày " + globalDayNumber(level, dayIndex);
    }

    private LevelConstants() {}
}
