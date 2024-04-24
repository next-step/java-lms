package nextstep.courses.domain.session.enrollment.count;

import nextstep.courses.domain.session.RegistrationCount;

public class FreeEnrollmentCount extends AbstractEnrollmentCount{

    protected FreeEnrollmentCount(RegistrationCount registrationCount) {
        super(registrationCount);
    }

    @Override
    public boolean isRegistrationWithinCapacity() {
        return registrationCount.isCountNotOver(Integer.MAX_VALUE);
    }
}
