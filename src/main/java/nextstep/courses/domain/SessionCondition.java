package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

public class SessionCondition {
    private Long amount;
    private Long maxUserNumber;

    public SessionCondition() {
        this.amount = 0L;
        this.maxUserNumber = 0L;
    }

    public SessionCondition(Long amount, Long maxUserNumber) {
        this.amount = amount;
        this.maxUserNumber = maxUserNumber;
    }

    public void match(Payment payment, Long userNumber) throws CannotEnrollException {
        if (!payment.isSameAmount(amount)) {
            throw new CannotEnrollException("결제 금액이 일치하지 않습니다.");
        }
        if (isPaidSession()) {
            isFull(userNumber);
        }
    }

    private boolean isPaidSession() {
        return amount > 0;
    }

    private void isFull(Long userNumber) throws CannotEnrollException {
        if (compareToMax(userNumber)) {
            throw new CannotEnrollException("수강 인원을 초과했습니다.");
        }
    }

    private boolean compareToMax(Long userNumber) {
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
