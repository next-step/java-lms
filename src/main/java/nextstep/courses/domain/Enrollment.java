package nextstep.courses.domain;

import nextstep.courses.domain.code.EnrollmentStatus;
import nextstep.courses.domain.code.EnrollmentType;

public class Enrollment {
    private final EnrollmentType enrollmentType;
    private final EnrollmentStatus enrollmentStatus;
    private final int capacity;
    private final Amount amount;

    public Enrollment(String enrollmentType,
                      String enrollmentStatus,
                      int capacity,
                      long amount) {
        this(EnrollmentType.valueOf(enrollmentType), EnrollmentStatus.valueOf(enrollmentStatus), capacity,
                new Amount(amount));
    }

    public Enrollment(EnrollmentType enrollmentType,
                      EnrollmentStatus enrollmentStatus,
                      int capacity,
                      Amount amount) {
        this.enrollmentType = enrollmentType;
        this.enrollmentStatus = enrollmentStatus;
        this.capacity = capacity;
        this.amount = amount;
    }

    public void validateEnroll(long payment,
                               Students students) {
        enrollmentStatus.validateEnroll();
        enrollmentType.validateEnroll(payment, amount, capacity, students);
    }

}
