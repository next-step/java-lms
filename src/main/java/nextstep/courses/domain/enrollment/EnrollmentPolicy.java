package nextstep.courses.domain.enrollment;

import nextstep.payments.domain.Payment;

public interface EnrollmentPolicy {
    PolicyType type();
    Long price();
    void validateEnrollment(Payment payment);
}
