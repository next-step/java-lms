package nextstep.courses.domain.policy;

import nextstep.courses.domain.value.Money;

public interface SessionPolicy {

    void validate(Money payment, int currentEnrollmentCount);

    SessionType type();

    Integer price();

    Integer capacity();
}
