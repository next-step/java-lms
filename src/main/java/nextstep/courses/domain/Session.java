package nextstep.courses.domain;

import java.time.LocalDate;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

/**
 * 강의 엔터티 - 가변 객체
 */
public class Session {
    private final Long id;
    private final Period period;
    private SessionStatus status;
    private final EnrollmentPolicy enrollmentPolicy;
    // 현재 수강생 수
    private long enrolledCount = 0;

    public Session(Long id, Period period) {
        this(id, period, SessionStatus.PREPARING);
    }

    public Session(Long id, Period period, SessionStatus status) {
        this(id, period, status, EnrollmentPolicy.free());
    }

    public Session(Long id, Period period, SessionStatus status, EnrollmentPolicy enrollmentPolicy) {
        this.id = id;
        this.period = period;
        this.status = status;
        this.enrollmentPolicy = enrollmentPolicy;
    }

    public boolean canEnroll() {
        return status.equals(SessionStatus.OPEN) && enrollmentPolicy.canEnroll(enrolledCount);
    }

    public LocalDate startAt() {
        return period.startAt();
    }

    public LocalDate endAt() {
        return period.endAt();
    }

    public boolean isFree() {
        return enrollmentPolicy.isFree();
    }

    public void enroll(Payment payment) {
        if (!canEnroll()) {
            throw new CannotEnrollException("강의가 모집중이 아닙니다.");
        }


    }
}
