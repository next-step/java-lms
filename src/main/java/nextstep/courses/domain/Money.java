package nextstep.courses.domain;

public class Money {
    public static final Money FREE = new Money(0L);

    private final long amount;

    public Money(long amount) {
        this.amount = amount;
        validateInput();
    }

    private void validateInput() {
        if (amount < 0) {
            throw new IllegalArgumentException("금액이 0보다 작을 수 없습니다.");
        }
    }

    public long amount() {
        return amount;
    }
}
