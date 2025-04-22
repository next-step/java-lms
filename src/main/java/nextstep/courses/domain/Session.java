package nextstep.courses.domain;

import java.time.LocalDate;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

/**
 * 강의 엔터티 - 가변 객체 : Aggregate Root
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

    /* ------------ 정책 검증 ------------ */
    // 모집중 상태인지
    private boolean isOpen() {
        return status.equals(SessionStatus.OPEN);
    }

    // 수강신청이 가능한 "상태"인지 체크 (모집중 + 좌석 여유)
    public boolean canEnroll() {
        return isOpen()
            && enrollmentPolicy.hasCapacity(enrolledCount);
    }

    // 실제 수강신청
    public void enroll(Payment payment) {
        if (!isOpen()) {
            throw new CannotEnrollException("강의가 모집중이 아닙니다.");
        }
        if (!enrollmentPolicy.matchesPayment(payment.pay())) {
            throw new CannotEnrollException("결제 금액이 수강료와 일치하지 않습니다.");
        }
        enrollmentPolicy.validateEnrollment(enrolledCount);
        enrolledCount++;
    }

    /* ------------ 정보성 메서드 ------------ */
    public Amount price() {
        return enrollmentPolicy.price();
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
}
