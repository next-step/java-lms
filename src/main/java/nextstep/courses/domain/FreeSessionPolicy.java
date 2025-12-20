package nextstep.courses.domain;

public class FreeSessionPolicy implements SessionPolicy {

    @Override
    public void validate(Money payment, Capacity capacity, int currentEnrollmentCount) {
    }
}
