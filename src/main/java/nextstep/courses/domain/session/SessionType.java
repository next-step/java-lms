package nextstep.courses.domain.session;

public enum SessionType {
    PAID,
    FREE;

    public boolean isPaid() {
        return PAID == this;
    }
}
