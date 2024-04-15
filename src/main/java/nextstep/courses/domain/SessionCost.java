package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class SessionCost {
    private final int cost;

    public SessionCost(int cost) {
        if (cost < 0)
            throw new IllegalArgumentException("비용은 음수 가 될 수 없습니다");
        this.cost = cost;
    }

    public boolean paymentCheck(Payment pay) {
        return cost == pay.getAmount();
    }
}
