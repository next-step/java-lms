package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class PaidSession extends Session {
    public static final String INVALID_AMOUNT_MSG = "결제한 금액과 강의의 금액이 다릅니다.";
    private Amount amount;
    private EnrollmentCount enrollmentCount;

    public PaidSession(final String title, final SessionPeriod sessionPeriod, final SessionStatus sessionStatus, final CoverImage coverImage, final Amount amount, final EnrollmentCount enrollmentCount) {
        this(null, title, sessionPeriod, sessionStatus, coverImage, amount, enrollmentCount, LocalDateTime.now(), null);
    }

    public PaidSession(final Long id, final String title, final SessionPeriod sessionPeriod, final SessionStatus sessionStatus, final CoverImage coverImage,
                       final Amount amount, final EnrollmentCount enrollmentCount, LocalDateTime createAt, LocalDateTime updatedAt) {
        super(id, title, sessionPeriod, sessionStatus, coverImage, createAt, updatedAt);
        this.amount = amount;
        this.enrollmentCount = enrollmentCount;
    }

    @Override
    public void enroll(final Payment payment) {
        checkAvailableEnroll(payment);

        checkRecruiting();

        enrollmentCount.decrease();
    }

    private void checkAvailableEnroll(final Payment payment) {
        if (enrollmentCount.isNotRemain()) {
            throw new IllegalArgumentException(NOT_REMAIN_MSG);
        }

        if (amount.isNotSame(payment)) {
            throw new IllegalArgumentException(INVALID_AMOUNT_MSG);
        }
    }

    public Amount amount() {
        return amount;
    }

    public EnrollmentCount enrollmentCount() {
        return enrollmentCount;
    }
}
