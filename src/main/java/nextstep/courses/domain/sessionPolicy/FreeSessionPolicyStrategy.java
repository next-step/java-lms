package nextstep.courses.domain.sessionPolicy;

import nextstep.payments.domain.Payment;

public class FreeSessionPolicyStrategy implements SessionPolicyStrategy {
    @Override
    public int getCapacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isCompletePay(Payment payment) {
        return true;
    }
}
