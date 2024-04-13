package nextstep.courses.domain.session.strategy;

public class FreeSessionStrategy implements SessionStrategy {

    @Override
    public boolean canEnroll(final Money payment, final EnrollmentCount currentEnrollmentCount) {
        return true;
    }
}
