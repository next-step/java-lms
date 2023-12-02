package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class FreeSession extends Session {
    protected FreeSession(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        super(title, startDate, endDate);
    }

    protected FreeSession(final String title, final LocalDateTime startDate, final LocalDateTime endDate, final CoverImage coverImage) {
        super(title, startDate, endDate, coverImage);
    }

    @Override
    public void enroll(final Payment payment) {
        if (isNotRecruiting()) {
            throw new IllegalStateException("session is not recruiting");
        }

        increaseEnrollment();
    }
}
