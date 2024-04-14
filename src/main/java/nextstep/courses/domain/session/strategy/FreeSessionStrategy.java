package nextstep.courses.domain.session.strategy;

import nextstep.courses.domain.session.EnrollmentCount;
import nextstep.payments.domain.Money;

public class FreeSessionStrategy implements SessionStrategy {

    @Override
    public boolean isPaymentSufficient(final Money paymentAmount) {
        return true;
    }

    @Override
    public boolean canEnrollMoreStudents(final EnrollmentCount currentEnrollmentCount) {
        return true;
    }
}
