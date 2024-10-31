package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

@FunctionalInterface
public interface SessionStrategy {
    boolean canEnroll(Payment payment);
}
