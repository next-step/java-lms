package nextstep.courses.domain.session.enrollment.count;

import nextstep.courses.domain.session.RegistrationCount;

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
