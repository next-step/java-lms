package nextstep.courses.domain;

public interface SessionPolicy {

    void validate(Money payment, Capacity capacity, int currentEnrollmentCount);
}
