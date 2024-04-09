package nextstep.courses.domain;

public enum SessionPayType {
    FREE,
    PAID;

    public boolean isPaid() {
        return this == PAID;
    }
}
