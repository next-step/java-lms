package nextstep.courses.domain;

import nextstep.courses.exception.IncorrectAmountException;
import nextstep.courses.exception.NotPositiveException;

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

    public void validateAmount(long payment) {
        if (!isSameAmount(payment)) {
            throw new IncorrectAmountException("결제 금액과 강의 금액이 다릅니다.");
        }
    }

    private boolean isSameAmount(long payment) {
        return this.amount == payment;
    }
}
