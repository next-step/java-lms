package nextstep.courses.domain.session.sessionType;

import nextstep.payments.domain.Payment;

public class FreeSessionType extends SessionType {
    private static final int MAX_NUMBER_OF_ENROLLMENT = Integer.MAX_VALUE;
    private static final long FEE_OF_FREE = 0;

    public FreeSessionType() {
        super(MAX_NUMBER_OF_ENROLLMENT, FEE_OF_FREE);
    }

    @Override
    public boolean isEnrollmentPossible(int currentNumberOfEnrollment, Payment payment) {
        return true;
    }
}
