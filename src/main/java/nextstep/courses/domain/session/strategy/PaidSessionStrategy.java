package nextstep.courses.domain.session.strategy;

public class PaidSessionStrategy implements SessionStrategy {

    private final Money fee;
    private final EnrollmentCount enrollmentLimit;

    public PaidSessionStrategy(final Money fee, final EnrollmentCount enrollmentLimit) {
        this.fee = fee;
        this.enrollmentLimit = enrollmentLimit;
    }

    @Override
    public boolean canEnroll(final Money payment, final EnrollmentCount currentEnrollmentCount) {
        return equalsFeeAndPayment(payment) && notExceedLimit(currentEnrollmentCount);
    }

    private boolean equalsFeeAndPayment(final Money payment) {
        return payment.equals(fee);
    }

    private boolean notExceedLimit(final EnrollmentCount currentEnrollmentCount) {
        return currentEnrollmentCount.compareTo(enrollmentLimit) < 0;
    }
}
