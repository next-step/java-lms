package nextstep.courses.domain;

public interface SessionPolicy {

    void validate(Money payment, int currentEnrollmentCount);
}
