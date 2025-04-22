package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.Amount;
import nextstep.payments.domain.Payment;

public class Enrollment {
    private SessionStatus status;
    private final EnrollmentPolicy enrollmentPolicy;
    private int enrolledCount = 0;

    public Enrollment(SessionStatus status, EnrollmentPolicy enrollmentPolicy) {
        this.status = status;
        this.enrollmentPolicy = enrollmentPolicy;
    }

    /* ------------ 정책 검증 ------------ */
    // 모집중 상태인지
    public boolean isOpen() {
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

    public boolean isFree() {
        return enrollmentPolicy.isFree();
    }
}
