package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import static nextstep.courses.ExceptionMessage.INVALID_MAX_NUMBER_OF_ENROLLMENT_AND_FEE;

public class FreeSessionType extends SessionType {
    private static final int MAX_NUMBER_OF_ENROLLMENT = Integer.MAX_VALUE;
    private static final long FEE_OF_FREE = 0;

    public FreeSessionType() {
        super(MAX_NUMBER_OF_ENROLLMENT, FEE_OF_FREE);
    }

    @Override
    protected void validateSessionTypeInput(int maxNumberOfEnrollment, long price) {
        if (maxNumberOfEnrollment < 0 || fee < 0) {
            throw new IllegalArgumentException(INVALID_MAX_NUMBER_OF_ENROLLMENT_AND_FEE.message());
        }
    }

    @Override
    public boolean isSessionFull(long currentNumberOfEnrollment) {
        return true;
    }

    @Override
    public boolean isValidPayment(Payment payment) {
        return true;
    }
}
