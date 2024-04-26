package nextstep.courses.domain.session.enrollment.count.engine;

import nextstep.courses.domain.session.enrollment.count.RegistrationCount;
import nextstep.courses.error.exception.MaxRegistrationExceededException;

public abstract class AbstractEnrollmentCount implements EnrollmentCount {

    protected final RegistrationCount registrationCount;

    protected AbstractEnrollmentCount(RegistrationCount registrationCount) {
        this.registrationCount = registrationCount;
    }

    @Override
    public void addRegistrationCount() {
        int addedRegistrationCount = registrationCount.addValue();

        if (addedRegistrationCount < 0) {
            throw new MaxRegistrationExceededException(registrationCount);
        }
    }
}
