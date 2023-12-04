package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;

public class FreeSession extends Session {

    public FreeSession(final Long id, final String title, final SessionPeriod sessionPeriod, final SessionStatus sessionStatus, final CoverImage coverImage) {
        super(id, title, sessionPeriod, sessionStatus, coverImage);
    }

    @Override
    public void enroll(final Payment payment) {
        checkRecruiting();
    }
}

