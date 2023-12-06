package nextstep.courses.domain.session;

import nextstep.courses.exception.NegativeOrZeroNumberException;
import nextstep.courses.exception.PaymentAmountNotEqualException;

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

    public void validatePrice(Long amount) {
        if (isNotEqualAmount(amount)){
            throw new PaymentAmountNotEqualException();
        }
    }

    private boolean isNotEqualAmount(Long amount) {
        return !amount.equals(this.amount);
    }
}
