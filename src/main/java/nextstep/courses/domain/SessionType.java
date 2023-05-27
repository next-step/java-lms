package nextstep.courses.domain;

public enum SessionType {
    PAID,
    FREE;

    public boolean isPaid() {
        return PAID == this;
    }
}
