package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;

public class PaidSession extends Session {
    public static final String INVALID_FEE_MESSAGE = "결제한 금액과 강의의 금액이 다릅니다.";

    private final SessionFee sessionFee;
    private final EnrollmentCount enrollmentCount;

    public PaidSession(final Long id, final String title, final SessionPeriod sessionPeriod, final nextstep.courses.domain.session.SessionState sessionState, final CoverImage coverImage, final SessionFee sessionFee, final int enrollmentCount) {
        super(id, title, sessionPeriod, sessionState, coverImage);
        this.sessionFee = sessionFee;
        this.enrollmentCount = new EnrollmentCount(enrollmentCount);
    }

    @Override
    public void enroll(final Payment payment) {
        checkEnrollmentAvailable(payment);
        validateSessionState();
        enrollmentCount.decrease();
    }

    private void checkEnrollmentAvailable(final Payment payment) {
        if (enrollmentCount.isNoRemaining()) {
            throw new IllegalArgumentException(NO_AVAILABLE_SEATS_MESSAGE);
        }
        if (sessionFee.isNotEqualTo(payment)) {
            throw new IllegalArgumentException(INVALID_FEE_MESSAGE);
        }
    }
}
