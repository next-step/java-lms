package nextstep.courses.domain.policy;

import nextstep.courses.domain.value.Capacity;
import nextstep.courses.domain.value.Money;

public class PaidSessionPolicy implements SessionPolicy {

    private final Money price;
    private final Capacity capacity;

    public PaidSessionPolicy(int price, int capacity) {
        this(new Money(price), new Capacity(capacity));
    }

    public PaidSessionPolicy(Money price, Capacity capacity) {
        this.price = price;
        this.capacity = capacity;
    }

    @Override
    public void validate(Money payment, int currentEnrollmentCount) {
        validatePayment(payment);
        validateEnrollmentCount(currentEnrollmentCount);
    }

    @Override
    public SessionType type() {
        return SessionType.PAID;
    }

    private void validatePayment(Money payment) {
        if (!price.equals(payment)) {
            throw new RuntimeException("결제 금액이 수강료와 일치하지 않습니다.");
        }
    }

    private void validateEnrollmentCount(int currentEnrollmentCount) {
        if (capacity.isFull(currentEnrollmentCount)) {
            throw new RuntimeException("수강 인원이 초과되었습니다.");
        }
    }

    @Override
    public Integer price() {
        return this.price.value();
    }

    @Override
    public Integer capacity() {
        return this.capacity.value();
    }
}
