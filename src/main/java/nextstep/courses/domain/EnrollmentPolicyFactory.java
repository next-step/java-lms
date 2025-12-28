package nextstep.courses.domain;

import static nextstep.courses.domain.PolicyType.FREE;
import static nextstep.courses.domain.PolicyType.PAID;

public class EnrollmentPolicyFactory {
    public static EnrollmentPolicy create(String name, Long price) {
        PolicyType type = PolicyType.valueOf(name);

        if (type == FREE) {
            return new FreeEnrollmentPolicy();
        }

        if (type == PAID) {
            return new PaidEnrollmentPolicy(new Money(price));
        }

        throw new IllegalArgumentException();
    }
}
