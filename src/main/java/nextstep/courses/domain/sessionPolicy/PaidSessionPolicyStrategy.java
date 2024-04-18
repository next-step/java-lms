package nextstep.courses.domain.sessionPolicy;

import nextstep.payments.domain.Payment;

public class PaidSessionPolicyStrategy implements SessionPolicyStrategy {
    private final int capacity;
    private final long price;

    public PaidSessionPolicyStrategy(int capacity, long price) {
        this.capacity = capacity;
        this.price = price;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isCompletePay(Payment payment) {
        return payment.isSame(price);
    }
}
