package nextstep.courses.domain.session.strategy;

import nextstep.courses.domain.session.EnrollmentCount;
import nextstep.payments.domain.Money;

public class PaidSessionStrategy implements SessionStrategy {

    private static final Money PAID_SESSION_MINIMUM_MONEY = new Money(1);
    private static final EnrollmentCount PAID_SESSION_MINIMUM_ENROLLMENT_COUNT = new EnrollmentCount(1);

    private final Money fee;
    private final EnrollmentCount enrollmentLimit;

    public PaidSessionStrategy(final Money fee, final EnrollmentCount enrollmentLimit) {
        validateFeeExceedMinimum(fee);
        validateEnrollmentLimitExceedMinimum(enrollmentLimit);

        this.fee = fee;
        this.enrollmentLimit = enrollmentLimit;
    }

    private void validateFeeExceedMinimum(final Money fee) {
        if (fee.isLessThan(PAID_SESSION_MINIMUM_MONEY)) {
            throw new IllegalArgumentException("수강료는 최소 수강료 미만일 수 없습니다. 수강료: " + fee);
        }
    }

    private void validateEnrollmentLimitExceedMinimum(final EnrollmentCount enrollmentLimit) {
        if (enrollmentLimit.isLessThan(PAID_SESSION_MINIMUM_ENROLLMENT_COUNT)) {
            throw new IllegalArgumentException("수강 인원 제한은 최소 인원 제한 미만일 수 없습니다. 인원: " + enrollmentLimit);
        }
    }

    @Override
    public boolean isPaymentSufficient(final Money paymentAmount) {
        return paymentAmount.equals(fee);
    }

    @Override
    public boolean canEnrollMoreStudents(final EnrollmentCount currentEnrollmentCount) {
        return currentEnrollmentCount.isLessThan(enrollmentLimit);
    }
}
