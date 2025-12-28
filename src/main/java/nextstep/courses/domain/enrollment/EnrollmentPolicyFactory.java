package nextstep.courses.domain.enrollment;

import static nextstep.courses.domain.enrollment.PolicyType.FREE;
import static nextstep.courses.domain.enrollment.PolicyType.PAID;

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
