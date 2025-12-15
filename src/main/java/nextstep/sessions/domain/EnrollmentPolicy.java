package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;

public interface EnrollmentPolicy {

    boolean canEnroll(Capacity capacity, Payment payment);

    void validate(Capacity capacity);
}
