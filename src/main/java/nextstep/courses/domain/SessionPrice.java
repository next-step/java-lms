package nextstep.courses.domain;

public class SessionPrice {
    private final Long price;

    public SessionPrice(Long price) {
        if (price < 0L) {
            throw new IllegalArgumentException("가격은 0원 이상이어야 합니다.");
        }
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public SessionPayType getPayType() {
        if (price == 0L) {
            return SessionPayType.FREE;
        }
        return SessionPayType.PAID;
    }
}
