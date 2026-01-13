package nextstep.courses.domain.policy;

import nextstep.courses.domain.money.Money;

public class FreeSessionPolicy implements SessionPolicy {
    @Override
    public void validate(Money paidAmount) {
        if (paidAmount != Money.FREE) {
            throw new IllegalArgumentException("무료 강의는 결제 금액이 존재할 수 없습니다.");
        }
    }
}
