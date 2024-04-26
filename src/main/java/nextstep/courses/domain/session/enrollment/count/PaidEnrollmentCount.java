package nextstep.courses.domain.session.enrollment.count;

import nextstep.courses.domain.session.enrollment.count.engine.AbstractEnrollmentCount;

public class PaidEnrollmentCount extends AbstractEnrollmentCount {

    private final MaxRegistrationCount maxRegistrationCount;

    public PaidEnrollmentCount(RegistrationCount registrationCount,
        MaxRegistrationCount maxRegistrationCount) {
        super(registrationCount);
        this.maxRegistrationCount = maxRegistrationCount;
    }

    @Override
    public boolean isRegistrationWithinCapacity() {
        return maxRegistrationCount.isCountNotOver(registrationCount);
    }

    @Override
    public int getRegistrationCount() {
        return registrationCount.getValue();
    }

    @Override
    public int getMaxRegistrationCount() {
        return maxRegistrationCount.getValue();
    }
}
