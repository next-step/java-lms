package nextstep.courses.domain;

public enum SessionType {
    FREE("무료"),
    PAID("유료"),
    ;

    private final String status;

    SessionType(String status) {
        this.status = status;
    }

    public static SessionType from(String value) {
        try {
            return SessionType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 SessionType: " + value);
        }
    }

    public boolean isPaid() {
        return this == PAID;
    }

    public boolean isFree() {
        return this == FREE;
    }
}
