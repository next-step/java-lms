package nextstep.courses.domain.session.policy.capacity;

import nextstep.courses.domain.registration.Registrations;

public class LimitedCapacity implements CapacityPolicy {
    private final int maxCapacity;

    public LimitedCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void validate(Registrations registrations) {
        registrations.validateCapacity(maxCapacity);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}

