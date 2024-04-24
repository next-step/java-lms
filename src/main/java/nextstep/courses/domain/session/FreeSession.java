package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.List;

public class FreeSession extends Session {
    public static final Long FREE_FEE = 0L;

    private FreeSession(Long sessionId, SessionPeriod sessionPeriod, List<CoverImage> coverImages,
                        SessionStatusEnum sessionStatus, int numberOfStudents, boolean isOpenForEnrollment,
                        Long fee) {
        super(sessionId, sessionPeriod, coverImages, sessionStatus, numberOfStudents, isOpenForEnrollment, fee);
    }

    public static FreeSession of(Long sessionId, SessionPeriod sessionPeriod, List<CoverImage> coverImages,
                                 SessionStatusEnum sessionStatus, int numberOfStudents, boolean isOpenForEnrollment) {
        return new FreeSession(sessionId, sessionPeriod, coverImages, sessionStatus, numberOfStudents, isOpenForEnrollment, FREE_FEE);
    }

    @Override
    public void enrollStudent(NsUser user, Payment payment) {
        assertAllConditions(user, payment);
        registerSession(user, payment);
    }

    private void assertAllConditions(NsUser user, Payment payment) {
        if (!this.isOpenForEnrollment()) {
            throw new IllegalArgumentException(SESSION_NOT_OPENED);
        }

        if (isPaymentAmountNotMatching(payment, fee)) {
            throw new IllegalArgumentException(PAYMENT_NOT_MATCHING);
        }

        if (hasAlreadyEnrolled(user, this)) {
            throw new IllegalArgumentException(ENROLLMENT_ALREADY_DONE);
        }
    }

    private boolean isPaymentAmountNotMatching(Payment payment, Long fee) {
        return !payment.isSamePriceWith(fee);
    }

    private boolean hasAlreadyEnrolled(NsUser nsUser, Session session) {
        return nsUser.hasEnrolledSession(session);
    }

    private void registerSession(NsUser user, Payment payment) {
        user.enrollSession(this);
        user.addPayment(payment);
    }

}
