package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

@FunctionalInterface
public interface SessionStrategy {
    boolean canEnroll(Payment payment);
}
