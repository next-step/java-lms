package nextstep.courses.domain;

import nextstep.courses.exception.NegativeOrZeroNumberException;
import nextstep.courses.exception.PaymentAmountNotEqualException;
import nextstep.payments.domain.Payment;

public class Price {

    private final Long amount;

    public Price(Long amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(Long amount) {
        if (amount <= 0) {
            throw new NegativeOrZeroNumberException();
        }
    }

    public void validatePrice(Payment payment) {
        if (isNotEqualAmount(payment)) {
            throw new PaymentAmountNotEqualException();
        }
    }

    private boolean isNotEqualAmount(Payment payment) {
        return !payment.getAmount().equals(this.amount);
    }
}
