package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

public class SessionPaymentInfo {
    private Long amount;
    private Long maxUser;
    private Long userNumber;

    public SessionPaymentInfo(Long amount, Long maxUser, Long userNumber) {
        this.amount = amount;
        this.maxUser = maxUser;
        this.userNumber = userNumber;
    }

    public void checkPaidSession(Payment payment) throws CannotEnrollException {
        if (isPaidSession()) {
            validatePaidSession(payment);
        }
    }

    private boolean isPaidSession() {
        return amount > 0;
    }

    private void validatePaidSession(Payment payment) throws CannotEnrollException {
        if (isFull()) {
            throw new CannotEnrollException("수강 인원을 초과했습니다.");
        }
        if (!payment.isSameAmount(amount)) {
            throw new CannotEnrollException("결제 금액이 일치하지 않습니다.");
        }
    }

    private boolean isFull() {
        return userNumber >= maxUser;
    }
}
