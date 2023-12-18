package nextstep.courses.domain.session;

public enum SessionType {
    FREE,
    PAID;

    public static boolean isFree(SessionType sessionType) {
        return sessionType == FREE;
    }

    public static boolean isPaid(SessionType sessionType) {
        return sessionType == PAID;
    }
}
