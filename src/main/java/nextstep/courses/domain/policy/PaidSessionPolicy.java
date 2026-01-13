package nextstep.courses.domain.policy;

import nextstep.courses.domain.capacity.Capacity;
import nextstep.courses.domain.money.Money;

public class PaidSessionPolicy implements SessionPolicy {
    private final Money sessionFee;
    private final Capacity sessionCapacity;

    public PaidSessionPolicy(int sessionFee, int sessionCapacity) {
        this(new Money(sessionFee), new Capacity(sessionCapacity));
    }

    public PaidSessionPolicy(Money sessionFee, Capacity sessionCapacity) {
        this.sessionFee = sessionFee;
        this.sessionCapacity = sessionCapacity;
    }

    @Override
    public void validate(Money paidAmount, Capacity capacity) {
        validatePaidAmount(paidAmount);
    }

    private void validatePaidAmount(Money paidAmount) {
        if (!sessionFee.isEqualTo(paidAmount)) {
            throw new IllegalArgumentException("결제 금액이 일치하지 않습니다");
        }
    }
}
