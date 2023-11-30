package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class PaidSession extends Session {
    protected PaidSession(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate) {
        super(title, price, startDate, endDate);
    }

    protected PaidSession(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate, final CoverImage coverImage) {
        super(title, price, startDate, endDate, coverImage);
    }

    @Override
    public void ready() {
        setStatus(SessionStatus.READY);
    }

    @Override
    public void recruit() {
        setStatus(SessionStatus.RECRUITING);
    }

    @Override
    public void close() {
        setStatus(SessionStatus.CLOSED);
    }

    @Override
    public void enroll(final Payment payment) {
        if (isNotRecruiting()) {
            throw new IllegalStateException("session is not recruiting");
        }

        if (payment.isNotPaid(getPrice())) {
            throw new IllegalStateException("paid amount is different with price");
        }

        if (isReachedMaxStudentLimit()) {
            throw new IllegalStateException("max student limit is reached");
        }

        increaseEnrollment();
    }
}
