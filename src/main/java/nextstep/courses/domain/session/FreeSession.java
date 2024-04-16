package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeSession extends Session {
    public static final Long FREE_FEE = 0L;

    public FreeSession(Long sessionId, SessionPeriod sessionPeriod,
                       CoverImage coverImage, SessionStatusEnum sessionStatus) {
        super(sessionId, sessionPeriod, coverImage, sessionStatus);
        this.fee = FREE_FEE;
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
        this.users.add(user);
        user.enrollSession(this);
        user.addPayment(payment);
    }

}
