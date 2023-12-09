package nextstep.courses.domain.session;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session extends BaseEntity {
    private static final String NOT_REMAIN_MSG = "남은 자리가 없습니다.";
    private static final String NOT_RECRUITING_MSG = "강의 모집중인 강의가 아닙니다.";
    public static final String INVALID_AMOUNT_MSG = "결제한 금액과 강의의 금액이 다릅니다.";
    private String title;
    private SessionPeriod sessionPeriod;
    private SessionStatus sessionStatus;
    private CoverImage coverImage;
    private SessionType sessionType;
    private Amount amount;
    private EnrollmentCount enrollmentCount;

    public Session(final String title, final SessionPeriod sessionPeriod, final SessionStatus sessionStatus,
                   final CoverImage coverImage, final Amount amount, final EnrollmentCount enrollmentCount) {
        this(null, title, sessionPeriod, sessionStatus, coverImage, SessionType.of(amount), amount, enrollmentCount,
                LocalDateTime.now(), null);
    }
    public Session(final Long id, final String title, final SessionPeriod sessionPeriod, final SessionStatus sessionStatus,
                   final CoverImage coverImage, final SessionType sessionType, final Amount amount, final EnrollmentCount enrollmentCount,
                   final LocalDateTime createAt, LocalDateTime updatedAt) {
        super(id, createAt, updatedAt);
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.amount = amount;
        this.enrollmentCount = enrollmentCount;
    }

    private void checkRecruiting() {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException(NOT_RECRUITING_MSG);
        }
    }
    public Enrollment enroll(NsUser user, final Payment payment) {
        checkAvailableEnroll(payment);

        enrollmentCount.decrease();

        return new Enrollment(user.getId(), payment.sessionId());
    }

    private void checkAvailableEnroll(final Payment payment) {
        if (sessionType.isFree()) {
            return;
        }

        checkRecruiting();

        if (enrollmentCount.isNotRemain()) {
            throw new IllegalArgumentException(NOT_REMAIN_MSG);
        }

        if (amount.isNotSame(payment)) {
            throw new IllegalArgumentException(INVALID_AMOUNT_MSG);
        }
    }

    public String title() {
        return title;
    }

    public SessionPeriod sessionPeriod() {
        return sessionPeriod;
    }

    public SessionStatus sessionStatus() {
        return sessionStatus;
    }

    public CoverImage coverImage() {
        return coverImage;
    }

    public Amount amount() {
        return amount;
    }

    public EnrollmentCount enrollmentCount() {
        return enrollmentCount;
    }

    public SessionType sessionType() {
        return sessionType;
    }
}
