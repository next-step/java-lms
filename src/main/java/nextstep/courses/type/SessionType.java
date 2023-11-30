package nextstep.courses.type;

public enum SessionType {
    FREE,
    PAID;

    public static boolean free(SessionType sessionType) {
        return sessionType == SessionType.FREE;
    }

    public static boolean paid(SessionType sessionType) {
        return sessionType == SessionType.PAID;
    }
}
