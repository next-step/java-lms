package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class PaidEnrollmentPolicy implements EnrollmentPolicy {

    private final Money money;
    private final Capacity capacity;

    public PaidEnrollmentPolicy(Money money, Capacity capacity) {
        this.money = money;
        this.capacity = capacity;
    }

    @Override
    public void validateEnrollment(Payment payment) {
        capacity.validateAvailable();
        validatePayment(payment);
    }

    private void validatePayment(Payment payment) {
        if (!payment.isSameAmount(money)) {
            throw new IllegalArgumentException();
        }
    }
}
