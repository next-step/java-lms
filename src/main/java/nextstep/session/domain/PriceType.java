package nextstep.session.domain;

public enum PriceType {
    FREE, PAID;

    public boolean isFree() {
        return this == FREE;
    }
}
