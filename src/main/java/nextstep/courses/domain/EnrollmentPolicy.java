package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public interface EnrollmentPolicy {
    PolicyType type();
    Long price();
    void validateEnrollment(Payment payment);
}
