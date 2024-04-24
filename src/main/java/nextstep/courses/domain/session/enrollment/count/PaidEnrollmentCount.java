package nextstep.courses.domain.session.enrollment.count;

import nextstep.courses.domain.session.MaxRegistrationCount;
import nextstep.courses.domain.session.RegistrationCount;

public class PaidEnrollmentCount extends AbstractEnrollmentCount{

    private final MaxRegistrationCount maxRegistrationCount;

    protected PaidEnrollmentCount(RegistrationCount registrationCount,
        MaxRegistrationCount maxRegistrationCount) {
        super(registrationCount);
        this.maxRegistrationCount = maxRegistrationCount;
    }

    @Override
    public boolean isRegistrationWithinCapacity() {
        return maxRegistrationCount.isCountNotOver(registrationCount);
    }
}
