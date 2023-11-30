package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class PaidSession extends Session {
    public PaidSession(final String title, final double price, final LocalDateTime startDate, final LocalDateTime endDate) {
        super(title, price, startDate, endDate);
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

        enrollStudent();
    }
}
