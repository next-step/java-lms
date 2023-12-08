package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;

public class FreeSession extends Session {

    public FreeSession(final Long id, final String title, final SessionPeriod sessionPeriod, final nextstep.courses.domain.session.SessionState sessionState, final CoverImage coverImage) {
        super(id, title, sessionPeriod, sessionState, coverImage);
    }

    @Override
    public void enroll(Payment payment) {
        validateSessionState();
    }
}
