package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

import static nextstep.courses.ExceptionMessage.INVALID_MAX_NUMBER_OF_ENROLLMENT_AND_FEE;

public class SessionType {
    private static final int MAX_NUMBER_OF_ENROLLMENT = Integer.MAX_VALUE;
    private static final Fee FEE_OF_FREE = new Fee(0L);

    protected int maxNumberOfEnrollment;
    protected Fee fee;

    public SessionType(int maxNumberOfEnrollment, long fee) {
        this(maxNumberOfEnrollment, new Fee(fee));
    }

    public SessionType(int maxNumberOfEnrollment, Fee fee) {
        validateSessionTypeInput(maxNumberOfEnrollment);
        this.maxNumberOfEnrollment = maxNumberOfEnrollment;
        this.fee = fee;
    }

    public static SessionType freeSessionType() {
        return new SessionType(MAX_NUMBER_OF_ENROLLMENT, FEE_OF_FREE);
    }

    protected void validateSessionTypeInput(int maxNumberOfEnrollment) {
        if (maxNumberOfEnrollment < 0) {
            throw new IllegalArgumentException(INVALID_MAX_NUMBER_OF_ENROLLMENT_AND_FEE.message());
        }
    }

    public boolean isEnrollmentPossible(int currentNumberOfEnrollment, Payment payment) {
        return isSessionNotFull(currentNumberOfEnrollment) && isValidPayment(payment);
    }

    private boolean isSessionNotFull(long currentNumberOfEnrollment) {
        if (fee.isFree()) {
            return true;
        }

        return currentNumberOfEnrollment < maxNumberOfEnrollment;
    }

    private boolean isValidPayment(Payment payment) {
        if (fee.isFree()) {
            return true;
        }

        return payment.isSameAmount(fee.getFee());
    }

    public int getMaxNumberOfEnrollment() {
        return maxNumberOfEnrollment;
    }

    public long getFee() {
        return fee.getFee();
    }
}
