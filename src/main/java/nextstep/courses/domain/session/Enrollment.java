package nextstep.courses.domain.session;


import java.util.OptionalLong;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.Amount;
import nextstep.payments.domain.Payment;

/**
 * 수강 신청 클래스
 */

public class Enrollment {
    private int enrolledCount = 0;
    private final EnrollmentPolicy enrollmentPolicy;

    public Enrollment(EnrollmentPolicy enrollmentPolicy) {
        this.enrollmentPolicy = enrollmentPolicy;
    }

    /* 복원용 생성자 */
    public Enrollment(EnrollmentPolicy enrollmentPolicy, int initialCount) {
        this.enrollmentPolicy = enrollmentPolicy;
        this.enrolledCount = initialCount;
    }

    /* ------------ 정책 검증 ------------ */
    public OptionalLong remainingSeats() {
        return enrollmentPolicy.remainingSeats(enrolledCount);
    }

    public boolean hasCapacity() {
        return enrollmentPolicy.hasCapacity(enrolledCount);
    }

    // 실제 수강신청
    public void enroll(Payment payment) {
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
