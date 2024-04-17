package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaidSession extends Session {
    public static final String OVER_MAX_ENROLLMENTS = "유료 강의는 강의 최대 수강 인원을 초과할 수 없습니다.";

    public PaidSession(Long sessionId, SessionPeriod sessionPeriod,
                       CoverImage coverImage, SessionStatusEnum sessionStatus,
                       int maxEnrollments, Long fee) {
        super(sessionId, sessionPeriod, coverImage, sessionStatus);
        this.maxEnrollments = maxEnrollments;
        this.fee = fee;
    }

    @Override
    public void enrollStudent(NsUser user, Payment payment) {
        assertAllConditions(user, payment);
        registerSession(user, payment);
    }

    private void assertAllConditions(NsUser user, Payment payment) {
        if (!this.isSessionOpened()) {
            throw new IllegalArgumentException(SESSION_NOT_OPENED);
        }

        if (isEnrollmentFull()) {
            throw new IllegalArgumentException(OVER_MAX_ENROLLMENTS);
        }

        if (isPaymentAmountNotMatching(payment, fee)) {
            throw new IllegalArgumentException(PAYMENT_NOT_MATCHING);
        }

        if (hasAlreadyEnrolled(user, this)) {
            throw new IllegalArgumentException(ENROLLMENT_ALREADY_DONE);
        }
    }

    private boolean isEnrollmentFull() {
        return maxEnrollments == this.users.getNumberOfUsers();
    }

    private boolean isPaymentAmountNotMatching(Payment payment, Long fee) {
        return !payment.isSamePriceWith(fee);
    }

    private boolean hasAlreadyEnrolled(NsUser nsUser, Session session) {
        return nsUser.hasEnrolledSession(session);
    }

    private void registerSession(NsUser user, Payment payment) {
        this.users.add(user);
        user.enrollSession(this);
        user.addPayment(payment);
    }

}
