package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

public class Amount {
    public static final String NEGATIVE_AMOUNT_MSG = "금액은 음수 일 수 없습니다.";
    private final Long amount;

    public Amount(final Long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(NEGATIVE_AMOUNT_MSG);
        }

        this.amount = amount;
    }

    public boolean isFree() {
        return amount == 0;
    }

    public boolean isNotSame(final Payment payment) {
        return amount != payment.amount();
    }
}
