package nextstep.courses.domain.session.sessionType;

import nextstep.payments.domain.Payment;

import static nextstep.courses.ExceptionMessage.INVALID_MAX_NUMBER_OF_ENROLLMENT_AND_FEE;

public abstract class SessionType {
    protected int maxNumberOfEnrollment;
    protected long fee;

    public SessionType(int maxNumberOfEnrollment, long fee) {
        validateSessionTypeInput(maxNumberOfEnrollment, fee);
        this.maxNumberOfEnrollment = maxNumberOfEnrollment;
        this.fee = fee;
    }

    protected void validateSessionTypeInput(int maxNumberOfEnrollment, long price) {
        if (maxNumberOfEnrollment < 0 || fee < 0) {
            throw new IllegalArgumentException(INVALID_MAX_NUMBER_OF_ENROLLMENT_AND_FEE.message());
        }
    }

    public abstract boolean isSessionNotFull(long currentNumberOfEnrollment);

    public abstract boolean isValidPayment(Payment payment);
}
