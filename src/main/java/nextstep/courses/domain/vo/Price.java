package nextstep.courses.domain.vo;

public class Price {
    public static final Price FREE = new Price(Money.ZERO);
    private Money price;

    public Price(Money price) {
        this.price = price;
    }

    public boolean paid(long amount) {
        return price.equals(new Money(amount));
    }
}
