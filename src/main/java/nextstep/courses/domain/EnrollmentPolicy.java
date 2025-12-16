package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public interface EnrollmentPolicy {
    void validateEnrollment(Payment payment);
}
