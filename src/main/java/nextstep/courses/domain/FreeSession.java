package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class FreeSession implements SessionStrategy {
    @Override
    public boolean canEnroll(Payment payment) {
        return true;
    }
}
