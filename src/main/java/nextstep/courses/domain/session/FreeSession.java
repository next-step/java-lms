package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.users.domain.NsUser;

public class FreeSession extends Session {
    public static final Long FREE_FEE = 0L;

    public FreeSession(Long sessionId, SessionPeriod sessionPeriod,
                       CoverImage coverImage, SessionStatusEnum sessionStatus) {
        super(sessionId, sessionPeriod, coverImage, sessionStatus);
    }

    @Override
    public void enrollStudent(NsUser user) {
        if (!this.isSessionOpened()) {
            throw new IllegalArgumentException(SESSION_NOT_OPENED);
        }

        if (isPaymentAmountMatching(user)) {
            throw new IllegalArgumentException(ENROLLMENT_ALREADY_DONE);
        }

        this.users.add(user);
    }

    private boolean isPaymentAmountMatching(NsUser user) {
        return user.hasPaidForSession(getSessionId(), FREE_FEE);
    }
}
