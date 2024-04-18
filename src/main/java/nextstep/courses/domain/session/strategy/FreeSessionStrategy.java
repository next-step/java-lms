package nextstep.courses.domain.session.strategy;

import nextstep.courses.domain.session.EnrollmentCount;
import nextstep.payments.domain.Money;

public class FreeSessionStrategy implements SessionStrategy {

    public static final String STRATEGY_NAME = "무료";

    private final Money fee;
    private final EnrollmentCount enrollmentLimit;

    public FreeSessionStrategy() {
        this.fee = new Money(0);
        this.enrollmentLimit = new EnrollmentCount(Integer.MAX_VALUE);
    }

    @Override
    public boolean isPaymentSufficient(final Money paymentAmount) {
        return true;
    }

    @Override
    public boolean canEnrollMoreStudents(final EnrollmentCount currentEnrollmentCount) {
        return true;
    }

    @Override
    public String name() {
        return STRATEGY_NAME;
    }

    @Override
    public int fee() {
        return this.fee.value();
    }

    @Override
    public int enrollmentLimit() {
        return this.enrollmentLimit.value();
    }
}
