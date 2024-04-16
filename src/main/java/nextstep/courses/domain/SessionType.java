package nextstep.courses.domain;

public enum SessionType {
    FREE,
    PAID;

    public static boolean isPaid(SessionType type) {
        return PAID.equals(type);
    }
}
