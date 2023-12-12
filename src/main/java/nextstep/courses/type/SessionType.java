package nextstep.courses.type;

public enum SessionType {
    FREE,
    PAID;

    public boolean isFree() {
        return this == SessionType.FREE;
    }

    public boolean isPaid() {
        return this == SessionType.PAID;
    }
}
