package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

import java.util.Objects;

public class Amount {
    public static final String NEGATIVE_AMOUNT_MSG = "금액은 음수 일 수 없습니다.";
    private Long amount;

    private Amount(final Long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(NEGATIVE_AMOUNT_MSG);
        }

        this.amount = amount;
    }

    public static Amount ZERO() {
        return new Amount(0L);
    }

    public static Amount of(Long amount) {
        return new Amount(amount);
    }

    public boolean isFree() {
        return amount == 0;
    }

    public boolean isNotSame(final Payment payment) {
        return !amount.equals(payment.amount());
    }

    public Long amount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount1 = (Amount) o;
        return Objects.equals(amount, amount1.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
