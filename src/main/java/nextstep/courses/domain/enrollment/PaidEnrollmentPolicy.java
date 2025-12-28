package nextstep.courses.domain.enrollment;

import nextstep.payments.domain.Payment;

public class PaidEnrollmentPolicy implements EnrollmentPolicy {

    private final Money money;

    public PaidEnrollmentPolicy(Money money) {
        this.money = money;
    }

    @Override
    public PolicyType type() {
        return PolicyType.PAID;
    }

    @Override
    public Long price() {
        return money.value();
    }

    @Override
    public void validateEnrollment(Payment payment) {
        validatePayment(payment);
    }

    private void validatePayment(Payment payment) {
        if (!payment.isSameAmount(money)) {
            throw new IllegalArgumentException();
        }
    }
}
