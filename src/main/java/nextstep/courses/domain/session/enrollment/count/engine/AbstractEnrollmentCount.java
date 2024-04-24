package nextstep.courses.domain.session.enrollment.count.engine;

import nextstep.courses.domain.session.enrollment.count.RegistrationCount;

public abstract class AbstractEnrollmentCount implements EnrollmentCount {

    protected final RegistrationCount registrationCount;

    protected AbstractEnrollmentCount(RegistrationCount registrationCount) {
        this.registrationCount = registrationCount;
    }

    @Override
    public void addRegistrationCount() {
        registrationCount.addValue();
    }
}
