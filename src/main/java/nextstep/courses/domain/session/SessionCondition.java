package nextstep.courses.domain.session;

import nextstep.courses.CannotApproveException;
import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

import java.util.Objects;

public class SessionCondition {
    private final Long amount;
    private final Long maxUserNumber;
    private Long userNumber;

    public SessionCondition(Long amount, Long maxUserNumber, Long userNumber) {
        this.amount = amount;
        this.maxUserNumber = maxUserNumber;
        this.userNumber = userNumber;
    }

    public void match(Payment payment) throws CannotEnrollException {
        if (!payment.isSameAmount(amount)) {
            throw new CannotEnrollException("결제 금액이 일치하지 않습니다.");
        }
    }

    public void isFull() throws CannotApproveException {
        if (compareToMax()) {
            throw new CannotApproveException("인원을 추가로 승인할 수 없습니다.");
        }
    }

    private boolean compareToMax() {
        return userNumber.compareTo(maxUserNumber) >= 0;
    }

    public void addUserNumber() {
        userNumber++;
    }

    public Long amount() {
        return amount;
    }

    public Long maxUserNumber() {
        return maxUserNumber;
    }

    public Long userNumber() {
        return userNumber;
    }

    @Override
    public String toString() {
        return "SessionCondition{" +
                "amount=" + amount +
                ", maxUserNumber=" + maxUserNumber +
                ", userNumber=" + userNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionCondition that = (SessionCondition) o;
        return Objects.equals(amount, that.amount) && Objects.equals(maxUserNumber, that.maxUserNumber) && Objects.equals(userNumber, that.userNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, maxUserNumber, userNumber);
    }
}
