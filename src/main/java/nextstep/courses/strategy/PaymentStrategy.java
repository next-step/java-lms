package nextstep.courses.strategy;

import nextstep.payments.domain.Payment;

public interface PaymentStrategy {
    boolean payable(Payment payment);
}
