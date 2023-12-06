package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;

public class PaidSession extends Session {
    public static final String INVALID_AMOUNT_MSG = "결제한 금액과 강의의 금액이 다릅니다.";
    private Amount amount;
    private EnrollmentCount enrollmentCount;

    public PaidSession(final Long id, final String title, final SessionPeriod sessionPeriod, final SessionStatus sessionStatus, final CoverImage coverImage, final Amount amount, final int enrollmentCount) {
        super(id, title, sessionPeriod, sessionStatus, coverImage);
        this.amount = amount;
        this.enrollmentCount = new EnrollmentCount(enrollmentCount);
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
}
