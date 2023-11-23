package nextstep.courses.domain;

import nextstep.courses.NotPositiveException;
import nextstep.payments.domain.Payment;

public class Amount {

    public static final long MIN_AMOUNT = 1L;
    private final long amount;

    public Amount(long amount) {
        if (isPositive(amount)) {
            throw new NotPositiveException("금액은 양수만 가능합니다.");
        }

        this.amount = amount;
    }

    private static boolean isPositive(long amount) {
        return amount < MIN_AMOUNT;
    }

    public boolean isSameAmount(Payment payment) {
        return payment.isSameAmount(amount);
    }
}
