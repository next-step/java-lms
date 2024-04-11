package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session extends BaseTimeEntity{

    private final CoverImage coverImage;
    private final EnrollmentManager enrollmentManager;
    private final SessionPeriod sessionPeriod;

    public Session(CoverImage coverImage, EnrollmentManager enrollmentManager, SessionPeriod sessionPeriod) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.coverImage = coverImage;
        this.enrollmentManager = enrollmentManager;
        this.sessionPeriod = sessionPeriod;
    }

    public Session enroll(Payment payment) {
        if (this.enrollmentManager.canEnroll(payment)) {
            return new Session(this.coverImage, this.enrollmentManager.decreaseCount(), this.sessionPeriod);
        }
        throw new IllegalArgumentException("수강 신청을 할 수 없습니다.");
    }
}
