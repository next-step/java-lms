package nextstep.courses.domain;

public enum SessionType {
    FREE,
    PAID;

    public boolean isFree() {
        return SessionType.FREE == this;
    }
}
