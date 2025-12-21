package nextstep.courses.domain.policy;

import nextstep.courses.domain.value.Money;

public class FreeSessionPolicy implements SessionPolicy {

    @Override
    public void validate(Money payment, int currentEnrollmentCount) {
    }
}
