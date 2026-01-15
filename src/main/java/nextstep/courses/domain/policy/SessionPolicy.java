package nextstep.courses.domain.policy;

import nextstep.courses.domain.capacity.Capacity;
import nextstep.courses.domain.money.Money;

public interface SessionPolicy {
    void validate(Money paidAmount, Capacity capacity);
}
