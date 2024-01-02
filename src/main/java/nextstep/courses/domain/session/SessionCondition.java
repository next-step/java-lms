package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

public class SessionCondition {
    private Long amount;
    private Long maxUserNumber;

    public SessionCondition(Long amount, Long maxUserNumber) {
        this.amount = amount;
        this.maxUserNumber = maxUserNumber;
    }

    public void match(Payment payment) throws CannotEnrollException {
        if (!payment.isSameAmount(amount)) {
            throw new CannotEnrollException("결제 금액이 일치하지 않습니다.");
        }
    }

    public boolean compareToMax(Long userNumber) {
        return userNumber.compareTo(maxUserNumber) >= 0;
    }

    public Long amount() {
        return amount;
    }

    public Long maxUserNumber() {
        return maxUserNumber;
    }

    @Override
    public String toString() {
        return "SessionPaymentCondition{" +
                "amount=" + amount +
                ", maxUser=" + maxUserNumber +
                '}';
    }
}
