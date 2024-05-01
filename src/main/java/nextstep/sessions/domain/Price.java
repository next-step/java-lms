package nextstep.sessions.domain;

public class Price {
    public static final int ZERO = 0;
    private final int price;

    public Price(int price) {
        validatePositive(price);
        this.price = price;
    }

    public boolean equals(int price) {
        return this.price == price;
    }
    private void validatePositive(int number) {
        if (number < ZERO) {
            throw new IllegalArgumentException("0보다 작은 수가 올 수 없습니다.");
        }
    }
}
