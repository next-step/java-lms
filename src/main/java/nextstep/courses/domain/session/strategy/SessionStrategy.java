package nextstep.courses.domain.session.strategy;

import nextstep.courses.domain.session.EnrollmentCount;
import nextstep.payments.domain.Money;

public interface SessionStrategy {

    boolean isPaymentSufficient(final Money paymentAmount);

    boolean canEnrollMoreStudents(final EnrollmentCount currentEnrollmentCount);
}
