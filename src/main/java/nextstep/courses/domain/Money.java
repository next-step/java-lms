package nextstep.courses.domain;

public class Money {

    private final long amount;

    public Money(long amount) {
        this.amount = amount;
    }

    public boolean equal(Money amount) {
        return this.amount == amount.amount;
    }
}
