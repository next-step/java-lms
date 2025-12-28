package nextstep.courses.domain.policy;

import nextstep.courses.domain.value.Money;

public class FreeSessionPolicy implements SessionPolicy {

    @Override
    public void validate(Money payment, int currentEnrollmentCount) {
    }

    @Override
    public SessionType type() {
        return SessionType.FREE;
    }

    @Override
    public Integer price() {
        return null;
    }

    @Override
    public Integer capacity() {
        return null;
    }
}
