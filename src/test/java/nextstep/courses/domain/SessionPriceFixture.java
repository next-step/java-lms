package nextstep.courses.domain;

public enum SessionPriceFixture {
    TDD_SESSION_PRICE(800_000);

    private final int price;

    SessionPriceFixture(int price) {
        this.price = price;
    }

    public SessionPrice sessionPrice() {
        return new SessionPrice(price);
    }
}
