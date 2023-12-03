package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;

public class Session {
    public static final String INVALID_AMOUNT_MSG = "결제한 금액과 강의의 금액이 다릅니다.";
    public static final String NOT_REMAIN_MSG = "남은 자리가 없습니다.";
    public static final String NOT_RECRUITING_MSG = "강의 모집중인 강의가 아닙니다.";
    private final Long id;
    private final String title;
    private final SessionPeriod sessionPeriod;
    private final SessionStatus sessionStatus;
    private final CoverImage coverImage;
    private final Amount amount;
    private final EnrollmentCount enrollmentCount;

    public Session(final String title, final SessionPeriod sessionPeriod,
                   final SessionStatus sessionStatus, final CoverImage coverImage,
                   final Amount amount, final int enrollmentCount) {
        this(null, title, sessionPeriod, sessionStatus, coverImage, amount, enrollmentCount);
    }
    public Session(final String title, final SessionPeriod sessionPeriod, final SessionStatus sessionStatus) {
        this(null, title, sessionPeriod, sessionStatus, null, new Amount(0L), 0);
    }

    public Session(final Long id, final String title, final SessionPeriod sessionPeriod,
                   final SessionStatus sessionStatus, final CoverImage coverImage,
                   final Amount amount, final int enrollmentCount) {
        this.id = id;
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
        this.amount = amount;
        this.enrollmentCount = new EnrollmentCount(enrollmentCount);
    }

    public void enroll(final Payment payment) {
        checkAvailableEnroll(payment);

        checkRecruiting();

        enrollmentCount.decrease();
    }

    private void checkRecruiting() {
        if (this.sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException(NOT_RECRUITING_MSG);
        }
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

    public Long id() {
        return this.id;
    }
}
