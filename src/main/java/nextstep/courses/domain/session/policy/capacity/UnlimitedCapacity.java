package nextstep.courses.domain.session.policy.capacity;

import nextstep.courses.domain.registration.Registrations;

public class UnlimitedCapacity implements CapacityPolicy {

    @Override
    public void validate(Registrations registrations) {
    }
}
