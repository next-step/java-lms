package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class FreeEnrollmentPolicy implements EnrollmentPolicy{
    @Override
    public void validateEnrollment(Payment payment) {
        // 무료강의는 검증하지 않는다
    }
}
