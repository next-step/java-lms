package nextstep.courses.domain.session;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class Session extends BaseEntity {
    private static final String NOT_REMAIN_MSG = "남은 자리가 없습니다.";
    private static final String NOT_IN_PROGRESS_MSG = "강의 진행중인 강의가 아닙니다.";
    public static final String INVALID_AMOUNT_MSG = "결제한 금액과 강의의 금액이 다릅니다.";
    private static final String NOT_RECRUITNG_MSG = "모집중인 강의가 아닙니다.";
    private String title;
    private SessionPeriod sessionPeriod;
    private SessionStatus sessionStatus;
    private List<CoverImage> coverImages;
    private SessionType sessionType;
    private Amount amount;
    private EnrollmentCount enrollmentCount;
    private RecruitStatus recruitStatus;

    public Session(final String title, final SessionPeriod sessionPeriod, final SessionStatus sessionStatus,
                   final List<CoverImage> coverImages, final Amount amount, final EnrollmentCount enrollmentCount,
                   final RecruitStatus recruitStatus) {
        this(null, title, sessionPeriod, sessionStatus, coverImages, SessionType.of(amount), amount, enrollmentCount,
                recruitStatus, LocalDateTime.now(), null);
    }
    public Session(final Long id, final String title, final SessionPeriod sessionPeriod, final SessionStatus sessionStatus,
                   final List<CoverImage> coverImages, final SessionType sessionType, final Amount amount,
                   final EnrollmentCount enrollmentCount, final RecruitStatus recruitStatus,
                   final LocalDateTime createAt, LocalDateTime updatedAt) {
        super(id, createAt, updatedAt);
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.sessionStatus = sessionStatus;
        this.coverImages = coverImages;
        this.sessionType = sessionType;
        this.amount = amount;
        this.enrollmentCount = enrollmentCount;
        this.recruitStatus = recruitStatus;
    }

    private void checkSessionStatus() {
        if (sessionStatus.isNotInProcess()) {
            throw new IllegalArgumentException(NOT_IN_PROGRESS_MSG);
        }

        if (recruitStatus.isNotRecruiting()) {
            throw new IllegalArgumentException(NOT_RECRUITNG_MSG);
        }
    }
    public Enrollment enroll(NsUser user, final Payment payment) {
        checkAvailableEnroll(payment);

        enrollmentCount.decrease();

        return new Enrollment(user.getId(), payment.sessionId());
    }

    private void checkAvailableEnroll(final Payment payment) {
        checkSessionStatus();

        if (sessionType.isFree()) {
            return;
        }

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

    public List<CoverImage> coverImages() {
        return coverImages;
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

    public RecruitStatus recruitStatus() {
        return recruitStatus;
    }
}
