package nextstep.courses.domain.session.strategy;

public interface SessionStrategy {

    boolean canEnroll(final Money payment, final EnrollmentCount currentEnrollmentCount);
}
