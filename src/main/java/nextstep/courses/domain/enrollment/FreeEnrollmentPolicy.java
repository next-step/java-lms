package nextstep.courses.domain.enrollment;

import nextstep.payments.domain.Payment;

public class FreeEnrollmentPolicy implements EnrollmentPolicy{
    @Override
    public PolicyType type() {
        return PolicyType.FREE;
    }

    @Override
    public Long price() {
        return null;
    }

    @Override
    public void validateEnrollment(Payment payment) {
        // 무료강의는 검증하지 않는다
    }
}
