package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class FreeSession extends Session {

    public FreeSession(final String title, final SessionPeriod sessionPeriod, final SessionStatus sessionStatus, final CoverImage coverImage) {
        super(title, sessionPeriod, sessionStatus, coverImage);
    }

    public FreeSession(final Long id, final String title, final SessionPeriod sessionPeriod, final SessionStatus sessionStatus,
                       final CoverImage coverImage, final LocalDateTime createAt, final LocalDateTime updatedAt) {
        super(id, title, sessionPeriod, sessionStatus, coverImage, createAt, updatedAt);
    }

    @Override
    public void enroll(final Payment payment) {
        checkRecruiting();
    }
}

