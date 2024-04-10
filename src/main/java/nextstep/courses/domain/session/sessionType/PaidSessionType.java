package nextstep.courses.domain.session.sessionType;

import nextstep.payments.domain.Payment;

import static nextstep.courses.ExceptionMessage.INVALID_MAX_NUMBER_OF_ENROLLMENT_AND_FEE;

public class PaidSessionType extends SessionType {
    public PaidSessionType(int maxNumberOfEnrollment, long fee) {
        super(maxNumberOfEnrollment, fee);
    }

    @Override
    protected void validateSessionTypeInput(int maxNumberOfEnrollment, long fee) {
        if (maxNumberOfEnrollment <= 0 || fee <= 0) {
            throw new IllegalArgumentException(INVALID_MAX_NUMBER_OF_ENROLLMENT_AND_FEE.message());
        }
    }

    @Override
    public boolean isSessionFull(long numberOfCurrentEnrollment) {
        return numberOfCurrentEnrollment < maxNumberOfEnrollment;
    }

    @Override
    public boolean isValidPayment(Payment payment) {
        return payment.amount() == fee;
    }
}
