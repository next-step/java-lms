package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;

public class Session {
    public static final String INVALID_AMOUNT_MSG = "결제한 금액과 강의의 금액이 다릅니다.";
    public static final String NOT_REMAIN_MSG = "남은 자리가 없습니다.";
    private final Long id;
    private final String title;
    private final SessionPeriod sessionPeriod;
    private final SessionStatus sessionStatus;
    private final CoverImage coverImage;
    private final Amount amount;
    private final EnrollmentCount enrollmentCount;

    public Session(final Long id, final String title, final SessionPeriod sessionPeriod,
                   final SessionStatus sessionStatus, final CoverImage coverImage,
                   final Amount amount, final EnrollmentCount enrollmentCount) {
        this.id = id;
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
        this.amount = amount;
        this.enrollmentCount = enrollmentCount;
    }

    public void enroll(final Payment payment) {
        checkAvailableEnroll(payment);

        enrollmentCount.decrease();
    }

    private void checkAvailableEnroll(final Payment payment) {
        if (isFree()) {
            return;
        }

        if (enrollmentCount.isNotRemain()) {
            throw new IllegalArgumentException(NOT_REMAIN_MSG);
        }

        if (amount.isNotSame(payment)) {
            throw new IllegalArgumentException(INVALID_AMOUNT_MSG);
        }
    }

    private boolean isFree() {
        return amount.isFree();
    }
}
