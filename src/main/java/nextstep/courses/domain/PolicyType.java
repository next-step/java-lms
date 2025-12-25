package nextstep.courses.domain;

public enum PolicyType {
    FREE,
    PAID;

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
