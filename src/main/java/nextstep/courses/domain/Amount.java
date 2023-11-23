package nextstep.courses.domain;

import nextstep.courses.NotPositiveException;

public class Amount {

    public static final int MIN_AMOUNT = 1;
    private final int amount;

    public Amount(int amount) {
        if (isPositive(amount)) {
            throw new NotPositiveException("금액은 양수만 가능합니다.");
        }

        this.amount = amount;
    }

    private static boolean isPositive(int amount) {
        return amount < MIN_AMOUNT;
    }
}
