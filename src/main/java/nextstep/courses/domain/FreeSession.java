package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class FreeSession extends Session {
    public FreeSession(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        super(title, startDate, endDate);
    }

    @Override
    public void enroll(final Payment payment) {
        if (isNotRecruiting()) {
            throw new IllegalStateException("session is not recruiting");
        }

        enrollStudent();
    }
}
