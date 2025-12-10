package nextstep.courses.domain.session.policy.capacity;

import nextstep.courses.domain.registration.Registrations;

public interface CapacityPolicy {
    void validate(Registrations registrations);
}
