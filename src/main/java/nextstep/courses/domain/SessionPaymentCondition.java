package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

public class SessionPaymentCondition {
    private Long amount;
    private Long maxUser;

    public SessionPaymentCondition() {
        this.amount = 0L;
        this.maxUser = 0L;
    }

    public SessionPaymentCondition(Long amount, Long maxUser) {
        this.amount = amount;
        this.maxUser = maxUser;
    }

    public void checkPaidSession(Payment payment, Long userNumber) throws CannotEnrollException {
        if (!payment.isSameAmount(amount)) {
            throw new CannotEnrollException("결제 금액이 일치하지 않습니다.");
        }
        if (isPaidSession()) {
            validatePaidSession(payment, userNumber);
        }
    }

    private boolean isPaidSession() {
        return amount > 0;
    }

    private void validatePaidSession(Payment payment, Long userNumber) throws CannotEnrollException {
        if (isFull(userNumber)) {
            throw new CannotEnrollException("수강 인원을 초과했습니다.");
        }
    }

    private boolean isFull(Long userNumber) {
        return userNumber.compareTo(maxUser) >= 0;
    }

    public Long amount() {
        return amount;
    }

    public Long maxUser() {
        return maxUser;
    }
}
