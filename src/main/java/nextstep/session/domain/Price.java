package nextstep.session.domain;

public class Price {

    public final long price;

    public Price(long price) {
        this.price = price;
    }

    public boolean isFullyPaid(long payAmount) {
        return price == payAmount;
    }
}
