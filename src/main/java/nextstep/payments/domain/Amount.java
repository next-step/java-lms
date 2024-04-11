package nextstep.payments.domain;

import java.util.Objects;

import static java.util.Objects.isNull;
import static nextstep.payments.ExceptionMessage.INVALID_PAYMENT;

public class Amount {
    private final Long amount;

    public Amount() {
        this(0L);
    }

    public Amount(Long amount) {
        validateAmountInput(amount);
        this.amount = amount;
    }

    private void validateAmountInput(Long amount) {
        if (isNull(amount) || amount < 0) {
            throw new IllegalArgumentException(INVALID_PAYMENT.message());
        }
    }

    public boolean isSame(Long amount) {
        return Objects.equals(this.amount, amount);
    }
}
